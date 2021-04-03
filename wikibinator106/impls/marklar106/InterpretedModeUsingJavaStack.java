/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;
import wikibinator106.impls.marklar106.*;
import wikibinator106.spec.*;

/** Works for all calls if you have enough memory and compute time. Is the least efficient,
compared to (TODO) optimizations by lwjgl opencl / GPU and javassist etc.
Those optimizations will retreat to lesser optimizations (such as just float double int long math in CPU)
or this where they dont know how to optimize a certain lambda call.
Even the multiply of 2 ints is expensive in interpreted mode
since it has to compute every bit as lambdas and could literally be
a billion times slower than a GPU optimization of the same lambdas,
so optimizations are very important to hook in by λ.setCompiled(EvalerChain).
Only lambda calls in very regular patterns (such as matrix multiply) can be GPU optimized.
More lambda calls can be CPU optimized (such as using javassist). Some wont be optimized
but will be so few they are not the bottleneck, and those use interpreted mode.
*/
public class InterpretedModeUsingJavaStack implements Evaler<fn>{
	
	public static final InterpretedModeUsingJavaStack instance = new InterpretedModeUsingJavaStack();
	
	public static final EvalerChain chain = new SimpleEvalerChain(instance);
	
	protected long howManyTimesReturnedFromCache;
	
	protected long howManyTimesPutInCache;
	
	public $<fn> eval(long gas, fn func, fn param){
		if(gas <= 0) throw new RuntimeException("Since everything costs at least 1 gas, including calling this to check the gas, caller should not have called this. gas="+gas);
		gas--;
		
		fn ret = AxfprCache.getOrNull(func, param);
		if(ret != null){
			if(lgCacheStatsEveryTime){
				howManyTimesReturnedFromCache++;
				lg("howManyTimesReturnedFromCache="+howManyTimesReturnedFromCache+" howManyTimesPutInCache="+howManyTimesPutInCache
					+" ret/put="+((double)howManyTimesReturnedFromCache/howManyTimesPutInCache));
			}
			return new $<fn>(gas,ret); //return from cache. Cache never expires, unless you need the memory back.
		}
		
		byte funcCurriesMore = func.curriesMore();
		byte paramCurriesMore = param.curriesMore();
		boolean funcIsHalted = funcCurriesMore>0;
		boolean paramIsHalted = paramCurriesMore>0;
		
		if(!funcIsHalted || !paramIsHalted){
			//In this wikibinator106 VM, curriesMore>0 (1..127) is always true cuz uses java stack
			//and doesnt create λ until that returns, but in other VMs curriesMore (0..127)could equal 0.
			throw new RuntimeException("funcCurriesMore="+funcCurriesMore+" paramCurriesMore="+paramCurriesMore);
		}
		boolean funcIsClean = func.isClean();
		if(funcIsClean && !param.isClean()){
			//FIXME pay gas for this or require that every dirty fn, in its constructor, have ptr to its clean form
			//so its bigO(1). For now, just get the logic working regardless of bigO that will be guaranteed later.
			param = truncateToClean_ignoringCost(param);
		}
		
		if(!func.evalsOnNextCurry()){
		//if((funcCurriesMore > 1 || func.curriesSoFar() < Marklar106bId.opIsKnownAt) & !func.isAx() & !func.isCbt()){
			//"FIXME need to check areSameSizeCbts at each curry, not just the last, similar to ax."
			
			//forkEdit to append another param cuz not enough params to eval yet and nothing to eval before last param
			ret = cp(func,param);
			
			//TODO just funcCurriesMore > 1 ?
			//If 0..4 params has curriesAll of 5, which is when the Op is known,
			//then curriesAll will be 1 before Op is known (at curriesSoFar==4).
			//If instead set that to 6, which includes comment,
			//then wouldnt need a separate check for curriesSoFar==4.
			//Also, (func.curriesSoFar() < Marklar106bId.opIsKnownAt)==(func.op6Bits()<32),
			//(other than maybe op6Bits==0 aka deeplazy but that never happens in this wikibinator106 VM).
		}else{
			//eval. funcCurriesMore==1.
			//the high 1 bit (if exists, else is deeplazy) tells size of bitstring 0..5 bits. Get those 5 bits.
			fn findFuncBody = func; //in case of curry1..curry16
			Op op = Op.ordinalToOp(func.op6Bits()&31);
			switch(op){
			case zero: case one:
				{
					if(funcCurriesMore==1){
						//its becoming so big it doesnt fit in cbt, so continue as Op.growinglist.
						ret = growinglist.e(nil).e(func).e(param);
					}else{
						if(funcIsClean && param.isClean()){ //cbt/bitstring optimization
							//TODO return some wrapper of bitstring like LongBlob (todo will rename that to PowOf2SizeLongArrayBlob?)
							//or blob of non-pow-of-2-aligned size that represents a powOf2 size cbt just doesnt store the padding,
							//in some cases. Which cases?
							boolean areSameSizeCbts = param.isCbt() && funcCurriesMore==paramCurriesMore;
							//A cbt called on anything is a cbt twice as big up to 2^120 bits but after that goes in growinglist.
							ret = cp(func, areSameSizeCbts?param:func);
							//FIXME pay gas but dont do this part cuz its bigO(1) to compute: get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
						}else{
							throw new RuntimeException("normal callpair instead of cbt optimization");
							//FIXME pay gas but dont do this part cuz its bigO(1) to compute: get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
						}
					}
				}
			break;case fal:
				ret = param; //return second of 2 params
			break;case tru:
				ret = func.r(); //second last param. return first of 2 params.
			break;case getfunc:
				ret = param.l(); //l and getfunc mean the same thing
			break;case getparam:
				ret = param.r(); //r and getparam mean the same thing
			break;case isleaf:
				ret = param.isLeaf() ? (funcIsClean?t:T) : (funcIsClean?f:F);
			break;case isclean:
				ret = param.isClean() ? (funcIsClean?t:T) : (funcIsClean?f:F); 
			break;case wiki:
				{
					$<fn> wikiReturned = wiki(gas, param);
					ret = wikiReturned.fn;
					gas = wikiReturned.gas;
				}
			break;case fpr:
				{
					//TODO use derived equals function instead of .equals, which will call the same thing (TODO)
					//wrapped in an Evaler instance, but to "close the loop" of reflection/selfAwareness
					//a wikibinator106 VM has to be implemented using only calls of λ.e(long,λ), and at
					//user level code (made of combos of calling λ on λ) create debugStepOver and debugStepInto λs etc.
					//but for now do this...
					
					fn funcL = func.l();
					fn w = funcL.l().r(); //fourth last param
					fn x = funcL.r(); //third last param
					fn y = func.r(); //second last param
					//Ignore last param, which is only there to delay eval.
					//axa and axb look for its first param called on u returns u vs anything except u
					ret = w.e(x).equals(y) ? u : uu;
					//Function equality checking costs average constant (bigO(1))
					//and worst case number of childs reachable deeply.
					//They say no lambda function can detect equality vs nonequality of 2 given lambda functions,
					//but I say thats because they are not pattern-calculus functions
					//which are a subset of lambdas, so given that we know the complement of that set
					//is not possible here, can prove more things about it,
					//and its not exactly the same thing cuz I'm including reflect ops (l r isleaf isclean)
					//in function behaviors since a function can be given a pointer to itself
					//(or already have such a pointer by being in a curry1..curry16 op)
					//and call reflect ops on itself and vary its behaviors depending on that.
					//For example, (S (T (S I I)) (T (S I I))) is a function that infinite loops for every possible param,
					//and you can make a function that answers T vs F
					//depending if its param is (S (T (S I I)) (T (S I I))) vs some other function,
					//and such a function is (Equals (S (T (S I I)) (T (S I I)))),
					//but todo as of 2021-3-1 I havent derived the equals function in wikibinator106 yet
					//but have in earlier forks of it and there wont be a problem doing so after the basic testcases pass.
					//Similarly, (Equals (Equals (S (T (S I I)) (T (S I I)))) (Equals (S (T (S I I)) (T (S I I)))))->T
					//and (Equals Equals Equals)->T and (Equals (Equals Equals) Equals)->F,
					//and Equals is just a word I'm using to refer to something you can build
					//by calling the universal function on itself in various combos (and will be optimized with an instance of Evaler.java)
				
					//FIXME get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
				}
			break;case axa:
				{
					if(func.curriesMore()==2){ //(axa x) verifies constraint. (axa x y)->(x (t y)).
						$<fn> constraintReturned = param.e(gas,u);
						if(constraintReturned.fn == u){ //verified constraint. Return (axa param) which is the constraint.
							//(axa x) is halted/verifiedConstraint if (x u) -> u.
							//constraintReturned.fn != null && constraintReturned.fn.isCleanLeaf()
							ret = cp(func,param); //func is axa or Axa
						}else{ //did not verify constraint. May be proven failed failed or did not have enough gas to verify it.
							//if returns anything except u/cleanLeaf, axa is disproven, and axb is proven.
							//TODO should the opcodes be slightly redesigned to allow axa to return an axb?
							boolean disprovedConstraint = constraintReturned.fn != null;
							if(Const.isDisproofOfOneKindOfAxReturnsTheOtherKindOfAx && disprovedConstraint){
								//disproof of [one of axa or axb] proves the other, but if nonhalting then neither can be
								//proven and neither is true so if you're using nsat colors at a lower level than lambdas,
								//using at least 3 colors: axa, axb, nonhalting, and maybe also color for leaf, normalcall,
								//etc, see comments and incomplete code
								//in earlier forks of wikibinator (such as wikibinator101..105).
								//I'm creating Op.isDisproofOfOneKindOfAxReturnsTheOtherKindOfAx in case want to change that later.
								ret = cp(funcIsClean?axb:Axb,param);
							}else{
								ret = null;
							}
						}
						gas = constraintReturned.gas;
									//FIXME thats what to do at first param of axa/axb. at second param,
									//call first param on T/t/F/f of second param for turing complete types.
					}else{ //curriesMore == 1
						ret = func.r().e(t(param)); //(axa x y)->(x (t y)).
					}
				}
			break;case axb:
				{
					if(func.curriesMore()==2){ //(axb x) verifies constraint. (axb x y)->(x (f y)).
						//see comments in axa.
						$<fn> constraintReturned = param.e(gas,u);
						if(constraintReturned.fn != null && constraintReturned.fn != u){ //verified constraint. Return (axb param) which is the constraint.
							//(axb x) is halted/verifiedConstraint if (x u) -> anything_except_u.
							ret = cp(func,param); //func is axb or Axb
						}else{
							boolean disprovedConstraint = constraintReturned.fn != null;
							if(Const.isDisproofOfOneKindOfAxReturnsTheOtherKindOfAx && disprovedConstraint){
								ret = cp(funcIsClean?axa:Axa,param);
							}else{
								ret = null;
							}
						}
						gas = constraintReturned.gas;
						//FIXME thats what to do at first param of axa/axb. at second param,
						//call first param on T/t/F/f of second param for turing complete types.
					}else{ //curriesMore == 1
						ret = func.r().e(f(param)); //(axb x y)->(x (f y)).
					}
				}
			break;case pair:
				{
					fn x = func.l().r(); //third last param
					fn y = func.r(); //second last param
					ret = param.e(x).e(y);
					//FIXME get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
				}
			break;case growinglist:
				{
					
					//(growinglist prevList ...64 things... nextThing)
					// -> (growinglist (growinglist prevList ...64 things...) nextThing).
					ret = cp(funcIsClean?growinglist_u:Growinglist_u, func, param);
					
					
					
					/*OLD...
					
					//(growinglist x y z) -> (growinglist (growinglist x y) z).
					fn x = func.l().r(); //third last param
					fn y = func.r(); //second last param
					//FIXME should the choice of Growinglist vs growinglist (clean/dirty growinglist)
					//depend on if func is Growinglist vs growinglist?
					//ret = growinglist.e(growinglist.e(x).e(y)).e(param);
					fn g = funcIsClean ? growinglist_u : Growinglist_u;
					fn growinglistOfX = cp(g,u); //nil/u as prefix of growinglist, similar to nil as suffix of linkedlist/[]
					fn growinglistOfXY = cp(growinglistOfX,y);
					ret = cp(cp(g,growinglistOfXY),param); //growinglistOfXYParam
					//ret = cp(cp(g,cp(g,cp(g,x),y)),param);
					//causes infloop to call eval inside eval this way, so use cp instead: ret = g.e(g.e(x).e(y)).e(param);
					//FIXME get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
					*/
				}
				//FIXME get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
			break;case typeval:
				{
					//(typeval x y z)->(y z). Normally just keep it as (typeval x y)
					//such as (typeval "image/jpeg" ...bytesofjpgfile...) as a semantic.
					//If you want turingComplete types, use Op.axa and Op.axb.
					//fn type = func.l().r() //only there to be seen by reflection ops (l r isleaf isclean).
					fn instance = func.r(); //second last param
					ret = instance.e(param); //act like the typed instance is the instance by itself
					//FIXME get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
				}
			break;case trecurse:
				{
					fn x = func.l().r(); //third last param
					fn y = func.r(); //second last param
					//same as [x.e(param).e(y.e(param)) except with checking gas at each step]
					//except suggests to VM it can be threaded (considering if the overhead to start and sync
					//threads likely costs less than doing that call single threaded). In this early
					//prototype wikibinator106 VM, uses only 1 CPU thread for evaling, but many GPU threads
					$<fn> forkReturned = fork(gas, x, y, param);
					ret = forkReturned.fn;
					gas = forkReturned.gas;
				}
			break;case curry16: findFuncBody = findFuncBody.l();
			case curry15: findFuncBody = findFuncBody.l(); //dont break, go 1 deeper each curry. funcBody at param 7
			case curry14: findFuncBody = findFuncBody.l();
			case curry13: findFuncBody = findFuncBody.l();
			case curry12: findFuncBody = findFuncBody.l();
			case curry11: findFuncBody = findFuncBody.l();
			case curry10: findFuncBody = findFuncBody.l();
			case curry9: findFuncBody = findFuncBody.l();
			case curry8: findFuncBody = findFuncBody.l();
			case curry7: findFuncBody = findFuncBody.l();
			case curry6: findFuncBody = findFuncBody.l();
			case curry5: findFuncBody = findFuncBody.l();
			case curry4: findFuncBody = findFuncBody.l();
			case curry3: findFuncBody = findFuncBody.l(); //dont break, go 1 deeper each curry. funcBody at param 7
			case curry2: findFuncBody = findFuncBody.l();
			case curry1: //1 less findFuncBody = findFuncBody.l() for all of them
			//FIXME does this run "findFuncBody = findFuncBody.l();" 1 too many/few times?
			fn funcBody = findFuncBody.r();
			lg("evaling. op="+op+". funcBody = "+funcBody);
			lg("evaling. op="+op+".     func = "+func);
			lg("evaling. op="+op+"     param = "+param);
			//same as ret = funcBody.e(pair_or_Pair.e(func).e(param)) but faster cuz know pair always halts on 2 params,
			fn p = funcIsClean ? pair : Pair;
			fn curryDatastruct = cp(cp(p,func),param);
			lg("curryDatastruct = "+curryDatastruct+", about to call funcBody on it");
			lg("About to call ("+funcBody+" "+curryDatastruct+")");
			ret = funcBody.e(curryDatastruct); //(funcBody [allParamExceptLast lastParam])
			}
			//FIXME??? does [allParamExceptLast lastParam] mean Pair when either of those 2 is dirty, else means pair?
			//	Its just syntax either way, not a problem for the universal function.
			//FIXME get $<fn> and verify gas at each step, but TODO create a func in ImportStatic to do a small forest of such calls automatically, instead of hardcoding it here
		}
		//Ret may have been set to null and gas left as a large positive number
		//cuz knew couldnt finish the requested work so did not waste the gas on it (ret==null && gas>0).
		//Or may have run out of gas (gas==0) so set ret to null.
		//Or may have used some gas and finished the requested work (ret!=null && gas>0).
		if(gas == 0) ret = null;
		if(ret != null){
			AxfprCache.put(func, param, ret);
			if(lgCacheStatsEveryTime) howManyTimesPutInCache++;
		}
		//If ret == null, this means didnt have enough gas to do the requested calculation,
		//and giving back whatever amount of gas was not used.
		return new $(gas,ret);
	}
	
	public static boolean areBothCleanCbtsAndSameSize(fn x, fn y){
		return x.isCleanCbt() && y.isCleanCbt() && x.curriesMore()==y.curriesMore();
	}

	public $<fn> wiki(long maxSpend, fn param){
		return WikiState.e(maxSpend,param);
	}

	public fn u(boolean isClean){
		throw new RuntimeException("TODO");
	}

	public fn op(boolean isClean, wikibinator106.spec.Op o){
		throw new RuntimeException("TODO");
	}

	public fn w(Object wrapMe){
		throw new RuntimeException("TODO");
	}

	public fn ww(Object wrapMe){
		throw new RuntimeException("TODO");
	}

}
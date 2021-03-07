/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import java.util.Arrays;

import wikibinator106.spec.*;

public class ImportStatic{
	
	/** log headers, for testing? */
	public static final boolean lgHeader = false;
	
	private static boolean assertionsAreOn_;
	public static final boolean assertionsAreOn;
	static{
		assert (assertionsAreOn_=true);
		assertionsAreOn = assertionsAreOn_;
		lg("assertionsAreOn="+assertionsAreOn);
	}
	
	/** the clean universal function aka the leaf which all paths in binary forest of call pairs lead to.
	Theres also a dirty form of it, which is a different universal function. The dirty layer is above the clean layer.
	The clean layer is universal by itself and cant see or create dirty objects,
	and if you call a clean on a dirty, the dirty is truncated to clean before its used,
	but dirty can use and create clean or dirty and combos of them.
	The difference between clean and dirty is when clean calls wiki its always nonhalting, and dirty can use Wiki,
	which is important since Wiki is where all nondeterminism andOr nonstandard andOr hard to sync (SyncLevel.slDirty) things go,
	and if something goes wrong, which it will trillions of times per second in the p2p network,
	sync will fork and merge blockchain-like (but actually web of merkle forest which syncs extremely faster)
	toward agreement of calling what on what returns what based on constraints like (L x (R x)) equals x forall x.
	In the clean layer, there is at most 1 correct answer to every question,
	so if something goes wrong in the dirty layer, the clean layer will keep working and can be used
	in various new experimental ways created at runtime by people and AIs in the system to experiment with
	what may have gone wrong and come up with theories and processes how to fix it,
	or just retreat to the clean layer and use just that for a while or permanently.
	*/
	public static final fn u = CleanLeaf.instance;
	public static final fn nil = u;
	
	public static final fn U = DirtyLeaf.instance;
	
	public static final fn uu = u.p(u);
	
	public static String toString(Object o){
		return o==null ? "null" : o.toString();
	}
	
	public static void lg(Object o){
		lg(toString(o));
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	public static fn L(fn parent){
		return parent.l();
	}
	
	public static fn R(fn parent){
		return parent.r();
	}
	
	/** TODO create e(long,fn...) a counterpart of e(fn), that limits by gas */
	public static fn e(fn... calls){
		//This could also be written as x = I, and loop over x = x.e(x), but avoid the extra call
		if(calls.length == 0) return I; //FIXME I vs i?
		fn x = calls[0];
		for(int j=1; j<calls.length; j++) x = x.e(calls[j]);
		return x;
	}
	
	/*TODO
	public static $<fn> e(long maxSpend, fn... calls){
		if(calls.length == 0) return I; //FIXME I vs i?
		$<fn> x = 
	}*/
	
	/** returns a halted call pair without evaling. If you call this on something that should eval,
	it may break lots of stuff that depends on that evalling as the func_param_return cache
	will answer this afterward, at least until uncached, and in further caches affected by that, and so on.
	This should only be called by VM code,
	and others use λ.e(λ) or λ.e(long,λ) instead which are safe to call in any combos.
	*/
	public static fn cp(fn func, fn param){
		fn ret = AxfprCache.getOrNull(func, param);
		if(ret == null){
			ret = new Funcall(func, param);
			AxfprCache.put(func, param, ret);
		}
		return ret;
	}
	
	/** cp(a,b,c,d) is cp(cp(cp(a,b),c),d) */
	public static fn cpp(fn func, fn... params){
		for(fn param : params) func = cp(func,param);
		return func;
	}
	
	public static fn s(fn a, fn b){
		return cp(cp(s,a),b);
	}
	
	/** s(a,b,c,d) is s(s(s(a,b),c),d). s(a,b) is cp(cp(s,a),b). */
	public static fn ss(fn metafunc, fn... metaparams){
		for(fn metaparam : metaparams) metafunc = s(metafunc,metaparam);
		return metafunc;
	}
	
	public static fn st(fn metafunc, fn... metaparams){
		return ss(t(metafunc),metaparams);
	}
	
	public static fn cp(boolean isClean, fn func, fn param){
		fn ret = AxfprCache.getOrNull(func, param);
		if(ret == null){
			ret = new Funcall(func, param);
			AxfprCache.put(func, param, ret);
		}
		return ret;
	}
	
	/*public static fn cp(fn func, fn param){
		if(func == ax || func == Ax) throw new RuntimeException("func is ax or Ax: "+func+" so it has to call its param on leaf to know its color proof, color disproof, or if it doesnt halt (which you probably wouldnt know) its color wordsalad. else its color normal.");
		return cp(λColor.nonaxof1paramcallNormal, func, param);
	}
	
	/** call pair *
	public static fn cp(λColor color, fn func, fn param){
		
		//FIXME check for Pairnode and Curnode so dedup works with their lazyEval and use of g(int) funcs,
		//but maybe do it in a more general way using an int
		//as a way to say which parts (1 2 4 8 16) are lazyEvaled (but inside them is not, skipping parts), etc,
		//though there might be deeper than that in treemap optimizations (it might have 4-7 params?) so need long?
		//
		//Use λ.isSkip(int binheapIndex) for that? Thats what its designed for, but I'm unsure if its an efficient way.
		
		//FIXME use hashtable for instant partial dedup (and ids for lazy perfect dedup)
		return new Simpleλ(color, func, param);
	}*/
	
	public static fn cp(fn... list){
		fn x = list[0];
		for(int i=1; i<list.length; i++) x = cp(x,list[i]);
		return x;
	}
	
	public static void x(String throwMessage){
		throw new RuntimeException(throwMessage);
	}
	
	public static fn bootOp(fn... params){
		fn x = u;
		for(fn param : params) x = cp(x,param);
		return x;
	}
	
	/** the isLeafsByte() for parent whose 2 childs are funcsIsleafsByte and anything. */
	public static byte nextIsleafsByte(byte funcsIsleafsByte){
		return (byte)((funcsIsleafsByte<<1)|1);		
	}
	
	/*FIXME??? u and (u u) must both be clean, so clean ops can be defined with those in their prefix.
	but FIXME I want there to be only 1 dirtyWiki (first param of u is the dirty one, then rest of prefix leads to wiki).
	Should (u (u u)) be the dirty prefix?
	That would make (u (u u) u u u u u) be dirtyWiki, but (u "hello" u u u u u) is also a dirtyWiki,
	if we go by the params being leaf vs nonleaf.
	Or could define wiki as infinitelooping if its first param (of 7) is not 1 of u or uu.
	Or could define that (u u) is the prefix of dirty things but u and (u u) are both clean.*/
	
	
	/*OLD... Op.deepLazy(0)
	Op.wiki(1)
	Op.isLeaf(1)
	Op.l(1)
	Op.r(1)
	Op.t(2)
	Op.fi(2)
	Op.curry(2)
	Op.cleanCall(2)
	Op.s(3)
	Op.pair(3)
	Op.typeval(3)
	Op.ax(4)*/
	
	
	/*
	ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
	ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
	ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
	ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
	ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
	ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
	ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
	ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
	XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ     λ)    ? //1
	XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ   (λ λ))  ? //0
	XXXXXXXX (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //infcur_if_next_param_is_leaf_else_curry_if_its_unarynum
	XXXXXXXX (λ   λ   (λ λ)   λ   (λ λ))  ?     ?     ? //typeval_and_the_2_get_truthval_ops
	ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp
	*/
	
	
	private static final fn[] op6BitsToCleanFn;
	private static final fn[] op6BitsToDirtyFn;
	static{
		op6BitsToCleanFn = new fn[64];
		op6BitsToDirtyFn = new fn[op6BitsToCleanFn.length];
		//op6BitsToCleanFn[0] is null cuz deeplazy which is not clean or dirty but unknown.
		//op6BitsToDirtyFn[0] is null cuz deeplazy which is not clean or dirty but unknown.
		fn uu = u.p(u);
		op6BitsToCleanFn[1] = u;
		op6BitsToDirtyFn[1] = U;
		for(int i=2; i<64; i++){
			fn param = (i&1)==0 ? u : uu;
			op6BitsToCleanFn[i] = op6BitsToCleanFn[i/2].p(param);
			op6BitsToDirtyFn[i] = op6BitsToDirtyFn[i/2].p(param);
		}
	}
	
	/** param is 1..63, or 0 to get null meaning deeplazy. */
	public static fn op6BitsToCleanFn(byte op6Bits){
		return op6BitsToCleanFn[op6Bits];
	}
	
	/** param is 1..63, or 0 to get null meaning deeplazy. */
	public static fn op6BitsToDirtyFn(byte op6Bits){
		return op6BitsToDirtyFn[op6Bits];
	}
	
	/** clean fn form of an Op */
	public static fn op(Op o){
		return op6BitsToCleanFn(o.op6Bits);
	}
	
	/** dirty fn form of an Op */
	public static fn Op(Op o){
		return op6BitsToDirtyFn(o.op6Bits);
	}
	
	//There is no dirty u or dirty (u u), but anything else has 2 forms: clean and dirty,
	//except FIXME what if theres a clean thing or dirty thing in first param of u other than u vs uu.
	//I dont plan to put anything but u or uu in first param of u, but it will happen in dovetailing
	//and in just randomly calling funcs on eachother, so the cleanness vs dirtyness of it must be defined.
	//Probably this will simply be (isClean x) means is its first param u or is it equal to u.
	
	
	
	//lowercase op name is clean, like wiki. Capital op name is dirty, like Wiki.
	//u is clean.
	//(u u) is clean.
	//(u (u u)) is dirty.
	//(u anythingExceptUAndUU) is dirty.
	
	//FIXME reverse order and swap u with uu, in the following?
	
	
	//Op.deeplazy is not a λ in this prototype cuz this prototype only has halted λs and uses java stack for the nonhalted parts,
	//but you can build something similar to occamsfuncer callquads inside calls of Op.ax
	//(dovetailing may be needed as an abstraction, but compute it procedurally forward efficiently using Evaler/Compiled).
	
	
	//FIXME 2021-2-28 these ops were recently copied from wikibinator105 so are the wrong way to derive those...
	
	//public static final fn wiki      = bootOp(u,	u,	u,	u,	u,	u);
	public static final fn wiki = op(Op.wiki);
	public static final fn Wiki = Op(Op.wiki);
	
	//public static final fn isleaf    = bootOp(u,	u,	u,	u,	u,	uu);
	public static final fn isleaf = op(Op.isleaf);
	public static final fn Isleaf = Op(Op.isleaf);
	public static final fn isnil = isleaf; //FIXME this should be isleaf AND isclean
	public static final fn Isnil = Isleaf;
	
	//public static final fn l         = bootOp(u,	u,	u,	u,	uu,	u);
	public static final fn l = op(Op.getfunc);
	public static final fn L = Op(Op.getfunc);
	
	//public static final fn r         = bootOp(u,	u,	u,	u,	uu,	uu);
	public static final fn r = op(Op.getparam);
	public static final fn R = Op(Op.getparam);
	
	//public static final fn t         = bootOp(u,	u,	u,	uu,	u);
	public static final fn t = op(Op.tru);
	public static final fn T = Op(Op.tru);
	
	//public static final fn f        = bootOp(u,	u,	u,	uu,	uu);
	public static final fn f = op(Op.fal);
	public static final fn F = Op(Op.fal);
	
	//public static final fn curry     = bootOp(u,	u,	uu,	u,	u);
	//public static final fn Curry     = curry.asDirty();
	
	//TODO derive this
	//public static final fn cleancall = bootOp(u,	u,	uu,	u,	uu);
	//even though its dirty, it still converts params to clean and returns a clean
	//public static final fn Cleancall = cleancall.asDirty();
	
	//Op.trecurse
	//public static final fn s         = bootOp(u,	u,	uu,	uu);
	public static final fn s = op(Op.trecurse);
	public static final fn S = Op(Op.trecurse);
	
	//public static final fn pair      = bootOp(u,	uu,	u,	u);
	public static final fn pair = op(Op.pair);
	public static final fn Pair = Op(Op.pair);
	
	//public static final fn typeval   = bootOp(u,	uu,	u,	uu);
	public static final fn typeval = op(Op.typeval);
	public static final fn Typeval = Op(Op.typeval);
	
	//public static final fn ax       = bootOp(u,	uu,	uu);
	
	public static final fn axa = op(Op.axa);
	public static final fn Axa = Op(Op.axa);
			
	public static final fn axb = op(Op.axb);
	public static final fn Axb = Op(Op.axb);
	
	public static final fn growinglist = op(Op.growinglist);
	public static final fn Growinglist = Op(Op.growinglist);
	
	/** u/cleanLeaf/nil is its first param, to start a growinglist so you can reliably know where it starts,
	such as if its first item is also a growinglist, similar to why linkedlists end with nil and <...> lists start with nil.
	[...] and <...> are both made of Op.pair (or Op.Pair if any of their contents are dirty?)
	and differ in which direction the list goes, prefix or suffix.
	{...} is sCurryList. (...) is currylist, just normal callpairs. All that is the default syntax.
	*/
	public static final fn growinglist_u = cp(growinglist,u);
	public static final fn Growinglist_u = cp(Growinglist,u);
	
	
	
	/** identityFunc */
	public static final fn i       = cp(f,u);
	public static final fn I       = cp(F,u);
	
	/** like cleancall except it just has 1 param, the thing to clean,
	which forkEdits param recursively to have u as all first params of u. There are no nonnormed clean forms.
	<br><br>
	FIXME update comments other places which say nonleaf is clean. Instead, leaf is clean, and nonleaf is dirty,
	in th at first param, such as (u u u u u u u) is cleanwiki and (u (u u) u u u u u) is a dirtywiki,
	and of course all forms of the wiki opcode use the same wiki.
	*/
	//public static final fn cleanone = cleancall.p(i);
	//even though its dirty, it still converts param to clean and returns it
	//public static final fn Cleanone = cleanone.asDirty();
	
	/** counterpart of cleanone and Cleanone. Returns a dirty form of its param,
	by forkEditing it recursively for all first params (of leaf) to be a nonleaf,
	and if they are already not a leaf then leaves them as they are else uses (leaf leaf) aka (u u).
	*
	public static final fn Dirtyonepassive = null; //FIXME not null
	
	/** returns the normed dirty form, where all first params (of leaf) are (leaf leaf) aka (u u).
	Similar to Dirtyonepassive except which nonleaf in first param.
	*
	public static final fn Dirtyonenorm = null; //FIXME not null
	*/
	
	public static final fn callParamOnItself = cp(cp(s,i),i);
	public static final fn CallParamOnItself = cp(cp(S,I),I);
	
	/** aka pair.e(s).e(t) but since its in ImportStatic dont want to call e/apply until testcases run
	cuz it might break earlier than that. 
	*/
	public static final fn iota = cp(cp(pair,s),t);
	public static final fn Iota = cp(cp(Pair,S),T);
	
	public static final fn cons = pair;
	public static final fn Cons = Pair;
	
	
	public static final fn tt = cp(t,t);
	public static final fn TT = cp(T,T);
	
	public static final fn tf = cp(t,f);
	public static final fn TF = cp(T,F);
	
	
	/** calls its param on t, such as if its param is a pair of 2 params it gets the first */
	public static final fn car = cp(cp(s,i),tt);
	public static final fn Car = cp(cp(S,I),TT);
	
	/** calls its param on f, such as if its param is a pair of 2 params it gets the second */
	public static final fn cdr = cp(cp(s,i),tf);
	public static final fn Cdr = cp(cp(S,I),TF);
	
	/** get nth param after funcBody param of any of the 16 curry ops, from the datastruct generated by curry op.
	funcBody is param 0.
	*/
	public static fn p(int n){
		return _p(n-6);
	}
	
	/** get nth param after cleanLeaf/dirtyLeaf from the datastruct generated by curry op. */
	public static fn _p(int i){
		throw new RuntimeException("TODO q(int) func is easier to start with cuz it doesnt need to know the number of params already curried.");
	}
	
	private static final fn[] q;
	static{
		q = new fn[23]; //first 5 params choose op. next param is comment. next is funcBody. Then 1..16 params of curry op.
		q[0] = r;
		for(int n=1; n<q.length; n++){
			//progn(l,r) gets all curries except last. r gets last.
			//Curry puts those 2 things in a pair and calls funcBody on it.
			fn[] prognParams = new fn[2+n+1]; //Example: q(4) returns progn(l,r,l,l,l,l,r)
			Arrays.fill(prognParams,l);
			prognParams[1] = prognParams[prognParams.length-1] = r;
			q[n] = progn(prognParams);
		}
	}
	
	/** get nth last param from the datastruct generated by curry op. 0 gets last param. 1 gets second last. and so on. */
	public static fn q(int n){
		return q[n];
	}
	
	
	/** calls q func on that number, get nth last param. q0 is last param. q2 is third last param. */
	public static final fn q22=q(22), q21=q(21), q20=q(20), q19=q(19), q18=q(18),
		q17=q(17), q16=q(16), q15=q(15), q14=q(14), q13=q(13), q12=q(12), q11=q(11), q10=q(10),
		q9=q(9), q8=q(8), q7=q(7), q6=q(6), q5=q(5), q4=q(4), q3=q(3), q2=q(2), q1=q(1), q0=q(0);
	
	private static final fn[] c = new fn[]{
		op(Op.curry1), op(Op.curry2), op(Op.curry3), op(Op.curry4),
		op(Op.curry5), op(Op.curry6), op(Op.curry7), op(Op.curry8),
		op(Op.curry9), op(Op.curry10), op(Op.curry11), op(Op.curry12),
		op(Op.curry13), op(Op.curry14), op(Op.curry15), op(Op.curry16),
	};
	
	/** get 1 of the 16 curry ops, each followed by comment, funcBody, then 1..16 params,
	and on its last param, it calls funcBody on allParamsExceptLast and lastParam.
	*/
	public static final fn c(int params){
		return c[params-1];
		//if(params<1 || 16<params) throw new RuntimeException("params="+params);
	}
	
	/** Called from Op.curry to get funcbody to call on [...linkedlist...] containing that funcbody and its params.
	The last (displayed on left, as its the [] kind of linkedlist, not <> kind) is comment (which is λ by default).
	From PairnodeWithFuncCache QUOTE
	TODO create an Evaler instance that looks for secondLast cache,
	and ImportStatic.secondLast.setCompiled(Evaler),
	and maybe create secondLast func in λ so dont have to cast that, and cuz its a calculation implied by Op.curry,
	BUT also consider that all ops must finish in bigo1 other than if they're implemented in java stack etc
	then they must create a lambda and call it instead of recursing on java stack OTHER THAN
	they are allowed to recurse into Evaler.eval(long,λ,λ) (aka λ.e(long,λ) but anything else has to be bigo1,
	so based on that, λ should NOT have a secondLast func but could still have a secondLastCacheElseNull func
	that doesnt recurse and just returns it if it knows it already.
	UNQUOTE.
	...
	Also do that for Secondlast aka secondlast.dirty() so call setCompiled on that too but
	a different func that does the dirty form of it.
	*
	public static final fn secondlast = null;
	public static final fn Secondlast = null; //FIXME should be secondlast.dirty(), and read comment in that about setCompiled here
	*/
	
	public static fn t(fn x){
		return t.e(x);
		//return cp(t,x);
	}
	public static fn T(fn x){
		return T.e(x);
		//return cp(T,x);
	}
	
	/** TODO also create lazig, which is a λfunc.λparam.λignore.(func param).
	FIXME handle clean vs dirty.
	*/
	public static fn lazy(fn func, fn param){
		return cp(s,t(func),t(param));
	}
	
	public static fn Lazy(fn func, fn param){
		return cp(S,T(func),T(param));
	}
	
	//TODO
	//public static final fn funcThatInfloopsForAllPossibleParams = lazy(callParamOnItself,callParamOnItself);
	//public static final fn FuncThatInfloopsForAllPossibleParams = Lazy(CallParamOnItself,CallParamOnItself);
	
	/** wrap just the bits in cbt, like of byte[] or double[] or String */
	public static fn w(Object wrapMe){
		throw new RuntimeException("TODO");
	}
	
	/** wrap in typeval */
	public static fn ww(Object wrapMe){
		throw new RuntimeException("TODO");
	}
	
	/** FIXME always limit cost by gas, recursively, but todo getting this working first. TODO use $<fn> recursively
	or more efficient recursion on java stack.
	*/
	public static fn truncateToClean_ignoringCost(fn x){
		if(x.isClean()) return x;
		if(x.isLeaf()) return u; //cleanLeaf
		//can use the faster cp(fn,fn) instead of fn.e(fn) cuz know it will halt instantly so returns same thing.
		return cp(truncateToClean_ignoringCost(x.l()),truncateToClean_ignoringCost(x.r()));
	}
	
	/** output of one is input to the next. */
	public static fn progn(fn... sequence){
		if(sequence.length == 0) return i;
		fn x = sequence[sequence.length-1];
		for(int i=sequence.length-2; i>=0; i--){
			x = st(x,sequence[i]);
		}
		return x;
	}

}

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
	public static fn ss(fn... funcs){
		if(funcs.length == 0) throw new RuntimeException("No params");
		fn func = funcs[0];
		for(int i=1; i<funcs.length; i++) func = s(func,funcs[i]);
		return func;
	}
	
	public static fn st(fn... funcs){
		if(funcs.length == 0) throw new RuntimeException("No params");
		fn func = t(funcs[0]);
		for(int i=1; i<funcs.length; i++) func = s(func,funcs[i]);
		return func;
		//return ss(t(metafunc),metaparams);
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
	
	/* clean op with comment of u/cleanLeaf (the default comment) */
	public static fn opu(Op o){
		return cp(op(o),u);
	}
	
	/* dirty Op with comment of u/cleanLeaf (the default comment) */
	public static fn Opu(Op o){
		return cp(Op(o),u);
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
	public static final fn wiki = opu(Op.wiki);
	public static final fn Wiki = Opu(Op.wiki);
	
	//public static final fn isleaf    = bootOp(u,	u,	u,	u,	u,	uu);
	public static final fn isleaf = opu(Op.isleaf);
	public static final fn Isleaf = Opu(Op.isleaf);
	public static final fn isnil = isleaf; //FIXME this should be isleaf AND isclean
	public static final fn Isnil = Isleaf;
	
	//public static final fn l         = bootOp(u,	u,	u,	u,	uu,	u);
	public static final fn l = opu(Op.getfunc);
	public static final fn L = Opu(Op.getfunc);
	
	//public static final fn r         = bootOp(u,	u,	u,	u,	uu,	uu);
	public static final fn r = opu(Op.getparam);
	public static final fn R = Opu(Op.getparam);
	
	//public static final fn t         = bootOp(u,	u,	u,	uu,	u);
	public static final fn t = opu(Op.tru);
	public static final fn T = Opu(Op.tru);
	
	//public static final fn f        = bootOp(u,	u,	u,	uu,	uu);
	public static final fn f = opu(Op.fal);
	public static final fn F = Opu(Op.fal);
	
	//public static final fn curry     = bootOp(u,	u,	uu,	u,	u);
	//public static final fn Curry     = curry.asDirty();
	
	//TODO derive this
	//public static final fn cleancall = bootOp(u,	u,	uu,	u,	uu);
	//even though its dirty, it still converts params to clean and returns a clean
	//public static final fn Cleancall = cleancall.asDirty();
	
	//Op.trecurse
	//public static final fn s         = bootOp(u,	u,	uu,	uu);
	public static final fn s = opu(Op.trecurse);
	public static final fn S = Opu(Op.trecurse);
	
	//public static final fn pair      = bootOp(u,	uu,	u,	u);
	public static final fn pair = opu(Op.pair);
	public static final fn Pair = Opu(Op.pair);
	
	//public static final fn typeval   = bootOp(u,	uu,	u,	uu);
	public static final fn typeval = opu(Op.typeval);
	public static final fn Typeval = Opu(Op.typeval);
	
	//public static final fn ax       = bootOp(u,	uu,	uu);
	
	public static final fn axa = opu(Op.axa);
	public static final fn Axa = Opu(Op.axa);
			
	public static final fn axb = opu(Op.axb);
	public static final fn Axb = Opu(Op.axb);
	
	public static final fn growinglist = opu(Op.growinglist);
	public static final fn Growinglist = Opu(Op.growinglist);
	
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
		//TODO this works, but put a comment in it as a semantic q[0] = r;
		q[0] = r;
		for(int n=1; n<q.length; n++){
			//progn(l,r) gets all curries except last. r gets last.
			//Curry puts those 2 things in a pair and calls funcBody on it.
			
			//q(0) returns r aka progn(r) which gets lastParam from (funcBody [allParamsExceptLast lastParam])
			//q(1) returns progn(l,r,r) which gets secondLastParam from (funcBody [(allParamsExceptLast2 secondLast) lastParam])
			//q(2) returns progn(l,r,l,r) which gets thirdLastParam from (funcBody [(allParamsExceptLast3 thirdLast secondLast) lastParam])
			//q(3) returns progn(l,r,l,l,r)
			//q(4) returns progn(l,r,l,l,l,r)
			//Theres always a l and r of everything, even if its u/cleanLeaf its l is i/identityFunc and its r is u/cleanLeaf,
			//cuz forall halted x: (L x (R x)) equals x, which is https://en.wikipedia.org/wiki/Pattern_calculus
			//L is dirty. l is clean. R is dirty. r is clean. Capital first letter normally means dirty, lowercase clean.
			//If a clean is called on a dirty, the dirty is truncated to clean recursively before the clean sees it as param.
			//If a dirty is called on clean or dirty, it sees it with no changes. Thats why its (L x (R x)) instead of (l x (r x)),
			//cuz L and R are the most general, and (R x) will be truncated to clean if (L x) returns a clean.

			fn[] prognParams = new fn[2+n];
			Arrays.fill(prognParams,l);
			prognParams[1] = prognParams[prognParams.length-1] = r;
			q[n] = progn(prognParams);
		}
		for(int n=0; n<q.length; n++){
			fn comment = uu; //TODO use typeval "text/plain" ("q"+n), after wrapping of strings etc is working
			q[n] = setComment(q[n],uu); //but for now just want these funcs to be different than other uses of l and r
		}
	}
	
	/** comment goes at param 6. appends u/cleanLeaf's if less than 5 params. Else adds/replaces param 6, leaving others as they are. */
	public static fn setComment(fn x, fn newComment){
		while(x.curriesSoFar() < 5) x = x.p(u); //comment goes at param 6
		if(x.curriesSoFar() > 6) return setComment(x.l(),newComment).p(x.r());
		return x.l().p(newComment); //x has 6 params. replace 6th param
	}
	
	public static fn comment(fn x){
		if(x.curriesSoFar() < 6) return u;
		if(x.curriesSoFar() > 6) return comment(x.l());
		return x.r();
	}
	
	/** max param of q(int) */
	public static final int maxQ = q.length-1;
	
	/** get nth last param from the datastruct generated by curry op. 0 gets last param. 1 gets second last. and so on. */
	public static fn q(int n){
		return q[n];
	}
	
	
	/** calls q func on that number, get nth last param. q0 is last param. q2 is third last param. */
	public static final fn q22=q(22), q21=q(21), q20=q(20), q19=q(19), q18=q(18),
		q17=q(17), q16=q(16), q15=q(15), q14=q(14), q13=q(13), q12=q(12), q11=q(11), q10=q(10),
		q9=q(9), q8=q(8), q7=q(7), q6=q(6), q5=q(5), q4=q(4), q3=q(3), q2=q(2), q1=q(1), q0=q(0);
	
	/** a function of 2 params calls itself recursively,
	such as in equals function calling itself twice to navigate a tree.
	TODO create general recur function that keeps going to left child until curriesSoFar==7
	which is just after funcBody is defined, so its the same recur for curry1..curry16.
	*/
	public static final fn funcOf2ParamsCallsItselfRecursively = progn(l,r,l);
	
	private static final fn[] c = new fn[]{
		opu(Op.curry1), opu(Op.curry2), opu(Op.curry3), opu(Op.curry4),
		opu(Op.curry5), opu(Op.curry6), opu(Op.curry7), opu(Op.curry8),
		opu(Op.curry9), opu(Op.curry10), opu(Op.curry11), opu(Op.curry12),
		opu(Op.curry13), opu(Op.curry14), opu(Op.curry15), opu(Op.curry16),
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
		//return t.e(x);
		return cp(t,x); //same as t.e(x) cuz know t takes more params
	}
	public static fn T(fn x){
		return T.e(x);
		//return cp(T,x);
	}
	
	public static fn tt(fn x){
		return cp(t,cp(t,x)); //same as t.e(t.e(x)) cuz know t takes more params (than 1)
	}
	
	public static fn ttt(fn x){
		return cp(t,cp(t,cp(t,x))); //same as t.e(t.e(t.e(x))) cuz know t takes more params (than 1)
	}
	
	public static fn tttt(fn x){
		return cp(t,cp(t,cp(t,cp(t,x)))); //same as t.e(t.e(t.e(t.e(x)))) cuz know t takes more params (than 1)
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
		fn x = sequence[0];
		for(int i=1; i<sequence.length; i++){
			x = st(sequence[i],x);
		}
		return x;
	}
	
	/** TODO rewrite these comments copied from occamsfuncer:
	(ifElse condition ifTrue ifFalse) evals to (ifTrue leaf) if condition==T and evals to (ifFalse leaf) if condition==F, for example.
	Normally used with lazig and S. See Example.equals() andOr ImportStatic.IF(fn,fn,fn) in ocfn2.
	TODO setComment to "ifElse" after create setComment func and use typeval for it being a string or a certain key "//" in comment being a map.
	*/
	public static final fn ifElse =
		c(3).p(st(
			pair,
			q(1), //getIfTrue - second last param
			q(0), //getIfFalse - last param
			q(2), //getCondition - third last param
			t(u)
		));
	
	/** λx.λy.λz.xy which is a kind of LAZyeval of (x y) that IGnores z and just waits on z to trigger eval */
	public static final fn lazig = c(3).e(s(q2,q1));
	
	public static fn iF(fn condition, fn ifTrue, fn ifFalse){
		return st(ifElse, condition, ifTrue, ifFalse);
	}
	
	//TODO are thenConst and thenT the same if its 1 param?
	
	public static fn thenConst(fn constant){
		return cp(lazig,t(constant));
	}
	
	/**TODO when wrapping objects is working...
	
	FIXME This text and code copied from occamsfuncer:
	
	the ST form of then(...). ST just does t(...) to its first param
	and other than that is the same as S(...).
	*
	public static fn thenT(Object... obs){
		return f(lazig,st(obs));
	}*/
	
	public static fn thenT(fn... obs){
		return cp(lazig,st(obs));
	}
	
	/*public static fn then(Object... obs){
		return f(lazig,s(obs));
	}*/
	
	public static fn then(fn... obs){
		return cp(lazig,ss(obs));
	}
	
	/** FIXME this text and code was copied from occamsfuncer, and modifying...
	 
	 
	like linPlusTwoscomplement except takes 1 param and adds 1 without carrying past the highest digit near the end.
	Example: (linPlusOneTwoscomplement (T (F u))) -> (F (T u)) //01 -> 10
	Example: (linPlusOneTwoscomplement (F (T u))) -> (T (T u)) //10 -> 11
	Example: (linPlusOneTwoscomplement (T (T u))) -> (F (F u)) //11 -> 00, wraps
	Example: (linPlusOneTwoscomplement (T (T (T u)))) -> (F (F (F u))) //111 -> 000, wraps
	Example: (linPlusOneTwoscomplement u) -> u //number with no digits wraps to itself
	*
	public static fn linPlusOneTwoscomplement =
		c(1).e(IF(
			st(isleaf,p(15)), //if param is leaf/u aka number with no digits, which normally happens just after the highest digit
			thenConst(u), //then return leaf/u
			then(IF( //else its supposed to be like (T (T (F (F (T u)))))
				st(L,p(15)), //lowest bit in param
				thenT( //lowest bit is T, THEN, Change lowest T to F and carry above that.
					F, //new lowest bit
					S(
						recur2(), //linPlusOneTwoscomplement. lambda(1 is used with recur2 cuz that doesnt include the context param. FIXME rename cuz thats confusing.
						p(14), //Copy p(14) which is a context (like defaultContext)
						ST(R,p(15)) //shift param down 1
					)
				),
				thenT(T,ST(R,p(15))) //lowest bit is F, so ELSE replace first bit with T and keep the rest as it is
				
			)) //lowest bit is F, so ELSE
		));
	
	
	/** FIXME this text and code was copied from occamsfuncer, and modifying...

	private static fn linPlusTwoscomplement;
	/** Only for 2 lin params of equal size, for example as generated from cbt32 to lin form to compute float32 multiply then back to cbt32.
	lin is a number whose digits are T/1 or F/0 similar to a linkedlist (except no pairs) like (T (T (F (T .)))) is 1011.
	Low digits are first cuz they're modified most often, so less objects created.
	For twosComplement here, the 2 params must be the same length (or TODO auto insert F F F... near the end aka the high digits being 0),
	and it does not carry past the top digit. So if they are both 2 32 bit numbers, they will stay 32 bit.
	In that way, does twosComplement plus of whatever size numbers you have, such as 2 int337s.
	<br><br>
	TODO in the faster implementation, create a Compiled.java instance that uses hardware int32 plus and int64 plus etc,
	and use int32 plus and bit shifts etc to derive int32 multiply,
	then create Compiled.java instance for int32 multiply that uses hardware optimization,
	then use ints to derive floats and doubles, then Compiled.java for those using hardware optimization.
	*
	public static fn linPlusTwoscomplement(){
		//If they're not equal size, then its caller's fault.
		if(linPlusTwoscomplement == null){
			fn x = ST(
				recur3(),
				p(13), //copy context
				ST(R,p(14)), //shift first param down 1
				ST(R,p(15)) //shift second param down 1
			);
			linPlusTwoscomplement = lambda(2,IF(
				//if first of 2 params is leaf/u aka number with no digits (other should be same len),
				//which normally happens just after the highest digit.
				ST(A,p(14)),
				thenConst(u), //number with no digits
				then(IF( //else the 2 params should have at least a digit each
					ST(L,p(14)), //low digit of first param
					then(IF( //low digit of first param is T
						ST(L,p(15)), //low digit of second param
						thenT(F,ST(linPlusOneTwoscomplement(),x)), //both low digits are T, so becomes F and carry.
						thenT(T,x) //a F and a T, so low digit becomes T and no carry
					)),
					then(IF( //low digit of first param is F
						ST(L,p(15)), //low digit of second param
						thenT(T,x), //a F and a T, so low digit becomes T and no carry
						thenT(F,x) //2 Fs, so low digit becomes F and no carry
					))
				))
			));
		}
		return linPlusTwoscomplement;
	}
	*/
	
	
	/** https://en.wikipedia.org/wiki/Church_encoding says and = Lp.Lq.pqp * */
	public static final fn and = c(2).p(ss(q1,q0,q1));
	
	/** https://en.wikipedia.org/wiki/Church_encoding says and = Lp.Lq.ppq */
	public static final fn or = c(2).p(ss(q1,q1,q0));
	
	public static final fn not = pair.p(f).p(t);
	
	/** TODO there must be a more efficient form */
	public static final fn xor = c(2).p(st(
		or,
		st(and,q1,st(not,q0)),
		st(and,st(not,q1),q0)
	));
	
	public static final fn xor3 = c(3).p(st( xor, q2, st(xor, q1, q0) ));
	
	public static final fn xor4 = c(4).p(st( xor, st(xor, q3, q2), st(xor, q1, q0) ));
	
	public static final fn nand = c(2).p(st( not, st(and,q1,q2)) );
	
	public static final fn nor = c(2).p(st( not, st(or, q1, q0) ));
	
	/** a universal logic operator on 3 bits to 1 bit that returns T half the time. TODO there must be a more efficient form */
	public static final fn minorityBit = c(3).p(st(
		xor4,
		t(t),
		st(and,q2,q1),
		st(and,q1,q0),
		st(and,q0,q2)
	));
	
	/** compares 2 lambda functions for equality, returning t or f, by their 2-way forest shape.
	
	Something like this, todo get the syntax working...
	{
	,c2
	"todo write a comment here"
	{
		,if
		{,a p0}
		{,a p1}
		{
			,if
			{,a p1}
			,,t
			{
				,and
				{recur {,l p0} {,l p1}}
				{recur {,r p0} {,r p1}}
			}
		}
	}
	*
	public static final fn equals = c(2).p(iF(
		st(isleaf, q0),
		st(isleaf, q1),
		iF(
			st(isleaf,q1),
			tt(t),
			st(
				and,
				st(funcOf2ParamsCallsItselfRecursively, st(l,q0), st(l,q1)),
				st(funcOf2ParamsCallsItselfRecursively, st(r,q0), st(r,q1))
			)
		)
	));
	*/
	public static final fn equals = c(2).p(iF(
		st(isleaf,q1), //if p9 is leaf
		thenT(isleaf,q0), //then return: p10 is leaf?
		then(iF( //else if
			st(isleaf,q1), //if p10 is leaf?
			thenConst(f), //then return f
			thenT( //else return AND of recurse 2 times on the left of both params and right of both params
				and,
				ss(funcOf2ParamsCallsItselfRecursively, st(l,q1), st(l,q0) ),
				ss(funcOf2ParamsCallsItselfRecursively, st(r,q1), st(r,q0) )
			)
		))
	));
	
	
	static{
		for(int n=0; n<q.length; n++) {
			lg("q"+n+" (aka getter of "+n+"th last param given curryDatastruct) = "+q(n));
		}
	}

}

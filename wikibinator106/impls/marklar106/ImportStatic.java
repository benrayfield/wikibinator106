/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;

import wikibinator106.spec.λ;

public class ImportStatic{
	
	public static String toString(Object o){
		return o==null ? "null" : o.toString();
	}
	
	public static void lg(Object o){
		lg(toString(o));
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	/** returns a halted call pair without evaling. If you call this on something that should eval,
	it may break lots of stuff that depends on that evalling as the func_param_return cache
	will answer this afterward, at least until uncached, and in further caches affected by that, and so on.
	This should only be called by VM code,
	and others use λ.e(λ) or λ.e(long,λ) instead which are safe to call in any combos.
	*/
	public static fn cp(fn func, fn param){
		fn ret = AxfprCache.getOrNull(func, param);
		if(ret == null){
			ret = new SimpleFn(func, param);
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
	//There is no dirty u.
	
	public static final fn uu = u.p(u);
	//There is no dirty (u u).
	
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
	
	public static final fn wiki      = bootOp(u,	u,	u,	u,	u,	u);
	public static final fn Wiki      = wiki.asDirty();
	
	public static final fn isleaf    = bootOp(u,	u,	u,	u,	u,	uu);
	public static final fn Isleaf    = isleaf.asDirty();
	
	public static final fn l         = bootOp(u,	u,	u,	u,	uu,	u);
	public static final fn L         = l.asDirty();
	
	public static final fn r         = bootOp(u,	u,	u,	u,	uu,	uu);
	public static final fn R         = r.asDirty();
	
	public static final fn t         = bootOp(u,	u,	u,	uu,	u);
	public static final fn T         = t.asDirty();
	
	public static final fn f        = bootOp(u,	u,	u,	uu,	uu);
	public static final fn F        = f.asDirty();
	
	public static final fn curry     = bootOp(u,	u,	uu,	u,	u);
	public static final fn Curry     = curry.asDirty();
	
	//TODO derive this
	//public static final fn cleancall = bootOp(u,	u,	uu,	u,	uu);
	//even though its dirty, it still converts params to clean and returns a clean
	//public static final fn Cleancall = cleancall.asDirty();
	
	//Op.trecurse
	public static final fn s         = bootOp(u,	u,	uu,	uu);
	public static final fn S         = s.asDirty();
	
	public static final fn pair      = bootOp(u,	uu,	u,	u);
	public static final fn Pair      = pair.asDirty();
	
	public static final fn typeval   = bootOp(u,	uu,	u,	uu);
	public static final fn Typeval   = typeval.asDirty();
	
	public static final fn ax        = bootOp(u,	uu,	uu);
	public static final fn Ax        = ax.asDirty();
	
	
	
	/** identityFunc */
	public static final fn i       = cp(f,u);
	public static final fn I       = i.asDirty();
	
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
	
	public static fn t(fn x){ return cp(t,x); }
	public static fn T(fn x){ return cp(T,x); }
	
	/** TODO also create lazig, which is a λfunc.λparam.λignore.(func param).
	FIXME handle clean vs dirty.
	*/
	public static fn lazy(fn func, fn param){
		return cp(s,t(func),t(param));
	}
	
	public static fn Lazy(fn func, fn param){
		return cp(S,T(func),T(param));
	}
	
	public static final λ funcThatInfloopsForAllPossibleParams = lazy(callParamOnItself,callParamOnItself);
	
	public static final λ FuncThatInfloopsForAllPossibleParams = funcThatInfloopsForAllPossibleParams.asDirty();

}

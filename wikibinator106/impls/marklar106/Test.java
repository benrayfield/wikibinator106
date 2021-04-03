package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;

import wikibinator106.spec.Const;
import wikibinator106.spec.Op;

/** See marklar106TestOutput.txt
FIXME make the tests independent of implementation, other than you cant call java from javascript,
but put it in spec instead of impl, and just call it on impl with param of a DIRTY instance of leaf
since everything can be derived from that including the CLEAN leaf.
*/
public class Test{
	
	public static void main(String... args){
		
		
		//double timeStart = Time.now();
		//try{
			//testBootIsT();
			
			//long nanotimeStart = System.nanoTime(); //TODO utcnano
			
			//testFnDotIdentityfunc();
		
			lg("\nequals = "+equals);
			//equals = (c2 {{,ifElse {,isleaf q1}}#n17 (lazig {,isleaf q0}) (lazig {n17 (lazig ,f) (lazig {,and {funcOf2ParamsCallsItselfRecursively {,l q1} {,l q0}} {funcOf2ParamsCallsItselfRecursively {,r q1} {,r q0}}})})})
			//probably right: equals = (c2 {{,(c3 {,pair {(t r)#n50 {n50 l}#n86}#n84 r {n50 {(t l)#n48 n86}#n14}#n35 ,λ}) {(t (λλ λ λλ λλ λ λ))#n9 n84}}#n2 ((c3 {n35 n84})#n61 {n9 r}) (n61 {n2 (n61 ,f) (n61 {,(c2 {n84 r n84}) {n14 {n48 n84} {n48 r}} {n14 {n50 n84} {n50 r}}})})})
			//probably right: equals = (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})})
			//wrong: equals = (i n14#n14 (n14 n14)#n2 n14 n14#n4 n14 n2 n14#c2 {,(n4 n2 n14 n14#c3 {,pair {,(n2 n14#n20 n2#n12 n14#n23 n2 n14)#n19 {,n19 (n23 n14 n14)#n1}#n15}#n10 n19 {,n19 {,n1 n15}#n13}#n9 ,n14}) {,(n12 n2 n14 n14) n10} (c3 {n9 n10}#n11 {,(n12 n2 n14 n14) n19}) (n11 {,(c3 {,pair n10 n19 n9 ,n14}) {,(n12 n2 n14 n14) n10} (n11 ,f) (n11 {,(c2 {n10 n19 n10}) {n13 {,n1 n10} {,n1 n19}} {n13 {,n19 n10} {,n19 n19}}})})})
			//System.exit(0); //FIXME remove this line
			
			testLeafAndFewOpsInternalStructures_withFewThingsCommentedOutCuzCodeWasFromDiffUniversalFunc_todoAddSimilarTests();
			testTF();
			testPair();
			testIota();
			testLRQuine();
			testIdentityFuncs();
			testSTLR();
			testConsCarCdr();
			testProgn();
			testCurry1ToCurry16();
			testLazig();
			testIfElse();
			testLogic();
			testEquals();
			test_cxLinPlusOnetwoscomplement();
			test_cxLinPlusTwoscomplement();
			testGrowinglist();
			testLinget();
			testLinput();
			testBitstringsSoBigTheyMustUseGrowinglist();
			test_axableTwoLazysEvalToSame();
			
			//thisHelpsInManuallyTestingCacheFuncParamReturnUsingDebugger();
			//testIsHalted();
			/*testLeafAndFewOpsInternalStructures_withFewThingsCommentedOutCuzCodeWasFromDiffUniversalFunc_todoAddSimilarTests();
			testBigCallParams(); //in wikibinator106 this means curry1 .. curry16
			
			//testBigCallRecur1To6();
			//TODO instead of recur1 to recur7, depending on number of params, use opcode (ops 1 - 7) to know exactly where to recur from,
			//which will in other implementations (than this spec) be cached in every node what is its op and number of curries.
			
			test_cxLinPlusOnetwoscomplement;
			*/
			
			//test_linPlusTwoscomplement();
			
			/*TODO testcases where callpairs call step, lazyCall, asCallPair, asCallQuad, stackDown, cacheKey, stack, etc.
			All existing testcases will be the same. The meta ops only add behaviors.
			Its most important to use a FPR <isDeterministic,func,param,return> which is a callquad (see OcfnUtil.cacheVal(fn) the value of fn.cacheKey)
			deterministicly from callpairs, a combo of a few of those.
			*/
			
			/*testIFInBigcall();
			testChurchEncodingOfArithmetic();
			testLinPlusOne();
			lg("Occamsfuncer tests passed.");
			*/
			
			/*long nanotimeEnd = System.nanoTime(); //TODO utcnano
			double duration = (nanotimeEnd-nanotimeStart)*1e-9;
			double stepsPerSec = OcfnUtil.countSteps_todoRemoveThisVar/duration;
			lg("steps="+OcfnUtil.countSteps_todoRemoveThisVar);
			lg("seconds="+duration);
			long objectsInCache = CacheFuncParamReturn.howManyCached();
			lg("stepsPerSec="+stepsPerSec);
			double objectsInCachePerSec = objectsInCache/duration;
			lg("objectsInCachePerSec="+objectsInCachePerSec+" objectsInCache="+objectsInCache);
			lg("In other implementations (not this spec), this will be optimized to make full use of GPU but CPU will often be many times slower than other programs that use CPU cuz of unusual cache needs.");
			*/
			
		/*}finally{
			double duration = Time.now()-timeStart;
			lg("CacheFuncParamReturn.howManyCached="+CacheFuncParamReturn.howManyCached());
			lg("OcfnUtil.test_countCallsOfStep="+OcfnUtil.test_countCallsOfStep);
			lg("duration="+duration);
		}*/
	}
	
	//FIXME this code was copied 2021-2-4 from a fork of occamsfuncer,
	//which is a very different universal func than wikibinator,
	//but it should have similar testcases anyways,
	//just differing in how to derive the basics of lisp and math etc.
		
	
	//TODO simplify this test code, something similar to JUnit,
	//but since its to be available at runtime and I dont want this simple spec to depend on extra libraries,
	//these few funcs might be the simplest way.
	//Unlike most programs, occamsfuncer is stateless except for a few memory and compute statistics etc,
	//so theres no setup and teardown like junit helps with.
	
	public static void test(boolean x){
		if(!x) throw new Error("Test failed");
		lg("### test pass");;
	}
	
	public static void test(String name, boolean x){
		if(!x) throw new Error("Test failed: "+name);
		lg("### test pass: "+name);;
	}
	
	public static void testEqq(String name, Object a, Object b){
		if(a != b){
			throw new Error("Test failed: "+name+" a["+a+"] b["+b+"]");
		}
		lg("### testEqq pass: "+name+", "+a);
	}
	
	public static void testNotEqq(String name, Object a, Object b){
		if(a == b){
			throw new Error("Test failed (cuz they equal but shouldnt): "+name+" a["+a+"] b["+b+"]");
		}
		lg("### testNotEqq pass: "+name);
	}
	
	public static void testEqq(String name, long a, long b){
		if(a != b){
			throw new Error("Test failed: "+name+" a["+a+"] b["+b+"]");
		}
		lg("### testEqq pass: "+name);
	}
	
	public static void testEqq(String name, double a, double b){
		if(a != b){
			throw new Error("Test failed: "+name+" a["+a+"] b["+b+"]");
		}
		lg("### testEqq pass: "+name);
	}
	
	public static void testDotEq(String name, Object a, Object b){
		if(!a.equals(b)){
			throw new Error("Test failed: "+name+" a["+a+"] b["+b+"]");
		}
		lg("### testDotEq pass: "+name);
	}

	
	//FIXME this was copied from ocfn3r, need to modify all code and comments to have 9 params of universalFunc instead of 10
	
	
	/*public static void testBootIsT(){
	//public static void testBootIsTAndIsUnaryBit(){
		lg("Starting testBootIsT");
		test("bootIsT T", bootIsT(T));
		test("!bootIsT F", !bootIsT(F));
		test("!bootIsT I", !bootIsT(I));
		test("!bootIsT u", !bootIsT(u));
		test("!bootIsT uu", !bootIsT(uu));
		test("!bootIsT op0", !bootIsT(op(0)));
		test("!bootIsT op1", !bootIsT(op(1)));
		test("!bootIsT op2", !bootIsT(op(2)));
	}*/
	
	/*public static void testFnDotIdentityfunc(){
		lg("Starting testFnDotIdentityfunc");
		testEqq("i==u.identityFunc()", i, u.identityFunc());
		testEqq("i==t.identityFunc()", i, t.identityFunc());
		testEqq("derive i/identityFunc", u.e(u).e(u.e(u)).e(u).e(u).e(u.e(u)).e(u).e(u).e(u).e(u).e(u).e(u).e(u).e(u).e(u), i);
		testEqq("(i p)==p", i.e(p), p);
	}*/
	
	public static void testLeafAndFewOpsInternalStructures_withFewThingsCommentedOutCuzCodeWasFromDiffUniversalFunc_todoAddSimilarTests(){
		lg("Starting testLeaf");
		testEqq("(l Leaf)", L(u), i);
		testEqq("(r leaf)", R(u), u);
		//testEqq("L(F)==op0", L(F), op0);
		//testEqq("L(L(I))==op0", L(L(I)), op0);
		//testEqq("L(T)==op1", L(T), op1);
		//testEqq("L(L(L))==op2", L(L(L)), op2);
		//testEqq("L(L(R))==op3", L(L(R)), op3);
		testEqq("(l Leaf) 2", e(l,u), i);
		testEqq("(r leaf) 2", e(r,u), u);
	}
	
	public static void testTF(){
		lg("Starting testTF");
		fn t_w = t.e(wiki);
		testEqq("(t wiki).l()->t", t_w.l(), t);
		testEqq("(t wiki).r()->wiki", t_w.r(), wiki);
		testEqq("(t wiki u)->wiki", t_w.e(u), wiki);
		testEqq("(t wiki pair)->wiki", t_w.e(pair), wiki);
		fn f_w = f.e(wiki);
		testEqq("(f wiki).l()->f", f_w.l(), f);
		testEqq("(f wiki).r()->wiki", f_w.r(), wiki);
		testEqq("(f wiki u)->u", f_w.e(u), u);
		testEqq("(f wiki pair)->pair", f_w.e(pair), pair);
	}
	
	public static void testPair(){
		lg("Starting testPair");
		testEqq("(pair wiki) is halted", pair.e(wiki), pair.e(wiki));
		fn pair_wiki_u = pair.e(wiki).e(u);
		testEqq("(pair wiki u) is halted", pair_wiki_u, pair.e(wiki).e(u));
		fn pair_wiki = pair.e(wiki);
		//fn lazy_pair_wiki_u = pair_wiki.f(u); lazy doesnt work that way in this wikibinator106 VM cuz evaling happens on java stack so theres no deeplazy
		//fn pair_wiki_u = lazy_pair_wiki_u.e();
		testEqq("(pair wiki u t)->wiki", pair_wiki_u.e(t), wiki);
		testEqq("(pair wiki u f)->u", pair_wiki_u.e(f), u);
	}
	
	public static void testIota(){
		lg("Starting testIota");
		fn x = iota;
		lg("iota = "+x);
		
		/* From ocfn3r:
		Starting testIota
		iota = ((P S) T)
		start eval: {((P S) T)((P S) T)}
		start step: {((P S) T)((P S) T)}
		After setCacheKey: {((P S) T)((P S) T) c<{((P S) T)((P S) T)}>}
		step returning {{((P S) T) S} T c<{{((P S) T) S} T}>}
		start step: {{((P S) T) S} T c<{{((P S) T) S} T}>}
		step returning {((P S) T) S l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		start step: {((P S) T) S l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		After setCacheKey: {((P S) T) S c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		
		step returning ((S S) T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		start step: ((S S) T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		Write cache (mid), key={((P S) T) S}
		Write cache (mid), val=((S S) T)
		step returning {((S S) T) T c<{{((P S) T) S} T}>}
		start step: {((S S) T) T c<{{((P S) T) S} T}>}
		step returning ((S T)(T T) c<((S T)(T T))>)
		end eval: ((S T)(T T)) --- is eval of: {((P S) T)((P S) T)}
		iotaIota = ((S T)(T T))
		*
		
		/*
		After setCacheKey: {((P S) T) S c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		{
			((P S) T)
			S
			c<{((P S) T) S}>
			l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>
		}
		
		CORRECT:
		step returning ((S S) T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		(
			(S S)
			T
			c<{((P S) T) S}>
			l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>
		)
		
		OBSERVED 2020-8-27-915a after changed var "n" to "evalingState" cuz there were multiple n vars:
		step returning {{S S} T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		{
			{S S}
			T
			c<{((P S) T) S}>
			l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>
		}
		It differs only by {S S} meaning !isHaltedAbove vs (S S) meaning isHaltedAbove
		
		OBSERVED 2020-8-23-10a:
		step returning {{S S} T c<{{S S} T}>}
		{
			{S S}
			T
			c<{{S S} T}>
		}
		*
		fn iotaIota = x.e(x);
		lg("(iota iota) -> "+iotaIota);
		
		
		/** ocfn3r:
		Starting testIota
		iota = ((P S) T)
		start eval: {((P S) T)((P S) T)}
		start step: {((P S) T)((P S) T)}
		After setCacheKey: {((P S) T)((P S) T) c<{((P S) T)((P S) T)}>}
		step returning {{((P S) T) S} T c<{{((P S) T) S} T}>}
		start step: {{((P S) T) S} T c<{{((P S) T) S} T}>}
		step returning {((P S) T) S l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		start step: {((P S) T) S l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		After setCacheKey: {((P S) T) S c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		
		step returning ((S S) T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		start step: ((S S) T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		Write cache (mid), key={((P S) T) S}
		Write cache (mid), val=((S S) T)
		step returning {((S S) T) T c<{{((P S) T) S} T}>}
		start step: {((S S) T) T c<{{((P S) T) S} T}>}
		step returning ((S T)(T T) c<((S T)(T T))>)
		end eval: ((S T)(T T)) --- is eval of: {((P S) T)((P S) T)}
		iotaIota = ((S T)(T T))
		
		After setCacheKey: {((P S) T) S c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		
		CORRECT:
		step returning ((S S) T c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		
		OBSERVED:
		step returning {{S S} T c<{{S S} T}>}
		
		----
		After those changes, the problem is seen in...
		
		start step: {(S T) {T T} c<{{S T}{T T}}>}
		step returning {T T r<{(S T) {T T} c<{{S T}{T T}}>}>}
		start step: {T T r<{(S T) {T T} c<{{S T}{T T}}>}>}
		After setCacheKey: {T T c<{T T}> r<{(S T) {T T} c<{{S T}{T T}}>}>}
		Returning from cache. Before restack: (T T)
		Returning from cache: (T T r<{T T}>) //this line is wrong cuz the r stack was bigger, and maybe it shouldnt have an r stack at all... code appears to call truncateToStackBottom which shouldnt have cacheKey or stack
		start step: (T T r<{T T}>)
		step returning {T (T T)}
		start step: {T (T T)}
		After setCacheKey: {T (T T) c<{T (T T)}>}
		step returning (T (T T) c<(T (T T))>)
		end eval: (T (T T)) --- is eval of: {((P S) T)((P S) T)}
		
		T
		*
		testEqq("(iota iota)->(S T (T T))", iotaIota, bootF(S,T,bootF(T,T)));
		
		testEqq("(iota iota N) cuz iota iota is an identityFunc", iotaIota.e(N), N);
		
		/** from ocfn3r (works) 2020-8-29-1220p:
		end eval: N --- is eval of: {((S T)(T T)) N}
		### testEqq pass: (iota iota N) cuz iota iota is an identityFunc
		start eval: {((P S) T)((P S) T)}
		start step: {((P S) T)((P S) T)}
		After setCacheKey: {((P S) T)((P S) T) c<{((P S) T)((P S) T)}>}
		step returning {{((P S) T) S} T c<{{((P S) T) S} T}>}
		start step: {{((P S) T) S} T c<{{((P S) T) S} T}>}
		step returning {((P S) T) S l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		start step: {((P S) T) S l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		After setCacheKey: {((P S) T) S c<{((P S) T) S}> l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>}
		Returning from cache. Before restack: ((S S) T)
		Returning from cache: ((S S) T l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		start step: ((S S) T l<{{((P S) T) S} T c<{{((P S) T) S} T}>}>)
		step returning {((S S) T) T c<{{((P S) T) S} T}>}
		start step: {((S S) T) T c<{{((P S) T) S} T}>}
		step returning ((S T)(T T) c<((S T)(T T))>)
		end eval: ((S T)(T T)) --- is eval of: {((P S) T)((P S) T)}
		start eval: {((P S) T)((S T)(T T))}
		start step: {((P S) T)((S T)(T T))}
		After setCacheKey: {((P S) T)((S T)(T T)) c<{((P S) T)((S T)(T T))}>}
		step returning {{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}
		start step: {{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}
		step returning {((S T)(T T)) S l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		start step: {((S T)(T T)) S l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		After setCacheKey: {((S T)(T T)) S c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		step returning {(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		start step: {(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		step returning {(T T) S r<{(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}>}
		start step: {(T T) S r<{(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}>}
		After setCacheKey: {(T T) S c<{(T T) S}> r<{(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}>}
		step returning (((opPrefix .)(..)). c<{(T T) S}> r<{(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}>)
		start step: (((opPrefix .)(..)). c<{(T T) S}> r<{(T S) {(T T) S} c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}>)
		Write cache (mid), key={(T T) S}
		Write cache (mid), val=T
		step returning {(T S) T c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		start step: {(T S) T c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>}
		step returning ((((((..).).)(..))(..)). c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>)
		start step: ((((((..).).)(..))(..)). c<{((S T)(T T)) S}> l<{{((S T)(T T)) S} T c<{{((S T)(T T)) S} T}>}>)
		Write cache (mid), key={((S T)(T T)) S}
		Write cache (mid), val=S
		step returning {S T c<{{((S T)(T T)) S} T}>}
		start step: {S T c<{{((S T)(T T)) S} T}>}
		step returning (S T c<(S T)>)
		end eval: (S T) --- is eval of: {((P S) T)((S T)(T T))}
		start eval: {((P S) T)(S T)}
		start step: {((P S) T)(S T)}
		After setCacheKey: {((P S) T)(S T) c<{((P S) T)(S T)}>}
		step returning {((S T) S) T c<{((S T) S) T}>}
		start step: {((S T) S) T c<{((S T) S) T}>}
		step returning {(T T)(S T) c<{(T T)(S T)}>}
		start step: {(T T)(S T) c<{(T T)(S T)}>}
		step returning (((opPrefix .)(..)). c<T>)
		end eval: T --- is eval of: {((P S) T)(S T)}
		### testEqq pass: get T from iota
		
		
		from this occamsfuncer (broken) 2020-8-29-1220p:
		end eval: N --- is eval of: {((S T)(T T)) N}
		### testEqq pass: (iota iota N) cuz iota iota is an identityFunc
		start eval: {((P S) T)((P S) T)}
		start step: {((P S) T)((P S) T)}
		After setCacheKey: {((P S) T)((P S) T) c<{((P S) T)((P S) T)}>}
		step returning {{((P S) T) S} T}
		start step: {{((P S) T) S} T}
		step returning {((P S) T) S l<{{((P S) T) S} T}>}
		start step: {((P S) T) S l<{{((P S) T) S} T}>}
		Returning from cache. Before restack: ((S S) T)
		Returning from cache: ((S S) T)
		end eval: ((S S) T) --- is eval of: {((P S) T)((P S) T)}
		start eval: {((P S) T)((S S) T)}
		start step: {((P S) T)((S S) T)}
		After setCacheKey: {((P S) T)((S S) T) c<{((P S) T)((S S) T)}>}
		step returning {{((S S) T) S} T}
		start step: {{((S S) T) S} T}
		step returning {((S S) T) S l<{{((S S) T) S} T}>}
		start step: {((S S) T) S l<{{((S S) T) S} T}>}
		After setCacheKey: {((S S) T) S c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		step returning {{S S}{T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		start step: {{S S}{T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		step returning {S S l<{{S S}{T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>}
		start step: {S S l<{{S S}{T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>}
		step returning (S S l<{{S S}{T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>)
		start step: (S S l<{{S S}{T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>)
		step returning {(S S) {T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		start step: {(S S) {T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		step returning {T S r<{(S S) {T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>}
		start step: {T S r<{(S S) {T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>}
		step returning (T S r<{(S S) {T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>)
		start step: (T S r<{(S S) {T S} c<{((S S) T) S}> l<{{((S S) T) S} T}>}>)
		step returning {(S S)(T S) c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		start step: {(S S)(T S) c<{((S S) T) S}> l<{{((S S) T) S} T}>}
		step returning ((S S)(T S) c<{((S S) T) S}> l<{{((S S) T) S} T}>)
		start step: ((S S)(T S) c<{((S S) T) S}> l<{{((S S) T) S} T}>)
		Write cache (mid), key={((S S) T) S}
		Write cache (mid), val=((S S)(T S))
		step returning {((S S)(T S)) T}
		start step: {((S S)(T S)) T}
		After setCacheKey: {((S S)(T S)) T c<{((S S)(T S)) T}>}
		step returning {{S T}{(T S) T}}
		start step: {{S T}{(T S) T}}
		step returning {S T l<{{S T}{(T S) T}}>}
		start step: {S T l<{{S T}{(T S) T}}>}
		step returning (S T l<{{S T}{(T S) T}}>)
		start step: (S T l<{{S T}{(T S) T}}>)
		step returning {(S T) {(T S) T}}
		start step: {(S T) {(T S) T}}
		step returning {(T S) T r<{(S T) {(T S) T}}>}
		start step: {(T S) T r<{(S T) {(T S) T}}>}
		After setCacheKey: {(T S) T c<{(T S) T}> r<{(S T) {(T S) T}}>}
		step returning ((((((((((((..)(..))(..)).)(..)).).).).).).). c<{(T S) T}> r<{(S T) {(T S) T}}>)
		start step: ((((((((((((..)(..))(..)).)(..)).).).).).).). c<{(T S) T}> r<{(S T) {(T S) T}}>)
		Write cache (mid), key={(T S) T}
		Write cache (mid), val=S
		step returning {(S T) S}
		start step: {(S T) S}
		step returning ((S T) S)
		end eval: ((S T) S) --- is eval of: {((P S) T)((S S) T)}
		start eval: {((P S) T)((S T) S)}
		start step: {((P S) T)((S T) S)}
		After setCacheKey: {((P S) T)((S T) S) c<{((P S) T)((S T) S)}>}
		step returning {{((S T) S) S} T}
		start step: {{((S T) S) S} T}
		step returning {((S T) S) S l<{{((S T) S) S} T}>}
		start step: {((S T) S) S l<{{((S T) S) S} T}>}
		After setCacheKey: {((S T) S) S c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		step returning {{T S}{S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		start step: {{T S}{S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		step returning {T S l<{{T S}{S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>}
		start step: {T S l<{{T S}{S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>}
		step returning (T S l<{{T S}{S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>)
		start step: (T S l<{{T S}{S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>)
		step returning {(T S) {S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		start step: {(T S) {S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		step returning {S S r<{(T S) {S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>}
		start step: {S S r<{(T S) {S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>}
		step returning (S S r<{(T S) {S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>)
		start step: (S S r<{(T S) {S S} c<{((S T) S) S}> l<{{((S T) S) S} T}>}>)
		step returning {(T S)(S S) c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		start step: {(T S)(S S) c<{((S T) S) S}> l<{{((S T) S) S} T}>}
		step returning ((((((((((((..)(..))(..)).)(..)).).).).).).). c<{((S T) S) S}> l<{{((S T) S) S} T}>)
		start step: ((((((((((((..)(..))(..)).)(..)).).).).).).). c<{((S T) S) S}> l<{{((S T) S) S} T}>)
		Write cache (mid), key={((S T) S) S}
		Write cache (mid), val=S
		step returning {S T}
		start step: {S T}
		step returning (S T)
		end eval: (S T) --- is eval of: {((P S) T)((S T) S)}
		Exception in thread "main" java.lang.Error: Test failed: get T from iota a[(S T)] b[T]
			at occamsfuncer.spec.TestSpec.testEqq(TestSpec.java:31)
			at occamsfuncer.spec.TestSpec.testIota(TestSpec.java:282)
			at occamsfuncer.spec.TestSpec.main(TestSpec.java:566)
		*/
		
		testEqq("get t from iota", x.e(x.e(x.e(x))), t);
		testEqq("get s from iota", x.e(x.e(x.e(x.e(x)))), s);
		lg("Tests pass: testIota");
	}
	
	public static void testSTLR(){
		lg("Starting testSTLR");
		fn st = cp(s,t);
		test("st.L()==s", L(st)==s);
		test("st.R()==t", R(st)==t);
		test("st.L()==s 2", e(l,st)==s);
		test("st.R()==t 2", e(r,st)==t);
	}
	
	/*public static void thisHelpsInManuallyTestingCacheFuncParamReturnUsingDebugger(){
		lg("Starting thisHelpsInManuallyTestingCacheFuncParamReturnUsingDebugger");
		Node x = f(P,f(I,N),f(I,N));
		OcfnUtil.forABreakpoint = true;
		Node pnn = eval(x); //returns (P N N)
		OcfnUtil.forABreakpoint = false;
		testEqq("pnn", pnn, bootF(P,N,N));
	}*/
	
	/*public static void testIsHalted(){
		lg("Starting testIsHalted");
		for(int params=0; params<15; params++){
			Node[] p = new Node[params+1];
			Arrays.fill(p, u);
			test("isHalted . "+params+"params", f(p).isHaltedAbove());
		}
		test("isHalted (..(..))", f(u,u,f(u,u)).isHaltedAbove());
		test("isHalted should be false . 15params", !f(u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u).isHaltedAbove());
		test("isHalted should be false (remember, universalFunc always curries 15 params, so any more are lazy after that 15 returns which is also lazy) . 11params", !f(u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u).isHaltedAbove());
	}*/
	
	public static void testLRQuine(fn x){
		testEqq("testLRQuine_"+x, e(l.e(x),r.e(x)), x);
	}
	
	public static void testLRQuine(){
		lg("Starting testLRQuine");
		//fn uuNonhalted = f(u,u);
		//testEqq("step (..) becomes halted on (..)", setCacheKey(step(uuNonhalted),null), bootF(u,u));
		testLRQuine(u);
		testLRQuine(e(u,u));
		testLRQuine(l);
		testLRQuine(r);
		testLRQuine(wiki);
		testLRQuine(pair.e(wiki).e(pair.e(pair).e(pair)));
		//todo text growinglist later... testLRQuine(growinglist.e(wiki).e(t).e(f).e(s));
		//todo test Dirty L later testLRQuine(L);
		//todo test Dirty R later testLRQuine(R);
		//todo derive equals and Equals: testLRQuine(Equals());
		//testLRQuine(P.e(Equals()).e(L));
		//testLRQuine(P.e(L).e(Equals()));
		//testLRQuine(P.e(Equals().e(Equals())));
		//TODO more calls of testLRQuine
	}
	
	public static void testIdentityFuncs(){
		lg("Starting testIdentityFuncs");
		fn stt = e(s,t,t);
		test("leaf.l()==i", e(l,u)==i);
		test("leaf.r()==leaf", e(r,u)==u);
		test("stt.f(i)==i", e(stt,i)==i);
		test("stt.f(t)==t", e(stt,t)==t);
		test("stt.f(f)==t", e(stt,f)==f);
		test("i.f(stt)==stt", e(i,stt)==stt);
		test("i.f(t)==t", e(i,t)==t);
	}
	
	public static void testConsCarCdr(){
		lg("Starting testConsCarCdr. Nil is leaf/theUniversalFunction. isNil is the isLeaf op.");
		fn list_wiki_u_l = cons.e(wiki).e(cons.e(nil).e(cons.e(l).e(nil)));
		testEqq("testConsCarCdr_1", car.e(list_wiki_u_l), wiki);
		testEqq("testConsCarCdr_2", car.e(cdr.e(list_wiki_u_l)), nil);
		testEqq("testConsCarCdr_3", car.e(cdr.e(cdr.e(list_wiki_u_l))), l);
		testEqq("testConsCarCdr_4", cdr.e(cdr.e(cdr.e(list_wiki_u_l))), nil);
		testEqq("isNil_nil", isnil.e(nil), t);
		testEqq("isNil_[list_N_A_L]", isnil.e(list_wiki_u_l), f);
	}
	
	public static void testProgn(){
		lg("Starting testProgn (which is about ImportStatic.progn, a java function, not a lambda form of the same thing (TODO), but it generates lambdas.");
		testEqq("progn(l)", progn(l), l);
		testEqq("progn(l,r).e(pair.e(t).e(f))", progn(l,r).e(pair.e(t).e(f)), t);
		testEqq("progn(r,l).e(pair.e(t).e(pair.e(wiki).e(typeval)))", progn(r,l).e(pair.e(t).e(pair.e(wiki).e(typeval))), pair.e(wiki));
		testEqq("progn(r,l,l).e(pair.e(t).e(pair.e(wiki).e(typeval)))", progn(r,l,l).e(pair.e(t).e(pair.e(wiki).e(typeval))), pair);
		testEqq("progn(r,l,l).e(pair.e(t).e(pair.e(wiki).e(typeval)))", progn(r,l,r).e(pair.e(t).e(pair.e(wiki).e(typeval))), wiki);
		lg("progn(r,l,l) = "+progn(r,l,l));
		testEqq("progn(l,r).e(pair.e(t).e(pair.e(wiki).e(typeval)))", progn(l,r).e(pair.e(t).e(pair.e(wiki).e(typeval))), t);
	}
	
	public static void testCurry1ToCurry16(){
		lg("Starting testCurry1ToCurry16");
		for(int params=1; params<16; params++){
			testCurryN(params);
		}
	}
	
	public static void testCurryN(int n){
		lg("Starting testCurry"+n);
		fn curryN = c(n);
		lg("curry"+n+" = "+curryN);
		lg("curry"+n+".curriesMore = "+curryN.curriesMore());
		lg("curry"+n+".curriesAll = "+curryN.curriesAll());
		lg("curry"+n+".curriesSoFar = "+curryN.curriesSoFar());
		fn findTheCleanleaf = curryN;
		String findTheCleanleaf_testName = "curry"+n;
		for(int i=0; i<curryN.curriesSoFar(); i++){
			findTheCleanleaf = findTheCleanleaf.l();
			findTheCleanleaf_testName += ".l";
		}
		findTheCleanleaf_testName += " == u/cleanLeaf";
		testEqq(findTheCleanleaf_testName, findTheCleanleaf, u);
		//curriesMore test is first cuz thats the one stored in header64 of marklar106b ids.
		testEqq("test curry"+n+".curriesMore is 1+numParams=="+(1+n)+" cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.",
			curryN.curriesMore(), 1+n);
		testEqq("test curry"+n+".curriesAll is 6+numParams=="+(7+n)+" cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those "+n+" param(s).",
			curryN.curriesAll(), 7+n);
		testEqq("test curry"+n+".curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.",
			curryN.curriesSoFar(), 6);
		fn comment = u;
		for(int getMthLastParam=0; getMthLastParam<n; getMthLastParam++){
			fn funcBody = q(getMthLastParam);
			lg("funcBody = "+funcBody);
			fn x = curryN.e(funcBody);
			int mForward = n-1-getMthLastParam; //get mForward'th param, where 0 is funcBody
			for(int i=0; i<n; i++){ //call it with n params that are each u. The mth last param is wiki.
				lg("x="+x);
				fn param = i==mForward ? uu : u;
				lg("getMthLastParam="+getMthLastParam+" mForward="+mForward+" param="+param+" i="+i+" n="+n);
				if(i==n-1){
					lg("Before last call: "+x+", about to call that on "+param);
					testEqq("testCurry"+n+"_get"+getMthLastParam+"thLastParam_verifyCurriesmoreIs1", x.curriesMore(), 1);
				}
				x = x.e(param);
			}
			testEqq("testCurry"+n+"_get"+getMthLastParam+"thLastParam_returned", x, uu);
		}
	}
	
	public static void testIfElse(){
		lg("Starting testIfElse");
		testEqq("e(ifElse,t,i,i)", e(ifElse,t,i,i), u);
		testEqq("e(ifElse,t,t(wiki),t(pair))", e(ifElse,t,t(wiki),t(pair)), wiki);
		testEqq("e(ifElse,F,t(N),t(P))", e(ifElse,F,t(wiki),t(pair)), pair);
		testEqq("e(ifElse,T,I,.) -> (I .) -> .", e(ifElse,t,i,u), u);
		testEqq("e(ifElse,F,I,.) -> (. .)", e(ifElse,f,i,u), e(u,u));
		//testEqq("ifElse thenConst 1", IF(tt(T),thenT(P),thenT(N)), P);
		//testEqq("ifElse thenConst 2", IF(tt(F),thenT(P),thenT(N)), N);
		
		testEqq("(,,,,u wiki) -> ,,,u", e(tttt(u),wiki), ttt(u));
		testEqq("ifElse T thenConst L", e(iF(t(t),thenConst(l),thenConst(r)),u), l);
		testEqq("ifElse F thenConst R", e(iF(t(f),thenConst(l),thenConst(r)),u), r);
		testEqq("ifElse I thenConst L cuz param of the IF is T so I gets T", e(iF(i,thenConst(l),thenConst(r)),t), l);
		testEqq("ifElse I thenConst R cuz param of the IF is F so I gets F", e(iF(i,thenConst(l),thenConst(r)),f), r);
		testEqq("ifElse car thenConst L cuz param of the IF is (P T F) so car gets T",
			e(iF(car,thenConst(l),thenConst(r)),e(pair,t,f)), l);
		testEqq("ifElse car then I, car gets T which chooses then(I), and the I called on (P T F) returns (P T F)",
			e(iF(car,then(i),thenConst(r)),e(pair,t,F)), e(pair,t,f));
		testEqq("ifElse cdr then I, cdr gets F which chooses thenT(P,I,I), and the thenT(P,I,I) called on e(P,T,F) returns (P (P T F) (P T F))",
			e(iF(cdr,then(i),thenT(pair,i,i)),e(pair,t,f)), e(pair,e(pair,t,f),e(pair,t,f)));
	}
	
	/*public static void testBigCallParams(){
		lg("Starting testBigCallParams");
		//The universalFunc always curries 15 params (more if lazy, but uses 15 at a time recursively).
		//7 of its 16 opcodes are a lambda call on 1-7 params. The last 1-7 of those (p(9) to p(15)) are those params.
		//Just before those are: comment funcBody context <...the 1-7 params...>.
		testEqq("lambda7.p15", e(lambda(7,p(15)),L,R,P,A,N,T,S), S);
		testEqq("lambda7.p14", e(lambda(7,p(14)),L,R,P,A,N,T,S), T);
		testEqq("lambda7.p13", e(lambda(7,p(13)),L,R,P,A,N,T,S), N);
		testEqq("lambda7.p12", e(lambda(7,p(12)),L,R,P,A,N,T,S), A);
		testEqq("lambda7.p11", e(lambda(7,p(11)),L,R,P,A,N,T,S), P);
		testEqq("lambda7.p10", e(lambda(7,p(10)),L,R,P,A,N,T,S), R);
		testEqq("lambda7.p9",  e(lambda(7, p(9)),L,R,P,A,N,T,S), L);
		testEqq("lambda7.p8",  e(lambda(7, p(8)),L,R,P,A,N,T,S), defaultContext);
		testEqq("lambda7.p7",  e(lambda(7, p(7)),L,R,P,A,N,T,S), p(7)); //p(7) is funcBody in this call
		testEqq("lambda7.p6",  e(lambda(7, p(6)),L,R,P,A,N,T,S), defaultComment);
		testEqq("lambda7.p5",  e(lambda(7, p(5)),L,R,P,A,N,T,S), R(op(7)));
		testEqq("lambda7.p4",  e(lambda(7, p(4)),L,R,P,A,N,T,S), R(L(op(7))));
		testEqq("lambda7.p3",  e(lambda(7, p(3)),L,R,P,A,N,T,S), R(L(L(op(7)))));
		testEqq("lambda7.p2",  e(lambda(7, p(2)),L,R,P,A,N,T,S), R(L(L(L(op(7))))));
		testEqq("lambda7.p1",  e(lambda(7, p(1)),L,R,P,A,N,T,S), u); //cuz first of 15 params is leaf for the universalFunc and nonleaf for future expansion
		
		//see OcfnUtil.equals for an example of tree recursion using this way of getting its own funcBody etc.
		testEqq("lambda5.p9",  e(lambda(5,p(9)),     P,A,N,T,S), p(9)); //p(9) is funcBody in this call
		testEqq("lambda6.p8",  e(lambda(6,p(8)),   R,P,A,N,T,S), p(8)); //p(8) is funcBody in this call
		
		//testEqq("lambda7.p12", e(lambda(7,p(12)),L,R,P,A,N,T,S), A);
		//testEqq("lambda7.p14", e(lambda(7,p(14)),L,R,P,A,N,T,S), T);
		testEqq("lambda7 create pair from 2 params",   e(lambda(7,ST(P,p(12),p(14))),L,R,P,A,N,T,S), P.e(A).e(T));

	}
	
	/*public static void testBigCallRecur1To6(){
		lg("Starting testBigCallRecur1To6");
		testEqq("recur6 without enough params", e(Big,recur6,L,R,P,A,N), e(Big,recur6,L,R,P,A,N));
		testEqq("recur6", e(Big,recur6,L,R,P,A,N,T), e(Big,recur6));
		testEqq("recur5 without enough params", e(Big,recur5,L,R,P,A), e(Big,recur5,L,R,P,A));
		testEqq("recur5", e(Big,recur5,L,R,P,A,N,T), e(Big,recur5,L));
		testEqq("recur4", e(Big,recur4,L,R,P,A,N,T), e(Big,recur4,L,R));
		testEqq("recur3", e(Big,recur3,L,R,P,A,N,T), e(Big,recur3,L,R,P));
		testEqq("recur2", e(Big,recur2,L,R,P,A,N,T), e(Big,recur2,L,R,P,A));
		testEqq("recur1", e(Big,recur1,L,R,P,A,N,T), e(Big,recur1,L,R,P,A,N));
	}*
	
	/*
	public static void testIFInBigcall(){
		lg("Starting testIFInBigcall");
		Node x = e(
			Big,
			IF(p7, then(p8), then(p9)),
			F, F, T //ignore first 3 of 6 params
		);
		testEqq("testIFInBigcall 1", e(x,T,A,N), A);
		testEqq("testIFInBigcall 2", e(x,F,A,N), N);
	}*/
	
	public static void testLogic(){
		lg("Starting testLogic");
		testEqq("and f f", e(and,f,f), f);
		testEqq("and f t", e(and,f,t), f);
		testEqq("and t f", e(and,t,f), f);
		testEqq("and t t", e(and,t,t), t);
		testEqq("or f f", e(or,f,f), f);
		testEqq("or f t", e(or,f,t), t);
		testEqq("or t f", e(or,t,f), t);
		testEqq("or t t", e(or,t,t), t);
		testEqq("xor f f", e(xor,f,f), f);
		testEqq("xor f t", e(xor,f,t), t);
		testEqq("xor t f", e(xor,t,f), t);
		testEqq("xor t t", e(xor,t,t), f);
		//minorityBit, in this very unoptimized math model, is probably being exponentially slow cuz I implemented it using 4 xors and 3 ands
		//and multiple Big calls. Its time to get CachefuncParamReturn working.
		//if(1<2) throw new Error("too slow past here. must get CachefuncParamReturn working first");
		testEqq("minorityBit f f f", e(minorityBit,f,f,f), t);
		testEqq("minorityBit f f t", e(minorityBit,f,f,t), t);
		testEqq("minorityBit f t f", e(minorityBit,f,t,f), t);
		testEqq("minorityBit f t t", e(minorityBit,f,t,t), f);
		testEqq("minorityBit t f f", e(minorityBit,t,f,f), t);
		testEqq("minorityBit t f t", e(minorityBit,t,f,t), f);
		testEqq("minorityBit t t f", e(minorityBit,t,t,f), f);
		testEqq("minorityBit t t t", e(minorityBit,t,t,t), f);
		//verify this fails: testEqq("minorityBit t t t", e(minorityBit,t,t,t), t);
	}
	
	/** lin aka linkedList number, is a simpler kind of linkedlist that doesnt use pair,
	like (T (T (F (T nilAkaLeaf)))) is the number 0x1101 aka baseTen11, and nilAkaLeaf is 0.
	Its a much slower form than the cbt/completeBinaryTree of bits which will
	be hardware optimized to use strictfp float double long int math, in other implementations.
	This implementation is just the math spec for solving disagreements in the possible behaviors of faster implementations.
	*
	public static void testLinPlusOne(){
		lg("Starting testLinArithmetic");
		Node lin0 = nil;
		Node lin1 = T.e(nil);
		Node lin2 = F.e(T.e(nil));
		Node lin3 = T.e(T.e(nil));
		Node lin4 = F.e(F.e(T.e(nil)));
		Node lin5 = T.e(F.e(T.e(nil)));
		testEqq("(linPlusOne lin0)->lin1", e(linPlusOne,lin0), lin1);
		testEqq("(linPlusOne lin1)->lin2", e(linPlusOne,lin1), lin2);
		testEqq("(linPlusOne lin2)->lin3", e(linPlusOne,lin2), lin3);
		testEqq("(linPlusOne lin3)->lin4", e(linPlusOne,lin3), lin4);
		testEqq("(linPlusOne lin4)->lin5", e(linPlusOne,lin4), lin5);
	}
	
	/*public static void testLinPlus(){
		lg("Starting testLinPlus");
		testEqq("lin11", lin(11), e(T,e(T,e(F,e(T,nil)))));
		testEqq("linPlus 0 0", e(linPlus,lin(0),lin(0)), lin(0));
		testEqq("linPlus 0 1", e(linPlus,lin(0),lin(1)), lin(1));
		testEqq("linPlus 1 0", e(linPlus,lin(1),lin(0)), lin(1));
		testEqq("linPlus 2 3", e(linPlus,lin(2),lin(3)), lin(5));
		testEqq("linPlus 3 2", e(linPlus,lin(3),lin(2)), lin(5));
		testEqq("linPlus 11 17", e(linPlus,lin(11),lin(17)), lin(28));
		testEqq("linPlus 20 17", e(linPlus,lin(20),lin(17)), lin(37));
		testEqq("linPlus 16 16", e(linPlus,lin(16),lin(16)), lin(32));
		testEqq("linPlus 16 1", e(linPlus,lin(16),lin(1)), lin(17));
	}*/
	
	/* fibonacci, to verify cache <func,param,return> is working (which 2020-6-23-10a its not,
	and the system is designed to work anyways just exponentially slower, wherever it lacks perfect dedup,
	so if you dedup everything except for n parts, it will be at worst constant^n times slower,
	and often much faster than that).
	*
	public static void testLinFibonacciSoDeepThatIfCacheFuncParamReturnIsntWorkingThenItWillTakeTrillionsOfYearsElseNearInstant(){
		lg("Starting testLinFibonacciSoDeepThatIfCacheFuncParamReturnIsntWorkingThenItWillTakeTrillionsOfYearsElseNearInstant");
		throw new Error("TODO");
	}*/
	
	public static void testEquals(){
		lg("Starting testEquals - The universalfunc being a patternCalculusfunc allows it to do this which lambdafuncs cant cuz its a subset of possible lambdafuncs thats a universal subset but also a subset that allows it to know patternCalculus things that it couldnt know outside that subset cuz it wouldnt know which are in or not in the subset, except that in this system its always in that subset. Its important to understand that the equals func is implemented as a pure sparse turing machine and does not use any implementing system's == or .equals operators etc except other implementations can do that as an optimization as long as it always gets the exact same result as the sparse turing machine.");
		lg("equals = "+equals);
		lg("equals (displayed as 2-way forest) = "+Lang.toStringCallpairsOnly(equals));
		testEqq("(equals . .)", e(equals,u,u), t);
		testEqq("(equals . (..))", e(equals,u,e(u,u)), f);
		testEqq("(equals (..) .)", e(equals,e(u,u),u), f);
		testEqq("(equals (..) (..))", e(equals,e(u,u),e(u,u)), t);
		testEqq("(equals ((..).) ((..).))", e( equals, e(e(u,u),u), e(e(u,u),u) ), t);
		testEqq("(equals (.(..)) (.(..)))", e( equals, e(u,e(u,u)), e(u,e(u,u)) ), t);
		testEqq("(equals ((..)(..)) ((..)(..)))", e( equals, e(e(u,u),e(u,u)), e(e(u,u),e(u,u)) ), t);
		testEqq("(equals (..) ((..).))", e( equals, e(u,u), e(e(u,u),u) ), f);
		testEqq("(equals ((..).) (..))", e( equals, e(e(u,u),u), e(u,u) ), f);
		testEqq("(equals car car)", e(equals,car,car), t);
		testEqq("(equals car cdr)", e(equals,car,cdr), f);
		testEqq("(equals equals equals)", e(equals,equals,equals), t);
		testEqq("(equals car equals)", e(equals,car,equals), f);
		testEqq("(equals (equals equals) equals)", e(equals,e(equals,equals),equals), f);
		testEqq("(equals (equals equals) (equals equals))", e(equals,e(equals,equals),e(equals,equals)), t);
	}
	
	/** (lazig x y z) returns (x y), a kind of lazyeval of (x y) that ignores z */
	public static void testLazig(){
		lg("Starting testLazig");
		//fn cbtAN = e(lazig(), isleaf, wiki, pair);
		testEqq("lazigIsleaf", e(lazig,isleaf), e(lazig,isleaf));
		testEqq("lazigIsleafWiki", e(lazig,isleaf,wiki), e(lazig,isleaf,wiki));
		testEqq("lazigIsleafWikiPair", e(lazig,isleaf,wiki,pair), e(isleaf,wiki));
		testEqq("lazigPairIsleafWiki", e(lazig,pair,isleaf,wiki), e(pair,isleaf));
	}
	
	public static void test_cxLinPlusOnetwoscomplement(){
		lg("Starting test_cxLinPlusOnetwoscomplement");
		//context/cx is normally some kind of treemap (TODO) which goes in first param of many funcs,
		//but since cxLinPlusOnetwoscomplement doesnt use it (its just there for consistency with other funcs), it can be anything.
		fn cx = u;
		testEqq("(f u)==i. Remember that when reading the following tests.", e(f,u), i);
		testEqq("(cxLinPlusOnetwoscomplement u) -> u, aka number with no digits", e(cxLinPlusOnetwoscomplement,cx,u), u);
		testEqq("(cxLinPlusOnetwoscomplement (f u)) -> (t u), aka 0 -> 1", e(cxLinPlusOnetwoscomplement,cx,e(f,u)), e(t,u));
		testEqq("(cxLinPlusOnetwoscomplement (t u)) -> (f u), aka 1 -> 0 losing high digit to twosComplement", e(cxLinPlusOnetwoscomplement,cx,e(f,u)), e(t,u));
		testEqq("(cxLinPlusOnetwoscomplement (t (f u))) -> (f (t u)) //01 -> 10", e(cxLinPlusOnetwoscomplement,cx, e(t,e(f,u))), e(f,e(t,u)));
		testEqq("(cxLinPlusOnetwoscomplement (f (t u))) -> (t (t u)) //10 -> 11", e(cxLinPlusOnetwoscomplement,cx, e(f,e(t,u))), e(t,e(t,u)));
		testEqq("(cxLinPlusOnetwoscomplement (t (t u))) -> (f (f u)) //11 -> 00, wraps", e(cxLinPlusOnetwoscomplement,cx, e(t,e(t,u))), e(f,e(f,u)));
		testEqq("(cxLinPlusOnetwoscomplement (t (t (t u)))) -> (f (f (f u))) //111 -> 000, wraps", e(cxLinPlusOnetwoscomplement,cx, e(t,e(t,e(t,u)))), e(f,e(f,e(f,u))));
		testEqq("(cxLinPlusOnetwoscomplement (t (t (f (t u))))) -> (f (f (t (t u)))) //1011 -> 1100", e(cxLinPlusOnetwoscomplement,cx, e(t,e(t,e(f,e(t,u))))), e(f,e(f,e(t,e(t,u)))));
		testEqq("(cxLinPlusOnetwoscomplement (f (f (f (f u))))) -> (t (f (f (f u)))) //0000 -> 0001", e(cxLinPlusOnetwoscomplement,cx, e(f,e(f,e(f,e(f,u))))), e(t,e(f,e(f,e(f,u)))));
		testEqq("(cxLinPlusOnetwoscomplement (f (f (f (t u))))) -> (f (t (f (f u)))) //1000 -> 0100", e(cxLinPlusOnetwoscomplement,cx, e(t,e(f,e(f,e(f,u))))), e(f,e(t,e(f,e(f,u)))));
	}
	
	public static void test_cxLinPlusTwoscomplement(){
		lg("Starting test_linPlusTwoscomplement");
		fn cx = u;
		testEqq("(f u)==i again. Remember that when reading the following tests.", e(f,u), i);
		fn plus = cxLinPlusTwoscomplement;
		fn plusCx = plus.e(cx);
		testEqq("(linPlusTwoscomplement u u) -> u, 2 numbers with no digits -> no digits", e(plusCx,u,u), u);
		testEqq("(linPlusTwoscomplement (F u) (F u)) -> (F u)", e(plusCx,e(f,u),e(f,u)), e(f,u));
		testEqq("(linPlusTwoscomplement (T u) (F u)) -> (T u)", e(plusCx,e(t,u),e(f,u)), e(t,u));
		testEqq("(linPlusTwoscomplement (F u) (T u)) -> (T u)", e(plusCx,e(f,u),e(t,u)), e(t,u));
		testEqq("(linPlusTwoscomplement (T u) (T u)) -> (F u), carry loses digit", e(plusCx,e(t,u),e(t,u)), e(f,u));
		testEqq("(linPlusTwoscomplement (T (F u)) (T (F u))) -> (F (T u)), 01+01->10", e(plusCx,e(t,e(f,u)),e(t,e(f,u))), e(f,e(t,u)));
		testEqq("(linPlusTwoscomplement (t (f u)) (f (t u))) -> (t (t u)), 01+10->11", e(plusCx,e(t,e(f,u)),e(f,e(t,u))), e(t,e(t,u)));
		
		fn twoZeros = e(f,e(f,u));
		fn threeZeros = e(f,twoZeros);
		fn fourZeros = e(f,threeZeros);
		
		fn twoOnes = e(t,e(t,u));
		fn threeOnes = e(t,twoOnes);
		fn fourOnes = e(t,threeOnes);
		fn fiveOnes = e(t,fourOnes);
		
		testEqq("(linPlusTwoscomplement (f (f u)) (f (f u))) -> (f (f u)), 00+00->00",
			e(plusCx, twoZeros, twoZeros), twoZeros);
		
		testEqq("(linPlusTwoscomplement (f (f u)) (t (t u))) -> (t (t u)), 00+11->11",
				e(plusCx, twoZeros, twoOnes), twoOnes);
		
		testEqq("(linPlusTwoscomplement (t (t u)) (f (f u))) -> (t (t u)), 11+00->11",
			e(plusCx, twoOnes, twoZeros), twoOnes);
		
		
		testEqq("(linPlusTwoscomplement (f (f (f (f u)))) (f (f (f (f u))))) -> (f (f (f (f u)))), 0000+0000->0000",
			e(plusCx, fourZeros, fourZeros), fourZeros);
		
		testEqq("(linPlusTwoscomplement (t (t (t (t u)))) (f (f (f (f u))))) -> (t (t (t (t u)))), 1111+0000->1111",
			e(plusCx, fourOnes, fourZeros), fourOnes);
		
		testEqq("(linPlusTwoscomplement (t (t (t (t u)))) (f (f (f (f u))))) -> (t (t (t (t u)))), 1111+0000->1111",
			e(plusCx, fourOnes, fourZeros), fourOnes);
		
		testEqq("(linPlusTwoscomplement (T (T (F (T u))))) (T (F (F (F u))))) -> (F (F (T (T u)))), 1011+0001->1100",
			e(plusCx,e(t,e(t,e(f,e(t,u)))),e(t,e(f,e(f,e(f,u))))), e(f,e(f,e(t,e(t,u)))) );
		
		testEqq("(linPlusTwoscomplement (t (f (t (t (f u))))) (f (t (f (f (t u)))))) -> (t (t (t (t (t u))))), 01101+10010->11111 (no carries)",
			e(plusCx, e(t,e(f,e(t,e(t,e(f,u))))), e(f,e(t,e(f,e(f,e(t,u)))))), fiveOnes);
	}
	
	public static void testGrowinglist(){
		lg("Starting testGrowinglist");
		fn g = growinglist;
		//prev list is nil/u. Next 64 params are list contents.
		//65th param causes another list with this list as its prev, and so on.
		//For example, a list with 3 pointers to growinglist holds 129-192 things.
		//A growinglist of 192 things requires storinbg 64+1+64+1+64 callpairs,
		//not including (growinglist u) which is shared between most or all growinglists.
		//Technically the first of its 65 params doesnt have to be a prev list, but its normally used that way.
		fn list = u;
		int offset = 0;
		for(int block=0; block<3; block++){
			{
				fn nextList = growinglist.e(list);
				
				//testEqq("testGrowinglist_block"+block+"_l", nextList.l(), g);
				test("testGrowinglist_block"+block+"_l", nextList.l()==g); //FIXME test passed but display of cbt needs this optimization: "Exception in thread "main" java.lang.RuntimeException: TODO lazyeval header to avoid looking for highest 1 bit at some recursions into l or r"
				
				//testEqq("testGrowinglist_block"+block+"_r_isPrevList", nextList.r(), list);
				test("testGrowinglist_block"+block+"_r_isPrevList", nextList.r()==list); //FIXME test passed but display of cbt needs this optimization: "Exception in thread "main" java.lang.RuntimeException: TODO lazyeval header to avoid looking for highest 1 bit at some recursions into l or r"
				
				list = nextList;
			}
			for(int i=0; i<64; i++){
				fn item = cbt((byte)offset);
				fn nextList = list.e(item);
				
				//testEqq("testGrowinglist_"+offset+"_l", nextList.l(), list);
				test("testGrowinglist_"+offset+"_l", nextList.l()==list); //FIXME test passed but display of cbt needs this optimization: "Exception in thread "main" java.lang.RuntimeException: TODO lazyeval header to avoid looking for highest 1 bit at some recursions into l or r"
				
				//testEqq("testGrowinglist_"+offset+"_r", nextList.r(), item);
				test("testGrowinglist_"+offset+"_r", nextList.r()==item); //FIXME test passed but display of cbt needs this optimization: "Exception in thread "main" java.lang.RuntimeException: TODO lazyeval header to avoid looking for highest 1 bit at some recursions into l or r"
				
				list = nextList;
				offset++;
			}
		}
		fn findAListItem = list;
		for(int i=0; i<64; i++){
			findAListItem = findAListItem.l();
		}
		findAListItem = findAListItem.r();
		for(int i=0; i<5; i++){
			findAListItem = findAListItem.l();
		}
		findAListItem = findAListItem.r();
		//testEqq("testGrowinglist_findAListItem", findAListItem, cbt((byte)(offset-1-64-5)));
		test("testGrowinglist_findAListItem", findAListItem==cbt((byte)(offset-1-64-5)));  //FIXME test passed but display of cbt needs this optimization: "Exception in thread "main" java.lang.RuntimeException: TODO lazyeval header to avoid looking for highest 1 bit at some recursions into l or r"
		lg("testGrowinglist tests pass");
	}
	
	public static void testLinget(){
		testEqq("(linget (pair zero one) u) -> (pair zero one)", e(linget,e(pair,zero,one),u), e(pair,zero,one));
		testEqq("(linget (pair zero one) (t u)) -> (pair zero)", e(linget,e(pair,zero,one),t(u)), e(pair,zero));
		testEqq("(linget (pair zero one) (t (t u))) -> pair", e(linget,e(pair,zero,one),tt(u)), pair);
		testEqq("(linget (pair zero one) (t (f u))) -> zero", e(linget,e(pair,zero,one),t(f(u))), zero);
		testEqq("(linget (pair zero one) (f u)) -> one", e(linget,e(pair,zero,one),f(u)), one);
		testEqq("(linget (pair (one zero) one) (t (f (t u)))) -> one", e(linget,e(pair,e(one,zero),one),t(f(t(u)))), one);
		testEqq("(linget (pair (one zero) one) (t (f (f u)))) -> zero", e(linget,e(pair,e(one,zero),one),t(f(f(u)))), zero);
		
		testEqq("(linget (pair s wiki) u) -> (pair s wiki)", e(linget,e(pair,s,wiki),u), e(pair,s,wiki));
		testEqq("(linget (pair s wiki) (t u)) -> (pair s)", e(linget,e(pair,s,wiki),t(u)), e(pair,s));
		testEqq("(linget (pair s wiki) (t (t u))) -> pair", e(linget,e(pair,s,wiki),tt(u)), pair);
		testEqq("(linget (pair s wiki) (t (f u))) -> s", e(linget,e(pair,s,wiki),t(f(u))), s);
		testEqq("(linget (pair s wiki) (f u)) -> wiki", e(linget,e(pair,s,wiki),e(f,u)), wiki);
		testEqq("(linget (pair (one zero) wiki) (t (f (t u)))) -> one", e(linget,e(pair,e(one,zero),wiki),t(f(t(u)))), one);
		testEqq("(linget (pair (one zero) wiki) (t (f (f u)))) -> zero", e(linget,e(pair,e(one,zero),wiki),t(f(f(u)))), zero);
	}
	
	public static void testLinput(){
		testEqq("(linput ((00)(00)) (t (t u)) 1)->((10)(00))",
			e(linput,e(zero,zero,e(zero,zero)),t(t(u)),one), e(one,zero,e(zero,zero)));
		testEqq("(linput ((00)(00)) (t (f u)) 1)->((01)(00))",
			e(linput,e(zero,zero,e(zero,zero)),t(f(u)),one), e(zero,one,e(zero,zero)));
		testEqq("(linput ((00)(00)) (f (t u)) 1)->((00)(10))",
			e(linput,e(zero,zero,e(zero,zero)),f(t(u)),one), e(zero,zero,e(one,zero)));
		testEqq("(linput ((00)(00)) (f (f u)) 1)->((00)(01))",
			e(linput,e(zero,zero,e(zero,zero)),f(f(u)),one), e(zero,zero,e(zero,one)));
	}
	
	public static void testBitstringsSoBigTheyMustUseGrowinglist(){
		lg("Starting testBitstringsSoBigTheyMustUseGrowinglist");
		fn cbt = zero; //cbt size pow(2,0)==1
		testEqq("log2OfMaxCbtSize", Const.log2OfMaxCbtSize, 120);
		for(int i=0; i<=Const.log2OfMaxCbtSize; i++){
			cbt = cbt.e(cbt);
			lg("i="+i+" cbt.curriesSoFar=="+cbt.curriesSoFar());
		}
		testEqq("cbt at max height, check curriesSoFar", cbt.curriesSoFar(), 126);
		testEqq("cbt at max height, check op6Bits", cbt.op6Bits(), Op.zero.op6Bits);
		fn aGrowinglist = cbt.e(cbt);
		testEqq("cbt became growinglist by passing max height", aGrowinglist.op6Bits(), Op.growinglist.op6Bits);
		testEqq("get cbt left from aGrowinglist", aGrowinglist.l().r(), cbt);
		testEqq("get cbt right from aGrowinglist", aGrowinglist.r(), cbt); //both are all 0s
		//aGrowinglist = aGrowinglist.e(aGrowinglist);
		lg("testBitstringsSoBigTheyMustUseGrowinglist tests pass");
	}
	
	/** this is a basic test of what axcompiler106 is a research path toward,
	that it will (TODO) transform lazy to lazy that evals to same thing
	and generate (axa (axableTwoLazysEvalToSame laxyX lazyY)) to prove it. 
	*/
	public static void test_axableTwoLazysEvalToSame(){
		lg("Starting test_axableTwoLazysEvalToSame");
		fn lazyReturnIota_byLazig = lazig.e(iota.l()).e(iota.r());
		fn lazyReturnIota_byT = t(iota);
		testEqq("(lazyReturnIota_byLazig u)->iota", lazyReturnIota_byLazig.e(u), iota);
		testEqq("(lazyReturnIota_byT u)->iota", lazyReturnIota_byT.e(u), iota);
		testEqq("twoLazysEvalToSame.e(lazyReturnIota_byLazig).e(lazyReturnIota_byT)",
			twoLazysEvalToSame.e(lazyReturnIota_byLazig).e(lazyReturnIota_byT), t);
		fn axableDoThe2LazysOfIotaBothReturnSame = axableTwoLazysEvalToSame.e(lazyReturnIota_byLazig).e(lazyReturnIota_byT);
		testEqq("(axableDoThe2LazysOfIotaBothReturnSame u)->u", axableDoThe2LazysOfIotaBothReturnSame.e(u), u);
		testEqq("axb becomes axa (or could be reversed, which would be an error), on axableDoThe2LazysOfIotaBothReturnSame",
			axb.e(axableDoThe2LazysOfIotaBothReturnSame), axa.e(axableDoThe2LazysOfIotaBothReturnSame));
		testEqq("axb becomes axa (verify its axa), on axableDoThe2LazysOfIotaBothReturnSame",
			axb.e(axableDoThe2LazysOfIotaBothReturnSame).l(), axa);
		fn lazyReturnsPair = t(pair);
		fn axableTheseLazysShouldntReturnSameButDoThey = axableTwoLazysEvalToSame.e(lazyReturnIota_byLazig).e(lazyReturnsPair);
		testEqq("(axableTheseLazysShouldntReturnSameButDoThey u)->(u u)", axableTheseLazysShouldntReturnSameButDoThey.e(u), uu);
		testEqq("axa becomes axb (or could be reversed, which would be an error), on axableTheseLazysShouldntReturnSameButDoThey",
			axa.e(axableTheseLazysShouldntReturnSameButDoThey), axb.e(axableTheseLazysShouldntReturnSameButDoThey));
		testEqq("axa becomes axb (verify its axb), on axableTheseLazysShouldntReturnSameButDoThey",
			axa.e(axableTheseLazysShouldntReturnSameButDoThey).l(), axb);
		testEqq("(axa i) is halted", axa.e(i), axa.p(i));
		testEqq("(axb pair) is halted", axb.e(pair), axb.p(pair));
		testEqq("(axa i 1)->(t 1)", axa.e(i).e(one), t(one)); //(axa x y)->(x (t y)), if (x u)->u.
		testEqq("(axb pair 1)->(pair (f 1))", axb.e(pair).e(one), pair.e(f(one))); //(axb x y)->(x (f y)), if (x u)->anything_except_u.
		lg("test_axableTwoLazysEvalToSame tests pass");
	}
	
	/*
	public static void testChurchEncodingOfArithmetic(){
		lg("Starting testChurchEncodingOfArithmetic aka https://en.wikipedia.org/wiki/Church_encoding");
		fn ch1 = e(chSucc,chZero);
		fn ch2 = e(chSucc,ch1);
		fn ch3 = e(chSucc,ch2);
		fn ch4 = e(chSucc,ch3);
		testEqq("ch3 .", e(ch3,t,nil), e(t,e(t,e(t,nil))));
		fn ch5a = e(chPlus,ch2,ch3);
		fn ch5b = e(chPlus,ch3,ch2);
		testEqq("ch5 a and b", e(ch5a,t,nil), e(ch5b,t,nil));
		testEqq("chMult vs chPlus", e(e(chMult,ch3,ch4),t,nil), e(e(chPlus,ch4,e(chPlus,ch4,ch4)),t,nil));
		testEqq("chExponent vs chMult", e(e(chExponent,ch4,ch3),t,nil), e(e(chMult,ch4,e(chMult,ch4,ch4)),t,nil));
		testEqq(
			"chExponent vs chMult, with plus",
			e( e(chPlus,ch2,e(chExponent,ch4,ch3)) ,t,nil),
			e( e(chPlus,e(chMult,ch4,e(chMult,ch4,ch4)),ch2) ,t,nil)
		);
		lg("testChurchEncodingOfArithmetic tests pass."
			+" The church encoding of arithmetic is a nonnormalized form cuz theres more than 1 form of each integer."
			+" Lin numbers, such as (T (T (T nil))) is 3, are a normalized form and are exponentially more efficient"
			+" as they store binary digits instead of unary. Cbt (complete binary tree of pairs of T and F)"
			+" bitstrings will be even more efficient than that as they"
			+" can be memory mapped between lambdas and large arrays such as for GPU optimizations or realtime transforms"
			+" between speakers and microphones processessing each of 44100 per second wave amplitudes individually or voxel graphics.");
	}*/

}
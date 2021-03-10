package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;

/** FIXME make the tests independent of implementation, other than you cant call java from javascript,
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
			
			//thisHelpsInManuallyTestingCacheFuncParamReturnUsingDebugger();
			//testIsHalted();
			/*testLeafAndFewOpsInternalStructures_withFewThingsCommentedOutCuzCodeWasFromDiffUniversalFunc_todoAddSimilarTests();
			testBigCallParams(); //in wikibinator106 this means curry1 .. curry16
			
			//testBigCallRecur1To6();
			//TODO instead of recur1 to recur7, depending on number of params, use opcode (ops 1 - 7) to know exactly where to recur from,
			//which will in other implementations (than this spec) be cached in every node what is its op and number of curries.
			
			testLazig();
			testIfElse();
			testLogic();
			testEquals();
			
			test_linPlusOneTwoscomplement();
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
		testEqq("ifElse T thenConst L", e(IF(t(t),thenConst(l),thenConst(r)),u), l);
		testEqq("ifElse F thenConst R", e(IF(t(f),thenConst(l),thenConst(r)),u), r);
		testEqq("ifElse I thenConst L cuz param of the IF is T so I gets T", e(IF(i,thenConst(l),thenConst(r)),t), l);
		testEqq("ifElse I thenConst R cuz param of the IF is F so I gets F", e(IF(i,thenConst(l),thenConst(r)),f), r);
		testEqq("ifElse car thenConst L cuz param of the IF is (P T F) so car gets T",
			e(IF(car,thenConst(l),thenConst(r)),e(pair,t,f)), l);
		testEqq("ifElse car then I, car gets T which chooses then(I), and the I called on (P T F) returns (P T F)",
			e(IF(car,then(i),thenConst(r)),e(pair,t,F)), e(pair,t,f));
		testEqq("ifElse cdr then I, cdr gets F which chooses thenT(P,I,I), and the thenT(P,I,I) called on e(P,T,F) returns (P (P T F) (P T F))",
			e(IF(cdr,then(i),thenT(pair,i,i)),e(pair,t,f)), e(pair,e(pair,t,f),e(pair,t,f)));
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
	}*
	
	public static void testLogic(){
		lg("Starting testLogic");
		testEqq("and F F", e(and(),F,F), F);
		testEqq("and F T", e(and(),F,T), F);
		testEqq("and T F", e(and(),T,F), F);
		testEqq("and T T", e(and(),T,T), T);
		testEqq("or F F", e(or(),F,F), F);
		testEqq("or F T", e(or(),F,T), T);
		testEqq("or T F", e(or(),T,F), T);
		testEqq("or T T", e(or(),T,T), T);
		testEqq("xor F F", e(xor(),F,F), F);
		testEqq("xor F T", e(xor(),F,T), T);
		testEqq("xor T F", e(xor(),T,F), T);
		testEqq("xor T T", e(xor(),T,T), F);
		//minorityBit, in this very unoptimized math model, is probably being exponentially slow cuz I implemented it using 4 xors and 3 ands
		//and multiple Big calls. Its time to get CacheFuncParamReturn working.
		//if(1<2) throw new Error("too slow past here. must get CacheFuncParamReturn working first");
		testEqq("minorityBit F F F", e(minorityBit(),F,F,F), T);
		testEqq("minorityBit F F T", e(minorityBit(),F,F,T), T);
		testEqq("minorityBit F T F", e(minorityBit(),F,T,F), T);
		testEqq("minorityBit F T T", e(minorityBit(),F,T,T), F);
		testEqq("minorityBit T F F", e(minorityBit(),T,F,F), T);
		testEqq("minorityBit T F T", e(minorityBit(),T,F,T), F);
		testEqq("minorityBit T T F", e(minorityBit(),T,T,F), F);
		testEqq("minorityBit T T T", e(minorityBit(),T,T,T), F);
		//verify this fails: testEqq("minorityBit T T T", e(minorityBit,T,T,T), T);
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
	}*
	
	public static void testEquals(){
		lg("Starting testEquals - The universalFunc being a patternCalculusFunc allows it to do this which lambdaFuncs cant cuz its a subset of possible lambdaFuncs thats a universal subset but also a subset that allows it to know patternCalculus things that it couldnt know outside that subset cuz it wouldnt know which are in or not in the subset, except that in this system its always in that subset. Its important to understand that the equals func is implemented as a pure sparse turing machine and does not use any implementing system's == or .equals operators etc except other implementations can do that as an optimization as long as it always gets the exact same result as the sparse turing machine.");
		testEqq("(equals . .)", e(Equals(),u,u), T);
		testEqq("(equals . (..))", e(Equals(),u,e(u,u)), F);
		testEqq("(equals (..) .)", e(Equals(),e(u,u),u), F);
		testEqq("(equals (..) (..))", e(Equals(),e(u,u),e(u,u)), T);
		testEqq("(equals ((..).) ((..).))", e( Equals(), e(e(u,u),u), e(e(u,u),u) ), T);
		testEqq("(equals (.(..)) (.(..)))", e( Equals(), e(u,e(u,u)), e(u,e(u,u)) ), T);
		testEqq("(equals ((..)(..)) ((..)(..)))", e( Equals(), e(e(u,u),e(u,u)), e(e(u,u),e(u,u)) ), T);
		testEqq("(equals (..) ((..).))", e( Equals(), e(u,u), e(e(u,u),u) ), F);
		testEqq("(equals ((..).) (..))", e( Equals(), e(e(u,u),u), e(u,u) ), F);
		testEqq("(equals car car)", e(Equals(),car,car), T);
		testEqq("(equals car cdr)", e(Equals(),car,cdr), F);
		testEqq("(equals equals equals)", e(Equals(),Equals(),Equals()), T);
		testEqq("(equals car equals)", e(Equals(),car,Equals()), F);
		testEqq("(equals (equals equals) equals)", e(Equals(),e(Equals(),Equals()),Equals()), F);
		testEqq("(equals (equals equals) (equals equals))", e(Equals(),e(Equals(),Equals()),e(Equals(),Equals())), T);
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
	
	/*public static void test_linPlusOneTwoscomplement(){
		lg("Starting test_linPlusOneTwoscomplement");
		testEqq("(linPlusOneTwoscomplement u) -> u, aka number with no digits", e(linPlusOneTwoscomplement(),u), u);
		testEqq("(F u)==I. Remember that when reading the following tests.", e(F,u), I);
		testEqq("(linPlusOneTwoscomplement (F u)) -> (T u), aka 0 -> 1", e(linPlusOneTwoscomplement(),e(F,u)), e(T,u));
		testEqq("(linPlusOneTwoscomplement (T u)) -> (F u), aka 1 -> 0 losing high digit to twosComplement", e(linPlusOneTwoscomplement(),e(F,u)), e(T,u));
		testEqq("(linPlusOneTwoscomplement (T (F u))) -> (F (T u)) //01 -> 10", e(linPlusOneTwoscomplement(), e(T,e(F,u))), e(F,e(T,u)));
		testEqq("(linPlusOneTwoscomplement (F (T u))) -> (T (T u)) //10 -> 11", e(linPlusOneTwoscomplement(), e(F,e(T,u))), e(T,e(T,u)));
		testEqq("(linPlusOneTwoscomplement (T (T u))) -> (F (F u)) //11 -> 00, wraps", e(linPlusOneTwoscomplement(), e(T,e(T,u))), e(F,e(F,u)));
		testEqq("(linPlusOneTwoscomplement (T (T (T u)))) -> (F (F (F u))) //111 -> 000, wraps", e(linPlusOneTwoscomplement(), e(T,e(T,e(T,u)))), e(F,e(F,e(F,u))));
		testEqq("(linPlusOneTwoscomplement (T (T (F (T u))))) -> (F (F (T (T u)))) //1011 -> 1100", e(linPlusOneTwoscomplement(), e(T,e(T,e(F,e(T,u))))), e(F,e(F,e(T,e(T,u)))));
	}
	
	public static void test_linPlusTwoscomplement(){
		lg("Starting test_linPlusTwoscomplement");
		testEqq("(F u)==I again. Remember that when reading the following tests.", e(F,u), I);
		fn plus = linPlusTwoscomplement();
		testEqq("(linPlusTwoscomplement u u) -> u, 2 numbers with no digits -> no digits", e(plus,u,u), u);
		testEqq("(linPlusTwoscomplement (F u) (F u)) -> (F u)", e(plus,e(F,u),e(F,u)), e(F,u));
		testEqq("(linPlusTwoscomplement (T u) (F u)) -> (T u)", e(plus,e(T,u),e(F,u)), e(T,u));
		testEqq("(linPlusTwoscomplement (F u) (T u)) -> (T u)", e(plus,e(F,u),e(T,u)), e(T,u));
		testEqq("(linPlusTwoscomplement (T u) (T u)) -> (F u), carry loses digit", e(plus,e(T,u),e(T,u)), e(F,u));
		testEqq("(linPlusTwoscomplement (T (F u)) (T (F u))) -> (F (T u)), 01+01->10", e(plus,e(T,e(F,u)),e(T,e(F,u))), e(F,e(T,u)));
		testEqq("(linPlusTwoscomplement (T (T (F (T u))))) (T (F (F (F u))))) -> (F (F (T (T u)))), 1011+0001->1100",
			e(plus,e(T,e(T,e(F,e(T,u)))),e(T,e(F,e(F,e(F,u))))), e(F,e(F,e(T,e(T,u)))) );
	}*/
	
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


/** 2021-3-10

assertionsAreOn=true
q0 (aka getter of 0th last param given curryDatastruct) = r
q1 (aka getter of 1th last param given curryDatastruct) = {,r {,r l}}
q2 (aka getter of 2th last param given curryDatastruct) = {,r {,l {,r l}}}
q3 (aka getter of 3th last param given curryDatastruct) = {,r {,l {,l {,r l}}}}
q4 (aka getter of 4th last param given curryDatastruct) = {,r {,l {,l {,l {,r l}}}}}
q5 (aka getter of 5th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,r l}}}}}}
q6 (aka getter of 6th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
q7 (aka getter of 7th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
q8 (aka getter of 8th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
q9 (aka getter of 9th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
q10 (aka getter of 10th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
q11 (aka getter of 11th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
q12 (aka getter of 12th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
q13 (aka getter of 13th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}
q14 (aka getter of 14th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}
q15 (aka getter of 15th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}
q16 (aka getter of 16th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}
q17 (aka getter of 17th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}}
q18 (aka getter of 18th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}}}
q19 (aka getter of 19th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}}}}
q20 (aka getter of 20th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}}}}}
q21 (aka getter of 21th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}}}}}}
q22 (aka getter of 22th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}}}}}}}}}
Starting testLeaf
### testEqq pass: (l Leaf), i
### testEqq pass: (r leaf), λ
### testEqq pass: (l Leaf) 2, i
### testEqq pass: (r leaf) 2, λ
Starting testTF
### testEqq pass: (t wiki).l()->t, t
### testEqq pass: (t wiki).r()->wiki, w
### testEqq pass: (t wiki u)->wiki, w
### testEqq pass: (t wiki pair)->wiki, w
### testEqq pass: (f wiki).l()->f, f
### testEqq pass: (f wiki).r()->wiki, w
### testEqq pass: (f wiki u)->u, λ
### testEqq pass: (f wiki pair)->pair, pair
Starting testPair
### testEqq pass: (pair wiki) is halted, (pair w)
### testEqq pass: (pair wiki u) is halted, (pair w λ)
### testEqq pass: (pair wiki u t)->wiki, w
### testEqq pass: (pair wiki u f)->u, λ
Starting testIota
iota = (pair s t)
### testEqq pass: get t from iota, t
### testEqq pass: get s from iota, s
Tests pass: testIota
Starting testLRQuine
### testEqq pass: testLRQuine_λ, λ
### testEqq pass: testLRQuine_λλ, λλ
### testEqq pass: testLRQuine_l, l
### testEqq pass: testLRQuine_r, r
### testEqq pass: testLRQuine_w, w
### testEqq pass: testLRQuine_(pair w (pair pair pair)), (pair w (pair pair pair))
Starting testIdentityFuncs
### test pass: leaf.l()==i
### test pass: leaf.r()==leaf
### test pass: stt.f(i)==i
### test pass: stt.f(t)==t
### test pass: stt.f(f)==t
### test pass: i.f(stt)==stt
### test pass: i.f(t)==t
Starting testSTLR
### test pass: st.L()==s
### test pass: st.R()==t
### test pass: st.L()==s 2
### test pass: st.R()==t 2
Starting testConsCarCdr. Nil is leaf/theUniversalFunction. isNil is the isLeaf op.
### testEqq pass: testConsCarCdr_1, w
### testEqq pass: testConsCarCdr_2, λ
### testEqq pass: testConsCarCdr_3, l
### testEqq pass: testConsCarCdr_4, λ
### testEqq pass: isNil_nil, t
### testEqq pass: isNil_[list_N_A_L], f
Starting testProgn (which is about ImportStatic.progn, a java function, not a lambda form of the same thing (TODO), but it generates lambdas.
### testEqq pass: progn(l), l
### testEqq pass: progn(l,r).e(pair.e(t).e(f)), t
### testEqq pass: progn(r,l).e(pair.e(t).e(pair.e(wiki).e(typeval))), (pair w)
### testEqq pass: progn(r,l,l).e(pair.e(t).e(pair.e(wiki).e(typeval))), pair
### testEqq pass: progn(r,l,l).e(pair.e(t).e(pair.e(wiki).e(typeval))), w
progn(r,l,l) = {,l {,l r}}
### testEqq pass: progn(l,r).e(pair.e(t).e(pair.e(wiki).e(typeval))), t
Starting testCurry1ToCurry16
Starting testCurry1
curry1 = (λ λλ λ λ λ λ λ)
curry1.curriesMore = 2
curry1.curriesAll = 8
curry1.curriesSoFar = 6
### testEqq pass: curry1.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry1.curriesMore is 1+numParams==2 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry1.curriesAll is 6+numParams==8 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 1 param(s).
### testEqq pass: test curry1.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(λ λλ λ λ λ λ λ r)
getMthLastParam=0 mForward=0 param=λλ i=0 n=1
Before last call: (λ λλ λ λ λ λ λ r), about to call that on λλ
### testEqq pass: testCurry1_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry1. funcBody = r
evaling. op=curry1.     func = (λ λλ λ λ λ λ λ r)
evaling. op=curry1     param = λλ
curryDatastruct = (pair (λ λλ λ λ λ λ λ r) λλ), about to call funcBody on it
About to call (r (pair (λ λλ λ λ λ λ λ r) λλ))
### testEqq pass: testCurry1_get0thLastParam_returned, λλ
Starting testCurry2
curry2 = c2
curry2.curriesMore = 3
curry2.curriesAll = 9
curry2.curriesSoFar = 6
### testEqq pass: curry2.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry2.curriesMore is 1+numParams==3 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry2.curriesAll is 6+numParams==9 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 2 param(s).
### testEqq pass: test curry2.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c2 r)
getMthLastParam=0 mForward=1 param=λ i=0 n=2
x=(c2 r λ)
getMthLastParam=0 mForward=1 param=λλ i=1 n=2
Before last call: (c2 r λ), about to call that on λλ
### testEqq pass: testCurry2_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry2. funcBody = r
evaling. op=curry2.     func = (c2 r λ)
evaling. op=curry2     param = λλ
curryDatastruct = (pair (c2 r λ) λλ), about to call funcBody on it
About to call (r (pair (c2 r λ) λλ))
### testEqq pass: testCurry2_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c2 {,r {,r l}})
getMthLastParam=1 mForward=0 param=λλ i=0 n=2
x=(c2 {,r {,r l}} λλ)
getMthLastParam=1 mForward=0 param=λ i=1 n=2
Before last call: (c2 {,r {,r l}} λλ), about to call that on λ
### testEqq pass: testCurry2_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry2. funcBody = {,r {,r l}}
evaling. op=curry2.     func = (c2 {,r {,r l}} λλ)
evaling. op=curry2     param = λ
curryDatastruct = (pair (c2 {,r {,r l}} λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c2 {,r {,r l}} λλ) λ))
### testEqq pass: testCurry2_get1thLastParam_returned, λλ
Starting testCurry3
curry3 = c3
curry3.curriesMore = 4
curry3.curriesAll = 10
curry3.curriesSoFar = 6
### testEqq pass: curry3.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry3.curriesMore is 1+numParams==4 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry3.curriesAll is 6+numParams==10 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 3 param(s).
### testEqq pass: test curry3.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c3 r)
getMthLastParam=0 mForward=2 param=λ i=0 n=3
x=(c3 r λ)
getMthLastParam=0 mForward=2 param=λ i=1 n=3
x=(c3 r λ λ)
getMthLastParam=0 mForward=2 param=λλ i=2 n=3
Before last call: (c3 r λ λ), about to call that on λλ
### testEqq pass: testCurry3_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry3. funcBody = r
evaling. op=curry3.     func = (c3 r λ λ)
evaling. op=curry3     param = λλ
curryDatastruct = (pair (c3 r λ λ) λλ), about to call funcBody on it
About to call (r (pair (c3 r λ λ) λλ))
### testEqq pass: testCurry3_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c3 {,r {,r l}})
getMthLastParam=1 mForward=1 param=λ i=0 n=3
x=(c3 {,r {,r l}} λ)
getMthLastParam=1 mForward=1 param=λλ i=1 n=3
x=(c3 {,r {,r l}} λ λλ)
getMthLastParam=1 mForward=1 param=λ i=2 n=3
Before last call: (c3 {,r {,r l}} λ λλ), about to call that on λ
### testEqq pass: testCurry3_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry3. funcBody = {,r {,r l}}
evaling. op=curry3.     func = (c3 {,r {,r l}} λ λλ)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,r l}} λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c3 {,r {,r l}} λ λλ) λ))
### testEqq pass: testCurry3_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c3 {,r {,l {,r l}}})
getMthLastParam=2 mForward=0 param=λλ i=0 n=3
x=(c3 {,r {,l {,r l}}} λλ)
getMthLastParam=2 mForward=0 param=λ i=1 n=3
x=(c3 {,r {,l {,r l}}} λλ λ)
getMthLastParam=2 mForward=0 param=λ i=2 n=3
Before last call: (c3 {,r {,l {,r l}}} λλ λ), about to call that on λ
### testEqq pass: testCurry3_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry3. funcBody = {,r {,l {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}}} λλ λ)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}}} λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c3 {,r {,l {,r l}}} λλ λ) λ))
### testEqq pass: testCurry3_get2thLastParam_returned, λλ
Starting testCurry4
curry4 = c4
curry4.curriesMore = 5
curry4.curriesAll = 11
curry4.curriesSoFar = 6
### testEqq pass: curry4.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry4.curriesMore is 1+numParams==5 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry4.curriesAll is 6+numParams==11 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 4 param(s).
### testEqq pass: test curry4.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c4 r)
getMthLastParam=0 mForward=3 param=λ i=0 n=4
x=(c4 r λ)
getMthLastParam=0 mForward=3 param=λ i=1 n=4
x=(c4 r λ λ)
getMthLastParam=0 mForward=3 param=λ i=2 n=4
x=(c4 r λ λ λ)
getMthLastParam=0 mForward=3 param=λλ i=3 n=4
Before last call: (c4 r λ λ λ), about to call that on λλ
### testEqq pass: testCurry4_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry4. funcBody = r
evaling. op=curry4.     func = (c4 r λ λ λ)
evaling. op=curry4     param = λλ
curryDatastruct = (pair (c4 r λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c4 r λ λ λ) λλ))
### testEqq pass: testCurry4_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c4 {,r {,r l}})
getMthLastParam=1 mForward=2 param=λ i=0 n=4
x=(c4 {,r {,r l}} λ)
getMthLastParam=1 mForward=2 param=λ i=1 n=4
x=(c4 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=2 param=λλ i=2 n=4
x=(c4 {,r {,r l}} λ λ λλ)
getMthLastParam=1 mForward=2 param=λ i=3 n=4
Before last call: (c4 {,r {,r l}} λ λ λλ), about to call that on λ
### testEqq pass: testCurry4_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry4. funcBody = {,r {,r l}}
evaling. op=curry4.     func = (c4 {,r {,r l}} λ λ λλ)
evaling. op=curry4     param = λ
curryDatastruct = (pair (c4 {,r {,r l}} λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c4 {,r {,r l}} λ λ λλ) λ))
### testEqq pass: testCurry4_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c4 {,r {,l {,r l}}})
getMthLastParam=2 mForward=1 param=λ i=0 n=4
x=(c4 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=1 param=λλ i=1 n=4
x=(c4 {,r {,l {,r l}}} λ λλ)
getMthLastParam=2 mForward=1 param=λ i=2 n=4
x=(c4 {,r {,l {,r l}}} λ λλ λ)
getMthLastParam=2 mForward=1 param=λ i=3 n=4
Before last call: (c4 {,r {,l {,r l}}} λ λλ λ), about to call that on λ
### testEqq pass: testCurry4_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry4. funcBody = {,r {,l {,r l}}}
evaling. op=curry4.     func = (c4 {,r {,l {,r l}}} λ λλ λ)
evaling. op=curry4     param = λ
curryDatastruct = (pair (c4 {,r {,l {,r l}}} λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c4 {,r {,l {,r l}}} λ λλ λ) λ))
### testEqq pass: testCurry4_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c4 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=0 param=λλ i=0 n=4
x=(c4 {,r {,l {,l {,r l}}}} λλ)
getMthLastParam=3 mForward=0 param=λ i=1 n=4
x=(c4 {,r {,l {,l {,r l}}}} λλ λ)
getMthLastParam=3 mForward=0 param=λ i=2 n=4
x=(c4 {,r {,l {,l {,r l}}}} λλ λ λ)
getMthLastParam=3 mForward=0 param=λ i=3 n=4
Before last call: (c4 {,r {,l {,l {,r l}}}} λλ λ λ), about to call that on λ
### testEqq pass: testCurry4_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry4. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry4.     func = (c4 {,r {,l {,l {,r l}}}} λλ λ λ)
evaling. op=curry4     param = λ
curryDatastruct = (pair (c4 {,r {,l {,l {,r l}}}} λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c4 {,r {,l {,l {,r l}}}} λλ λ λ) λ))
### testEqq pass: testCurry4_get3thLastParam_returned, λλ
Starting testCurry5
curry5 = c5
curry5.curriesMore = 6
curry5.curriesAll = 12
curry5.curriesSoFar = 6
### testEqq pass: curry5.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry5.curriesMore is 1+numParams==6 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry5.curriesAll is 6+numParams==12 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 5 param(s).
### testEqq pass: test curry5.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c5 r)
getMthLastParam=0 mForward=4 param=λ i=0 n=5
x=(c5 r λ)
getMthLastParam=0 mForward=4 param=λ i=1 n=5
x=(c5 r λ λ)
getMthLastParam=0 mForward=4 param=λ i=2 n=5
x=(c5 r λ λ λ)
getMthLastParam=0 mForward=4 param=λ i=3 n=5
x=(c5 r λ λ λ λ)
getMthLastParam=0 mForward=4 param=λλ i=4 n=5
Before last call: (c5 r λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry5_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry5. funcBody = r
evaling. op=curry5.     func = (c5 r λ λ λ λ)
evaling. op=curry5     param = λλ
curryDatastruct = (pair (c5 r λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c5 r λ λ λ λ) λλ))
### testEqq pass: testCurry5_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c5 {,r {,r l}})
getMthLastParam=1 mForward=3 param=λ i=0 n=5
x=(c5 {,r {,r l}} λ)
getMthLastParam=1 mForward=3 param=λ i=1 n=5
x=(c5 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=3 param=λ i=2 n=5
x=(c5 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=3 param=λλ i=3 n=5
x=(c5 {,r {,r l}} λ λ λ λλ)
getMthLastParam=1 mForward=3 param=λ i=4 n=5
Before last call: (c5 {,r {,r l}} λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry5_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry5. funcBody = {,r {,r l}}
evaling. op=curry5.     func = (c5 {,r {,r l}} λ λ λ λλ)
evaling. op=curry5     param = λ
curryDatastruct = (pair (c5 {,r {,r l}} λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c5 {,r {,r l}} λ λ λ λλ) λ))
### testEqq pass: testCurry5_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c5 {,r {,l {,r l}}})
getMthLastParam=2 mForward=2 param=λ i=0 n=5
x=(c5 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=2 param=λ i=1 n=5
x=(c5 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=2 param=λλ i=2 n=5
x=(c5 {,r {,l {,r l}}} λ λ λλ)
getMthLastParam=2 mForward=2 param=λ i=3 n=5
x=(c5 {,r {,l {,r l}}} λ λ λλ λ)
getMthLastParam=2 mForward=2 param=λ i=4 n=5
Before last call: (c5 {,r {,l {,r l}}} λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry5_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry5. funcBody = {,r {,l {,r l}}}
evaling. op=curry5.     func = (c5 {,r {,l {,r l}}} λ λ λλ λ)
evaling. op=curry5     param = λ
curryDatastruct = (pair (c5 {,r {,l {,r l}}} λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c5 {,r {,l {,r l}}} λ λ λλ λ) λ))
### testEqq pass: testCurry5_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c5 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=1 param=λ i=0 n=5
x=(c5 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=1 param=λλ i=1 n=5
x=(c5 {,r {,l {,l {,r l}}}} λ λλ)
getMthLastParam=3 mForward=1 param=λ i=2 n=5
x=(c5 {,r {,l {,l {,r l}}}} λ λλ λ)
getMthLastParam=3 mForward=1 param=λ i=3 n=5
x=(c5 {,r {,l {,l {,r l}}}} λ λλ λ λ)
getMthLastParam=3 mForward=1 param=λ i=4 n=5
Before last call: (c5 {,r {,l {,l {,r l}}}} λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry5_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry5. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry5.     func = (c5 {,r {,l {,l {,r l}}}} λ λλ λ λ)
evaling. op=curry5     param = λ
curryDatastruct = (pair (c5 {,r {,l {,l {,r l}}}} λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c5 {,r {,l {,l {,r l}}}} λ λλ λ λ) λ))
### testEqq pass: testCurry5_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c5 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=0 param=λλ i=0 n=5
x=(c5 {,r {,l {,l {,l {,r l}}}}} λλ)
getMthLastParam=4 mForward=0 param=λ i=1 n=5
x=(c5 {,r {,l {,l {,l {,r l}}}}} λλ λ)
getMthLastParam=4 mForward=0 param=λ i=2 n=5
x=(c5 {,r {,l {,l {,l {,r l}}}}} λλ λ λ)
getMthLastParam=4 mForward=0 param=λ i=3 n=5
x=(c5 {,r {,l {,l {,l {,r l}}}}} λλ λ λ λ)
getMthLastParam=4 mForward=0 param=λ i=4 n=5
Before last call: (c5 {,r {,l {,l {,l {,r l}}}}} λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry5_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry5. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry5.     func = (c5 {,r {,l {,l {,l {,r l}}}}} λλ λ λ λ)
evaling. op=curry5     param = λ
curryDatastruct = (pair (c5 {,r {,l {,l {,l {,r l}}}}} λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c5 {,r {,l {,l {,l {,r l}}}}} λλ λ λ λ) λ))
### testEqq pass: testCurry5_get4thLastParam_returned, λλ
Starting testCurry6
curry6 = c6
curry6.curriesMore = 7
curry6.curriesAll = 13
curry6.curriesSoFar = 6
### testEqq pass: curry6.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry6.curriesMore is 1+numParams==7 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry6.curriesAll is 6+numParams==13 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 6 param(s).
### testEqq pass: test curry6.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c6 r)
getMthLastParam=0 mForward=5 param=λ i=0 n=6
x=(c6 r λ)
getMthLastParam=0 mForward=5 param=λ i=1 n=6
x=(c6 r λ λ)
getMthLastParam=0 mForward=5 param=λ i=2 n=6
x=(c6 r λ λ λ)
getMthLastParam=0 mForward=5 param=λ i=3 n=6
x=(c6 r λ λ λ λ)
getMthLastParam=0 mForward=5 param=λ i=4 n=6
x=(c6 r λ λ λ λ λ)
getMthLastParam=0 mForward=5 param=λλ i=5 n=6
Before last call: (c6 r λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry6_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry6. funcBody = r
evaling. op=curry6.     func = (c6 r λ λ λ λ λ)
evaling. op=curry6     param = λλ
curryDatastruct = (pair (c6 r λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c6 r λ λ λ λ λ) λλ))
### testEqq pass: testCurry6_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c6 {,r {,r l}})
getMthLastParam=1 mForward=4 param=λ i=0 n=6
x=(c6 {,r {,r l}} λ)
getMthLastParam=1 mForward=4 param=λ i=1 n=6
x=(c6 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=4 param=λ i=2 n=6
x=(c6 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=4 param=λ i=3 n=6
x=(c6 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=4 param=λλ i=4 n=6
x=(c6 {,r {,r l}} λ λ λ λ λλ)
getMthLastParam=1 mForward=4 param=λ i=5 n=6
Before last call: (c6 {,r {,r l}} λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry6_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry6. funcBody = {,r {,r l}}
evaling. op=curry6.     func = (c6 {,r {,r l}} λ λ λ λ λλ)
evaling. op=curry6     param = λ
curryDatastruct = (pair (c6 {,r {,r l}} λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c6 {,r {,r l}} λ λ λ λ λλ) λ))
### testEqq pass: testCurry6_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c6 {,r {,l {,r l}}})
getMthLastParam=2 mForward=3 param=λ i=0 n=6
x=(c6 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=3 param=λ i=1 n=6
x=(c6 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=3 param=λ i=2 n=6
x=(c6 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=3 param=λλ i=3 n=6
x=(c6 {,r {,l {,r l}}} λ λ λ λλ)
getMthLastParam=2 mForward=3 param=λ i=4 n=6
x=(c6 {,r {,l {,r l}}} λ λ λ λλ λ)
getMthLastParam=2 mForward=3 param=λ i=5 n=6
Before last call: (c6 {,r {,l {,r l}}} λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry6_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry6. funcBody = {,r {,l {,r l}}}
evaling. op=curry6.     func = (c6 {,r {,l {,r l}}} λ λ λ λλ λ)
evaling. op=curry6     param = λ
curryDatastruct = (pair (c6 {,r {,l {,r l}}} λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c6 {,r {,l {,r l}}} λ λ λ λλ λ) λ))
### testEqq pass: testCurry6_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c6 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=2 param=λ i=0 n=6
x=(c6 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=2 param=λ i=1 n=6
x=(c6 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=2 param=λλ i=2 n=6
x=(c6 {,r {,l {,l {,r l}}}} λ λ λλ)
getMthLastParam=3 mForward=2 param=λ i=3 n=6
x=(c6 {,r {,l {,l {,r l}}}} λ λ λλ λ)
getMthLastParam=3 mForward=2 param=λ i=4 n=6
x=(c6 {,r {,l {,l {,r l}}}} λ λ λλ λ λ)
getMthLastParam=3 mForward=2 param=λ i=5 n=6
Before last call: (c6 {,r {,l {,l {,r l}}}} λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry6_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry6. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry6.     func = (c6 {,r {,l {,l {,r l}}}} λ λ λλ λ λ)
evaling. op=curry6     param = λ
curryDatastruct = (pair (c6 {,r {,l {,l {,r l}}}} λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c6 {,r {,l {,l {,r l}}}} λ λ λλ λ λ) λ))
### testEqq pass: testCurry6_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c6 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=1 param=λ i=0 n=6
x=(c6 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=1 param=λλ i=1 n=6
x=(c6 {,r {,l {,l {,l {,r l}}}}} λ λλ)
getMthLastParam=4 mForward=1 param=λ i=2 n=6
x=(c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ)
getMthLastParam=4 mForward=1 param=λ i=3 n=6
x=(c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ λ)
getMthLastParam=4 mForward=1 param=λ i=4 n=6
x=(c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ λ λ)
getMthLastParam=4 mForward=1 param=λ i=5 n=6
Before last call: (c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry6_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry6. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry6.     func = (c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ λ λ)
evaling. op=curry6     param = λ
curryDatastruct = (pair (c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c6 {,r {,l {,l {,l {,r l}}}}} λ λλ λ λ λ) λ))
### testEqq pass: testCurry6_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c6 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=0 param=λλ i=0 n=6
x=(c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ)
getMthLastParam=5 mForward=0 param=λ i=1 n=6
x=(c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ)
getMthLastParam=5 mForward=0 param=λ i=2 n=6
x=(c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ)
getMthLastParam=5 mForward=0 param=λ i=3 n=6
x=(c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ λ)
getMthLastParam=5 mForward=0 param=λ i=4 n=6
x=(c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ λ λ)
getMthLastParam=5 mForward=0 param=λ i=5 n=6
Before last call: (c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry6_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry6. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry6.     func = (c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ λ λ)
evaling. op=curry6     param = λ
curryDatastruct = (pair (c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c6 {,r {,l {,l {,l {,l {,r l}}}}}} λλ λ λ λ λ) λ))
### testEqq pass: testCurry6_get5thLastParam_returned, λλ
Starting testCurry7
curry7 = c7
curry7.curriesMore = 8
curry7.curriesAll = 14
curry7.curriesSoFar = 6
### testEqq pass: curry7.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry7.curriesMore is 1+numParams==8 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry7.curriesAll is 6+numParams==14 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 7 param(s).
### testEqq pass: test curry7.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c7 r)
getMthLastParam=0 mForward=6 param=λ i=0 n=7
x=(c7 r λ)
getMthLastParam=0 mForward=6 param=λ i=1 n=7
x=(c7 r λ λ)
getMthLastParam=0 mForward=6 param=λ i=2 n=7
x=(c7 r λ λ λ)
getMthLastParam=0 mForward=6 param=λ i=3 n=7
x=(c7 r λ λ λ λ)
getMthLastParam=0 mForward=6 param=λ i=4 n=7
x=(c7 r λ λ λ λ λ)
getMthLastParam=0 mForward=6 param=λ i=5 n=7
x=(c7 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=6 param=λλ i=6 n=7
Before last call: (c7 r λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry7_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = r
evaling. op=curry7.     func = (c7 r λ λ λ λ λ λ)
evaling. op=curry7     param = λλ
curryDatastruct = (pair (c7 r λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c7 r λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry7_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c7 {,r {,r l}})
getMthLastParam=1 mForward=5 param=λ i=0 n=7
x=(c7 {,r {,r l}} λ)
getMthLastParam=1 mForward=5 param=λ i=1 n=7
x=(c7 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=5 param=λ i=2 n=7
x=(c7 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=5 param=λ i=3 n=7
x=(c7 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=5 param=λ i=4 n=7
x=(c7 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=5 param=λλ i=5 n=7
x=(c7 {,r {,r l}} λ λ λ λ λ λλ)
getMthLastParam=1 mForward=5 param=λ i=6 n=7
Before last call: (c7 {,r {,r l}} λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry7_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = {,r {,r l}}
evaling. op=curry7.     func = (c7 {,r {,r l}} λ λ λ λ λ λλ)
evaling. op=curry7     param = λ
curryDatastruct = (pair (c7 {,r {,r l}} λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c7 {,r {,r l}} λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry7_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c7 {,r {,l {,r l}}})
getMthLastParam=2 mForward=4 param=λ i=0 n=7
x=(c7 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=4 param=λ i=1 n=7
x=(c7 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=4 param=λ i=2 n=7
x=(c7 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=4 param=λ i=3 n=7
x=(c7 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=4 param=λλ i=4 n=7
x=(c7 {,r {,l {,r l}}} λ λ λ λ λλ)
getMthLastParam=2 mForward=4 param=λ i=5 n=7
x=(c7 {,r {,l {,r l}}} λ λ λ λ λλ λ)
getMthLastParam=2 mForward=4 param=λ i=6 n=7
Before last call: (c7 {,r {,l {,r l}}} λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry7_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = {,r {,l {,r l}}}
evaling. op=curry7.     func = (c7 {,r {,l {,r l}}} λ λ λ λ λλ λ)
evaling. op=curry7     param = λ
curryDatastruct = (pair (c7 {,r {,l {,r l}}} λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c7 {,r {,l {,r l}}} λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry7_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c7 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=3 param=λ i=0 n=7
x=(c7 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=3 param=λ i=1 n=7
x=(c7 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=3 param=λ i=2 n=7
x=(c7 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=3 param=λλ i=3 n=7
x=(c7 {,r {,l {,l {,r l}}}} λ λ λ λλ)
getMthLastParam=3 mForward=3 param=λ i=4 n=7
x=(c7 {,r {,l {,l {,r l}}}} λ λ λ λλ λ)
getMthLastParam=3 mForward=3 param=λ i=5 n=7
x=(c7 {,r {,l {,l {,r l}}}} λ λ λ λλ λ λ)
getMthLastParam=3 mForward=3 param=λ i=6 n=7
Before last call: (c7 {,r {,l {,l {,r l}}}} λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry7_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry7.     func = (c7 {,r {,l {,l {,r l}}}} λ λ λ λλ λ λ)
evaling. op=curry7     param = λ
curryDatastruct = (pair (c7 {,r {,l {,l {,r l}}}} λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c7 {,r {,l {,l {,r l}}}} λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry7_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c7 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=2 param=λ i=0 n=7
x=(c7 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=2 param=λ i=1 n=7
x=(c7 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=2 param=λλ i=2 n=7
x=(c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ)
getMthLastParam=4 mForward=2 param=λ i=3 n=7
x=(c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ)
getMthLastParam=4 mForward=2 param=λ i=4 n=7
x=(c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ λ)
getMthLastParam=4 mForward=2 param=λ i=5 n=7
x=(c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ λ λ)
getMthLastParam=4 mForward=2 param=λ i=6 n=7
Before last call: (c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry7_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry7.     func = (c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ λ λ)
evaling. op=curry7     param = λ
curryDatastruct = (pair (c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c7 {,r {,l {,l {,l {,r l}}}}} λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry7_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=1 param=λ i=0 n=7
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=1 param=λλ i=1 n=7
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ)
getMthLastParam=5 mForward=1 param=λ i=2 n=7
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ)
getMthLastParam=5 mForward=1 param=λ i=3 n=7
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ)
getMthLastParam=5 mForward=1 param=λ i=4 n=7
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ λ)
getMthLastParam=5 mForward=1 param=λ i=5 n=7
x=(c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ λ λ)
getMthLastParam=5 mForward=1 param=λ i=6 n=7
Before last call: (c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry7_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry7.     func = (c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ λ λ)
evaling. op=curry7     param = λ
curryDatastruct = (pair (c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c7 {,r {,l {,l {,l {,l {,r l}}}}}} λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry7_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=0 param=λλ i=0 n=7
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ)
getMthLastParam=6 mForward=0 param=λ i=1 n=7
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ)
getMthLastParam=6 mForward=0 param=λ i=2 n=7
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ)
getMthLastParam=6 mForward=0 param=λ i=3 n=7
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ)
getMthLastParam=6 mForward=0 param=λ i=4 n=7
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ λ)
getMthLastParam=6 mForward=0 param=λ i=5 n=7
x=(c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=6 mForward=0 param=λ i=6 n=7
Before last call: (c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry7_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry7. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry7.     func = (c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ λ λ)
evaling. op=curry7     param = λ
curryDatastruct = (pair (c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c7 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry7_get6thLastParam_returned, λλ
Starting testCurry8
curry8 = c8
curry8.curriesMore = 9
curry8.curriesAll = 15
curry8.curriesSoFar = 6
### testEqq pass: curry8.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry8.curriesMore is 1+numParams==9 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry8.curriesAll is 6+numParams==15 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 8 param(s).
### testEqq pass: test curry8.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c8 r)
getMthLastParam=0 mForward=7 param=λ i=0 n=8
x=(c8 r λ)
getMthLastParam=0 mForward=7 param=λ i=1 n=8
x=(c8 r λ λ)
getMthLastParam=0 mForward=7 param=λ i=2 n=8
x=(c8 r λ λ λ)
getMthLastParam=0 mForward=7 param=λ i=3 n=8
x=(c8 r λ λ λ λ)
getMthLastParam=0 mForward=7 param=λ i=4 n=8
x=(c8 r λ λ λ λ λ)
getMthLastParam=0 mForward=7 param=λ i=5 n=8
x=(c8 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=7 param=λ i=6 n=8
x=(c8 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=7 param=λλ i=7 n=8
Before last call: (c8 r λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry8_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = r
evaling. op=curry8.     func = (c8 r λ λ λ λ λ λ λ)
evaling. op=curry8     param = λλ
curryDatastruct = (pair (c8 r λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c8 r λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry8_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c8 {,r {,r l}})
getMthLastParam=1 mForward=6 param=λ i=0 n=8
x=(c8 {,r {,r l}} λ)
getMthLastParam=1 mForward=6 param=λ i=1 n=8
x=(c8 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=6 param=λ i=2 n=8
x=(c8 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=6 param=λ i=3 n=8
x=(c8 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=6 param=λ i=4 n=8
x=(c8 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=6 param=λ i=5 n=8
x=(c8 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=6 param=λλ i=6 n=8
x=(c8 {,r {,r l}} λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=6 param=λ i=7 n=8
Before last call: (c8 {,r {,r l}} λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry8_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,r l}}
evaling. op=curry8.     func = (c8 {,r {,r l}} λ λ λ λ λ λ λλ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,r l}} λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c8 {,r {,r l}} λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry8_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c8 {,r {,l {,r l}}})
getMthLastParam=2 mForward=5 param=λ i=0 n=8
x=(c8 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=5 param=λ i=1 n=8
x=(c8 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=5 param=λ i=2 n=8
x=(c8 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=5 param=λ i=3 n=8
x=(c8 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=5 param=λ i=4 n=8
x=(c8 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=5 param=λλ i=5 n=8
x=(c8 {,r {,l {,r l}}} λ λ λ λ λ λλ)
getMthLastParam=2 mForward=5 param=λ i=6 n=8
x=(c8 {,r {,l {,r l}}} λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=5 param=λ i=7 n=8
Before last call: (c8 {,r {,l {,r l}}} λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry8_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,l {,r l}}}
evaling. op=curry8.     func = (c8 {,r {,l {,r l}}} λ λ λ λ λ λλ λ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,l {,r l}}} λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c8 {,r {,l {,r l}}} λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry8_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c8 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=4 param=λ i=0 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=4 param=λ i=1 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=4 param=λ i=2 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=4 param=λ i=3 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=4 param=λλ i=4 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ)
getMthLastParam=3 mForward=4 param=λ i=5 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ λ)
getMthLastParam=3 mForward=4 param=λ i=6 n=8
x=(c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=4 param=λ i=7 n=8
Before last call: (c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry8_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry8.     func = (c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ λ λ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c8 {,r {,l {,l {,r l}}}} λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry8_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c8 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=3 param=λ i=0 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=3 param=λ i=1 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=3 param=λ i=2 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=3 param=λλ i=3 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ)
getMthLastParam=4 mForward=3 param=λ i=4 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ)
getMthLastParam=4 mForward=3 param=λ i=5 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ λ)
getMthLastParam=4 mForward=3 param=λ i=6 n=8
x=(c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=3 param=λ i=7 n=8
Before last call: (c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry8_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry8.     func = (c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ λ λ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c8 {,r {,l {,l {,l {,r l}}}}} λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry8_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=2 param=λ i=0 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=2 param=λ i=1 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=2 param=λλ i=2 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ)
getMthLastParam=5 mForward=2 param=λ i=3 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ)
getMthLastParam=5 mForward=2 param=λ i=4 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ)
getMthLastParam=5 mForward=2 param=λ i=5 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ λ)
getMthLastParam=5 mForward=2 param=λ i=6 n=8
x=(c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=2 param=λ i=7 n=8
Before last call: (c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry8_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry8.     func = (c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ λ λ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c8 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry8_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=1 param=λ i=0 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=1 param=λλ i=1 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ)
getMthLastParam=6 mForward=1 param=λ i=2 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ)
getMthLastParam=6 mForward=1 param=λ i=3 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ)
getMthLastParam=6 mForward=1 param=λ i=4 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ)
getMthLastParam=6 mForward=1 param=λ i=5 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=6 mForward=1 param=λ i=6 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=1 param=λ i=7 n=8
Before last call: (c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry8_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry8.     func = (c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ λ λ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c8 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry8_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=0 param=λλ i=0 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ)
getMthLastParam=7 mForward=0 param=λ i=1 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ)
getMthLastParam=7 mForward=0 param=λ i=2 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ)
getMthLastParam=7 mForward=0 param=λ i=3 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ)
getMthLastParam=7 mForward=0 param=λ i=4 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ)
getMthLastParam=7 mForward=0 param=λ i=5 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=7 mForward=0 param=λ i=6 n=8
x=(c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=0 param=λ i=7 n=8
Before last call: (c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry8_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry8. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry8.     func = (c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ λ λ)
evaling. op=curry8     param = λ
curryDatastruct = (pair (c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c8 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry8_get7thLastParam_returned, λλ
Starting testCurry9
curry9 = c9
curry9.curriesMore = 10
curry9.curriesAll = 16
curry9.curriesSoFar = 6
### testEqq pass: curry9.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry9.curriesMore is 1+numParams==10 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry9.curriesAll is 6+numParams==16 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 9 param(s).
### testEqq pass: test curry9.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c9 r)
getMthLastParam=0 mForward=8 param=λ i=0 n=9
x=(c9 r λ)
getMthLastParam=0 mForward=8 param=λ i=1 n=9
x=(c9 r λ λ)
getMthLastParam=0 mForward=8 param=λ i=2 n=9
x=(c9 r λ λ λ)
getMthLastParam=0 mForward=8 param=λ i=3 n=9
x=(c9 r λ λ λ λ)
getMthLastParam=0 mForward=8 param=λ i=4 n=9
x=(c9 r λ λ λ λ λ)
getMthLastParam=0 mForward=8 param=λ i=5 n=9
x=(c9 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=8 param=λ i=6 n=9
x=(c9 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=8 param=λ i=7 n=9
x=(c9 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=8 param=λλ i=8 n=9
Before last call: (c9 r λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry9_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = r
evaling. op=curry9.     func = (c9 r λ λ λ λ λ λ λ λ)
evaling. op=curry9     param = λλ
curryDatastruct = (pair (c9 r λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c9 r λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry9_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c9 {,r {,r l}})
getMthLastParam=1 mForward=7 param=λ i=0 n=9
x=(c9 {,r {,r l}} λ)
getMthLastParam=1 mForward=7 param=λ i=1 n=9
x=(c9 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=7 param=λ i=2 n=9
x=(c9 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=7 param=λ i=3 n=9
x=(c9 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=7 param=λ i=4 n=9
x=(c9 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=7 param=λ i=5 n=9
x=(c9 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=7 param=λ i=6 n=9
x=(c9 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=7 param=λλ i=7 n=9
x=(c9 {,r {,r l}} λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=7 param=λ i=8 n=9
Before last call: (c9 {,r {,r l}} λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry9_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,r l}}
evaling. op=curry9.     func = (c9 {,r {,r l}} λ λ λ λ λ λ λ λλ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,r l}} λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c9 {,r {,r l}} λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry9_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c9 {,r {,l {,r l}}})
getMthLastParam=2 mForward=6 param=λ i=0 n=9
x=(c9 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=6 param=λ i=1 n=9
x=(c9 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=6 param=λ i=2 n=9
x=(c9 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=6 param=λ i=3 n=9
x=(c9 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=6 param=λ i=4 n=9
x=(c9 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=6 param=λ i=5 n=9
x=(c9 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=6 param=λλ i=6 n=9
x=(c9 {,r {,l {,r l}}} λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=6 param=λ i=7 n=9
x=(c9 {,r {,l {,r l}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=6 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,r l}}} λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry9_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,r l}}}
evaling. op=curry9.     func = (c9 {,r {,l {,r l}}} λ λ λ λ λ λ λλ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,r l}}} λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c9 {,r {,l {,r l}}} λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry9_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c9 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=5 param=λ i=0 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=5 param=λ i=1 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=5 param=λ i=2 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=5 param=λ i=3 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=5 param=λ i=4 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=5 param=λλ i=5 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ)
getMthLastParam=3 mForward=5 param=λ i=6 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=5 param=λ i=7 n=9
x=(c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=5 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry9_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry9.     func = (c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ λ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c9 {,r {,l {,l {,r l}}}} λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry9_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c9 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=4 param=λ i=0 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=4 param=λ i=1 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=4 param=λ i=2 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=4 param=λ i=3 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=4 param=λλ i=4 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ)
getMthLastParam=4 mForward=4 param=λ i=5 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ)
getMthLastParam=4 mForward=4 param=λ i=6 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=4 param=λ i=7 n=9
x=(c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=4 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry9_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry9.     func = (c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ λ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c9 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry9_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=3 param=λ i=0 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=3 param=λ i=1 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=3 param=λ i=2 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=3 param=λλ i=3 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ)
getMthLastParam=5 mForward=3 param=λ i=4 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ)
getMthLastParam=5 mForward=3 param=λ i=5 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ)
getMthLastParam=5 mForward=3 param=λ i=6 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=3 param=λ i=7 n=9
x=(c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=3 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry9_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry9.     func = (c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ λ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c9 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry9_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=2 param=λ i=0 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=2 param=λ i=1 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=2 param=λλ i=2 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ)
getMthLastParam=6 mForward=2 param=λ i=3 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ)
getMthLastParam=6 mForward=2 param=λ i=4 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ)
getMthLastParam=6 mForward=2 param=λ i=5 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=6 mForward=2 param=λ i=6 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=2 param=λ i=7 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=2 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry9_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry9.     func = (c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ λ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c9 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry9_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=1 param=λ i=0 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=1 param=λλ i=1 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ)
getMthLastParam=7 mForward=1 param=λ i=2 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ)
getMthLastParam=7 mForward=1 param=λ i=3 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ)
getMthLastParam=7 mForward=1 param=λ i=4 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ)
getMthLastParam=7 mForward=1 param=λ i=5 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=7 mForward=1 param=λ i=6 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=1 param=λ i=7 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=1 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry9_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry9.     func = (c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ λ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c9 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry9_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=0 param=λλ i=0 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ)
getMthLastParam=8 mForward=0 param=λ i=1 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ)
getMthLastParam=8 mForward=0 param=λ i=2 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ)
getMthLastParam=8 mForward=0 param=λ i=3 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ)
getMthLastParam=8 mForward=0 param=λ i=4 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=8 mForward=0 param=λ i=5 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=8 mForward=0 param=λ i=6 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=0 param=λ i=7 n=9
x=(c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=0 param=λ i=8 n=9
Before last call: (c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry9_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry9. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry9.     func = (c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ λ λ)
evaling. op=curry9     param = λ
curryDatastruct = (pair (c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c9 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry9_get8thLastParam_returned, λλ
Starting testCurry10
curry10 = c10
curry10.curriesMore = 11
curry10.curriesAll = 17
curry10.curriesSoFar = 6
### testEqq pass: curry10.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry10.curriesMore is 1+numParams==11 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry10.curriesAll is 6+numParams==17 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 10 param(s).
### testEqq pass: test curry10.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c10 r)
getMthLastParam=0 mForward=9 param=λ i=0 n=10
x=(c10 r λ)
getMthLastParam=0 mForward=9 param=λ i=1 n=10
x=(c10 r λ λ)
getMthLastParam=0 mForward=9 param=λ i=2 n=10
x=(c10 r λ λ λ)
getMthLastParam=0 mForward=9 param=λ i=3 n=10
x=(c10 r λ λ λ λ)
getMthLastParam=0 mForward=9 param=λ i=4 n=10
x=(c10 r λ λ λ λ λ)
getMthLastParam=0 mForward=9 param=λ i=5 n=10
x=(c10 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=9 param=λ i=6 n=10
x=(c10 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=9 param=λ i=7 n=10
x=(c10 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=9 param=λ i=8 n=10
x=(c10 r λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=9 param=λλ i=9 n=10
Before last call: (c10 r λ λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry10_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = r
evaling. op=curry10.     func = (c10 r λ λ λ λ λ λ λ λ λ)
evaling. op=curry10     param = λλ
curryDatastruct = (pair (c10 r λ λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c10 r λ λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry10_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c10 {,r {,r l}})
getMthLastParam=1 mForward=8 param=λ i=0 n=10
x=(c10 {,r {,r l}} λ)
getMthLastParam=1 mForward=8 param=λ i=1 n=10
x=(c10 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=8 param=λ i=2 n=10
x=(c10 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=8 param=λ i=3 n=10
x=(c10 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=8 param=λ i=4 n=10
x=(c10 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=8 param=λ i=5 n=10
x=(c10 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=8 param=λ i=6 n=10
x=(c10 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=8 param=λ i=7 n=10
x=(c10 {,r {,r l}} λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=8 param=λλ i=8 n=10
x=(c10 {,r {,r l}} λ λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=8 param=λ i=9 n=10
Before last call: (c10 {,r {,r l}} λ λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry10_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,r l}}
evaling. op=curry10.     func = (c10 {,r {,r l}} λ λ λ λ λ λ λ λ λλ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,r l}} λ λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c10 {,r {,r l}} λ λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry10_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c10 {,r {,l {,r l}}})
getMthLastParam=2 mForward=7 param=λ i=0 n=10
x=(c10 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=7 param=λ i=1 n=10
x=(c10 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=7 param=λ i=2 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=7 param=λ i=3 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=7 param=λ i=4 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=7 param=λ i=5 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=7 param=λ i=6 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=7 param=λλ i=7 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=7 param=λ i=8 n=10
x=(c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=7 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry10_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,r l}}}
evaling. op=curry10.     func = (c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ λλ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c10 {,r {,l {,r l}}} λ λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry10_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c10 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=6 param=λ i=0 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=6 param=λ i=1 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=6 param=λ i=2 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=6 param=λ i=3 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=6 param=λ i=4 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=6 param=λ i=5 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ)
getMthLastParam=3 mForward=6 param=λλ i=6 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ)
getMthLastParam=3 mForward=6 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=6 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=6 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry10_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c10 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry10_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c10 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=5 param=λ i=0 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=5 param=λ i=1 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=5 param=λ i=2 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=5 param=λ i=3 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=5 param=λ i=4 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ)
getMthLastParam=4 mForward=5 param=λλ i=5 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ)
getMthLastParam=4 mForward=5 param=λ i=6 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ)
getMthLastParam=4 mForward=5 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=5 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=5 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry10_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c10 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry10_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=4 param=λ i=0 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=4 param=λ i=1 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=4 param=λ i=2 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=4 param=λ i=3 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ)
getMthLastParam=5 mForward=4 param=λλ i=4 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ)
getMthLastParam=5 mForward=4 param=λ i=5 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ)
getMthLastParam=5 mForward=4 param=λ i=6 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=5 mForward=4 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=4 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=4 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry10_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c10 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry10_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=3 param=λ i=0 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=3 param=λ i=1 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=3 param=λ i=2 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ)
getMthLastParam=6 mForward=3 param=λλ i=3 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ)
getMthLastParam=6 mForward=3 param=λ i=4 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ)
getMthLastParam=6 mForward=3 param=λ i=5 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ)
getMthLastParam=6 mForward=3 param=λ i=6 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=6 mForward=3 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=3 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=3 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry10_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c10 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry10_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=2 param=λ i=0 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=2 param=λ i=1 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ)
getMthLastParam=7 mForward=2 param=λλ i=2 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ)
getMthLastParam=7 mForward=2 param=λ i=3 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ)
getMthLastParam=7 mForward=2 param=λ i=4 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ)
getMthLastParam=7 mForward=2 param=λ i=5 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=7 mForward=2 param=λ i=6 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=7 mForward=2 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=2 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=2 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry10_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c10 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry10_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=1 param=λ i=0 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ)
getMthLastParam=8 mForward=1 param=λλ i=1 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ)
getMthLastParam=8 mForward=1 param=λ i=2 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ)
getMthLastParam=8 mForward=1 param=λ i=3 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ)
getMthLastParam=8 mForward=1 param=λ i=4 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ)
getMthLastParam=8 mForward=1 param=λ i=5 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=8 mForward=1 param=λ i=6 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=8 mForward=1 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=1 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=1 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry10_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry10_get8thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}})
getMthLastParam=9 mForward=0 param=λλ i=0 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ)
getMthLastParam=9 mForward=0 param=λ i=1 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ)
getMthLastParam=9 mForward=0 param=λ i=2 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ)
getMthLastParam=9 mForward=0 param=λ i=3 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ)
getMthLastParam=9 mForward=0 param=λ i=4 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=9 mForward=0 param=λ i=5 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=9 mForward=0 param=λ i=6 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=9 mForward=0 param=λ i=7 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=0 param=λ i=8 n=10
x=(c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=0 param=λ i=9 n=10
Before last call: (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry10_get9thLastParam_verifyCurriesmoreIs1
evaling. op=curry10. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
evaling. op=curry10.     func = (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
evaling. op=curry10     param = λ
curryDatastruct = (pair (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} (pair (c10 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λλ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry10_get9thLastParam_returned, λλ
Starting testCurry11
curry11 = c11
curry11.curriesMore = 12
curry11.curriesAll = 18
curry11.curriesSoFar = 6
### testEqq pass: curry11.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry11.curriesMore is 1+numParams==12 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry11.curriesAll is 6+numParams==18 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 11 param(s).
### testEqq pass: test curry11.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c11 r)
getMthLastParam=0 mForward=10 param=λ i=0 n=11
x=(c11 r λ)
getMthLastParam=0 mForward=10 param=λ i=1 n=11
x=(c11 r λ λ)
getMthLastParam=0 mForward=10 param=λ i=2 n=11
x=(c11 r λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=3 n=11
x=(c11 r λ λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=4 n=11
x=(c11 r λ λ λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=5 n=11
x=(c11 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=6 n=11
x=(c11 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=7 n=11
x=(c11 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=8 n=11
x=(c11 r λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=10 param=λ i=9 n=11
x=(c11 r λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=10 param=λλ i=10 n=11
Before last call: (c11 r λ λ λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry11_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = r
evaling. op=curry11.     func = (c11 r λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry11     param = λλ
curryDatastruct = (pair (c11 r λ λ λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c11 r λ λ λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry11_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c11 {,r {,r l}})
getMthLastParam=1 mForward=9 param=λ i=0 n=11
x=(c11 {,r {,r l}} λ)
getMthLastParam=1 mForward=9 param=λ i=1 n=11
x=(c11 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=9 param=λ i=2 n=11
x=(c11 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=9 param=λ i=3 n=11
x=(c11 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=9 param=λ i=4 n=11
x=(c11 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=9 param=λ i=5 n=11
x=(c11 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=9 param=λ i=6 n=11
x=(c11 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=9 param=λ i=7 n=11
x=(c11 {,r {,r l}} λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=9 param=λ i=8 n=11
x=(c11 {,r {,r l}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=9 param=λλ i=9 n=11
x=(c11 {,r {,r l}} λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=9 param=λ i=10 n=11
Before last call: (c11 {,r {,r l}} λ λ λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry11_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,r l}}
evaling. op=curry11.     func = (c11 {,r {,r l}} λ λ λ λ λ λ λ λ λ λλ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,r l}} λ λ λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c11 {,r {,r l}} λ λ λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry11_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c11 {,r {,l {,r l}}})
getMthLastParam=2 mForward=8 param=λ i=0 n=11
x=(c11 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=8 param=λ i=1 n=11
x=(c11 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=8 param=λ i=2 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=8 param=λ i=3 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=8 param=λ i=4 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=8 param=λ i=5 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=8 param=λ i=6 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=8 param=λ i=7 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=8 param=λλ i=8 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=8 param=λ i=9 n=11
x=(c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=8 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry11_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,r l}}}
evaling. op=curry11.     func = (c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λλ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c11 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry11_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c11 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=7 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=7 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=7 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=7 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=7 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=7 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ)
getMthLastParam=3 mForward=7 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=7 param=λλ i=7 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ)
getMthLastParam=3 mForward=7 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=7 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=7 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry11_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c11 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry11_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c11 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=6 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=6 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=6 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=6 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=6 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ)
getMthLastParam=4 mForward=6 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ)
getMthLastParam=4 mForward=6 param=λλ i=6 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ)
getMthLastParam=4 mForward=6 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=4 mForward=6 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=6 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=6 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c11 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry11_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=5 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=5 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=5 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=5 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ)
getMthLastParam=5 mForward=5 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ)
getMthLastParam=5 mForward=5 param=λλ i=5 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ)
getMthLastParam=5 mForward=5 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ)
getMthLastParam=5 mForward=5 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=5 mForward=5 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=5 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=5 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c11 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry11_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=4 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=4 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=4 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ)
getMthLastParam=6 mForward=4 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ)
getMthLastParam=6 mForward=4 param=λλ i=4 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ)
getMthLastParam=6 mForward=4 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ)
getMthLastParam=6 mForward=4 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=6 mForward=4 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=6 mForward=4 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=4 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=4 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c11 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry11_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=3 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=3 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ)
getMthLastParam=7 mForward=3 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ)
getMthLastParam=7 mForward=3 param=λλ i=3 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ)
getMthLastParam=7 mForward=3 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ)
getMthLastParam=7 mForward=3 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ)
getMthLastParam=7 mForward=3 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=7 mForward=3 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=7 mForward=3 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=3 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=3 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry11_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=2 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ)
getMthLastParam=8 mForward=2 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ)
getMthLastParam=8 mForward=2 param=λλ i=2 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ)
getMthLastParam=8 mForward=2 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ)
getMthLastParam=8 mForward=2 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ)
getMthLastParam=8 mForward=2 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=8 mForward=2 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=8 mForward=2 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=8 mForward=2 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=2 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=2 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry11_get8thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}})
getMthLastParam=9 mForward=1 param=λ i=0 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ)
getMthLastParam=9 mForward=1 param=λλ i=1 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ)
getMthLastParam=9 mForward=1 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ)
getMthLastParam=9 mForward=1 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ)
getMthLastParam=9 mForward=1 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ)
getMthLastParam=9 mForward=1 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=9 mForward=1 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=9 mForward=1 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=9 mForward=1 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=1 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=1 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get9thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry11_get9thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}})
getMthLastParam=10 mForward=0 param=λλ i=0 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ)
getMthLastParam=10 mForward=0 param=λ i=1 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ)
getMthLastParam=10 mForward=0 param=λ i=2 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ)
getMthLastParam=10 mForward=0 param=λ i=3 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=4 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=5 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=6 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=7 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=8 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=9 n=11
x=(c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=0 param=λ i=10 n=11
Before last call: (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry11_get10thLastParam_verifyCurriesmoreIs1
evaling. op=curry11. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
evaling. op=curry11.     func = (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ)
evaling. op=curry11     param = λ
curryDatastruct = (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} (pair (c11 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry11_get10thLastParam_returned, λλ
Starting testCurry12
curry12 = c12
curry12.curriesMore = 13
curry12.curriesAll = 19
curry12.curriesSoFar = 6
### testEqq pass: curry12.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry12.curriesMore is 1+numParams==13 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry12.curriesAll is 6+numParams==19 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 12 param(s).
### testEqq pass: test curry12.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c12 r)
getMthLastParam=0 mForward=11 param=λ i=0 n=12
x=(c12 r λ)
getMthLastParam=0 mForward=11 param=λ i=1 n=12
x=(c12 r λ λ)
getMthLastParam=0 mForward=11 param=λ i=2 n=12
x=(c12 r λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=3 n=12
x=(c12 r λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=4 n=12
x=(c12 r λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=5 n=12
x=(c12 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=6 n=12
x=(c12 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=7 n=12
x=(c12 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=8 n=12
x=(c12 r λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=9 n=12
x=(c12 r λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λ i=10 n=12
x=(c12 r λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=11 param=λλ i=11 n=12
Before last call: (c12 r λ λ λ λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry12_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = r
evaling. op=curry12.     func = (c12 r λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry12     param = λλ
curryDatastruct = (pair (c12 r λ λ λ λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c12 r λ λ λ λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry12_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c12 {,r {,r l}})
getMthLastParam=1 mForward=10 param=λ i=0 n=12
x=(c12 {,r {,r l}} λ)
getMthLastParam=1 mForward=10 param=λ i=1 n=12
x=(c12 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=10 param=λ i=2 n=12
x=(c12 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=3 n=12
x=(c12 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=4 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=5 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=6 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=7 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=8 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=10 param=λ i=9 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=10 param=λλ i=10 n=12
x=(c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=10 param=λ i=11 n=12
Before last call: (c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry12_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,r l}}
evaling. op=curry12.     func = (c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λλ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c12 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry12_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c12 {,r {,l {,r l}}})
getMthLastParam=2 mForward=9 param=λ i=0 n=12
x=(c12 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=9 param=λ i=1 n=12
x=(c12 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=9 param=λ i=2 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=9 param=λ i=3 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=9 param=λ i=4 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=9 param=λ i=5 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=9 param=λ i=6 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=9 param=λ i=7 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=9 param=λ i=8 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=9 param=λλ i=9 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=9 param=λ i=10 n=12
x=(c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=9 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry12_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,r l}}}
evaling. op=curry12.     func = (c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λλ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c12 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry12_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c12 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=8 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=8 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=8 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=8 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=8 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=8 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ)
getMthLastParam=3 mForward=8 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=8 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=8 param=λλ i=8 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ)
getMthLastParam=3 mForward=8 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=8 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=8 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry12_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c12 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry12_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c12 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=7 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=7 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=7 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=7 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=7 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ)
getMthLastParam=4 mForward=7 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ)
getMthLastParam=4 mForward=7 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=7 param=λλ i=7 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ)
getMthLastParam=4 mForward=7 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ)
getMthLastParam=4 mForward=7 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=7 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=7 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c12 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry12_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=6 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=6 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=6 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=6 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ)
getMthLastParam=5 mForward=6 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ)
getMthLastParam=5 mForward=6 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ)
getMthLastParam=5 mForward=6 param=λλ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ)
getMthLastParam=5 mForward=6 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=5 mForward=6 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ)
getMthLastParam=5 mForward=6 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=6 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=6 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry12_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=5 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=5 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=5 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ)
getMthLastParam=6 mForward=5 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ)
getMthLastParam=6 mForward=5 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ)
getMthLastParam=6 mForward=5 param=λλ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ)
getMthLastParam=6 mForward=5 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ)
getMthLastParam=6 mForward=5 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=6 mForward=5 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ)
getMthLastParam=6 mForward=5 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=5 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=5 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry12_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=4 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=4 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ)
getMthLastParam=7 mForward=4 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ)
getMthLastParam=7 mForward=4 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ)
getMthLastParam=7 mForward=4 param=λλ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ)
getMthLastParam=7 mForward=4 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ)
getMthLastParam=7 mForward=4 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=7 mForward=4 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=7 mForward=4 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ)
getMthLastParam=7 mForward=4 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=4 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=4 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry12_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=3 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ)
getMthLastParam=8 mForward=3 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ)
getMthLastParam=8 mForward=3 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ)
getMthLastParam=8 mForward=3 param=λλ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ)
getMthLastParam=8 mForward=3 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ)
getMthLastParam=8 mForward=3 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ)
getMthLastParam=8 mForward=3 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=8 mForward=3 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=8 mForward=3 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ)
getMthLastParam=8 mForward=3 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=3 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=3 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry12_get8thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}})
getMthLastParam=9 mForward=2 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ)
getMthLastParam=9 mForward=2 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ)
getMthLastParam=9 mForward=2 param=λλ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ)
getMthLastParam=9 mForward=2 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ)
getMthLastParam=9 mForward=2 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ)
getMthLastParam=9 mForward=2 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=9 mForward=2 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=9 mForward=2 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=9 mForward=2 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ)
getMthLastParam=9 mForward=2 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=2 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=2 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get9thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry12_get9thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}})
getMthLastParam=10 mForward=1 param=λ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ)
getMthLastParam=10 mForward=1 param=λλ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ)
getMthLastParam=10 mForward=1 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ)
getMthLastParam=10 mForward=1 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ)
getMthLastParam=10 mForward=1 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=1 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get10thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry12_get10thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}})
getMthLastParam=11 mForward=0 param=λλ i=0 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ)
getMthLastParam=11 mForward=0 param=λ i=1 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ)
getMthLastParam=11 mForward=0 param=λ i=2 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ)
getMthLastParam=11 mForward=0 param=λ i=3 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=4 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=5 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=6 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=7 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=8 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=9 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=10 n=12
x=(c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=0 param=λ i=11 n=12
Before last call: (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry12_get11thLastParam_verifyCurriesmoreIs1
evaling. op=curry12. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
evaling. op=curry12.     func = (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry12     param = λ
curryDatastruct = (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} (pair (c12 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry12_get11thLastParam_returned, λλ
Starting testCurry13
curry13 = c13
curry13.curriesMore = 14
curry13.curriesAll = 20
curry13.curriesSoFar = 6
### testEqq pass: curry13.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry13.curriesMore is 1+numParams==14 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry13.curriesAll is 6+numParams==20 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 13 param(s).
### testEqq pass: test curry13.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c13 r)
getMthLastParam=0 mForward=12 param=λ i=0 n=13
x=(c13 r λ)
getMthLastParam=0 mForward=12 param=λ i=1 n=13
x=(c13 r λ λ)
getMthLastParam=0 mForward=12 param=λ i=2 n=13
x=(c13 r λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=3 n=13
x=(c13 r λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=4 n=13
x=(c13 r λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=5 n=13
x=(c13 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=6 n=13
x=(c13 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=7 n=13
x=(c13 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=8 n=13
x=(c13 r λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=9 n=13
x=(c13 r λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=10 n=13
x=(c13 r λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λ i=11 n=13
x=(c13 r λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=12 param=λλ i=12 n=13
Before last call: (c13 r λ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry13_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = r
evaling. op=curry13.     func = (c13 r λ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry13     param = λλ
curryDatastruct = (pair (c13 r λ λ λ λ λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c13 r λ λ λ λ λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry13_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c13 {,r {,r l}})
getMthLastParam=1 mForward=11 param=λ i=0 n=13
x=(c13 {,r {,r l}} λ)
getMthLastParam=1 mForward=11 param=λ i=1 n=13
x=(c13 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=11 param=λ i=2 n=13
x=(c13 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=3 n=13
x=(c13 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=4 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=5 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=6 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=7 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=8 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=9 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λ i=10 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=11 param=λλ i=11 n=13
x=(c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=11 param=λ i=12 n=13
Before last call: (c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry13_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,r l}}
evaling. op=curry13.     func = (c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λλ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c13 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry13_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c13 {,r {,l {,r l}}})
getMthLastParam=2 mForward=10 param=λ i=0 n=13
x=(c13 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=10 param=λ i=1 n=13
x=(c13 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=10 param=λ i=2 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=3 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=4 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=5 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=6 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=7 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=8 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=10 param=λ i=9 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=10 param=λλ i=10 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=10 param=λ i=11 n=13
x=(c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=10 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry13_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,r l}}}
evaling. op=curry13.     func = (c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λλ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c13 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry13_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c13 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=9 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=9 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=9 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=9 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=9 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=9 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ)
getMthLastParam=3 mForward=9 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=9 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=9 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=9 param=λλ i=9 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=3 mForward=9 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=9 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=9 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry13_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c13 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry13_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c13 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=8 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=8 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=8 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=8 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=8 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ)
getMthLastParam=4 mForward=8 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ)
getMthLastParam=4 mForward=8 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=8 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=8 param=λλ i=8 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ)
getMthLastParam=4 mForward=8 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=4 mForward=8 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=8 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=8 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c13 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry13_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=7 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=7 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=7 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=7 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ)
getMthLastParam=5 mForward=7 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ)
getMthLastParam=5 mForward=7 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ)
getMthLastParam=5 mForward=7 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ)
getMthLastParam=5 mForward=7 param=λλ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ)
getMthLastParam=5 mForward=7 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ)
getMthLastParam=5 mForward=7 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=5 mForward=7 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=7 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=7 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry13_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=6 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=6 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=6 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ)
getMthLastParam=6 mForward=6 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ)
getMthLastParam=6 mForward=6 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ)
getMthLastParam=6 mForward=6 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ)
getMthLastParam=6 mForward=6 param=λλ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ)
getMthLastParam=6 mForward=6 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=6 mForward=6 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ)
getMthLastParam=6 mForward=6 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=6 mForward=6 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=6 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=6 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=5 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=5 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ)
getMthLastParam=7 mForward=5 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ)
getMthLastParam=7 mForward=5 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ)
getMthLastParam=7 mForward=5 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ)
getMthLastParam=7 mForward=5 param=λλ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ)
getMthLastParam=7 mForward=5 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ)
getMthLastParam=7 mForward=5 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=7 mForward=5 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ)
getMthLastParam=7 mForward=5 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=7 mForward=5 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=5 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=5 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=4 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ)
getMthLastParam=8 mForward=4 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ)
getMthLastParam=8 mForward=4 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ)
getMthLastParam=8 mForward=4 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ)
getMthLastParam=8 mForward=4 param=λλ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ)
getMthLastParam=8 mForward=4 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ)
getMthLastParam=8 mForward=4 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=8 mForward=4 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=8 mForward=4 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ)
getMthLastParam=8 mForward=4 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=8 mForward=4 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=4 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=4 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get8thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}})
getMthLastParam=9 mForward=3 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ)
getMthLastParam=9 mForward=3 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ)
getMthLastParam=9 mForward=3 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ)
getMthLastParam=9 mForward=3 param=λλ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ)
getMthLastParam=9 mForward=3 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ)
getMthLastParam=9 mForward=3 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ)
getMthLastParam=9 mForward=3 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=9 mForward=3 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=9 mForward=3 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ)
getMthLastParam=9 mForward=3 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=9 mForward=3 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=3 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=3 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get9thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get9thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}})
getMthLastParam=10 mForward=2 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ)
getMthLastParam=10 mForward=2 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ)
getMthLastParam=10 mForward=2 param=λλ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ)
getMthLastParam=10 mForward=2 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ)
getMthLastParam=10 mForward=2 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ)
getMthLastParam=10 mForward=2 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=2 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get10thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get10thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}})
getMthLastParam=11 mForward=1 param=λ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ)
getMthLastParam=11 mForward=1 param=λλ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ)
getMthLastParam=11 mForward=1 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ)
getMthLastParam=11 mForward=1 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ)
getMthLastParam=11 mForward=1 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=1 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get11thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get11thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}})
getMthLastParam=12 mForward=0 param=λλ i=0 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ)
getMthLastParam=12 mForward=0 param=λ i=1 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ)
getMthLastParam=12 mForward=0 param=λ i=2 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ)
getMthLastParam=12 mForward=0 param=λ i=3 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=4 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=5 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=6 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=7 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=8 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=9 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=10 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=11 n=13
x=(c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=0 param=λ i=12 n=13
Before last call: (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry13_get12thLastParam_verifyCurriesmoreIs1
evaling. op=curry13. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
evaling. op=curry13.     func = (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry13     param = λ
curryDatastruct = (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} (pair (c13 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry13_get12thLastParam_returned, λλ
Starting testCurry14
curry14 = c14
curry14.curriesMore = 15
curry14.curriesAll = 21
curry14.curriesSoFar = 6
### testEqq pass: curry14.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry14.curriesMore is 1+numParams==15 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry14.curriesAll is 6+numParams==21 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 14 param(s).
### testEqq pass: test curry14.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c14 r)
getMthLastParam=0 mForward=13 param=λ i=0 n=14
x=(c14 r λ)
getMthLastParam=0 mForward=13 param=λ i=1 n=14
x=(c14 r λ λ)
getMthLastParam=0 mForward=13 param=λ i=2 n=14
x=(c14 r λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=3 n=14
x=(c14 r λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=4 n=14
x=(c14 r λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=5 n=14
x=(c14 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=6 n=14
x=(c14 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=7 n=14
x=(c14 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=8 n=14
x=(c14 r λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=9 n=14
x=(c14 r λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=10 n=14
x=(c14 r λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=11 n=14
x=(c14 r λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λ i=12 n=14
x=(c14 r λ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=13 param=λλ i=13 n=14
Before last call: (c14 r λ λ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry14_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = r
evaling. op=curry14.     func = (c14 r λ λ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λλ
curryDatastruct = (pair (c14 r λ λ λ λ λ λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c14 r λ λ λ λ λ λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry14_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c14 {,r {,r l}})
getMthLastParam=1 mForward=12 param=λ i=0 n=14
x=(c14 {,r {,r l}} λ)
getMthLastParam=1 mForward=12 param=λ i=1 n=14
x=(c14 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=12 param=λ i=2 n=14
x=(c14 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=3 n=14
x=(c14 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=4 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=5 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=6 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=7 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=8 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=9 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=10 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λ i=11 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=12 param=λλ i=12 n=14
x=(c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=12 param=λ i=13 n=14
Before last call: (c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry14_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,r l}}
evaling. op=curry14.     func = (c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λλ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c14 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry14_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c14 {,r {,l {,r l}}})
getMthLastParam=2 mForward=11 param=λ i=0 n=14
x=(c14 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=11 param=λ i=1 n=14
x=(c14 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=11 param=λ i=2 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=3 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=4 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=5 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=6 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=7 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=8 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=9 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λ i=10 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=11 param=λλ i=11 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=11 param=λ i=12 n=14
x=(c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=11 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry14_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,r l}}}
evaling. op=curry14.     func = (c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c14 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry14_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c14 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=10 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=10 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=10 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=10 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=10 param=λλ i=10 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=3 mForward=10 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=10 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=10 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry14_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c14 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry14_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c14 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=9 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=9 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=9 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=9 param=λλ i=9 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=4 mForward=9 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=4 mForward=9 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=9 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=9 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c14 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry14_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=8 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=8 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=8 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=5 mForward=8 param=λλ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ)
getMthLastParam=5 mForward=8 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=5 mForward=8 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=5 mForward=8 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=8 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry14_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=7 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=7 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=7 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ)
getMthLastParam=6 mForward=7 param=λλ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ)
getMthLastParam=6 mForward=7 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ)
getMthLastParam=6 mForward=7 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=6 mForward=7 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=7 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=6 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=6 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ)
getMthLastParam=7 mForward=6 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ)
getMthLastParam=7 mForward=6 param=λλ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ)
getMthLastParam=7 mForward=6 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=7 mForward=6 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ)
getMthLastParam=7 mForward=6 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=6 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=5 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ)
getMthLastParam=8 mForward=5 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ)
getMthLastParam=8 mForward=5 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ)
getMthLastParam=8 mForward=5 param=λλ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ)
getMthLastParam=8 mForward=5 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ)
getMthLastParam=8 mForward=5 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=8 mForward=5 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=5 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get8thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}})
getMthLastParam=9 mForward=4 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ)
getMthLastParam=9 mForward=4 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ)
getMthLastParam=9 mForward=4 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ)
getMthLastParam=9 mForward=4 param=λλ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ)
getMthLastParam=9 mForward=4 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ)
getMthLastParam=9 mForward=4 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=9 mForward=4 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=4 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get9thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get9thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}})
getMthLastParam=10 mForward=3 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ)
getMthLastParam=10 mForward=3 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ)
getMthLastParam=10 mForward=3 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ)
getMthLastParam=10 mForward=3 param=λλ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ)
getMthLastParam=10 mForward=3 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ)
getMthLastParam=10 mForward=3 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ)
getMthLastParam=10 mForward=3 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=3 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get10thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get10thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}})
getMthLastParam=11 mForward=2 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ)
getMthLastParam=11 mForward=2 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ)
getMthLastParam=11 mForward=2 param=λλ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ)
getMthLastParam=11 mForward=2 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ)
getMthLastParam=11 mForward=2 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ)
getMthLastParam=11 mForward=2 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=2 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get11thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get11thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}})
getMthLastParam=12 mForward=1 param=λ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ)
getMthLastParam=12 mForward=1 param=λλ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ)
getMthLastParam=12 mForward=1 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ)
getMthLastParam=12 mForward=1 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ)
getMthLastParam=12 mForward=1 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=1 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get12thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get12thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}})
getMthLastParam=13 mForward=0 param=λλ i=0 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ)
getMthLastParam=13 mForward=0 param=λ i=1 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ)
getMthLastParam=13 mForward=0 param=λ i=2 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ)
getMthLastParam=13 mForward=0 param=λ i=3 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=4 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=5 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=6 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=7 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=8 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=9 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=10 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=11 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=12 n=14
x=(c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=0 param=λ i=13 n=14
Before last call: (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry14_get13thLastParam_verifyCurriesmoreIs1
evaling. op=curry14. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}
evaling. op=curry14.     func = (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry14     param = λ
curryDatastruct = (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} (pair (c14 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry14_get13thLastParam_returned, λλ
Starting testCurry15
curry15 = c15
curry15.curriesMore = 16
curry15.curriesAll = 22
curry15.curriesSoFar = 6
### testEqq pass: curry15.l.l.l.l.l.l == u/cleanLeaf, λ
### testEqq pass: test curry15.curriesMore is 1+numParams==16 cuz its after comment==u/cleanLeaf/theDefaultComment then funcBody then those params.
### testEqq pass: test curry15.curriesAll is 6+numParams==22 cuz its 5 params to choose op, then comment (curryN is here, with defaultComment==u/cleanLeaf), then funcBody, then those 15 param(s).
### testEqq pass: test curry15.curriesSoFar is 6 cuz first 5 params of leaf choose which op, then comment==u/cleanLeaf/theDefaultComment.
funcBody = r
x=(c15 r)
getMthLastParam=0 mForward=14 param=λ i=0 n=15
x=(c15 r λ)
getMthLastParam=0 mForward=14 param=λ i=1 n=15
x=(c15 r λ λ)
getMthLastParam=0 mForward=14 param=λ i=2 n=15
x=(c15 r λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=3 n=15
x=(c15 r λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=4 n=15
x=(c15 r λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=5 n=15
x=(c15 r λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=6 n=15
x=(c15 r λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=7 n=15
x=(c15 r λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=8 n=15
x=(c15 r λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=9 n=15
x=(c15 r λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=10 n=15
x=(c15 r λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=11 n=15
x=(c15 r λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=12 n=15
x=(c15 r λ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λ i=13 n=15
x=(c15 r λ λ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=0 mForward=14 param=λλ i=14 n=15
Before last call: (c15 r λ λ λ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λλ
### testEqq pass: testCurry15_get0thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = r
evaling. op=curry15.     func = (c15 r λ λ λ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λλ
curryDatastruct = (pair (c15 r λ λ λ λ λ λ λ λ λ λ λ λ λ λ) λλ), about to call funcBody on it
About to call (r (pair (c15 r λ λ λ λ λ λ λ λ λ λ λ λ λ λ) λλ))
### testEqq pass: testCurry15_get0thLastParam_returned, λλ
funcBody = {,r {,r l}}
x=(c15 {,r {,r l}})
getMthLastParam=1 mForward=13 param=λ i=0 n=15
x=(c15 {,r {,r l}} λ)
getMthLastParam=1 mForward=13 param=λ i=1 n=15
x=(c15 {,r {,r l}} λ λ)
getMthLastParam=1 mForward=13 param=λ i=2 n=15
x=(c15 {,r {,r l}} λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=3 n=15
x=(c15 {,r {,r l}} λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=4 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=5 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=6 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=7 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=8 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=9 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=10 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=11 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λ i=12 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=1 mForward=13 param=λλ i=13 n=15
x=(c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=1 mForward=13 param=λ i=14 n=15
Before last call: (c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λ λλ), about to call that on λ
### testEqq pass: testCurry15_get1thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,r l}}
evaling. op=curry15.     func = (c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λ λλ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λ λλ) λ), about to call funcBody on it
About to call ({,r {,r l}} (pair (c15 {,r {,r l}} λ λ λ λ λ λ λ λ λ λ λ λ λ λλ) λ))
### testEqq pass: testCurry15_get1thLastParam_returned, λλ
funcBody = {,r {,l {,r l}}}
x=(c15 {,r {,l {,r l}}})
getMthLastParam=2 mForward=12 param=λ i=0 n=15
x=(c15 {,r {,l {,r l}}} λ)
getMthLastParam=2 mForward=12 param=λ i=1 n=15
x=(c15 {,r {,l {,r l}}} λ λ)
getMthLastParam=2 mForward=12 param=λ i=2 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=3 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=4 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=5 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=6 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=7 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=8 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=9 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=10 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λ i=11 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=2 mForward=12 param=λλ i=12 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=2 mForward=12 param=λ i=13 n=15
x=(c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=2 mForward=12 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ λλ λ), about to call that on λ
### testEqq pass: testCurry15_get2thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,r l}}}
evaling. op=curry15.     func = (c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ λλ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ λλ λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}}} (pair (c15 {,r {,l {,r l}}} λ λ λ λ λ λ λ λ λ λ λ λ λλ λ) λ))
### testEqq pass: testCurry15_get2thLastParam_returned, λλ
funcBody = {,r {,l {,l {,r l}}}}
x=(c15 {,r {,l {,l {,r l}}}})
getMthLastParam=3 mForward=11 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ)
getMthLastParam=3 mForward=11 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ)
getMthLastParam=3 mForward=11 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=3 mForward=11 param=λλ i=11 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=3 mForward=11 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=3 mForward=11 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=3 mForward=11 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ λ), about to call that on λ
### testEqq pass: testCurry15_get3thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,r l}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,r l}}}} (pair (c15 {,r {,l {,l {,r l}}}} λ λ λ λ λ λ λ λ λ λ λ λλ λ λ) λ))
### testEqq pass: testCurry15_get3thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,r l}}}}}
x=(c15 {,r {,l {,l {,l {,r l}}}}})
getMthLastParam=4 mForward=10 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ)
getMthLastParam=4 mForward=10 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ)
getMthLastParam=4 mForward=10 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=4 mForward=10 param=λλ i=10 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=4 mForward=10 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=4 mForward=10 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=4 mForward=10 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=4 mForward=10 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get4thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,r l}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,r l}}}}} (pair (c15 {,r {,l {,l {,l {,r l}}}}} λ λ λ λ λ λ λ λ λ λ λλ λ λ λ) λ))
### testEqq pass: testCurry15_get4thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}})
getMthLastParam=5 mForward=9 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ)
getMthLastParam=5 mForward=9 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ)
getMthLastParam=5 mForward=9 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ)
getMthLastParam=5 mForward=9 param=λλ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ)
getMthLastParam=5 mForward=9 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=5 mForward=9 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=5 mForward=9 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=5 mForward=9 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get5thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,r l}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,r l}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,r l}}}}}} λ λ λ λ λ λ λ λ λ λλ λ λ λ λ) λ))
### testEqq pass: testCurry15_get5thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}})
getMthLastParam=6 mForward=8 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ)
getMthLastParam=6 mForward=8 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ)
getMthLastParam=6 mForward=8 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ)
getMthLastParam=6 mForward=8 param=λλ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ)
getMthLastParam=6 mForward=8 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ)
getMthLastParam=6 mForward=8 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=6 mForward=8 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=6 mForward=8 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get6thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,r l}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,r l}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,r l}}}}}}} λ λ λ λ λ λ λ λ λλ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get6thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}})
getMthLastParam=7 mForward=7 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ)
getMthLastParam=7 mForward=7 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ)
getMthLastParam=7 mForward=7 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ)
getMthLastParam=7 mForward=7 param=λλ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ)
getMthLastParam=7 mForward=7 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ)
getMthLastParam=7 mForward=7 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ)
getMthLastParam=7 mForward=7 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=7 mForward=7 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get7thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,r l}}}}}}}} λ λ λ λ λ λ λ λλ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get7thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}})
getMthLastParam=8 mForward=6 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ)
getMthLastParam=8 mForward=6 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ)
getMthLastParam=8 mForward=6 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ)
getMthLastParam=8 mForward=6 param=λλ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ)
getMthLastParam=8 mForward=6 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ)
getMthLastParam=8 mForward=6 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ)
getMthLastParam=8 mForward=6 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=8 mForward=6 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get8thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}} λ λ λ λ λ λ λλ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get8thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}})
getMthLastParam=9 mForward=5 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ)
getMthLastParam=9 mForward=5 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ)
getMthLastParam=9 mForward=5 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ)
getMthLastParam=9 mForward=5 param=λλ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ)
getMthLastParam=9 mForward=5 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ)
getMthLastParam=9 mForward=5 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ)
getMthLastParam=9 mForward=5 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=9 mForward=5 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get9thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}} λ λ λ λ λ λλ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get9thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}})
getMthLastParam=10 mForward=4 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ)
getMthLastParam=10 mForward=4 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ)
getMthLastParam=10 mForward=4 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ)
getMthLastParam=10 mForward=4 param=λλ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ)
getMthLastParam=10 mForward=4 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ)
getMthLastParam=10 mForward=4 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ)
getMthLastParam=10 mForward=4 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=10 mForward=4 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get10thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}} λ λ λ λ λλ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get10thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}})
getMthLastParam=11 mForward=3 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ)
getMthLastParam=11 mForward=3 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ)
getMthLastParam=11 mForward=3 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ)
getMthLastParam=11 mForward=3 param=λλ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ)
getMthLastParam=11 mForward=3 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ)
getMthLastParam=11 mForward=3 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ)
getMthLastParam=11 mForward=3 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=11 mForward=3 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get11thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}} λ λ λ λλ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get11thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}})
getMthLastParam=12 mForward=2 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ)
getMthLastParam=12 mForward=2 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ)
getMthLastParam=12 mForward=2 param=λλ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ)
getMthLastParam=12 mForward=2 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ)
getMthLastParam=12 mForward=2 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ)
getMthLastParam=12 mForward=2 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=12 mForward=2 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get12thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}} λ λ λλ λ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get12thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}})
getMthLastParam=13 mForward=1 param=λ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ)
getMthLastParam=13 mForward=1 param=λλ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ)
getMthLastParam=13 mForward=1 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ)
getMthLastParam=13 mForward=1 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ)
getMthLastParam=13 mForward=1 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=13 mForward=1 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get13thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}} λ λλ λ λ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get13thLastParam_returned, λλ
funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}})
getMthLastParam=14 mForward=0 param=λλ i=0 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ)
getMthLastParam=14 mForward=0 param=λ i=1 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ)
getMthLastParam=14 mForward=0 param=λ i=2 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ)
getMthLastParam=14 mForward=0 param=λ i=3 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=4 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=5 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=6 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=7 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=8 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=9 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=10 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=11 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=12 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=13 n=15
x=(c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ λ)
getMthLastParam=14 mForward=0 param=λ i=14 n=15
Before last call: (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ λ), about to call that on λ
### testEqq pass: testCurry15_get14thLastParam_verifyCurriesmoreIs1
evaling. op=curry15. funcBody = {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}}
evaling. op=curry15.     func = (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ λ)
evaling. op=curry15     param = λ
curryDatastruct = (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ λ) λ), about to call funcBody on it
About to call ({,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} (pair (c15 {,r {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,l {,r l}}}}}}}}}}}}}}} λλ λ λ λ λ λ λ λ λ λ λ λ λ λ) λ))
### testEqq pass: testCurry15_get14thLastParam_returned, λλ
Starting testLazig
### testEqq pass: lazigIsleaf, (c3 {,r {,l {,r l}} {,r {,r l}}} (λλ λ λλ λλ λ λ))
### testEqq pass: lazigIsleafWiki, (c3 {,r {,l {,r l}} {,r {,r l}}} (λλ λ λλ λλ λ λ) w)
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} (λλ λ λλ λλ λ λ) w)
evaling. op=curry3     param = pair
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} (λλ λ λλ λλ λ λ) w) pair), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} (λλ λ λλ λλ λ λ) w) pair))
### testEqq pass: lazigIsleafWikiPair, f
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} pair (λλ λ λλ λλ λ λ))
evaling. op=curry3     param = w
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} pair (λλ λ λλ λλ λ λ)) w), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} pair (λλ λ λλ λλ λ λ)) w))
### testEqq pass: lazigPairIsleafWiki, (pair (λλ λ λλ λλ λ λ))
Starting testIfElse
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t i)
evaling. op=curry3     param = i
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t i) i), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t i) i))
### testEqq pass: e(ifElse,t,i,i), λ
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t ,w)
evaling. op=curry3     param = ,pair
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t ,w) ,pair), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t ,w) ,pair))
### testEqq pass: e(ifElse,t,t(wiki),t(pair)), w
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f ,w)
evaling. op=curry3     param = ,pair
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f ,w) ,pair), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f ,w) ,pair))
### testEqq pass: e(ifElse,F,t(N),t(P)), pair
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t i)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t i) λ), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t i) λ))
### testEqq pass: e(ifElse,T,I,.) -> (I .) -> ., λ
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f i)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f i) λ), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f i) λ))
### testEqq pass: e(ifElse,F,I,.) -> (. .), λλ
### testEqq pass: (,,,,u wiki) -> ,,,u, ,,,λ
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ) λ))
### testEqq pass: ifElse T thenConst L, l
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} ,l λ)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,r λ) λ))
### testEqq pass: ifElse F thenConst R, r
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l t))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r t)
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l t)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r t)), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l t)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r t)))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} ,l t)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,l t) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,l t) λ))
### testEqq pass: ifElse I thenConst L cuz param of the IF is T so I gets T, l
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} ,l f))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r f)
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} ,l f)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r f)), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} ,l f)) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r f)))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r f)
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,r f) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,r f) λ))
### testEqq pass: ifElse I thenConst R cuz param of the IF is F so I gets F, r
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l (pair t f)))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} ,l (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} ,l (pair t f))
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,l (pair t f)) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} ,l (pair t f)) λ))
### testEqq pass: ifElse car thenConst L cuz param of the IF is (P T F) so car gets T, l
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} t (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)) λ))
### testEqq pass: ifElse car then I, car gets T which chooses then(I), and the I called on (P T F) returns (P T F), (pair t f)
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f)) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f)) λ))
### testEqq pass: ifElse cdr then I, cdr gets F which chooses thenT(P,I,I), and the thenT(P,I,I) called on e(P,T,F) returns (P (P T F) (P T F)), (pair (pair t f) (pair t f))


*/
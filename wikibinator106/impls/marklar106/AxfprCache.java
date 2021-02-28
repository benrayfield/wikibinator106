package wikibinator106.impls.marklar106;
import java.util.HashMap;
import java.util.Map;


/** func param return caching, like in https://en.wikipedia.org/wiki/Continuation but more stateless than that,
though you could derive continuations from this by including a treemap param in functions
that call eachother and pass that same or forkEdited treemap to deeper calls.
This is a pure math system more like lambdas than continuations, and also pattern-calculus and combinator,
so it doesnt exactly fit into any existing category of functions.
To represent it as lambdas, you'd need a funcall function that takes 2 params,
instead of calling those 2 on eachother directly, to make the reflect ops (l r isleaf isclean) work,
or you could build that into the 2 functions as long as they're never called on anything not
created by combos of such a universal function which would break the reflect ops.
Thats built into λ.e(λ) and λ.e(long,λ) and happens automatically when anything is called on anything.
*/
public class AxfprCache{
	
	public static int cacheEntries(){
		return callToRet.size();
	}
	
	public static void garbcol(int numCacheEntries){
		int garbcolDownToNumOfCacheEntries = Math.max(0,cacheEntries()-numCacheEntries);
		while(cacheEntries() > garbcolDownToNumOfCacheEntries){
			//loop over the map of Call to Ret in order of Ret.garbcolOrder,
			//and garbcol enough of them that maybe around 1/3 of the available memory
			throw new RuntimeException("TODO");
		}
		//System.gc();
	}
	
	public static fn getOrNull(fn func, fn param){
		Call call = new Call(func,param);
		Ret r = callToRet.get(call);
		if(r == null) return null;
		touch(r);
		return r.ret;
	}
	
	//FIXME? is this a cache of 3-way-forest where 1 of those childs is boolean isClean,
	//or is this a cache of 2-way forest thats whatever happens when call one on the other?
	
	/* Fixed this by x.isClean()==(x.l().isClean()&x.r().isClean(), and (L x (R x)) equals x, forall x[
		Do I even want to have fns where both childs are clean and parent is dirty?
		If not, then all fns can be derived just by (func param)->ret starting from cleanLeaf and dirtyLeaf,
		and other than there being 2 leafs, all data is in the forest shape, and everything else is cache,
		so if a node is dirty then it has at least 1 dirty child, in that possible design.
		FIXME
		/** If func and param are both clean, then you have a choice
		for the parent node (of that funcall, not the return value) to be clean vs dirty.
		If func is clean and param is dirty, then param is truncated to clean first,
		eve WAIT...
		
		FIXME I'm designing this wrong. There needs to be 2 separate kinds of AxfprCache,
		though can share code... (func param)->ret, and the func and param child nodes
		of a parent node and that parent being clean vs dirty.
		
		There is a way to merge those, but I dont know if I want to pay the extra complexity...
		If there was an Op.dirty op, where (Op.dirty x) is dirty x,
		and (L (dirty x)) is the same as (L x) other than x would otherwise be unable to
		contain dirty childs...
		So maybe Op.dirty should take 3 params which are the 2 childs of x then the param of x?
		I'd need it to be equally fast as the other way (storing a bit in header for isclean vs dirty)
		and not to actually have 3 such params, just use it as a math abstraction,
		and I'd need to find a way for it not to interfere with "(L x (R x)) equals x forall x"
		where Op.dirty could be an x, so (dirty x.l x.r y) would be the same as (dirty_form_of_x y).
		But (dirty x.l x.r y) is (((dirty x.l) x.r) y) so that seems to interfere with
		(L ((dirty x.l) x.r)) being both x.l and (dirty x.l),
		but does not seem to interfere with (R ((dirty x.l) x.r)) being x.r cuz thats true either way.
	]
	*/
	public static void put(fn func, fn param, fn ret){
		callToRet.put(new Call(func,param), new Ret(ret));
	}
	
	/*
	//TODO garbcol in order of Ret.garbcolOrder but only of those not reachable,
	//so might need to use WeakReference or Runtime get amount of memory or something.
	Maybe use a SortedMap and touch(Ret) removes from the map, changes Ret.garbcolOrder, then adds to map,
	where map is sorted by Ret.garbcolOrder.
	That would solve the order problem, but not the problem of
	how to know which are reachable at JVM level.
	Might need to create a Pointer class thats has a long localId and a WeakReference (or is a WeakReference?)
	to the λ which also knows its long localId, and use localId instead of == to compare and hashcode them.
	Or maybe this cache should prevent garbcol (not use any weakreference) and just watch the amount of memory
	and when it gets low garbcol from the low end of Ret.garbcolOrder.
	Also, must consider NIO memory (such as lwjgl opencl uses),
	but that can be isolated in MultiPool (or what was the "pool" class name in lazycl?)
	and in stack during OpenclUtil.callOpencl and .callOpenclDependnet and maybe in Mem.java instances.
	*/
	
	private static Map<Call,Ret> callToRet = new HashMap();
	//private static SortedMap<Call,Ret> callToRet = new TreeMap();
	//FIXME SortedMap is sorted by key, but want it sorted by Ret.garbcolOrder
	
	private static final class Call{
		
		public final fn func, param;
		
		public final int hash;
		
		public Call(fn func, fn param){
			this.func = func;
			this.param = param;
			hash = System.identityHashCode(func)-System.identityHashCode(param);
		}
		
		public int hashCode(){ return hash; }
		
		public boolean equals(Object o){
			if(!(o instanceof Call)) return false;
			Call c = (Call)o;
			return c.func == func && c.param == param;
		}
		
		/*TODO??? hashcode and equals, and make sure the hashcode
		uses strongrand salts generated once per JVM run so if something causes hash collisions (32 bit) in one computer
		it will very probably not in another computer, and same for every time you restart the wikibinator105 VM
		copy some code from occamsfuncer's CacheFuncParamRet.java.
		Actually, that only matters for id256s, as this cache works even before those are generated (if they ever are)
		so uses System.identityHashCode...
		*/
		
	}
	
	private static long max;
	
	private static void touch(Ret r){
		r.garbcolOrder = ++max;
	}
	
	private static final class Ret{
		
		/** garbcol (garbage collect) in order of least recently used, of those which have no incoming pointers.
		this might be utc nanoseconds, or just a counter. When use this in a Map.Entry<Call,Ret>,
		update this long to be bigger than all other such longs. 
		*/
		public long garbcolOrder;
		
		public final fn ret;
		
		public Ret(fn ret){
			this.ret = ret;
			touch(this);
		}
	}

}
package wikibinator106.impls.marklar106;

public class SimpleFn implements fn{
	
	public final long header;
	
	/** bize64 lowest 64 bits, cached from bizi (bize32 lowest 32 bits) AND containsBit1,
	which are both in the default kind of header, so need to recurse at most 32 times
	to get to the bizi/32, at each node above that checking containsBit1 to know to recurse left vs right.
	*/
	protected long bizj;
	
	public SimpleFn(fn func, fn param){
		TODO compute bizj from func.bizj() and param.bizj()???
	}

}

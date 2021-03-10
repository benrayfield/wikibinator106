package wikibinator106.spec;

import java.math.BigInteger;

public class Const{
	private Const(){}

	/** If true, then (axa x) evals to halted (axa x) if (x u)->u,
	and evals to (axb x) if (x u) -> anything except u, where u is cleanLeaf,
	but either way if (x u) does not halt then (axa x) does not halt,
	and similar for (axb x).
	If false, then instead of returning the opposite of axa/axb when disproof (proves the opposite),
	infinite loops (halts instantly, giving up to not waste gas on known infinite loop, but not all infinite loops can be detected,
	just those marked that way such as putting an Evaler instance to do that in (s (t (s i i)) (t (s i i))))
	which is a function that infinite loops for all possible params, then in a deepest evaler
	such as wikibinator106.impls.marklar106.InterpretedModeUsingJavaStack, could call (s (t (s i i)) (t (s i i)))
	stored in some public static final λ java var, call that on u, when func is axa or axb and param is x
	and its disproven, instead of returning cp(opposite_axa_or_axb,x)
	which works if call that but no need to call it just return the cp(...) call pair for efficiency. 
	*/
	public static final boolean isDisproofOfOneKindOfAxReturnsTheOtherKindOfAx = true;
	
	public static final int log2OfMaxCbtSize = Op.zero.curriesAll-6;
	
	/** cbtSize is 2^cbtHeight so 2^0 is 1 */
	public static final int minCbtSize = 1;
	
	/** max bitstring size (in bits). exact value fits in float, but its BigInteger in case of opensource forking. */
	public static final BigInteger maxCbtSize = BigInteger.TWO.pow(log2OfMaxCbtSize);
	
	/** bize (bitstring size) is 0 when cbt is all 0s or is 100000...000000 or is not a cbt.
	You can tell the difference between those using op6Bits and curriesSoFar or curriesMore.
	*/
	public static final int minBize = 0;
	
	/** bize is bitstring size (in bits) */
	public static final BigInteger maxBize = maxCbtSize.subtract(BigInteger.ONE);
	
	/** cleanLeaf and dirtyLeaf */
	public static final int numberOfLeafs = 2;
	
	/** 3 ids [parent, parent.l(), and parent.r()], which is enough to know the idMaker algorithm (such as marklar106b)
	unless someone intentionally designs an idMaker to return the same id as some other idMaker for some inputs.
	Normally this is 96 bytes (32 bytes each) but could be 192 bytes (64 bytes each) for other id types. 
	An id by itself does not imply what kind of id it is (unless prefixed in a multicodec/multihash, TODO)
	so you might, for example, use application/x-wikibinator106-marklar106b
	or maybe it should be application/x-wikibinator106-p-marklar106b
	or application/x-wikibinator106-id-marklar106b ???
	*/
	public static final String contentTypePlr = "application/x-wikibinator106-plr";
}

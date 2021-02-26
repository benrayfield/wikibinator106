package wikibinator106.spec;
import java.math.BigInteger;
import java.util.function.UnaryOperator;

/** a universal function of 7..127 params,
which is a combinator, universal lambda, and pattern calculus function,
and is designed to be GPU optimized and for gaming-low-lag sync in p2p network, graphics, sound, etc,
and has bitstrings built in up to 2^120 bits which in abstract math are padded with 10000...000 until next powOf2
and can be sparse or dense, but in practice will store only the parts you actually need. 
*/
public interface λ<Subclass extends λ<Subclass>> extends UnaryOperator<Subclass>{
	
	/*OLD... op.curry (of up to 32 opcodes) takes a cbt1 to cbt<2^126> (or something around that limit)
	as one of its params, and counts down params similar to wikibinator105.
	Can the number of curries be the first param of cleanLeaf and first param of dirtyLeaf?
	??? Could if there were 4 leaf types instead of those 2: cleanLeaf, dirtyLeaf, zero, and one,
	and zero and one would both be clean and when called on anything always returns a cbt,
	either a cbt twice its size or maybe returns itself if is at max curries (127),
	but I'm trying to keep it just to those 2 leaf types. ???
	Do I want zero and one to be just another op among the up to 32 ops?
	Should those 32 ops be chosen by the first 5 params (after cleanLeaf/dirtyLeaf)?
	Should those first 5 params have to be either zero or one?
	*/
	
	
	public EvalerChain compiled();
	
	/** new EvalerChain's prev() normally points at what compiled() returns before this was called.
	Many fns can share the same EvalerChain.
	*/
	public void setCompiled(EvalerChain compiled);
	
	public default Subclass e(Subclass param){
		return e(Long.MAX_VALUE, param).fn; //FIXME what if .fn is null cuz didnt have enough gas? throw?
	}
	
	public default $<Subclass> e(long maxSpend, Subclass param){
		return compiled().Eval(maxSpend, this, param);
	}
	
	public boolean isLeaf();
	
	public boolean isClean();
	
	public default boolean isCleanLeaf(){
		return isLeaf() && isClean();
	}
	
	public default boolean isDirtyLeaf(){
		return isLeaf() && !isClean();
	}
	
	public Subclass asClean();
	
	public Subclass asDirty();
	
	/** true iff deep contains any (Op.ax x y) for any x and y, but doesnt count (Op.ax x) or Op.ax by itself.
	Where this is false, then sync and verifying is much easier.
	TODO implement this in subclasses using a bit in header, for constant cost instead of this exponential cost.
	*/
	public default boolean containsAxOf2Params(){
		return !isLeaf() && (isAxOf2Params() || l().containsAxOf2Params() || r().containsAxOf2Params());
	}
	
	public boolean isAxOf2Params();
	
	/** sparse matrix optimization, and helps with computing bize when content is all 0s, to know if first bit is 1 vs 0.
	True if contains any Op.one.
	Does not include Op.one where comment is not cleanLeaf. Only includes the clean normed form, cuz cbt optimizations work on it.
	TODO implement this in subclasses using a bit in header, for constant cost instead of this exponential cost.
	*/
	public default boolean containsCleanNormedBit1(){
		return !isLeaf() && (isCleanNormedBit1() || l().containsCleanNormedBit1() || r().containsCleanNormedBit1());
	}
	
	/** see containsBit1() */
	public boolean isCleanNormedBit1();
	
	/** gets the sixth param of cleanLeaf/dirtyLeaf, else returns cleanLeaf cuz it has less than 6 params so far.
	The first 5 params are either cleanLeaf vs anything_except_cleanLeaf to choose from 32 ops, or 64 if count clean vs dirty.
	*/
	public default λ comment(){
		throw new RuntimeException("TODO");
	}
	
	/** forkEdit this so comment() returns the given param.
	If doesnt have 6 params yet, appends cleanLeaf for the remaining params.
	The first 5 params are either cleanLeaf vs anything_except_cleanLeaf to choose from 32 ops, or 64 if count clean vs dirty.
	*/
	public default λ comment(λ newComment){
		throw new RuntimeException("TODO");
	}
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public default byte curriesAll(){
		return (byte)(curriesSoFar()+curriesMore());
	}
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public default byte curriesSoFar(){
		return (byte)(curriesAll()-curriesMore());
	}
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public default byte curriesMore(){
		return (byte)(curriesAll()-curriesSoFar());
	}
	
	public boolean isCleanCbt();
	
	//Even if we only know the low 32 bits of bize in some id types, can still effectively use powOf2 size cbts bigger.

	/** Bitstring sIZE. range 0..(2^120-1). */
	public default Number bize(){
		throw new RuntimeException("TODO return new BigInteger(15 bytes of bizk() bizj());");
	}
	
	/** Bitstring sIZE. low 32 bits of bize which ranges 0..(2^120-1).
	Since curriesAll ranges 0..127, bize cant be bigger than fits in int128.
	The default id type only knows the low 40 bits of bize and ceil(log2(bize)) up to 120.
	*/
	public default int bizi(){ return (int)bizj(); }
	
	/** Low 40 bits of bize, which ranges 0..(2^120-1). Same as bizj()&0xffffffffffL but may be more efficient,
	considering that marklar106b ids store the low 40 bits of bize and store curriesSoFar
	so know ceil(log2(bize)) if its not higher than it needs to be
	which is a valid cbt but not the normed form of a bitstring.
	Like the bitstring 01110, in abstract math is 01110100
	and its nonnormed forms include 0111010000000000 and 01110100000000000000000000000000.
	If a cbt is all 0s, its bize is 0, even though the bize of a 1 then 0s until the end is also 0,
	and to know the difference between those, check if its a call of Op.zero or Op.one which tells the first bit.
	*/
	public default long biz40(){ return bizj()&0xffffffffffL; }
	
	/** Bitstring sIZE. low 64 bits of bize which ranges 0..(2^120-1). See comment of bizi. */
	public default long bizj(){
		return bize().longValue();
	}
	
	/** Bitstring sIZE. high 56 bits of bize (in my low 56 bits) which ranges 0..(2^120-1). See comment of bizi. */
	public default long bizk(){
		Number b = bize();
		if(b instanceof BigInteger){
			BigInteger B = (BigInteger)b;
			return B.shiftRight(64).longValue(); //could &0x00ffffffffffffff but if bize() is correct that has no effect.
		}else{
			throw new RuntimeException("TODO how to get the high long from "+b);
		}
	}
	
	//Should bize always be long and max cbt is cbt63? js doesnt have longs. max cbt53 cuz double can count to 2^53?
	//What about long gas? double gas with max value of 2^53-1?
	
	
	
	//////////
	
	public default Subclass l(){ return g(2); }
	
	public default Subclass r(){ return g(3); }
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	same as l().r() but may be more efficient, such as in the optimization used in Pair.java
	to store x and y but lazyEval (pair x) in (pair x y) and not trigger laziEval of that just to get x or y.
	*/
	public default Subclass lr(){ return g(5); }	
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default Subclass g(int binheapIndex){ return g((long)binheapIndex); }
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public Subclass g(long binheapIndex);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*8..n*8+7 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of byte[] etc.
	*/
	public byte b(int n);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*16..n*16+15 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of short[] or char[] or String etc.
	*/
	public short s(int n);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105. */
	public default char c(int n){
		return (char)s(n);
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*32..n*32+31 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of int[] or float[] etc.
	*/
	public int i(int n);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105. */
	public default float f(int n){
		return Float.intBitsToFloat(i(n));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*64..n*64+63 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of long[] or double[] etc.
	*/
	public long j(int n);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105. */
	public default double d(int n){
		return Double.longBitsToDouble(j(n));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default Subclass G(int binheapIndex){ return G((long)binheapIndex); }
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public Subclass G(long binheapIndex);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	callpair of this and param, without checking if thats a valid thing to do,
	since (todo choose a design?) only halted nodes are allowed.
	*/
	public Subclass p(Subclass r);

}

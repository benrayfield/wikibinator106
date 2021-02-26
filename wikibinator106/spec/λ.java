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
	
	
	public Subclass e(long and withoutlong forms todo)
	
	public boolean isLeaf();
	
	public boolean isClean();
	
	public default boolean isCleanLeaf(){
		return isLeaf() && isClean();
	}
	
	public default boolean isDirtyLeaf(){
		return isLeaf() && !isClean();
	}
	
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
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 (UPDATE: 0..63) */
	public byte curriesAll();
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 (UPDATE: 0..63) */
	public byte curriesSoFar();
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 (UPDATE: 0..63) */
	public byte curriesMore();
	
	//Even if we only know the low 32 bits of bize in some id types, can still effectively use powOf2 size cbts bigger.

	/** Bitstring sIZE. range 0..(2^120-1). */
	public default Number bize(){
		return new BigInteger(bytes of bizk() bizj());
	}
	
	/** Bitstring sIZE. low 32 bits of bize which ranges 0..(2^120-1).
	Since curriesAll ranges 0..127, bize cant be bigger than fits in int128.
	The default id type only knows the low 40 bits of bize and ceil(log2(bize)) up to 120.
	*/
	public default int bizi(){ return (int)bizj(); }
	
	/** Low 40 bits of bize, which ranges 0..(2^120-1). Same as bizj()&0xffffffffffL but may be more efficient,
	considering that marklar106b ids store the low 40 bits of bize and store curriesSoFar so know ceil(log2(bize)).
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
		return bize().
	}
	
	//Should bize always be long and max cbt is cbt63? js doesnt have longs. max cbt53 cuz double can count to 2^53?
	//What about long gas? double gas with max value of 2^53-1?

}

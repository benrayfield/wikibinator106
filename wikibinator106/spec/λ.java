package wikibinator106.spec;
import java.util.function.UnaryOperator;

public interface λ<Subclass extends λ<Subclass>> extends UnaryOperator<Subclass>{
	
	op.curry (of up to 32 opcodes) takes a cbt1 to cbt<2^126> (or something around that limit)
	as one of its params, and counts down params similar to wikibinator105.
	Can the number of curries be the first param of cleanLeaf and first param of dirtyLeaf?
	??? Could if there were 4 leaf types instead of those 2: cleanLeaf, dirtyLeaf, zero, and one,
	and zero and one would both be clean and when called on anything always returns a cbt,
	either a cbt twice its size or maybe returns itself if is at max curries (127),
	but I'm trying to keep it just to those 2 leaf types. ???
	Do I want zero and one to be just another op among the up to 32 ops?
	Should those 32 ops be chosen by the first 5 params (after cleanLeaf/dirtyLeaf)?
	Should those first 5 params have to be either zero or one?
	
	
	public Subclass e(long and withoutlong forms todo)
	
	public boolean isLeaf();
	
	public boolean isClean();
	
	public default boolean isCleanLeaf(){
		return isLeaf() && isClean();
	}
	
	public default boolean isDirtyLeaf(){
		return isLeaf() && !isClean();
	}
	
	/** get whats at second curry, or if dont have that many curries, its cleanLeaf */
	public default λ comment(){
		throw new RuntimeException("TODO");
	}
	
	/** forkEdit this to have the given comment aka whatevers at second curry, and if dont have that many curries adds it */
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

	/** Bitstring sIZE.  range 0 ... 2^126-1 or somewhere around that range, certainly fits in 128 bits */
	public Number bize();
	
	/** Bitstring sIZE. low 32 bits of bize. Since curriesAll ranges 0..127, bize cant be bigger than fits in int128.
	The default id type only knows the low 32 bits of bize and ceil(log2(bize)) as high as the universal function can do.
	*/
	public int bizi();
	
	/** Bitstring sIZE. low 64 bits of bize. Since curriesAll ranges 0..127, bize cant be bigger than fits in int128. */
	public long bize();
	
	/** Bitstring sIZE. high 64 bits of bize. Since curriesAll ranges 0..127, bize cant be bigger than fits in int128. */
	public long bizk();
	
	Should bize always be long and max cbt is cbt63? js doesnt have longs. max cbt53 cuz double can count to 2^53?
	What about long gas? double gas with max value of 2^53-1?

}

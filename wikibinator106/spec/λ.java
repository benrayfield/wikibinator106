package wikibinator106.spec;
import java.util.function.UnaryOperator;

public interface λ<Subclass extends λ<Subclass>> extends UnaryOperator<Subclass>{
	
	public Subclass e(long and withoutlong forms todo)
	
	public boolean isLeaf();
	
	public boolean isClean();
	
	public default boolean isCleanLeaf(){
		return isLeaf() && !isClean();
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
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public byte curriesAll();
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public byte curriesSoFar();
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public byte curriesMore();
	
	/** Bitstring sIZE.  range 0 ... 2^126-1 or somewhere around that range, certainly fits in 128 bits */
	public Number bize();
	
	/** Bitstring sIZE. low 32 bits of bize. Since curriesAll ranges 0..127, bize cant be bigger than fits in int128.
	The default id type only knows the low 32 bits of bize and ceil(log2(bize)) as high as the universal function can do.
	*/
	public int bizi();
	
	/** Bitstring sIZE. low 64 bits of bize. Since curriesAll ranges 0..127, bize cant be bigger than fits in int128. */
	public long bizj();
	
	/** Bitstring sIZE. high 64 bits of bize. Since curriesAll ranges 0..127, bize cant be bigger than fits in int128. */
	public long bizk();

}

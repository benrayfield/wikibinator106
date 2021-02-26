package wikibinator106.impls.marklar106;
import wikibinator106.spec.λ;

public interface fn extends λ<fn>{
	
	public long marklar106bHeader();
	
	/** Low 8 bits of bIZe */
	public default byte liz(){
		return Marklar106bId.liz(marklar106bHeader());
	}
	
	public default boolean isLeaf(){
		return Marklar106bId.isLeaf(marklar106bHeader());
	}

	public default boolean isClean(){
		return Marklar106bId.isClean(marklar106bHeader());
	}

}

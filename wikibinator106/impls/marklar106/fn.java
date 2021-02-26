package wikibinator106.impls.marklar106;
import wikibinator106.spec.λ;

public interface fn extends λ<fn>{
	
	public long marklar106bHeader();
	
	/** Low 8 bits of bIZe */
	public default byte liz(){
		long h = marklar106bHeader();
		if(Marklar106bId.isLiteral256Bits(h)){
			throw new RuntimeException("get it from content bits. find the last 1 bit (if exists, else return 0).");
		}else{
			return Marklar106bId.liz_ignoreIfLiteralCbt256(h);
		}
	}
	
	public default boolean isLeaf(){
		return Marklar106bId.isLeaf(marklar106bHeader());
	}

	public default boolean isClean(){
		return Marklar106bId.isClean(marklar106bHeader());
	}

}

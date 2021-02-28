package wikibinator106.impls.marklar106;
import wikibinator106.spec.λ;

public interface fn extends λ<fn>{
	
	public long marklar106bHeader();
	
	public default int bizi(){
		return (int)biz40();
	}
	
	public default long biz40(){
		long h = marklar106bHeader();
		if(Marklar106bId.isLiteral256Bits(h)){
			return Marklar106bId.bizeOfContent(j(0), j(1), j(2), j(3));
			//throw new RuntimeException("get it from content bits. find the last 1 bit (if exists, else return 0).");
		}else{
			return Marklar106bId.bizb_ignoreIfLiteralCbt256(h);
		}
	}
	
	public default boolean isLeaf(){
		return Marklar106bId.isLeaf(marklar106bHeader());
	}

	public default boolean isClean(){
		return Marklar106bId.isClean(marklar106bHeader());
	}
	
	public default boolean isCleanCbt(){
		return Marklar106bId.isCleanCbt(marklar106bHeader());
	}
	
	public default byte op6Bits(){
		return Marklar106bId.op6Bits(marklar106bHeader());
	}
	
	public default byte curriesAll(){
		return Marklar106bId.curriesAll(marklar106bHeader());
	}
	
	public default byte curriesSoFar(){
		return Marklar106bId.curriesSoFar(marklar106bHeader());
	}
	
	public default byte curriesMore(){
		return Marklar106bId.curriesMore(marklar106bHeader());
	}

}

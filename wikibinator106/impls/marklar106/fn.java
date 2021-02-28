package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.cp;

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
	
	
	/** Only to be called by a wikibinator106 VM.
	only forkEdits this node, and (TODO?) throws if causes x.isClean()==(x.l().isClean()&x.r().isClean()) cuz
	it would break AxfprCache for that not to be true, and cuz...
	x.isClean()==(x.l().isClean()&x.r().isClean()), and (L x (R x)) equals x, forall x.
	(L cleanLeaf) is cleanIdentityFunc. (L dirtyLeaf) is dirtyIdentityFunc.
	(R cleanLeaf) is cleanLeaf. (R dirtyLeaf) is dirtyLeaf.
	*/
	public fn vm_asClean();
	
	/** Only to be called by a wikibinator106 VM.
	only forkEdits this node, and (TODO?) throws if causes x.isClean()==(x.l().isClean()&x.r().isClean()) cuz
	it would break AxfprCache for that not to be true, and cuz...
	x.isClean()==(x.l().isClean()&x.r().isClean()), and (L x (R x)) equals x, forall x.
	(L cleanLeaf) is cleanIdentityFunc. (L dirtyLeaf) is dirtyIdentityFunc.
	(R cleanLeaf) is cleanLeaf. (R dirtyLeaf) is dirtyLeaf.
	*/
	public fn vm_asDirty();
	
	/** Only to be called by a wikibinator106 VM. The default implementation uses AxfprCache
	so is average cost of constant and worst cost of number of objects reachable below by l and r.
	*/
	public default fn vm_asDirty_recursiveAll(){
		return isLeaf() ? vm_asDirty() : cp(l().vm_asDirty_recursiveAll(),r().vm_asDirty_recursiveAll());
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

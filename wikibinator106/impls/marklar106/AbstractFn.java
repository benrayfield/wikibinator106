package wikibinator106.impls.marklar106;

import wikibinator106.spec.EvalerChain;

public abstract class AbstractFn implements fn{
	
	public final long marklar106bHeader;
	
	protected EvalerChain compiled = InterpretedMode.chain;
	
	public AbstractFn(long marklar106bHeader){
		this.marklar106bHeader = marklar106bHeader;
	}
	
	public long marklar106bHeader(){
		return marklar106bHeader;
	}
	
	public EvalerChain compiled(){
		return compiled;
	}
	
	public void setCompiled(EvalerChain compiled){
		this.compiled = compiled;
	}

}

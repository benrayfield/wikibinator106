package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;
import wikibinator106.spec.EvalerChain;

public abstract class AbstractFn implements fn{
	
	public final long marklar106bHeader;
	
	protected EvalerChain compiled = InterpretedModeUsingJavaStack.chain;
	
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
	
	public boolean isAxconstraint(){
		return Marklar106bId.isAxconstraint(marklar106bHeader);
	}

	public boolean isCleanNormedBit1(){
		return Marklar106bId.isCleanNormedBit1(marklar106bHeader);
	}
	
	public fn p(fn param){
		return cp(this,param);
	}
	
	public int bizi(){
		return (int)biz40();
	}
	
	public fn w(Object wrapMe){
		return ImportStatic.w(wrapMe);
	}
	
	public fn ww(Object wrapMe){
		return ImportStatic.ww(wrapMe);
	}
	
	

}

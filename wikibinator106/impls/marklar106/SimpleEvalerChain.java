/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import wikibinator106.spec.*;

public class SimpleEvalerChain implements EvalerChain<fn>{
	
	protected boolean on = true;
	
	public final EvalerChain<fn> prevOrNull;
	
	public final Evaler<fn> wrapMe;
	
	public SimpleEvalerChain(Evaler<fn> wrapMe){
		this(wrapMe, null);
	}
	
	public SimpleEvalerChain(Evaler<fn> wrapMe, EvalerChain<fn> prevOrNull){
		this.wrapMe = wrapMe;
		this.prevOrNull = prevOrNull;
	}

	/** todo optimize by extending SimpleCompiled directly and overriding this apply func, consistent with setOn and prev etc? */
	public $<fn> Eval(long maxSpend, fn func, fn param){
		return (on?wrapMe:prevOrNull).Eval(maxSpend, func, param);
	}
	
	public $<fn> Fork(long maxSpend, fn funcA, fn funcB, fn param){
		return (on?wrapMe:prevOrNull).Fork(maxSpend, funcA, funcB, param);
	}
	
	public $<fn> Wiki(long maxSpend, fn param){
		return (on?wrapMe:prevOrNull).Wiki(maxSpend, param);
	}

	public void setOn(boolean on){
		if(!on && prevOrNull == null) throw new RuntimeException(
			"Cant turn off the deepest Compiled whose prev()==null cuz that would change its behaviors to have no implementation of lambda math at all. this="+this);
		this.on = on;
	}

	public boolean on(){
		return on;
	}

	public EvalerChain prev(){
		return prevOrNull;
	}

	public fn u(boolean isClean){
		return (on?wrapMe:prevOrNull).u(isClean);
	}

	public fn op(boolean isClean, Op o){
		return (on?wrapMe:prevOrNull).op(isClean,o);
	}

	public fn w(Object wrapThis){
		return (on?wrapMe:prevOrNull).w(wrapThis);
	}

	public fn ww(Object wrapThis){
		return (on?wrapMe:prevOrNull).ww(wrapThis);
	}

}

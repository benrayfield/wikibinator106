package wikibinator106.plugins.codemindmap.ui.old;
import java.util.Map;

import wikibinator106.spec.λ;

/** Bidirectional Map.Entry used in 1-to-1 mapping between String name and λ func.
λ.equals(λ) and λ.hashCode() will work soon but not yet in wikibinator106 as of 2021-3-15
cuz need optimizations before (λ)ImportStatic.equals can practically be used in Object.equals
and cuz it hasnt been hooked together yet at all.
*/
public interface Node extends Map.Entry<String,λ>{
	
	public String name();
	
	public λ func();
	
	public default String getKey(){
		return name();
	}
	
	public default λ getValue(){
		return func();
	}
	
	//FIXME should Node have prilist of Nodes, or put that somewhere else?
			
	//FIXME of course UI state doesnt go in Node, but where does scrolling position of a prilist go?

}
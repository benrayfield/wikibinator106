package wikibinator106.plugins.codemindmap;

import wikibinator106.plugins.codemindmap.ui.old.Node;
import wikibinator106.spec.λ;

/** Mutable NameSpace. a 1-to-1 mapping between λ (lambda) and String names,
not necessarily including all the lambdas reachable from those in the map
since their id (such as marklar106b id), written in hex or base58, can be used as their name.
*/
public interface NS{
	
	public λ func(String name);
	
	public String name(λ func);
	
	public void put(Node node);
	
	public void put(String name, λ func);
	
	/** remove a λ/String pair */
	public void rem(Node node);
	
	/** remove a λ/String pair */
	public void rem(λ func);
	
	/** remove a λ/String pair */
	public void rem(String name);
	
	/** number of func/name pairs in the namespace *
	public long size();
	*/

}

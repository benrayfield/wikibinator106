package wikibinator106.plugins.codemindmap;

import java.util.List;

import wikibinator106.spec.λ;

/** mutable node in a web of nodes, with edges between them, as x being in y's list means y is in x's list but can be different orders,
and nodes may have other fields.
*/
public class Webnode{
	
	/** If isClean, can only recursively reach clean nodes,
	and func.isClean(), but FIXME how could dirty nodes point at clean nodes
	since if x is in y's list then y has to be in x's list?
	Maybe 2 lists, 1 for clean and 1 for dirty? Maybe sort the 1 list that way? I dont like either of those.
	*
	public final boolean isClean;
	*/
	
	protected String name;
	public String name(){ return name; }
	
	protected λ func;
	public λ func(){ return func; }
	
	/** immutable list of mutable Webnodes??? */
	public List<Webnode> list;
	public List<Webnode> list(){ return list; }
	
	public void setList(List<Webnode> list){
		this.list = list;
	}

}

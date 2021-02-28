/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;
import wikibinator106.spec.*;

public class CleanLeaf extends Leaf{
	
	public CleanLeaf(){
		super(Marklar106bId.headerOfCleanLeaf);
	}

	public static final fn instance = new CleanLeaf();

	/** true, this is the leaf */
	public boolean isLeaf(){ return true; }

	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn l(){ return i; }
	
	public fn vm_asClean(){
		return this;
	}

	public fn vm_asDirty(){
		return DirtyLeaf.instance;
	}

	public fn vm_asDirty_recursiveAll(){
		return DirtyLeaf.instance;
	}

}

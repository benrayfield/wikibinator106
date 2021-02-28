/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;
import wikibinator106.spec.*;

public class DirtyLeaf extends Leaf{
	
	public DirtyLeaf(){
		super(Marklar106bId.headerOfDirtyLeaf);
	}
	
	public static final fn instance = new DirtyLeaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn l(){ return I; }
	
	public fn vm_asClean(){
		return CleanLeaf.instance;
	}

	public fn vm_asDirty(){
		return this;
	}

	public fn vm_asDirty_recursiveAll(){
		return this;
	}

}

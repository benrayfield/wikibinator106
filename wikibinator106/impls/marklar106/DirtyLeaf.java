/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import static wikibinator105.impls.marklar105b.ImportStatic.*;

import wikibinator105.impls.marklar105b.fn;
import wikibinator105.impls.marklar105b.ids.Marklar105bId;
import wikibinator105.spec.*;

public class DirtyLeaf extends AbstractFn{
	
	public static final fn instance = new DirtyLeaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn l(){ return I; }

	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn r(){ return this; }

	public long marklar105bHeader(){
		return Marklar105bId.headerOfDirtyLeaf;
	}

	public fn g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public byte b(int n){
		throw new RuntimeException("TODO");
	}

	public short s(int n){
		throw new RuntimeException("TODO");
	}

	public int i(int n){
		throw new RuntimeException("TODO");
	}

	public long j(int n){
		throw new RuntimeException("TODO");
	}

	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public fn w(Object wrapMe){
		throw new RuntimeException("TODO");
	}

	public $<fn> e(long maxSpend, fn r){
		throw new RuntimeException("TODO");
	}

	public boolean containsAxOf2Params(){
		return false;
	}
	
	/** 0 cuz not a cbt so its bize is 0 */
	public byte lizif(){
		return 0;
	}
	
	public fn asClean(){
		return CleanLeaf.instance;
	}

	public fn asDirty(){
		return this;
	}

}

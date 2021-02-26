/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;
import wikibinator106.spec.*;

public class CleanLeaf extends AbstractFn{
	
	public static final fn instance = new CleanLeaf();

	/** true, this is the leaf */
	public boolean isLeaf(){ return true; }

	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn l(){ return i; }

	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn r(){ return this; }

	public long marklar105bHeader(){
		return Marklar106bId.headerOfCleanLeaf;
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

	/** 0 cuz not a cbt so its bize is 0 */
	public byte liz(){
		return 0;
	}
	
	public fn asClean(){
		return this;
	}

	public fn asDirty(){
		return DirtyLeaf.instance;
	}

}

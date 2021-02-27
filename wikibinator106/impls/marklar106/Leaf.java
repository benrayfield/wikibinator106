package wikibinator106.impls.marklar106;

import wikibinator106.spec.$;

public abstract class Leaf extends AbstractFn{
	
	public Leaf(long header){
		super(header);
	}
	
	/** (L x (R x)) equals x, forall x, so L of a leaf is identityFunc (of same clean/dirty as the leaf), and R of a leaf is that leaf */
	public fn r(){ return this; }

	public fn g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public byte b(int n){
		return 0;
	}

	public short s(int n){
		return 0;
	}

	public int i(int n){
		return 0;
	}

	public long j(int n){
		return 0;
	}

	public fn G(long binheapIndex){
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

}

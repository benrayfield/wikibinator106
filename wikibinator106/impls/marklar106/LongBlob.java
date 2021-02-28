package wikibinator106.impls.marklar106;

import wikibinator106.spec.$;
import wikibinator106.spec.Op;

public class LongBlob extends AbstractFn{
	//TODO rename to PowOf2SizeLongArrayBlob //could be smaller in other classes as we know its padded with 10000...0000.
	
	protected final long[] content;

	//public final long bize;
	
	//TODO theres 3 firstbytes (11111001 11111010 11111011) as id whose literal value is 1 less than that,
	//but the other 31 bytes are themself. Should this store the id, value it represents, or both
	//(as 2 first longs? 2 first bytes? a boolean to add 1 or not, or subtract 1 or not?)
	
	public LongBlob(long... content){
		super(Marklar106bId.headerForPowOf2SizeContent(content));
		this.content = content;
		//bize = Marklar106bId.bize(data);
		//curriesSoFar = 6+ceil(log2(data.length));
	}

	public fn e(long p){
		throw new RuntimeException("TODO");
	}
	
	public $<fn> e(long maxSpend, fn param){
		throw new RuntimeException("TODO");
	}
	
	/*public long bizj(){
		return bize;
	}

	public byte liz(){
		return (byte)bize;
	}*/

	public fn e(fn param){
		throw new RuntimeException("TODO");
	}

	public fn apply(fn t){
		throw new RuntimeException("TODO");
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
		return content[n];
	}

	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public fn p(fn r){
		throw new RuntimeException("TODO");
	}

	public fn vm_asClean(){
		return this;
	}

	public fn vm_asDirty(){
		throw new RuntimeException("TODO, asDirty vs asDirty_recursive???, and... return the dirty unoptimized callpair form of this blob. cbt optimizations are only form clean cbts.");
	}

	public fn vm_asDirty_recursiveAll(){
		throw new RuntimeException("TODO, asDirty vs asDirty_recursive???, and..., that throw message in asDirty"); 
	}

}

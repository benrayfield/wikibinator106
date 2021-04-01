package wikibinator106.impls.marklar106;
import immutable.util.MathUtil;
import wikibinator106.spec.$;
import wikibinator106.spec.Op;

public class PowOf2SizeBytesBlob extends AbstractFn{
	
	protected final byte[] content;
	
	/** indexs in byte array.
	Midpoint is (from+toExcl)>>1 and is used for recursions into l() and r(), sharing same byte[].
	*/
	public final int from, toExcl;
	
	protected fn l, r;
	

	public PowOf2SizeBytesBlob(short s){
		this(MathUtil.shortToBytes(s));
	}
	
	public PowOf2SizeBytesBlob(int i){
		this(MathUtil.intToBytes(i));
	}
	
	public PowOf2SizeBytesBlob(long j){
		this(MathUtil.longToBytes(j));
	}
	
	public PowOf2SizeBytesBlob(byte... content){
		this(0, content.length, content);
	}
	
	public PowOf2SizeBytesBlob(int from, int toExcl, byte... content){
		super(Marklar106bId.headerForPowOf2SizeContent(content)); //FIXME
		if(toExcl-from <= 1) throw new RuntimeException("Use ImportStatic.cbt(byte) instead to get deduped form of a byte");
		this.from = from;
		this.toExcl = toExcl;
		this.content = content;
		if(from!=0 || toExcl != content.length){
			throw new RuntimeException("TODO lazyeval header to avoid looking for highest 1 bit at some recursions into l or r");
		}
	}
	
	//FIXME implement the funcs that get bit byte short char int long. keep the default float and double funcs.
	//and subrange;
	
	public fn l(){
		if(l != null) return l;
		int byteSize = toExcl-from;
		if(byteSize == 1){
			throw new RuntimeException("TODO return flyweight of ImportStatic.byteToFn[256], or blob containing 1 long 8 bytes so check for bytesize being 8?");
		}else{
			//TODO remember in SoftReference? normal ptr?
			return l = new PowOf2SizeBytesBlob(from, (from+toExcl)>>1, content);
		}
	}
	
	public fn r(){
		if(r != null) return r;
		int byteSize = toExcl-from;
		if(byteSize == 1){
			throw new RuntimeException("TODO return flyweight of ImportStatic.byteToFn[256], or blob containing 1 long 8 bytes so check for bytesize being 8?");
		}else{
			//TODO remember in SoftReference? normal ptr?
			return r = new PowOf2SizeBytesBlob((from+toExcl)>>1, toExcl, content);
		}
	}

	public fn g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

}
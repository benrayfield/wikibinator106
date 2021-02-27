package wikibinator106.impls.marklar106;

/** a normal lambda call pair. In this prototype they are always halted cuz uses java stack,
but the the marklar106b ids (including long header) support nonhalted call pairs.
The other kinds are for cbt/bitstring such as PowOf2SizeLongArrayBlob.
All are lambdas.
*/
public class Funcall extends AbstractFn{
	
	public final fn func, param;
	
	/** clean if both are clean, else dirty. If you want it to be dirty when both are clean, use the other constructor. */
	public Funcall(fn func, fn param){
		this(func.isClean()&param.isClean(), func, param);
	}
	
	public Funcall(boolean isClean, fn func, fn param){
		super(Marklar106bId.parentHeader(
			isClean, func.marklar106bHeader(), func.bizb(), func.j(2), param.marklar106bHeader(), param.bizb()));
		this.func = func;
		this.param = param;
	}
	
	public fn l(){
		return func;
	}
	
	public fn r(){
		return param;
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

	public fn p(fn r){
		throw new RuntimeException("TODO");
	}

	public fn apply(fn t){
		throw new RuntimeException("TODO");
	}

	public fn asClean(){
		throw new RuntimeException("TODO");
	}

	public fn asDirty(){
		throw new RuntimeException("TODO");
	}

}

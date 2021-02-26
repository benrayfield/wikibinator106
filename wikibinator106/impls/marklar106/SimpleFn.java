package wikibinator106.impls.marklar106;

public class SimpleFn extends AbstractFn{
	
	public final fn func, param;
	
	public SimpleFn(fn func, fn param){
		super(Marklar106bId.parentHeader(
			func.marklar106bHeader(), func.liz(), func.j(2), param.marklar106bHeader(), param.liz()));
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

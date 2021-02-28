package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;

/** a normal lambda call pair. In this prototype they are always halted cuz uses java stack,
but the the marklar106b ids (including long header) support nonhalted call pairs.
The other kinds are for cbt/bitstring such as PowOf2SizeLongArrayBlob.
All are lambdas.
*/
public class Funcall extends AbstractFn{
	
	public final fn func, param;
	
	/** If this is clean, then mirror is dirty. If this is dirty, then mirror is clean.
	Other than that, they have the same childs recursively below
	(including mixes of clean vs dirty in combos of childs below).
	This starts null and is filled in when requested.
	FIXME make sure if its filled in clean->dirty that its also filled in dirty->clean of those same 2 fns.
	*/
	protected fn mirror;
	
	/** clean if both are clean, else dirty. If you want it to be dirty when both are clean, use the other constructor. */
	public Funcall(fn func, fn param){
		this(func.isClean()&param.isClean(), func, param);
	}
	
	/** Called automatically when creating a cached clean/dirty mirror pair,
	which are the same in every way except self is locally clean vs dirty (childs are the same both ways).
	*/
	protected Funcall(fn mirror){
		super(mirror.marklar106bHeader()^Marklar106bId.maskIsClean_ignoreIfLiteralCbt256);
		if(Marklar106bId.isLiteral256Bits(marklar106bHeader)){
			throw new RuntimeException("TODO mirror optimization for literalCbt256, which only gets cbt optimization on clean side,"
				+" but still maybe should have its normal funcall form have a pointer to that, but unsure if"
				+" that should have a pointer to the normal funcall form since it might be wasteful"
				+" or interfere with the cbt optimization???");
		}
		func = mirror.l();
		param = mirror.r();
		this.mirror = mirror;
		if(isClean() != (func.isClean()&param.isClean())) throw new RuntimeException(
			isClean()+"==isClean() != ("+func.isClean()+"==func.isClean()&param.isClean()=="+param.isClean()+")");
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

	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public fn p(fn r){
		return cp(this,r);
	}

	public fn apply(fn t){
		throw new RuntimeException("TODO");
	}
	
	protected void createMirrorBothDirectionsIdempotently(){
		if(mirror == null){
			mirror = new Funcall(this);
			//FIXME put in AxfprCache, but need to slightly modify that design cuz...
			//FIXME??? (L x (R x)) equals x forall x that are the same clean_vs_dirty as x.l and x.r.
			//But for example of x.l is clean and x.r is clean and x is dirty, then (L x (R x)) returns the clean form of x,
			//which does not equal x. So you cant put (x.l x.r)->x in AxfprCache every time,
			//but it is the correct design, just gets a little more complex with there being 1 bit of clean_vs_dirty per node.
		}
	}

	public fn vm_asClean(){
		if(isClean()) return this;
		createMirrorBothDirectionsIdempotently();
		return mirror;
	}

	public fn vm_asDirty(){
		if(!isClean()) return this;
		createMirrorBothDirectionsIdempotently();
		return mirror;
	}
	

}

/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator106.spec;

public interface Evaler<T extends λ>{
	
	/** returns amount not spent, and if finished the calculation then the result of calling func on param.
	If did not finish, then eval(long,T,T).fn == null, and .gas may still be a big long
	if it knew it could not do the requested work with at most that amount of gas so did not waste the gas.
	*/
	public $<T> eval(long maxSpend, T func, T param);
	
	/** same as these 3 evals, ((funcA param) (funcB param)) aka (S funcA funcB param), aka Op.Trecurse,
	but may differ in number of threads or which gets how much gas,
	such as in some implementations it might allow up to half the gas to
	each of (funcA param) and up to half the gas to (funcB param)
	and if both of those finish and if there is any gas left then
	evals what one returns on what the other returns.
	<br><br>
	The default implementation (in Evaler interface) just does it single threaded.
	<br><br>
	If you want more threads, use Op.Trecurse and Op.Pair together in various combos,
	like if you want the 4 calls (a b) and (c d) and (e f) and (g h),
	then you might want (pair (pair (a b) (c d)) (pair (e f) (g h))) aka [[(a b) (c d)][(e f) (g h)]],
	so you could eval ({,pair {,pair {,a ,b} {,c ,d}} {,pair {,e ,f} {,g ,h}}} u)
	which evals to that, but before that last u param
	its halted (a derived kind of lazyeval, not lazy at VM level).
	If you eval that, then in some kinds of Evaler
	(TODO create InterpretedModeUsingJavaStack.multiThreadedInstance?),
	when it gets to Op.treecurse or Op.Treecurse in eval(long,λ,λ), it would call fork(long,λ,λ,λ),
	which would recursively call fork, and so on,
	BUT if it just divides the gas in half each time then that would likely run out of gas in some parts
	and have more than enough in other parts but since its done in parallel
	some parts would fail and some would succeed so the whole calculation in total would fail
	even though if it had been done sequentially it could share gas between those calls
	so if some use less and some use more but in total theres enough, in any combo of that, it would work.
	FIXME design some way for a parallel calculation to wait on other parallel calculations
	to finish with gas left over, instead of failing, wait for the more gas to come in,
	or something like that.
	*/
	public default $<T> fork(long maxSpend, T funcA, T funcB, T param){
		//single threaded
		$<T> ap = funcA.e(maxSpend,param);
		if(ap.fn == null) return ap;
		$<T> bp = funcB.e(ap.gas,param);
		if(bp.fn == null) return bp;
		return ap.fn.e(bp.gas,bp.fn);
	}
	
	/** See Op.Wiki. All nondeterminism goes here, and in dirty ops (start with capital letter like Op.Trecurse vs Op.trecurse)
	which can call this Op.Wiki without truncateToClean happening automatically (cuz clean can only see and create clean).
	*/
	public default $<T> wiki(long maxSpend, T param){
		return eval(maxSpend, op(false,Op.wiki), param);
	}
	
	/** cleanLeaf or dirtyLeaf */
	public T u(boolean isClean);
	
	/** cleanLeaf */
	public default T u(){ return u(true); };
	/** dirtyLeaf */
	public default T U(){ return u(false); }
	
	/** get the lambda form of an opcode, with comment being cleanLeaf (the normed form of the opcode) */
	public T op(boolean isClean, Op o);
	/** clean op */
	public default T op(Op o){ return op(true,o); }
	/** dirty op */
	public default T Op(Op o){ return op(false,o); }
	
	/** wrap an arrays bits in a lambda without type info, such as byte[] or double[] or String */
	public T w(Object wrapMe);
	
	/** like w(Object) but with type info, using Op.typeval. TODO maybe that should be a param
	so the spec doesnt require any specific way of using typeval such as "double[]" vs "[double;" vs contentType form
	vs types that arent even strings. Of course Op.ax is for turingCompleteTypes and this is for loose unverified types.
	*/
	public T ww(Object wrapMe);

}
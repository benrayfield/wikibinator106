package wikibinator106.spec;
import java.math.BigInteger;
import java.util.function.UnaryOperator;

import wikibinator106.impls.marklar106.Marklar106bId;
import wikibinator106.impls.marklar106.fn;

/** a universal function of 7..127 params,
which is a combinator, universal lambda, and pattern calculus function,
and is designed to be GPU optimized and for gaming-low-lag sync in p2p network, graphics, sound, etc,
and has bitstrings built in up to 2^120 bits which in abstract math are padded with 10000...000 until next powOf2
and can be sparse or dense, but in practice will store only the parts you actually need. 
*/
public interface λ<Subclass extends λ<Subclass>> extends UnaryOperator<Subclass>{
	
	/*OLD... op.curry (of up to 32 opcodes) takes a cbt1 to cbt<2^126> (or something around that limit)
	as one of its params, and counts down params similar to wikibinator105.
	Can the number of curries be the first param of cleanLeaf and first param of dirtyLeaf?
	??? Could if there were 4 leaf types instead of those 2: cleanLeaf, dirtyLeaf, zero, and one,
	and zero and one would both be clean and when called on anything always returns a cbt,
	either a cbt twice its size or maybe returns itself if is at max curries (127),
	but I'm trying to keep it just to those 2 leaf types. ???
	Do I want zero and one to be just another op among the up to 32 ops?
	Should those 32 ops be chosen by the first 5 params (after cleanLeaf/dirtyLeaf)?
	Should those first 5 params have to be either zero or one?
	*/
	
	public EvalerChain compiled();
	
	/** new EvalerChain's prev() normally points at what compiled() returns before this was called.
	Many fns can share the same EvalerChain.
	*/
	public void setCompiled(EvalerChain compiled);
	
	public default Subclass e(Subclass param){
		return e(Long.MAX_VALUE, param).fn; //FIXME what if .fn is null cuz didnt have enough gas? throw?
	}
	
	public default $<Subclass> e(long maxSpend, Subclass param){
		return compiled().eval(maxSpend, this, param);
	}
	
	public default Subclass e(Object wrapMe){
		//FIXME pay gas (part of maxSpend) for the wrapping
		return e(compiled().ww(wrapMe));
	}
	
	public default Subclass e(long maxSpend, Object wrapMe){
		//FIXME pay gas (part of maxSpend) for the wrapping
		return e(maxSpend,compiled().ww(wrapMe));
	}
	
	/** UnaryOperator does lambda call */
	public default Subclass apply(Subclass param){
		return e(param);
	}
	
	/** x.isClean()==(x.l().isClean()&x.r().isClean()), and (L x (R x)) equals x, forall x.
	(L cleanLeaf) is cleanIdentityFunc. (L dirtyLeaf) is dirtyIdentityFunc.
	(R cleanLeaf) is cleanLeaf. (R dirtyLeaf) is dirtyLeaf.
	*/
	public boolean isLeaf();

	/** x.isClean()==(x.l().isClean()&x.r().isClean(), and (L x (R x)) equals x, forall x */
	public boolean isClean();
	
	public default boolean isCleanLeaf(){
		return isLeaf() && isClean();
	}
	
	public default boolean isDirtyLeaf(){
		return isLeaf() && !isClean();
	}
	
	/** true iff deep contains any (Op.axa x) or (Op.axb x), clean or dirty form, for any x,
	but doesnt count Op.axa or Op.axb by itself
	Where this is false, then sync and verifying is much easier.
	TODO implement this in subclasses using a bit in header, for constant cost instead of this exponential cost.
	*/
	public default boolean containsAxconstraint(){
		return !isLeaf() && (isAxconstraint() || l().containsAxconstraint() || r().containsAxconstraint());
	}
	
	/** is Op.axa or Op.axb (clean or dirty forms, all 4 of those being kinds of "ax") of 1 param.
	This is important to know cuz ax evals constraint at 1 param, and evals normally at 2 params.
	If constraint fails, then the constraint call never halts, so just seeing such a constraint means its true.
	Example: (axa (fpr (pair "hello" "world") fal "world")) is a true constraint
	cuz (fpr (pair "hello" "world") fal "world" u)->u meaning (pair "hello" "world" fal)->"world",
	but (axa (fpr (pair "hello" "world") fal (pair axa axb))) infinite loops
	cuz (fpr (pair "hello" "world") fal (pair axa axb))->(u u)
	cuz (pair "hello" "world" fal) returns "world" instead of returns (pair axa axb).
	Also, if first param of ax, called on u/cleanLeaf, does not halt, then the ax call does not halt either,
	so ax constraints can only be true when the param halts when called on leaf.
	After that halts, you get, for example, a halted (axa (fpr (pair "hello" "world") fal "world")).
	The call and the halted form are the same, and is just a constraint to verify.
	Its marked as verified by observing that object,
	instead of seeing its 2 childs separately in Evaler.Eval(long,λ,λ).
	Call it on 1 more param (second/last param of axa or axb) and it does this, for turing-complete-types:
	(axa x y)->(x (t y)).
	(Axa x y)->(x (T y)).
	(axb x y)->(x (f y)).
	(Axb x y)->(x (F y)).
	*/
	public default boolean isAxconstraint(){
		byte o6 = op6Bits();
		return (o6==Op.axa.op6Bits || o6==Op.axb.op6Bits) && curriesMore()==1;
	}
	
	/** 1 less param than isAxconstraint. Is Op.axa or Op.axb (clean or dirty form) by itself */
	public default boolean isAx(){
		byte o6 = op6Bits();
		return (o6==Op.axa.op6Bits || o6==Op.axb.op6Bits) && curriesMore()==2;
	}
	
	/** sparse matrix optimization, and helps with computing bize when content is all 0s, to know if first bit is 1 vs 0.
	True if contains any Op.one.
	Does not include Op.one where comment is not cleanLeaf. Only includes the clean normed form, cuz cbt optimizations work on it.
	TODO implement this in subclasses using a bit in header, for constant cost instead of this exponential cost.
	*/
	public default boolean containsCleanNormedBit1(){
		return !isLeaf() && (isCleanNormedBit1() || l().containsCleanNormedBit1() || r().containsCleanNormedBit1());
	}
	
	/** see containsBit1() */
	public boolean isCleanNormedBit1();
	
	/** gets the sixth param of cleanLeaf/dirtyLeaf, else returns cleanLeaf cuz it has less than 6 params so far.
	The first 5 params are either cleanLeaf vs anything_except_cleanLeaf to choose from 32 ops, or 64 if count clean vs dirty.
	*/
	public default λ comment(){
		throw new RuntimeException("TODO");
	}
	
	/** forkEdit this so comment() returns the given param.
	If doesnt have 6 params yet, appends cleanLeaf for the remaining params.
	The first 5 params are either cleanLeaf vs anything_except_cleanLeaf to choose from 32 ops, or 64 if count clean vs dirty.
	*/
	public default λ comment(λ newComment){
		throw new RuntimeException("TODO");
	}
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public default byte curriesAll(){
		return (byte)(curriesSoFar()+curriesMore());
	}
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public default byte curriesSoFar(){
		return (byte)(curriesAll()-curriesMore());
	}
	
	/** curriesSoFar+curriesMore==curriesAll, range 0..127 */
	public default byte curriesMore(){
		return (byte)(curriesAll()-curriesSoFar());
	}
	
	/** including curriesMore and evalsEarly, since some things eval on multiple curries. See Op.evalsEarly. */
	public default boolean evalsOnNextCurry(){
		return Op.evalsOnNextCurry(op6Bits(),curriesMore());
	}
	
	/** Can be true even if l().evalsOnNextCurry() (where r() is its next curry)
	cuz, this wikibinator106 VM uses java stack instead of simulating the stack (see callquads in occamsfuncer),
	and the λ isnt created until its halted, even if the evaling and halted form are the same (which is often true).
	*/
	public default boolean isHalted(){
		return curriesMore()>0; //FIXME read comment of this func, about cases like (axa anything)
		//which isHalted only if (anything u)->u, but before (anything u) returns,
		//dont know if its halted or not.
		//If (anything u)->u then /*nonhalted*/(axa anything)->/*halted*/(axa anything),
		//which seems to be bad logic but since this wikibinator106 VM only has halted nodes,
		//this function can actually just be "return true",
		//and (axa ) would never happen unless its halted since
		//it would instead be on java stack in Evaler.eval(long,axa,anything)
		//until it halts (if ever) then it would return (axa anything) or (axb anything)
		//depending if (anything u) returns u/cleanLeaf vs returns anything other than u/cleanLeaf.
		//Similarly, (axb anything) is halted if (anything u)->anything_except_u
		//such as (axb (fpr (+ 2) 2 5)) would be halted cuz (fpr (+ 2) 2 5 u)->(u u)
		//cuz 2+2 does not equal 5. (axa (fpr (+ 2) 2 4)) would be halted cuz 2+2 equals 4.
	}
	
	/** If this is a cbt, then it has 2 exponent cbtHeight() bits, and this ranges 0..120, or 121 is a nonhalted state.
	If this is not a cbt, then this is just curriesSoFar()-6.
	*/
	public default byte cbtHeight_ignoreUnlessCbt(){
		return (byte)(curriesSoFar()-6);
	}
	
	public boolean isCleanCbt();
	
	//Even if we only know the low 32 bits of bize in some id types, can still effectively use powOf2 size cbts bigger.

	/** Bitstring sIZE. range 0..(2^120-1). */
	public default Number bize(){
		throw new RuntimeException("TODO return new BigInteger(15 bytes of bizk() bizj());");
	}
	
	public default byte bizb(){
		return (byte)bizi();
	}
	
	/** Bitstring sIZE. low 32 bits of bize which ranges 0..(2^120-1).
	Since curriesAll ranges 0..127, bize cant be bigger than fits in int128.
	The default id type only knows the low 40 bits of bize and ceil(log2(bize)) up to 120.
	*/
	public default int bizi(){ return (int)bizj(); }
	
	/** Low 40 bits of bize, which ranges 0..(2^120-1). Same as bizj()&0xffffffffffL but may be more efficient,
	considering that marklar106b ids store the low 40 bits of bize and store curriesSoFar
	so know ceil(log2(bize)) if its not higher than it needs to be
	which is a valid cbt but not the normed form of a bitstring.
	Like the bitstring 01110, in abstract math is 01110100
	and its nonnormed forms include 0111010000000000 and 01110100000000000000000000000000.
	If a cbt is all 0s, its bize is 0, even though the bize of a 1 then 0s until the end is also 0,
	and to know the difference between those, check if its a call of Op.zero or Op.one which tells the first bit.
	*/
	public default long biz40(){ return bizj()&0xffffffffffL; }
	
	/** Bitstring sIZE. low 64 bits of bize which ranges 0..(2^120-1). See comment of bizi. */
	public default long bizj(){
		return bize().longValue();
	}
	
	/** Bitstring sIZE. high 56 bits of bize (in my low 56 bits) which ranges 0..(2^120-1). See comment of bizi. */
	public default long bizk(){
		Number b = bize();
		if(b instanceof BigInteger){
			BigInteger B = (BigInteger)b;
			return B.shiftRight(64).longValue(); //could &0x00ffffffffffffff but if bize() is correct that has no effect.
		}else{
			throw new RuntimeException("TODO how to get the high long from "+b);
		}
	}
	
	//Should bize always be long and max cbt is cbt63? js doesnt have longs. max cbt53 cuz double can count to 2^53?
	//What about long gas? double gas with max value of 2^53-1?
	
	
	
	//////////
	
	public default Subclass l(){ return g(2); }
	
	public default Subclass r(){ return g(3); }
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	same as l().r() but may be more efficient, such as in the optimization used in Pair.java
	to store x and y but lazyEval (pair x) in (pair x y) and not trigger laziEval of that just to get x or y.
	*/
	public default Subclass lr(){ return g(5); }	
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default Subclass g(int binheapIndex){ return g((long)binheapIndex); }
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public Subclass g(long binheapIndex);
	
	/** See Op.op6Bits. Its the low 6 bits of this byte. If this has 5 params, then the 6 bits are 32|anOp.ordinal().
	Leaf.op6Bits()==1. deeplazy.op6Bits()==0. From 1 to 31 are number of curries being 1..4.
	Anything at param 6 or higher copies its op6Bits from its left child if both childs are halted, else 0/deeplazy.
	*/
	public byte op6Bits();
	
	public default boolean isCbt(){
		byte o6 = op6Bits();
		return o6==Op.one.op6Bits || o6==Op.zero.op6Bits; 
	}
	
	/** get bit at index, if this is a cbt, else 0/false. You should probably use i(int) or j(int) etc for efficiency instead.
	This helps with default implementations of those, which should be overridden by subclasses for efficiency,
	such as a subclass that wraps a float[] would be most efficient to use f(int), but can still get its bits this way
	and rebuild a float from 32 calls of this.
	*/
	public default boolean z(long bitIndex){
		if(!isCbt() || bitIndex < 0) return false; //0
		byte h = cbtHeight_ignoreUnlessCbt();
		//FIXME what if bitIndex is bigger than 2 exponent h. Return 0/false. But that case isnt being checked for here.
		if(h > 0){
			//FIXME does this get the wrong answer around 2 exponent 62 ... 2 exponent 64?
			if(h > 63){ //recurse into left child
				return l().z(bitIndex);
			}else{ //recurse into right child
				long leftCbtSize = 1L<<(h-1);
				return r().z(bitIndex-leftCbtSize);
			}
		}else{
			return op6Bits()==Op.one.op6Bits; //This is a cbt1 (cbt of 1 bit, which may be 0 or 1). Read it.
		}
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*8..n*8+7 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of byte[] etc.
	*/
	public default byte b(int n){
		return (byte)(j(n>>3)>>(56-(n<<3)));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*16..n*16+15 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of short[] or char[] or String etc.
	*/
	public default short s(int n){
		return (short)(j(n>>2)>>(48-(n<<2)));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105. */
	public default char c(int n){
		return (char)s(n);
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*32..n*32+31 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of int[] or float[] etc.
	*/
	public default int i(int n){
		return (int)(j(n>>1)>>(32-(n<<1)));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105. */
	public default float f(int n){
		return Float.intBitsToFloat(i(n));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	0 if not a cbt. get bits n*64..n*64+63 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This can be overridden in subclasses for efficient for wrappers of long[] or double[] etc,
	but is very slow in default implementation.
	*/
	public default long j(int n){
		long ret = 0;
		for(int i=0; i<64; i++) ret = (ret<<1) | (z((n<<5)+i) ? 1L : 0L); //read 1 bit 64 times
		return ret;
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105. */
	public default double d(int n){
		return Double.longBitsToDouble(j(n));
	}
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default Subclass G(int binheapIndex){ return G((long)binheapIndex); }
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public Subclass G(long binheapIndex);
	
	/** FIXME rewrite text and redesign code maybe, as this was copied from wikibinator105.
	callpair of this and param, without checking if thats a valid thing to do,
	since (todo choose a design?) only halted nodes are allowed.
	*/
	public Subclass p(Subclass r);
	
	public default Subclass p(Object wrapMe){
		return p(compiled().ww(wrapMe));
	}
	
	/*public default String toDataUrl(fn idMaker){
		throw new RuntimeException("TODO concat data:"+Const.contentTypePlr+" base64 something, then call idMaker on this, on this.l(), on this.r()... Or just define a dataUrl as a kind of id, so given an idMaker (such as marklar106b idMaker), the dataUrlIdMaker would do this. Want this for dragAndDrop into and out of java window");
	}*/

}

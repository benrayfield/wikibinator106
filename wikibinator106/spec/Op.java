package wikibinator106.spec;

import static wikibinator106.impls.marklar106.ImportStatic.cp;

import java.util.Arrays;

import wikibinator106.impls.marklar106.Marklar106bId;

/** 32 ops, each with a clean form and a dirty form, so theres actually 64 ops.
The clean form of an op starts with a lowercase letter, and dirty form is capital, like wiki vs Wiki.
These are chosen in the first 5 params each being cleanLeaf vs anything_except_cleanLeaf,
then a comment param (which can be anything and is ignored other than by reflection ops),
then the params of that op. The total number of params of leaf is at most 127 so fits in unsigned byte.
*/
public enum Op{
	
	/** after first 5 params, next param is comment, and to use cbt/bitstring optimizations that comment must be cleanLeaf,
	then up to 120 params which result in a halted cbt of 2^0 to 2^120 bits, and the next param results in
	(Op.growinglist (Op.growinglist cleanLeaf this) param)), and so on. The cleanLeaf means nil as growinglist normally starts.
	So you can keep using cbts/bitstrings the same way, calling growinglist_cbtlike_thing on growinglist_cbtlike_thing,
	to get a growinglist_cbtlike_thing twice as big, but its not a cbt anymore.
	FIXME you can or cant have a comment other than cleanLeaf in clean cbt???
	<br><br>
	UPDATE: growinglist takes 5+1+1+64+1=74 params,
	used to have less objects in linkedlists than pair func and for unlimited size bitstrings.
	*/
	zero(5+1+120+1,true),
	
	/** See comment of Op.zero about params. Bitstrings up to 2^120-1 bits. The last 1 bit is the first bit of padding.
	The default kind of id is a 256 bit id and stores the low 32 (UPDATE: 40) bits of bize (bitstring size),
	so if you want an int64 or int128 bitstring size you have to compute it in interpred mode as lambdas,
	or derive a 512 bit id that has more room for bize.
	<br><br>
	(one_or_zero param1 param2 ... param120 param121) -> (growinglist (one_or_zero param1 param2 ... param120) param121),
	other than up to param120 every param is verified to be a cbt of same size else evals self on self,
	so get a cbt twice as big. (UPDATE: put a nil/u f irst in that growinglist)
	<br><br>
	TODO rewrite disorganized text below...
	<br><br>
	A cbt is a complete binary tree of bits, in this case up to 2^120 bits.
	one = (leaf u u u u u u comment), and zero = (leaf u u u u u (u u) comment), or something like that???
	(one zero) is halted.
	((one zero) (one one)) is halted.
	((one zero) one) -> ((one zero) (one zero)) cuz,
	up to cbt<2^120>, a cbt called on anything is a cbt twice as big,
	and if its called on anything else its the same as calling it on itself.
	<br><br>
	A cbt<2^120> x called on y is (growinglist x y),
	and (growinglist x y z) is (growinglist (growinglist x y) z), and so on???
	growinglist can be derived from curry3,
	but I'll make it an op so all the ops behaviors can be implemented without curry*.
	<br><br>
	up to cbt<2^120> cuz the number of curries after leaf must fit in a signed byte (max 127),
	and the 127th curry must eval to something smaller since if it didnt you coult put a 128th.
	and it starts with (leaf x x x x x comment). The first 5 params choose which of 32 ops.
	The sixth param is always comment.
	In the case of zero and one, you dont get the cbt/blob optimization if comment is not cleanLeaf.
	Biggest: (leaf x x x x x comment ...120ParamsAllMadeOfZerosAndOnes...).
	The default kind of id stores the low 32 bits of bize (bitstring size)
	so if its up to cbt<31> then bize fits in int.
	You can still have up to cbt<2^120>, and can still compute bize using lambdas,
	but its slower.
	TODO what should the 127th curry do?
	<br><br>
	UPDATE: growinglist takes 5+1+1+64+1=74 params,
	used to have less objects in linkedlists than pair func and for unlimited size bitstrings.
	*/
	one(5+1+120+1,true),
	
	/** the lambda Lx.Ly.y. (fal u) is the normed form of cleanIdentityFunc,
	and (Fal u) is normed dirtyIdentityFunc which is the most general but as usual can only
	be called by other dirty funcs without truncating their param to clean before they see it.
	*/
	fal(5+1+2,false),
	
	/** the lambda Lx.Ly.x aka the K lambda of ski calculus */
	tru(5+1+2,false),
	
	/** The reflect ops are: getfunc/l, getparam/r, isleaf, isclean.
	aka L. (L x (R x)) equals x, forall halted x. x.l().e(x.r()).equals(x) forall halted x.
	*/
	getfunc(5+1+1,false),
	
	/** The reflect ops are: getfunc/l, getparam/r, isleaf, isclean.
	aka R. (L x (R x)) equals x, forall halted x. x.l().e(x.r()).equals(x) forall halted x.
	*/
	getparam(5+1+1,false),
	
	/** The reflect ops are: getfunc/l, getparam/r, isleaf, isclean.
	returns Op.tru or Op.fal (or Tru or Fal if self is Dirty) depending if its param is leaf
	(either of cleanLeaf or DirtyLeaf)
	*/
	isleaf(5+1+1,false),
	
	/** The reflect ops are: getfunc/l, getparam/r, isleaf, isclean.
	Every object is a 2-way forest with 1 bit of data (UPDATE: 0 bits of data (forest shape only):
	clean vs dirty is derived and cached, except for at the 2 leafs) at each node, that bit being isCleanVsDirty,
	and all paths lead to cleanLeaf or dirtyLeaf, and a parent must be dirty if either of its 2 childs is dirty,
	and if both its childs are clean then the parent can be clean or dirty,
	and if a clean is called on a dirty then that dirty is truncateToClean (call (fal u) on it, aka clean identityFunc)
	before the clean sees it.
	<br><br>
	x.isClean()==(x.l().isClean()&x.r().isClean()), and (L x (R x)) equals x, forall x.
	(L cleanLeaf) is cleanIdentityFunc. (L dirtyLeaf) is dirtyIdentityFunc.
	(R cleanLeaf) is cleanLeaf. (R dirtyLeaf) is dirtyLeaf.
	*/
	isclean(5+1+1,false),
	
	/** All nondeterminism goes here, and if clean then it infinite loops for all possible params,
	else is decided by ax fpr statements.
	<br><br>
	(axa (fpr wiki x y)) means (wiki x)->y.
	Also, there will be a few functions built in, something like
	(curry... wiki "spend" salt maxAmountToSpendAsInt64 x) -> [amountDidNotSpend (x cleanLeaf)], or something like that.
	(curry... wiki "wallet" salt) -> how much is left for spend calls etc, as int64, or something like that.
	(curry... solve x) -> y where (ax (fpr x y cleanLeaf)).
	64 bit local ids of things, actually global ids but with some prefix so nobody else would randomly choose it?
	*/
	wiki(5+1+1,false),
	
	/** (fpr func param ret u) -> u if (func param)->ret, else -> (u u), where u is cleanLeaf.
	Example: (axa (fpr ["hello" "world"] fal "world")) cuz (["hello" "world"] fal) -> "world".
	Example: (axb (fpr ["hello" 235] fal "world")) cuz (["hello" 235] fal) -not-> "world" aka does not return "world",
	so in that case (fpr ["hello" 235] fal "world" u) -> (u u).
	*/
	fpr(5+1+4,false),
	
	/** (axA x) and (axB x) cant both exist.
	(axA x) is halted if (x u)->u.
	(axB x) is halted if (x u) -> anything except u.
	//Maybe, (axC x) is halted if (x u) does not halt, but I'm unsure if should have an axC.
	(axA x y) -> (x (T y))
	(axB x y) -> (x (F y))
	How would that be detected? A lambda could generate a hash thats the same for (axA x) and (axB x),
	for any x, but different for axA vs axB, or something like that.
	(details to work out on whats a normed form)
	<br><br>
	The sparse turingComplete bloomfilter is made of axa and axb statements.
	Its a bloomfilter of 2 bits per binary forest node n.
	bloomFilter(n)==10 if (axa n) andOr (Axa n) exists.
	bloomFilter(n)==01 if (axb n) andOr (Axb n) exists.
	bloomFilter(n)==00 until, if ever, those are observed.
	bloomFilter(n)==11 if both are observed, which is BULL aka an error that requires forking.
	In the CLEAN parts, as long as the math of the universal function is computed precisely,
	its impossible to generate BULL aka bloomFilter(n)==11,
	and every possibly halting lambda call will have an ax statement,
	such as using dovetailing (see fntape in readme.md of occamsfuncer)
	to loop over all possible lambda calls andOr halted lambdas that they return.
	<br><br>
	BULL occurs when (axa (fpr pair s pair)) and (axb (fpr pair s pair)) exist
	somewhere reachable from the same node.
	<br><br>
	Ax, like in axiom, but only axioms that if true can be verified in some finite time,
	and if false may take infinite time to disprove as claiming that a certain lambda call halts but it actually doesnt,
	since of course halting-oracles are logically impossible since they claim that can always be done in finite time. 
	This is how constraints and turingCompleteTypes and turingCompleteChallengeResponse is done.
	The turing complete type system is on the return types, while always allowing all possible params regardless of type,
	so its not exactly untyped lambdas or typed lambdas by the existing models of lambedas.
	<br><br>
	axa, axb, zero, and one, are the only ops that eval before it has all its params,
	and zero and one always halt instantly when they do that, but this may take up to infinite time (halting problem related).
	It evals at its first param to verify constraint,
	UPDATE[switches from axa to axb or from axb to axa if disproven and halts, see Evaler where that happens]
	OLD[and if not verified then it infinite loops (evals to (S I I (S I I))) so it cant exist if]
	the statement it represents (such as "(x u)->u") is not true.
	*/
	axa(5+1+2,true),
	
	/** the bloom-filter counterpart of axa. Theres also a third case, not axa or axb, where it doesnt halt,
	but lambdas will never see that, even if they're in debugStepOver andOr debutStepInto
	cuz sometimes it takes an infinite number of steps to know it doesnt halt,
	while other times you could easily prove something doesnt halt such as (s i i (s i i),
	but since the universal function has to be deterministic, and it could never be agreed on
	where the line between can-prove-it-halts vs halting-oracle is, there is no third ax statement,
	but there should be at an NSAT level below the lambda level if its computed such as by 3sat constraints and
	can be computed a variety of ways (GPU, CPU, pen and paper, 3SAT, etc) and they will all get the same result.
	*/
	axb(5+1+2,true),
	
	/** the lambda Lx.Ly.Lz.zxy. Church-pair lambda of 3 params */
	pair(5+1+3,false),
	
	/** (growinglist prevList param0 param1 ... param64 param65)
	-> (growinglist (growinglist prevList param0 param1 ... param64) param65).
	Its similar to pair func for making linkedlists but creates less objects for list of same size.
	A reverse ordered pair list of 5 things is: (pair (pair (pair (pair (pair λ a) b) c) d) e)
	aka [[[[[λ a] b] c] d] e]. λ is nil/cleanleaf/u.
	A growinglist of 5 thigns is: (growinglist λ a b c d e),
	and only acts like pair with an extra object per 64 instead of per 2.
	You can of course display a growinglist reversed or not,
	but pair can actually store things reversed or forward like
	[[[[[nil a] b] c] d] e] aka <λ a b c d e>
	vs [a [b [c [d [e nil]]]]] aka [a b c d e λ].
	You always write λ/nil in these various kinds of linkedlists cuz otherwise
	it would imply nil is there when its not, and you might instead want a tree of pairs without nil.
	It also simplifies the syntax parsing and tostring rules.
	<br><br>
	As usual the first 5 params choose which of 32 ops, and next param is comment which can be anything.
	After that is prevList, then 0-64 list items (can be anything), and if theres another param it evals to
	put self as prevList into a new growinglist with λ as its comment (cuz λ is default comment).
	<br><br>
	You might, for example, want to use a growinglist as a simple map alternating key val key val key val...
	so can fit a map of size 32 into the first (growinglist nil ...64 things...),
	and a map of 33..64 things in (growinglist (growinglist nil ...64 things...) ...64 things...),
	and so on, but you'd still need a function wrapping it to do mapPut and mapGet,
	and it would be slow to linear search it, checking equals for each key,
	so its only meant for small maps, and for bigger maps use curry1..curry16 to derive treemap
	using maybe marklar106bId to generate cbt256 ids to sort the treemap by
	so anything can be a key and anything can be a value.
	<br><br>
	---
	<br><br>
	SOLUTION IS: changing growinglist to take 65 params, the first being nil or the previous list its appended to,
	so each growinglist section holds 0-64 list items, so for example a list of 192 things
	starts with (growinglist nil) then call that on 192 things.
	After calling it on 64 things its just (growinglist nil ...64 things...),
	but 1 more thing x and its (growinglist (growinglist nil ...64 things...) x)
	so 192 things is (growinglist (growinglist (growinglist nil ...64 things...) ...64 things...) ...64 things...),
	or 131 things is (growinglist (growinglist (growinglist nil ...64 things...) ...64 things...) ...3 things...),
	or 0 things is (growinglist nil). nil is u/cleanleaf.
	<br><br>
	<br><br>
	<br><br>
	PROBLEM WAS...
	want a growinglist-like 120 or 121 param thing, or maybe just 64? or maybe it just infloops when reaches 127 params?
	Maybe replace curry16 with that?
	Or maybe replace growinglist with that?
	Dont want it to be curry120 cuz thats too much recursion to find funcBody,
	even though funcBody will likely be cached in some implementations, its too much recursion to verify funcBody.
	An infcur op (never fills all its params, just keeps appending them) would make optimizations slower
	that depend on curriesMore curriesAll and curriesSoFar each fitting in a byte and not having to check for an infcur value of them.
	If there was 1 more bit of op, then there could be 64 ops instead of 32, or 128 instead of 64 if you count clean/dirty,
	plus 1 more bit for the binheap indexing, so whats currently called op6Bits would be op7Bits,
	and the isclean bit would normally be stored with it. But if want an extra bit,
	have to take it from somewhere in the 64 bit header. If take it from bize40 making that bize39,
	that complicates bize storage not being byte aligned. 
	If go down to max 63 params instead of 127, number of curries fits in 1 less bit, and bitstrings would have max 2^56 bits.
	I dont want to reduce any of those things.
	*
	<br><br>
	OLD...
	<br><br>
	(growinglist x y z) -> (growinglist (growinglist x y) z).
	This is mostly here so Op.zero and Op.one can keep acting like bitstrings above 2^120 bits,
	as a cbt called on anything is a cbt twice as big,
	and 2 cbts size 2^120 bits one called on the other returns a growinglist containing those 2, and so on,
	and some funcs might be designed to look for growinglist AND cbts instead of just CBTs,
	but probably the biggest cbt anyone will practically use fits in a long,
	unless they're trying to model some sparse space like the whole state space of ethereum
	or all possible universe states, but even then its probably better to use some other datastruct
	cuz cbt is as deep as log of its size, which can get very deep if its very sparse.
	*/
	growinglist(5+1+1+64+1,false),
	//OLD: growinglist(5+1+3,false),
	
	/** (typeval x y z)->(y z). Normally just keep it as (typeval x y)
	such as (typeval "image/jpeg" ...bytesofjpgfile...) as a semantic.
	If you want turingComplete types, use Op.axa and Op.axb.
	*/
	typeval(5+1+3,false),
	
	/** the S lambda of ski calculus: Lx.Ly.Lz.xz(yz) */
	trecurse(5+1+3,false),
	
	/** Example: (curry5 comment funcBody a b c d) is halted,
	and (curry5 comment funcBody a b c d e) -> (funcBody [(curry5 comment funcBody a b c d) e]),
	where [a b] means (pair a b).
	Each of the 32 ops is chosen by the first 5 params. Next param is comment. Then are params of those ops.
	*
	curry,
	FIXME choose number of curries.
	todo choose where comment param goes, is it always fifth param, after 4 params to choose from 16 ops?
	Or should there be 1-16 params of curry and have that be 32 opcodes, and blobs still go up to 127 curries?
	Its suggested that the first param after funcBody be context, as in occamsfuncer
	you might put a treemap of namespace in it or [salt treemapNamespace].
	TODO should this instead be curry2 to curry17?
	*/
	curry1(5+1+1+1,false), curry2(5+1+1+2,false), curry3(5+1+1+3,false), curry4(5+1+1+4,false),
	curry5(5+1+1+5,false), curry6(5+1+1+6,false), curry7(5+1+1+7,false), curry8(5+1+1+8,false),
	curry9(5+1+1+9,false), curry10(5+1+1+10,false), curry11(5+1+1+11,false), curry12(5+1+1+12,false),
	curry13(5+1+1+13,false), curry14(5+1+1+14,false), curry15(5+1+1+15,false), curry16(5+1+1+16,false);
	
	/** curriesAll-6. after the first 6 params (first 5 chooses op, next param is comment), then the params of the op) *
	public final byte params;
	*/
	
	/** Does it eval on every curry, or just the last/curriesAll/curriesMore? False if only has 1 curry.
	Things like axa verifying constraint on its first param, and still evaling as normal on second param,
	and cbt checks if its param is a cbt of same size and if its not (unless is last curry) then calls self on self instead
	so a cbt (unless max size of 2^120 bits, after which it uses Op.growinglist) called on anything is a cbt twice as big.
	*/
	public final boolean evalsEarly;
	
	/** 6+params */
	public final byte curriesAll;

	/** the 6 op bits in low bits of a byte, that choose between 32 ops, 31 possible leaf/anynonleaf prefixes of them, and 0/deeplazy.
	That describes 0..5 params, and every param after that copies those same 6 bits from its left child.
	If this has 5 params, then the 6 bits are 32|anOp.ordinal().
	Leaf.op6Bits()==1. deeplazy.op6Bits()==0. From 1 to 31 are number of curries being 1..4.
	Anything at param 6 or higher copies its op6Bits from its left child if both childs are halted, else 0/deeplazy.
	(todo shorten that comment, it has duplicate info).
	*/
	public final byte op6Bits;
	
	private Op(int curriesAll, boolean evalsEarly){
		if(curriesAll < 6 || curriesAll > 127) throw new RuntimeException("curriesAll = "+curriesAll);
		this.curriesAll = (byte)curriesAll;
		this.evalsEarly = evalsEarly;
		this.op6Bits = (byte)(32|ordinal());
	}
	
	public static byte nextOp6Bits(byte leftOp6Bits, byte leftCurriesMore, byte rightOp6Bits, byte rightCurriesMore, boolean rightIsClean){
		//FIXME did i miss a deeplazy case?
		//FIXME did i miss a case (maybe leftCurriesMore==1 ?) when at its last param it starts to eval but keeps its op6Bits?
		if(leftCurriesMore == 0 || rightCurriesMore == 0) return 0; //deeplazy
		if(leftOp6Bits < 32){ //has 0..4 params so hasnt chosen an op yet at 5th param
			boolean rightIsCleanLeaf = (rightOp6Bits==1 && rightIsClean);
			return rightIsCleanLeaf ? (byte)(leftOp6Bits<<1) : (byte)((leftOp6Bits<<1)+1);
		}else{
			return leftOp6Bits; //copy op6Bits from left child after its known at param 5
		}
	}
	
	/** The op of 1 less curry, if it already has 1..5 curries,
	or the op of curriesSoFar-4 number of less curries if curriesSoFar>5,
	since op6bits is known at 5 curries and copied from left child after that. 
	There is no prev op before cleanLeaf or before dirtyLeaf. There are many possible prev ops before a deeplazy.
	*/
	public static byte prevOp6Bits_ignoreIfParamIsDeeplazyOrLeafOrCurriessofarGreaterthan5(byte op6Bits){
		return (byte)(op6Bits>>1);
	}
	
	public static void main(String[] args){
		if(Op.values().length != 32) throw new RuntimeException("Must be exactly 32 ops but is "+Op.values().length);
		for(Op op : Op.values()){
			System.out.println(op+"("+op.curriesAll+") aka 5+1+"+(op.curriesAll-6));
		}
	}
	
	public static final boolean lgManyEvalsOnNextCurryWhenBoot = false;
	
	/**
	if(funcCurriesMore > 1 || func.curriesSoFar() < Marklar106bId.opIsKnownAt){
		//If 0..4 params has curriesAll of 5, which is when the Op is known,
		//then curriesAll will be 1 before Op is known (at curriesSoFar==4).
		//If instead set that to 6, which includes comment,
		//then wouldnt need a separate check for curriesSoFar==4.
	if(funcCurriesMore > 1){
		//forkEdit to append another param cuz not enough params to eval yet
		ret = cp(func,param);
	}else{
		//eval. funcCurriesMore==1.
		byte funcOp6bits = func.op6Bits();
		int ordinal = funcOp6bits&
		Op op = Op.ordinalToOp(paramCurriesMore)
	}
	*/
	//public static final byte curriesAll_beforeKnowWhichOp = (byte)127;
	//public static final byte curriesAll_beforeKnowWhichOp = (byte)6;
	public static final byte curriesAll_beforeKnowWhichOp = (byte)5; //opIsKnownAt==5
	
	private static final byte[] op6Bits_to_curriesAll;
	private static final Op[] ordinalToOp;
	private static final long op6BitsTo_evalsOnNextCurry_64Bits; //FIXME need to know curriesMore==1 in some cases
	static{
		long evalsOnNextCurry = 0;
		ordinalToOp = Op.values();
		op6Bits_to_curriesAll = new byte[64];
		//op6Bits_to_curriesAll[0]==0 is deeplazy
		//At fifth param, know which Op. 6th param is comment, then params of the op.
		for(int i=1; i<32; i++) op6Bits_to_curriesAll[i] = curriesAll_beforeKnowWhichOp;
		for(Op o : Op.values()) op6Bits_to_curriesAll[32+o.ordinal()] = o.curriesAll;
		for(byte o6=0; o6<64; o6++){ //TODO swap big/little endian? this is confusing.
			boolean is4CurriesOfLeaf = 16<=o6 && o6<32;
			if(is4CurriesOfLeaf || (32<=o6 && (ordinalToOp[o6-32]).evalsEarly)){	
				evalsOnNextCurry |= (1L<<o6);
			}			
		}
		op6BitsTo_evalsOnNextCurry_64Bits = evalsOnNextCurry;
		if(lgManyEvalsOnNextCurryWhenBoot) for(byte o6=0; o6<64; o6++){
			for(byte curriesMore=1; curriesMore<5; curriesMore++){
				lg("evalsOnNextCurry("+o6+","+curriesMore+")="+evalsOnNextCurry(o6,curriesMore)+" "+(o6>=32 ? ordinalToOp[o6-32] : ""));
			}
		}
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	public static byte op6Bits_to_curriesAll(byte op6Bits){
		return op6Bits_to_curriesAll[op6Bits];
	}
	
	/** same as Op.values()[ordinal] but makes sure to avoid creating such an array each time,
	since someone may modify the contents of such an array so JVM likely copies it every time,
	unless maybe JVM knows no code writes it, but I dont want to risk JVM not looking for that optimization. 
	*/
	public static Op ordinalToOp(int ordinal0To31){
		return ordinalToOp[ordinal0To31];
	}
	
	/** includes evalsEarly and curriesMore. If curriesMore==1 and curriesAll==5 then its about to choose which op,
	so FIXME choose a design about that, does that count as evaling or not, since it always instantly changes
	to another op and is instantly halted.
	*/
	public static boolean evalsOnNextCurry(byte op6Bits, byte curriesMore){
		return curriesMore==1 || (((1L<<op6Bits)&op6BitsTo_evalsOnNextCurry_64Bits)!=0);
	}

}



/*marklar106b id:
8 bits of magic/isliteralcbt256 (if it starts with 111110 then it is itself.
	11111000 is normal id. the next +1 +2 +3 are id of id of id of that)
1 bit containsAxconstraint.
1 bit isclean.
6 bits of opWithBinheapIndexElse0MeansDeeplazy.
1 bit containsBit1.
7 bits of curriesSoFar.
40 bits of lowBitsOfBize, so efficiently up to 1 terabit aka 128gB, and if powOf2 sized up to 2^120 bits.
192 bits of hashOrLiteral. //TODO which end of this does the literal go at?
*/

//"*a such as marklar105a and marklar106a should always be a very simple and not practically efficient kind of id, for academic proofs, tutorials, etc"
//"TODO name this id type maybe its marklar106b? (havent built the other marklar106b so just remove those comments)"

/*TODO default id type...
8 bits of magic/isliteralcbt256 (if it starts with 111110 then it is itself.
11111000 is normal id. the next +1 +2 +3 are id of id of id of that)
max cbt<2^120>, 40bits of bize so up to 128gB,
1 bit ignore.
7 bits of curriesMore (or curriesSoFar? choose 1),
5 bits of op,
3 bits of masks (containsAx.. containsBit1 isclean)
40 bits of bize, so up to 128gB aka 1 terabit.
*/

/*Should there be deeplazy op, and should it go at op 0, and should there be binheap indexing so 1 more bit,
so 6 bits instead of 5, vs should it just pad with whatevers the r() if you go deeper into leaf, and shift it, in 5 bits?
There is that "1 bit of ignore" so can spare 1 more bit.

/** the first 5 ops choose which of the 32 ops *
_chooser(5),
*/








/*isCleanLeaf,

isDirtyLeaf,

asClean,

asDirty,

cleanCall,
*

getComment,

setComment,
*/



/*infcur,

/** returns cbt8 *
curriesAll,
*/


/* wikibinator105 as of 2021-2-25:
_deeplazy
_root
_chooser
_Chooser
wiki
Wiki
isLeaf
IsLeaf
getFunc
GetFunc
getParam
GetParam
tru
Tru
fal
Fal
pair
Pair
trecurse
Trecurse
blob
Blob
isclean
Isclean
curryOrInfcurOrTypeval
CurryOrInfcurOrTypeval
ax
Ax
fpr
Fpr
*/

/** recursively forkEdits everything below to dirty form (vs clean).
Theres no asclean op cuz all you need to do is call clean identityFunc (fal u) on it,
since any clean called on any dirty forkEdits the dirty to clean first.

TODO Could derive this and optimize it with Evaler.java instance?
*
asdirty(1),
*
TODO recursive all, vs recursive all except cbts, vs recursive all except cbts and growinglists of them, vs nonrecursive?
Maybe best to derive those.
*
_reservedForFutureExpansion_(121),
//TODO room for 1 more op. _deeplazy? _chooser?
*/


/*TODO max cbt<2^56>, 43 bits of bize so up to 1tB,
6 bits of curriesMore,
5 bits of op,
3 bits of masks (containsAx.. containsBit1 isclean)
7 bits of magic/isliteralcbt256???
	or stick with 8 of those and its up to 512gB aka 4 terabits aka 42 bits of bize?
*/
	

/*TODO

default id type is 256 bits, has these bytes:
isliteralcbt256_vs_normal_op, and any 32 bytes that dont start with 0xf8 0xf9 0xfa and 0xfb are themself as their id.
dont need curriesall since its known in the 5 bits of op.
so could technically have 41 bits of bize. can i get that up to 43 or 48?
could have 48 bits of bize if have max 128 bits of literal per id256,
and that kind of id would be more efficient to use directly.

Make it come with 2 kinds of ids: Marklar106c and Marklar106d.
Marklar106c is a 256 bit id type that has low 32 bits of bize and usually can fit 256 bits of literal in a 256 bit id
and the only time it cant is if the first byte is 0xfb cuz the id of that requires 2 of cbt128.
and marklar106d has 48 bits of bize but max 128 bits of literal per id.
*/

/** the 6 op bits in low bits of a byte, that choose between 32 ops, 31 possible leaf/anynonleaf prefixes of them, and 0/deeplazy.
That describes 0..5 params, and every param after that copies those same 6 bits from its left child.
*
public static byte op6Bits(Op o){
	return (byte)(32|o.ordinal());
}*/
package wikibinator106.spec;

/** 32 ops, each with a clean form and a dirty form, so theres actually 64 ops.
The clean form of an op starts with a lowercase letter, and dirty form is capital, like wiki vs Wiki.
These are chosen in the first 5 params each being cleanLeaf vs anything_except_cleanLeaf,
then a comment param (which can be anything and is ignored other than by reflection ops),
then the params of that op. The total number of params of leaf is at most 127 so fits in unsigned byte.
*/
public enum Op{
	
	
	/*marklar106b id:
		8 bits of magic/isliteralcbt256 (if it starts with 111110 then it is itself.
			11111000 is normal id. the next +1 +2 +3 are id of id of id of that)
		1 bit containsAxof2params.
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
	
	
	/** (axa (fpr wiki x y)) means (wiki x)->y.
	Also, there will be a few functions built in, something like
	(curry... wiki "spend" salt maxAmountToSpendAsInt64 x) -> [amountDidNotSpend (x cleanLeaf)], or something like that.
	(curry... wiki "wallet" salt) -> how much is left for spend calls etc, as int64, or something like that.
	(curry... solve x) -> y where (ax (fpr x y cleanLeaf)).
	64 bit local ids of things, actually global ids but with some prefix so nobody else would randomly choose it?
	*/
	wiki(1),
	
	/** Ax, like in axiom, but only axioms that if true can be verified in some finite time,
	and if false may take infinite time to disprove as claiming that a certain lambda call halts but it actually doesnt,
	since of course halting-oracles are logically impossible since they claim that can always be done in finite time. 
	This is how constraints and turingCompleteTypes and turingCompleteChallengeResponse is done.
	The turing complete type system is on the return types, while always allowing all possible params regardless of type,
	so its not exactly untyped lambdas or typed lambdas by the existing models of lambedas.
	<br><br>
	This, and zero and one, are the only ops that eval before it has all its params,
	and zero and one always halt instantly when they do that, but this may take up to infinite time (halting problem related).
	It evals at 2 params to verify constraint,
	and if not verified then it infinite loops (evals to (S I I (S I I))) so it cant exist if
	the statement it represents (such as "(x u)->u") is not true.
	<br><br>
	(ax u x) is halted if (x u)->u.
	(ax anything_except_u x) is halted if (x u) -> something except u.
	(ax u x y) -> (x (tru y)).
	(ax anything_except_u x y) -> (x (fal y)).
	If its Ax (dirty form) instead of ax (clean form) then its Tru and Fal instead of tru and fal.
	(ax u) is called axa. (ax (u u)) is called axb, and technically so is (ax "hello") since "hello" is not u.
	<br><br>
	The default kind of id has a bit for containsAxof2params,
	which is 1 if recursively theres any (ax u x) or (ax (u u) x) or (Ax "hello" y) etc,
	BUT its not referring to ax by itself or (ax u) by itself or (ax (u u)) by itself etc.
	*/
	ax(3),
	
	/** Every object is a 2-way forest with 1 bit of data at each node, that bit being isCleanVsDirty,
	and all paths lead to cleanLeaf or dirtyLeaf, and a parent must be dirty if either of its 2 childs is dirty,
	and if both its childs are clean then the parent can be clean or dirty,
	and if a clean is called on a dirty then that dirty is truncateToClean (call (fal u) on it, aka clean identityFunc)
	before the clean sees it.
	*/
	isclean(1),
	
	/** (growinglist x y z) -> (growinglist (growinglist x y) z).
	This is mostly here so Op.zero and Op.one can keep acting like bitstrings above 2^120 bits,
	as a cbt called on anything is a cbt twice as big,
	and 2 cbts size 2^120 bits one called on the other returns a growinglist containing those 2, and so on,
	and some funcs might be designed to look for growinglist AND cbts instead of just CBTs,
	but probably the biggest cbt anyone will practically use fits in a long,
	unless they're trying to model some sparse space like the whole state space of ethereum
	or all possible universe states, but even then its probably better to use some other datastruct
	cuz cbt is as deep as log of its size, which can get very deep if its very sparse.
	*/
	growinglist(3),
	
	isleaf(1),

	getfunc(1),
	
	getparam(1),
	
	tru(2),
	
	fal(2),
	
	/** Bitstrings up to 2^120-1 bits. The last 1 bit is the first bit of padding.
	The default kind of id is a 256 bit id and stores the low 32 bits of bize (bitstring size),
	so if you want an int64 or int128 bitstring size you have to compute it in interpred mode as lambdas,
	or derive a 512 bit id that has more room for bize.
	<br><br>
	(one_or_zero param1 param2 ... param120 param121) -> (growinglist (one_or_zero param1 param2 ... param120) param121),
	other than up to param120 every param is verified to be a cbt of same size else evals self on self,
	so get a cbt twice as big.
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
	*/
	one(121),
	zero(121),
	
	pair(3),
	
	/** (typeval x y z)->(y z). Normally just keep it as (typeval x y)
	such as (typeval "image/jpeg" ...bytesofjpgfile...) as a semantic.
	If you want turingComplete types, use Op.axa and Op.axb.
	*/
	typeval(3),
	
	trecurse(3),
	
	/** (fpr func param ret u) -> u if (func param)->ret, else -> (u u), where u is cleanLeaf.
	Example: (axa (fpr ["hello" "world"] fal "world")) cuz (["hello" "world"] fal) -> "world".
	Example: (axb (fpr ["hello" 235] fal "world")) cuz (["hello" 235] fal) -not-> "world" aka does not return "world",
	so in that case (fpr ["hello" 235] fal "world" u) -> (u u).
	*/
	fpr(4),
	
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
	curry1(1), curry2(2), curry3(3), curry4(4),
	curry5(5), curry6(6), curry7(7), curry8(8),
	curry9(9), currya(10), curryb(11), curryc(12),
	curryd(13), currye(14), curryf(15), curry16(16);
	
	/** evaling. This kind of *
	_deeplazy(0);
	*/
	
	public final byte params;
	
	private Op(int params){
		if(params < 1 || params > 121) throw new RuntimeException("param = "+params);
		this.params = (byte)params;
	}
	
	
	
	
	
	
	
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
	

}

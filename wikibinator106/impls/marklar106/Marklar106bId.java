package wikibinator106.impls.marklar106;

import immutable.util.HashUtil;
import immutable.util.MathUtil;
import immutable.util.Text;
import wikibinator106.spec.Op;

/**
 * This kind of 256 bit id uniquely identifies a lambda function, in data at
 * rest or p2p network. In this system, the lambda, instead of the bit, is the
 * smallest unit of computing, and they are streamed at gaming-low-lag between
 * many computers, GPU, CPU, etc, in theory. You dont need an id for a lambda
 * just to use it, as the id is lazy evaled, but you do need some of the ids to
 * cross untrusted borders, and can derive other ids on the other side. Ids are
 * generated deterministicly from their 2 child ids, or the id of the leaf, and
 * for every node there is a clean form and a dirty form, so theres cleanLeaf
 * and dirtyLeaf. <br>
 * <br>
 * UPDATE: Here's the id256 datastruct (and can derive new kinds of ids at
 * runtime but got to start somewhere): -- 8 bits of magic/isliteralcbt256 (if
 * it starts with anything except 111110 then its 256 literal bits that are
 * their own id, 11111000 is normal id. the next +1 +2 +3 are id of id of id of
 * that) -- 1 bit containsAxconstraint. -- 1 bit isclean. -- 6 bits of
 * opWithBinheapIndexElse0MeansDeeplazy. -- 1 bit containsBit1. -- 7 bits of
 * curriesMore. -- 40 bits of lowBitsOfBize, so efficiently up to 1 terabit aka
 * 128gB, and if powOf2 sized up to 2^120 bits. -- 192 bits of hashOrLiteral.
 * //TODO which end of this does the literal go at? <br>
 * <br>
 * TODO I might still reorder the parts of the id, and make small changes, as of
 * 2021-2-25, but its very close to what I want and will finalize it soon, TODO,
 * after which any new id type or opcodes would be named something other than
 * wikibinator106 and this id type marklar106b.
 */
public class Marklar106bId{
	private Marklar106bId(){}
	
	/** Only the last 192 bits of this are used. Change this, for example to "SHA256", and it will work in CPU,
	but any GPU optimizations of it would not change algorithm with it automatically unless TODO those
	are generated from lambdas at runtime in custom JIT compiler I'm making for wikibinator106.
	*/
	public static final String defaultHashAlgorithm = "SHA3-256";
	
	public static final String nameOfUniversalFunction = "wikibinator106";
	
	public static final String nameOfIdType = "marklar106b";
	
	public static final String appendTo2ChildIdsToMakeHashContent_string = "_"+nameOfUniversalFunction+"_"+nameOfIdType;
	
	/** This causes ids, other than cbt/bitstring literals that fit in an id, to differ for different nameOfUniversalFunction and nameOfIdType,
	so any proofOfWork done in other systems does not help in cracking this id algorithm.
	You'd have to compute and store about 2^96 ids before finding 1 collision, since it has 192 bits of hash or is a literal,
	and if such a collision is found, the proof of the collision will have the nameOfUniversalFunction and nameOfIdType in it,
	even though those arent stored with the ids, just 32 bytes each and their 2 childs, its part of hash algorithm.
	192 bits of hash are the last 192 bits of
	MessageDigest.getInstance(defaultHashAlgorithm).digest(concat(idOfLeftChild,idOfRightChild,appendTo2ChildIdsToMakeHashContent)).
	<br><br>
	This only costs as much compute time as copying this once, and does not change the hashing time,
	since SHA3-256 has a constant size input, and runs more cycles for bigger inputs. hashContentBytes is verified to fit in 1 SHA3-256 cycle.
	*/
	private static final byte[] appendTo2ChildIdsToMakeHashContent = Text.stringToBytes(appendTo2ChildIdsToMakeHashContent_string);
	
	public static final int hashContentBytes = 32*2+appendTo2ChildIdsToMakeHashContent.length;
	static{
		if(defaultHashAlgorithm.toLowerCase().replace("-","").equals("sha3256")){
			if(hashContentBytes > 135) throw new RuntimeException(
				"hashContentBytes="+hashContentBytes+" but for efficiency should be at most 135 bytes cuz thats 1 SHA3-256 cycle.");
		}else{
			System.out.println("Warning: hashContentBytes="+hashContentBytes+" and dont know if "+defaultHashAlgorithm
				+" hash algorithm costs more than 1 hash cycle (for efficiency) for that size, and if it does,"
				+ " could shrink that by using a smaller nameOfIdType="+nameOfIdType+" or if its a new universal function"
				+" then could choose a shorter nameOfUniversalFunction="+nameOfUniversalFunction);
		}
	}
	
	/** If the first byte is anything except 0xf8, 0xf9, 0xfa, or 0xfb, then its a literal 256 bits that are their own id,
	so most random 256 bits are their own id (63/64 of the time),
	and even more often (255/256 of the time) the 256 bits of random content that an id refers to can be derived from that id,
	by it either already being its own id or in 3/256 possible cases subtract 1 from its first byte.
	<br><br>
	If its 0xf8, then its a normal call pair id or literal 1..128 bits.
	If its 0xf9, then its the id of literal 256 bits whose first 8 bits are 0xf8.
	If its 0xfa, then its the id of literal 256 bits whose first 8 bits are 0xf9.
	If its 0xfb, then its the id of literal 256 bits whose first 8 bits are 0xfa.
	The id of a 256 bits whose first 8 bits are 0xfb, is the id of parent of 2 of 128 bits
	one lambda called on the other which halts instantly on those being its left and right childs.
	*/
	public static final long maskDatastructType = 0xff00000000000000L;
	public static final int shiftDatastructType = 56;
	
	public static final long maskContainsAxConstraint_ignoreIfLiteralCbt256 = 0x0080000000000000L;
	
	public static final long maskIsClean_ignoreIfLiteralCbt256 = 0x0040000000000000L;
	
	/** these bits op6Bits(Op) which is known at 5 params (and higher, copied from left child),
	and values 1..31 are 0..4 params, and 0 means deeplazy. See op6Bits(Op).
	*/
	public static final long maskOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256 = 0x003f000000000000L;
	public static final int shiftOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256 = 48;
	
	public static final long maskContainsCleanNormedBit1_ignoreIfLiteralCbt256 = 0x0000800000000000L;
	
	public static final long maskCurriesMore_ignoreIfLiteralCbt256 = 0x00007f0000000000L;
	public static final int shiftCurriesMore_ignoreIfLiteralCbt256 = 40;
	
	public static final long maskLow40BitsOfBize_ignoreIfLiteralCbt256 = 0x000000ffffffffffL;
	//public static final int shiftLow40BitsOfBize_ignoreIfLiteralCbt256 = 0;
	
	public static final long firstByteAs1 = 1L<<56;
	
	/** If it has at least this many params and is halted, then which Op is known */
	public static final byte opIsKnownAt = 5;
	
	//Leaf is param index 0. Max possible halted param index is 126 or less depending which op.
	//public static final byte leafAt = 0;
	
	public static final byte commentAt = 6;
	
	public static final byte smallestCbtAt = commentAt;
	
	public static final byte firstParamAt = commentAt+1;
	
	/** Which param index is funcBody at, for all of Op.curry1 .. Op.curry16 */
	public static final byte funcBodyAt = firstParamAt;
	
	public static final byte curriesMoreOfCbt1 = Op.zero.params;
	public static final byte curriesMoreOfCbt256 = (byte)(curriesMoreOfCbt1-8);
	
	public static final byte zero6Bits = op6Bits(Op.zero);
	public static final byte one6Bits = op6Bits(Op.one);
	public static final byte axa6Bits = op6Bits(Op.axa);
	public static final byte axb6Bits = op6Bits(Op.axb);
	
	public static boolean isLiteral256BitsThatIsItsOwnId(long header){
		return (header>>>58)!=0b111110;
	}
	
	/** else is [normal call pair id or literal 1..128 bits] */
	public static boolean isLiteral256Bits(long header){
		return (header>>>56)!=0b11111000;
	}
	
	public static boolean isLiteral256BitsThatIsTheIdOfItselfExceptSubtract1FromFirstByte(long header){
		//TODO optimize. Is there a faster way than checking all 3?
		byte firstByte = (byte)(header>>>56);
		return firstByte==(byte)0b11111001 || firstByte==(byte)0b11111010 || firstByte==(byte)0b11111011;
	}
	
	/** see isLiteral256BitsThatIsTheIdOfItselfExceptSubtract1FromFirstByte(header) */ 
	public static long subtract1FromFirstByte(long header){
		return header-firstByteAs1;
	}
	
	/** see isLiteral256BitsThatIsTheIdOfItselfExceptSubtract1FromFirstByte(header) */
	public static long add1ToFirstByte(long header){
		return header+firstByteAs1;
	}
	
	/** FIXME is it leftC, depends on "TODO which end of this does the literal go at?".
	Bizb is low 8 bits of bize and is only needed for literalCbt256 since its header is literal data so doesnt contain biz40.
	*/
	public static long parentHeader(
			boolean isClean,
			long leftHeader, byte leftBizb, long leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128,
			long rightHeader, byte rightBizb){
		//FIXME this incomplete code copied from Marklar105bId
		//FIXME consider deeplazy as op6bits==0
		if(isClean & (!isClean(leftHeader) || !isClean(rightHeader)))
			throw new RuntimeException("Cant be clean if either child is dirty. If both childs are clean, parent can be clean or dirty.");
		boolean leftIsLiteralCbt128 = isLiteralCbt128(leftHeader);
		boolean rightIsLiteralCbt128 = isLiteralCbt128(rightHeader);
		//Cbt called on anything is cbt twice as big, by if its param is anything except a cbt of same size
		//its return value is instead itself called on itself, but thats an eval, and before that it would be Op._deeplazy.
		//So here, its only a cbt256 if both its childs are cbt128 and if the first byte of left's content is not 0xf9.
		boolean parentIsLiteralCbt256 = leftIsLiteralCbt128 & rightIsLiteralCbt128
			& isLiteral256Bits(leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128); //check for 0xf9
		if(parentIsLiteralCbt256) return leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128;
		byte leftCurriesMore = curriesMore(leftHeader);
		byte rightCurriesMore = curriesMore(rightHeader);
		boolean leftIsHalted = leftCurriesMore>0;
		boolean rightIsHalted = rightCurriesMore>0;
		byte curriesMore = (leftIsHalted&rightIsHalted) ? (byte)(leftCurriesMore-1) : (byte)0; //7 bits
		//boolean parentIsHalted = curriesMore>0;
		boolean containsAxconstraint = containsAxConstraint(leftHeader) | containsAxConstraint(rightHeader) | isAxaOrAxb(leftHeader);
		byte leftOp6Bits = op6Bits(leftHeader);
		byte rightOp6Bits = op6Bits(rightHeader);
		byte op6bits = Op.nextOp6Bits(leftOp6Bits, leftCurriesMore, rightOp6Bits, rightCurriesMore);
		//FIXME know !parentIsLiteralCbt256, but what if left or right is?
		boolean containsCleanNormedBit1 = containsCleanNormedBit1_ignoreIfLiteralCbt256(leftHeader)
			|| containsCleanNormedBit1_ignoreIfLiteralCbt256(rightHeader)
			//check if parent is cleanNormedBit1. curriesSoFar of a cbt1 is 6 (opIsKnownAt+1) cuz it includes comment==cleanLeaf.
			|| (leftOp6Bits==one6Bits && curriesSoFar(leftHeader)==opIsKnownAt);
		//low 40 bits of bize (bitstring size). bize ranges 0..(2^120-1).
		//Bize is position of last 1 bit in content, or 0 if there is no 1 bit. Content is concat(leftCbt,rightCbt).
		//FIXME use the params leftBizb and rightBizb?
		long leftBiz40 = leftHeader&maskLow40BitsOfBize_ignoreIfLiteralCbt256;
		long rightBiz40 = rightHeader&maskLow40BitsOfBize_ignoreIfLiteralCbt256;
		long biz40; //if !isCbt or if its 1000000...000 or if its all 0s.
		boolean isCbt = isCbt(leftHeader) && isCbt(rightHeader) && leftCurriesMore==rightCurriesMore;
		if(isCbt){
			byte leftCbtHeight = (byte)(curriesSoFar(leftHeader)-smallestCbtAt); //cbt size is 1<<cbtHeight
			if(containsCleanNormedBit1_ignoreIfLiteralCbt256(rightHeader)){
				//add leftCbtSize==rightCbtSize. The last 1 bit is in right child.
				//cbtSize has to be an int120 to hold all the digits, but since only computing low 40 bits of it, use a long.
				biz40 = ((1L<<leftCbtHeight)+rightBiz40)&maskLow40BitsOfBize_ignoreIfLiteralCbt256;
			}else{ //right child is all 0s so the last 1 bit (if exists) is in left child so has same bize as left child
				biz40 = leftBiz40;
			} 
			//Is concat(cbt1,cbt1) up to concat(cbt<2^119>,cbt<2^119).
			//FIXME if its a call of cbt<2^120> on cbt<2^120> then that evals to (Op.growinglist cbt<2^120> cbt<2^120>)
			//but before that the op6bits here is still op6bits(Op.zero) or op6bits(Op.one) but its curriesMore==0
			//and therefore has 2 cbt<2^120> childs and is not halted.
		}else{
			biz40 = 0; //if !isCbt or if its 1000000...000 or if its all 0s.
		}
		return headerOfFuncall(containsAxconstraint, isClean, op6bits, containsCleanNormedBit1, curriesMore, biz40);
	}
	
	public static long headerOfFuncall(
		boolean containsAxconstraint,
		boolean isClean,
		byte opWithBinheapIndexElse0MeansDeeplazy_6bits,
		boolean containsCleanNormedBit1,
		byte curriesMore_7bits,
		long lowBitsOfBize_40bits
	){
		return (containsAxconstraint ? maskContainsAxConstraint_ignoreIfLiteralCbt256 : 0)
			| (isClean ? maskIsClean_ignoreIfLiteralCbt256 : 0)
			| ((((long)opWithBinheapIndexElse0MeansDeeplazy_6bits)<<shiftOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256)&maskOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256)
			| (containsCleanNormedBit1 ? maskContainsCleanNormedBit1_ignoreIfLiteralCbt256 : 0)
			| ((((long)curriesMore_7bits)<<shiftCurriesMore_ignoreIfLiteralCbt256)&maskCurriesMore_ignoreIfLiteralCbt256)
			| (lowBitsOfBize_40bits&maskLow40BitsOfBize_ignoreIfLiteralCbt256);
	}
	
	/** until gets 5 params, dont know which Op (32 of them so 5 bits) but do know op6bits which has a binheap index
	that means a bitstring of 0..5 bits, stored in 6 bits, and deeplazy is op6bits==0.
	*/
	public static final long headerOfCleanLeaf = headerOfFuncall(false, true, (byte)1, false, (byte)5, 0L);
	
	public static final long headerOfDirtyLeaf = headerOfCleanLeaf^maskIsClean_ignoreIfLiteralCbt256;
	
	public static boolean isLeaf(long header){
		return op6Bits(header)==1;
	}
	
	/** evaling, not halted. Either way its an immutable/stateless snapshot of a tiny piece of computing.
	Not everything thats evaling/!isHalted is deeplazy. The first is just lazy, which happens at the last param,
	such as (S x y z) before it evals to (x z (y z)). S aka (Op.trecurse comment), and default comment is cleanLeaf.
	Deeplazy is what happens past that to prevent any op from having more than 127 params (fits in unsigned byte).
	When evaling, you dont yet (or ever, cuz of halting-problem) know the op6bits of what it will return,
	so we view it like its just 1 curry as lambdas normally are, x called on y.
	FIXME is curriesSoFar andOr curriesMore andOr curriesAll consistent with that?
	*/
	public static boolean isDeeplazy(long header){
		return op6Bits(header)==0;
	}
	
	/** Op is known if its halted and has at least 5 params/curries */
	public static boolean opIsKnown(long header){
		return isHalted(header) && curriesSoFar(header)>=5;
	}
	
	public static boolean isHalted(long header){
		return curriesMore(header)>0;
	}
	
	public static boolean isClean(long header){
		return isLiteral256Bits(header) || (header&maskIsClean_ignoreIfLiteralCbt256)!=0;
	}
	
	public static boolean isCleanLeaf(long header){
		//TODO optimize by inlining code from these funcs and merging duplicate code
		return isLeaf(header) && isClean(header);
	}
	
	public static boolean isDirtyLeaf(long header){
		//TODO optimize by inlining code from these funcs and merging duplicate code
		return isLeaf(header) && !isClean(header);
	}
	
	public static boolean isCleanCbt(long header){
		//true if its a literal cbt256 or...
		throw new RuntimeException("TODO");
	}
	
	public static boolean isAxOf2Params(long header){
		throw new RuntimeException("TODO check isLiteral256Bits and if op is Op.ax and curriesSoFar is 8");
	}

	public static boolean isCleanNormedBit1(long header){
		throw new RuntimeException("TODO check isLiteral256Bits and if op is Op.one and curriesSoFar is 6");
	}
	
	public static byte curriesAll(long header){
		return isLiteral256Bits(header)
			? Op.zero.curriesAll //same as Op.one.curriesAll
			: Op.op6Bits_to_curriesAll(op6Bits(header));
	}
	
	public static byte curriesSoFar(long header){
		//TODO optimize by inlining both funcs code here and merging the duplicate code
		return (byte)(curriesAll(header)-curriesMore(header));
	}
	
	public static byte curriesMore(long header){
		return isLiteral256Bits(header)
			? curriesMoreOfCbt256
			: (byte)((header&maskCurriesMore_ignoreIfLiteralCbt256)>>shiftCurriesMore_ignoreIfLiteralCbt256);
	}
	
	/** true if its (axa anything) or (axb anything), either clean or dirty form of those */
	public static boolean containsAxConstraint(long header){
		return !isLiteral256Bits(header) && (header&maskContainsAxConstraint_ignoreIfLiteralCbt256)!=0;
	}
	
	/** true if its axa or axb, either clean or dirty form of those.
	This is used on a left child to check if containsAxConstraint is true of parent,
	such as while parent is being created need to compute the containsAxConstraint bit in header.
	*/
	public static boolean isAxaOrAxb(long header){
		byte o6 = op6Bits(header); 
		return (o6==axa6Bits || o6==axb6Bits) && curriesSoFar(header)==opIsKnownAt;
	}

	public static boolean containsCleanNormedBit1_ignoreIfLiteralCbt256(long header){
		return (header&maskContainsCleanNormedBit1_ignoreIfLiteralCbt256)!=0;
		//return !isLiteral256Bits(header) && (header&maskContainsCleanNormedBit1_ignoreIfLiteralCbt256)!=0;
	}
	
	/** does it deeply anywhere contain Op.one aka clean bit 1, even if its not a cbt it may still contain that *
	public static boolean containsBit1(long header, byte liz){
		//FIXME this incomplete code copied from Marklar105bId
		
		return liz != 0 || (header&containsCleanNormedBit1_optimization) != 0; //optimization of the commentedout code below
		/*if(liz != 0){
			return true;
		}else if(isLiteralCbt256(header)){ //liz==0 so its either 256 0s or 1 then 255 0s
			return header!=0;
		}else{
			return (header&maskContainsBit1_ifFirstByteIsNotF9) != 0;
			//FIXME go down from 4tB to 2tB so these 3 bits are part of all non-isLiteralCbt256 headers,???
			//instead of having to check if it uses the cbtHeightAndBize46???
		}*
	}*/
	
	
	/** "FIXME this incomplete code copied from Marklar105bId".
	<br><br>
	Eager eval of id256. Usually lazyeval is best.
	Returns long[4], a marklar105b id deterministicly generated from 2 child marklar105b ids.
	Should take a few microseconds for one of these, or a few nanoseconds in GPU for many (id,id)->id in parallel,
	so when GPU gives you a large primitive array,
	it can also give you the id of that array (in log number of kernel calls) or not,
	which should be fast enough to, if you wanted (not necessary),
	generate global id of every frame of video (1/60 second) in a FPS game and screen blits etc,
	or at least it could at low resolution. Will have to experiment with it to know exactly how fast.
	Default hashAlgorithm is "SHA3-256", and whatever it is, only the last 192 bits are used.
	TODO would it be more secure to XOR in the other 64 bits into all 3 of the other 64 64 64 bits?
	*/
	public static long[] parentId(
			String hashAlgorithm,
			boolean isClean,
			long leftHeader, long leftB, long leftC, long leftD,
			long rightHeader, long rightB, long rightC, long rightD){
		
		//FIXME this incomplete code copied from Marklar105bId
		
		long header = parentHeader(
			isClean,
			leftHeader, lizOfId(leftHeader,leftB,leftC,leftD), leftC,
			rightHeader, lizOfId(rightHeader,rightB,rightC,rightD)
		);
		if(isLiteral256Bits(header)){ //(cbt128,cbt128)->cbt256 by concat. header==leftC. Most random 256 bits are their own id.
			assert header==leftC; //FIXME when are JVM assert turned on?
			return new long[]{ leftC, leftD, rightC, rightD };
		}else{
			//dont know its a cbt yet, but compute cbtHeights in case it is
			
			byte leftCurriesSoFar = curriesSoFar(leftHeader);
			byte rightCurriesSoFar = curriesSoFar(leftHeader);
			byte leftCbtHeight = (byte)(leftCurriesSoFar-smallestCbtAt); //cbt size is 1<<leftCbtHeight
			
			//int leftCbtHeight = heightIf15(leftHeader)-minHeightOfCbt; //number of bits in cbt is 2^cbtHeight, including padding.
			//int rightCbtHeight = heightIf15(rightHeader)-minHeightOfCbt;
			//cbt called on anything is cbt twice as big, so if they're different heights it has to eval that first,
			//which is a bigO(1) eval to just call left on left, but this parentId function was given the 2 childs,
			//and if those 2 childs cant be together in a halted state as they are, it has to be marked as Op._deeplazy.
			if(isLiteralCbt1To128(header) && leftCurriesSoFar == rightCurriesSoFar){
				//TODO optimize by not using switch here, but the switch might be useful for debugging for a while.
				long c = 0, d = 0;
				//cbt1 to cbt128 literals have all 0s between end of header and start of the content which ends at end of id256.
				switch(leftCbtHeight){
				case 0: //(cbt1,cbt1)->cbt2 by concat
					d = (leftD<<1)|rightD;
				break;
				case 1: //(cbt2,cbt2)->cbt4
					d = (leftD<<2)|rightD;
				break;
				case 2: //(cbt4,cbt4)->cbt8
					d = (leftD<<4)|rightD;
				break;
				case 3: //(cbt8,cbt8)->cbt16
					d = (leftD<<8)|rightD;
				break;
				case 4: //(cbt16,cbt16)->cbt32
					d = (leftD<<16)|rightD;
				break;
				case 5: //(cbt32,cbt32)->cbt64
					d = (leftD<<32)|rightD;
				break;
				case 6: //(cbt64,cbt64)->cbt128
					c = leftD;
					d = rightD;
				}
				return new long[]{ header, 0, c, d };
			}else{
				return id256FromParentHeaderConcatHash192_onlyUseIfNotLiteralCbt(header, hashAlgorithm,
					leftHeader, leftB, leftC, leftD,
					rightHeader, rightB, rightC, rightD);
			}
		}
	}
	
	/** returns id256 */
	public static long[] id256FromParentHeaderConcatHash192_onlyUseIfNotLiteralCbt(
			long header, String hashAlgorithm, long... in){
		
		//FIXME this incomplete code copied from Marklar105bId
		
		if(isLiteralCbt(header)) throw new RuntimeException("isLiteralCbt "+header);
		long[] ret = new long[4];
		ret[0] = header;
		hash192(hashAlgorithm,ret,1,in);
		return ret;
	}
	
	/** last 192 bits of hashAlgorithm. returns long[3] */
	public static long[] hash192(String hashAlgorithm, long... in){
		
		//FIXME this incomplete code copied from Marklar105bId
		
		long[] ret = new long[3];
		hash192(hashAlgorithm,ret,0,in);
		return ret;
	}
	
	/** last 192 bits of sha3_256. writes writeHere[offset..(offset+2)]. normally this is long[8] of 2 id256s.
	*/
	public static void hash192(String hashAlgorithm, long[] writeHere, int offset, long... in){
		byte[] hash = HashUtil.hash(hashAlgorithm,MathUtil.longsAndBytesToBytes(in,appendTo2ChildIdsToMakeHashContent));
		writeHere[offset] = MathUtil.readLongFromByteArray(hash, hash.length-24);
		writeHere[offset+1] = MathUtil.readLongFromByteArray(hash, hash.length-16);
		writeHere[offset+2] = MathUtil.readLongFromByteArray(hash, hash.length-8);
	}
	
	/** returns index of last 1 bit, or returns 0 if there is no 1 bit. Its correct bize either way. */
	public static long bizeOfContent(long... literal){
		for(int i=literal.length-1; i>=0; i--){
			long j = literal[i];
			if(j != 0) return (i*64L)+63-Long.numberOfTrailingZeros(j);
		}
		return 0;
	}
	
	public static long headerForPowOf2SizeContent(long... content){
		if((content.length&(content.length-1)) != 0) throw new RuntimeException("Not a powOf2 number of longs: "+content.length);
		throw new RuntimeException("TODO");
	}
	
	/** Low 8 bits of bIZe, but only works if its not a literal cbt256 */
	public static byte bizb_ignoreIfLiteralCbt256(long header){
		return (byte)header; //maskLow40BitsOfBize_ignoreIfLiteralCbt256 is low 40 bits
	}
	
	/** Low 8 bits of bIZe */
	public static byte lizOfId(long header, long b, long c, long d){
		if(isLiteral256Bits(header)){
			return (byte)Math.max(0,255-numberOfTrailingZeros(header, b, c, d));
		}else{
			return bizb_ignoreIfLiteralCbt256(header);
		}
	}
	
	/** same as Long.numberOfTrailingZeros(int256) would be. Ranges 0 to 256 */
	public static int numberOfTrailingZeros(long a, long b, long c, long d){
		if(d != 0) return (byte)(255-Long.numberOfTrailingZeros(d));
		if(c != 0) return (byte)(191-Long.numberOfTrailingZeros(c));
		if(b != 0) return (byte)(127-Long.numberOfTrailingZeros(b));
		if(a != 0) return (byte)(63-Long.numberOfTrailingZeros(a));
		return 256;
	}
	
	public static boolean isLiteralCbt1To128(long header){
		if(!isCbt(header)) return false;
		int cbtHeight = curriesSoFar(header)-smallestCbtAt;
		return 1 <= cbtHeight && cbtHeight <= 128;
	}
	
	public static boolean isLiteralCbt128(long header){
		return isCbt(header) && !isLiteral256Bits(header) && curriesSoFar(header)==(smallestCbtAt+7); //1<<7==128
	}
	
	public static boolean isLiteralCbt(long header){
		//TODO optimize. theres probably some duplicate code between those 2 funcs
		return isLiteral256Bits(header) || isLiteralCbt1To128(header);
	}
	
	/** the 6 op bits in low bits of a byte, that choose between 32 ops, 31 possible leaf/anynonleaf prefixes of them, and 0/deeplazy.
	That describes 0..5 params, and every param after that copies those same 6 bits from its left child.
	*/
	public static byte op6Bits(long header){
		if(isLiteral256Bits(header)){
			return header<0 ? one6Bits : zero6Bits; //first of 256 bits
		}else{
			return (byte)((header&maskOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256)
				>>shiftOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256);
		}
	}
		
	public static final byte op6Bits(Op o){
		return (byte)(32|o.ordinal());
	}
	
	public static boolean isCbt(long header){
		//TODO optimize using a mask that ignores the 1 bit where one6Bits and zero6Bits differ
		int op6Bits = op6Bits(header);
		return op6Bits==one6Bits || op6Bits==zero6Bits;
	}

}
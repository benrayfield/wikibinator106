package wikibinator106.impls.marklar106;

import immutable.util.HashUtil;
import immutable.util.MathUtil;
import wikibinator106.spec.Op;

/** This kind of 256 bit id uniquely identifies a lambda function, in data at rest or p2p network.
In this system, the lambda, instead of the bit, is the smallest unit of computing,
and they are streamed at gaming-low-lag between many computers, GPU, CPU, etc, in theory.
You dont need an id for a lambda just to use it, as the id is lazy evaled,
but you do need some of the ids to cross untrusted borders, and can derive other ids on the other side.
Ids are generated deterministicly from their 2 child ids, or the id of the leaf,
and for every node there is a clean form and a dirty form, so theres cleanLeaf and dirtyLeaf.
<br><br>
UPDATE: Here's the id256 datastruct (and can derive new kinds of ids at runtime but got to start somewhere):
-- 8 bits of magic/isliteralcbt256 (if it starts with anything except 111110 then its 256 literal
	bits that are their own id, 11111000 is normal id. the next +1 +2 +3 are id of id of id of that)
-- 1 bit containsAxof2params.
-- 1 bit isclean.
-- 6 bits of opWithBinheapIndexElse0MeansDeeplazy.
-- 1 bit containsBit1.
-- 7 bits of curriesSoFar.
-- 40 bits of lowBitsOfBize, so efficiently up to 1 terabit aka 128gB, and if powOf2 sized up to 2^120 bits.
-- 192 bits of hashOrLiteral. //TODO which end of this does the literal go at?
<br><br>
TODO I might still reorder the parts of the id, and make small changes, as of 2021-2-25,
but its very close to what I want and will finalize it soon, TODO,
after which any new id type or opcodes would be
named something other than wikibinator106 and this id type marklar106b.
*/
public class Marklar106bId{
	private Marklar106bId(){}
	
	/** Only the last 192 bits of this are used. Change this, for example to "SHA256", and it will work in CPU,
	but any GPU optimizations of it would not change algorithm with it automatically unless TODO those
	are generated from lambdas at runtime in custom JIT compiler I'm making for wikibinator106.
	*/
	public static final String defaultHashAlgorithm = "SHA3-256";
	
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
	
	public static final long maskContainsAxof2params_ignoreIfLiteralCbt256 = 0x0080000000000000L;
	
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
	
	/** If it has at least this many params and is halted, then which Op is known */
	public static final byte opIsKnownAt = 5;
	
	//Leaf is param index 0. Max possible halted param index is 126 or less depending which op.
	//public static final byte leafAt = 0;
	
	public static final byte commentAt = 6;
	
	public static final byte smallestCbtAt = commentAt;
	
	public static final byte firstParamAt = commentAt+1;
	
	/** Which param index is funcBody at, for all of Op.curry1 .. Op.curry16 */
	public static final byte funcBodyAt = firstParamAt;
	
	public static final byte one6Bits = op6Bits(Op.one);
	public static final byte zero6Bits = op6Bits(Op.zero);
	public static final byte ax6Bits = op6Bits(Op.ax);
	
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
	
	public static long subtract1FromFirstByte(long header){
		return header-0x0100000000000000L;
	}
	
	public static long add1ToFirstByte(long header){
		return header+0x0100000000000000L;
	}
	
	/** FIXME is it leftC, depends on "TODO which end of this does the literal go at?" */
	public static long parentHeader(
			boolean isClean,
			long leftHeader, byte leftLiz, long leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128,
			long rightHeader, byte rightLiz){
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
		//parentIsHalted == leftCurriesMore>1 && leftIsHalted && rightIsHalted
		byte curriesMore = (leftIsHalted&rightIsHalted) ? (byte)(leftCurriesMore-1) : (byte)0; //7 bits
		boolean containsAxof2params = containsAxOf2Params(leftHeader) | containsAxOf2Params(rightHeader) | isAxOf1Param(leftHeader);
		byte op6bits = Op.nextOp6Bits(op6Bits(leftHeader), leftCurriesMore, op6Bits(rightHeader), rightCurriesMore);
		//FIXME know !parentIsLiteralCbt256, but what if left or right is?
		boolean containsCleanNormedBit1 = containsCleanNormedBit1_ignoreIfLiteralCbt256(leftHeader)
			|| containsCleanNormedBit1_ignoreIfLiteralCbt256(rightHeader) || isCleanNormedBit1(leftHeader);
		//low 40 bits of bize (bitstring size). bize ranges 0..(2^120-1).
		//Bize is position of last 1 bit in content, or 0 if there is no 1 bit. Content is concat(leftCbt,rightCbt).
		long leftBiz40 = leftHeader&maskLow40BitsOfBize_ignoreIfLiteralCbt256;
		long rightBiz40 = rightHeader&maskLow40BitsOfBize_ignoreIfLiteralCbt256;
		long biz40 = 0;
		boolean isCbt = isCbt(leftHeader) && isCbt(rightHeader) && leftCurriesMore==rightCurriesMore;
		if(isCbt){
			byte leftCbtHeight = (byte)(curriesSoFar(leftHeader)-smallestCbtAt); //cbt size is 1<<cbtHeight
			if()
			TODO shifts
		}
		return headerOfFuncall(containsAxof2params, isClean, op6bits, containsCleanNormedBit1, curriesMore, biz40);
	}
	
	public static long headerOfFuncall(
		boolean containsAxof2params,
		boolean isClean,
		byte opWithBinheapIndexElse0MeansDeeplazy_6bits,
		boolean containsCleanNormedBit1,
		byte curriesMore_7bits,
		long lowBitsOfBize_40bits
	){
		return (containsAxof2params ? maskContainsAxof2params_ignoreIfLiteralCbt256 : 0)
			| (isClean ? maskIsClean_ignoreIfLiteralCbt256 : 0)
			| ((((long)opWithBinheapIndexElse0MeansDeeplazy_6bits)<<shiftOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256)&maskOpWithBinheapIndexElse0MeansDeeplazy_ignoreIfLiteralCbt256)
			| (containsCleanNormedBit1 ? maskContainsCleanNormedBit1_ignoreIfLiteralCbt256 : 0)
			| ((((long)curriesMore_7bits)<<shiftCurriesMore_ignoreIfLiteralCbt256)&maskCurriesMore_ignoreIfLiteralCbt256)
			| (lowBitsOfBize_40bits&maskLow40BitsOfBize_ignoreIfLiteralCbt256);
	}
	
	public static final long headerOfCleanLeaf = headerOfFuncall(false, true, (byte)1, false, (byte)0, 0L);
	
	public static final long headerOfDirtyLeaf = headerOfCleanLeaf^maskIsClean_ignoreIfLiteralCbt256;
	
	public static boolean isLeaf(long header){
		throw new RuntimeException("TODO");
	}
	
	public static boolean isClean(long header){
		return (header&maskIsClean_ignoreIfLiteralCbt256)!=0;
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
		throw new RuntimeException("TODO check isLiteral256Bits andOr op, but not the 7 curry_something_todo_choose_a_design bits.");
	}
	
	public static byte curriesSoFar(long header){
		//TODO optimize by inlining both funcs code here and merging the duplicate code
		return (byte)(curriesAll(header)-curriesMore(header));
	}
	
	public static byte curriesMore(long header){
		throw new RuntimeException("TODO check isLiteral256Bits, op, andOr the 7 bits of curry_something_todo_choose_a_design");
	}
	
	public static boolean containsAxOf2Params(long header){
		return !isLiteral256Bits(header) && (header&maskContainsAxof2params_ignoreIfLiteralCbt256)!=0;
	}
	
	public static boolean isAxOf1Param(long header){
		return op6Bits(header)==ax6Bits && curriesSoFar(header)==firstParamAt;
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
		byte[] hash = HashUtil.hash(hashAlgorithm,MathUtil.longsToBytes(in));
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
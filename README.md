# wikibinator106
(TODO) A deterministic way for millions of people and AIs to build and play together in p2p, safely sandboxed but not dumbed-down, a very simple kind of self-aware living number, where 2 numbers combine to create or find another number, and so on, and a number can be anything such a word, video, game, simulation, publicKey, GPU optimization, music tools, way to use multiple clouds together, etc.

While nearly finished creating the wikibinator105 prototype I found this optimization that required changing the universal function.
Once I get past the prototype kind of id working efficiently for derived debugStepInto and debugStepOver lambdas, and some basic graphics and sound derived from lambdas, wont need to keep changing the universal function, just use Evaler.ava to put JIT compiled optimizations in it, keyed by those ids or == in hashtable etc.

Look how complicated the bit shifts and masks and if/else conditions got in https://github.com/benrayfield/wikibinator105/blob/main/wikibinator105/impls/marklar105b/ids/MarklarId105b.java so I'm designing a universal function where that kind of thing will be simpler.

Like https://github.com/benrayfield/wikibinator105 (which is incomplete) except:
* Easier to optimize.
* Max 127 curries, instead of vararg, unless you derive a currying system outside the curry op which you can since its turing complete.
* Every object has comment param (can be anything such as words, an icon, or various metadata) as curry number 2, instead of just wikibinator105's curry op having comment.
* Has opcodes to get number of curries as cbt8.

Like wikibinator105, you can derive new kinds of ids at runtime.
It will come with the following kind of id,
and is suggested to derive a 512 bit kind of id if you want bitstrings bigger than 256mB and extra high security,
the 256 bit (32 byte)data structure:

datastructtype8 //chooses literal 256 bits (usually is id of itself, 4/256 cases when its not) vs normal id.

issclean1 //clean vs dirty

containsAxConstraint1 //does it contain any of (axA x) or (axB x) aka an Op.ax constraint?
 
containsBitOne1 //does it contain a 1 bit anywhere deeply, such as for sparse matrix optimization.

opcode5 //choose between up to 32 opcodes (have only filled around 20 of them, todo redesigning these)

ignore1 curriesall7 //How many curries in total, starting from _root/universalFunc, 1..127

ignore1 curriesmore7 //How many curries more until eval, range 0..127

lowBitsOfBize32 //If is a 256mB-1 bit bitstring then this is Integer.MAX_VALUE. Also efficient for tiny bitstrings.

hashOrLiteral192 //literal cbt1..128 (and the rest 0s) else last 192 bits of sha3_256(concat(leftChildId,rightChildId,"wikibinator106.marklar106bId"))

The "wikibinator106.marklar106bId" included in the content to hash will prove that if the ids are cracked, it was not reusing calculations from any other system and will provide motivation to many people to redesign the system to be more secure, given that 2^96 ids had to be stored and searched 2^96 times to find 1 collision, in theory.
If so, we will just go to 512 bit ids with some kind of pascals triangle hashing or whatever NPHARD-proven kind of hashing or maybe SHA3 is good enough and SHA3 would be alot faster, of some derived kind, and wont be any more collisions (if there are in the first place?) unless you have a computer the size of a planets volume at least, in theory.

Its just a prototype, but I do want the prototype to scale so millions of people can get a feel for using the system together, and because I might only get one chance at this, once it goes-viral it takes on a life of its own and not I nor anyone else has the power to change how opensource forks go, how opensource networks fit together. I could of course keep creating improved variations of it, but like IPFS has its network protocols and data structures, once a peer to peer network goes online it gets harder to get people to fork to alternative incompatible improvements.

datastructtype - First byte chooses if its a normal id vs a literal 256 bits.
	If it does NOT start with 111110 (which no utf8 byte does, and most floats and doubles do not)
	then it is literal 256 bits that are their own id.
	If it starts with 11111000 then its a normal id.
	If it starts with that plus 1, 2, or 3, then it is the id of the literal bitstring 1 below it,
	like if it starts with 11111011 it is the id of the same 256 bits except with first byte of 11111010.
	The id of 256 bits which start with 111110 requires 2 of 128 bits which each have their own 256 bit id,
	and its the parent of those 2 which has its own 256 bit id.
	

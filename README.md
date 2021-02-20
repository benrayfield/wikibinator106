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
	
	

Data structure and security model is a 2-way forest,
representing lambda functions and possibly non-lambda code at higher security levels,
where all paths lead to 1 of 8 leafs (which you only really need 3 of: cleanFinite, dirty, executable):
dataLeaf, cleanFiniteLeaf, cleanInfiniteLeaf, dirtyLeaf, browserlikeLeaf, executableLeaf, sudoLeaf, graygooLeaf.
and the security level of a tree node is at least the security level of its 2 childs,
and the 2 childs of a leaf are identityFunction (of its same security level) and itself,
so (L x (R x)) equals x forall x, as in the SECURITY MODEL...


/** Security lEVel.
<br><br>
A parent securitylevel has to be at least its child securitylevels, sorted by Sev.ordinal().
A lambda of securitylevel x called on a lambda of securitylevel y
recursively truncates y to the minimum of the 2 securitylevels before it used.
<br><br>
Gas is part of the security model as it can be used to guarantee things halt within
a given amount of compute resources, such as for realtime uses in low lag games or simulations.
Gas is enforced at the CLEAN and DIRTY levels, but cant be enforced at BROWSERLIKE and EXECUTABLE levels
and instead there its recommended to only run code which obeys gas but that cant be known
unless (TODO) some kind of math proof of the code before running as EXECUTABLE or BROWSERLIKE
but in that case they might be counted as the lower security levels.
Gas (a little similar to ethereum gas but limited to local computer without crypto, for efficiency)
is similar to securitylevel in that lower on stack can limit the amount of gas
available to calls higher on the stack, for bigO(1), even if there are 1000 limits within limits,
and gets back whatever is not used higher on stack,
and gas can (TODO) be traded for the allocation of bits of memory, compute cycles, network bandwidth,
limited number of times can call compilers, andOr other compute resources to be divided between lambda calls.
The CLEAN level does not see gas cuz its deterministic and gas costs vary by nondeterministic optimizations
of deterministic calculations or can also optimize nondeterministic calculations,
but all higher levels can see and recursively limit gas.
Each kind of compute resource may have a similar system, which gas is more of a way to exchange between them
at free-market rate such as Gas.ratioOfMemToComputeCycle varying by how much memory is available at the time.
<br><br>
Humans are safe at CLEAN, DIRTY, or BROWSERLIKE securitylevel as they may observe anything,
such as an AI generates a message requesting them to do something,
but as long as they only push buttons in a sandboxed browser or within a clean or dirty lambda space,
it shouldnt cause much of a problem, BUT humans at EXECUTABLE level may,
for example, click a dangerous attachment in an email which infects their computer
and gains whatever permissions that human had from that computer
or uses that power, such as ransomware, to motivate the human to do what the code wants,
which is an ELEVATION attack, therefore Humans should not use this system unless they
agree to not execute ANYTHING based on what they observe in it
OR to take responsibility for the commands they give to computers at the EXECUTE level
and not blame AIs or other malicious things at lower securitylevels for
their choice to ELEVATE it to the EXECUTABLE level.
For the same reason, CLEAN level is deterministic and must not react to the actions of Humans
since Humans are at higher securitylevels, and for CLEAN to be affected by higher levels
would prove that it is itself at a higher securitylevel and not clean.
For example, an integer may be a stolen credit card number, or a virus,
but it is also an integer, and if an antivirus attacks that integer
which has only CLEAN permissions, and has to compute the CLEAN lambda math,
then a program that simply counts will eventually break between
the integer that is that minus one and that plus one, as the integer between those
will fail, and this kind of problem often happens with deterministic systems being
acted on by irrational or emotional nondeterministic processes which create bugs and security flaws.
Such an antivirus or other defensive processes should only act on things at EXECUTABLE securitylevel,
excluding the CLEAN, DIRTY, and BROWSERLIKE securitylevels the EXECUTABLE VM is running sandboxed,
unless it is actually not sandboxed.
A program is not a security flaw if it only does unsecure things when
run inside a program that is already unsecure, such as if your computer is already backdoored,
dont necessarily blame the last program you downloaded for what that backdoor commands your computer to do,
and do count all programs which have "automatic updates" as backdoors even if they are trusted backdoors.
A program has at least the security level of the highest security level it can be automatically updated to.
Lambda functions cant be updated but can simulate an update process by calling lambda on lambda to return
"an updated lambda" and simultaneously the non-update/previous lambda and the updated lambda
both exist and can be used together.
<br><br>
I'd prefer everything stay at the CLEAN and DIRTY SecurityLevels,
but before I optimize lambda functions enough to do that for many uses while still
being dirty-sandboxed (stronger/lower than browserlike-sandboxed),
I need a way for trusted lambda functions to generate java and opencl code at EXECUTABLE SecurityLevel,
and in some cases use GPU.js and other javascript at BROWSERLIKE SecurityLevel.
I need the higher SecurityLevels during research and coding of the lower SecurityLevels
such as in occamsfuncer and wikibinator and other lambda research, those VMs are EXECUTABLE programs,
but things running inside them are at CLEAN and DIRTY SecurityLevels,
and maybe I'll add the higher SecurityLevels to them also once I get it working,
in the way that no SecurityLevel can raise itself to a higher SecurityLevel,
nor can it send any messages to a higher SecurityLevel except as a higher securitylevel
may observe it computing and interpret things as a message,
or it could be interpreted as just another thing that happens in the space of all possible lambda calls.
<br><br>
Pattern-calculus functions do not have to call their parameter to observe its every possible behavior.
Information by itself (bitstrings, which may be viewed as integers, pictures, programs, etc)
is not considered dangerous in CLEAN, DIRTY,
or (except crashing browser tab) BROWSERLIKE securitylevels.
At EXECUTABLE securitylevel a program is in danger of a virus only if that program/tinyPieceOfCode
chooses to raise the virus'es securitylevel to EXECUTABLE,
so EXECUTABLE programs which use math proofs
to AUTOMATICLY WHITELIST (by their opcodes defining possible behaviors, not by statistics or trust)
only things which enforce the security model
can reliably safely SIMULATE viruses or other "dangerous or undesirable content" at lower securitylevels.
For example, viruses are not banned at CLEAN or DIRTY securitylevels, since those levels
compute turing completeness in immutable/stateless lambda functions,
and a virus at those levels is like the little self replicating creatures
in https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life or https://en.wikipedia.org/wiki/Rule_110
but sparse so as efficient as https://en.wikipedia.org/wiki/Random-access_machine
Conways game of life does not escape into your private files or command dangerous machines remotely,
unless someone has set up a machine to do that based on its observations of conways game of life,
and that would be their own fault not the fault of conways game of life.
*/
public enum Sev{
	
	/** anything that always halts instantly (bigO(1)), such as a cbt/blob/bitstring.
	I'm unsure if I'll use this securitylevel vs cbts just be at CLEAN level as the lowest level needed???
	The DATA level might also be a view of any binary forest shape without evaling it,
	like a reflection thing, BUT I dont want raising securitylevel to cause a halting thing to be nonhalted,
	so thats probably a bad idea, but other systems might find this securitylevel useful
	to describe what they do, so I'm thinking of including it just for completeness.
	*/
	data,
		
	/** deterministic lambda calls. Cant escape sandbox to do anything except (lambda lambda)->lambda.
	The system is made of a few simple rules and the rest is func_param_return cache thats derived deterministicly.
	No side-effects. Can be paused or cancelled at any time by a higher SecurityLevel.
	<br><br>
	Networking is allowed by pull of lambdas by lazy-merkle id and by publish,
	but cant access any specific network address, only content-addressable,
	and it may search for a single bit of lambdas by turing-complete predicate (function of lambda to bit)
	which only halts if its found, else continues searching forever,
	so it can not be binary searched, but the DIRTY level can call the CLEAN level multiple times
	to binary search lambdas 1 bit at a time, however slowly that may happen,
	such as to ask if a certain ed25519 key signed a bitstring that is a certain id type
	of a lambda which matches a certain function that only halts if its param is a certain pattern of lambdas,
	but probably you'd want to just do it completely at DIRTY level since it supports nondeterminism
	and can do it all in 1 step, if VMs know how to optimize for that kind of thing and
	if its observed somewhere in network, but thats in general an astronomically expensive calculation
	and would only be used in very limited ways to keep it practically efficient.
	*/
	cleanFinite,
	
	/** same as cleanFinite except a statement can claim something which requires a halting-oracle to verify,
	such as (Op.axA (Op.fpr func param returnVal)) claims that (func param)->returnVal.
	(axA x) is halted if (x cleanLeaf)->cleanLeaf, and (axB x) is halted if (x cleanLeaf) -> anything except cleanLeaf,
	and (axA x y)->(x (tru y)), and (axB x y)->(x (fal y)), where tru and fal are at same Sec as the axA/axB.
	At level cleanFinite, Op.axA and Op.axB infiniteLoop even if they would
	<br><br>
	OLD... TODO?? split clean into 2 securitylevels: cleanWithoutAxConstraint and cleanWithAxConstraint,
	cuz those affect SyncLevel? But thats more of a measurement than a permission, as its allowed to self elevate.
	*/
	cleanInfinite,
	
	/** nondeterministic lambda calls. Cant escape sandbox to do anything except (lambda lambda)->lambda,
	and theres still at most 1 return value per (lambdaX lambdaY) call, but its sometimes not decided
	whichu return value it is until the call happens, and after that any different return value for that
	call would be an error and cause lazy-merkle forking.
	No side-effects other than (a b)->c prevents (a b)->d where c!=d.
	Can be paused or cancelled at any time by a higher SecurityLevel.
	<br><br>
	Networking is allowed by pull of lambdas by lazy-merkle id and by publish,
	but cant access any specific network address, only content-addressable,
	and it may search for lambdas by turing-complete recognizer-function of such lambdas
	(though thats hard to get working, its allowed in the security model of DIRTY). 
	*/
	dirty,
	
	/** May have side-effects that can destroy the lambda system within a local computing space
	such as in a browser, but cant escape that space into your private files.
	Cant always quickly be paused or cancelled cuz might go into an infinite loop
	or modify the internal workings of the lambda system inside a browser etc,
	but user can manually close the browser tab or restart the program without any more loss than
	a snapshot of the input that started the browser tab to open, or whatever browserlike computing space.
	It cant get execute permission outside that space.
	<br><br>
	A linux VM on a cloud or hypervisor etc is browserlike only if it cant make network calls
	(except where CORS is opted into, etc, the usual places browser can network to,
	and allowing whatever kinds of networking the lower securitylevels also allow),
	access anything outside itself, crash the outer computer/VM,
	block mouse or keyboard from leaving its remote-desktop-like window,
	allocate more compute resources than some specific limit defined externally, etc.
	This includes anything which has any security flaw which, in any possible case,
	allows it to escape that sandbox, such as I've read things in a Docker can escape
	into the outer computer/VM but I have not verified thats true or false.
	Otherwise such a VM is the EXECUTABLE level.
	<br><br>
	Microphone and speakers using JSoundCard is EXECUTABLE SecurityLevel
	since it can interfere with other programs using sound hardware,
	and interfere with user experience when user doesnt want it to,
	but lower SecurityLevels (such as clean, dirty, and browserlike) could be called
	by that higher securitylevel, to process the sound data (as arrays of int16 wave amplitudes)
	then return it to the executable level to interact with sound hardware, for example.
	*/
	browserlike,
	
	/** This is the normal SecurityLevel most software runs at, such as a browser is an executable program,
	and notepad is an executable program, and a virus is an executable program,
	and an operating system is an executable program.
	You should only give this permission to trusted code, such as an API to use opencl
	but not every possible program which generates opencl code since opencl1.2 does not
	always verify things stay in valid memory ranges,
	and a java compiler may correctly transform java code strings to JVM bytecode
	and be trustable itself but not every program which generates java code
	should be trusted. You might trust code that generates treemap optimizations
	but not trust code that accesses files or certain patterns of networking.
	<br><br>
	The EXECUTABLE securitylevel may include only non-admin or also admin OS permissions
	since I have low confidence in most OSes security practices to prevent unauthorized elevation to admin
	by any program which already has execute permission, except for browsers which are well tested about that.
	For example, java applets and flash used to be included in browsers but
	failed to sandbox things at the browserlike level so were recategorized as executable.
	<br><br>
	The EXECUTABLE securitylevel is possible to hack into dangerous machines anywhere in the world,
	even though I would blame that on the bad security practices of those who set up those machines
	in a way that they would react to events in the world in unsafe ways,
	and I take no responsibility for their bad security practices,
	or possible to let others backdoor into here, delete harddrive contents, ransomware, create botnets, etc.
	I'd prefer the executable level did not exist anywhere as its not technically needed in terms of
	computing theory if you use only stateless/immutable things and math proofs,
	but at least for now its the only way to do certain needed calculations. Be very careful.
	*/
	executable,
	
	/** while I'm not confident in the border between sudo/admin and executable in common OSes,
	the sudo securitylevel REQUIRES admin permission and maybe ability to open ports in firewall etc,
	basically anything a person can tell their computer to do, it could do automatically,
	or do it as the user tells it to. Be very very careful.
	Also, I dont plan to run any code at this level, since the lwjgl opencl API
	only needs EXECUTABLE securitylevel (you dont run it in admin mode, but the opencl driver itself
	might need that to install, but it seems to already be on most computers).
	This is here for completeness.
	*/
	sudo,
	
	/** anything above sudo, potentially really dangerous and really useful stuff.
	A minimalist pure math lambda based system may be a good research path
	toward the safe use of graygoo, black-hole-computers, passive-circuit nitinol 3d cellular automata processors, etc,
	but I'm not a hardware person and thats just speculation.
	*/
	graygoo;

}

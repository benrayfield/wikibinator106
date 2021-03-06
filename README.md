# wikibinator106
(TODO) A deterministic way for millions of people and AIs to build and play together in p2p, safely sandboxed but not dumbed-down, a very simple kind of self-aware living number, where 2 numbers combine to create or find another number, and so on, and a number can be anything such a word, video, game, simulation, publicKey, GPU optimization, music tools, way to use multiple clouds together, etc.

A universal function of 7..127 params, which is a combinator, universal lambda, and pattern calculus function, and is designed to be GPU optimized and for gaming-low-lag sync in p2p network, graphics, sound, etc, and has bitstrings built in up to 2^120 bits which in abstract math are padded with 10000...000 until next powOf2 and can be sparse or dense, but in practice will store only the parts you actually need.

Its still impractically slow cuz doesnt have a compiler and is running in interpreted mode, so its hard to write interesting code for a tutorial yet.

All test cases pass: https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/impls/marklar106/Test.java
https://github.com/benrayfield/wikibinator106/blob/main/data/wikibinator106/testOutputs/marklar106TestOutput.txt
That test output contains "(c2 {{,ifElse {,isleaf q1}}#n21 (lazig {,isleaf q0}) (lazig {n21 (lazig ,f) (lazig {,and {recur2 {,l q1} {,l q0}} {recur2 {,r q1} {,r q0}}})})})" which is a derived equals function. Every (...) or {...} etc is a self contained function, and it can be written in a more detailed form where every function is a call pair of 2 child functions (even the universal function is the call pair of identityFunction and itself, and identityFunction is derived from the universal function, so its nothing but a directed graph of call pairs, containing all possible lambdas). An inner function cant see "var names" in the outer function. There are no vars at all, only constants, but I'm planning to derive treemaps and content-types like "image/jpeg" and "text/plain" (see typeval opcode) so you can put strings, pictures, or even lambdas as keys and map them to anything, effectively namespaces, but everything is still immutable/stateless and efficiently forkEditable. {,ifElse {,isleaf q1}}#n21 is a self contained function, and the #n21 is not part of the function but is only displayed that way to say {,ifElse {,isleaf q1}} occurs again later in that code string. Similarly, ifElse is a self contained function (not displayed here, but I built it in ImportStatic.java, and it is a lambda like anything else. All lambdas are made of a universal function (λ), either that or the dirty form of the universal function which is like a nonstrict vs strict mode, except they're not modes, they're functions that can be called on eachother in any combos.

Forall lambda x, (L x (R x)) equals x. For example, (L L (R L)) equals L. Its the left and right childs of the call pair which returns the parent, if the parent is halted.

While its not a cryptocurrency or blockchain, it is a lazyEvaled merkle forest which is a data structure compatible with them. Its designed for low enough lag to satisfy hardcore gamers, and security that uses 192 bits of hash compared to https://en.wikipedia.org/wiki/Ethereum#Addresses uses 160 bit addresses. It is in theory scale-free and can already create around a million lambdas per second per computer, expecting to hook that into GPU without breaking compatibility with pure lambdas.

Maybe... UPDATE 2021-4-2, starting another implementation of wikibinator106 (axcompiler106) designed to be far more efficient, that computes the exact same universal function (and can be used interchangibly with, or both together in multiple computers, marklar106 implementation):[
```
package wikibinator106.impls.axcompiler106;

/** In this implementation of wikibinator106, the java stack is not used directly like in marklar106,
and instead everything is represented as lazyEvals, like a call of (s x y z)
is evaled by proving (lazig (s x y) z) lazyevals to same as (lazig (lazig x z) (lazig y z)),
and the equality of what those 2 lazys would return is proven using axa, without actually evaling them yet.
Or similar lazy statements. A lazy statement has room to include any extra data you want,
such as path backward on a stack and parts of stack (like callquad),
cuz whatever it is just has to eval to the same thing,
and theres an infinite number of ways to compute each possible thing.
If a statement is ever true, its always true,
so we dont have to care which path(s) of peephole optimizations generated it.
Use dovetailing as a math abstraction (and literally run it if in interpreted mode,
but it will run out of gas nearly always if so) if needed to procedurally eval the
constraint generators (axa statements that generate more axa statements)
that are the peephole optimizations. Its a research path to explore how well it can be optimized
even though its certain it will work as its derived math, its very uncertain if will be practically fast.
Each peephole optimization, a lambda call from a lazyeval to return another lazyeval (not evaled yet),
needs to be run with its own gas/maxSpend limit since any one of them may have infinite cost.
<br><br>
A statement is true IFF it halts. Forall x, if (axa x) then NOT (axb x),
and if (axb x) then NOT (axa x), and both (axa x) and (axb x) may be false/nonhalting,
but they cant both be true/halts about any specific x.
This is the core logic of the sparse turing complete bloom filter.
<br><br>
Trying to manually predict which combos of peephole optimizations will be needed,
like in code that uses all of GPU and music tools and loops with treemaps as namespace,
is too hard to manually think through, so this new strategy is needed. 
<br><br>
This is a way to organize many compilerPeepholeOptimizations (each a func)
( https://en.wikipedia.org/wiki/Peephole_optimization )
into a directedGraph between many possible constraint based statements (using axa)
that say certain pairs of lazyEvals will return the same thing,
so starting from any lazyEval (such as a call of lazig on 2 params or t on 1 param),
and given n compilerPeepholeOptimizations, every sequence of optimizations
called on it returns another lazyEval (or the same one) which, when called on u,
return the same thing as the original,
and it can prove the eval of it, as a lazyEval, if the lazyEval is (t x)
cuz t/true called on any 2 params returns the first param,
so if a lazyEval can be peephole transformed to (t anythingXYZ) then it evals to anythingXYZ.
These peephole optimizations will include lazycl (lwjgl opencl/gpu),
acyclicFlow (forest of (double,double)->double ops, such as for musical instruments),
things that can generate javassist code, loops and sequences of treemap transforms (like namespaces),
blobs containing a single primitive (double float long int byte etc) generating other primitives
such as double double -> double multiply, int int -> int plus, shifts,
and other basic math using hardware optimizations,
and whatever other turing-complete-general optimizations turn out to be useful, etc.
<br><br>
This will be https://en.wikipedia.org/wiki/Peephole_optimization research of
implementing the wikibinator106 universal function in the form of many (axa (fpr func param return)),
and between those, the peephole optimizations will be funcs (with Evaler.java optimizations)
that take a lazyX as param and return a lazyY,
which proves (axa (twoLazysEvalToSame lazyX lazyY)) and (axa (twoLazysEvalToSame lazyY lazyX)),
where twoLazysEvalToSame is a derived func of 3 params that calls each of its first 2 params
on u/nil/theUniversalFunc and returns u or (u u) depending if what those return is equal,
and as usual the axa around it is halted only
if its param called on u (which is ignored in this case) returns u.
*/
public class TODO_this_is_the_plan_for_axcompiler106{
	
	TODO

}
```
]

Latest progress 2021-3-14 [ test_cxLinPlusTwoscomplement works, and am about to derive int, long, float, and double math, then optimize it using an Evaler.java instance which will read 32 or 64 bits from 2 param cbts and return another 32 or 64 bit cbt, such as (dmul 3.45 10.)->34.5 ]

A universal function of 7..127 params,
which is a combinator, universal lambda, and pattern calculus function,
and is designed to be GPU optimized and for gaming-low-lag sync in p2p network, graphics, sound, etc,
and has bitstrings built in up to 2^120 bits which in abstract math are padded with 10000...000 until next powOf2
and can be sparse or dense, but in practice will store only the parts you actually need.

The system is very very very slow in interpreted mode, but there will be multiple levels of https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/spec/Evaler.java optimizations. It can do about 1 million lambdas per second, and to multiply 2 floats costs a few thousand lambdas so maybe 300 flops. Not gigaflops. Just flops. 0.0000003 gigaflops. With https://github.com/benrayfield/lazycl/ optimization that will get up to about 100 gigaflops (on GPU) and https://en.wikipedia.org/wiki/Javassist about 1 gigaflop in CPU, so to be competitive with other number crunching systems I plan to make it about 300,000,000 times faster than it currently is (normal GPU speeds). Times the number of computers its run on at once. On 1 million computers (most of which have a person using the system, other computers optionally in clouds), at 10^11 flops (float multiplies per second), at 3000 lambdas per flop, that would be 300,000,000,000,000,000,000 lambdas (300 exa-lambdas, 100 petaflops) per second shared among 1 million simultaneous users each providing part of that compute power toward their shared goals. Plus some huger amount of compute power some people (in peer to peer / p2p network) may rent and contribute to the network or specific goals within it. The 300 exa-lambdas would be just the basic speed without cloud acceleration. https://en.wikipedia.org/wiki/FLOPS

The graphics will look like this, with a code editor and games all in the same 2d, 3d, or n dimensional space, using this voxel system.
Each voxel is 64 bits and will be generated by lambdas, and can be a single pixel, rectangle, oval, line at an angle, or char of simple text (no unicode yet, todo).
For example, that checkbox in the pic is made of 7 voxels: 1 for background square, 4 for its borders, and 2 for the X, so a checkbox is 56 bytes.
The smooth colors in top left are 1 voxel per pixel, 64k of them. The blue curve is made of circles. The 64 bits are divided this way: 11 bits for xStart, yStart, xEnd, yEnd (a rectangle/oval or lines 2 ends), so up to 2048x2048 resolution. 12 bit color, so 16 brightnesses of red, 16 of green, and 16 of blue. Line thickness ranges 1-128 pixels, and thats the negative byte values. Byte value 0 is rectangle. 1 is oval. 2-127 is text (todo unicode, but need bigger voxels than 64 bits later). This will be enough to create huge massively multiplayer 3d worlds, kind of cartoony but its more about the gameplay which you can (todo) modify while playing it to evolve or design millions of new games per second all within the same endless space of endless number of dimensions, branching and merging how they fit together with other peoples creations smoothly.
<img src=https://github.com/benrayfield/wikibinator106/blob/main/data/wikibinator106/pics/voxels_2021-3-7.png>

Example code of a lambda that returns t/true or f/false for if its 2 params equal (are same 2-way forest shape),
given some names (such as (ifElse condition ifTrue ifFalse) evals to (ifTrue u) or (ifFalse u) and q0 (gets last param) and q1 (gets second last param)) of lambdas defined similarly elsewhere, but its all derived from the universal function if you for example look into (l ifElse) and (r ifElse) and (l (l ifElse)) to see what its made of.
Notice it deduplicated the "iF(st(isleaf,q1)" by naming it n17.

```
(
	c2
	{
		{,ifElse {,isleaf q1}}#n17
		(lazig {,isleaf q0})
		(
			lazig
			{
				n17
				(lazig ,f)
				(
					lazig
					{
						,and
						{funcOf2ParamsCallsItselfRecursively {,l q1} {,l q0}}
						{funcOf2ParamsCallsItselfRecursively {,r q1} {,r q0}}
					}
				)
			}
		)
	}
)#equals

The java code which generated the equals lambda is
in https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/impls/marklar106/ImportStatic.java
public static final fn equals = c(2).p(iF(
	st(isleaf,q1), //if p9 is leaf
	thenT(isleaf,q0), //then return: p10 is leaf?
	then(iF( //else if
		st(isleaf,q1), //if p10 is leaf?
		thenConst(f), //then return f
		thenT( //else return AND of recurse 2 times on the left of both params and right of both params
			and,
			ss(funcOf2ParamsCallsItselfRecursively, st(l,q1), st(l,q0) ),
			ss(funcOf2ParamsCallsItselfRecursively, st(r,q1), st(r,q0) )
		)
	))
));
```

equals = (c2 {{,ifElse {,isleaf q1}}#n95 (lazig {,isleaf q0}) (lazig {n95 (lazig ,f) (lazig {,and {funcOf2ParamsCallsItselfRecursively {,l q1} {,l q0}} {funcOf2ParamsCallsItselfRecursively {,r q1} {,r q0}}})})})

equals (displayed as 2-way forest) = (((((((λ (λ λ)) λ) λ) λ) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) (((((((λ (λ λ)) λ) λ) (λ λ)) λ) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) (λ λ)) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) (λ λ)))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))))) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) (λ λ)) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))))) ((((((((λ (λ λ)) λ) λ) (λ λ)) λ) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) (λ λ)) λ) λ))) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) (λ λ)))))) ((((((((λ (λ λ)) λ) λ) (λ λ)) λ) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) (((((((λ (λ λ)) λ) λ) (λ λ)) λ) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) (λ λ)) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) (λ λ)))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))))) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) (λ λ)) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))))) ((((((((λ (λ λ)) λ) λ) (λ λ)) λ) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) λ) (λ λ)) λ) λ))))) ((((((((λ (λ λ)) λ) λ) (λ λ)) λ) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) (((((((λ (λ λ)) λ) λ) λ) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) (λ λ)))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ))))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) (λ λ)))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) λ) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (λ λ)) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) λ) λ)))))) ((((((((λ λ) (λ λ)) (λ λ)) (λ λ)) (λ λ)) λ) (((((((λ λ) λ) λ) (λ λ)) (λ λ)) λ) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) λ))) ((((((λ λ) λ) (λ λ)) λ) (λ λ)) (λ λ))))))))))

The above (...) is similar to https://en.wikipedia.org/wiki/G%C3%B6del_numbering (godel numbering), in this case of an equals function (not the only one). If there are 2 functions equalsA and equalsB, both of which tell if their 2 params equal eachother (by 2-way forest shape down to cleanLeaf andOr dirtyLeaf), then equalsA and equalsB are both valid equals functions, and they are different functions as in (equalsA equalsA equalsB)->f and (equalsA equalsA equalsA)->t and (equalsA (equalsB equalsA) (equalsB equalsA))->t for example. When you include reflect ops (l r isleaf isclean) in the definition of function equality, its far easier and also far less useful than what godel wanted which is infinite but instead this is a system of finite amounts of logic (any finite number nomatter how big, such as the number of atoms in the visible universe). Halting problem, godel incompleteness, etc, are parts of logic, or vaguely describe parts at least, which one does not need to go that far to go a million times farther than whats computers can do today. One problem is infinite, and the other is finite.

UPDATE: the equals (of 2 lambdas) function works, which means nearly all of the clean (vs dirty) parts of the VM are working.

It will appear Human readable after the syntax is adjusted, but until then shared branches are displayed as duplicated every time recursively and it lacks comments (param 6 of the universal function is always comment, which can be any lambda such as containing text, icon pixels, various metadata).

in an improved syntax...
equals = (c2 {{,(c3 {,pair {(t r)#n50 {n50 l}}#n84 r {n50 funcOf2ParamsCallsItselfRecursively}#n35 ,λ}) {(t (λλ λ λλ λλ λ λ))#n9 n84}}#n2 ((c3 {n35 n84})#n61 {n9 r}) (n61 {n2 (n61 ,f) (n61 {,(c2 {n84 r n84}) {funcOf2ParamsCallsItselfRecursively {(t l)#n48 n84} {n48 r}} {funcOf2ParamsCallsItselfRecursively {n50 n84} {n50 r}}})})})

without dedup in syntax...
equals = (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})})

About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}} (pair (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})} (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})} (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})}))) (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})} (c2 {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} {,(λλ λ λλ λλ λ λ) r}) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}) {,(λλ λ λλ λλ λ λ) {,r {,r l}}} (c3 {,r {,l {,r l}} {,r {,r l}}} ,f) (c3 {,r {,l {,r l}} {,r {,r l}}} {,(c2 {,r {,r l} r {,r {,r l}}}) {,l {,r l} {,l {,r {,r l}}} {,l r}} {,l {,r l} {,r {,r {,r l}}} {,r r}}})})})))) λ))

### testEqq pass: (equals (equals equals) (equals equals)), t



As of 2021-3-10, the equals function is nearly finished (a lambda that can detect equality of any 2 given lambdas, in a pattern-calculus way that allows reflection ops to see the internal 2-way forest shape which is all its really comparing), and these testcases are working, and the rest are commentedout cuz havent rewritten them from earlier forks.

Test cases https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/impls/marklar106/Test.java
testLeafAndFewOpsInternalStructures_withFewThingsCommentedOutCuzCodeWasFromDiffUniversalFunc_todoAddSimilarTests();
testTF();
testPair();
testIota();
testLRQuine();
testIdentityFuncs();
testSTLR();
testConsCarCdr();
testProgn();
testCurry1ToCurry16();
testLazig();
testIfElse();

q3 (aka getter of 3th last param given curryDatastruct) = {,r {,l {,l {,r l}}}}
q4 (aka getter of 4th last param given curryDatastruct) = {,r {,l {,l {,l {,r l}}}}}
q5 (aka getter of 5th last param given curryDatastruct) = {,r {,l {,l {,l {,l {,r l}}}}}}

{,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} ,r (pair t f))))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)) λ))
### testEqq pass: ifElse car then I, car gets T which chooses then(I), and the I called on (P T F) returns (P T F), (pair t f)
evaling. op=curry3. funcBody = {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ}
evaling. op=curry3.     func = (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f)))
evaling. op=curry3     param = (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))
curryDatastruct = (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))), about to call funcBody on it
About to call ({,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} (pair (c3 {,pair {,r {,r l}} r {,r {,l {,r l}}} ,λ} f (c3 {,r {,l {,r l}} {,r {,r l}}} i (pair t f))) (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))))
evaling. op=curry3. funcBody = {,r {,l {,r l}} {,r {,r l}}}
evaling. op=curry3.     func = (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f))
evaling. op=curry3     param = λ
curryDatastruct = (pair (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f)) λ), about to call funcBody on it
About to call ({,r {,l {,r l}} {,r {,r l}}} (pair (c3 {,r {,l {,r l}} {,r {,r l}}} {,pair i i} (pair t f)) λ))
### testEqq pass: ifElse cdr then I, cdr gets F which chooses thenT(P,I,I), and the thenT(P,I,I) called on e(P,T,F) returns (P (P T F) (P T F)), (pair (pair t f) (pair t f))


```
λ#u
(u u)#uu
(u uu u u u u u u)#Wiki
(...)#x
(...)#a
(...)#t
(...)#f
(f u)#i
(...)#ifButNotInS
(...)#if
(...)#paramN
(...)#comment
(...)#recur
(...)#p0
(...)#p2
(...)#p3
(...)#p4
(...)#p5
(...)#and
(...)#and3
(...)#c
(c ,u)#c1
(c ,,u)#c2
(c ,,,u)#c3
(c ,,,,u)#c4
(c ,,,,,u)#c5
(...)#isColorProof
(...)#isColorDisproof
(...)#nor

/* TODO where to write comments like this? Maybe curry isnt the only place comments should have a place in? I might want them in {...} which always contains an S op,
and maybe also in [] and <> which are made of pairs aka (pair x y) aka ((pair x) y){
	,c2
	"its colorNormal if its halted and neither of colorProof or colorDisproof, since colorWordsalad is (ax anything) where (anything u) does not halt so that (ax anything) does not halt. colorNormal is anything whose left child is not ax."
	{,nor {,isColorDisproof p0
}#isColorNormal
*/
{,nor isColorProof isColorDisproof}#isColorNormal

{
	,c2
	"todo write a comment here"
	{
		,if
		{,a p0}
		{,a p1}
		{
			,if
			{,a p1}
			,,t
			{
				,and
				{recur {,l p0} {,l p1}}
				{recur {,r p0} {,r p1}}
			}
		}
	}
}#equals

{,equals ,equals ,equals}#lazy_eqeqeq_callMeOnLeafAndShouldGetT
{,equals ,"hello" ,"hi"}#lazy_eqhellohi_callMeOnLeafAndShouldGetF
{,equals ,"hello" {,L ,"hello" {,R ,"hello"}}}#lazy_eqhellolhellorhello_callMeOnLeafAndShouldGetT

(ax (fpr lazy_eqeqeq_callMeOnLeafAndShouldGetT u t))#test_lazy_eqeqeq_callMeOnLeafAndShouldGetT_todoVerifyColorIsProof
(ax (fpr lazy_eqeqeq_callMeOnLeafAndShouldGetT u f))#testOpp_lazy_eqeqeq_callMeOnLeafAndShouldGetT_todoVerifyColorIsDisproof
(,isColorProof ,test_lazy_eqeqeq_callMeOnLeafAndShouldGetT_todoVerifyColorIsProof)#testColorProof_shouldBeT
```

2021-3-4 Tests passing...


```
public static void testConsCarCdr(){
	lg("Starting testConsCarCdr. Nil is leaf/theUniversalFunction. isNil is the isLeaf op.");
	fn list_wiki_u_l = cons.e(wiki).e(cons.e(nil).e(cons.e(l).e(nil)));
	testEqq("testConsCarCdr_1", car.e(list_wiki_u_l), wiki);
	testEqq("testConsCarCdr_2", car.e(cdr.e(list_wiki_u_l)), nil);
	testEqq("testConsCarCdr_3", car.e(cdr.e(cdr.e(list_wiki_u_l))), l);
	testEqq("testConsCarCdr_4", cdr.e(cdr.e(cdr.e(list_wiki_u_l))), nil);
	testEqq("isNil_nil", isnil.e(nil), t);
	testEqq("isNil_[list_N_A_L]", isnil.e(list_wiki_u_l), f);
}

assertionsAreOn=true
Starting testLeaf
### testEqq pass: (l Leaf), i
### testEqq pass: (r leaf), λ
### testEqq pass: (l Leaf) 2, i
### testEqq pass: (r leaf) 2, λ
Starting testTF
### testEqq pass: (t wiki).l()->t, t
### testEqq pass: (t wiki).r()->wiki, w
### testEqq pass: (t wiki u)->wiki, w
### testEqq pass: (t wiki pair)->wiki, w
### testEqq pass: (f wiki).l()->f, f
### testEqq pass: (f wiki).r()->wiki, w
### testEqq pass: (f wiki u)->u, λ
### testEqq pass: (f wiki pair)->pair, pair
Starting testPair
### testEqq pass: (pair wiki) is halted, (pair w)
### testEqq pass: (pair wiki u) is halted, ((pair w) λ)
### testEqq pass: (pair wiki u t)->wiki, w
### testEqq pass: (pair wiki u f)->u, λ
Starting testIota
iota = ((pair s) t)
### testEqq pass: get t from iota, t
### testEqq pass: get s from iota, s
Tests pass: testIota
Starting testLRQuine
### testEqq pass: testLRQuine_λ, λ
### testEqq pass: testLRQuine_λλ, λλ
### testEqq pass: testLRQuine_l, l
### testEqq pass: testLRQuine_r, r
### testEqq pass: testLRQuine_w, w
### testEqq pass: testLRQuine_((pair w) ((pair pair) pair)), ((pair w) ((pair pair) pair))
Starting testIdentityFuncs
### test pass: leaf.l()==i
### test pass: leaf.r()==leaf
### test pass: stt.f(i)==i
### test pass: stt.f(t)==t
### test pass: stt.f(f)==t
### test pass: i.f(stt)==stt
### test pass: i.f(t)==t
Starting testSTLR
### test pass: st.L()==s
### test pass: st.R()==t
### test pass: st.L()==s 2
### test pass: st.R()==t 2
Starting testConsCarCdr. Nil is leaf/theUniversalFunction. isNil is the isLeaf op.
### testEqq pass: testConsCarCdr_1, w
### testEqq pass: testConsCarCdr_2, λ
### testEqq pass: testConsCarCdr_3, l
### testEqq pass: testConsCarCdr_4, λ
### testEqq pass: isNil_nil, t
### testEqq pass: isNil_[list_N_A_L], f
```


The plan is for many opensource forks of the wikibinator106 VM to run in the same p2p network
and compute the exact same bits as defined by that universal function,
but they will vary in what optimizations they offer such as lwjgl opencl/GPU, javassist compiling, using it in python or javascript, networking, etc,
and they will share immutable 2-way forest objects (each a function) in realtime, contained in 32 byte ids
which are deterministicly generated from 2 child ids down to either of the 2 leafs,
and multiple id types can be used at once since an id maker function is any function that returns
a bitstring used as the id of its parameter, but hopefully there wont be a need for more than 1 id type. 
At that point I will stop development of this prototype wikibinator106 VM
and join the others in just using wikibinator106 at "user level",
building things only using combos of calling the universal function on itself,
and many people and computers build fun andOr useful things together,
including AI reserach and massively multiplayer games (MMG) and music tools and mindmap software and whatever we want,
and the parts or wholes of these tools can be built into other tools by immutable/stateless forkEdit in realtime.
The risk of not censoring things, not anyone backdoors, etc, is spread across many wikibinator106 VM developers
and their users. I plan to make sure that nobody, not even I, have the ability to backdoor or control the system
but each individual user does for their own uses of it, in p2p networks and data at rest and clouds as peers.
The system is not designed to push people into breaking any laws and will make it easy for
people to build tools to scan for any patterns of content they do or do not want to host and build on,
but when you're creating trillions of new lambda functions per second,
as the lambda is the core of this system like the bit is the core of other systems,
and building lambdas made from call pairs of other lambdas very deeply and in many combos,
you cant just let someone say, you see that lambda (which is both code and data) buried deep
in the foundation of these 5000 new tools that were created in the last month or in the last half second,
those are illegal and you have to delete those and all those tools have to be modified.
You can build lambdas that modify other lambdas automatically, so that would be possible to some extent,
but I'm not going to suggest to people that they do or dont allow such requests to destroy what is built,
and instead such choices are left to the individual users
and tools we build for eachother to help evolve whatever needed processes.
A tool is just a 256 bit (32 byte) number and the 2-way forest recursively below it.
You dont have to use any tool that you dont want to.
A tool is just a number, and a number cant be changed (is immutable and stateless).
Just cuz someone says a number is changed, does not change the number that you are using.
They are instead calling one number on another number to find/create a number, which is a separate number.
Nobody owns the basics of math. All possible finite math statements can be written as a wikibinator106 number,
such as there is an exact representation of pi and e as a function of integer to get which binary digit to get that digit.
Nobody owns numbers. Just because you were the first to find or compute a number does not mean
you can control what others do with that number. You can literally say "pi is exactly 3" in this system
because it is so self referencing, using Op.axa and Op.fpr and a few other things,
but you can also say "pi is not exactly 3" (using Op.axb),
and more importantly, you can prove it, disproving that "pi is exactly 3",
so let the pursuit of truth solve most if not all our disagreements.
For example, if a certain lambda is a virus, like theres little self replicating creatures in conways game of life
and you might call them viruses, then lets just say hey theres a virus and run it in simulations if you want
but dont give ANYTHING execute permission ever for any reason,
because if theres a trillion new lambdas per second, and only 1 in a million of them are viruses,
then thats a million viruses per second. The system is designed to be 100% immune to viruses
(conditional on using a set of id types that are not ALL cracked at once),
and to have 100% uptime even if the entire internet is destroyed and the system
has to be computed on pen and paper or in the exchange of USB sticks offline, IF
the VM is correctly sandboxed, similar to viruses in a html file dont escape the browser into your private files.
Since all objects are immutable and stateless, a virus can not modify anything to infect it.
Nothing can modify anything ever for any reason, except to cache things for efficiency.
Since there is no WRITE, only READ, and all viruses need to WRITE to infect, all viruses fail to infect,
unless the wikibinator106 VM you are using does not correctly compute the exact bits
that the universal function (a math spec) says to compute. This is a research path so is not necessarily safe yet.



Some people thought it impossible. Supports function.equals(function) with perfect deduplication of functions, and can map functions 1-to-1 with the integers, and even better it has average of constant cost (and worst case of number of childs reachable deeply), as in...
```
fn funcL = func.l();
fn w = funcL.l().r(); //fourth last param
fn x = funcL.r(); //third last param
fn y = func.r(); //second last param
//Ignore last param, which is only there to delay eval.
//axa and axb look for its first param called on u returns u vs anything except u
ret = w.e(x).equals(y) ? u : uu;
//Function equality checking costs average constant (bigO(1))
//and worst case number of childs reachable deeply.
//They say no lambda function can detect equality vs nonequality of 2 given lambda functions,
//but I say thats because they are not pattern-calculus functions
//which are a subset of lambdas, so given that we know the complement of that set
//is not possible here, can prove more things about it,
//and its not exactly the same thing cuz I'm including reflect ops (l r isleaf isclean)
//in function behaviors since a function can be given a pointer to itself
//(or already have such a pointer by being in a curry1..curry16 op)
//and call reflect ops on itself and vary its behaviors depending on that.
//For example, (S (T (S I I)) (T (S I I))) is a function that infinite loops for every possible param,
//and you can make a function that answers T vs F
//depending if its param is (S (T (S I I)) (T (S I I))) vs some other function,
//and such a function is (Equals (S (T (S I I)) (T (S I I)))),
//but todo as of 2021-3-1 I havent derived the equals function in wikibinator106 yet
//but have in earlier forks of it and there wont be a problem doing so after the basic testcases pass.
//Similarly, (Equals (Equals (S (T (S I I)) (T (S I I)))) (Equals (S (T (S I I)) (T (S I I)))))->T
//and (Equals Equals Equals)->T and (Equals (Equals Equals) Equals)->F,
//and Equals is just a word I'm using to refer to something you can build
//by calling the universal function on itself in various combos (and will be optimized with an instance of Evaler.java)
```

```
//A basic form of graphics will be...
package wikibinator106.plugins.voxel_y10_x10_w6_h6_unicode20_color12;
import java.awt.Graphics;
import java.util.function.Consumer;
import javax.swing.JPanel;
public class UI extends JPanel implements Consumer<long[]>{
	
	/** variable size. can replace this array */
	public long[] voxels;
	
	/** 1024x1024 */
	public int[] pixels;
	
	public void paint(Graphics g){
		paint(g, voxels);
	}
	
	public static void paint(Graphics g, long... voxels){
		for(long voxel : voxels){
			//If every pixels color is chosen individually, a 1024x1024 12 bit color image is 8mB.
			//If you want a solid color across it all, thats 256 squares of 64x64 pixels each as a long each so 2kB.
			//If you want a solid color background with 1000 colored circles between 1..64 radius each, thats 10kB.
			//If you also want 1000 chars of text on screen, thats 18kB.
			
			//TODO between 1x1 and 1x64 and 64x1 and 64x64, per voxel.
			//TODO theres a single 12 bit color, of (unicode20Value==0 ? perfectRectangle : a unicode text symbol/codepoint).
			int high = (int)(voxel>>>32), low = (int)voxel;
			int shape20 = TODO; //solid color if 0, circle/oval if 1, else unicode symbol 2..(2^20-1)
			int colorRGB24 = TODO; //expand 12 bit color to 24 bits
			int height = TODO; //1..64
			int width = TODO; //1..64
			int y10x10 = TODO; //low&0xfffff; 0..2^20-1, yStart=0..1023, xStart=0..1023
			
			
			pixels[low&0xfffff] = 
		}
	}
	
	public void accept(long[] voxels){
		this.voxels = voxels;
		paint();
	}
}
```

Default security level is 96 bits cuz uses last 192 bits of SHA3-256, so to find 2 lambdas, or calls of them, which have the same id256, you'd have to store 2^96 (79228162514264337593543950336) ids and search them 2^96 times, to find 1 collision, and then we would just start using some kind of 512 bit ids or multiple id types at once will be supported as you can derive new kinds of id maker functions at runtime. They are themselves lambdas. A 2-way forest node is identified by an id256, and is a lambda or a call of them andOr cache of func_param_return. The id256 is proof of the lambda's behaviors since its like a sparse turing machine you just compute deterministicly using that to create more 2-way forest nodes. But some lambdas contain Op.axa andOr Op.axb statements, which are also lambdas but which claim that x called on y returns z or returns something other than z or various kinds of turing complete types and constraints but only constraints that can be proven in some finite time but may take infinite time to disprove (using Op.axa and Op.fpr together). So a halted lambda without any axa/axb/constraints/cache cant be faked, as long as the whole 2-way forest below it is available (each parent and its 2 childs are proven by hash). The problem comes when a lambda contains such axa/axb/constraints/cache statements (its id has a bit that says does it contain any of those or not, and is it clean vs dirty), so be very skeptical of those shared across untrusted borders (such as internet or distros of wikibinator106 or even your own harddrive if other programs have access to it). If we share only halted lambdas without axa/axb/etc, then we can each verify lambdas as fast as they come in thru the network and fast enough to do graphics and sound with them at gaming-low-lag.

Cache never expires, unless you need the memory back, and cache always gives the correct answer at gaming-low-lag, since every lambda call has at most 1 correct return value, theres never a reason to invalidate a cache of func_param_return unless theres a bug in the VM or you trust VM output by someone you should not have trusted (dont need to trust halted lambdas (fast and average_bigO(1) to verify), only cached constraints, which is optional) as it was not really their VM output but something they made up. Theres turing complete challenge response using an infinite size sparse bloom filter to help verify that and toward low lag convergence of a computing process across many computers and people, but its very experimental. Even a person, or quantum computer or anything nondeterministic, if hooked in, should always give the same answer to the same lambda call since mutable systems will be wrapped in digital signatures that sign a param/return pair (a system called mutableWrapperLambda) so a publicKey can be used as a deterministic lambda function that when called on such a param it gives that return, unless someone signs 2 return values for the same param in which case the energy function of Op.axa and Op.axb (the bloom filter, which only 1 of can be true of a certain statement or neither) can help figure out which one was built on by other people recursively, such as (Op.axa (Op.fpr func param ret)) where many such func param ret triples act like constraints, trying to satisfy the most number of them, so that if (apublickey "hello")->"world" and (concat (apublickey "hello") "xyz")->"worldxyz", then apublickey cant just sign the pair of "hello" "bob" cuz then (concat (apublickey "hello") "xyz")->"bobxyz" would have to be true but the constraints already say that returns "worldxyz". That would be a very expensive calculation to verify even small combos, but the research path is there to hold the system together by turing complete constraints. Will start on smaller easier things like 2d games, parsing its own programming language syntax, simple musical instruments, etc, especially in the CLEAN part where there is no Op.wiki, searching for what publickeys signed, etc... only deterministic things, and work up from there. In theory.

The whole program state, potentially including millions of computers, is stored in an immutable/stateless lazy-merkle 2-way forest of 32 byte ids, where all paths lead to either of 2 leaf ids (cleanLeaf and dirtyLeaf). Each 2-way node contains no data other than the 2-way forest shape, and the id has caches of things that can be derived from that forest shape such as storing 2 float64s in an id as a complete binary tree of Op.zero and Op.one 128 times so 7 deep and below that are the boilerplate 6 params the first 5 of which choose which of 32 opcodes and the sixth param is a comment (text, icon pixels, any metadata, a whole snapshot of the internet, whatever you want to put in comment). At any momement during computing, you can take the ids and copy them to another computer and continue the calculation there and get the same result, at least in the clean nodes (which are mostly a mirror of the dirty nodes but dont allow nondeterminism). Many computers can compute together at gaming-low-lag by sharing ids or things that can generate them. Hashtable func_param_return caches (which can be reflected as Op.ax statements), GPU memory pooling, lazy-eval of ids, wrapping of large 1d primitive arrays such as byte[] and double[] and String, etc, can be derived from the ids but takes a little longer to rebuild that cache if you just have the ids. There is nothing in the system other than ids and things which can be generated from ids. An id is the id of its 2 child ids, in a 2-way forest where they share branches. Ids have an average of 49% binary storage efficiency, and worst case of 24%, for raw binary data, and if you skip some of the middle nodes in a bitstring, like only compute an id every 4 levels instead of every 1 level so use it as a 16-way tree, then you can raise that to around 15/16 binary storage efficiency, or just store content as it is with 1 id for the whole thing for 99.999...% binary storage efficiency. The system is turing complete and can generate new id types since any function can be an id maker, that takes a function as a parameter and returns a cbt256/bitstring or cbt512/bitstring etc, but at least for now I'm manually optimizing (will create an instance of Evaler.java) for the specific id maker function, and when I derive that id maker function from calling the universal function on itself in various combos (thats what combinators do) I'll attach that Evaler to that derived id maker so when its called, instead of running in interpreted mode (which would generate the same id, but extremely slower) it will call that java code in the Evaler as efficient as any other system. Interpreted mode is very very slow, and its only practical speed by including Evaler optimizations for double and float and int and long math etc and building up from there optimizations built on optimizations, in Evaler.java instances. Different wikibinator106 VMs can have different optimizations (different Evaler instances) as long as they all compute the exact same 2-way forest shape (with 1 bit of clean/dirty per node) return value for the same parameter, exactly the same bits, no exceptions not even if the GPU cant do strict float32 math it would have to compute floats as ints with masks and shifts or if you dont have a GPU have to compute it in CPU and if you dont have any optimizations for a certain lambda call it still works if you have enough time and memory since it retreats to interpreted mode for those parts. An id maker function can, of course, compute the id of itself, the same way it computes the id of any other lambda function, but it can only do it for halted lambdas or halted debugStepOver and debugStepInto lambdas which will be a debugger built from inside the system to increase its self-awareness/reflection, and you will be able to debug a debugger of a debugger... to any depth and forkEdit them like having as many VMs/hypervisors/VMWares/etc (but just for lambdas) as bits.

This system will, in theory, be the best load balancer ever created since it can run a single lambda call on multiple clouds at once before that call halts. For example, if you need the combined compute power of google, amazon, and azure all at once for a brief moment, because no one of them has enough compute power by themself, it is in theory possible to run a single lambda call at low lag across any set of computers andOr people involved in it. Unlike blockchains, its not bottlenecked by a single accumulation of state. Its made of 2-way branches in many combos, and in the clean half, those are completely deterministic so even if the same lambda call happens multiple places at once the ids still match.

Test cases https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/impls/marklar106/Test.java and from now on will do https://en.wikipedia.org/wiki/Test-driven_development on wikibinator106. They dont pass yet as the VM is incomplete, but it shows what needs to be done next.

UPDATE: Here's the id256 datastruct, which is deterministicly computed from 2 child id256 or is the cleanLeaf or the dirtyLeaf, and can derive new kinds of ids at runtime but got to start somewhere:

* 8 bits of magic/isliteralcbt256 (if it starts with anything except 111110 then its 256 literal bits that are their own id,
		11111000 is normal id. the next +1 +2 +3 are id of id of id of that)
* 1 bit containsAxconstraint.
* 1 bit isclean.
* 6 bits of opWithBinheapIndexElse0MeansDeeplazy.
* 1 bit containsBit1.
* 7 bits of curriesMore. //number of curries until eval
* 40 bits of lowBitsOfBize, so efficiently up to 1 terabit aka 128gB, and if powOf2 sized up to 2^120 bits.
* 192 bits of hashOrLiteral. //TODO which end of this does the literal go at?

TODO I'm coding this id type in https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/impls/marklar106/Marklar106bId.java and when thats working will use it in https://github.com/benrayfield/wikibinator106/blob/main/wikibinator106/impls/marklar106/SimpleFn.java and the VM. Theres code in wikibinator105 I can use. Hopefully will get a prototype working soon.


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
	
The 32 opcodes:

package wikibinator106.spec;

/** 32 ops, each with a clean form and a dirty form, so theres actually 64 ops.
The clean form of an op starts with a lowercase letter, and dirty form is capital, like wiki vs Wiki.
These are chosen in the first 5 params each being cleanLeaf vs anything_except_cleanLeaf,
then a comment param (which can be anything and is ignored other than by reflection ops),
then the params of that op. The total number of params of leaf is at most 127 so fits in unsigned byte.
*/
public enum Op{
	
	zero(121),
	
	/** Bitstrings up to 2^120-1 bits. The last 1 bit is the first bit of padding.
	The default kind of id is a 256 bit id and stores the low 32 (UPDATE: 40) bits of bize (bitstring size),
	so if you want an int64 or int128 bitstring size you have to compute it in interpred mode as lambdas,
	or derive a 512 bit id that has more room for bize.
	<br><br>
	(one_or_zero param1 param2 ... param120 param121) -> (growinglist (one_or_zero param1 param2 ... param120) param121),
	other than up to param120 every param is verified to be a cbt of same size else evals self on self,
	so get a cbt twice as big. (UPDATE: put a nil/u first in that growinglist)
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
	
	fal(2),
	
	tru(2),
	
	getfunc(1),
	
	getparam(1),
	
	isleaf(1),
	
	/** Every object is a 2-way forest with 1 bit of data at each node, that bit being isCleanVsDirty,
	and all paths lead to cleanLeaf or dirtyLeaf, and a parent must be dirty if either of its 2 childs is dirty,
	and if both its childs are clean then the parent can be clean or dirty,
	and if a clean is called on a dirty then that dirty is truncateToClean (call (fal u) on it, aka clean identityFunc)
	before the clean sees it.
	*/
	isclean(1),
	
	/** (axa (fpr wiki x y)) means (wiki x)->y.
	Also, there will be a few functions built in, something like
	(curry... wiki "spend" salt maxAmountToSpendAsInt64 x) -> [amountDidNotSpend (x cleanLeaf)], or something like that.
	(curry... wiki "wallet" salt) -> how much is left for spend calls etc, as int64, or something like that.
	(curry... solve x) -> y where (ax (fpr x y cleanLeaf)).
	64 bit local ids of things, actually global ids but with some prefix so nobody else would randomly choose it?
	*/
	wiki(1),
	
	/** (fpr func param ret u) -> u if (func param)->ret, else -> (u u), where u is cleanLeaf.
	Example: (axa (fpr ["hello" "world"] fal "world")) cuz (["hello" "world"] fal) -> "world".
	Example: (axb (fpr ["hello" 235] fal "world")) cuz (["hello" 235] fal) -not-> "world" aka does not return "world",
	so in that case (fpr ["hello" 235] fal "world" u) -> (u u).
	*/
	fpr(4),
	
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
	and if not verified then it infinite loops (evals to (S I I (S I I))) so it cant exist if
	the statement it represents (such as "(x u)->u") is not true.
	*/
	axa(2),
	
	/** the bloom-filter counterpart of axa */
	axb(2),
	
	/** church-pair lambda of 3 params */
	pair(3),
	
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
	
	/** (typeval x y z)->(y z). Normally just keep it as (typeval x y)
	such as (typeval "image/jpeg" ...bytesofjpgfile...) as a semantic.
	If you want turingComplete types, use Op.axa and Op.axb.
	*/
	typeval(3),
	
	trecurse(3),
	
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
	curry9(9), curry10(10), curry11(11), curry12(12),
	curry13(13), curry14(14), curry15(15), curry16(16);
...

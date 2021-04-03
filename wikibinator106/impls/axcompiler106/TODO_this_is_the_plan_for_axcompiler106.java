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
	
	//TODO

}

package wikibinator106.plugins.codemindmap.ui.old;

/** The state of a mindmap is similar to the state of a https://github.com/benrayfield/listweb mindmap
except its for wikibinator106 lambdas instead of just text.
NS (namespace) has the 1-to-1 mapping between λ (lambda) and String (name) together called a node,
which is only for display and does not affect the ids of the lambdas
which are deterministicly generated from 2-way forest shape.
Another part of state is each node has a list of nodes, like prilist in https://github.com/benrayfield/listweb
and if x is in y's list then y is in x's list,
and adding x to y's list causes y to appear at end of x's list,
and can drag and drop to reorder them.
I'm undecided if there will be a textarea to write or copy/paste anything you want about
the funcs, since they each have a comment param (param number 6) but its immutable there
and can be mutable in the mindmap.
Theres also a PrilistStack (together with prilist is a PrilistPanel, which there are 2 of
in https://github.com/benrayfield/listweb one above and one below the middle search bar).
A big difference between listweb and this is if you change the name of a λ,
its changed in ALL the code where it occurs, instead of them being separate nodes.
You cant edit a function. You can only create new functions.
For example, if you have a function named "hypot", thats supposed to compute (double)sqrt(x*x+y*y),
but it doesnt do that (the lambdas do exactly what they should, but you used the lambdas wrong),
you cant change that λ but you can rename it to "hypot_buggy4523324" or remove its name completely
then name some other λ "hypot". Any λs which used "the old hypot" aka "hypot_buggy4523324"
still point at that, so if you want to "change those to use the new hypot"
then all of those need to be similarly replaced, and there maybe (TODO?) will be
an automatic way to do that, but since it may cause nonhalting lambda calls
to just replace one function with another function, maybe that should only be allowed
when it results in instantly being halted and does not have any axa or axb statements containing it.
Or to start with, just dont have that feature.
Each name will have a code string thats editable like a textarea
but creates a new function at each edit.
It will be a small code string, like at most a page of text,
like those in wikibinator106's ImportStatic.java public static final fn.
Anything without a name will be displayed as expanded, or will be auto assigned a name (by its id?).
Anything with a name may be displayed just as that name or optionally also expanded.
Therefore each displaynode (which refers to a node) needs a binary forest of 1 bit per lllrlrrl etc path,
such as a 3-way tree with 3-way trees as 2 of its childs and t or f as its third child,
or a function of function to bit to display it as expanded or not.
Or maybe that would be automatically chosen based on expanding it a few levels deep breadth-first?
Mindmap state also needs to remember the scroll position of each prilistpanel,
and popghost vs popnormal (what were those ui actions called in listweb?)
where in a stack of nodes if you click lower in the stack the others higher on the stack
still appear there until something else is pushed onto the stack to choose a different path,
and until then you can click the gray nodes on stack to go back to them.
Whichever node is clicked on a stack, its prilist appears beside the stack
(list of nodes chosen and ordered manually), and its code forest appears.
In listweb theres 2 prilistpanels, which do the same thing but can view 2 parts of the system at once.
I want more than 2 for this, maybe 4 with 2 on the left side and 2 on the right side of a big area
in the middle used for code editing and doing AI experiments and games.
*/
public class MindmapState{
	
	//todo

}

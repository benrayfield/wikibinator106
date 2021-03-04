package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import immutable.util.Text;
import wikibinator106.spec.λ;

/** parsing and toString etc of the default syntax for wikibinator106 as a programming language,
where (...) is calls, [...] is linkedlistNodes and doesnt hide nil/cleanLeaf at end (or anywhere),
<...> is oppositely ordered linkedlistNode and doesnt hide nil/cleanLeaf at other end (or anywhere),
and {...} is sCurryList, and "..." is stringLiteral
inside a typeval of (notintypevalbutdisplayedthatwayanywayscuzisfirstparamoftypeval)"text/plain",
and 5.67e-8 is a numberLiteral, and λ is cleanLeaf, and Λ is dirtyLeaf,
and (...)#aName is a local name that doesnt affect ids
like in (a [b (c d e)#aName] d (e aName f) aName)#anotherName.
I might generalize a<...> to z<...> etc to be multiple syntaxs such as another one for Op.growinglist,
a syntax for treemaps, etc.
<br><br>
Will start with a far simpler syntax, just the (...) since the other parts of syntax
can be written using only that and λ and Λ and if you need to avoid exponential size for shared branches
then also need #aName. Will use a java.lang.Map or something like that temporarily as param
in such a toString func to create temp names for params so can refer to them in other places.
*/
public class Lang{
	private Lang(){}
	
	public static String toString(fn x){
		final Map<λ,String> names = new HashMap();
		long count = 0;
		boolean goPastLeaf = false; //FIXME true, but wait until get more things working cuz CleanLeaf.l() is seeing ImportStatic.i==null
		for(λ y : whichNeedNames(x,goPastLeaf)){
			names.put(y, "n"+(count++));
		}
		names.put(u, "λ"); //cleanLeaf
		names.put(U, "Λ"); //dirtyLeaf
		names.put(uu, "λλ");
		names.put(i, "i");
		names.put(I, "I");
		names.put(l, "l");
		names.put(L, "L");
		names.put(r, "r");
		names.put(R, "R");
		names.put(t, "t");
		names.put(T, "T");
		names.put(f, "f");
		names.put(F, "F");
		names.put(pair, "pair");
		names.put(Pair, "Pair");
		names.put(s, "s");
		names.put(S, "S");
		names.put(wiki, "w");
		names.put(Wiki, "W");
		names.put(axa, "axa");
		names.put(Axa, "Axa");
		names.put(axb, "axb");
		names.put(Axb, "Axb");
		names.put(growinglist, "growinglist");
		names.put(Growinglist, "Growinglist");
		//TODO all other fn fields in ImportStatic have names, and todo copy them to an immutable map there instead of here
		StringBuilder sb = new StringBuilder();
		toString(sb, x, (λ y)->names.get(y));
		String s = sb.toString();
		return toStringIncludesTopHeader ? s+"_"+Marklar106bId.toDetailString(x.marklar106bHeader()) : s;
	}
	
	public static final boolean toStringIncludesTopHeader = false;
	
	public static void toString(StringBuilder sb, λ x, Function<λ,String> funcToNameOrNull){
		String name = funcToNameOrNull.apply(x);
		if(name != null){
			sb.append(name);
		}else{
			sb.append('(');
			toString(sb, x.l(), funcToNameOrNull);
			sb.append(' ');
			toString(sb, x.r(), funcToNameOrNull);
			sb.append(')');
		}
	}
	
	/** needs a name if it has more than 1 incoming ptr */
	public static Set<λ> whichNeedNames(λ x, boolean goPastLeaf){
		Set<λ> all = reachableFrom(x,goPastLeaf);
		Set<λ> foundOnce = new HashSet();
		Set<λ> foundMoreThanOnce = new HashSet();
		for(λ y : reachableFrom(x,goPastLeaf)) {
			if(foundOnce.contains(y)) foundMoreThanOnce.add(y);
			else foundOnce.add(y);
		}
		return Collections.unmodifiableSet(foundMoreThanOnce);
	}
	
	public static Set<λ> reachableFrom(λ x, boolean goPastLeaf){
		Set<λ> ret = new HashSet();
		reachableFrom(x,ret,goPastLeaf);
		return Collections.unmodifiableSet(ret);
	}
	
	public static void reachableFrom(λ x, Set<λ> fillMe, boolean goPastLeaf){
		fillMe.add(x);
		if(!goPastLeaf) return;
		if(!fillMe.contains(x.l())) reachableFrom(x.l(),fillMe,goPastLeaf); //also gets identityFunc which is child of leaf
		if(!fillMe.contains(x.r())) reachableFrom(x.r(),fillMe,goPastLeaf);
	}

}
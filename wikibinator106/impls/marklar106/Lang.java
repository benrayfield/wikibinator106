package wikibinator106.impls.marklar106;
import static wikibinator106.impls.marklar106.ImportStatic.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import immutable.util.Text;
import wikibinator106.spec.Op;
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
		names.put(u, "λ"); //cleanLeaf
		names.put(U, "Λ"); //dirtyLeaf
		names.put(uu, "λλ");
		names.put(isleaf, "isleaf");
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
		names.put(funcOf2ParamsCallsItselfRecursively, "funcOf2ParamsCallsItselfRecursively");
		names.put(lazig, "lazig");
		names.put(and, "and");
		names.put(or, "or");
		names.put(xor, "xor");
		names.put(not, "not");
		names.put(ifElse, "ifElse");
		Set<λ> needNamesCuzOccurMultipleTimes_mutable = new HashSet(whichNeedNames(x,goPastLeaf));
		needNamesCuzOccurMultipleTimes_mutable.removeAll(names.values());
		
		needNamesCuzOccurMultipleTimes_mutable.clear(); //FIXME remove this line but its generating the wrong code like equals function starts with (i instead of (c2
		
		//names map gets first priority, but if it doesnt have a name then use this
		Map<λ,String> everythingGetsAName = new HashMap();
		
		//for(λ y : needNamesCuzOccurMultipleTimes_mutable){
		for(λ y : reachableFrom(x,goPastLeaf)){
			everythingGetsAName.put(y, "n"+(count++));
			//if(!names.containsKey(y)){
			//	names.put(y, "n"+(count++));
			//}
		}
		for(int i=2; i<=16; i++) names.put(c(i), "c"+i); //FIXME should start at i=1
		
		for(int i=0; i<=ImportStatic.maxQ; i++) names.put(q(i), "q"+i);
		//TODO all other fn fields in ImportStatic have names, and todo copy them to an immutable map there instead of here
		StringBuilder sb = new StringBuilder();
		Set<λ> rememberWhatWasDisplayedTwice = new HashSet();
		
		toString(false, false, sb, x, needNamesCuzOccurMultipleTimes_mutable, (λ y)->names.get(y), everythingGetsAName, new HashSet(), rememberWhatWasDisplayedTwice);
		sb.setLength(0);
		//ugly hack to call toString twice, first to fill rememberWhatWasDisplayedTwice where everything gets a name,
		//then to do it again where only things that needed a name (have more than 1 incoming pointer) display that name.
		Map<λ,String> someThingsGetAName = new HashMap();
		for(λ z : rememberWhatWasDisplayedTwice) someThingsGetAName.put(z, everythingGetsAName.get(z));
		toString(false, false, sb, x, needNamesCuzOccurMultipleTimes_mutable, (λ y)->names.get(y), someThingsGetAName, new HashSet(), new HashSet());
		
		
		String s = sb.toString();
		//s = removeSomeParens(s);
		return Options.funcTostringIncludesHeader ? s+"_"+Marklar106bId.toDetailString(x.marklar106bHeader()) : s;
	}
	
	/** exponential size and not human readable, but very simple. useful to show how complex syntaxes
	refer to a 2-way forest of calls of the universal function.
	*/
	public static String toStringCallpairsOnly(fn x){
		StringBuilder sb = new StringBuilder();
		toStringCallpairsOnly(
			x,
			sb,
			(fn y)->{
				if(y.isCleanLeaf()) return "λ";
				if(y.isDirtyLeaf()) return "Λ";
				return null;
			}
		);
		return sb.toString();
	}
	
	static void toStringCallpairsOnly(fn x, StringBuilder sb, Function<fn,String> names){
		String name = names.apply(x);
		if(name == null){ //recurse
			sb.append('(');
			toStringCallpairsOnly(x.l(), sb, names);
			sb.append(' ');
			toStringCallpairsOnly(x.r(), sb, names);
			sb.append(')');
		}else{
			sb.append(name);
		}
		
	}
	
	
	
	/** displayAsLeftChild causes (((a b) c) d) to appear as (a b c d). its true for left child, false for right.
	Removes from needNamesCuzOccurMultipleTimes_mutable after displaying each the first time,
	and after that uses its name.
	Things that have a name but are not in needNamesCuzOccurMultipleTimes_mutable use the name every time.
	*/
	public static void toString(boolean inSSyntax, boolean displayAsLeftChild, StringBuilder sb, λ x,
			Set<λ> needNamesCuzOccurMultipleTimes_mutable, Function<λ,String> funcToNameOrNull,
			Map<λ,String> everythingGetsAName, Set<λ> rememberWhatWasDisplayed, Set<λ> rememberWhatWasDisplayedTwice){
		String name = funcToNameOrNull.apply(x);
		boolean wasIn_needNamesCuzOccurMultipleTimes_mutable = needNamesCuzOccurMultipleTimes_mutable.remove(x);
		if(name != null /*&& !wasIn_needNamesCuzOccurMultipleTimes_mutable*/){
			sb.append(name);
		}else{
			name = everythingGetsAName.get(x);
			
			boolean displayName = name != null;
			boolean displayName_is_false_cuz_uglyHackT = false;
			if(x.l()==t && funcToNameOrNull.apply(x.r())!=null){
				//Example: instead of (t l)#n48 display it as ,l
				displayName = false;
				displayName_is_false_cuz_uglyHackT = true;
			}
			
			if(displayName) displayAsLeftChild = false; //avoid multiple names appearing consecutively not separated by () or {}
			
			if(rememberWhatWasDisplayed.contains(x)){
				sb.append(name);
				rememberWhatWasDisplayedTwice.add(x);
			}else{
				if(x.l() == t && !displayName){ //display (t (t (t x))) as ,,,x
					//FIXME ,l#n35 should appear as (t l)#n35 but without a name ,l is ok.
					sb.append(",");
					toString(false, false, sb, x.r(), needNamesCuzOccurMultipleTimes_mutable, funcToNameOrNull, everythingGetsAName, rememberWhatWasDisplayed, rememberWhatWasDisplayedTwice);
				}else if(x.l().l() == s){ //display (s a (s b c)) as {a {b c}}
					//op6Bits() == Op.trecurse.op6Bits && x.isHalted() && x.isClean().
					//TODO !isClean form has a syntax too, prefix all dirty thigns with some char?
					if(!displayAsLeftChild){
						sb.append('{');
					}
					toString(true, true, sb, x.l().r(), needNamesCuzOccurMultipleTimes_mutable, funcToNameOrNull, everythingGetsAName, rememberWhatWasDisplayed, rememberWhatWasDisplayedTwice); //a in (s a b)
					sb.append(' ');
					toString(true, false, sb, x.r(), needNamesCuzOccurMultipleTimes_mutable, funcToNameOrNull, everythingGetsAName, rememberWhatWasDisplayed, rememberWhatWasDisplayedTwice); //b in (s a b)
					if(!displayAsLeftChild) {
						sb.append('}');
					}
				}else{
					if(!displayAsLeftChild || inSSyntax){ //check for inSSyntax so {(a b) c} doesnt display as {a b c} meaning {{a b} c}.
						sb.append('(');
					}
					toString(false, true, sb, x.l(), needNamesCuzOccurMultipleTimes_mutable, funcToNameOrNull, everythingGetsAName, rememberWhatWasDisplayed, rememberWhatWasDisplayedTwice);
					sb.append(' ');
					toString(false, false, sb, x.r(), needNamesCuzOccurMultipleTimes_mutable, funcToNameOrNull, everythingGetsAName, rememberWhatWasDisplayed, rememberWhatWasDisplayedTwice);
					if(!displayAsLeftChild || inSSyntax){
						sb.append(')');
					}	
				}
				if(displayName){
					sb.append("#"+name);
				}
				if(!displayName_is_false_cuz_uglyHackT){
					rememberWhatWasDisplayed.add(x);
				}
			}
			
			/*if(wasIn_needNamesCuzOccurMultipleTimes_mutable){
				if(name == null) throw new RuntimeException("Was in needNamesCuzOccurMultipleTimes_mutable but had no name");
				sb.append('#').append(name);
			}*/
		}
	}
	
	/** change (((a b) c) d) to (a b c d), etc. Throws if contains stringLiterals. TODO handle stringliterals and other syntax. *
	public static String removeSomeParens(String code){
		if(code.contains("\"")) throw new RuntimeException("TODO handle stringliterals. code["+code+"]");
		if(code.length() < 3) return code;
		
		int i = 0, j = code.length()-1;
		
		
		/*no this wont work cuz it needs to be recursive. it will generate the wrong code.
		//TODO optimize (this is bigO code.length() squared but should be linear.
		int i;
		while((i=code.lastIndexOf("((")) != -1){
			int j = code.indexOf(")",i);
			if(j == -1) throw new RuntimeException("Parens dont match in code["+code+"]");
			//after the last (( replace that with ( and remove the next )
			code = code.substring(0,i+1)+code.substring(i+2,j)+code.substring(j+1);
		}
		return code;
		*/
		
		/*int parensDeepSinceNonparen = 0;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<code.length(); i++){
			char c = code.charAt(i);
			switch(c){
			case '(':
				if(parensDeepSinceNonparen == 0){
					sb.append(c);
				}
				parensDeepSinceNonparen++;
			break;
			case ')':
				parensDeepSinceNonparen = Math.max(0, parensDeepSinceNonparen-1);
				if(parensDeepSinceNonparen == 0){
					sb.append(c);
				}
			break;
			default:
				sb.append(c);
			}
		}*
	}*/
	
	/** needs a name if it has more than 1 incoming ptr */
	public static Set<λ> whichNeedNames(λ x, boolean goPastLeaf){
		Set<λ> all = reachableFrom(x,goPastLeaf);
		Set<λ> foundOnce = new HashSet();
		Set<λ> foundMoreThanOnce = new HashSet();
		for(λ y : all){
			if(foundOnce.contains(y.l())) foundMoreThanOnce.add(y.l());
			else foundOnce.add(y.l());
			if(foundOnce.contains(y.r())) foundMoreThanOnce.add(y.r());
			else foundOnce.add(y.r());
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
		if(!goPastLeaf && x.isLeaf()) return;
		if(!fillMe.contains(x.l())) reachableFrom(x.l(),fillMe,goPastLeaf); //also gets identityFunc which is child of leaf
		if(!fillMe.contains(x.r())) reachableFrom(x.r(),fillMe,goPastLeaf);
	}

}
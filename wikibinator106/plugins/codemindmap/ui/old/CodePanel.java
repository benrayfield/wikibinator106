package wikibinator106.plugins.codemindmap.ui.old;
import java.awt.Color;
import javax.swing.JPanel;
import mutable.util.Var;

public class CodePanel extends JPanel{
	
	public final Var<Node> getNode;
	
	//FIXME where does the other UI state go? Node isnt enough.
	
	public CodePanel(Var<Node> getNode){
		this.getNode = getNode;
		setBackground(new Color(0xffcccccc));
	}

}

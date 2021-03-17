package wikibinator106.plugins.codemindmap.ui.old;
import java.awt.Color;

import javax.swing.JList;

import mutable.util.Rand;

public class PrilistPanel extends JList{
	
	public final boolean flipVertical;
	
	public PrilistPanel(boolean flipVertical){
		super(new String[]{"testA","testB","testC"});
		this.flipVertical = flipVertical;
		setBackground(new Color(0xff000000|Rand.strongRand.nextInt(0x1000000)));
		setDragEnabled(true);
	}

}

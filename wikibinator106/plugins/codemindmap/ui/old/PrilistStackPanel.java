package wikibinator106.plugins.codemindmap.ui.old;

import java.awt.BorderLayout;
import java.awt.Color;
import java.security.SecureRandom;

import javax.swing.JPanel;

import mutable.util.Rand;

/** has a stack JList and a prilist JList, similar to in https://github.com/benrayfield/listweb PrilistPanel */
public class PrilistStackPanel extends JPanel{
	
	public final boolean flipVertical;
	
	public final PrilistPanel prilistPanel;
	
	public final StackPanel stackPanel;
	
	public PrilistStackPanel(boolean flipVertical){
		this.flipVertical = flipVertical;
		setLayout(new BorderLayout());
		prilistPanel = new PrilistPanel(flipVertical);
		stackPanel = new StackPanel(flipVertical);
		if(flipVertical){
			//add(prilistPanel, BorderLayout.CENTER);
			JPanel prilistContainer = new JPanel(new BorderLayout());
			JPanel empty = new JPanel();
			empty.setBackground(prilistPanel.getBackground());
			prilistContainer.add(empty, BorderLayout.CENTER);
			prilistContainer.add(prilistPanel, BorderLayout.SOUTH);
			add(prilistContainer);
			add(stackPanel, BorderLayout.SOUTH);
		}else{
			add(stackPanel, BorderLayout.NORTH);
			add(prilistPanel, BorderLayout.CENTER);
		}
	}

}

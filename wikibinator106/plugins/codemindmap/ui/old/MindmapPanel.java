package wikibinator106.plugins.codemindmap.ui.old;
import java.awt.GridLayout;
import javax.swing.JPanel;
public class MindmapPanel extends JPanel{
	
	public MindmapPanel(){
		setLayout(new GridLayout(2,3));
		add(new PrilistStackPanel(true));
		add(new VoxelPanel());
		add(new PrilistStackPanel(true));
		add(new PrilistStackPanel(false));
		add(new CodePanel(null));
		add(new PrilistStackPanel(false));
	}

}

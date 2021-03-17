package wikibinator106.plugins.codemindmap.ui.old;

import javax.swing.JFrame;

public class Start{
	
	public static void main(String[] args){
		MindmapPanel mmp = new MindmapPanel();
		JFrame window = new JFrame("Wikibinator106");
		window.add(mmp);
		window.setSize(800,700);
		window.setLocation(200, 200);
		window.setVisible(true);
	}

}

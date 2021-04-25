package estructuraTP;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import estructuraTP.vista.PanelPrincipal;

public class App {

	private static int width = 650;
	private static int height = 470;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-width/2, dim.height/2-height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Historiales Clínicos");
		PanelPrincipal panel = new PanelPrincipal(frame);
		frame.setContentPane(panel);
		frame.setVisible(true);	
		}
}

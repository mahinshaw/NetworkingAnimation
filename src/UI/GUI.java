package UI;

import java.awt.Frame;

import javax.swing.*;

public class GUI extends JFrame {

	public GUI(){
		createMain();
	}
	
	private void createMain(){
		//set up the window properties
		setTitle("Networking Animation");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultLookAndFeelDecorated(true);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		GUI start = new GUI();
	}
}

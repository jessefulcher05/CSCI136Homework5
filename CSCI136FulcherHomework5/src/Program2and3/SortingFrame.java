package Program2and3;

import java.io.FileNotFoundException;

import javax.swing.JFrame;


public class SortingFrame {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		JFrame SortingFrame = new JFrame();
		
		//build frame
		SortingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SortingFrame.setTitle("Kid Sorting");
		
		//add panel
		SortingPanel myPanel = new SortingPanel();
		SortingFrame.getContentPane().add(myPanel);
		
		// pack
		SortingFrame.pack();
		
        // set visibility to true
		SortingFrame.setVisible(true);
	}

}

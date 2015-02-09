package Program1;

import javax.swing.JFrame;


public class BlobFighterFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame BlobFrame = new JFrame();
		
		//build frame
		BlobFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BlobFrame.setTitle("Blob Fighter");
		
		//add panel
		BlobFighterPanel myPanel = new BlobFighterPanel();
		BlobFrame.getContentPane().add(myPanel);
		
		// pack
		BlobFrame.pack();
		
        // set visibility to true
		BlobFrame.setVisible(true);
	}

}

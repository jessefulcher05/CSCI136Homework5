package Program1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BlobFighterPanel extends JPanel{
	
	//start game button
	startGameListener startListener = new startGameListener();
	JButton startButton = new JButton("Start Game");
	
	//good guy monster
	ImageIcon goodGuy = new ImageIcon("monster.png");
	
	//bullet
	ImageIcon bullet = new ImageIcon("bullet.png");
	
	//bad monster1
	ImageIcon monster1 = new ImageIcon("badguy.png");
	
	//bad monster2
	ImageIcon monster2 = new ImageIcon("monster2.jpg");
	
	//bad monster3
	ImageIcon monster3 = new ImageIcon("monster3.jpg");
	
	//winner
	ImageIcon winner = new ImageIcon("youwin.jpg");
	
	//monster 1 coordinates
	int mon1x = 1000;
	int mon1y = 300;
	
	//good guy coordinates
	int goodx = 0;
	int goody = 350;
	
	//bullet image coordinates
	int bulletx = goodx + 150; //so that it is equal with the good monster
	int bullety = 450;
	
	//game timer
	gameTimer myGameTimer = new gameTimer();
	Timer gameTimer = new Timer(10,myGameTimer);
	
	//bad monster1 timer
	boolean mon1bool = true;
	monster1ListenerTimer monster1Listener = new monster1ListenerTimer();
	Timer moveTimer = new Timer(100,monster1Listener);
	
	//create keylistener so we can move the good monster
	KeyListenerAction myKeyActionListener = new KeyListenerAction();
	
	//create main panel for the fighting
	JPanel mainPanel = new JPanel();
	
	//create lables for score
	int playerLifeInt = 100;
	int monsterLifeInt = 100;
	
	JLabel playerTitle = new JLabel("");
	
	int monNum = 1;
	JLabel monster1Title = new JLabel("Monster "+ monNum +" Life: ");
	//JLabel monster1Life = new JLabel();
	


	public BlobFighterPanel()
	{
		//add button
		startButton.addActionListener(startListener);
		add(startButton);
		
		//add info panel
		JPanel infoPanel = new JPanel();
		add(infoPanel);
		
		//add info to infopanel
		playerTitle = new JLabel("Player Life: " + playerLifeInt);
		monster1Title = new JLabel("Monster " + monNum + " Life: " + monsterLifeInt);

		
		infoPanel.add(playerTitle);
		infoPanel.add(monster1Title);

		
		//creates new panel for monsters to be in so that i can manipulate it
		mainPanel = new JPanel();
		add(mainPanel);
		
		
		//add key listener so that i can move my goodmonster
		mainPanel.addKeyListener(myKeyActionListener);
		
		setPreferredSize(new Dimension(1300,600)); //set size of main panel
	}
	
	public void paintComponent(Graphics g) { 
		
		super.paintComponent(g);
		
		monster1.paintIcon(mainPanel,g,mon1x,mon1y);
		goodGuy.paintIcon(mainPanel,g,goodx,goody);
		bullet.paintIcon(mainPanel, g, bulletx, bullety);
	
	}
	boolean bulletShot = false;
	private class gameTimer implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(goodx >= mon1x-150) //when bad monster hits good monster he is shot back and should lose life
			{
				returMinGoodX(200);//method to return goodx that has catch for the min value
				//lose player life
				playerLifeInt -= 10;
				playerTitle.setText("Player Life: "+ playerLifeInt);
			}
			
			if (bulletShot == true)
			{
				shoot();
			}
		}
	}

	private class monster1ListenerTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			//moves monster IF x coordinate is less than 650
			if(mon1bool == true)
			if (mon1x < 150)
			{
				mon1x = mon1x;
			}
			else
			{
				mon1x -= 6;
			}	
			repaint();
		}
	}
	
	private class startGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			//starts move timer
			moveTimer.start();
			//start gametimer
			gameTimer.start();
			
			//sets focus to panel so the image icon can move AFTER game is started
			mainPanel.requestFocus();

		} //end actionPerformed
	}//end startGameLIstener
	
	public class KeyListenerAction implements KeyListener  {

		public void keyPressed (KeyEvent event)
		{
			switch(event.getKeyCode())
			{
			case KeyEvent.VK_LEFT: //on left press (move back)
				returMinGoodX(10);//method to return goodx that has catch for the min value
				break;
			case KeyEvent.VK_RIGHT: //on right press (move forward)
				if (goodx > (mon1x-150))
				{
					goodx = goodx;
				}
				else
				{
					goodx += 10;
					bulletx = goodx + 150;
					bulletShot = false;
				}
				break;
			case KeyEvent.VK_SPACE: //on space bar (shoot)
				//logic to shoot
				bulletShot = true;

			}//end switch
			
			repaint();
		}//end keypressed
		
		public void keyTyped(KeyEvent event){} //must implament so we have to have this
		public void keyReleased(KeyEvent event){} //must implament so we have to have this
		
	}
	
	int test = 0;
	//method to "shoot" the bullet
	public void shoot()
	{
		if(bulletx <= mon1x)
		{
			bulletx +=15;
			repaint();
		}
		else if(bulletx >= mon1x)
		{
			bulletx = goodx +150;
			bulletShot = false;
			
			//deduct monster life
			if (monsterLifeInt > 0)
			{
				monsterLifeInt -= 20;
				monster1Title.setText("Monster "+ monNum +" Life: " + monsterLifeInt);
			}
			else if(monsterLifeInt == 0) //to make sure monster life can't go below negative
			{
				monsterLifeInt = 0;
				monster1Title.setText("Monster "+ monNum +" Life: " + monsterLifeInt);
			}
			
			
			if (monsterLifeInt == 0)
			{
				if (test == 0)
				{
					mon1x = 1000; //once dead moves monster back to start
					monster1.setImage(monster2.getImage()); //changes image to next monster
					monNum += 1; //adds 1 to monster number
					monsterLifeInt = 100; //resets monster life to 100
					monster1Title.setText("Monster "+ monNum +" Life: " + monsterLifeInt); //changes jlabel to reflect new monsters life
					test = 1;
					//System.out.println(test);
				}
				else if (test == 1)
				{
					mon1x = 1000; //once dead moves monster back to start
					monster1.setImage(monster3.getImage());
					monNum += 1; //adds 1 to monster number
					monsterLifeInt = 100; //resets monster life to 100
					monster1Title.setText("Monster "+ monNum +" Life: " + monsterLifeInt); //changes jlabel to reflect new monsters life
					test = 2;
					//System.out.println(test);
				}
				else if (test == 2)
				{
					monster1.setImage(winner.getImage());
					moveTimer.stop();
				}
 
			}

		}
	}
	
	//pass in how much he will fall back while giving a minimum he can
	public int returMinGoodX(int backAmount)
	{
		if (goodx <= 0)
		{
			goodx = 0;
		}
		else
		{
			goodx -= backAmount;
			if(goodx <= 0)
			{
				goodx = 0; //sets it so good will never go past 0
			}
			bulletx = goodx + 150;
			bulletShot = false;
		}
		return goodx;
	}
}

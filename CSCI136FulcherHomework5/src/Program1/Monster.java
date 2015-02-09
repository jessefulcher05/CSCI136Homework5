package Program1;

public class Monster {
	
	String imgPath;
	int monx, mony;
	
	public Monster(String imagePath, int monx, int mony)
	{
		this.imgPath = imgPath;
		this.monx = monx;
		this.mony = mony;
	}

	public int getX()
	{
		return monx;
	}
}

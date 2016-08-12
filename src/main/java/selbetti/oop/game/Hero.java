package selbetti.oop.game;

public class Hero {

	public Hero( int pPositionX, int pPositionY, Directions pHeadAngle )
	{
		this.positionX = pPositionX;
		this.positionY = pPositionY;
		this.headAngle = pHeadAngle;
	}

	int positionX;
	int positionY;
	Directions headAngle;
	boolean isAlive = true;
}

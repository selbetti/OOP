package selbetti.oop.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors( fluent = true )
@RequiredArgsConstructor
public class Board {

	final int width;
	final int height;
	final int movimentLimit;
	final Directions movimentAngle;

	public void actionHero( HeroActions move, Hero hero )
	{
		switch ( move ) {
			case Move: {
				move( hero );
				break;
			}
			case Ro
		}
	}

	private void move( Hero hero )
	{
		switch ( hero.headAngle ) {
			case Up:
				hero.positionY = alterPositionY( hero.positionY, -movimentLimit );
			break;
			case Right:
				hero.positionX = alterPositionX( hero.positionX, movimentLimit );
			break;
			case Down:
				hero.positionY = alterPositionY( hero.positionY, movimentLimit );
			break;
			case Left:
				hero.positionX = alterPositionX( hero.positionX, -movimentLimit );
			break;
		}
	}

	private int alterPositionX( int positionHero, int moviment )
	{
		final int newX = positionHero + moviment;
		if ( newX >= 0 && newX < width )
			return newX;
		return positionHero;
	}

	private int alterPositionY( int positionHero, int moviment )
	{
		final int newY = positionHero + moviment;
		if ( newY >= 0 && newY < height )
			return newY;
		return positionHero;
	}
}

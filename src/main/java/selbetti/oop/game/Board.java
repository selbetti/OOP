package selbetti.oop.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.Accessors;

@Getter
@Accessors( fluent = true )
@RequiredArgsConstructor
public class Board {

	static final int LEFT = Directions.values().length - 1;
	static final int RIGHT = 1;

	final List<Hero> heroes;
	final int width;
	final int height;
	final int movimentLimit;

	public void actionHero( HeroActions action, int id ) {
		val hero = heroes.get( id );
		switch ( action ) {
			case Move: move( hero ); break;
			case RotateLeft: rotate( hero, LEFT ); break;
			case RotateRight: rotate( hero, RIGHT ); break;
			case Shoot: shoot( hero ); break;
			default:
				throw new UnsupportedOperationException( action.toString() );
		}
	}

	private void shoot( Hero hero ) {
		switch ( hero.headAngle )
		{
			case Right:
				shootEnemy(  h -> ( h.positionY == hero.positionY && h.positionX > hero.positionX ),  this::compareByRow  );
			break;
			case Left:
				shootEnemy(  h -> ( h.positionY == hero.positionY && h.positionX < hero.positionX ), this::compareByRowReverse );
			break;
			case Down:
				shootEnemy(  h -> ( h.positionX == hero.positionX && h.positionY > hero.positionY ), this::compareByRow );
			break;
			case Up:
				shootEnemy(  h -> ( h.positionX == hero.positionX && h.positionY < hero.positionY ), this::compareByRowReverse );
			break;
			default:
				throw new UnsupportedOperationException( hero.headAngle.toString() );
		}
	}


	private List<Hero> orderList( Comparator<Hero> compare ) {
		val result = new ArrayList<>(heroes);
		result.sort( compare );
		return result;
	}

	
	int compareByRow( Hero one, Hero two ) {
		int v = Integer.compare( one.positionX, two.positionX );
		if ( v == 0 )
			v = Integer.compare( one.positionY, two.positionY );
		return v;
	}

	int compareByRowReverse( Hero one, Hero two ) {
		int v = Integer.compare( two.positionX, one.positionX );
		if ( v == 0 )
			v = Integer.compare( two.positionY, one.positionY );
		return v;
	}

	private void rotate( Hero hero, final int moveFactor ) {
		val tempNewPosition = ( hero.headAngle.ordinal() + moveFactor ) % Directions.values().length;
		hero.headAngle = Directions.values()[tempNewPosition];
	}

	private void move( Hero hero )
	{
		switch ( hero.headAngle ) {
			case Up:
				hero.positionY = alterPositionY( hero, -movimentLimit );
			break;
			case Right:
				hero.positionX = alterPositionX( hero, movimentLimit );
			break;
			case Down:
				hero.positionY = alterPositionY( hero, movimentLimit );
			break;
			case Left:
				hero.positionX = alterPositionX( hero, -movimentLimit );
			break;
		}
	}

	private int alterPositionX( Hero hero, int moviment )
	{
		final int newX = hero.positionX + moviment;
		if ( newX >= 0 && newX < width )
		{
			final Hero foundedHero = find(heroes, h -> h.positionX == newX && h.positionY == hero.positionY );
			if ( foundedHero == null )
				return newX;
		}
		return hero.positionX;
	}

	private int alterPositionY( Hero hero, int moviment )
	{
		final int newY = hero.positionY + moviment;
		if ( newY >= 0 && newY < height )
		{
			final Hero foundedHero = find(heroes, h -> h.positionY == newY && h.positionX == hero.positionX );
			if ( foundedHero == null )
				return newY;
		}
		return hero.positionY;
	}

	private Hero find( List<Hero> lstHeroes, Function<Hero, Boolean> enemyMatcher ) {

		for ( final Hero otherHero : lstHeroes )
			if ( otherHero.isAlive && enemyMatcher.apply( otherHero ) )
				return otherHero;

		return null;
	}

	void shootEnemy(Function<Hero, Boolean> enemyMatcher, Comparator<Hero> compare ) {

		final List<Hero> orderList = orderList( compare );
		final Hero foundedHero = find( orderList, enemyMatcher );
		if(foundedHero != null)
			foundedHero.isAlive = false;
	}

	public void addHero() {
		heroes.add(createHero());
	}

	Hero createHero() {
		return (heroes.size() == 0) ?
				new Hero(0, 0, Directions.Down) :
				new Hero(height - 1, width - 1, Directions.Up);
	}

	public boolean hasEnoughPlayersToContinueTheGame() {
		int qtHeroes = 0;
		for(val hero : heroes){
			if(hero.isAlive) qtHeroes++;
		}
		return qtHeroes > 1;
	}

	public static Board create(int size){
		return new Board(new ArrayList<>(),size,size,1);
	}
}

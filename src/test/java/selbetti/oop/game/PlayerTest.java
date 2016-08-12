package selbetti.oop.game;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import lombok.val;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void ensureThatPlayerANextCommandIsCorrect()
	{
		final List<HeroActions> actionSequence = Arrays.asList( HeroActions.Move, HeroActions.RotateRight, HeroActions.Shoot );

		final Player player = new Player( actionSequence );

		for ( val expectedAction : actionSequence )
			assertEquals( expectedAction, player.getNextAction() );

	}

	@Test
	public void ensureThatPlayerBNextCommandIsCorrect()
	{
		final List<HeroActions> actionSequence = Arrays.asList( HeroActions.RotateRight, HeroActions.Shoot, HeroActions.Move );

		final Player player = new Player( actionSequence );

		for ( val expectedAction : actionSequence )
			assertEquals( expectedAction, player.getNextAction() );
	}
}

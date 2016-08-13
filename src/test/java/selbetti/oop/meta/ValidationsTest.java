package selbetti.oop.meta;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 */
public class ValidationsTest {

	@Test
	public void ensureThatCanValidateEmail(){
		String email = "douglas.schuelter@selbetti.com.br";
		assertTrue( Validations.isValidEmail( email ) );
	}

	@Test
	public void ensureThatCanNotValidateAnInvalidEmail(){
		String email = "douglas.schuelter4selbetti.com.br";
		assertFalse( Validations.isValidEmail( email ) );
	}

	@Test
	public void ensureThatCanNotValidateAnInvalidEmailWithTwoArroba(){
		String email = "douglas.schuelter@selbetti@.com.br";
		assertFalse( Validations.isValidEmail( email ) );
	}

	@Test
	public void ensureThatCanNotValidateAnInvalidEmailWithTwoColon(){
		String email = "douglas.schuelter@selbetti:com.br";
		assertFalse( Validations.isValidEmail( email ) );
	}

	@Test
	public void ensureThatStringIsAtLeast3CharactersLonger(){
		String str = "Hello";
		assertTrue( Validations.isAtLeastLonger( str, 3 ) );
	}

	@Test
	public void ensureThatStringIsNot30CharactersLonger(){
		String str = "Hello";
		assertFalse( Validations.isAtLeastLonger( str, 30 ) );
	}

	@Test
	public void ensureThatStringIsAtMost30CharactersLonger(){
		String str = "Hello";
		assertTrue( Validations.isAtMostLonger( str, 30 ) );
	}

	@Test
	public void ensureThatStringIsNotAtMost3CharactersLonger(){
		String str = "Hello";
		assertFalse( Validations.isAtMostLonger( str, 3 ) );
	}

	@Test
	public void ensureThatStringDoesNotContainsSpace(){
		String str = "Hello";
		assertTrue( Validations.doesNotContains( str, " " ) );
	}

	@Test
	public void ensureThatStringContainsSpace(){
		String str = "Hello World";
		assertFalse( Validations.doesNotContains( str, " " ) );
	}
}

package selbetti.oop.big;

import static selbetti.oop.big.TestUtils.*;
import static org.junit.Assert.assertTrue;

import java.io.File;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

public class ExecutorTest {

	@Before
	public void cleanup()
	{
		deleteRecursively( new File( "target/test" ) );
	}

	@Test
	@SneakyThrows
	public void ensureThatExecuteTheJob()
	{
		final Executor exec = new Executor( "target/test", "src/test/resources/file2lines.txt" );
		exec.runJob();

		assertTrue( new File( "target/test/4d39a692-dc6f-11e3-8db0-a41731050d2d.txt" ).exists() );
		assertTrue( new File( "target/test/4d384310-dc6f-11e3-8db0-a41731050d2d.txt" ).exists() );
	}

}

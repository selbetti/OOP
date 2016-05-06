package selbetti.oop.big;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static selbetti.oop.big.TestUtils.readFile;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

public class RequestWriterTest {

	final List<Request> requestListUser1 = new ArrayList<>();

	@Before
	public void before() throws ParseException {
		requestListUser1
				.add( Request
						.loadObject( "177.126.180.83 - - [15/04/2016 19:45:12.357332 -0300] \"GET /meme.jpg HTTP/1.1\" 200 2148 \"-\" \"userid=4d39a692-dc6f-11e3-8db0-a41731050d2d\"" ) );
		requestListUser1
				.add( Request
						.loadObject( "177.126.180.83 - - [14/04/2016 19:45:12.357332 -0300] \"GET /meme.jpg HTTP/1.1\" 200 2148 \"-\" \"userid=4d39a692-dc6f-11e3-8db0-a41731050d2d\"" ) );
	}

	@Test
	@SneakyThrows
	public void ensureThatUserFileWasCreated()
	{
		final RequestWriter rwriter = RequestWriter.forceCreateFromFolder( new File( "target/test" ) );
		rwriter.sortAndWrite( "4d39a692-dc6f-11e3-8db0-a41731050d2d", requestListUser1 );

		assertTrue( new File( "target/test/4d39a692-dc6f-11e3-8db0-a41731050d2d.txt" ).exists() );
	}

	@Test
	@SneakyThrows
	public void ensureThatUserFileIsSorted()
	{
		final RequestWriter rwriter = RequestWriter.forceCreateFromFolder( new File( "target/test" ) );
		rwriter.sortAndWrite( "4d39a692-dc6f-11e3-8db0-a41731050d2d", requestListUser1 );

		final String actualFile = readFile( "target/test/4d39a692-dc6f-11e3-8db0-a41731050d2d.txt" );
		final String expectedFile = readFile( "src/test/resources/expectedFileWrite.txt" );
		assertEquals( expectedFile, actualFile );
	}
}

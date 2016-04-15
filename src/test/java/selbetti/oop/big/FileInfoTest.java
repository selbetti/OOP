package selbetti.oop.big;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FileInfoTest {

	private static final String FIRST_LINE = "177.126.180.83 - - [08/Apr/2016:18:19:05.268007 -0300] \"GET /meme.jpg HTTP/1.1\" 200 2148 \"-\" \"userid=4d3acd7e-dc6f-11e3-8db0-a41731050d2d\"";

	final String filePath = "src/test/resources/Teste.txt";

	@Test
	public void ensureReadLine()
	{
		final FileInfo file = new FileInfo( new File( filePath ) );
		final String line = file.readLine();
		assertNotNull( line );

		assertEquals( FIRST_LINE, line );
	}

}

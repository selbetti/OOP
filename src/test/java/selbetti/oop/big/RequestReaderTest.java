package selbetti.oop.big;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.File;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.Test;

public class RequestReaderTest {

	final String filePath = "src/test/resources/Teste.txt";
	final RequestReader file = new RequestReader( new File( filePath ) );

	@Test
	public void ensureReadLineRequest() throws ParseException
	{
		final Request req = file.readRequest();
		assertNotNull( req );
		assertEquals( "4d39a692-dc6f-11e3-8db0-a41731050d2d", req.name );
		assertEquals( 1460760669332L, req.date.getTime() );
	}

	@Test
	public void ensureThatCanReadTheEntireFile() throws ParseException {
		final RequestReaderListener<Request> mock = mock( RequestReaderListener.class );
		file.readEntireFile( mock );
		verify( mock, times( 10 ) ).accept( any() );
	}

	@Test
	public void ensureThatCanReadTwoFixedLine() throws ParseException {
		final RequestReader file = new RequestReader( new File( "src/test/resources/file2lines.txt" ) );
		final RequestReaderListener<Request> mock = mock( RequestReaderListener.class );
		file.readEntireFile( mock );
		verify( mock, times( 2 ) ).accept( any() );
	}

	@Test
	public void ensureThatCanReadEmptyFile() throws ParseException {
		final RequestReader file = new RequestReader( new File( "src/test/resources/emptyFile.txt" ) );
		final RequestReaderListener<Request> mock = mock( RequestReaderListener.class );
		file.readEntireFile( mock );
		verify( mock, never() ).accept( any() );
	}

	@Test
	public void ensureThatReceivedFinishEvent() throws ParseException
	{
		final RequestReader file = new RequestReader( new File( "src/test/resources/emptyFile.txt" ) );
		final RequestReaderListener<Request> mock = mock( RequestReaderListener.class );
		file.readEntireFile( mock );
		verify( mock ).finish();
	}
}

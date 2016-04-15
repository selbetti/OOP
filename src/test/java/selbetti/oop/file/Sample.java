package selbetti.oop.file;

import java.io.RandomAccessFile;

import lombok.SneakyThrows;

import org.junit.Test;

public class Sample {

	@Test
	@SneakyThrows
	public void so() {
		// create a new RandomAccessFile with filename test
		final RandomAccessFile raf = new RandomAccessFile( "target/test.txt", "rw" );

		// write something in the file
		raf.writeUTF( "Hello World" );

		// set the file pointer at 0 position
		raf.seek( 0 );

		// print the string
		System.out.println( raf.readUTF() );

		// set the file pointer at 5 position
		raf.seek( 7 );

		// write something in the file
		raf.writeUTF( "This is an example" );
		raf.writeUTF( "This is an example" );

		// set the file pointer at 0 position
		raf.seek( 0 );

		// print the string
		System.out.println( raf.readUTF() );
		raf.close();
	}
}

package selbetti.oop.big;

public interface RequestReaderListener<T> {
	public void accept( T req );

	public void finish();
}

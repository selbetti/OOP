package selbetti.oop.meta;

import java.lang.reflect.*;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@RequiredArgsConstructor
public class HttpClientWrapper implements InvocationHandler {

	final DefaultHttpClient client;
	final AtomicBoolean failureStatus;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		try {
			final Object result = method.invoke(client, args);
			failureStatus.set( false );
			return result;
		} catch ( Throwable cause ) {
			failureStatus.set( true );
			throw cause;
		}
	}

	@SuppressWarnings("unchecked")
	public static HttpClient create( final DefaultHttpClient client, AtomicBoolean failureStatus )
	{
		final InvocationHandler handler = new HttpClientWrapper( client, failureStatus );

		return (HttpClient) Proxy.newProxyInstance(
				client.getClass().getClassLoader(),
				new Class<?>[] { HttpClient.class },
				handler
		);
	}
}

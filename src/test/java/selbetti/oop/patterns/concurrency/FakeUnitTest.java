package selbetti.oop.patterns.concurrency;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.*;

public class FakeUnitTest {

	static final int EXPECTED = 10000000;
	final BlockingQueue<Object> queue = new LinkedBlockingDeque<>();

	@Test
	public void ensureSomething() throws InterruptedException {
		queue.take();
	}

	@Test
	public void doThreadCounting() {
		final CounterThread counterThread = new CounterThread();
		counterThread.run();
		assertEquals(EXPECTED, counterThread.i);
	}

	@Test
	@Ignore
	public void doThreadCountingInParallel() {
		final CounterThread counterThread = new CounterThread();
		counterThread.start();
		assertEquals(EXPECTED, counterThread.i);
	}

	@Test(timeout = 20000)
	@Ignore
	public void doThreadCountingInParallel2() throws InterruptedException {
		final CountDownLatch counter = new CountDownLatch(EXPECTED);
		final CounterThread counterThread = spy(new CounterThread());

		doAnswer(a -> {
			counter.countDown();
			a.callRealMethod();
			return null;
		}).when(counterThread).countDown();

		counterThread.start();
		counter.await();

		assertEquals(EXPECTED, counterThread.i);
	}

	@Test(timeout = 30000)
	@Ignore
	public void doThreadCountingInParallel3() throws InterruptedException {
		final CountDownLatch counter = new CountDownLatch(EXPECTED);
		final CounterRunnable counterThread = spy(new CounterRunnable());

		doAnswer(a -> {
			a.callRealMethod();
			counter.countDown();
			return null;
		}).when(counterThread).countDown();

		new Thread(counterThread).start();
		counter.await();

		assertEquals(EXPECTED, counterThread.i.get(), 0);
	}

	@Test(timeout = 20000)
	@Ignore
	public void doThreadCountingInParallel4() throws InterruptedException {
		final CountDownLatch counter = new CountDownLatch(EXPECTED);
		final CounterRunnable counterThread = spy(new CounterRunnable());

		doAnswer(a -> {
			a.callRealMethod();
			counter.countDown();
			return null;
		}).when(counterThread).countDown();

		final ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(counterThread);
		counter.await();

		assertEquals(EXPECTED, counterThread.i.get(), 0);
	}

	@Test(timeout = 20000)
	@Ignore
	public void doThreadCountingInParallel5() throws InterruptedException {
		final CountDownLatch counter = new CountDownLatch(EXPECTED);
		final CounterRunnable counterThread = spy(new CounterRunnable());

		doAnswer(a -> {
			a.callRealMethod();
			counter.countDown();
			return null;
		}).when(counterThread).countDown();

		final ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(counterThread);
		executorService.submit(counterThread);
		counter.await();

		assertEquals(EXPECTED, counterThread.i.get(), 0);
	}

	@Test(timeout = 20000)
	public void doThreadCountingInParallel6() throws InterruptedException, ExecutionException {
		final CounterRunnable counterThread = new CounterRunnable();
		final ExecutorService executorService = Executors.newFixedThreadPool(2);
		final Future<?> future = executorService.submit(counterThread);
		future.get();
		assertEquals(EXPECTED, counterThread.i.get(), 0);
	}

	@Test(timeout = 20000)
	public void doThreadCountingInParallel7() throws InterruptedException, ExecutionException {
		final CounterRunnable counterThread = new CounterRunnable();
		final ExecutorService executorService = Executors.newCachedThreadPool();

		final Future<?> future = executorService.submit(counterThread);
		final Future<?> future2 = executorService.submit(counterThread);
		future.get();
		future2.get();
		assertEquals(EXPECTED, counterThread.i.get(), 0);
	}

	@Test(timeout = 20000)
	public void doThreadCountingInParallel8() throws InterruptedException, ExecutionException {
		final AnotherCounter counterThread = new AnotherCounter();
		final ExecutorService executorService = Executors.newFixedThreadPool(2);
		final Future<?> future = executorService.submit(counterThread);
		assertEquals(EXPECTED, future.get());
	}

	@Test(timeout = 20000)
	public void doThreadCountingInParallel9() throws InterruptedException, ExecutionException {
		final ExecutorService executorService = Executors.newFixedThreadPool(2);
		final Future<?> future = executorService.submit(this::counter);
		assertEquals(EXPECTED, future.get());
	}

	Integer counter() {
		final AtomicInteger counter = new AtomicInteger();
		while (counter.get() < EXPECTED)
			counter.incrementAndGet();
		return counter.get();
	}
}

class CounterRunnable implements Runnable {

	final AtomicInteger i = new AtomicInteger( 0 );

	@Override
	public void run() {
		while ( i.get() < FakeUnitTest.EXPECTED )
			countDown();
	}

	int countDown() {
		return i.incrementAndGet();
	}
}

class CounterThread extends Thread {

	int i = 0;

	@Override
	public void run() {
		while ( i < FakeUnitTest.EXPECTED )
			countDown();
	}

	void countDown() {
		i++;
	}
}

class AnotherCounter implements Callable<Integer> {

	private int i = 0;

	void countDown() {
		i++;
	}

	@Override
	public Integer call() throws Exception {
		while ( i < FakeUnitTest.EXPECTED )
			countDown();
		return i;
	}

}
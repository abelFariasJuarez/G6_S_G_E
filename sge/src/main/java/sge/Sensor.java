package sge;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



 

 

public class Sensor implements Publisher<Integer> {
 
	private static final String LOG_MESSAGE_FORMAT = "Publisher >> [%s] %s%n";
 
	final ExecutorService executor = Executors.newFixedThreadPool(2);
	private List<MySubscription> subscriptions = Collections.synchronizedList(new ArrayList<MySubscription>());
 
	private final CompletableFuture<Void> terminated = new CompletableFuture<>();
 int v;
	public Sensor(int _v) {
		 v = _v;
	}
	public void setV(int _v) {
		v=_v;
		//subscriptions.forEach(sub->sub.request(1));
	}
	@Override
	public void subscribe(Subscriber<? super Integer> subscriber) {
		MySubscription subscription = new MySubscription(subscriber);
 
		subscriptions.add(subscription);
 
		subscriber.onSubscribe(subscription);
	}
 
	public void waitUntilTerminated() throws InterruptedException {
		try {
			terminated.get();
		} catch (ExecutionException e) {
			System.out.println(e);
		}
	}
 
	private class MySubscription implements Subscription {
 
	
 
		private Subscriber<? super Integer> subscriber;
		
		private AtomicBoolean isCanceled;
 
		public MySubscription(Subscriber<? super Integer> subscriber) {
			this.subscriber = subscriber;
			
 
			isCanceled = new AtomicBoolean(false);
		}
 
		@Override
		public void request(long n) {
			if (isCanceled.get())
				return;
				publishItems(n);
		}
 
		@Override
		public void cancel() {
			isCanceled.set(true);
 
			synchronized (subscriptions) {
				subscriptions.remove(this);
			
			}
		}
 
		private void publishItems(long n) {
		
 
				executor.execute(() -> {
					
					log("publish item: [" + v + "] ...");
					subscriber.onNext(v);
				});
			
		}
 
	
 
	private void log(String message, Object... args) {
		String fullMessage = String.format(LOG_MESSAGE_FORMAT, currentThread().getName(), message);
 
		System.out.printf(fullMessage, args);
	}
}
}


package sge;


 
import static java.lang.Thread.currentThread;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
 
public class Condicion implements Subscriber<Integer> {
 
	private static final String LOG_MESSAGE_FORMAT = "Subscriber %s >> [%s] %s%n";
	 

 
	private String name;
	private Subscription subscription;
	private int medicion;
 

	
	public Condicion(String name) {
		this.name = name;
	}
 
	@Override
	public void onSubscribe(Subscription subscription) {
		log("Subscribed");
		this.subscription = subscription;
		log("Requesting %d new items...", 1);
		subscription.request(1);
	}
 

 
	@Override
	public void onNext(Integer item) {
		try {
			medicion=item;
			System.out.println(medicion);
			Thread.sleep(1000);
			log("Requesting %d new items...", 1);
			subscription.request(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
 
	@Override
	public void onComplete() {
		log("Complete!");
	}
 
	@Override
	public void onError(Throwable t) {
		log("Subscriber Error >> %s", t);
	}
 
	private void log(String message, Object... args) {
		String fullMessage = String.format(LOG_MESSAGE_FORMAT, this.name, currentThread().getName(), message);
 
		System.out.printf(fullMessage, args);
	}
}
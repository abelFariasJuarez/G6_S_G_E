package sge.regla;

import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class Regla implements Subscriber<Integer> {

	private static final String LOG_MESSAGE_FORMAT = "Subscriber %s >> [%s] %s%n";

	private String name;
	private List<Subscription> subscriptions =new ArrayList<Subscription>();
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	private List<Actuador>	actuadores=new ArrayList<Actuador>();
	public Regla(String name) {
		this.name = name;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		log("Subscribed");
		subscriptions.add(subscription);
		log("Requesting new items...");
		subscription.request(1);
	}

	@Override
	public void onNext(Integer item) {

		this.accionarSiCorresponde();

	}

	public void accionarSiCorresponde() {
		//if (this.verificarCondiciones()) {
			//this.ejecutarAcciones();
		//}
System.out.println("sensor se activa");
	}

	private void ejecutarAcciones() {
		actuadores.forEach(a->a.ejecutarAccion());

	}

	private boolean verificarCondiciones() {
		return condiciones.stream().allMatch(cond -> cond.verificar());

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
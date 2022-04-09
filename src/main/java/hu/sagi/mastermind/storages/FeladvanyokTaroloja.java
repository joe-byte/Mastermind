package hu.sagi.mastermind.storages;

import java.util.HashMap;
import java.util.Map;

import hu.sagi.mastermind.domain.Feladvany;

public class FeladvanyokTaroloja {

	private static FeladvanyokTaroloja peldany = null;
	private static volatile Map<String, Feladvany> feladvanyok;
	
	
	private FeladvanyokTaroloja() {
		feladvanyok = new HashMap<>();
	}
	
	public static synchronized FeladvanyokTaroloja getInstance() {
		if (peldany == null) {
			peldany = new FeladvanyokTaroloja();
		}
		return peldany;
	}

	public synchronized Map<String, Feladvany> getFeladvany() {
		return feladvanyok;
	}

	public synchronized void setFeladvany(Feladvany feladvany) {
		feladvanyok.put(feladvany.getiD(), feladvany) ;
	}
	
	
}

package hu.sagi.mastermind;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hu.sagi.mastermind.storages.FeladvanyokTaroloja;


@Component
public class ScheduledTasks {
	
	byte ttl;
	String[] keys = new String[10];
	int index = 0;
	
	@Scheduled(fixedRate = 12000)
	public void timeCheck() {		
		
		FeladvanyokTaroloja.getInstance().getFeladvany().forEach((key, felad) -> {		
			ttl = felad.gettTL();
			System.out.println(key + "  " + felad + "   " + ttl);  
			if (ttl <= 0) {
				keys[index] = felad.getiD();
				index++;
			} else {
				ttl--;
				felad.settTL(ttl);
			}

	});
		for (String key : keys) {
			FeladvanyokTaroloja.getInstance().deleteFeladvany(key);
		}
	}
}

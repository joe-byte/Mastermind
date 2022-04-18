package hu.sagi.mastermind.services;


import java.util.Arrays;
import java.util.UUID;


import org.springframework.stereotype.Service;

import hu.sagi.mastermind.domain.Feladvany;
import hu.sagi.mastermind.domain.MastDtO;
import hu.sagi.mastermind.domain.MastResponse;
import hu.sagi.mastermind.exceptions.FullStorage;
import hu.sagi.mastermind.exceptions.NotFoundKey;
import hu.sagi.mastermind.storages.FeladvanyokTaroloja;

@Service
public class MastService {
	
	private final byte tTL = 15;
	private final int szinekSzama = 6;
	private final int feadvanyHossza = 4;
	private final String[] szinek = {"blue", "red", "yellow", "green", "white","black"};

	
	public String feladvanytLetrehoz() throws FullStorage {
		int[] feladvanyt = new int[4];
		if (FeladvanyokTaroloja.getInstance().getFeladvany().size() >= 10) {
			throw new FullStorage("Nincs tobb");
		}
		for ( int i = 0; i < feadvanyHossza; i ++ ) {
			int szam = 0;
			feladvanyt[i] = (int) (Math.random() * szinekSzama + 1);
			
			while (szam < i) {
	               if (feladvanyt[szam] == feladvanyt[i]) {
	                   i--;
	                   break;
	               }
	               szam++;
	            }
		
		}
		System.out.println(Arrays.toString(feladvanyt));
		Feladvany feladvany = new Feladvany();
		feladvany.setFeladat(feladvanyt);
		feladvany.setiD(UUID.randomUUID().toString());
		feladvany.settTL(tTL);
		FeladvanyokTaroloja.getInstance().setFeladvany(feladvany);
		System.out.println("Size: " + FeladvanyokTaroloja.getInstance().getFeladvany().size());
		FeladvanyokTaroloja.getInstance().getFeladvany().forEach((key, felad) -> System.out.println(key + "  " + felad));
		return feladvany.getiD();
	} 
	
	// Tippek ellenőrzése
	public MastResponse tippEllenorzese(MastDtO tippek) throws NotFoundKey {
		if (!FeladvanyokTaroloja.getInstance().getFeladvany().containsKey(tippek.getId())) {
			throw new NotFoundKey("Nincs ilyen iD feladat, valószinű lejárt az ideje");
		}
		System.out.println("Service   " + tippek.getId());
		System.out.println("Service " + tippek.getTippek());
		FeladvanyokTaroloja.getInstance().getFeladvany().get(tippek.getId()).settTL(tTL);
		int[] feladvany = FeladvanyokTaroloja.getInstance().getFeladvany().get(tippek.getId()).getFeladat();
		int k = 0;
		int[] eredmeny = new int[feadvanyHossza];
		int[] tipsor = tippek.getTippek();
		MastResponse  mRespose = new MastResponse();
		for ( int i = 0; i < feadvanyHossza; i ++ ) {
			for ( int j = 0; j < feadvanyHossza; j ++ ) {
				
				if (feladvany[i] == tipsor[j]) {
					if (i == j) {
						eredmeny[k] = 2;
					}else {
						eredmeny[k] = 1;
					}
					k++;
				}
			}
		}
		mRespose.setEredmeny(eredmeny);
		k = 0;
		for (int i : eredmeny) {
			k = k + i;
		}
		mRespose.setKesz( (k == 8 ) ? true : false );
		mRespose.setId(tippek.getId());
		return mRespose;
	}
	
	public String[] megold(String azonosito) throws NotFoundKey {
		
		System.out.println(azonosito);
		if (!FeladvanyokTaroloja.getInstance().getFeladvany().containsKey(azonosito)) {
			throw new NotFoundKey("Nincs ilyen iD feladat, valószinű lejárt az ideje");
		}

		String[] feladvany = new String[4];
		int j = 0;
		int[] feladvanyInd = FeladvanyokTaroloja.getInstance().getFeladvany().get(azonosito).getFeladat();
		for (int i : feladvanyInd) {
			feladvany[j] = szinek[i-1];
			j++;
		}
		System.out.println(Arrays.toString(feladvany));
		FeladvanyokTaroloja.getInstance().deleteFeladvany(azonosito);
		return feladvany;
	}
	
	public void felad(String azonosito) throws NotFoundKey {
		if (!FeladvanyokTaroloja.getInstance().getFeladvany().containsKey(azonosito)) {
			throw new NotFoundKey("Nincs ilyen iD feladat, valószinű lejárt az ideje");
		}
		FeladvanyokTaroloja.getInstance().deleteFeladvany(azonosito);
	}
}

package hu.sagi.mastermind.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.sagi.mastermind.domain.MastDtO;
import hu.sagi.mastermind.domain.MastResponse;
import hu.sagi.mastermind.exceptions.FullStorage;
import hu.sagi.mastermind.exceptions.NotFoundKey;
import hu.sagi.mastermind.services.MastService;

@RestController

@RequestMapping("/mastrm")
public class MastController {

	@Autowired
	private  MastService mservice;

	public void setMservice(MastService mservice) {
		this.mservice = mservice;
	}
	
	@PostMapping("/start")
	public ResponseEntity<String> start() throws FullStorage {
		
		return ResponseEntity.ok(mservice.feladvanytLetrehoz());
	}
	
	
	@PostMapping("/playing")
	public ResponseEntity<MastResponse> playing(@RequestBody MastDtO tippek) throws NotFoundKey {
		System.out.println("Controller " + tippek);
		return ResponseEntity.ok(mservice.tippEllenorzese(tippek));
	}
	
	@PostMapping("/kesz")
	public ResponseEntity<String[]> megoldasKuldése(@RequestBody String azonosito) throws NotFoundKey {
		System.out.println(" azon cotroller  " + azonosito);
		return ResponseEntity.ok(mservice.megold(azonosito));
	}
	
	@PostMapping("/felad")
	public ResponseEntity<String[]> feladas(@RequestBody String azonosito) throws NotFoundKey {
		return ResponseEntity.ok(mservice.megold(azonosito));
	}
}

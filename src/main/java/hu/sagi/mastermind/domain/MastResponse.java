package hu.sagi.mastermind.domain;

public class MastResponse {

	private String id;
	private int[] eredmeny;
	private boolean kesz;
	
	
	
	public int[] getEredmeny() {
		return eredmeny;
	}
	public void setEredmeny(int[] eredmeny) {
		this.eredmeny = eredmeny;
	}
	public boolean isKesz() {
		return kesz;
	}
	public void setKesz(boolean kesz) {
		this.kesz = kesz;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}

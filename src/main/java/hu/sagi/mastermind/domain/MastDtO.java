package hu.sagi.mastermind.domain;

import java.util.Arrays;

public class MastDtO {

	private int[] tippek;
	private String id;
	
	
	

	public int[]  getTippek() {
		return tippek;
	}
	public void setTippek(int[] tippek) {
		this.tippek = tippek;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "MastDtO [tippek=" + Arrays.toString(tippek) + ", id=" + id + "]";
	}

	
}

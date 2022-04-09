package hu.sagi.mastermind.domain;

import java.util.Arrays;

public class Feladvany {

	private int[] feladat;
	private String iD;
	
	
	public int[] getFeladat() {
		return feladat;
	}
	public void setFeladat(int[] feladat) {
		this.feladat = feladat;
	}
	public String getiD() {
		return iD;
	}
	public void setiD(String iD) {
		this.iD = iD;
	}
	@Override
	public String toString() {
		return "Feladvany [feladat=" + Arrays.toString(feladat) + ", iD=" + iD + "]";
	}
	
	
}

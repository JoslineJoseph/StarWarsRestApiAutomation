package com.loyaltyone.response;

import java.util.ArrayList;

public class Planets {
	private float count;
	private String next;
	private String previous;
	ArrayList < Planet > results = new ArrayList < Planet > ();


	// Getter Methods 
	public float getCount() {
		return count;
	}

	public String getNext() {
		return next;
	}

	public String getPrevious() {
		return previous;
	}
	public ArrayList<Planet> getResults() {
		return results;
	}

	// Setter Methods 



	public void setResults(ArrayList<Planet> results) {
		this.results = results;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

}

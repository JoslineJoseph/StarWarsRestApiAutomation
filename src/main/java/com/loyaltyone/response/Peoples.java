package com.loyaltyone.response;

import java.util.ArrayList;

public class Peoples {
	private float count;
	 private String next;
	 private String previous;
	 ArrayList < People > results = new ArrayList < People > ();

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
 
	 // Setter Methods 

	 public ArrayList<People> getResults() {
		return results;
	}

	public void setResults(ArrayList<People> results) {
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

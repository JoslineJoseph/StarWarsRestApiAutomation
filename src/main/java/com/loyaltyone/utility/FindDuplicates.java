package com.loyaltyone.utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicates {

	static List<Integer> findDuplicates(List<Integer> series, int count){
		if(series.isEmpty())
			return  new ArrayList<Integer>() ;
		
		Map<Integer, Long> counts =
				series.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return (List<Integer>) 
				counts.entrySet()
				.stream()
				.filter(value -> value.getValue() == count)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		List <Integer> list = new ArrayList<Integer>();
		list.add(-1);
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(2);
		list.add(3);
		list.add(3);
		
		List <Integer> fList = findDuplicates(list, 5);
		for(Integer I : fList) {
			System.out.println(I);
		}	
		
		List <Integer> emptyList = new ArrayList<Integer>();
		fList = findDuplicates(emptyList, 3);
		System.out.println(fList.isEmpty());
	}

}

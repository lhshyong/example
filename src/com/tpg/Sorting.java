package com.tpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {
	
	public static void testCase(){
		System.out.println("Preparing sample data...");		
		List<SampleStudent> list = new ArrayList<SampleStudent>();
		list.add(new SampleStudent(44,"Zeta",3.75));
		list.add(new SampleStudent(33,"Tina",3.68));
		list.add(new SampleStudent(77,"Samil",3.75));
		list.add(new SampleStudent(85,"Loius",3.85));
		list.add(new SampleStudent(56,"Samil",3.75));
		list.add(new SampleStudent(16,"alex",3.34));
		list.add(new SampleStudent(27,"George",3.76));
		list.add(new SampleStudent(19,"Samar",3.75));
		list.add(new SampleStudent(59,"Samil",3.53));
		list.add(new SampleStudent(14,"alex",3.76));
		list.add(new SampleStudent(22,"Lorry",3.76));
		list.add(new SampleStudent(66,"Samil",3.75));
		
		System.out.println("List before sort");		
		list.stream().forEach(o -> System.out.println(o.toString()));
		
		ComparatorChain<SampleStudent> chain = new ComparatorChain<SampleStudent>();
		//comparator will sort by following sequence.
		chain.addComparator(SampleStudent.BY_CPGA);
		chain.addComparator(SampleStudent.BY_NAME);
		chain.addComparator(SampleStudent.BY_ID);

		Collections.sort(list, chain);		

		System.out.println("***************");		
		System.out.println("List after sort");		
		list.stream().forEach(o -> System.out.println(o.toString()));		
	}
		
	public static class ComparatorChain<T> implements Comparator<T>{
		
		List<Comparator<T>> comparatorList = new ArrayList<Comparator<T>>();

		public void addComparator(Comparator<T> comparator){
			this.comparatorList.add(comparator);
		}

		@Override
		public int compare(T o1, T o2) {		
			int index = 0; 
			int result = 0;
			do { 
				if(index >= comparatorList.size()){
					return result;
				}
				result = comparatorList.get(index++).compare(o1, o2);				
			} while (result == 0);
			
			return result;
		}
		
	}
	
	
	public static class SampleStudent{
		//sorted by name asc
		public static Comparator<SampleStudent> BY_NAME 	= (s1,s2)-> s1.getName().compareToIgnoreCase(s2.getName());
		//sorted by id asc
		public static Comparator<SampleStudent> BY_ID 		= (s1,s2)-> s1.getId().compareTo(s2.getId());
		//sorted by cpga desc
		public static Comparator<SampleStudent> BY_CPGA 	= (s1,s2)-> s2.getCpga().compareTo(s1.getCpga());

		
		private Integer id;
		private String name;
		private Double cpga;
				
		public SampleStudent(Integer id, String name, Double cpga) {
			super();
			this.id = id;
			this.name = name;
			this.cpga = cpga;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getCpga() {
			return cpga;
		}
		public void setCpga(Double cpga) {
			this.cpga = cpga;
		}

		@Override
		public String toString() {
			return String.format("SampleStudent [id=%s, name=%s, cpga=%s]", id, name, cpga);
		}
		
		
	}
}


package tema5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Tasks {
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	  }
	private List<MonitoredData> list = null;
	static int i=0;
	
	public Tasks()
	{
		task0();
	}
	
	private void task0() {
		list = StreamUtility.getActivities("Activities.txt");
	}
	
	public void showData()
	{
		list.forEach(
				p->{
						if(p.getActivityLabel().equals("Snack") || p.getActivityLabel().equals("Lunch"))
						{
							System.out.println(p.getActivityLabel() + " \t\t"+
									"START:\t"+
									p.getStartTime().getYear() + "-"+(p.getStartTime().getMonth()+1)+"-" +p.getStartTime().getDate()
									+ " "+ p.getStartTime().getHours() + ":"+ p.getStartTime().getMinutes() +":"+ p.getStartTime().getSeconds()
									+"\tEND:\t"+
									p.getEndTime().getYear() + "-"+(p.getEndTime().getMonth()+1)+"-" +p.getEndTime().getDate()
									+ " "+ p.getEndTime().getHours() + ":"+ p.getEndTime().getMinutes() +":"+ p.getEndTime().getSeconds());
						}
						else
						{
							System.out.println(p.getActivityLabel() + " \t"+
									"START:\t"+
									p.getStartTime().getYear() + "-"+(p.getStartTime().getMonth()+1) +"-" +p.getStartTime().getDate()
									+ " "+ p.getStartTime().getHours() + ":"+ p.getStartTime().getMinutes() +":"+ p.getStartTime().getSeconds()
									+"\tEND:\t"+
									p.getEndTime().getYear() + "-"+(p.getEndTime().getMonth()+1)+"-" +p.getEndTime().getDate()
									+ " "+ p.getEndTime().getHours() + ":"+ p.getEndTime().getMinutes() +":"+ p.getEndTime().getSeconds());
						}
						
				}
				);
	}
	
	/**
	 * Number of distinct days 
	 */
	public void task1()
	{
		List<MonitoredData> l = list
				.stream()
				.filter(distinctByKey(p -> p.getStartTime().getDate()+p.getStartTime().getMonth()))
				.collect(Collectors.toList());
		System.out.println("\n# of distict days:" + l.size());
	}
	
	/**
	 * Count how many times each activity appears
	 */
	public void task2()
	{
		Map<String,Integer> map = (Map)list
				.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivityLabel,Collectors.counting()));
		FileUtil.writeMapToFile(map,"task2_map.txt");
	}
	
	/**
	 * Count how many times each avtivity appears, for each day
	 */
	public void task3()
	{
		Map<Integer,Map<String,Integer>> map3 = (Map)list
				.stream()
				.collect(Collectors.groupingBy( data ->{ return data.getStartTime().getDate(); },
						 Collectors.groupingBy( MonitoredData::getActivityLabel ,Collectors.counting())));
		FileUtil.writeMapToFile(map3,"task3_map.txt");
	}
	
	
	/**
	 * For each activity, count the duration
	 */
	public void task4()
	{
		
		Map m4 = list.stream()
				.collect(Collectors.groupingBy(MonitoredData::getStartTime, Collectors.groupingBy(MonitoredData::getActivityLabel,
						Collectors.mapping(MonitoredData::durationInMiliseconds, Collectors.toList() )) ));
		//m4.forEach((k,v)->{System.out.println(k +" "+ v);});
		FileUtil.writeMapToFile(m4,"task4_map.txt");
		List l4 = list
				.stream()
				.map(data -> { return data.getEndTime().getTime() - data.getStartTime().getTime();})
				.collect(Collectors.toList());
		l4.forEach(date->{
			java.util.Date  d = new java.util.Date((long) date);
			//System.out.println(date);
			long seconds =(long)((long)date / 1000)%60;
			long minutes = (long) ((long)date/(1000*60)) %60;
			long hours  = (long) ((long)date / (1000*60*60))%24;
				//System.out.println("\t" + hours +":"+minutes+":"+seconds);
		});
		FileUtil.writeListToFile(l4, "task4_list.txt");
	}
	
	
	/**
	 * For each activity count the total amount of duration
	 */
	public void task5()
	{
		Map<String,Long> map4 = list
				.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivityLabel,
				Collectors.summingLong(data -> {
					return data.getEndTime().getTime() - data.getStartTime().getTime();})));
		FileUtil.writeMapToFile(map4,"task5_map.txt");
		
		map4.forEach((key,val)->{ 
			
			long seconds =(long)((long)val / 1000)%60;
			long minutes = (long) ((long)val/(60*1000)) %60;
			long hours  = (long) ((long)val / (60*60*1000));
			System.out.println("\t"+key +" "+ hours +":"+minutes+":"+seconds);
		});
		
		List a = map4.entrySet()
				.stream()
				.filter( data -> data.getValue() / (60*60*1000) < 10)
				.collect(Collectors.toList());
		
		FileUtil.writeListToFile(a, "task5_list.txt");
		
	}
	
	/**
	 * Filter the activities that have 90% of their duration under 5 minutes
	 */
	public void task6()
	{
		
		Map<String,Long> map =list
				.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivityLabel,Collectors.counting()));
		
		Map<String,Long> map2 = list
				.stream()
				.filter(data-> data.durationInMinutes() <5)
				.collect( Collectors.groupingBy(MonitoredData::getActivityLabel,Collectors.counting()) );
		
		List<String> l1 = map2
				.entrySet()
				.stream()
				.filter(data -> (data.getValue()) >= map.get(data.getKey())*( (double)90/(double)100) )
				.map(data -> data.getKey())
				.collect(Collectors.toList());
		
		FileUtil.writeListToFile(l1, "task6_list.txt");
			
	}
	

}

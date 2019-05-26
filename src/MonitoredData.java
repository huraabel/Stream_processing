package tema5;

import java.time.LocalDateTime;

public class MonitoredData {
	
	private java.util.Date  start_time, end_time;
	private String activity_label;
	
	public MonitoredData() {
		super();
	}
	
	public MonitoredData(java.util.Date start_time, java.util.Date end_time, String activity_label) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity_label = activity_label;
	}
	public java.util.Date getStartTime() {
		return start_time;
	}
	public void setStartTime(java.util.Date start_time) {
		this.start_time = start_time;
	}
	public java.util.Date getEndTime() {
		return end_time;
	}
	public void setEndTime(java.util.Date end_time) {
		this.end_time = end_time;
	}
	public String getActivityLabel() {
		return activity_label;
	}
	public void setActivityLabel(String activity_label) {
		this.activity_label = activity_label;
	}
	
	public String toString()
	{
		if(activity_label.equals("Snack") || activity_label.equals("Lunch"))
			return  activity_label + " \t\t START:"+ start_time +" \tEND:" + end_time; 
		return  activity_label + " \t START:"+ start_time +" \tEND:" + end_time; 
		
	}
	
	public long durationInMiliseconds()
	{
		return (getEndTime().getTime() - getStartTime().getTime());
	}
	
	public double durationInMinutes()
	{
		return (getEndTime().getTime() - getStartTime().getTime())*1.667/100000;
	}
	
	public double durationInHours() {
		return durationInMinutes()/60;
	}
	
}

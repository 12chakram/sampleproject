package com.eng.gp.project.util.date;



import java.sql.SQLData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

 


public class StringTodate {

	private static Date date;

  public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

public static long stringToDate (String str_date){
	 try{
		                     //new FlightSearchForm().getDate();
		 
		 DateFormat formatter;
		 
	String str_date_time = null;
   StringBuffer sb = new StringBuffer();
  long mill =  System.currentTimeMillis();
  Date tempDate = new Date(mill);
  int hours = tempDate.getHours();
  int min = tempDate.getMinutes();
  

  sb.append(str_date+" ");
  sb.append(hours);
  sb.append(":");
  sb.append(min);
  str_date_time = sb.toString();
  
		
		formatter = new SimpleDateFormat("MM-dd-yy hh:mm");
		date= (Date)formatter.parse(str_date_time);
		 
		 System.out.println(date.getTime());
		 long milliseconds;
		 
		milliseconds = date.getTime();
		return milliseconds;
		 
		 }catch(Exception e){
			 System.out.println(e);
			 return 0;
		}
	 }

public static long stringToEndDate (String end_date){
	 try{
		                     //new FlightSearchForm().getDate();
		 
		 DateFormat formatter;
		 
	String str_date_time = null;
  StringBuffer sb = new StringBuffer();
 long mill =  System.currentTimeMillis();
 Date tempDate = new Date(mill);
 int hours = tempDate.getHours();
 int min = tempDate.getMinutes();
 

 sb.append(end_date+" ");
 sb.append(23);
 sb.append(":");
 sb.append(59);
 str_date_time = sb.toString();
 
		
		formatter = new SimpleDateFormat("MM-dd-yy hh:mm");
		date= (Date)formatter.parse(str_date_time);
		 
		 System.out.println(date.getTime());
		 long milliseconds;
		 
		milliseconds = date.getTime();
		return milliseconds;
		 
		 }catch(Exception e){
			 System.out.println(e);
			 return 0;
		}
	 }


public static void main(String[] args) {
	
	StringTodate.stringToDate("24-08-2011");
}

}

package com.personal;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

//The class contains methods to calculate reports
public class ReportService {
		
		Repository repo = Repository.getRepository();

// this method is called to return month wise expense details 
public Map<String,Float> MonthlyReportService(){
			
		Map<String,Float> m = new TreeMap<String,Float>();
		
		for(expense exp :repo.expList) {
		Date date = exp.getDate();
		String yearmonth = dateUtil.yearmonth(date);
		
//when expense is already present in month
		if(m.containsKey(yearmonth)) {
			Float total = m.get(yearmonth);
			total = total + exp.getAmount();
			 m.put(yearmonth, total);
			
		}else {
//This month is not yet added, so add here	
			 m.put(yearmonth, exp.getAmount());
		}
		}
		 return m;
		}
//this method is called to return year wise expense details 		
public Map<Integer,Float> YearlyReportService(){
			
			Map<Integer,Float> m = new TreeMap<Integer,Float>();
			for(expense exp :repo.expList) {
			Date date = exp.getDate();
			Integer year = dateUtil.getyear(date);
	//when expense is already present in Year
			if(m.containsKey(year)) {
				Float total = m.get(year);
				total = total + exp.getAmount();
				 m.put(year, total);
				
			}else {
	//when Year is not yet added, so add here	
				 m.put(year, exp.getAmount());
			}
			}
			 return m;
			}
//this method is called to return Categorized expense details 		
public Map<String,Float> oncategarisesExpense(){
			
			Map<String,Float> m = new TreeMap<String,Float>();
			
			for(expense exp :repo.expList) {
			Long catid = exp.getCategaryID();
			String catname = getnamebyid(catid);
	//when expense is already present in Category
			if(m.containsKey(catname)) {
				Float total = m.get(catname);
				total = total + exp.getAmount();
				 m.put(catname, total);
				
			}else {
	//When Category is not yet added, so add here	
				 m.put(catname, exp.getAmount());
			}
			}
			 return m;
			}
// Accept Category Id and returns the category name of that ID
public String getnamebyid(Long catid) {	
			
			for(categary c:repo.catList) {
				if(c.getCategaryID() == catid) {
					return c.getName();
				}
				
			}
			return null;
	}

}

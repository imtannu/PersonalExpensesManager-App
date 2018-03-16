package com.personal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// The class contains most of the operation related to PEM Application.  
// class make use of repository to store data and also use ReportService to generate Required reports 
public class PEMService {
	
	public PEMService() {
		readRepoObjects();
	}

	// Static method which return singleton object
	Repository repo = Repository.getRepository();
	
// call different methods to calculate reports
	ReportService report = new ReportService();
	
	Scanner sc = new Scanner(System.in);
	private int choice;
	
// prepares a PEM Application Menu using switch case and accept User Choice
	public void showMenu() {
		while(true) {
			printMenu();
			switch(choice) {
			case 1: 
				addCategary();
				pressAnyKey();
				break;
			case 2: 
				onlistCategary();
				pressAnyKey();
				break;
			case 3: 
				addExpense();
				pressAnyKey();
				break;	
			case 4: 
				onlistExpenses();
				pressAnyKey();
				break;	
			case 5: 
				onmonthlyExpense();
				pressAnyKey();
				break;	
			case 6: 
				onyearlyExpense();
				pressAnyKey();
				break;	
			case 7: 
				oncategarisesExpense();
				pressAnyKey();
				break;		
				
			case 0:
				onExit();
				break;
			}
			
		}
	}

	// method to print  menu	
	public void printMenu() {
		System.out.println("------------PEM Menu------------");
		System.out.println("1: Add Categary");
		System.out.println("2: Categary List");
		System.out.println("3: Add Expense");
		System.out.println("4: Expenses List");
		System.out.println("5: Monthly Expenses List");
		System.out.println("6: Yearly Expense List");
		System.out.println("7: Categarised Expense List");
		System.out.println("0: Exit & Save");
		System.out.println("--------------------------------");
		System.out.print("Enter Choice:");
		choice = sc.nextInt();
	}
	
//method is called to hold a screen after process output screen
	public void pressAnyKey(){
		System.out.println("Press any Key to Continue...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//method is taking Category input to add new category and save in System
	public void addCategary() {
		sc.nextLine();
		System.out.println("Enter Categary Name: ");
		String cat = sc.nextLine();
		categary catr = new categary(cat);
		repo.catList.add(catr);
		System.out.println(" Categary "+cat+ " Successfully Added...");
	}

	//method to print existing category list 
	public void onlistCategary() {
		System.out.println(" Categary List:");
		List<categary> clist= repo.catList;
		for(int i=0; i<clist.size();i++) {
			categary c = clist.get(i);
			System.out.println("No|Categary Name|Categary Id");
			System.out.println((i+1) + "   "+ c.getName()+"        " +c.getCategaryID());
		}
	}
// method to enter Expense details and Store in System	
	public void addExpense() {
		sc.nextLine();
		
		System.out.println("Add Expense Details:");
		onlistCategary();
		
		System.out.println("Enter the Categary No: ");
		int catChoice= sc.nextInt();
		
		categary selectedCat = repo.catList.get(catChoice-1);
		System.out.println("Enter the Expense Details for the Category " +selectedCat.getName());
		
		Long catid = selectedCat.getCategaryID();
		String catname = report.getnamebyid(catid);
		
		System.out.println("Enter Amount:");
		float amt = sc.nextFloat();
		
		System.out.println("Enter the Expense Description");
		sc.nextLine();
		String desc = sc.nextLine();
		
		System.out.println("Enter the Date(dd/MM/yyyy)");
		String stringasdate = sc.nextLine();
		Date date = dateUtil.stringToDate(stringasdate);
		 
		
		expense ex = new expense(catid,amt,date,desc,catname);
		repo.expList.add(ex);
		System.out.println("Expense added Successfully");
		
	}
//Method prints all entered expenses	
	public void onlistExpenses() {
		System.out.println("Listing Expenses with Categaries...");
		List<expense> explist = repo.expList;
		for(int i=0;i<explist.size();i++) {
			expense exp = explist.get(i);
			String datetoprint = dateUtil.DateToString(exp.getDate());
			System.out.println("No|Categary|Discription| Amount | Date ");
			System.out.println((i+1)+"  " +exp.getCatName()+"   "+ exp.getDescription()+"   "+exp.getAmount()+"   "+datetoprint);
		}
	}
//this method calculates Months Wise Expense total. Use ReportService to calculate report	
	public void onmonthlyExpense() {
		System.out.println("Monthly Expense Totol: ");
		Map<String,Float> resultmap = report.MonthlyReportService();
		Set<String> keys = resultmap.keySet();
		for(String yearmonth:keys) {
			 String arr[] = yearmonth.split(",");
			 String year = arr[0];
			 Integer month = new Integer(arr[1]);
			String mnamee = dateUtil.monthNumToName(month);
			
			System.out.println(year+", "+mnamee+" : "+resultmap.get(yearmonth)+"/-");
		}
	}
//this method calculates Year Wise Expense total. Use ReportService to calculate report	
	public void onyearlyExpense() {
		System.out.println("yearly Expense Totol:");
		Map<Integer,Float> result = report.YearlyReportService();
		Set<Integer> years = result.keySet();
		Float total = 0.0F;
		for(Integer year : years) {
			Float exp = result.get(year);
			total = total + exp;
		System.out.println(year+" : "+exp);
	}
		System.out.println("-----------------------------");
		System.out.println("Totol Expense(INR): "+total );
	}
////this method calculates Category Wise Expense total. Use ReportService to calculate report		
	public void oncategarisesExpense() {
		System.out.println("Categarywise Expense");
		Map<String,Float> resultm =  report.oncategarisesExpense();
		Set<String> set = resultm.keySet();
		Float total = 0.0F;
		for(String catname : set) {
			Float exp = resultm.get(catname);
			total = total + exp;
		System.out.println(catname +" : "+resultm.get(catname));
	}
		System.out.println("---------------------------------");
		System.out.println("Total Categirsed Totol:(INR) "+total);

	}
//this Method Store Data Permanently in file and stop JVM and close Application	
	public void onExit() {
		storeRepoObjects();
		System.exit(0);
	}
	
// to generate categaryID & ExpenseID unique because it's depend on milliseconds 
	void delay() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
//On Application Exit Store/Write objects in the file 	
	private void storeRepoObjects() {
		writeobj("category.ser",repo.catList);
		writeobj("expenses.ser",repo.expList);
		
	}
	public void writeobj(String file, Object obj) {
	try {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj); //Store Category and Expense objects in File
		oos.close();
		fos.close();
	} catch (Exception e) {
		System.out.println("File Writting Failed");
	}
	}
	
//on Application StartUp restore/read Objects/data from the file
   public void readRepoObjects() {
	   
	 @SuppressWarnings("unchecked")
	List<categary> filecatlist = (List<categary>) readobj("category.ser");
	 @SuppressWarnings("unchecked")
	List<expense> fileexplist = (List<expense>) readobj("expenses.ser");
	 
	//set existing categaryList in repository 
	 if(filecatlist!=null) {
		 repo.catList = filecatlist;
	 }
	 if(fileexplist!=null) {
		 repo.expList = fileexplist;
	 }
	}
      public Object readobj(String file) {
    	  try {
    	  FileInputStream fin = new FileInputStream(file);
    	  @SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(fin);
    	  
			Object obj = ois.readObject();
			return obj;
			
		} catch (Exception e) {
			System.out.println("No Existing Data Stored");
			return null;
		}
	}
}
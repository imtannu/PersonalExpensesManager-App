package com.personal;

import java.util.ArrayList;
import java.util.List;

//Class used as database/repository and it's singelton 

public class Repository {

	//This list holds all expense object
	public List<expense> expList = new ArrayList<expense>();
	
	//This list holds all Category object
	public List<categary> catList = new ArrayList<categary>();
	
// A singleton reference of repository	
private static Repository repository; 

//Private Constructor to restrict object creation from outside...
private Repository() {
		}

// method returns Singleton object of repository
	public static Repository getRepository() {
		if(repository == null) {
			repository = new Repository();
		}
		return repository;
	}

}
 
package com.personal;

//This class is an entry point of execution for PersonalExpenseManager Application(PEMApp)
public class startApp {

	public static void main(String[] args) {
		
		// Create PEMService object and show application menu by calling ShowMenu method
		PEMService i = new PEMService();
		i.showMenu();
	}
}

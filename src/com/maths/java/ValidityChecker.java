package com.maths.java;

import java.util.Calendar;
import java.util.regex.Pattern;

public class ValidityChecker {
	public static boolean isUnameValid(String uname) {
		String pattern1 = "[a-z0-9_]{5,12}";                   // pattern for username
		if(Pattern.matches(pattern1,uname)) {
			return true;
		}else {
			System.out.println("Username did not meet the requirements");
			return false;
		}
	}
	
	public static boolean isPassValid(String pass) {
		String pattern2 = "^" + "$";           // pattern for password
		if(Pattern.matches(pattern2,pass)) {
			return true;
		}else {
			System.out.println("Password did not meet the requirements");
			return false;
		}
	}
	
	public static boolean isEmailValid(String email) {
		String pattern2 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						  + "[A-Za-z0-9-]+(\\.[A-za-z0-9]+)*(\\.[A-za-z]{2,})$";           // pattern for email
		if(Pattern.matches(pattern2,email)) {
			return true;
		}else {
			System.out.println("Email did not meet the requirements");
			return false;
		}
	}
	
	public static boolean isDobValid(String dob) {             // dob validator
        Calendar cal=Calendar.getInstance();

        String arr[]=dob.split("-");
        int date=Integer.parseInt(arr[2]);
        int month=Integer.parseInt(arr[1]);
        int year=Integer.parseInt(arr[0]);

        cal.set(Calendar.YEAR,year);
        switch(month){
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                cal.set(Calendar.MONTH,Calendar.JANUARY);
                break;
            case 2:
                cal.set(Calendar.MONTH,Calendar.FEBRUARY);
                break;
            case 4:case 6:case 9:case 11:
                cal.set(Calendar.MONTH,Calendar.APRIL);
                break;
            default:
                System.out.println("enter correct month");
        }

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(month <= 12 && date<=maxDay){
            System.out.println("valid");
            return true;
        }else{
            System.out.println("Invalid");
            return false;
        }
    }
}

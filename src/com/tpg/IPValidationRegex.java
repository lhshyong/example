package com.tpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPValidationRegex {

	public static void testCase(){
		BufferedReader reader = null;
        try {
			System.out.println("Reading ip list from file and validate");					
			InputStreamReader isReader = new InputStreamReader(IPValidationRegex.class.getResourceAsStream("iplist.txt"));
            reader = new BufferedReader(isReader);

            String singleLine = "";
			while ((singleLine = reader.readLine()) != null) {
				if(!IPValidationRegex.validate(singleLine)){
				    System.out.println("\"" + singleLine + "\" is not a valid ipaddress");					
				}
			}
			System.out.println("validation end");								
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    private static final String IPADDRESS_PATT =
        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static boolean validate(final String ipAddress){         
    	Pattern pattern = Pattern.compile(IPADDRESS_PATT);
    	Matcher matcher = pattern.matcher(ipAddress);
    	return matcher.matches();            
    }

}

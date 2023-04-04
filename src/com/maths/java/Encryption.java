package com.maths.java;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	public static String getMD5(String pass) {                 // password encryption
		String encryptedValue = null;
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte messagedigest[]=md.digest(pass.getBytes());
			System.out.println();
			BigInteger bigInt = new BigInteger(1,messagedigest);
			encryptedValue = bigInt.toString(16);
		}catch(NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return encryptedValue;
	}
}

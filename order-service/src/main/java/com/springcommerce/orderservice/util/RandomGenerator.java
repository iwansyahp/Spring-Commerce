package com.springcommerce.orderservice.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomGenerator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int NAME_LENGTH = 16;
    
    public static String generateRandomName() {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(NAME_LENGTH);
        for (int i = 0; i < NAME_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    } 
}

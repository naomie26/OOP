package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Flesch {
	private static final double const1 = 206.835;
	private static final double const2 = 84.6;
	private static final double const3 = 1.015;
	String[] delStrings = new String[] {".", "'", ":", ")","(", "*","&", "^","%","$","#","@","!","_","+","-","=","]","[","}","{","?",";","]"};
   
	
	
	public static void main(String[] args) {
    	
        try (FileReader reader = new FileReader(args[0]);
             BufferedReader br = new BufferedReader(reader)) {
        	int sentences = 0, words=0, syllables=0;
            // read line by line
            String line;
            String delim = ".,':)(*&^%$#@!_+-=][}{?; ";
            String delim_sentence = ".;,?!";
            while ((line = br.readLine()) != null) {
            	StringTokenizer token = new StringTokenizer(line, delim);
            	words += token.countTokens();
            	while (token.hasMoreElements()) {
            		String w = token.nextToken();
            		syllables += countSyllables(w); 	
        		}
            	String[] letters = line.split("", -1);
            	for (String i : letters) {
            		if(delim_sentence.contains(i) && i.length()>0) {
            			sentences++;
            		}
            	}
            }
            double flesch = const1 - const2 * syllables / words - const3 * words / sentences;
            //System.out.println(sentences +" "+ words +" "+ syllables);
            System.out.println("Flesch indicator is: " +flesch);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    
	
    // Counts the number of syllables wrt the rules described.
    public static int countSyllables(String str) {
    	str = str.toLowerCase();
    	int count = 0;
        StringTokenizer syllables = new StringTokenizer(str, "aeiouy");
        while (syllables.hasMoreElements()) {
			syllables.nextElement();
        	count++;
    	}
        count--;
        String[] vowels = new String[] {"a", "e", "i", "o", "u", "y"};
        String[] ends = new String[] {"ae", "ee", "ie", "oe", "ue", "ye"};
    	// If the word begins with a vowels, the syllable hasn't been counted yet
        for (String c : vowels) {
    		if(str.startsWith(c) || ( str.endsWith(c) && (c != "e" )) ) {
    			count++;
    		}
    	}
        for (String c : ends) {
        	if(str.endsWith(c)) {
        		count++;
        	}
        }
    	return Math.max(1, count);
    }

}
package anagramsChecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class anagrams {
	public static void main(String [] args) {

        String fisier_intrare = "sample.txt";
        String linia_preluata = null, linia_sortata="";

        try {
            FileReader fileReader = new FileReader(fisier_intrare);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((linia_preluata = bufferedReader.readLine()) != null) {
            	
            	linia_sortata = "";
            	
            	int[] a = new int[20]; 
            	
            	//stocarea unui cuvant intr-un array de tip int
            	for (int i = 0; i < linia_preluata.length(); i++) {
            		a[i] = (int)linia_preluata.charAt(i);
            	}
            	
//            	for(int i = 0; i < linia_preluata.length(); i++) {
//            		System.out.print((char)a[i]);
//            	}
//            	System.out.println("---------");
            	
            	int minim, pozitia=0;
            	for (int i = 0; i < linia_preluata.length(); i++)
                {
                    minim = a[i];
                    for (int j = i + 1; j < linia_preluata.length(); j++)
                        if (minim > a[j]) {
                        	minim = a[j];
                        	pozitia = j;
                        }
                    
                    if(minim < a[i]) {
                    	a[pozitia] = a[i];
                    	a[i] = minim;
                    }
                    
                }
            	
        		for(int l = 0; l < linia_preluata.length(); l++) {
            		System.out.print((char)a[l]);
            	}
                System.out.println();

            	
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Fisierul nu poate fi deschis!");                
        }
        catch(IOException ex) {
            System.out.println("Fisierul nu poate fi citit!");      
        }
    }
}

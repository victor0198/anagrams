package anagramsChecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class anagrams {
	public static void main(String [] args) {

        String fisier_intrare = "sample.txt";
        String linia_preluata = null, linia_sortata;
        String linia_precedenta_sortata = "", linia_precedenta = "";
        List<String> setDeAnagrame = new ArrayList<String>();
        
        try {
            FileReader fileReader = new FileReader(fisier_intrare);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean primaAnagrama = true;
            
            while((linia_preluata = bufferedReader.readLine()) != null) {
            	
            	linia_sortata = "";
            	
            	int[] a = new int[20]; 
            	
            	//stocarea unui cuvant intr-un array de tip int
            	for (int i = 0; i < linia_preluata.length(); i++) {
            		a[i] = (int)linia_preluata.charAt(i);
            	}
            	
            	//sortarea alfabetica a literelor din cuvant
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
        			 linia_sortata += (char)a[l];
            	}
        		
            	if(Objects.equals(linia_precedenta_sortata, linia_sortata)) {
            		if(primaAnagrama == true) {
            			setDeAnagrame.add(linia_precedenta);
            		}
            		setDeAnagrame.add(linia_preluata);
            		primaAnagrama = false;
                }else {
        			primaAnagrama = true;
        			
        			//printarea setului de anagrame in ordinea alfabetica
        			Collections.sort(setDeAnagrame, Collator.getInstance());
        			if(setDeAnagrame.size() > 0) {
	        			for (int i = 0; i < setDeAnagrame.size(); i++) {
	        				System.out.print(setDeAnagrame.get(i) + " ");
	        			}
	        			System.out.println();
        			}
        			
        			setDeAnagrame.clear();
        		}
            	
            	linia_precedenta_sortata = linia_sortata;
            	linia_precedenta = linia_preluata;
                
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

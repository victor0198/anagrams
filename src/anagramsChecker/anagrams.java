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
	
	public static int[] dinStringInArray(String cuvant) {
		int[] a = new int[20]; 
		for (int i = 0; i < cuvant.length(); i++) {
    		a[i] = (int)cuvant.charAt(i);
    	}
		return a;
	}
	
	public static int[] sorteazaArray(int[] a, int len) {
		int minim, pozitia=0;
    	for (int i = 0; i < len; i++)
        {
            minim = a[i];
            for (int j = i + 1; j < len; j++)
                if (minim > a[j]) {
                	minim = a[j];
                	pozitia = j;
                }
            
            if(minim < a[i]) {
            	a[pozitia] = a[i];
            	a[i] = minim;
            }
        }
		return a;
	}
	
	public static String dinArrayInString(int[] a, int len) {
		String cuvant = "";
		for(int l = 0; l < len; l++) {
			 cuvant += (char)a[l];
		}
		return cuvant;
	}
	
	public static void printeazaLista(List<String> lista) {
		//printarea setului de anagrame in ordinea alfabetica
		Collections.sort(lista, Collator.getInstance());
		if(lista.size() > 0) {
			for (int i = 0; i < lista.size(); i++) {
				System.out.print(lista.get(i) + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args) {
		
        String fisier_intrare = "sample.txt";
        String linia_preluata = null, linia_sortata;
        String linia_precedenta_sortata = "", linia_precedenta = "";
        
        try {
            FileReader fileReader = new FileReader(fisier_intrare);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            List<String> setDeAnagrame = new ArrayList<String>();
            boolean primaAnagrama = true;
            
            while((linia_preluata = bufferedReader.readLine()) != null) {
            	
            	linia_sortata = "";
            	
            	//stocarea unui cuvant intr-un array de tip int
            	int[] a = dinStringInArray(linia_preluata);
            	
            	//sortarea alfabetica a literelor din cuvant
            	a = sorteazaArray(a, linia_preluata.length());
            	
            	//convertirea array-ului in cuvant (cu literele sortate alfabetic)
            	linia_sortata = dinArrayInString(a, linia_preluata.length());
        		
            	if(Objects.equals(linia_precedenta_sortata, linia_sortata)) {
            		if(primaAnagrama == true) {
            			setDeAnagrame.add(linia_precedenta);
            		}
            		setDeAnagrame.add(linia_preluata);
            		
            		primaAnagrama = false;
            		
                }else {
                	primaAnagrama = true;
        			
                	printeazaLista(setDeAnagrame);
                	
        			setDeAnagrame.clear();
        		}
            	
            	linia_precedenta_sortata = linia_sortata;
            	linia_precedenta = linia_preluata;
                
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Fisierul nu este gasit");                
        }
        catch(IOException ex) {
            System.out.println("Fisierul nu poate fi citit");      
        }
    }
}

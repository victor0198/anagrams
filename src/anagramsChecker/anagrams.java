package anagramsChecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class anagrams {
			
	public static int[] dinStringInArray(String cuvant) {
		int[] a = new int[30]; 
		for (int i = 0; i < cuvant.length(); i++) {
    		a[i] = (int)cuvant.charAt(i);
    	}
		return a;
	}
	
	public static int[] sorteazaArray(int[] a, int len) {
		int minim, pozitia=0;
    	for (int i = 0; i < len-1; i++)
        {
            minim = a[i];
            for (int j = i + 1; j < len; j++) {
            	if (minim > a[j]) {
                	minim = a[j];
                	pozitia = j;
                }
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
		if(lista.size() > 0) {
			for (int i = 0; i < lista.size(); i++) {
				System.out.print(lista.get(i) + " ");
			}
			System.out.println();
		}
	}
	
	public static List<String> sorteazaListaString(List<String> lista) {
		String inferior;
		int pozitia=0;
    	for (int i = 0; i < lista.size(); i++)
        {
    		inferior = lista.get(i);
            for (int j = i + 1; j < lista.size(); j++) {
            	if (comparaCuvintele(inferior,lista.get(j)) < 0) {
                	inferior = lista.get(j);
                	pozitia = j;
              }
            }
            
            if(comparaCuvintele(inferior, lista.get(i)) > 0) {
            	lista.set(pozitia, lista.get(i));
            	lista.set(i, inferior);
            }
        }
		
		return lista;
	}
	
	public static int comparaCuvintele(String cuvant1, String cuvant2) {
		int rezultat = 0;
		
		if(cuvant1.length() != cuvant2.length()) {
			return 2;
		}else {
			for(int i = 0; i < cuvant1.length(); i++) {		
				if((int)cuvant1.charAt(i)>(int)cuvant2.charAt(i)) {
					rezultat = -1;
					break;
				}
				if((int)cuvant1.charAt(i)<(int)cuvant2.charAt(i)) {
					rezultat = 1;
					break;
				}
			}
		}
		return rezultat;
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
            	
            	//stocarea unui cuvant intr-un array de tip int (selectie)
            	int[] a = dinStringInArray(linia_preluata);
            	
            	//sortarea alfabetica a literelor din cuvant
            	a = sorteazaArray(a, linia_preluata.length());
            	
            	//convertirea array-ului in cuvant (cu literele sortate alfabetic)
            	linia_sortata = dinArrayInString(a, linia_preluata.length());
            	
            	if(comparaCuvintele(linia_precedenta_sortata, linia_sortata) == 0) {
            		if(primaAnagrama == true) {               //daca a fost identificata anagrama, exclusiv prima
            			setDeAnagrame.add(linia_precedenta);
            		}
            		setDeAnagrame.add(linia_preluata);
            		
            		primaAnagrama = false;
            		
                }else {                                       
                	primaAnagrama = true;
        			
                	if(setDeAnagrame.size() > 0) {
                		//sortarea lstei cu datagrame in ordine alfabetica
                    	setDeAnagrame = sorteazaListaString(setDeAnagrame);
                    	
                    	//printarea setului de anagrame
                    	printeazaLista(setDeAnagrame);
                	}
                	
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

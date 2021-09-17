package tabela1Ligi;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AlfabetycznaListaDruzyn {
	
	// zmienne klasy AlfabetycznaListaDruzyn
	private static String[] alfabetycznaListaDruzyn;
	
	// konstruowanie klasy
	public AlfabetycznaListaDruzyn(String adresWWW) {
		// ��czenie si� z baz� informacji
		Polaczenie polaczenie = new Polaczenie(adresWWW);
		Document dokument = polaczenie.dajDokument();
		
		// wyodr�bnianie ze strony samych nazw klub�w, bez sortowania do tablicy string�w
		Elements duzyBox = dokument.getElementsByClass("box5");	
		Elements tabelaLigowa = duzyBox.select("table[class=table-ligowa]").select("td[class=td2]").select("a");
		String niesortowaneDruzyny = tabelaLigowa.html(); 
		alfabetycznaListaDruzyn = niesortowaneDruzyny.split("\n");
		
		// sortowanie b�belkowe nazw klub�w
		for(int i=0; i<alfabetycznaListaDruzyn.length-1; i++) {
			for(int j=0; j<alfabetycznaListaDruzyn.length-1; j++) {
				if(alfabetycznaListaDruzyn[j].compareTo(alfabetycznaListaDruzyn[j+1])>0) {
					String temp = alfabetycznaListaDruzyn[j+1];
					alfabetycznaListaDruzyn[j+1]=alfabetycznaListaDruzyn[j];
					alfabetycznaListaDruzyn[j]=temp;
				}
			}
		}
		
		// odkomentowanie do zobaczenia listy dru�yn bior�cych udzia� w rozgrywkach
//		for(int i=0; i<alfabetycznaListaDruzyn.length; i++) {
//			System.out.println(alfabetycznaListaDruzyn[i]);
//		}
	}
	
	// zwroc posortowana liste druzyn
	public String[] pobierzListeDruzyn() {
		return alfabetycznaListaDruzyn;
	}
}

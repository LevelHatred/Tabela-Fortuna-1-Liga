package tabela1Ligi;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RezultatyMeczow {
	// zmienne klasy
	private static String [][] rezultatyMeczow;
	private static Elements gospodarze, wyniki, goscie;
	
	// konstruowanie klasy
	public RezultatyMeczow() {
		rezultatyMeczow=stworzTabeleRezultatow();
	}
	
	// generowanie tablicy wynikow do tabeli
	private static String[][] stworzTabeleRezultatow() {
		String[][] temp = new String[18][18];
		for(int gospodarz=0; gospodarz<18; gospodarz++) {
			for(int gosc=0; gosc<18; gosc++) {
				if(gospodarz==gosc) {
					temp[gospodarz][gosc]="x";
				}
				else {
					temp[gospodarz][gosc]="-";
				}
			}
		}
		return temp;
	}
	// pobieranie wyników meczów do listy
	public void pobierzWyniki() {
		Polaczenie polaczenie = new Polaczenie("http://www.polskapilka.net/1-liga/");
		Document dokument = polaczenie.dajDokument();
		gospodarze = dokument.select("table[class=kolejka]").select("td[class=td3]").select("div[class=f_left]").select("p");
		wyniki = dokument.select("table[class=kolejka]").select("td[class=td3]").select("div[class=result]");
		goscie = dokument.select("table[class=kolejka]").select("td[class=td3]").select("div[class=f_right]").select("p");
	}
	// przepisywanie wyników z listy do tablicy
	public void uzupelnijWyniki(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn) {
		String[] listaGospodarze =gospodarze.html().split("\n");
		String[] listaWyniki = wyniki.html().split("\n");
		String[] listaGoscie = goscie.html().split("\n");
		int nrGospodarza=0;
		int nrGoscia=0;
		for(int i=0; i<listaGospodarze.length; i++) {
			for(int j=0; j<18; j++) {
				if(listaGospodarze[i].equals(alfabetycznaListaDruzyn.pobierzListeDruzyn()[j])) {
					nrGospodarza=j;
					break;
				}
			}
			for(int j=0; j<18; j++) {
				if(listaGoscie[i].equals(alfabetycznaListaDruzyn.pobierzListeDruzyn()[j])) {
					nrGoscia=j;
					break;
				}
			}
			if(rezultatyMeczow[nrGospodarza][nrGoscia].equals("-")==false) {
				rezultatyMeczow[nrGoscia][nrGospodarza]=listaWyniki[i];
			}
			else {
				rezultatyMeczow[nrGospodarza][nrGoscia]=listaWyniki[i];
			}
			
			System.out.println(rezultatyMeczow[11][13]);
		}
//		for(int i=0;i<listaGoscie.length;i++) {
//			System.out.println(listaGospodarze[i] + " " + listaWyniki[i] + " "+ listaGoscie[i]);
//		}
		System.out.println("test" + rezultatyMeczow[11][13]);
		//tymczasowo wyswietl format wynikow
		for(int i=0; i<18;i++) {
			for(int j=0; j<18;j++) {
				System.out.print(rezultatyMeczow[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public String[][] dajRezultatyMeczow(){
		return rezultatyMeczow;
	}
}

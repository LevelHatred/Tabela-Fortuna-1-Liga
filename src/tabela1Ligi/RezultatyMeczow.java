package tabela1Ligi;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RezultatyMeczow {
	// zmienne klasy
	private static String [][] rezultatyMeczow;
	private static Elements gospodarze, wyniki, goscie;
	
	// konstruowanie klasy
	public RezultatyMeczow(AlfabetycznaListaDruzyn ALD) {
		rezultatyMeczow=stworzTabeleRezultatow(ALD);
	}
	
	// generowanie tablicy wynikow do tabeli
	private static String[][] stworzTabeleRezultatow(AlfabetycznaListaDruzyn ALD) {
		int liczbaDruzyn = ALD.pobierzListeDruzyn().length;
		String[][] tablicaWynikow = new String[liczbaDruzyn][liczbaDruzyn];
		for(int gospodarz=0; gospodarz<liczbaDruzyn; gospodarz++) {
			for(int gosc=0; gosc<liczbaDruzyn; gosc++) {
				if(gospodarz==gosc) {
					tablicaWynikow[gospodarz][gosc]="x";
				}
				else {
					tablicaWynikow[gospodarz][gosc]="-:-";
				}
			}
		}
		return tablicaWynikow;
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
	public void uzupelnijWyniki(AlfabetycznaListaDruzyn ALD) {
		String[] listaDruzyn = ALD.pobierzListeDruzyn();
		int liczbaDruzyn = ALD.pobierzListeDruzyn().length;
		String[] listaGospodarze =gospodarze.html().split("\n");
		String[] listaWyniki = wyniki.html().split("\n");
		String[] listaGoscie = goscie.html().split("\n");
		int nrGospodarza=0;
		int nrGoscia=0;
		for(int i=0; i<listaGospodarze.length; i++) {
			for(int j=0; j<liczbaDruzyn; j++) {
				if(listaGospodarze[i].equals(listaDruzyn[j])) {
					nrGospodarza=j;
					break;
				}
			}
			for(int j=0; j<liczbaDruzyn; j++) {
				if(listaGoscie[i].equals(listaDruzyn[j])) {
					nrGoscia=j;
					break;
				}
			}
			if(rezultatyMeczow[nrGospodarza][nrGoscia].equals("-:-")==false) {
				rezultatyMeczow[nrGoscia][nrGospodarza]=listaWyniki[i];
			}
			else {
				rezultatyMeczow[nrGospodarza][nrGoscia]=listaWyniki[i];
			}
		}
		// wyswietlanie listy dostepnych kolejek
//		for(int i=0;i<listaGoscie.length;i++) {
//			System.out.println(listaGospodarze[i] + " " + listaWyniki[i] + " "+ listaGoscie[i]);
//		}
		//tymczasowo wyswietl format wynikow
		for(int i=0; i<liczbaDruzyn;i++) {
			for(int j=0; j<liczbaDruzyn;j++) {
				System.out.print(rezultatyMeczow[i][j]+"\t");
			}
			System.out.println();
		}
	}
	// zwroc tabele rezultatow
	public String[][] dajRezultatyMeczow(){
		return rezultatyMeczow;
	}
}

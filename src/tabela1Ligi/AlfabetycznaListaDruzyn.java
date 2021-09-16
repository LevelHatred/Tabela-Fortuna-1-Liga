package tabela1Ligi;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AlfabetycznaListaDruzyn {
	// zmienne klasy
	private static String[] alfabetycznaListaDruzyn;
	
	// konstruowanie klasy
	public AlfabetycznaListaDruzyn() {
		// ³¹czenie siê z baz¹ informacji
		Polaczenie polaczenie = new Polaczenie("http://www.polskapilka.net/1-liga/");
		Document dokument = polaczenie.dajDokument();
		
		// wyodrêbnianie ze strony samych nazw klubów, bez sortowania do tablicy stringów
		Elements duzyBox = dokument.getElementsByClass("box5");	
		Elements tabelaLigowa = duzyBox.select("table[class=table-ligowa]").select("td[class=td2]").select("a");
		String niesortowaneDruzyny = tabelaLigowa.html(); 
		alfabetycznaListaDruzyn = niesortowaneDruzyny.split("\n");
		
		// sortowanie b¹belkowe nazw klubów
		for(int i=0; i<alfabetycznaListaDruzyn.length-1; i++) {
			for(int j=0; j<alfabetycznaListaDruzyn.length-1; j++) {
				if(alfabetycznaListaDruzyn[j].compareTo(alfabetycznaListaDruzyn[j+1])>0) {
					String temp = alfabetycznaListaDruzyn[j+1];
					alfabetycznaListaDruzyn[j+1]=alfabetycznaListaDruzyn[j];
					alfabetycznaListaDruzyn[j]=temp;
				}
			}
		}
		
		// odkomentowanie do zobaczenia listy dru¿yn bior¹cych udzia³ w rozgrywkach
		for(int i=0; i<alfabetycznaListaDruzyn.length; i++) {
			System.out.println(alfabetycznaListaDruzyn[i]);
		}
	}
	
	public String[] pobierzListeDruzyn() {
		return alfabetycznaListaDruzyn;
	}

// lista dru¿yn alfabetycznie do oznaczenia w przysz³ych dzia³aniach
//	Arka Gdynia - 1
//	Chrobry G³ogów - 2 
//	GKS Jastrzêbie - 3
//	GKS Katowice - 4
//	GKS Tychy - 5
//	Górnik Polkowice - 6
//	Korona Kielce - 7
//	MiedŸ Legnica - 8
//	Odra Opole - 9
//	Podbeskidzie Bielsko-Bia³a - 10
//	Puszcza Niepo³omice - 11
//	Resovia - 12
//	Sandecja Nowy S¹cz - 13
//	Skra Czêstochowa - 14
//	Stomil Olsztyn - 15
//	Widzew £ódŸ - 16
//	Zag³êbie Sosnowiec - 17
//	£KS £ódŸ - 18
}

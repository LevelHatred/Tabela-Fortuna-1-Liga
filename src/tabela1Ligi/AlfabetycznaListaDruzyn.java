package tabela1Ligi;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AlfabetycznaListaDruzyn {
	private static String[] alfabetycznaListaDruzyn; //lista druzyn
	
	//konstruowanie klasy
	public AlfabetycznaListaDruzyn() {
		Polaczenie polaczenie = new Polaczenie();
		Document dokument = polaczenie.dajDokument();
		Elements duzyBox = dokument.getElementsByClass("box5");	// wyodrêbnij klase box5
		Elements tabelaLigowa = duzyBox.select("table[class=table-ligowa]").select("td[class=td2]").select("a"); // wyodrêbnij element a href
		String niesortowaneDruzyny = tabelaLigowa.html(); // wyodrêbnij same nazwy
		alfabetycznaListaDruzyn = niesortowaneDruzyny.split("\n"); // podziel nazwy klubów na tablice stringów, bez sortowania
		
		//sortowanie nazw
		for(int i=0; i<17;i++) {
			for(int j=0; j<17;j++) {
				if(alfabetycznaListaDruzyn[j].compareTo(alfabetycznaListaDruzyn[j+1])>0) {
					String temp = alfabetycznaListaDruzyn[j+1];
					alfabetycznaListaDruzyn[j+1]=alfabetycznaListaDruzyn[j];
					alfabetycznaListaDruzyn[j]=temp;
				}
			}
		}
		
	}
	
	public String[] pobierzListeDruzyn() {
		return alfabetycznaListaDruzyn;
	}

// lista druzyn alfabetycznie do oznaczenia potem
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

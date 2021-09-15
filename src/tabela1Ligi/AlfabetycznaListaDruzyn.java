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
		Elements duzyBox = dokument.getElementsByClass("box5");	// wyodr�bnij klase box5
		Elements tabelaLigowa = duzyBox.select("table[class=table-ligowa]").select("td[class=td2]").select("a"); // wyodr�bnij element a href
		String niesortowaneDruzyny = tabelaLigowa.html(); // wyodr�bnij same nazwy
		alfabetycznaListaDruzyn = niesortowaneDruzyny.split("\n"); // podziel nazwy klub�w na tablice string�w, bez sortowania
		
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
//	Chrobry G�og�w - 2 
//	GKS Jastrz�bie - 3
//	GKS Katowice - 4
//	GKS Tychy - 5
//	G�rnik Polkowice - 6
//	Korona Kielce - 7
//	Mied� Legnica - 8
//	Odra Opole - 9
//	Podbeskidzie Bielsko-Bia�a - 10
//	Puszcza Niepo�omice - 11
//	Resovia - 12
//	Sandecja Nowy S�cz - 13
//	Skra Cz�stochowa - 14
//	Stomil Olsztyn - 15
//	Widzew ��d� - 16
//	Zag��bie Sosnowiec - 17
//	�KS ��d� - 18
}

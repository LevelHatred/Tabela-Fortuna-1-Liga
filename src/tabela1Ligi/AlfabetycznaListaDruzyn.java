package tabela1Ligi;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AlfabetycznaListaDruzyn {
	// zmienne klasy
	private static String[] alfabetycznaListaDruzyn;
	
	// konstruowanie klasy
	public AlfabetycznaListaDruzyn() {
		// ��czenie si� z baz� informacji
		Polaczenie polaczenie = new Polaczenie("http://www.polskapilka.net/1-liga/");
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
		for(int i=0; i<alfabetycznaListaDruzyn.length; i++) {
			System.out.println(alfabetycznaListaDruzyn[i]);
		}
	}
	
	public String[] pobierzListeDruzyn() {
		return alfabetycznaListaDruzyn;
	}

// lista dru�yn alfabetycznie do oznaczenia w przysz�ych dzia�aniach
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

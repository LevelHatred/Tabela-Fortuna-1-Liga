package tabela1Ligi;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RezultatyMeczow {
	private static String [][] rezultatyMeczow;
	
	public RezultatyMeczow(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn) {
		rezultatyMeczow=stworzTabeleRezultatow(alfabetycznaListaDruzyn);
//		for(int i=0; i<19;i++) {
//			for(int j=0; j<19;j++) {
//				System.out.print(rezultatyMeczow[i][j]+"\t\t");
//			}
//			System.out.println();
//		}
	}
	
	private static String[][] stworzTabeleRezultatow(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn) {
		String[] temp1=alfabetycznaListaDruzyn.pobierzListeDruzyn();
		String[][] temp2 = new String[19][19];
		for(int gospodarz=0; gospodarz<19; gospodarz++) {
			for(int gosc=0; gosc<19; gosc++) {
				if(gospodarz==0 && gosc==0) {
					temp2[gospodarz][gosc]="Dru¿yny";
				}
				else if(gospodarz==gosc) {
					temp2[gospodarz][gosc]="x";
				}
				else if(gospodarz==0 && gosc!=0) {
					temp2[gospodarz][gosc]=temp1[gosc-1];
				}
				else if(gospodarz!=0 && gosc==0) {
					temp2[gospodarz][gosc]=temp1[gospodarz-1];
				}
				else {
					temp2[gospodarz][gosc]="-";
				}
			}
		}
		return temp2;
	}

	public void uzupelnijWyniki() {
		Connection polaczenie = Jsoup.connect("http://www.polskapilka.net/1-liga/");
		Document dokument;
		try {
			dokument = polaczenie.get();
			Elements gospodarze = dokument.select("table[class=kolejka]").select("td[class=td3]").select("div[class=f_left]").select("p");
			Elements wyniki = dokument.select("table[class=kolejka]").select("td[class=td3]").select("div[class=result]");
			Elements goscie = dokument.select("table[class=kolejka]").select("td[class=td3]").select("div[class=f_right]").select("p");
			String test = wyniki.html();
			System.out.println(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

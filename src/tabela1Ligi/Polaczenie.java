package tabela1Ligi;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Polaczenie {
	private Connection polaczenie;
	private Document dokument;
	public Polaczenie() {
		polaczenie = Jsoup.connect("http://www.polskapilka.net/1-liga/");
		try {
			dokument = polaczenie.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Document dajDokument() {
		return dokument;
	}
}

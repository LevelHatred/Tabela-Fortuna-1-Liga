package tabela1Ligi;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Polaczenie {
	//zmienne klasy
	private Connection polaczenie;
	private Document dokument;
	
	//konstruowanie klasy
	public Polaczenie(String s) {
		polaczenie = Jsoup.connect(s);
		try {
			dokument = polaczenie.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Document dajDokument() {
		return dokument;
	}
}

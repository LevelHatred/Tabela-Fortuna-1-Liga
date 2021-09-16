package tabela1Ligi;

public class Uruchom {

	public static void main(String[] args) {
		// generowanie pierwotnych informacji, pobranie listy dru�yn i rezultat�w znanych mecz�w
		AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn();
		RezultatyMeczow rezultatyMeczow = new RezultatyMeczow();
		rezultatyMeczow.pobierzWyniki();
		
		// uzupelnianie rezultatow do tablicy meczow
		rezultatyMeczow.uzupelnijWyniki(alfabetycznaListaDruzyn);
		
		Tabela tabela = new Tabela(alfabetycznaListaDruzyn, rezultatyMeczow);
		// otw�rz okienko GUI z tabela
		OkienkoGUI okienkoGUI = new OkienkoGUI(alfabetycznaListaDruzyn, tabela);
		okienkoGUI.setVisible(true);
	}

}

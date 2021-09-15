package tabela1Ligi;

public class Uruchom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn();
		RezultatyMeczow rezultatyMeczow = new RezultatyMeczow(alfabetycznaListaDruzyn);
		rezultatyMeczow.uzupelnijWyniki();
		OkienkoGUI okienkoGUI = new OkienkoGUI();
		okienkoGUI.setVisible(true);
	}

}

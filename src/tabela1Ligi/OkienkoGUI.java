package tabela1Ligi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import tabela1Ligi.WyborLigi.KrajLigi;

public class OkienkoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982292960425747281L;

	// zmienne klasy
	private Container kontenerBorderCenter = new Container();
	private JScrollPane suwakTabeli = new JScrollPane();
	private JTable tabela = new JTable();
	
	private Vector<Vector<String>> dane = new Vector<>();
	
	private Container kontenerBorderNorth = new Container();
	private Container kontenerBorderSouth = new Container();
	
	private String[] magazynNazwLig = new String[] {"Anglia - Premier League",
			"Francja - Ligue 1",
			"Hiszpania - La Liga",
			"Niemcy - Bundesliga",
			"Polska - PKO Ekstraklasa",
			"Polska - Fortuna 1 Liga",
			"Polska - 2 Liga",
			"W³ochy - Serie A"};
	private JComboBox<String> wyborLigiJCB;
	
	// konstruowanie klasy
	public OkienkoGUI() {
		stworzGUI();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		dodanieNaglowkowTabeli();
		szerokoscNaglowkowTabeli(tabela, 1000, new double[] {1,7,1,1,1,1,1,1,1});
		dodanieTabeli();
		dodanieWyboruLigi();
		poczatkoweDaneTabeli();
		
	}

	// stworz okienko, wymiar, wybierz Layout, umieœæ na œrodku
	private void stworzGUI() {
		this.setTitle("Tabele rozgrywek pi³karskich");
		this.setLayout(new BorderLayout(10,10));
		this.setSize(683, 500);
		this.setLocationRelativeTo(null);
	}
	
	// dodaj nag³ówki tabeli
	private void dodanieNaglowkowTabeli() {
		Vector<String> naglowki = new Vector<>();
		naglowki.add("Lp");
		naglowki.add("Druzyna");
		naglowki.add("M");
		naglowki.add("Pkt");
		naglowki.add("W");
		naglowki.add("R");
		naglowki.add("P");
		naglowki.add("BZ");
		naglowki.add("BS");
		
		tabela = new JTable(dane, naglowki);		
	}
	
	// ustaw szerokoœæ kolumn tabeli
	private void szerokoscNaglowkowTabeli(JTable tabela, int szerokoscTabeli, double... wypelnienie) {
		double suma = 0;
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			suma += wypelnienie[i];
		}
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			TableColumn kolumna = tabela.getColumnModel().getColumn(i);
			kolumna.setPreferredWidth((int) (szerokoscTabeli * (wypelnienie[i] / suma)));
		}
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			if(i==1) {
				continue;
			}
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tabela.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
	}
	
	// dodaj tabelê do suwaka i ca³oœæ do okienka
	private void dodanieTabeli() {
		suwakTabeli = new JScrollPane(tabela);
		this.add(kontenerBorderCenter, BorderLayout.CENTER);
		kontenerBorderCenter.setLayout(new GridLayout());	
		kontenerBorderCenter.add(suwakTabeli);
		tabela.setDefaultEditor(Object.class, null); // brak mo¿liwoœci edytowania komórki tabeli
		tabela.getTableHeader().setReorderingAllowed(false); // brak reorganizacji kolejnoœci kolumn tabeli
	}
	
	// tworzenie 
	private void dodanieWyboruLigi() {
		this.add(kontenerBorderNorth, BorderLayout.NORTH);
		wyborLigiJCB = new JComboBox<String>(magazynNazwLig);
		kontenerBorderNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		kontenerBorderNorth.add(wyborLigiJCB);
		wyborLigiJCB.setSelectedItem(magazynNazwLig[5]);
		
		
	}
	
	private void poczatkoweDaneTabeli() {
		WyborLigi wyborLigi;
		if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[0])) {
			wyborLigi = new WyborLigi(KrajLigi.ANGLIA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[1])) {
			wyborLigi = new WyborLigi(KrajLigi.FRANCJA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[2])) {
			wyborLigi = new WyborLigi(KrajLigi.HISZPANIA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[3])) {
			wyborLigi = new WyborLigi(KrajLigi.NIEMCY);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[4])) {
			wyborLigi = new WyborLigi(KrajLigi.POLSKA_EKSTRAKLASA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[5])) {
			wyborLigi = new WyborLigi(KrajLigi.POLSKA_1_LIGA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[6])) {
			wyborLigi = new WyborLigi(KrajLigi.POLSKA_2_LIGA);
		}
		else {
			wyborLigi = new WyborLigi(KrajLigi.W£OCHY);
		}
		AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn(wyborLigi.dajAdresDoPobraniaDanych());
		RezultatyMeczow rezultatyMeczow = new RezultatyMeczow(alfabetycznaListaDruzyn);
		rezultatyMeczow.pobierzWyniki(wyborLigi.dajAdresDoPobraniaDanych());
		rezultatyMeczow.uzupelnijWyniki(alfabetycznaListaDruzyn);
		Tabela tabela = new Tabela(alfabetycznaListaDruzyn, rezultatyMeczow);
		for(int i=0; i<alfabetycznaListaDruzyn.pobierzListeDruzyn().length; i++) {
			Vector<String> tempDane = new Vector<>();
			tempDane.add(String.valueOf(i+1));
			tempDane.add(alfabetycznaListaDruzyn.pobierzListeDruzyn()[i]);
			for (int j=0; j<7; j++) {
				tempDane.add(String.valueOf(0));
			}
			dane.add(tempDane);
		}
		uzupelnijTabele(tabela, alfabetycznaListaDruzyn);
	}
	
	private void uzupelnijTabele(Tabela tabelaZWynikami, AlfabetycznaListaDruzyn alfabetycznaListaDruzyn) {
		int[] rozegraneMeczeTabela = tabelaZWynikami.paczkaDanychTabeli()[0];
		int[] punkty = tabelaZWynikami.paczkaDanychTabeli()[1];
		int[] wygrane = tabelaZWynikami.paczkaDanychTabeli()[2];
		int[] remisy = tabelaZWynikami.paczkaDanychTabeli()[3];
		int[] porazki = tabelaZWynikami.paczkaDanychTabeli()[4];
		int[] bramkiStrzelone = tabelaZWynikami.paczkaDanychTabeli()[5];
		int[] bramkiStracone = tabelaZWynikami.paczkaDanychTabeli()[6];
		dane.clear();
		for(int i=0; i<alfabetycznaListaDruzyn.pobierzListeDruzyn().length; i++) {
			Vector<String> tempDane = new Vector<>();
			tempDane.add(String.valueOf(i+1));
			tempDane.add(tabelaZWynikami.druzynyTabela[i]);
			tempDane.add(String.valueOf(rozegraneMeczeTabela[i]));
			tempDane.add(String.valueOf(punkty[i]));
			tempDane.add(String.valueOf(wygrane[i]));
			tempDane.add(String.valueOf(remisy[i]));
			tempDane.add(String.valueOf(porazki[i]));
			tempDane.add(String.valueOf(bramkiStrzelone[i]));
			tempDane.add(String.valueOf(bramkiStracone[i]));
			dane.add(tempDane);
		}
	}
}

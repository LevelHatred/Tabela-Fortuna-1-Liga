package tabela1Ligi;

// import bibliotek
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import tabela1Ligi.WyborLigi.KrajLigi;

public class OkienkoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982292960425747281L; // usuniecie jakiegos warninga zwiazanego z JFrame

	// zmienne klasy OkienkoGUI
	private Container kontenerBorderCenter = new Container(); // kontener wewnatrz glownego centralnego layoutu
	private JScrollPane suwakTabeli = new JScrollPane(); // suwak do tabeli
	private JTable tabela = new JTable(); // tabela wsadzana do suwaka i do kontenera w srodku
	
	private Vector<Vector<String>> dane = new Vector<>(); // wektor danych 
	
	private Container kontenerBorderNorth = new Container(); // kontener wewn¹trz glownego polnocnego layoutu
	private Container kontenerBorderSouth = new Container(); // kontener wewn¹trz glownego poludniowego layoutu
	
	private JComboBox<String> wyborLigiJCB; // JComboBox do kontenera polnocnego z wyborem ligi do wyswietlania tabeli
	private String[] magazynNazwLig = new String[] {"Anglia - Premier League",
			"Francja - Ligue 1",
			"Hiszpania - La Liga",
			"Niemcy - Bundesliga",
			"Polska - PKO Ekstraklasa",
			"Polska - Fortuna 1 Liga",
			"Polska - 2 Liga",
			"W³ochy - Serie A"}; // lista nag³ówków do JComboBoxa
	
	private WyborLigi wyborLigi; // klasa z pakietem informacji jak adresHTML do poboru danych, karne punkty itd.
	
	// konstruowanie klasy
	public OkienkoGUI() {
		stworzGUI(); // pakiet instrukcji generujaca pierwotne okienko, rozmiar, umiejscowienie
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // wcisniecie krzyzyka okienka wylacza dzialanie programu
		dodanieNaglowkowTabeli(); // pakiet instrukcji dajacych naglowki tabeli
		szerokoscNaglowkowTabeli(tabela, 683, new double[] {1,7,1,1,1,1,1,1,1}); // ustaw szerokosc naglowkow dla preferowanego udzialu ulamkowego
		dodanieTabeli(); // dodaj tabele do centralnego kontenera pakietem instrukcji
		dodanieWyboruLigi(); // dodaj w glownym polnocnym layoucie JComboBox z naglowkami lig
		wypelnijTabele(); // wypelnij zawartosc tabeli
		kolorujKolumny();
		eventy();
		
	}

	// nadaj tytul okienka, okresl layout i jego granice, ustaw rozmiar okienka i lokalizacje
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
		
		tabela = new JTable(dane, naglowki); // stworz tabele z naglowkami zdefiniowanymi wczesniej	
	}
	
	// ustaw szerokoœæ kolumn tabeli. Na wejscie daj tabele JTable, szerokosc tabeli i stopien wypelnienia tej szerokosci
	private void szerokoscNaglowkowTabeli(JTable tabela, int szerokoscTabeli, double... wypelnienie) {
		double suma = 0;
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			suma += wypelnienie[i];
		} // wylicz sume stopni wypelnienia
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			TableColumn kolumna = tabela.getColumnModel().getColumn(i);
			kolumna.setPreferredWidth((int) (szerokoscTabeli * (wypelnienie[i] / suma)));
		} // ustaw preferowane szerokosci kolumn
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			if(i==1) {
				continue;
			}
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tabela.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		} // wysrodkuj elementy kolumn tabeli oprocz drugiej kolumny
	}
	
	// dodaj tabelê do suwaka, wstaw kontener do centralnego layoutu, kontener ustaw na GridLayout i do kontenera dodaj suwak z tabel¹
	private void dodanieTabeli() {
		suwakTabeli = new JScrollPane(tabela);
		this.add(kontenerBorderCenter, BorderLayout.CENTER);
		kontenerBorderCenter.setLayout(new GridLayout());	
		kontenerBorderCenter.add(suwakTabeli);
		tabela.setDefaultEditor(Object.class, null); // brak mo¿liwoœci edytowania komórki tabeli
		tabela.getTableHeader().setReorderingAllowed(false); // brak reorganizacji kolejnoœci kolumn tabeli
	}
	
	// dodanie kontenera do polnocnego glownego layoutu, zdefiniowanie JComboBoxa, ustawienie layoutu kontenera, dodanie JCB
	private void dodanieWyboruLigi() {
		this.add(kontenerBorderNorth, BorderLayout.NORTH);
		wyborLigiJCB = new JComboBox<String>(magazynNazwLig);
		kontenerBorderNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		kontenerBorderNorth.add(wyborLigiJCB);
		wyborLigiJCB.setSelectedItem(magazynNazwLig[5]); // ustawienie pierwszej domyslnej wartosci JComboBoxa
	}
	
	// wypelnij tabele zgodnie z wybran¹ pozycj¹ z listy JComboBoxa
	private void wypelnijTabele() {
		wyborLigi = this.odczytajWskazanieJComboBoxa(); // wybierz wlasciwego enuma
		AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn(wyborLigi.dajAdresDoPobraniaDanych()); // stworz obiekt z lista druzyn
		RezultatyMeczow rezultatyMeczow = new RezultatyMeczow(alfabetycznaListaDruzyn, wyborLigi); // zbierz rezultaty meczow
		Tabela tabela = new Tabela(alfabetycznaListaDruzyn, rezultatyMeczow, wyborLigi); // stworz tabele
		this.uzupelnijTabele(tabela, alfabetycznaListaDruzyn); // wstaw rekord do tabeli
	}
	
	// przekaz wskazanie JComboBoxa do wybrania wlasciwego enuma z poczatkowymi danymi
	private WyborLigi odczytajWskazanieJComboBoxa() {
		if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[0])) {
			return new WyborLigi(KrajLigi.ANGLIA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[1])) {
			return new WyborLigi(KrajLigi.FRANCJA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[2])) {
			return new WyborLigi(KrajLigi.HISZPANIA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[3])) {
			return new WyborLigi(KrajLigi.NIEMCY);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[4])) {
			return new WyborLigi(KrajLigi.POLSKA_EKSTRAKLASA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[5])) {
			return new WyborLigi(KrajLigi.POLSKA_1_LIGA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[6])) {
			return new WyborLigi(KrajLigi.POLSKA_2_LIGA);
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[7])){
			return new WyborLigi(KrajLigi.W£OCHY);
		}
		return new WyborLigi(KrajLigi.POLSKA_1_LIGA);
	}
	
	// wypelnij tabele wlasciwymi informacjami
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
		((DefaultTableModel)(tabela.getModel())).fireTableDataChanged(); // komenda ktora wraz ze zmiana zawartosci jcomboboxa 
		// nie wymaga zmiany wymiarow okienka do wystapienia zmian
	}
	
	private void kolorujKolumny() {
		int[] test1 = wyborLigi.liczbaDoKolorowania();
		int[][] test2 = wyborLigi.tablicaKolorow();
		
		int aT1, aT2, aT3, aT4, sT1, sT2;
		int[] kolorAT1, kolorAT2, kolorAT3, kolorAT4, kolorST1, kolorST2;
		
		aT1=test1[0];
		aT2=test1[1];
		aT3=test1[2];
		aT4=test1[3];
		sT1=test1[4];
		sT2=test1[5];
		kolorAT1=test2[0];
		kolorAT2=test2[1];
		kolorAT3=test2[2];
		kolorAT4=test2[3];
		kolorST1=test2[4];
		kolorST2=test2[5];
		

	}
	
	// eventy wystepujace na zmiane sytuacji lub przycisk
	private void eventy() {
		wyborLigiJCB.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	long startTime = System.currentTimeMillis();
		        wypelnijTabele();
		        long estimatedTime = System.currentTimeMillis()-startTime;
		        System.out.println("Czas poboru danych - " + estimatedTime);
		    }
		});
		
	}
}

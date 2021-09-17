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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import tabela1Ligi.WyborLigi.KrajLigi;

public class OkienkoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982292960425747281L; // usuniecie jakiegos warninga zwiazanego z JFrame

	// zmienne klasy OkienkoGUI
	private Container kontenerBorderCenter = new Container(); // kontener wewnatrz glownego centralnego layoutu
	private JScrollPane suwakTabeli = new JScrollPane(); // suwak do tabeli
	private JTable tabelaJT; // tabela wsadzana do suwaka i do kontenera w srodku
	
	private Vector<String> naglowki = new Vector<>();
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
	
	private DefaultTableCellRenderer[][] renderKomorek;
	
	// konstruowanie klasy
	public OkienkoGUI() {
		stworzGUI(); // pakiet instrukcji generujaca pierwotne okienko, rozmiar, umiejscowienie
		zagospodarujPolnocGlownegoLayoutu();
		zagospodarujCentrumGlownegoLayoutu();
		szerokoscNaglowkowTabeli(683, new double[] {1,7,1,1,1,1,1,1,1}); // ustaw szerokosc naglowkow dla preferowanego udzialu ulamkowego
		eventy();
		
	}

	// nadaj tytul okienka, okresl layout i jego granice, ustaw rozmiar okienka i lokalizacje
	private void stworzGUI() {
		this.setTitle("Tabele rozgrywek pi³karskich");
		this.setLayout(new BorderLayout(10,10));
		this.setSize(683, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // wcisniecie krzyzyka okienka wylacza dzialanie programu
	}
	
	private void zagospodarujPolnocGlownegoLayoutu() {
		dodanieWyboruLigi(); // dodaj w glownym polnocnym layoucie JComboBox z naglowkami lig
	}
	
	// dodanie kontenera do polnocnego glownego layoutu, zdefiniowanie JComboBoxa, ustawienie layoutu kontenera, dodanie JCB
	private void dodanieWyboruLigi() {
		this.add(kontenerBorderNorth, BorderLayout.NORTH);
		wyborLigiJCB = new JComboBox<String>(magazynNazwLig);
		kontenerBorderNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		kontenerBorderNorth.add(wyborLigiJCB);
		wyborLigiJCB.setSelectedItem(magazynNazwLig[5]); // ustawienie pierwszej domyslnej wartosci JComboBoxa
	}
	
	private void zagospodarujCentrumGlownegoLayoutu() {
		dodanieNaglowkowTabeli(); // pakiet instrukcji dajacych naglowki tabeli
		dodanieTabeli();
		wstawienieTabeliDoKontenera();
		
	}
	
	// dodaj nag³ówki tabeli
	private void dodanieNaglowkowTabeli() {
		naglowki.add("Lp");
		naglowki.add("Druzyna");
		naglowki.add("M");
		naglowki.add("Pkt");
		naglowki.add("W");
		naglowki.add("R");
		naglowki.add("P");
		naglowki.add("BZ");
		naglowki.add("BS");
		
	}

	private void dodanieTabeli() {
		
		przygotowanieWygladuTabeli();
		stworzenieTresciTabeli();
	}
	
	private void stworzenieTresciTabeli() {
		wyborLigi = this.odczytajWskazanieJComboBoxa(); // wybierz wlasciwego enuma
		AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn(wyborLigi.dajAdresDoPobraniaDanych()); // stworz obiekt z lista druzyn
		RezultatyMeczow rezultatyMeczow = new RezultatyMeczow(alfabetycznaListaDruzyn, wyborLigi); // zbierz rezultaty meczow
		Tabela tabela = new Tabela(alfabetycznaListaDruzyn, rezultatyMeczow, wyborLigi); // stworz tabele
		this.wstawRekordyTabeli(tabela, alfabetycznaListaDruzyn); // wstaw rekord do tabeli
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
	private void wstawRekordyTabeli(Tabela tabela, AlfabetycznaListaDruzyn alfabetycznaListaDruzyn) {
		int[] rozegraneMeczeTabela = tabela.paczkaDanychTabeli()[0];
		int[] punkty = tabela.paczkaDanychTabeli()[1];
		int[] wygrane = tabela.paczkaDanychTabeli()[2];
		int[] remisy = tabela.paczkaDanychTabeli()[3];
		int[] porazki = tabela.paczkaDanychTabeli()[4];
		int[] bramkiStrzelone = tabela.paczkaDanychTabeli()[5];
		int[] bramkiStracone = tabela.paczkaDanychTabeli()[6];
		dane.clear();
		for(int i=0; i<alfabetycznaListaDruzyn.pobierzListeDruzyn().length; i++) {
			Vector<String> tempDane = new Vector<>();
			tempDane.add(String.valueOf(i+1));
			tempDane.add(tabela.druzynyTabela[i]);
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
	
	private void przygotowanieWygladuTabeli() {
		
		tabelaJT = new JTable(dane, naglowki) 
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -8598422295593414811L;
			
			@Override
            public Component prepareRenderer(TableCellRenderer renderer, int wiersz, int kolumna) {
				int[] paczka1 = wyborLigi.liczbaDoKolorowania();
				int[][] paczka2 = wyborLigi.tablicaKolorow();
				int aT1, aT2, aT3, aT4, sT1, sT2;
				int[] kolorAT1, kolorAT2, kolorAT3, kolorAT4, kolorST1, kolorST2;
				
				aT1=paczka1[0];
				aT2=paczka1[1];
				aT3=paczka1[2];
				aT4=paczka1[3];
				sT1=paczka1[4];
				sT2=paczka1[5];
				kolorAT1=paczka2[0];
				kolorAT2=paczka2[1];
				kolorAT3=paczka2[2];
				kolorAT4=paczka2[3];
				kolorST1=paczka2[4];
				kolorST2=paczka2[5];
				int liczbaDruzyn = dane.size();
                Component comp = super.prepareRenderer(renderer, wiersz, kolumna);
                if(wiersz<aT1) {
                	comp.setBackground(new Color(kolorAT1[0], kolorAT1[1], kolorAT1[2]));
                }
                else if (wiersz<aT1+aT2){
                	comp.setBackground(new Color(kolorAT2[0], kolorAT2[1], kolorAT2[2]));
                }
                else if (wiersz<aT1+aT2+aT3){
                	comp.setBackground(new Color(kolorAT3[0], kolorAT3[1], kolorAT3[2]));
                }
                else if (wiersz<aT1+aT2+aT3+aT4){
                	comp.setBackground(new Color(kolorAT4[0], kolorAT4[1], kolorAT4[2]));
                }
                else if (wiersz>liczbaDruzyn-sT1) {
                	comp.setBackground(new Color(kolorST1[0], kolorST1[1], kolorST1[2]));
                }
                else if (wiersz>liczbaDruzyn-sT1-sT2) {
                	comp.setBackground(new Color(kolorST2[0], kolorST2[1], kolorST2[2]));
                }
                else {
                	comp.setBackground(Color.WHITE);
                }
                return comp;
            }
		}
		; // stworz tabele z naglowkami zdefiniowanymi wczesniej	
		for (int i=0; i < tabelaJT.getColumnModel().getColumnCount(); i++) {
			if(i==1) {
				continue;
			}
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tabelaJT.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		} // wysrodkuj elementy kolumn tabeli oprocz drugiej kolumny
		
	}
	
	// dodaj tabelê do suwaka, wstaw kontener do centralnego layoutu, kontener ustaw na GridLayout i do kontenera dodaj suwak z tabel¹
	private void wstawienieTabeliDoKontenera() {
		suwakTabeli = new JScrollPane(tabelaJT);
		this.add(kontenerBorderCenter, BorderLayout.CENTER);
		kontenerBorderCenter.setLayout(new GridLayout());	
		kontenerBorderCenter.add(suwakTabeli);
		tabelaJT.setDefaultEditor(Object.class, null); // brak mo¿liwoœci edytowania komórki tabeli
		tabelaJT.getTableHeader().setReorderingAllowed(false); // brak reorganizacji kolejnoœci kolumn tabeli
	}
	
	// ustaw szerokoœæ kolumn tabeli. Na wejscie daj tabele JTable, szerokosc tabeli i stopien wypelnienia tej szerokosci
	private void szerokoscNaglowkowTabeli(int szerokoscTabeli, double... wypelnienie) {
		double suma = 0;
		for (int i=0; i < tabelaJT.getColumnModel().getColumnCount(); i++) {
			suma += wypelnienie[i];
		} // wylicz sume stopni wypelnienia
		for (int i=0; i < tabelaJT.getColumnModel().getColumnCount(); i++) {
			TableColumn kolumna = tabelaJT.getColumnModel().getColumn(i);
			kolumna.setPreferredWidth((int) (szerokoscTabeli * (wypelnienie[i] / suma)));
		} // ustaw preferowane szerokosci kolumn
	}

	// eventy wystepujace na zmiane sytuacji lub przycisk
	private void eventy() {
		wyborLigiJCB.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	long startTime = System.currentTimeMillis();
		    	stworzenieTresciTabeli();
		    	((DefaultTableModel)(tabelaJT.getModel())).fireTableDataChanged();
		        long estimatedTime = System.currentTimeMillis()-startTime;
		        System.out.println("Czas poboru danych - " + estimatedTime);
		    }
		});
		
	}
}

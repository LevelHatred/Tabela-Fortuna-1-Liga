package tabela1Ligi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import tabela1Ligi.WyborLigi.KrajLigi;

public class OkienkoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982292960425747281L; // usuniecie jakiegos warninga zwiazanego z JFrame

	// zmienne klasy OkienkoGUI
	
	// kontenery do elementow
	private Container kontenerBorderCenter = new Container(); // glowny centralny layout
	private Container kontenerBorderNorth = new Container(); // glowny polnocny layout
	private Container kontenerBorderSouth = new Container(); // glowny poludniowy layout
	
	// elementy wizualne tabeli
	private JScrollPane suwakTabeli; // suwak do tabeli
	private JTable tabelaJT; // tabela wsadzana do suwaka
	private JLabel legendaJL = new JLabel(); // opis legendy
	private JButton pokazTerminarzJB = new JButton("Poka¿ terminarz");
	
	// dane tabeli
	private Vector<String> naglowki = new Vector<>(); // naglowki
	private Vector<Vector<String>> dane = new Vector<>(); // wektor danych 
	
	// mozliwosc wyboru ligi
	private JComboBox<String> wyborLigiJCB; // JComboBox z wyborem ligi
	private String[] magazynNazwLig = new String[] {"Anglia - Premier League",
			"Francja - Ligue 1",
			"Hiszpania - La Liga",
			"Niemcy - Bundesliga",
			"Polska - PKO Ekstraklasa",
			"Polska - Fortuna 1 Liga",
			"Polska - 2 Liga",
			"W³ochy - Serie A"}; // lista nag³ówków do JComboBoxa
	private WyborLigi wyborLigi; // klasa z pakietem informacji jak adresHTML do poboru danych, karne punkty itd. przy wybraniu JComboBoxa
	

	
	// konstruowanie klasy
	public OkienkoGUI() {
		stworzGUI(); // pakiet instrukcji generujaca pierwotne okienko, rozmiar, umiejscowienie
		zagospodarujPolnocGlownegoLayoutu();
		zagospodarujCentrumGlownegoLayoutu();
		szerokoscNaglowkowTabeli(683, new double[] {1,7,1,1,1,1,1,1,1}); // ustaw szerokosc naglowkow dla preferowanego udzialu ulamkowego
		zagospodarujPoludnieGlownegoLayoutu();
		eventy();
		
	}

	// nadaj tytul okienka, okresl layout i jego granice, ustaw rozmiar okienka i lokalizacje
	private void stworzGUI() {
		this.setTitle("Tabele rozgrywek pi³karskich");
		this.setLayout(new BorderLayout(10,10));
		this.setSize(683, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // wcisniecie krzyzyka okienka wylacza dzialanie programu
	}
	
	// stworz wyglad polnocnego layoutu
	private void zagospodarujPolnocGlownegoLayoutu() {
		dodanieWyboruLigi(); // dodaj w glownym polnocnym layoucie JComboBox z naglowkami lig
		kontenerBorderNorth.add(pokazTerminarzJB);
	}
	
	// dodanie kontenera do polnocnego glownego layoutu, zdefiniowanie JComboBoxa, ustawienie layoutu kontenera, dodanie JCB
	private void dodanieWyboruLigi() {
		this.add(kontenerBorderNorth, BorderLayout.NORTH);
		wyborLigiJCB = new JComboBox<String>(magazynNazwLig);
		kontenerBorderNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		kontenerBorderNorth.add(wyborLigiJCB);
		wyborLigiJCB.setSelectedItem(magazynNazwLig[5]); // ustawienie pierwszej domyslnej wartosci JComboBoxa
	}
	
	// stworz wyglad centralnego layoutu
	private void zagospodarujCentrumGlownegoLayoutu() {
		dodanieNaglowkowTabeli(); // pakiet instrukcji dajacych naglowki tabeli
		stworzenieTresciTabeli(); // pobranie wlasciwych danych tabeli
		przygotowanieWygladuTabeli(); // generowanie instrukcji ktore zmianiaja wyglad tabeli, nawet przy zmianie pobranych danych
		wstawienieTabeliDoKontenera(); // wstawienie tabeli do kontenera
		
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
	
	// pobranie danych do tabeli ktore wynikaja z wyboru jComboBoxa
	private void stworzenieTresciTabeli() {
		wyborLigi = this.odczytajWskazanieJComboBoxa(); // wybierz wlasciwego enuma na bazie JComboBoxa
		AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn(wyborLigi.dajAdresDoPobraniaDanych()); // stworz obiekt z lista druzyn
		RezultatyMeczow rezultatyMeczow = new RezultatyMeczow(alfabetycznaListaDruzyn, wyborLigi); // zbierz rezultaty meczow
		Tabela tabela = new Tabela(alfabetycznaListaDruzyn, rezultatyMeczow, wyborLigi); // stworz tabele
		this.wstawRekordyTabeli(tabela, alfabetycznaListaDruzyn); // wstaw rekord do tabeli
	}
	
	// przekaz wskazanie JComboBoxa do wybrania wlasciwego enuma z poczatkowymi danymi
	private WyborLigi odczytajWskazanieJComboBoxa() {
		Object aktualnyWybor = wyborLigiJCB.getSelectedItem();
		if(aktualnyWybor.equals(magazynNazwLig[0])) {
			return new WyborLigi(KrajLigi.ANGLIA);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[1])) {
			return new WyborLigi(KrajLigi.FRANCJA);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[2])) {
			return new WyborLigi(KrajLigi.HISZPANIA);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[3])) {
			return new WyborLigi(KrajLigi.NIEMCY);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[4])) {
			return new WyborLigi(KrajLigi.POLSKA_EKSTRAKLASA);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[5])) {
			return new WyborLigi(KrajLigi.POLSKA_1_LIGA);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[6])) {
			return new WyborLigi(KrajLigi.POLSKA_2_LIGA);
		}
		else if(aktualnyWybor.equals(magazynNazwLig[7])){
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
	
	// przygotowanie wygladu tabeli, wysrodkowanie wlasciwych kolumn i malowanie wierszy
	private void przygotowanieWygladuTabeli() {
		
		tabelaJT = new JTable(dane, naglowki) 
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -8598422295593414811L; // usuniecie jakiegos warninga
			
			// prerenderer tabeli czyli malowanie tla i srodkowanie napisow w komorkach
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
		};
		
		// wysrodkuj elementy kolumn tabeli oprocz drugiej kolumny
		for (int i=0; i < tabelaJT.getColumnModel().getColumnCount(); i++) {
			if(i==1) {
				continue;
			}
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tabelaJT.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		} 
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
	
	private void zagospodarujPoludnieGlownegoLayoutu() {
		this.add(kontenerBorderSouth, BorderLayout.SOUTH);
		kontenerBorderSouth.setLayout(new GridLayout(1,1,10,10));
		kontenerBorderSouth.add(legendaJL);
		dodanieLegendy();
		dodanieOpisuKolorow();
	}

	private void dodanieLegendy() {
		String tekstLegendy = "<html>&nbsp;Legenda:<br/>"
				+ "&nbsp;Lp - miejsce&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;M - iloœæ rozegranych meczów&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;Pkt - iloœæ zdobytych punktów<br/>"
				+ "&nbsp;W - iloœæ wygranych&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;R - iloœæ remisów&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;P - iloœæ pora¿ek<br/>"
				+ "&nbsp;BZ - bramki zdobyte&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;BS - bramki stracone</html>";
		
		if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[1])) {
			tekstLegendy=tekstLegendy.substring(0,tekstLegendy.length()-7);
			tekstLegendy=tekstLegendy+"<br/>&nbsp;Nice -1 punkt</html>";
		}
		else if(wyborLigiJCB.getSelectedItem().equals(magazynNazwLig[6])) {
			tekstLegendy=tekstLegendy.substring(0,tekstLegendy.length()-7);
			tekstLegendy=tekstLegendy+"<br/>&nbsp;GKS Be³chatów -4 punkty</html>";
		}
		legendaJL.setText(tekstLegendy);
	}
	
	private void dodanieOpisuKolorow() {
		String tekst=legendaJL.getText();
		int[] paczka1 = wyborLigi.liczbaDoKolorowania();
		int[][] paczka2 = wyborLigi.tablicaKolorow();
		String[] paczka3 = wyborLigi.tekstyDoKolorowania();
		
		if(paczka1[0]!=0) {
			tekst=tekst.substring(0,tekst.length()-7);
			tekst = tekst + "<p style=\"color:rgb("+Integer.toString(paczka2[0][0])+","+Integer.toString(paczka2[0][1])+","+Integer.toString(paczka2[0][2])+");\">&nbsp;"
					+ paczka3[0] + "</p></html>";
			//tekst = tekst + "asdasdasd</html>";
		}
		if(paczka1[1]!=0) {
			tekst=tekst.substring(0,tekst.length()-7);
			tekst = tekst + "<p style=\"color:rgb("+Integer.toString(paczka2[1][0])+","+Integer.toString(paczka2[1][1])+","+Integer.toString(paczka2[1][2])+");\">&nbsp;"
					+ paczka3[1] + "</p></html>";
			//tekst = tekst + "asdasdasd</html>";
		}
		if(paczka1[2]!=0) {
			tekst=tekst.substring(0,tekst.length()-7);
			tekst = tekst + "<p style=\"color:rgb("+Integer.toString(paczka2[2][0])+","+Integer.toString(paczka2[2][1])+","+Integer.toString(paczka2[2][2])+");\">&nbsp;"
					+ paczka3[2] + "</p></html>";
			//tekst = tekst + "asdasdasd</html>";
		}
		if(paczka1[3]!=0) {
			tekst=tekst.substring(0,tekst.length()-7);
			tekst = tekst + "<p style=\"color:rgb("+Integer.toString(paczka2[3][0])+","+Integer.toString(paczka2[3][1])+","+Integer.toString(paczka2[3][2])+");\">&nbsp;"
					+ paczka3[3] + "</p></html>";
			//tekst = tekst + "asdasdasd</html>";
		}
		if(paczka1[4]!=0) {
			tekst=tekst.substring(0,tekst.length()-7);
			tekst = tekst + "<p style=\"color:rgb("+Integer.toString(paczka2[4][0])+","+Integer.toString(paczka2[4][1])+","+Integer.toString(paczka2[4][2])+");\">&nbsp;"
					+ paczka3[4] + "</p></html>";
			//tekst = tekst + "asdasdasd</html>";
		}
		if(paczka1[5]!=0) {
			tekst=tekst.substring(0,tekst.length()-7);
			tekst = tekst + "<p style=\"color:rgb("+Integer.toString(paczka2[5][0])+","+Integer.toString(paczka2[5][1])+","+Integer.toString(paczka2[5][2])+");\">&nbsp;"
					+ paczka3[5] + "</p></html>";
			//tekst = tekst + "asdasdasd</html>";
		}
		legendaJL.setText(tekst);

		//System.out.println(tekst);
	}

	// eventy wystepujace na zmiane sytuacji lub przycisk
	private void eventy() {
		wyborLigiJCB.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	long startTime = System.currentTimeMillis();
		    	stworzenieTresciTabeli();
		    	dodanieLegendy();
		    	dodanieOpisuKolorow();
		    	((DefaultTableModel)(tabelaJT.getModel())).fireTableDataChanged();
		        long estimatedTime = System.currentTimeMillis()-startTime;
		        System.out.println("Czas poboru danych - " + estimatedTime);
		    }
		});
		
		pokazTerminarzJB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlfabetycznaListaDruzyn alfabetycznaListaDruzyn = new AlfabetycznaListaDruzyn(wyborLigi.dajAdresDoPobraniaDanych()); // stworz obiekt z lista druzyn
				RezultatyMeczow rezultatyMeczow = new RezultatyMeczow(alfabetycznaListaDruzyn, wyborLigi); // zbierz rezultaty meczow
				OkienkoJDialog okienkoJDialog = new OkienkoJDialog(rezultatyMeczow);
				okienkoJDialog.setVisible(true);
			}
		});
	}
}

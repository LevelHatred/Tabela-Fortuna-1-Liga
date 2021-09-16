package tabela1Ligi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class OkienkoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982292960425747281L;

	// zmienne klasy
	private Container kontenerBorderCenter = new Container();
	private JScrollPane suwakTabeli = new JScrollPane();
	private JTable tabela = new JTable();
	
	private Container kontererBorderSouth = new Container();
	
	private Vector<Vector<String>> dane = new Vector<>();
	
	
	// konstruowanie klasy
	public OkienkoGUI(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn, Tabela tabelaZWynikami) {
		stworzGUI();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		dodanieNaglowkowTabeli();
		szerokoscNaglowkowTabeli(tabela, 1000, new double[] {1,7,1,1,1,1,1,1,1});
		dodanieTabeli();
		poczatkoweDaneTabeli(alfabetycznaListaDruzyn);
		uzupelnijTabele(tabelaZWynikami, alfabetycznaListaDruzyn);
	}

	// stworz okienko, wymiar, wybierz Layout, umieœæ na œrodku
	private void stworzGUI() {
		this.setTitle("Fortuna 1 Liga");
		this.setLayout(new BorderLayout(10,10));
		this.setSize(683, 384);
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
	
	private void poczatkoweDaneTabeli(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn) {
		for(int i=0; i<alfabetycznaListaDruzyn.pobierzListeDruzyn().length; i++) {
			Vector<String> tempDane = new Vector<>();
			tempDane.add(String.valueOf(i+1));
			tempDane.add(alfabetycznaListaDruzyn.pobierzListeDruzyn()[i]);
			for (int j=0; j<7; j++) {
				tempDane.add(String.valueOf(0));
			}
			dane.add(tempDane);
		}
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

package tabela1Ligi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OkienkoJDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4805810178686097655L;
	private String[] listaGospodarzy, listaWynikow, listaGosci;
	private Vector<String> naglowki = new Vector<>();
	private Vector<Vector<String>> dane = new Vector<>(); 
	private JComboBox<Integer> nrKolejkiJCB; 
	private JScrollPane suwakKolejek; // suwak do tabeli
	private JTable wynikiKolejkiJT;
	
	public OkienkoJDialog(RezultatyMeczow rezultatyMeczow) {
		this.setTitle("Terminarz");
		inicjalizacjaJDialog();
		naglowki.add("Gospodarz");
		naglowki.add("Wynik");
		naglowki.add("Goœæ");
		listaGospodarzy=rezultatyMeczow.dajCiagKolejek()[0];
		listaWynikow=rezultatyMeczow.dajCiagKolejek()[1];
		listaGosci=rezultatyMeczow.dajCiagKolejek()[2];
		int liczbaKolejek=listaGospodarzy.length/(rezultatyMeczow.dajRezultatyMeczow().length/2);
		Integer[] nrKolejkiI = new Integer[liczbaKolejek];
		for(int i=1; i<=liczbaKolejek; i++) {
			nrKolejkiI[i-1]=i;
		}
		nrKolejkiJCB = new JComboBox<Integer> (nrKolejkiI);
		Container kontenerPolnocny = new Container();
		this.add(kontenerPolnocny, BorderLayout.NORTH);
		kontenerPolnocny.setLayout(new FlowLayout());
		kontenerPolnocny.add(nrKolejkiJCB);
		

		
		wynikiKolejkiJT = new JTable(dane, naglowki);
		dane.clear();
		int aktualnyNumerJCB=nrKolejkiJCB.getSelectedIndex();
		int poczatkowyIndeks=aktualnyNumerJCB*(rezultatyMeczow.dajRezultatyMeczow().length/2);
		int liczbaMeczowNaKolejka=rezultatyMeczow.dajRezultatyMeczow().length/2;
		for(int i=poczatkowyIndeks;i<poczatkowyIndeks+liczbaMeczowNaKolejka;i++) {
			Vector<String> tempDane = new Vector<>(); 
			tempDane.add(listaGospodarzy[i]);
			tempDane.add(listaWynikow[i]);
			tempDane.add(listaGosci[i]);
			dane.add(tempDane);
		}
		Container kontenerCentralny = new Container();
		this.add(kontenerCentralny, BorderLayout.CENTER);
		
		kontenerCentralny.setLayout(new GridLayout());
		suwakKolejek = new JScrollPane(wynikiKolejkiJT);
		kontenerCentralny.add(suwakKolejek);
		
		((DefaultTableModel)(wynikiKolejkiJT.getModel())).fireTableDataChanged();
		
		
		nrKolejkiJCB.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	dane.clear();
				int aktualnyNumerJCB=nrKolejkiJCB.getSelectedIndex();
				int poczatkowyIndeks=aktualnyNumerJCB*(rezultatyMeczow.dajRezultatyMeczow().length/2);
				int liczbaMeczowNaKolejka=rezultatyMeczow.dajRezultatyMeczow().length/2;
				for(int i=poczatkowyIndeks;i<poczatkowyIndeks+liczbaMeczowNaKolejka;i++) {
					Vector<String> tempDane = new Vector<>(); 
					tempDane.add(listaGospodarzy[i]);
					tempDane.add(listaWynikow[i]);
					tempDane.add(listaGosci[i]);
					dane.add(tempDane);
					//System.out.println(listaGospodarzy[i]);
				}
		    	((DefaultTableModel)(wynikiKolejkiJT.getModel())).fireTableDataChanged();
		    }
		});
	}

	private void inicjalizacjaJDialog() {
		this.setLayout(new BorderLayout());
		this.setSize(600, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setModal(true);
	}	
}

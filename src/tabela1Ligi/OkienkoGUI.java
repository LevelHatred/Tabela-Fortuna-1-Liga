package tabela1Ligi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class OkienkoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982292960425747281L;

	private Container kontenerBorderCenter = new Container();
	private JScrollPane suwakTabeli = new JScrollPane();
	private JTable tabela = new JTable();
	
	private Vector<Vector<String>> dane;
	
	public OkienkoGUI() {
		stworzGUI();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		dodanieNaglowkowTabeli();
		dodanieTabeli();
	}

	private void stworzGUI() {
		this.setTitle("Fortuna 1 Liga");
		this.setLayout(new BorderLayout(10,10));
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
	}
	
	private void dodanieNaglowkowTabeli() {
		Vector<String> naglowki = new Vector<>();
		naglowki.add("Lp");
		naglowki.add("Druzyna");
		naglowki.add("Mecze");
		naglowki.add("Punkty");
		naglowki.add("Wygrane");
		naglowki.add("Remisy");
		naglowki.add("Porazki");
		naglowki.add("Bramki strzelone");
		naglowki.add("Bramki stracone");
		
		tabela = new JTable(dane, naglowki);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(4);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
	}
	
	private void dodanieTabeli() {
		suwakTabeli = new JScrollPane(tabela);
		this.add(kontenerBorderCenter, BorderLayout.CENTER);
		kontenerBorderCenter.setLayout(new GridLayout());	
		kontenerBorderCenter.add(suwakTabeli);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
	}
}

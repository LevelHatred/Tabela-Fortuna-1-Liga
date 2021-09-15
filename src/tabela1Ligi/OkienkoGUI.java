package tabela1Ligi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;

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
		szerokoscNaglowkowTabeli(tabela, 1000, new double[] {1,7,1,1,1,1,1,1,1});
		dodanieTabeli();
	}

	private void stworzGUI() {
		this.setTitle("Fortuna 1 Liga");
		this.setLayout(new BorderLayout(10,10));
		this.setSize(1366, 768);
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
	}
	
	private void szerokoscNaglowkowTabeli(JTable tabela, int szerokoscTabeli, double... wypelnienie) {
		double suma = 0;
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			suma += wypelnienie[i];
		}
		for (int i=0; i < tabela.getColumnModel().getColumnCount(); i++) {
			TableColumn kolumna = tabela.getColumnModel().getColumn(i);
			kolumna.setPreferredWidth((int) (szerokoscTabeli * (wypelnienie[i] / suma)));
		}
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

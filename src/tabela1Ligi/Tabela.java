package tabela1Ligi;

public class Tabela {
	String[] druzynyTabela = new String[18];
	private int[] rozegraneMeczeTabela = new int[18];
	private int[] punkty = new int[18];
	private int[] wygrane = new int[18];
	private int[] remisy = new int[18];
	private int[] porazki = new int[18];
	private int[] bramkiStrzelone = new int[18];
	private int[] bramkiStracone = new int[18];
	
	public Tabela(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn, RezultatyMeczow rezultatyMeczow) {
		for(int i=0; i<18; i++) {
			druzynyTabela[i]=alfabetycznaListaDruzyn.pobierzListeDruzyn()[i];
			rozegraneMeczeTabela[i]=0;
			punkty[i]=0;
			wygrane[i]=0;
			remisy[i]=0;
			porazki[i]=0;
			bramkiStrzelone[i]=0;
			bramkiStracone[i]=0;
			
		}
		for(int i=0; i<18; i++) {
			for(int j=0; j<18; j++) {
				if(i==j) {
					continue;
				}
				else if(rezultatyMeczow.dajRezultatyMeczow()[i][j].equals("-")) {
					continue;
				}
				else {
					int gospodarz = i;
					int gosc = j;
					rozegraneMeczeTabela[i]++;
					rozegraneMeczeTabela[j]++;
					String[] wynik = rezultatyMeczow.dajRezultatyMeczow()[i][j].split("-");
					if(Integer.valueOf(wynik[0])>Integer.valueOf(wynik[1])) {
						punkty[i]+=3;
						wygrane[i]++;
						porazki[j]++;
						bramkiStrzelone[i]+=Integer.valueOf(wynik[0]);
						bramkiStracone[i]+=Integer.valueOf(wynik[1]);
						bramkiStrzelone[j]+=Integer.valueOf(wynik[1]);
						bramkiStracone[j]+=Integer.valueOf(wynik[0]);
					}
					else if(Integer.valueOf(wynik[0])<Integer.valueOf(wynik[1])) {
						punkty[j]+=3;
						wygrane[j]++;
						porazki[i]++;
						bramkiStrzelone[i]+=Integer.valueOf(wynik[0]);
						bramkiStracone[i]+=Integer.valueOf(wynik[1]);
						bramkiStrzelone[j]+=Integer.valueOf(wynik[1]);
						bramkiStracone[j]+=Integer.valueOf(wynik[0]);
					}
					else {
						punkty[i]++;
						punkty[j]++;
						remisy[i]++;
						remisy[j]++;
						bramkiStrzelone[i]+=Integer.valueOf(wynik[0]);
						bramkiStracone[i]+=Integer.valueOf(wynik[1]);
						bramkiStrzelone[j]+=Integer.valueOf(wynik[1]);
						bramkiStracone[j]+=Integer.valueOf(wynik[0]);
					}
				}
			}
		}
		for(int i=0; i<17; i++) {
			for(int j=0; j<17; j++) {
				if(punkty[j]<punkty[j+1]) {
					int temp1, temp2, temp3, temp4, temp5, temp6, temp7;
					String tempS = druzynyTabela[j];
					temp1 = rozegraneMeczeTabela[j];
					temp2=punkty[j];
					temp3=wygrane[j];
					temp4=remisy[j];
					temp5=porazki[j];
					temp6=bramkiStrzelone[j];
					temp7=bramkiStracone[j];
					
					druzynyTabela[j]=druzynyTabela[j+1];
					rozegraneMeczeTabela[j]=rozegraneMeczeTabela[j+1];
					punkty[j]=punkty[j+1];
					wygrane[j]=wygrane[j+1];
					remisy[j]=remisy[j+1];
					porazki[j]=porazki[j+1];
					bramkiStrzelone[j]=bramkiStrzelone[j+1];
					bramkiStracone[j]=bramkiStracone[j+1];
					
					druzynyTabela[j+1]=tempS;
					rozegraneMeczeTabela[j+1]=temp1;
					punkty[j+1]=temp2;
					wygrane[j+1]=temp3;
					remisy[j+1]=temp4;
					porazki[j+1]=temp5;
					bramkiStrzelone[j+1]=temp6;
					bramkiStracone[j+1]=temp7;
					
				}
			}
		}
	}
	public int[][] paczkaDanychTabeli(){
		return new int[][] {rozegraneMeczeTabela, punkty, wygrane, remisy, porazki, bramkiStrzelone, bramkiStracone};
	}
	
	public String[] druzynyTabeli(){
		return druzynyTabela;
	}
}

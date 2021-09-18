package tabela1Ligi;

public class Tabela {
	
	// zmienne klasy
	String[] druzynyTabela;
	private int[] rozegraneMeczeTabela;
	private int[] punkty;
	private int[] wygrane;
	private int[] remisy;
	private int[] porazki;
	private int[] bramkiStrzelone;
	private int[] bramkiStracone;
	
	// konstruowanie klasy
	public Tabela(AlfabetycznaListaDruzyn alfabetycznaListaDruzyn, RezultatyMeczow rezultatyMeczow, WyborLigi wyborLigi) {
		
		// inicjowanie danych tabeli
		int liczbaDruzyn=rezultatyMeczow.dajRezultatyMeczow().length;
		druzynyTabela = new String[liczbaDruzyn];
		rozegraneMeczeTabela = new int[liczbaDruzyn];
		punkty = new int[liczbaDruzyn];
		wygrane = new int[liczbaDruzyn];
		remisy = new int[liczbaDruzyn];
		porazki = new int[liczbaDruzyn];
		bramkiStrzelone = new int[liczbaDruzyn];
		bramkiStracone = new int[liczbaDruzyn];
		
		// zaimportowanie karnych punktow;
		int[] karnePunkty = wyborLigi.pobierzKarnePunkty();
		String[] ALD = alfabetycznaListaDruzyn.pobierzListeDruzyn();
		
		// wypelnianie danych takich jak mecze, bramki etc druzyn zerami i dodanie karnych punktow
		for(int i=0; i<liczbaDruzyn; i++) {
			druzynyTabela[i]=ALD[i];
			rozegraneMeczeTabela[i]=0;
			punkty[i]=karnePunkty[i];
			wygrane[i]=0;
			remisy[i]=0;
			porazki[i]=0;
			bramkiStrzelone[i]=0;
			bramkiStracone[i]=0;
		}
		
		// uwzglednianie rezultatow meczy do tabeli
		for(int i=0; i<liczbaDruzyn; i++) {
			for(int j=0; j<liczbaDruzyn; j++) {
				if(i==j) {
					continue;
				}
				else if(rezultatyMeczow.dajRezultatyMeczow()[i][j].equals("-")) {
					continue;
				}
				else if(rezultatyMeczow.dajRezultatyMeczow()[i][j].equals("-:-")) {
					continue;
				}
				else if(rezultatyMeczow.dajRezultatyMeczow()[i][j].equals("<br>Odwo³any")) {
					continue;
				}
				else {
					//gospodarz - i, gosc - j
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
		
		// sortowanie tabeli po punktach druzyn
		for(int i=0; i<liczbaDruzyn-1; i++) {
			for(int j=0; j<liczbaDruzyn-1; j++) {
				if(punkty[j]<punkty[j+1]) {
					this.zamianaMiejsc(j);
				}
			}
		}
		
		// sortowanie po bilansie bramek w ramach tej samej ilosci punktow
		for(int i=0; i<liczbaDruzyn-1; i++) {
			for(int j=0; j<liczbaDruzyn-1; j++) {
				if(punkty[j]==punkty[j+1]) {
					if(bramkiStrzelone[j]-bramkiStracone[j]<bramkiStrzelone[j+1]-bramkiStracone[j+1]) {
						this.zamianaMiejsc(j);
					}
					else if (bramkiStrzelone[j]-bramkiStracone[j]==bramkiStrzelone[j+1]-bramkiStracone[j+1]) {
						if(bramkiStrzelone[j]<bramkiStrzelone[j+1]) {
							this.zamianaMiejsc(j);
						}
					}
				}

			}
		}

	}
	
	// kod zamiany miejsc druzyn przy sortowaniu
	private void zamianaMiejsc(int j) {
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
	
	// zwroc paczke danych do tabeli jak punkty, ilosc meczow itd.
	public int[][] paczkaDanychTabeli(){
		return new int[][] {rozegraneMeczeTabela, punkty, wygrane, remisy, porazki, bramkiStrzelone, bramkiStracone};
	}
	
	// zwroc nazwy druzyn na wlasciwych miejscach
	public String[] druzynyTabeli(){
		return druzynyTabela;
	}
}

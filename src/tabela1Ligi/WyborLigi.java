package tabela1Ligi;

public class WyborLigi {
	
	// typy enum czyli mozliwe ligi
	public enum KrajLigi {
		ANGLIA,
		FRANCJA,
		HISZPANIA,
		NIEMCY,
		POLSKA_EKSTRAKLASA,
		POLSKA_1_LIGA,
		POLSKA_2_LIGA,
		W£OCHY;
	}
	
	// zmienne klasy
	KrajLigi krajLigi; // typ enum
	private String adresWebDanych; // adres strony do pobrania danych
	private int[] karnePunkty; // tabela karnych punktow
	private String awansTyp1, awansTyp2, awansTyp3, awansTyp4, spadekTyp1, spadekTyp2; // teksty do legendy o awansach i spadkach
	private int aT1, aT2, aT3, aT4, sT1, sT2; // liczba druzyn do awansow i spadkow
	private int[] kolorAT1, kolorAT2, kolorAT3, kolorAT4, kolorST1, kolorST2; // kolory RGB tla tabeli dla druzyn z awansow i spadkow
	
	
	// konstruowanie klasy
	public WyborLigi(KrajLigi KL) {
		krajLigi=KL;
		kolorAT1=new int[] {0,120,0};
		kolorAT2=new int[] {0,200,0};
		kolorAT3=new int[] {0,120,255};
		kolorAT4=new int[] {0,180,255};
		kolorST1=new int[] {255,0,0};
		kolorST2=new int[] {255,120,0};
		switch (krajLigi) {
			case ANGLIA:
				// liczba druzyn 20;
				adresWebDanych="http://www.polskapilka.net/anglia/";
				karnePunkty=new int[20];
				for(int i=0; i<20; i++) {
					karnePunkty[i]=0;
				}
				awansTyp1="Faza grupowa Ligi Mistrzów";
				aT1=4;
				awansTyp2="Faza grupowa Ligi Europy";
				aT2=1;
				awansTyp3="";
				aT3=0;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do Championship";
				sT1=3;
				spadekTyp2="";
				sT2=0;
				break;
				
			case FRANCJA:
				// liczba druzyn 20 i jedna druzyna kara;
				adresWebDanych="http://www.polskapilka.net/francja/";
				karnePunkty=new int[20];
				// Nice -1 punkt
				for(int i=0; i<20; i++) {
					if(i==13) {
						karnePunkty[i]=-1;
					}
					else {
						karnePunkty[i]=0;
					}
				}
				awansTyp1="Faza grupowa Ligi Mistrzów";
				aT1=2;
				awansTyp2="Eliminacje Ligi Mistrzów";
				aT2=1;
				awansTyp3="Faza grupowa Ligi Europy";
				aT3=1;
				awansTyp4="Eliminacje Ligi Konferencji";
				aT4=1;
				spadekTyp1="Spadek do Ligue 2";
				sT1=2;
				spadekTyp2="Bara¿ o utrzymanie";
				sT2=1;
				break;
				
			case HISZPANIA:
				// liczba druzyn 20;
				adresWebDanych="http://www.polskapilka.net/hiszpania/";
				karnePunkty=new int[20];
				for(int i=0; i<20; i++) {
					karnePunkty[i]=0;
				}
				awansTyp1="Faza grupowa Ligi Mistrzów";
				aT1=4;
				awansTyp2="Faza grupowa Ligi Europy";
				aT2=1;
				awansTyp3="Eliminacje Ligi Konferencji";
				aT3=1;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do La Liga2";
				sT1=3;
				spadekTyp2="";
				sT2=0;
				break;
				
			case NIEMCY:
				// liczba druzyn 18;
				adresWebDanych="http://www.polskapilka.net/niemcy/";
				karnePunkty=new int[18];
				for(int i=0; i<18; i++) {
					karnePunkty[i]=0;
				}
				awansTyp1="Faza grupowa Ligi Mistrzów";
				aT1=4;
				awansTyp2="Faza grupowa Ligi Europy";
				aT2=1;
				awansTyp3="Eliminacje Ligi Konferencji";
				aT3=1;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do 2.Bundesligi";
				sT1=2;
				spadekTyp2="Bara¿ o utrzymanie";
				sT2=1;
				break;
				
			case POLSKA_EKSTRAKLASA:
				// liczba druzyn 18;
				adresWebDanych="http://www.polskapilka.net/ekstraklasa/";
				karnePunkty=new int[18];
				for(int i=0; i<18; i++) {
					karnePunkty[i]=0;
				}
				awansTyp1="Eliminacje Ligi Mistrzów";
				aT1=1;
				awansTyp2="Eliminacje Ligi Konferencji";
				aT2=2;
				awansTyp3="";
				aT3=0;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do Fortuna 1 Ligi";
				sT1=3;
				spadekTyp2="";
				sT2=0;
				break;
				
			case POLSKA_1_LIGA:
				// liczba druzyn 18;
				adresWebDanych="http://www.polskapilka.net/1-liga/";
				karnePunkty=new int[18];
				for(int i=0; i<18; i++) {
					karnePunkty[i]=0;
				}
				awansTyp1="Awans do PKO Ekstraklasy";
				aT1=2;
				awansTyp2="Bara¿e o grê w PKO Ekstraklasie";
				aT2=4;
				awansTyp3="";
				aT3=0;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do 2 Ligi";
				sT1=3;
				spadekTyp2="";
				sT2=0;
				break;
				
			case POLSKA_2_LIGA:
				// liczba druzyn 18 i jedna druzyna kara;
				adresWebDanych="http://www.polskapilka.net/2-liga/";
				karnePunkty=new int[18];
				// GKS Belchatow -4 punkty
				for(int i=0; i<18; i++) {
					if(i==1) {
						karnePunkty[i]=-4;
					}
					else {
						karnePunkty[i]=0;
					}
				}
				awansTyp1="Awans do Fortuna 1 Liga";
				aT1=2;
				awansTyp2="Bara¿e o grê w Fortuna 1 Lidze";
				aT2=4;
				awansTyp3="";
				aT3=0;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do 3 Ligi";
				sT1=4;
				spadekTyp2="";
				sT2=0;
				break;
				
			case W£OCHY:
				// liczba druzyn 20;
				adresWebDanych="http://www.polskapilka.net/wlochy/";
				karnePunkty=new int[20];
				for(int i=0; i<20; i++) {
					karnePunkty[i]=0;
				}
				awansTyp1="Faza grupowa Ligi Mistrzów";
				aT1=4;
				awansTyp2="Faza grupowa Ligi Europy";
				aT2=1;
				awansTyp3="Eliminacje Ligi Konferencji";
				aT3=1;
				awansTyp4="";
				aT4=0;
				spadekTyp1="Spadek do Serie B";
				sT1=3;
				spadekTyp2="";
				sT2=0;
				break;
				
			default:
				break;
		}
	}
	
	// zwroc adres html
	public String dajAdresDoPobraniaDanych() {
		return adresWebDanych;
	}
	
	// daj tablice karnych punktow
	public int[] pobierzKarnePunkty() {
		return karnePunkty;
	}
	
	// zwroc informacje o awansach i spadkach
	public String[] tekstyDoKolorowania() {
		return new String[] {awansTyp1, awansTyp2, awansTyp3, awansTyp4, spadekTyp1, spadekTyp2};
	}
	// zwroc liczbe wierszy do zmian koloru
	public int[] liczbaDoKolorowania() {
		return new int[] {aT1, aT2, aT3, aT4, sT1, sT2};
	}
	// zwroc tablice kolorow zmian wierszy
	public int[][] tablicaKolorow() {
		return new int[][] {kolorAT1, kolorAT2, kolorAT3, kolorAT4, kolorST1, kolorST2};
	}
}

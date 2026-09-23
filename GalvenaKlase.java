package pakotne;

import java.text.DecimalFormat;
import java.util.Scanner;
public class GalvenaKlase {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		char izv;
		do {
			System.out.println("\nPieejamās darbības:\n" 
					+ "1 - Ievadīt studenta daudzumu\n" 
					+ "2 - Ievadīt kritērija daudzumu\n"
					+ "3 - Ievadīt studenta vārdus\n" 
					+ "4 - Ievadīt kritērija nosaukumus\n"
					+ "5 - Ievadīt kritērijiem svaru daudzumu\n"
					+ "6 - Ievadīt vērtējumus katram studentam\n"
					+ "7 - Izvadīt katra studenta vērtejumu\n"
					+ "8 - Labot kritērija nosaukumu\n"
					+ "9 - Labot kritērija svaru\n"
					+ "x - Apturēt programmu");
					
			System.out.print("Tava izvēle: ");
			izv = scan.next().charAt(0);
			izv = Character.toLowerCase(izv);
			
			switch(izv) {
			case '1': Metodes.studenti(); break;
			case '2': Metodes.kriteriji(); break;
			case '3': Metodes.studIevadisana(); break;
			case '4': Metodes.kritIevadisana(); break;
			case '5': Metodes.svaraIevadisana(); break;
			case '6': Metodes.vertIevadisana(); break;
			case '7': Metodes.izvadisana(); break;
			case '8': Metodes.labotKrit(); break;
			case '9': Metodes.labotSvaru(); break;
			case 'x': System.out.println("Programma apturēta!"); break;
			default: System.out.println("Darbība nepastāv!");
				
			}
		} while (izv != 'x');
		
		scan.close();
	}
}

package pakotne;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Metodes {
	static DecimalFormat df = new DecimalFormat("0.#");
	static Scanner scan = new Scanner(System.in);
	static int kritSk=0, studSk = 0, maxSvars = 100, sk = 1;
	static double atlSvars;
	static String[] studenti = null;
	static String[] kriteriji = null;
	static int[] kriterijaSvars = null;
	static int[][] kriterijaVertejums = null;
	static double[] semestraVertejums = null;
	
	// Audzēkņu skaita ievade
	static void studenti() {
	do {
		System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
	while(!scan.hasNextInt()) {
		System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
		scan.next();
		}
		studSk = scan.nextInt();
	}while(studSk<1);
		studenti = new String [studSk];
	}
	// Vērtēšanas kritēriju skaita ievade
	static void kriteriji() {
	do {
		System.out.println("Kāds būs kritēriju skaits?");
	while(!scan.hasNextInt()) {
		System.out.println("Kāds būs kritēriju skaits?");
		scan.next();
		}
		kritSk = scan.nextInt();
	}while(kritSk<1);
		kriteriji = new String[kritSk];
		kriterijaSvars = new int[kritSk];
		kriterijaVertejums = new int[studSk][kritSk];
		semestraVertejums = new double[studSk];
				
		scan.nextLine();
	}
	// Ievada audzēkņu vārdus, uzvārdus
	static void studIevadisana() {
		if(studenti == null)
			System.out.println("Nav ievadīts studentu daudzums!");
		else {
		for(int i=0; i<studenti.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". studentu");
				studenti[i] = scan.nextLine().trim();
			} while(!studenti[i].matches("^[\\p{L} ]+$"));
		}
	}
	}
	// Definē kritērijus
	static void kritIevadisana() {
		if(kriteriji == null)
			System.out.println("Nav ievadīts kritēriju daudzums!");
		else {
		for(int i=0; i<kriteriji.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". kritēriju");
				kriteriji[i] = scan.nextLine().trim();
			} while(!kriteriji[i].matches("^[\\p{L} ]+$"));
			
	}
	}
	}
	// Norāda katra kritērija svaru
	static void svaraIevadisana() {
		if(kriteriji == null) 
			System.out.println("Sākumā ievadi kritēriju skaitu!");
		else {
		for(int i=0; i<kriteriji.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". kritērija svaru (max: "+maxSvars+")");
				while(!scan.hasNextInt()) {
					System.out.println("Ievadi "+(i+1)+". kritērija svaru");
					scan.next();
				}
				kriterijaSvars[i] = scan.nextInt();
				/* Minimālā KATRA ATLIKUŠĀ kritērija svars ir 5
				 * kopējai svaru vērtībai ir jābūt 100 (ne mazāk, ne vairāk)
				*/
				atlSvars = (maxSvars - kriterijaSvars[i]) / (double)(kriteriji.length - sk);
			} while(kriterijaSvars[i]>maxSvars || kriterijaSvars[i]<5 || 
				  (i != kriteriji.length-1 && kriterijaSvars[i] == maxSvars) ||
				  (i == kriteriji.length-1 && (maxSvars - kriterijaSvars[i])  > 0) 
				  || atlSvars < 5);
			maxSvars -= kriterijaSvars[i];
			sk++;
			scan.nextLine();
		}
		}
	}
	// Norāda vērtējumu kādu ieguvis katrs audzēknis par katru kritēriju
	static void vertIevadisana() {
		if(kriterijaVertejums == null || kriteriji[0] == null) 
			System.out.println("Nav ievadīti nepieciešamie dati!");
		else {
		for(int i=0; i<kriterijaVertejums.length; i++) {
			for(int j=0; j<kriterijaVertejums[i].length; j++) {
				do {
					System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
					while(!scan.hasNextInt()) {
						System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
						scan.next();
					}
					kriterijaVertejums[i][j] = scan.nextInt();
				}while(kriterijaVertejums[i][j]<0 || kriterijaVertejums[i][j]>10);
			}
		}
		}
	}
	static void labotKrit() {
		if(kriteriji == null || kriteriji[0] == null)
			System.out.println("Nav ievadīti nepieciešamie dati!");
		else {
		int nr;
		do {
			System.out.println("Kuru kritērija nosaukumu pēc nr. labot?");
		while(!scan.hasNextInt()) {
			System.out.println("Kuru kritērija nosaukumu pēc nr. labot?");
			scan.next();
			}
			nr = scan.nextInt();
		}while(nr<1);
		scan.nextLine();
		do {
		System.out.println("Ievadi " + nr + ". kritērija jauno nosaukumu:");
		kriteriji[nr-1] = scan.nextLine().trim();
		} while(!kriteriji[nr-1].matches("^[\\p{L} ]+$"));
	}
	}
	static void labotSvaru() {
		svaraIevadisana();
	}
	// Gala vērtējumu izvadīšana
	static void izvadisana() {
		if(studenti == null || studenti[0] == null || kriteriji[0] == null || kriterijaSvars == null || kriterijaVertejums == null || kriterijaVertejums[0] == null) { 
			System.out.println("Nav ko izvadīt!");
		}else {
				double rezultats;
				for(int i=0; i<studenti.length; i++) {
					rezultats=0;
					for(int j=0; j<kriteriji.length; j++) {
						rezultats += ((double) kriterijaSvars[j]/100)*kriterijaVertejums[i][j];
					}
					semestraVertejums[i] = rezultats;
				}
				
				for(int i=0; i<studenti.length; i++) {	
					for(int j=0; j<kriteriji.length; j++) {
						System.out.println("Studenta "+studenti[i]+" vērtējums par kritēriju "+kriteriji[j]+" ir "+kriterijaVertejums[i][j]+", kura svars ir "+kriterijaSvars[j]);
					}
					System.out.println("Semestra vērtējums ir "+df.format(semestraVertejums[i])+" balles"
							+ "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				}
		}
	}
	public static void main(String[] args) {

	}

}

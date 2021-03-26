package fr.eni.saute_mouton;

import java.util.Scanner;

public class SauteMouton {
	

	public static void main(String[] args) {
		 int encore;
		int record = 1000;
		
		 do {
			int cpt = 0;
			int index;
			char[] jeu = new char[7];
			Scanner scanner = new Scanner(System.in);
			
			initialiserJeu(jeu);
			
			do {
				afficherJeu(jeu);
				
				do {
					System.out.println("Quel mouton voulez-vous déplacer ?");
					index = scanner.nextInt() - 1;
					if ((index < 0) || (index > 6)) {
						System.out.println("Entrez une position valide (entre 1 et 7).");
					}
					else if (!peutBouger(jeu, index)){
						System.out.println("Ce mouton ne peut pas être déplacé");
					}
				} while (!positionValide(jeu, index));
				
				cpt++;
				
				majJeu(jeu, index);
				
			} while (laPartieContinue(jeu));
			
			record = finDePartie(jeu, cpt, record);
			cpt = 0;
			
			do {
				System.out.println("Voulez-vous rejouer ? (1-OUI/2-NON)");
				encore = scanner.nextInt();
				if (!((encore == 1) || (encore == 2))) {
					System.out.println("Veuillez entrer '1' ou '2'");
				}
			} while (!((encore == 1) || (encore == 2)));
			System.out.println("_______________________________________");
		} while (encore == 1);
	}
	
		
		
	
	public static void initialiserJeu(char[] jeu) {
		
		for (int i=0; i<3; i++) {	
			jeu[i] = '>';
		}
		
		jeu[3] = ' ';
		
		for (int i=4; i<jeu.length; i++) {
			jeu[i] = '<';
		}
	}	
		
		
		
	public static void afficherJeu(char[] jeu) {
		System.out.println(" 1 2 3 4 5 6 7");
		for (int i=0; i<jeu.length; i++) {
			System.out.print("|"+jeu[i]);
		}
		System.out.println("|");
	}
	
	
	public static boolean peutBouger(char[] jeu, int index) {
		boolean result = false;
	
		if (jeu[index] == '>') {
			if (index == jeu.length-2) {
				if ((jeu[index+1]) == (' ')) {
					result = true;
				}
			}
			else  if(index != jeu.length-1){
				if (((jeu[index+1]) == (' ')) || ((jeu[index+2]) == (' '))) {
					result = true;
				}
			}
		}
		
		else if (jeu[index] == '<') {
			if (index == 1) {
				if ((jeu[index-1]) == (' ')) {
					result = true;
				}
			}
			else if(index != 0){
				if (((jeu[index-1]) == (' ')) || ((jeu[index-2]) == (' '))) {
				result = true;
				}
			}
		}
		
		return result;
	}
	
	
	public static boolean laPartieContinue(char[] jeu) {
//		partie terminée si plus aucun mouton ne peut être déplacé
		
		boolean result = false;
		
		for (int i = 0; i<jeu.length; i++) {
			if (peutBouger(jeu, i)) {
				result = true;
				i = jeu.length;
			}
		}
		
		return result;
	}
	
	
	public static void majJeu(char[] jeu, int index) {
		if (jeu[index] == '>') {
			jeu[index] = ' ';
			if (jeu[index+1] == ' ') {
				jeu[index+1] = '>';
			}
			else {
				jeu[index+2] = '>';
			}
		}
		
		if (jeu[index] == '<') {
			jeu[index] = ' ';
			if (jeu[index-1] == ' ') {
				jeu[index-1] = '<';
			}
			else {
				jeu[index-2] = '<';
			}
		}
		
	}
	
	
	public static boolean positionValide(char[] jeu, int index) {
		boolean result = true;
		if ((index < 0) || (index > 6)) {
			result = false;
		}
		else if (!peutBouger(jeu, index)){
			result = false;
		}
		return result;
	}
	

	
	public static int finDePartie(char[] jeu, int cpt, int record) {
		int result = record;
		boolean gagnee = true;
		
		for (int i = 0; i < 3; i++) {
			if (jeu[i] != '<') {
				gagnee = false;
			}
		}
		for (int i = 4; i < 7; i++) {
			if (jeu[i] != '>') {
				gagnee = false;
			}
		}
		
		if (jeu[3] != ' ') {
			gagnee = false;
		}
		
		if (gagnee && (cpt < record)) {
			result = cpt;
		}
		
		afficherJeu(jeu);
		if (gagnee) {
			System.out.println("Bravo ! Vous avez gagné la partie en " + cpt + " coups ! (record : " + result + ")");
		}
		
		else {
			System.out.println("Dommage ! Vous ete bloqué. Essayez encore !");
		}
		
		return result;
		
	}
		
		
		
}

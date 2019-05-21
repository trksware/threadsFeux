
public final class Actions_4D {
	
	private static Action traverse = new Action("Pietons Traverse (+)", "RRRR");
	
	private static Action estContinue = new Action("Voiture Est Continue (+)", "V?RR");
	private static Action estTourneDroite = new Action("Voiture Est Tourne Droite (+)", "V?RR");
	private static Action estTourneGauche = new Action("Voiture Est Tourne Gauche (+)", "VRRR");
	
	private static Action ouestContinue = new Action("Voiture Ouest Continue (+)", "?VRR");
	private static Action ouestTourneDroite = new Action("Voiture Ouest Tourne Droite (+)", "?VRR");
	private static Action ouestTourneGauche = new Action("Voiture Ouest Tourne Gauche (+)", "RVRR");
	
	private static Action sudContinue = new Action("Voiture Sud Continue (+)", "RRV?");
	private static Action sudTourneDroite = new Action("Voiture Sud Tourne Droite (+)", "RRV?");
	private static Action sudTourneGauche = new Action("Voiture Sud Tourne Gauche (+)", "RRVR");
	
	private static Action nordContinue = new Action("Voiture Nord Continue (+)", "RR?V");
	private static Action nordTourneDroite = new Action("Voiture Nord Tourne Droite (+)", "RR?V");
	private static Action nordTourneGauche = new Action("Voiture Nord Tourne Gauche (+)", "RRRV");
	
	private static int actionCount[] = {0,0,0,0,0,0,0,0,0,0,0,0};
	
	public static void setACtionCountByIndex(Action action, int nb) {
		int index = 0;
		if (action == estContinue) { index = 0; }
		else if (action == estTourneDroite) { index = 1; }
		else if (action == estTourneGauche) { index = 2; }
		
		else if (action == ouestContinue) { index = 3; }
		else if (action == ouestTourneDroite) { index = 4; }
		else if (action == ouestTourneGauche) { index = 5; }
		
		else if (action == sudContinue) { index = 6; }
		else if (action == sudTourneDroite) { index = 7; }
		else if (action == sudTourneGauche) { index = 8; }
		
		else if (action == nordContinue) { index = 9; }
		else if (action == nordTourneDroite) { index = 10; }
		else if (action == nordTourneGauche) { index = 11; }
		
		actionCount[index] = nb;
	}
	
	public static int getACtionCountByIndex(Action action) {
		int index = 0;
		if (action == estContinue) { index = 0; }
		else if (action == estTourneDroite) { index = 1; }
		else if (action == estTourneGauche) { index = 2; }
		
		else if (action == ouestContinue) { index = 3; }
		else if (action == ouestTourneDroite) { index = 4; }
		else if (action == ouestTourneGauche) { index = 5; }
		
		else if (action == sudContinue) { index = 6; }
		else if (action == sudTourneDroite) { index = 7; }
		else if (action == sudTourneGauche) { index = 8; }
		
		else if (action == nordContinue) { index = 9; }
		else if (action == nordTourneDroite) { index = 10; }
		else if (action == nordTourneGauche) { index = 11; }
		
		return actionCount[index];
	}

	public static Action getTraverse() {
		return traverse;
	}

	public static Action getEContinue() {
		return estContinue;
	}

	public static Action getETourneDroite() {
		return estTourneDroite;
	}

	public static Action getETourneGauche() {
		return estTourneGauche;
	}

	public static Action getOContinue() {
		return ouestContinue;
	}

	public static Action getOTourneDroite() {
		return ouestTourneDroite;
	}

	public static Action getOTourneGauche() {
		return ouestTourneGauche;
	}

	public static Action getSContinue() {
		return sudContinue;
	}

	public static Action getSTourneDroite() {
		return sudTourneDroite;
	}

	public static Action getSTourneGauche() {
		return sudTourneGauche;
	}

	public static Action getNContinue() {
		return nordContinue;
	}

	public static Action getNTourneDroite() {
		return nordTourneDroite;
	}

	public static Action getNTourneGauche() {
		return nordTourneGauche;
	}
}

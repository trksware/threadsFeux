
public final class Actions_3D {
	
	private static Action traverse = new Action("Pietons Traverse (T)", "RRR");
	
	private static Action estContinue = new Action("Voiture Est Continue (T)", "V?R");
	private static Action estTourneGauche = new Action("Voiture Est Tourne Gauche (T)", "VRR");
	
	private static Action ouestContinue = new Action("Voiture Ouest Continue (T)", "?VR");
	private static Action ouestTourneDroite = new Action("Voiture Ouest Tourne Droite (T)", "?VR");
	
	private static Action sudTourneDroite = new Action("Voiture Sud Tourne Droite (T)", "RRV");
	private static Action sudTourneGauche = new Action("Voiture Sud Tourne Gauche (T)", "RRV");
	
	private static int actionCount[] = {0,0,0,0,0,0};
	
	public static void setACtionCountByIndex(Action action, int nb) {
		int index = 0;
		if (action == estContinue) { index = 0; }
		else if (action == estTourneGauche) { index = 1; }
		
		else if (action == ouestContinue) { index = 2; }
		else if (action == ouestTourneDroite) { index = 3; }
		
		else if (action == sudTourneDroite) { index = 4; }
		else if (action == sudTourneGauche) { index = 5; }
		
		actionCount[index] = nb;
	}
	
	public static int getACtionCountByIndex(Action action) {
		int index = 0;
		if (action == estContinue) { index = 0; }
		else if (action == estTourneGauche) { index = 1; }
		
		else if (action == ouestContinue) { index = 2; }
		else if (action == ouestTourneDroite) { index = 3; }
		
		else if (action == sudTourneDroite) { index = 4; }
		else if (action == sudTourneGauche) { index = 5; }
		
		return actionCount[index];
	}
	
	public static Action getTraverse() {
		return traverse;
	}
	public static Action getEContinue() {
		return estContinue;
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
	public static Action getSTourneDroite() {
		return sudTourneDroite;
	}
	public static Action getSTourneGauche() {
		return sudTourneGauche;
	}
}

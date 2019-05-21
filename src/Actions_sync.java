public final class Actions_sync {

    private static Action ouestContinue4D = new Action("Voiture ouestContinue4D", "RRRR");
    private static Action sudTourneDroite4D = new Action("Voiture sudTourneDroite4D", "RRRR");
    private static Action nordTourneGauche4D = new Action("Voiture nordTourneGauche4D", "RRRR");
    
    private static Action estContinue3D = new Action("Voiture estContinue3D", "RRRR");
    private static Action sudTourneGauche3D = new Action("Voiture sudTourneGauche3D", "RRRR");

	public static Action getOuestContinue4D() {
		return ouestContinue4D;
	}

	public static Action getSudTourneDroite4D() {
		return sudTourneDroite4D;
	}

	public static Action getNordTourneGauche4D() {
		return nordTourneGauche4D;
	}

	public static Action getEstContinue3D() {
		return estContinue3D;
	}

	public static Action getSudTourneGauche3D() {
		return sudTourneGauche3D;
	}
}

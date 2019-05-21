
public final class Intersection_Sync{

	
	Intersection_Sync() { }

	public static void ouestContinue_4D() {
		new Voiture(Actions_3D.getOContinue()).start();
		int l = Actions_3D.getACtionCountByIndex(Actions_3D.getOContinue())+1;
		Actions_3D.setACtionCountByIndex(Actions_3D.getOContinue(), l);
		
	}

	public static void sudTourneDroite_4D() {
		new Voiture(Actions_3D.getOContinue()).start();
		int l = Actions_3D.getACtionCountByIndex(Actions_3D.getOContinue())+1;
		Actions_3D.setACtionCountByIndex(Actions_3D.getOContinue(), l);
	}

	public static void nordTourneGauche_4D() {
		new Voiture(Actions_3D.getOContinue()).start();
		int l = Actions_3D.getACtionCountByIndex(Actions_3D.getOContinue())+1;
		Actions_3D.setACtionCountByIndex(Actions_3D.getOContinue(), l);
	}

	public static void estContinue_3D() {
		new Voiture(Actions_4D.getEContinue()).start();
		int l = Actions_4D.getACtionCountByIndex(Actions_4D.getEContinue())+1;
		Actions_4D.setACtionCountByIndex(Actions_4D.getEContinue(), l);
	}

	public static void sudTourneGauche_3D() {
		new Voiture(Actions_4D.getEContinue()).start();
		int l = Actions_4D.getACtionCountByIndex(Actions_4D.getEContinue())+1;
		Actions_4D.setACtionCountByIndex(Actions_4D.getEContinue(), l);		
	}


}

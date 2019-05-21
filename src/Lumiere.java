
public class Lumiere {

	private String nom;
	private String couleur = "R";
	
	public Lumiere(String nom) {
		this.nom = nom;
	}

	public void setFeuV(){
		if (this.couleur == "R") {
			this.couleur = "V";
			System.out.println();
			System.out.println("Lumiere " + this.nom + " Vert");
		}
	}
	
	public void setFeuR(){
		if (this.couleur == "V") {
			this.couleur = "R";
			System.out.println();
			System.out.println("Lumiere " + this.nom + " Rouge");
		}
	}

	public String getCouleur() {
		return couleur;
	}
	
}

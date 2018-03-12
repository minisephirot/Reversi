package reversi.etats;

public enum Jeton {
	
	// param de l'enum
	J1("N"),
	J2("B"),
	Possible("P");
	
	private String couleur;
	
	
	private Jeton(String s){
		this.couleur = s;
	}
	
	private Jeton (Jeton j){
		this.couleur = j.couleur;
	}
	
	public String toString(){
		return couleur;
	}
	
	public void retourner(){
		if (this.couleur.equals("B"))
			this.couleur = "N";
		else
			this.couleur = "B";
	}
	
	public boolean idem(Jeton j){
		return this.couleur.equals(j.toString());
	}

}

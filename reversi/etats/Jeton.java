package reversi.etats;

public enum Jeton {
	
	// param de l'enum
	J1("N"),
	J2("B");
	
	private String couleur;
	
	
	private Jeton(String s){
		this.couleur = s;
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

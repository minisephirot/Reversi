package reversi.etats;

public enum Jeton {
	
	// param de l'enum
	J1("noir"),
	J2("blanc");
	
	private String couleur;
	
	
	private Jeton(String s){
		this.couleur = s;
	}
	
	public String toString(){
		return couleur;
	}
	
	public void retourner(){
		if (this.couleur.equals("blanc"))
			this.couleur = "noir";
		else
			this.couleur = "blanc";
	}
	
	public boolean idem(Jeton j){
		return this.couleur.equals(j.toString());
	}

}

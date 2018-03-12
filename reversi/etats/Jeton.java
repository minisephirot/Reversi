package reversi.etats;

public  class Jeton {

	private String couleur;

	public Jeton(String s){
		this.couleur = s;
	}

	public Jeton (Jeton j){
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

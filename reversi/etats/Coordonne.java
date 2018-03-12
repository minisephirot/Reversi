package reversi.etats;

public class Coordonne {
	
	private int x;
	private int y;
	
	public Coordonne(int a,int b){
		this.x = a;
		this.y = b;
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Coordonne)) return false;
		Coordonne c = (Coordonne) o;
		if (c.x == this.x && c.y == this.y) return true;
		else return false;
	}

}

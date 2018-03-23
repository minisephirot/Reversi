package reversi.etats;

public class Coordonne {
	
	private int x;
	private int y;
	
	public Coordonne(int a,int b){
		this.setX(a);
		this.setY(b);
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Coordonne)) return false;
		Coordonne c = (Coordonne) o;
		if (c.getX() == this.getX() && c.getY() == this.getY()) return true;
		else return false;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}

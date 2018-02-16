package reversi.etats;

public abstract class Etat {
	
	abstract public void read();
	
	abstract public void write();
	
	@Override
	abstract public String toString();
	
	@Override
	abstract public boolean equals(Object o);

}

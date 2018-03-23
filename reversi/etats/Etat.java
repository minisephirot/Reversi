package reversi.etats;

import java.util.Observable;

public abstract class Etat extends Observable{
	
	
	@Override
	abstract public String toString();
	
	@Override
	abstract public boolean equals(Object o);

}

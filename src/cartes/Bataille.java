package cartes;

import jeu.Joueur;
import java.util.ArrayList;
import java.util.List;

public abstract class Bataille extends Probleme {

	protected Bataille(Type type, int nbr) {
		super(type,nbr);
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		List<Type> btypes = new ArrayList<>();
		for(Botte botte:j.getBotteList()) {
			btypes.add(botte.getType());
		}		
		if (!btypes.contains(this.getType())) {
			if (!j.getBataillePile().isEmpty()) {
				Carte top = j.getBataillePile().peek();
				if ((this instanceof Attaque && top instanceof Attaque) || (this instanceof Parade && top instanceof Parade))
					return false;
				j.getBataillePile().add(this);
				return true;
			}else {
				if (this.getType().equals(Type.FEU) && this instanceof Parade) {
					j.getBataillePile().add(this);
					return true;
				}
				return false;
			}	
		}
		else
			return false;
	}
}

package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import cartes.*;

public class Utils {
	
	
	//La version sans le listeIterator
	public static <T>T extraire(List<T> list) {
		if (list.isEmpty()) {
            throw new IllegalArgumentException("La liste est vide.");
        }
		Random random = new Random();
		int rand = random.nextInt(list.size());
		return list.remove(rand);
	}
	
	
	//La version avec le listeIterator
	public static <T>T extraireIterator(List<T> list) {
		Random random = new Random();
		Carte carte = null;
		int rand = random.nextInt(list.size());
		ListIterator<T> iterator = list.listIterator();
		while(iterator.hasNext() && carte != list.get(rand)) {
			iterator.next();
		}
		return iterator.next();
	}
	
	
	//Le mélange
	public static <T> List<T> melanger(List<T> list){
		ArrayList<T> resultat = new ArrayList<>(list);
		Collections.shuffle(resultat);
		return resultat;
	}
	
	
	
	public static <T> boolean verifierMelange(List<T> list1, List<T> list2) {
		return list1.size() == list2.size() &&
	               list1.containsAll(list2) &&
	               list2.containsAll(list1);
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
        List<T> resultat = new ArrayList<>();
        if (liste.isEmpty()) {
            return resultat;
        }

        T precedent = liste.get(0);
        resultat.add(precedent);

        for (int i = 1; i < liste.size(); i++) {
            T actuel = liste.get(i);
            if (!actuel.equals(precedent)) {
            	resultat.add(actuel);
                precedent = actuel;
            }
        }

        return resultat;
    }
	
	public static <T> boolean verifierRassemblement(List<T> list1) {
		int indexFin = list1.size()-1;
		int index = 0;
		ListIterator<?> listIteratorDebut = list1.listIterator();
		ListIterator<?> listIteratorFin = list1.listIterator(indexFin);
		while(listIteratorDebut.hasNext() && listIteratorDebut.nextIndex()<indexFin) {
			while(listIteratorDebut.next().equals(listIteratorDebut)) {
				listIteratorDebut.next();
			}
			index = listIteratorDebut.nextIndex()-1;
			for (int i = indexFin; i > index; i--) {
				if(listIteratorFin.equals(listIteratorDebut)) {
					return false;
				}
			}
			listIteratorDebut.next();
		}
		return true;		
	}


	public static <T> boolean verifierRassemblement(List<T> listeRassemblee, List<T> listemelangee) {
        if (listeRassemblee.size() > listemelangee.size()) {
            return false;
        }

        int i = 0;
        for (T elementRassemblee : listeRassemblee) {
            while (i < listemelangee.size()) {
                if (listemelangee.get(i).equals(elementRassemblee)) {
                    i++;
                    break;
                }
                i++;
            }

            if (i >= listemelangee.size() && !elementRassemblee.equals(listemelangee.get(listemelangee.size() - 1))) {
                return false;
            }
        }

        return true;
    }
}

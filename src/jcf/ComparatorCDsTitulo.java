package jcf;

import java.util.Comparator;

import objetos.CD;

public class ComparatorCDsTitulo implements Comparator<CD> {

    @Override
    public int compare(CD cd1, CD cd2) {
        int diff = cd1.getTitulo().compareTo(cd2.getTitulo());
        if (diff != 0) {
            return diff;
        }
        //Mesmo titulo (compara o preco cresc)
        return cd1.compareTo(cd2);
    }

}

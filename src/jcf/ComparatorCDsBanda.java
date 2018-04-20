package jcf;

import java.util.Comparator;

import objetos.CD;

public class ComparatorCDsBanda implements Comparator<CD> {

    @Override
    public int compare(CD cd1, CD cd2) {
        int diff = cd1.getBanda().compareTo(cd2.getBanda());
        if (diff != 0) {
            return diff;
        }
        //Mesmo titulo (compara o preco decresc)
        if (cd1.getPreco() == cd2.getPreco()) {
            return 0;
        } else {
            if (cd1.getPreco() < cd2.getPreco()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

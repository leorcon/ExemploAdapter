import javax.swing.UIManager;

import visual.BuscadorDeCDs;

public class Inicializador {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            BuscadorDeCDs bCD = new BuscadorDeCDs();
            bCD.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

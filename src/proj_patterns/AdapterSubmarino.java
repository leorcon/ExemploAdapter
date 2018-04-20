package proj_patterns;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.omg.PortableServer.POAPackage.ServantNotActive;

import didatico.SubmarinoProducts;
import objetos.CD;

public class AdapterSubmarino implements Loja {

    private SubmarinoProducts servidorSubmarino;

    @Override
    public boolean conectar(String usuario, String senha) {
        if (servidorSubmarino == null) {
            servidorSubmarino = SubmarinoProducts.getInstance();
        }

        if (!servidorSubmarino.isConnected()) {
            try {
                servidorSubmarino.connect(usuario, senha);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public void desconectar() {
        if (servidorSubmarino.isConnected()) {
            servidorSubmarino.disconnect();
        }
    }

    @Override
    public Collection<CD> procurar(String chave) {
        Set<CD> listaDeCds = new HashSet<CD>();
        if (servidorSubmarino.isConnected()) {
            String[][] matrizCds;
            try {
                matrizCds = servidorSubmarino.getCDProducts();
                for (String[] cds : matrizCds) {
                    //if (cds[2].toLowerCase().contains(chave.toLowerCase()) || cds[0].toLowerCase().contains(chave.toLowerCase())) {
                    if (cds[2].equals(chave) || cds[0].equals(chave)) {
                        listaDeCds.add(new CD(cds[2], cds[0], Double.parseDouble(cds[3]), "Submarino"));
                    }
                }
            } catch (ServantNotActive e) {
                e.printStackTrace();
            }
        } else {
            // Erro de conexao nao aberta
            JOptionPane.showMessageDialog(null, "A conexão não está ativa e a busca não pode ser realizada.", "Não há conexão ativa!", JOptionPane.ERROR_MESSAGE);
        }
        return listaDeCds;
    }

}

package proj_patterns;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import conexao.SomLivreServidor;
import objetos.CD;

public class AdapterSomLivre implements Loja {

    private SomLivreServidor servidorSomLivre;

    @Override
    public boolean conectar(String usuario, String senha) {
        if (servidorSomLivre == null) {
            servidorSomLivre = new SomLivreServidor();
        }

        return servidorSomLivre.registrar(usuario); //Retorna false apenas se nao for um nome valido
    }

    @Override
    public void desconectar() {
        //Desconexao para a loja da Somlivre de acordo com o JavaDoc:
        //true - caso o nome seja valido. A partir deste momento as outras operacoees podem ser realizadas. 
        //false - caso o nome nao seja valido. Perde o registro valido anterior - usado para desconexao.
        servidorSomLivre.registrar("");
    }

    @Override
    public Collection<CD> procurar(String chave) {
        Set<CD> listaDeCds = new HashSet<CD>();

        for (String strCd : servidorSomLivre.buscaCD()) {
            String[] campos = strCd.split("[|]");
            //if (campos[0].toLowerCase().contains(chave.toLowerCase()) || campos[1].toLowerCase().contains(chave.toLowerCase())) {
            if (campos[0].equals(chave) || campos[1].equals(chave)) {
                listaDeCds.add(new CD(campos[0], campos[1], Double.parseDouble(campos[2]), "SomLivre"));
            }
        }

        return listaDeCds;
    }
}

package proj_patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import objetos.CD;

public class BuscadorAdapter {

    private AdapterSubmarino subAdapt = new AdapterSubmarino();
    private AdapterSomLivre somAdapt = new AdapterSomLivre();

    public List<CD> buscar(String chave) {
        Set<CD> setDeCds = new HashSet<>();
        //Busca todos do sub
        setDeCds.addAll(subAdapt.procurar(chave));

        //Busca todos do som
        setDeCds.addAll(somAdapt.procurar(chave));

        //Retorna results das duas lojas
        return new ArrayList<CD>(setDeCds);
    }

    public void abreConexoes() {
        subAdapt.conectar("furb", "furb");
        somAdapt.conectar("furb", "");
    }

    public void fechaConexoes() {
        subAdapt.desconectar();
        somAdapt.desconectar();
    }

}

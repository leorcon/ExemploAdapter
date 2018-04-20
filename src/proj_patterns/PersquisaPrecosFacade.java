package proj_patterns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import objetos.Busca;
import objetos.CD;
import persistencia.PersistenciaBuscas;

public class PersquisaPrecosFacade {

    private static final String arquivo = "ArquivoDeBuscas.txt";

    private PersistenciaBuscas persistenciaBuscas = new PersistenciaBuscas();
    private BuscadorAdapter buscaAdapter;

    private ArrayList<Busca> buscas;
    private List<CD> cdsBusca;

    public void pesquisar(String chave) {
        if (buscaAdapter == null) {
            buscaAdapter = new BuscadorAdapter();
        }

        //Reseta o array de CDs antes de fazer a busca
        cdsBusca = new ArrayList<CD>();

        //Faz a busca
        cdsBusca = buscaAdapter.buscar(chave);
    }

    public void salvar() {
        persistenciaBuscas.salvarBuscas(buscas, Paths.get(arquivo));
    }

    public void ler() {
        buscas = new ArrayList<Busca>();
        Path arq = Paths.get(arquivo);

        if (!Files.exists(arq)) {
            try {
                Files.createFile(arq);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            buscas = persistenciaBuscas.carregarBuscas(arq);
        }
    }

    public ArrayList<Busca> getBuscas() {
        return buscas;
    }

    public void setBuscas(ArrayList<Busca> buscas) {
        this.buscas = buscas;
    }

    public List<CD> getCdsBusca() {
        return cdsBusca;
    }

    public String getArquivo() {
        return PersquisaPrecosFacade.arquivo;
    }

    public void abreConexoes() {
        if (buscaAdapter == null) {
            buscaAdapter = new BuscadorAdapter();
        }
        buscaAdapter.abreConexoes();
    }

    public void fechaConexoes() {
        buscaAdapter.fechaConexoes();
    }
}

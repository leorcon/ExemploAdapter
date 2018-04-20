package objetos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import jcf.ComparatorCDsBanda;
import jcf.ComparatorCDsTitulo;
import jcf.Ordenacao;

public class Busca implements Serializable {

    private String chaveDeBusca;
    private List<CD> cdsDaBusca = new ArrayList<CD>();
    private Date dtSalva;
    private Ordenacao ordem;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Comparator<CD> compCDsTitulo = new ComparatorCDsTitulo();
    private static Comparator<CD> compCDsBanda = new ComparatorCDsBanda();

    public String getChaveDeBusca() {
        return chaveDeBusca;
    }

    public void setChaveDeBusca(String chaveDeBusca) {
        this.chaveDeBusca = chaveDeBusca;
    }

    public List<CD> getCdsDaBusca() {
        return cdsDaBusca;
    }

    public void setCdsDaBusca(List<CD> cdsDaBusca) {
        this.cdsDaBusca = cdsDaBusca;
    }

    public Date getDtSalva() {
        return dtSalva;
    }

    public String getFormatedDtSalva() {
        return sdf.format(this.getDtSalva());
    }

    public void setDtSalvo(Date dtSalva) {
        this.dtSalva = dtSalva;
    }

    public Ordenacao getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordenacao ordem) {
        this.ordem = ordem;
    }

    public void addCD(CD cd) {
        this.cdsDaBusca.add(cd);
    }

    public void ordenaCDsPorPrecoCrescente() {
        Collections.sort(cdsDaBusca);
    }

    public void ordenaCDsPorPrecoDecrescente() {
        Collections.sort(cdsDaBusca);
        Collections.reverse(cdsDaBusca);
    }

    private void ordenaCDsPorTituloEPrecoASC() {
        Collections.sort(cdsDaBusca, compCDsTitulo);
    }

    private void ordenaCDsPorBandaEPrecoASC() {
        Collections.sort(cdsDaBusca, compCDsBanda);
    }

    public int getQuantidadeDeCDsEncontrados() {
        return this.cdsDaBusca.size();
    }

    @Override
    public String toString() {
        String result = (this.getQuantidadeDeCDsEncontrados() == 1 ? "resultado" : "resultados");
        String s = "";
        s += this.getChaveDeBusca() + " (" + this.getQuantidadeDeCDsEncontrados() + " " + result + ")";
        s += " de: " + sdf.format(this.getDtSalva());
        return s;
    }

    public void ordena(Ordenacao ordenacao) {
        switch (ordenacao) {
            case PRECO_ASC:
                ordenaCDsPorPrecoCrescente();
                break;
            case PRECO_DESC:
                ordenaCDsPorPrecoDecrescente();
                break;
            case TITULO_PRECO_CRESC:
                ordenaCDsPorTituloEPrecoASC();
                break;
            case BANDA_PRECO_DESC:
                ordenaCDsPorBandaEPrecoASC();
                break;
            default:
                ordenaCDsPorPrecoCrescente();
                break;
        }
    }

}

package objetos;

import java.io.Serializable;

public class CD implements Comparable<CD>, Serializable {

    private String titulo;
    private String banda;
    private double preco;
    private String loja;

    public CD(String titulo, String banda, double preco, String loja) {
        this.titulo = titulo;
        this.banda = banda;
        this.preco = preco;
        this.loja = loja;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    @Override
    public int compareTo(CD outroCD) {
        //return (int) (this.getPreco()-outroCD.getPreco());
        //Talvez nao funcione quando o preco tiver apenas diferenca de centavos

        if (this.getPreco() == outroCD.getPreco()) {
            //Precos iguais
            return 0;
        } else {
            double dif = this.getPreco() - outroCD.getPreco();
            if (dif < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((banda == null) ? 0 : banda.hashCode());
        result = prime * result + ((loja == null) ? 0 : loja.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CD other = (CD) obj;
        if (banda == null) {
            if (other.banda != null) return false;
        } else if (!banda.equals(other.banda)) return false;
        if (loja == null) {
            if (other.loja != null) return false;
        } else if (!loja.equals(other.loja)) return false;
        if (titulo == null) {
            if (other.titulo != null) return false;
        } else if (!titulo.equals(other.titulo)) return false;
        return true;
    }

}

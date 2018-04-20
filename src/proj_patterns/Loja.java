package proj_patterns;

import java.util.Collection;

import objetos.CD;

public interface Loja {

    public boolean conectar(String usuario, String senha);

    public void desconectar();

    public Collection<CD> procurar(String chave);
}

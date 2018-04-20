package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import objetos.Busca;

public class PersistenciaBuscas {

    public void salvarBuscas(ArrayList<Busca> buscas, Path path) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(path.toFile());
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(buscas);

            out.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Busca> carregarBuscas(Path path) {
        ArrayList<Busca> buscas = new ArrayList<Busca>();

        try {
            FileInputStream fis = new FileInputStream(path.toFile());
            ObjectInputStream in = new ObjectInputStream(fis);

            buscas = (ArrayList<Busca>) in.readObject();

            in.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return buscas;
    }
}

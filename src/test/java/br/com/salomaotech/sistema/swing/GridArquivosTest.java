package br.com.salomaotech.sistema.swing;

import br.com.salomaotech.sistema.algoritmos.CriaPastaLocal;
import br.com.salomaotech.sistema.algoritmos.RemovePastaDeArquivos;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridArquivosTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testExibir() {

        String pastaDeTeste = pathLocal + "/grid_arquivos/";
        RemovePastaDeArquivos.remover(pastaDeTeste);

        int i;

        for (i = 0; i <= 10; i++) {

            CriaPastaLocal.criar(pastaDeTeste + i);

        }

        GridArquivos grid = new GridArquivos(null);
        int arquivosExibidos = grid.exibir(pastaDeTeste, new JPanel(), new JFrame(), null);

        System.out.println("Testando classe GridArquivos metodo: exibir");
        assertEquals(true, arquivosExibidos == i);

    }

}

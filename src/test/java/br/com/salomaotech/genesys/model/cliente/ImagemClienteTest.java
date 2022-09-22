package br.com.salomaotech.genesys.model.cliente;

import br.com.salomaotech.genesys.view.JFcliente;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImagemClienteTest {

    @Test
    public void testUpload() {

        System.out.println("Testando ImagemCliente metodo: upload");
        assertEquals(true, ImagemCliente.upload("1"));

    }

    @Test
    public void testExibir() {

        ImagemCliente.upload("1");
        System.out.println("Testando ImagemCliente metodo: exibir");
        assertEquals(true, new ImagemCliente().exibir("1", new JPanel()));

    }

    @Test
    public void testRemover() {

        System.out.println("Testando ImagemCliente metodo: remover");
        assertEquals(true, ImagemCliente.remover("1", new JFcliente().jPdadosPerfilFoto));

    }

}

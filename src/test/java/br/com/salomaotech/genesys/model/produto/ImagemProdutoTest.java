package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.genesys.view.JFproduto;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImagemProdutoTest {

    @Test
    public void testUpload() {

        System.out.println("Testando ImagemProduto metodo: upload");
        assertEquals(true, ImagemProduto.upload("1"));

    }

    @Test
    public void testExibir() {

        ImagemProduto.upload("1");
        System.out.println("Testando ImagemProduto metodo: exibir");
        assertEquals(true, new ImagemProduto().exibir("1", new JPanel()));

    }

    @Test
    public void testRemover() {

        System.out.println("Testando ImagemProduto metodo: remover");
        assertEquals(true, ImagemProduto.remover("1", new JFproduto().jPdadosPerfilFoto));

    }

}

package br.com.salomaotech.genesys.controller.produto;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ProdutoControllerTest {

    private final ProdutoController produtoController = new ProdutoController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            produtoController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoController metodo: construir");
        assertEquals(false, isErro);

    }

    @Test
    public void testConstruirEstoqueBaixo() {

        boolean isErro = false;

        try {

            produtoController.construirEstoqueBaixo();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoController metodo: construirEstoqueBaixo");
        assertEquals(false, isErro);

    }

}

package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.produto.VendaIniciaMetodosProdutos;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaControllerProdutosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos = new VendaIniciaMetodosProdutos(view);
    private final VendaCalculaControllerProdutos vendaCalculaController = new VendaCalculaControllerProdutos(0, vendaIniciaMetodosProdutos);

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            vendaCalculaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaController metodo: construir");
        assertEquals(false, isErro);

    }

}

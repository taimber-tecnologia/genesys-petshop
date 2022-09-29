package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaControllerTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(view);
    private final VendaCalculaController vendaCalculaController;

    public VendaCalculaControllerTest() {

        vendaCalculaController = new VendaCalculaController(new ProdutoModelo(), vendaIniciaMetodos);

    }

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

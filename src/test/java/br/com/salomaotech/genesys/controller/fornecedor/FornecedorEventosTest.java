package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.view.JFfornecedor;
import org.junit.Test;
import static org.junit.Assert.*;

public class FornecedorEventosTest {

    private final JFfornecedor view = new JFfornecedor();
    private final FornecedorEventos fornecedorEventos = new FornecedorEventos(view);

    @Test
    public void testSetFornecedorMetodos() {

        boolean isErro = false;

        try {

            fornecedorEventos.setFornecedorMetodos(new FornecedorMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FornecedorEventos metodo: setFornecedorMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        /* add eventos */
        fornecedorEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe FornecedorEventos método: addEventos");
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jTresultados.getKeyListeners().length == 2);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jTpesquisaNome.getKeyListeners().length == 1);
        assertEquals(true, view.jTpesquisaCnpj.getKeyListeners().length == 1);
        assertEquals(true, view.jBenderecoBuscarCep.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);

    }

}

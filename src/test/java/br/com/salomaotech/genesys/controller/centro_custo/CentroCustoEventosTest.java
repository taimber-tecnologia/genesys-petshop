package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.view.JFcentroCusto;
import org.junit.Test;
import static org.junit.Assert.*;

public class CentroCustoEventosTest {

    private final JFcentroCusto view = new JFcentroCusto();
    private final CentroCustoEventos centroCustoEventos = new CentroCustoEventos(view);

    @Test
    public void testSetCentroCustoMetodos() {

        boolean isErro = false;

        try {

            centroCustoEventos.setCentroCustoMetodos(new CentroCustoMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe CentroCustoEventos metodo: setCentroCustoMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        /* eventos */
        centroCustoEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe CentroCustoEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jTpesquisaNome.getKeyListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaReseta.getActionListeners().length == 1);

    }

}

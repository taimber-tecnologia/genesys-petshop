package br.com.salomaotech.genesys.controller.principal;

import br.com.salomaotech.genesys.view.JFprincipal;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrincipalEventosTest {

    private final JFprincipal view = new JFprincipal();
    private final PrincipalEventos principalEventos = new PrincipalEventos(view);

    @Test
    public void testSetPrincipalMetodos() {

        boolean isErro = false;

        try {

            principalEventos.setPrincipalMetodos(new PrincipalMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe PrincipalEventos metodo: setPrincipalMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        principalEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe PrincipalEventos metodo: addEventos");
        assertEquals(true, view.jBatalhoClientes.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoAgenda.getActionListeners().length == 1);
        assertEquals(true, view.jTagendaResultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoProdutos.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoConfiguracoesEmpresa.getActionListeners().length == 1);
        assertEquals(true, view.getWindowListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jLabrirSuporte.getMouseListeners().length == 1);
        assertEquals(true, view.jBatalhoAnimais.getMouseListeners().length == 1);
        assertEquals(true, view.jBatalhoFornecedores.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoFinanceiro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoVenda.getActionListeners().length == 1);
        assertEquals(true, view.jMagenda.getActionListeners().length == 1);
        assertEquals(true, view.jMfinanceiroPagar.getActionListeners().length == 1);
        assertEquals(true, view.jMfinanceiroReceber.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoServicos.getActionListeners().length == 1);
        assertEquals(true, view.jMatualizar.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoConfiguracoes.getActionListeners().length == 1);

    }

}

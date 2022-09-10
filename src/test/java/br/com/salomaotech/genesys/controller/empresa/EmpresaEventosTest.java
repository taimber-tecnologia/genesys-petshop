package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.controller.empresa.EmpresaEventos;
import br.com.salomaotech.genesys.view.JFempresa;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaEventosTest {

    private final JFempresa view = new JFempresa();
    private final EmpresaEventos empresaEventos = new EmpresaEventos(view);

    @Test
    public void testSetEmpresaMetodos() {

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        empresaEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe EmpresaEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jBadicionaFoto.getActionListeners().length == 1);
        assertEquals(true, view.jBremoveFoto.getActionListeners().length == 1);
        assertEquals(true, view.jBenderecoBuscarCep.getActionListeners().length == 1);

    }

}

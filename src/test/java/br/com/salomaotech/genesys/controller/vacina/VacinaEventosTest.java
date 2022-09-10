package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.controller.vacina.VacinaEventos;
import br.com.salomaotech.genesys.view.JFvacina;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacinaEventosTest {

    private final JFvacina view = new JFvacina();
    private final VacinaEventos vacinaEventos = new VacinaEventos(view);

    @Test
    public void testSetVacinaMetodos() {

    }

    @Test
    public void testSetAnimalModelo() {

    }

    @Test
    public void testSetComboBoxVacinas() {

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        vacinaEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe VacinaEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBatualizarNomesVacinas.getActionListeners().length == 1);

    }

}

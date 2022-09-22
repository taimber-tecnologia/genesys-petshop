package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.view.JFanimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalEventosTest {

    private final JFanimal view = new JFanimal();
    private final AnimalEventos animalEventos = new AnimalEventos(view);

    @Test
    public void testSetAnimalMetodos() {

    }

    @Test
    public void testSetComboBoxClientesCadastro() {

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

    }

    @Test
    public void testSetComboBoxAnimalRaca() {

    }

    @Test
    public void testSetComboBoxAnimalEspecie() {

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        animalEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe AnimalEventos método: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaClienteCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshClienteCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaNomeClienteRefresh.getActionListeners().length == 1);
        assertEquals(true, view.jTpesquisaNome.getKeyListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoVacinas.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshRacaCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshEspecieCadastro.getActionListeners().length == 1);

    }

}

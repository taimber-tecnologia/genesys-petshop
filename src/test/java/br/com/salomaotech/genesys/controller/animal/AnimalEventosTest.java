package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalEspecie;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalRaca;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFanimal;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalEventosTest {

    private final JFanimal view = new JFanimal();
    private final AnimalEventos animalEventos = new AnimalEventos(view);

    @Test
    public void testSetAnimalMetodos() {

        boolean isErro = false;

        try {

            animalEventos.setAnimalMetodos(new AnimalMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalEventos método: setAnimalMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxClientesCadastro() {

        boolean isErro = false;

        try {

            animalEventos.setComboBoxClientesCadastro(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalEventos método: setComboBoxClientesCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

        boolean isErro = false;

        try {

            animalEventos.setComboBoxClientesPesquisa(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalEventos método: setComboBoxClientesPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxAnimalRaca() {

        boolean isErro = false;

        try {

            animalEventos.setComboBoxAnimalRaca(new ComboBoxAnimalRaca(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalEventos método: setComboBoxAnimalRaca");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxAnimalEspecie() {

        boolean isErro = false;

        try {

            animalEventos.setComboBoxAnimalEspecie(new ComboBoxAnimalEspecie(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalEventos método: setComboBoxAnimalEspecie");
        assertEquals(false, isErro);

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

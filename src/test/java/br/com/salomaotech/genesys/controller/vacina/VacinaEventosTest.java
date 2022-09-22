package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.vacina.ComboBoxVacinas;
import br.com.salomaotech.genesys.view.JFvacina;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacinaEventosTest {

    private final JFvacina view = new JFvacina();
    private final VacinaEventos vacinaEventos = new VacinaEventos(view);

    @Test
    public void testSetVacinaMetodos() {

        boolean isErro = false;

        try {

            vacinaEventos.setVacinaMetodos(new VacinaMetodos(view, new AnimalModelo()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VacinaEventos metodo: setVacinaMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetAnimalModelo() {

        boolean isErro = false;

        try {

            vacinaEventos.setAnimalModelo(new AnimalModelo());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VacinaEventos metodo: setAnimalModelo");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxVacinas() {

        boolean isErro = false;

        try {

            vacinaEventos.setComboBoxVacinas(new ComboBoxVacinas(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VacinaEventos metodo: setComboBoxVacinas");
        assertEquals(false, isErro);

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

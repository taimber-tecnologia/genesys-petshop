package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinanceiroEventosTest {

    private final JFfinanceiro view = new JFfinanceiro();
    private final FinanceiroEventos financeiroEventos = new FinanceiroEventos(view);

    @Test
    public void testSetFinanceiroMetodos() {

        boolean isErro = false;

        try {

            financeiroEventos.setFinanceiroMetodos(new FinanceiroMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FinanceiroEventos metodo: setFinanceiroMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxCentroCusto() {

        boolean isErro = false;

        try {

            financeiroEventos.setComboBoxCentroCusto(new ComboBoxCentroCusto(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FinanceiroEventos metodo: setComboBoxCentroCusto");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        financeiroEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe FinanceiroEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshCadastroCentroCusto.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoCentroCusto.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaCentroCusto.getActionListeners().length == 1);
        assertEquals(true, view.jCpesquisaDataAnterior.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaReseta.getActionListeners().length == 1);

    }

}

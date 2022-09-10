package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.controller.venda.VendaEventos;
import br.com.salomaotech.genesys.view.JFvenda;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaEventosTest {

    private final JFvenda view = new JFvenda();
    private final VendaEventos vendaEventos = new VendaEventos(view);

    @Test
    public void testSetVendaMetodos() {

    }

    @Test
    public void testSetComboBoxAnimaisCadastro() {

    }

    @Test
    public void testSetComboBoxClientesCadastro() {

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

    }

    @Test
    public void testSetComboBoxCentroCusto() {

    }

    @Test
    public void setJtableProduto() {

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        vendaEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe VendaEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBimprimir.getActionListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaAnimal.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshAnimais.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshCliente.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaNomeClienteRefresh.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaCliente.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroSelecionarProduto.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroRemoverItem.getActionListeners().length == 1);
        assertEquals(true, view.jTcadastroPorcentagemJuros.getKeyListeners().length == 1);
        assertEquals(true, view.jTcadastroValorDesconto.getKeyListeners().length == 1);
        assertEquals(true, view.jBcadastroPesquisarItemProduto.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroRefreshItemProduto.getActionListeners().length == 1);
        assertEquals(true, view.jTvendaProdutosDisponiveis.getMouseListeners().length == 3);
        assertEquals(true, view.jTvendaProdutosDisponiveis.getKeyListeners().length == 2);
        assertEquals(true, view.jTvendaModeloItemSelecionados.getMouseListeners().length == 3);
        assertEquals(true, view.jTvendaModeloItemSelecionados.getKeyListeners().length == 2);
        assertEquals(true, view.jBfinalizarVenda.getActionListeners().length == 1);
        assertEquals(true, view.jBreabrirVenda.getActionListeners().length == 1);
        assertEquals(true, view.jTcadastroValorEntrada.getKeyListeners().length == 1);
        assertEquals(true, view.jBrefreshCadastroCentroCusto.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaCentroCusto.getActionListeners().length == 1);

    }

}

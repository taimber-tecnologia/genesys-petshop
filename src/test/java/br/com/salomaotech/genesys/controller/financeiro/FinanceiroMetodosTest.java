package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.math.BigDecimal;
import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinanceiroMetodosTest {

    private final JFfinanceiro view = new JFfinanceiro();
    private Calendar calendar = Calendar.getInstance();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private FinanceiroModelo financeiroModelo = new FinanceiroModelo();
    private final FinanceiroMetodos financeiroMetodos = new FinanceiroMetodos(view);
    private final ComboBoxCentroCusto comboBoxCentroCusto;

    public FinanceiroMetodosTest() {

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

        /* remove registros financeiros anteriores */
        new Repository(new FinanceiroModelo()).deleteTodos();
        for (int i = 0; i <= 3; i++) {

            /* atualiza o calendário */
            calendar = Datas.adicionarMesCalendar(calendar, i);

            /* simula cadastro de financeiro */
            financeiroModelo = new FinanceiroModelo();
            financeiroModelo.setData(calendar);
            financeiroModelo.setValor(new BigDecimal(100));
            financeiroModelo.setDescricao("Teste A");
            financeiroModelo.setIdCentroCusto(centroCustoModelo.getId());
            financeiroModelo.setIsPago(true);
            financeiroModelo.setIdCliente(1);
            financeiroModelo.setIdVenda(2);
            new Repository(financeiroModelo).save();

        }

        /* combobox de cadastro com lista de centro de custo */
        comboBoxCentroCusto = new ComboBoxCentroCusto(view.jCcadastroCentroCusto);
        comboBoxCentroCusto.preencher();

        /* metodos */
        financeiroMetodos.setComboBoxCentroCusto(comboBoxCentroCusto);

    }

    @Test
    public void testSetComboBoxCentroCusto() {

        boolean isErro = false;

        try {

            financeiroMetodos.setComboBoxCentroCusto(comboBoxCentroCusto);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FinanceiroMetodos metodo: setComboBoxCentroCusto");
        assertEquals(false, isErro);

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        financeiroMetodos.popularFormulario(financeiroModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe FinanceiroMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == financeiroModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(financeiroModelo.getData()));
        assertEquals(true, view.jTcadastroValor.getText().equals(financeiroModelo.getValor().toString()));
        assertEquals(true, view.jTcadastroDescricao.getText().equals(financeiroModelo.getDescricao()));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == financeiroModelo.getIdCentroCusto());
        assertEquals(true, view.jCcadastroPago.isSelected() == financeiroModelo.isIsPago());
        assertEquals(true, view.getIdVenda() == financeiroModelo.getIdVenda());
        assertEquals(true, view.getIdCliente() == financeiroModelo.getIdCliente());
        assertEquals(true, view.jCcadastroIsDespesa.getSelectedIndex() == 1);

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        financeiroMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe FinanceiroMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, isNull(view.jDcadastroData.getDate()));
        assertEquals(true, view.jTcadastroValor.getText().equals(""));
        assertEquals(true, view.jTcadastroDescricao.getText().equals(""));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == 0);
        assertEquals(true, view.jCcadastroPago.isSelected() == false);
        assertEquals(true, view.getIdVenda() == 0);
        assertEquals(true, view.getIdCliente() == 0);
        assertEquals(true, view.jCcadastroIsDespesa.getSelectedIndex() == 0);

    }

    @Test
    public void testHabilitarCampos() {

        /* é esperado que alguns campos estejam desabilitados */
        financeiroMetodos.habilitarCampos();

        /* testa se os campos estão desabilitados */
        System.out.println("Testando classe FinanceiroMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* é esperado que alguns campos estejam habilitados */
        financeiroMetodos.popularFormulario(financeiroModelo);
        financeiroMetodos.habilitarCampos();

        /* é esperado que alguns campos estejam habilitados */
        System.out.println("Testando classe FinanceiroMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        financeiroMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se os eventos de pop menu foram adicionados */
        System.out.println("Testando classe FinanceiroMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroValor));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroDescricao));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        financeiroMetodos.abrirCadastro(financeiroModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe FinanceiroMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == financeiroModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(financeiroModelo.getData()));
        assertEquals(true, view.jTcadastroValor.getText().equals(financeiroModelo.getValor().toString()));
        assertEquals(true, view.jTcadastroDescricao.getText().equals(financeiroModelo.getDescricao()));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == financeiroModelo.getIdCentroCusto());
        assertEquals(true, view.jCcadastroPago.isSelected() == financeiroModelo.isIsPago());
        assertEquals(true, view.getIdVenda() == financeiroModelo.getIdVenda());
        assertEquals(true, view.getIdCliente() == financeiroModelo.getIdCliente());
        assertEquals(true, view.jCcadastroIsDespesa.getSelectedIndex() == 1);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        financeiroMetodos.popularFormulario(financeiroModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        financeiroModelo = financeiroMetodos.salvar();
        financeiroMetodos.popularFormulario(financeiroModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe FinanceiroMetodos metodo: salvar");
        assertEquals(true, view.getId() == financeiroModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(financeiroModelo.getData()));
        assertEquals(true, view.jTcadastroValor.getText().equals(financeiroModelo.getValor().toString()));
        assertEquals(true, view.jTcadastroDescricao.getText().equals(financeiroModelo.getDescricao()));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == financeiroModelo.getIdCentroCusto());
        assertEquals(true, view.jCcadastroPago.isSelected() == financeiroModelo.isIsPago());
        assertEquals(true, view.getIdVenda() == financeiroModelo.getIdVenda());
        assertEquals(true, view.getIdCliente() == financeiroModelo.getIdCliente());
        assertEquals(true, view.jCcadastroIsDespesa.getSelectedIndex() == 1);

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        financeiroMetodos.popularFormulario(financeiroModelo);

        /* testa exclusão */
        System.out.println("Testando classe FinanceiroMetodos metodo: excluir");
        assertEquals(true, financeiroMetodos.excluir());

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data de inicio */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data de fim */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(calendar.getTime());
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data de inicio e data de fim */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(calendar.getTime());
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data anterior */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(true);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 05");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: pago todos */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 06");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: pago sim */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Sim");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 07");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: pago nao */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Nao");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 08");
        assertEquals(true, view.jTresultados.getRowCount() == 0);

        /* usando filtro: data de inicio, pago sim */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Sim");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 09");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data de inicio, pago nao */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Nao");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 10");
        assertEquals(true, view.jTresultados.getRowCount() == 0);

        /* usando filtro: data de inicio, pago todos */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(null);
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 11");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data fim, pago sim */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(calendar.getTime());
        view.jCpesquisaPago.setSelectedItem("Sim");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 12");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data fim, pago nao */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(calendar.getTime());
        view.jCpesquisaPago.setSelectedItem("Nao");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 13");
        assertEquals(true, view.jTresultados.getRowCount() == 0);

        /* usando filtro: data fim, pago todos */
        view.jDpesquisaDataInicio.setDate(null);
        view.jDPesquisaDataFim.setDate(calendar.getTime());
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 14");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jDpesquisaDataInicio.setDate(calendar.getTime());
        view.jDPesquisaDataFim.setDate(calendar.getTime());
        view.jCpesquisaPago.setSelectedItem("Todos");
        view.jCdataAnterior.setSelected(false);
        financeiroMetodos.pesquisar();
        System.out.println("Testando classe FinanceiroMetodos metodo: pesquisar etapa 15");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}

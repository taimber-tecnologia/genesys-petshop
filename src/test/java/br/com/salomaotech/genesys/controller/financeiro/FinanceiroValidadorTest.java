package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.controller.financeiro.FinanceiroValidador;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinanceiroValidadorTest {

    private final JFfinanceiro view = new JFfinanceiro();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private final ComboBoxCentroCusto comboBoxCentroCusto;
    private FinanceiroValidador financeiroValidador;

    public FinanceiroValidadorTest() {

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

        /* combobox de cadastro com lista de centro de custo */
        comboBoxCentroCusto = new ComboBoxCentroCusto(view.jCcadastroCentroCusto);
        comboBoxCentroCusto.preencher();

    }

    @Test
    public void testIsValido() {

        /* validando usando filtro: nenhum */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(null);
        view.jTcadastroValor.setText(null);
        comboBoxCentroCusto.selecionarItemPorId(0);
        System.out.println("Testando classe FinanceiroValidador metodo: isValido etapa 01");
        assertEquals(false, financeiroValidador.isValido());

        /* validando usando filtro: data */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jTcadastroValor.setText(null);
        comboBoxCentroCusto.selecionarItemPorId(0);
        System.out.println("Testando classe FinanceiroValidador metodo: isValido etapa 02");
        assertEquals(false, financeiroValidador.isValido());

        /* validando usando filtro: valor */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(null);
        view.jTcadastroValor.setText("100");
        comboBoxCentroCusto.selecionarItemPorId(0);
        System.out.println("Testando classe FinanceiroValidador metodo: isValido etapa 03");
        assertEquals(false, financeiroValidador.isValido());

        /* validando usando filtro: centro de custo */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(null);
        view.jTcadastroValor.setText(null);
        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        System.out.println("Testando classe FinanceiroValidador metodo: isValido etapa 04");
        assertEquals(false, financeiroValidador.isValido());

        /* validando usando filtro: todos */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jTcadastroValor.setText("100");
        view.jCcadastroCentroCusto.getEditor().setItem("Teste");
        System.out.println("Testando classe FinanceiroValidador metodo: isValido etapa 05");
        assertEquals(true, financeiroValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* validando usando filtro: nenhum */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(null);
        view.jTcadastroValor.setText(null);
        comboBoxCentroCusto.selecionarItemPorId(0);
        financeiroValidador.isValido();
        System.out.println("Testando classe FinanceiroValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, financeiroValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: data */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jTcadastroValor.setText(null);
        comboBoxCentroCusto.selecionarItemPorId(0);
        financeiroValidador.isValido();
        System.out.println("Testando classe FinanceiroValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, financeiroValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(null);
        view.jTcadastroValor.setText("100");
        comboBoxCentroCusto.selecionarItemPorId(0);
        financeiroValidador.isValido();
        System.out.println("Testando classe FinanceiroValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, financeiroValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: centro de custo */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(null);
        view.jTcadastroValor.setText(null);
        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        financeiroValidador.isValido();
        System.out.println("Testando classe FinanceiroValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, financeiroValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos */
        financeiroValidador = new FinanceiroValidador(view);
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jTcadastroValor.setText("100");
        view.jCcadastroCentroCusto.getEditor().setItem("Teste");
        financeiroValidador.isValido();
        System.out.println("Testando classe FinanceiroValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, financeiroValidador.getMensagensErro().length() == 0);

    }

}

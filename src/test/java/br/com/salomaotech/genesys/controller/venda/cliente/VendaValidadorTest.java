package br.com.salomaotech.genesys.controller.venda.cliente;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaValidadorTest {

    private final JFvenda view = new JFvenda();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();

    private final ComboBoxAnimais comboBoxAnimaisCadastro;
    private final ComboBoxClientes comboBoxClientesCadastro;
    private final ComboBoxCentroCusto comboBoxCentroCusto;
    private final DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTvendaModeloItemSelecionados.getModel();

    public VendaValidadorTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorCusto(new BigDecimal(100));
        produtoModelo.setCategoria("ABC");
        new Repository(produtoModelo).save();

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

        /* combobox de cadastro com lista de animais */
        comboBoxAnimaisCadastro = new ComboBoxAnimais(view.jCcadastroNomeAnimal, clienteModelo.getId());
        comboBoxAnimaisCadastro.preencher();

        /* combobox de cadastro com lista de clientes */
        comboBoxClientesCadastro = new ComboBoxClientes(view.jCcadastroNomeCliente, comboBoxAnimaisCadastro);
        comboBoxClientesCadastro.preencher();

        /* combobox de cadastro com lista de centro de custo */
        comboBoxCentroCusto = new ComboBoxCentroCusto(view.jCcadastroCentroCusto);
        comboBoxCentroCusto.preencher();

    }

    @Test
    public void testIsValido() {

        /* validando usando filtro: nenhum */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        VendaValidador validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 01");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: data */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 02");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: cliente */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 03");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: animal */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 04");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: centro de custo */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 05");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: item de produto */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 06");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: valor */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 07");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: desconto */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText("10");
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 08");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: juros */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText("20");
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 09");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: valor inválido */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        view.jTcadastroValor.setText("123,456,789");
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 10");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: desconto inválido */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText("123,456,789");
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 11");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: porcentagem inválida */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText("123,456,789");
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 12");
        assertEquals(false, validador.isValido());

        /* validando usando filtro: todos */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText("5");
        view.jTcadastroPorcentagemJuros.setText("0");
        defaultTableModel.setNumRows(1);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        System.out.println("Testando classe VendaValidador metodo: isValido etapa 13");
        assertEquals(true, validador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* validando usando filtro: nenhum */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        VendaValidador validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, validador.getMensagensErro().length() > 0);


        /* validando usando filtro: data */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: cliente */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: animal */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: centro de custo */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: item de produto */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 06");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 07");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: desconto */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText("10");
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 08");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: juros */
        view.jDcadastroData.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        comboBoxAnimaisCadastro.selecionarItemComBox(0);
        comboBoxCentroCusto.selecionarItemPorId(0);
        view.jTcadastroValor.setText(null);
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText("20");
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 09");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor inválido */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        view.jTcadastroValor.setText("123,456,789");
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 10");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: desconto inválido */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText("123,456,789");
        view.jTcadastroPorcentagemJuros.setText(null);
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 11");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: porcentagem inválida */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText(null);
        view.jTcadastroPorcentagemJuros.setText("123,456,789");
        defaultTableModel.setNumRows(0);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 12");
        assertEquals(true, validador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientesCadastro.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimaisCadastro.selecionarItemComBox(animalModelo.getId());
        view.jTcadastroValor.setText("100");
        view.jTcadastroValorDesconto.setText("5");
        view.jTcadastroPorcentagemJuros.setText("0");
        defaultTableModel.setNumRows(1);
        validador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);
        validador.isValido();
        System.out.println("Testando classe VendaValidador metodo: getMensagensErro etapa 13");
        assertEquals(true, validador.getMensagensErro().length() == 0);

    }

}

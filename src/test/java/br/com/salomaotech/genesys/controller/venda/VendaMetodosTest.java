package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.controller.venda.VendaMetodos;
import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.produto.JtableProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaMetodosTest {

    private final JFvenda view = new JFvenda();
    private final ComboBoxAnimais comboBoxAnimaisCadastro;
    private final ComboBoxClientes comboBoxClientesCadastro;
    private final ComboBoxClientes comboBoxClientesPesquisa;
    private final ComboBoxCentroCusto comboBoxCentroCusto;
    private final JtableProduto jtableProduto;
    private final VendaMetodos vendaMetodos = new VendaMetodos(view);
    private List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final Calendar calendar = Calendar.getInstance();

    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private VendaModeloItem vendaModeloItem = new VendaModeloItem();
    private VendaModelo vendaModelo;

    public VendaMetodosTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animais */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Felix");
        animalModelo.setEspecie("Felino");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(10));
        new Repository(produtoModelo).save();

        /* simula o cadastro de item de venda */
        vendaModeloItem.setDescricao("Teste");
        vendaModeloItem.setValor(new BigDecimal(100));
        vendaModeloItem.setQuantidade(new BigDecimal(10));
        new Repository(vendaModeloItem).save();
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo = new VendaModelo();
        vendaModelo.setData(calendar);
        vendaModelo.setIdAnimal(animalModelo.getId());
        vendaModelo.setIdCliente(clienteModelo.getId());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setDesconto(new BigDecimal(0));
        vendaModelo.setFormaPagamento("Credito");
        vendaModelo.setJuros(new BigDecimal(0));
        vendaModelo.setFinalizado(false);
        vendaModelo.setNumeroParcelas(10);
        vendaModelo.setIdCentroCusto(centroCustoModelo.getId());
        new Repository(vendaModelo).save();

        /* combobox de cadastro com lista de animais */
        comboBoxAnimaisCadastro = new ComboBoxAnimais(view.jCcadastroNomeAnimal, 0);
        comboBoxAnimaisCadastro.preencher();

        /* combobox de cadastro com lista de clientes */
        comboBoxClientesCadastro = new ComboBoxClientes(view.jCcadastroNomeCliente, comboBoxAnimaisCadastro);
        comboBoxClientesCadastro.preencher();

        /* combobox de pesquisa com lista de clientes */
        comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente, comboBoxAnimaisCadastro);
        comboBoxClientesPesquisa.preencher();

        /* combobox de cadastro com lista de centro de custo */
        comboBoxCentroCusto = new ComboBoxCentroCusto(view.jCcadastroCentroCusto);
        comboBoxCentroCusto.preencher();

        /* jlist com lista de produto cadastrados */
        jtableProduto = new JtableProduto(view.jTvendaProdutosDisponiveis);
        jtableProduto.preencher();

        /* metodos */
        vendaMetodos.setComboBoxAnimaisCadastro(comboBoxAnimaisCadastro);
        vendaMetodos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        vendaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        vendaMetodos.setComboBoxCentroCusto(comboBoxCentroCusto);
        vendaMetodos.setJtableProduto(jtableProduto);

    }

    /**
     * Simula clicar em produtos
     */
    private void simularSelecionarItemNaListaDeProdutos() {

        /* quantidade, juros, desconto */
        BigDecimal quantidade = new BigDecimal(3);
        BigDecimal juros = new BigDecimal(2);
        BigDecimal desconto = new BigDecimal(6);

        /* limpa o venda atual para simular um novo */
        vendaModelo = new VendaModelo();
        vendaModeloItemList = new ArrayList();
        vendaModeloItem = new VendaModeloItem();

        /* seleciona o primeiro item de produto disponiveis */
        view.jTvendaProdutosDisponiveis.setRowSelectionInterval(0, 0);
        long id = (long) view.jTvendaProdutosDisponiveis.getModel().getValueAt(view.jTvendaProdutosDisponiveis.getSelectedRow(), 0);
        view.jTcadastroQuantidadeProduto.setText(quantidade.toString());
        view.jTcadastroPorcentagemJuros.setText(juros.toString());
        view.jTcadastroValorDesconto.setText(desconto.toString());

        /* adiciona o produto selecionado com a quantidade */
        vendaMetodos.adicionarItemDeProduto();

        /* carrega os dados do produto selecionado */
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(id);

        /* popula um item de venda */
        vendaModeloItem.setDescricao(produtoModelo.getNome());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setQuantidade(quantidade);
        vendaModeloItem.setMedida(produtoModelo.getMedida());

        /* adiciona o item de venda na lista */
        vendaModeloItemList.add(vendaModeloItem);

        /* aplica juros e desconto */
        vendaModelo.setJuros(juros);
        vendaModelo.setDesconto(desconto);

        /* adiciona itens selecionados ao venda */
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);

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
    public void testSetIsAprovarVenda() {

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        vendaMetodos.popularFormulario(vendaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe VendaMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == vendaModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(vendaModelo.getData()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == vendaModelo.getIdCliente());
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == vendaModelo.getIdAnimal());
        assertEquals(true, vendaModeloItemList.equals(vendaModelo.getVendaModeloItemList()));
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString())));
        assertEquals(true, view.jTcadastroValorDesconto.getText().equals(vendaModelo.getDesconto().toString()));
        assertEquals(true, view.jCcadastroFormaPagamento.getSelectedItem().equals(vendaModelo.getFormaPagamento()));
        assertEquals(true, view.jTcadastroPorcentagemJuros.getText().equals(vendaModelo.getJuros().toString()));
        assertEquals(true, view.jCnumeroDeParcelas.getSelectedItem().equals(String.valueOf(vendaModelo.getNumeroParcelas())));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == vendaModelo.getIdCentroCusto());

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        vendaMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe VendaMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, isNull(view.jDcadastroData.getCalendar()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == 0);
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == 0);
        assertEquals(true, view.jTvendaModeloItemSelecionados.getRowCount() == 0);
        assertEquals(true, view.jTvendaProdutosDisponiveis.getRowCount() == 1);
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter("0")));
        assertEquals(true, view.jTcadastroValorDesconto.getText().equals("0"));
        assertEquals(true, view.jCcadastroFormaPagamento.getSelectedIndex() == 0);
        assertEquals(true, view.jTcadastroPorcentagemJuros.getText().equals("0"));
        assertEquals(true, view.jCnumeroDeParcelas.getSelectedIndex() == 0);
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == 0);

    }

    @Test
    public void testHabilitarCampos() {

        /* maioria dos campos habilitados */
        vendaMetodos.habilitarCampos();
        System.out.println("Testando classe VendaMetodos metodo: habilitarCampos etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());
        assertEquals(false, view.jBimprimir.isEnabled());

        /* maioria do campos habilitados */
        vendaMetodos.popularFormulario(vendaModelo);
        vendaMetodos.habilitarCampos();
        System.out.println("Testando classe VendaMetodos metodo: habilitarCampos etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());
        assertEquals(true, view.jBimprimir.isEnabled());

    }

    @Test
    public void testHabilitarCamposSelecaoProduto() {

        /* nenhum produto selecionado */
        vendaMetodos.habilitarCamposSelecaoProdutos();
        System.out.println("Testando classe VendaMetodos metodo: habilitarCamposSelecaoProduto etapa 01");
        assertEquals(false, view.jBcadastroSelecionarProduto.isEnabled());
        assertEquals(false, view.jTcadastroQuantidadeProduto.isEnabled());

        /* simula o clique em um produto disponível */
        view.jTvendaProdutosDisponiveis.setRowSelectionInterval(0, 0);
        vendaMetodos.habilitarCamposSelecaoProdutos();
        System.out.println("Testando classe VendaMetodos metodo: habilitarCamposSelecaoProduto etapa 02");
        assertEquals(true, view.jBcadastroSelecionarProduto.isEnabled());
        assertEquals(true, view.jTcadastroQuantidadeProduto.isEnabled());

    }

    @Test
    public void testHabilitarCamposSelecaoProdutoSelecionados() {

        /* nenhum produto selecionado */
        vendaMetodos.habilitarCamposSelecaoProdutosSelecionados();
        System.out.println("Testando classe VendaMetodos metodo: habilitarCamposSelecaoProdutoSelecionados etapa 01");
        assertEquals(false, view.jBcadastroRemoverItem.isEnabled());

        /* simula o clique em um produto disponível */
        simularSelecionarItemNaListaDeProdutos();
        view.jTvendaModeloItemSelecionados.setRowSelectionInterval(0, 0);
        vendaMetodos.habilitarCamposSelecaoProdutosSelecionados();
        System.out.println("Testando classe VendaMetodos metodo: habilitarCamposSelecaoProdutoSelecionados etapa 02");
        assertEquals(true, view.jBcadastroRemoverItem.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        vendaMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se o menu de popup foi adicionado */
        System.out.println("Testando classe VendaMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroQuantidadeProduto));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroPorcentagemJuros));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroValorEntrada));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroValorDesconto));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro com o ID de cadastro já feito no construtor */
        vendaMetodos.abrirCadastro(vendaModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe VendaMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == vendaModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(vendaModelo.getData()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == vendaModelo.getIdCliente());
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == vendaModelo.getIdAnimal());
        assertEquals(true, vendaModeloItemList.equals(vendaModelo.getVendaModeloItemList()));
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString())));
        assertEquals(true, view.jTcadastroValorDesconto.getText().equals(vendaModelo.getDesconto().toString()));
        assertEquals(true, view.jCcadastroFormaPagamento.getSelectedItem().equals(vendaModelo.getFormaPagamento()));
        assertEquals(true, view.jTcadastroPorcentagemJuros.getText().equals(vendaModelo.getJuros().toString()));
        assertEquals(true, view.jCnumeroDeParcelas.getSelectedItem().equals(String.valueOf(vendaModelo.getNumeroParcelas())));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == vendaModelo.getIdCentroCusto());

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        vendaMetodos.popularFormulario(vendaModelo);
        vendaModelo = vendaMetodos.salvar();
        vendaMetodos.popularFormulario(vendaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe VendaMetodos metodo: salvar");
        assertEquals(true, view.getId() == vendaModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(vendaModelo.getData()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == vendaModelo.getIdCliente());
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == vendaModelo.getIdAnimal());
        assertEquals(true, vendaModeloItemList.size() == vendaModelo.getVendaModeloItemList().size());
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString())));
        assertEquals(true, view.jTcadastroValorDesconto.getText().equals(vendaModelo.getDesconto().toString()));
        assertEquals(true, view.jCcadastroFormaPagamento.getSelectedItem().equals(vendaModelo.getFormaPagamento()));
        assertEquals(true, view.jTcadastroPorcentagemJuros.getText().equals(vendaModelo.getJuros().toString()));
        assertEquals(true, view.jCnumeroDeParcelas.getSelectedItem().equals(String.valueOf(vendaModelo.getNumeroParcelas())));
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == vendaModelo.getIdCentroCusto());

    }

    @Test
    public void testExcluir() {

        /* popula o formulário simulando o preenchimento dos dados */
        vendaMetodos.popularFormulario(vendaModelo);

        /* testa exclusão */
        System.out.println("Testando classe VendaMetodos metodo: excluir");
        assertEquals(true, vendaMetodos.excluir());

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaData.setCalendar(null);
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        vendaMetodos.pesquisar();
        System.out.println("Testando classe VendaMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data */
        view.jDpesquisaData.setCalendar(vendaModelo.getData());
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        vendaMetodos.pesquisar();
        System.out.println("Testando classe VendaMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        view.jDpesquisaData.setCalendar(null);
        comboBoxClientesPesquisa.selecionarItemPorId(clienteModelo.getId());
        vendaMetodos.pesquisar();
        System.out.println("Testando classe VendaMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jDpesquisaData.setCalendar(vendaModelo.getData());
        comboBoxClientesPesquisa.selecionarItemPorId(clienteModelo.getId());
        vendaMetodos.pesquisar();
        System.out.println("Testando classe VendaMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testRecalcularValorTotal() {

        vendaMetodos.popularFormulario(vendaModelo);
        vendaMetodos.recalcularValorTotal();
        System.out.println("Testando classe VendaMetodos metodo: recalcularValorTotal");
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString())));

    }

    @Test
    public void testAdicionarItemDeProduto() {

        /* simula clicar em produto */
        simularSelecionarItemNaListaDeProdutos();

        System.out.println("Testando classe VendaMetodos metodo: adicionarItemDe etapa 01 comparando valor total");
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString())));

        System.out.println("Testando classe VendaMetodos metodo: adicionarItemDe etapa 02 aba selecionada");
        assertEquals(true, view.jTabbedPane1.getSelectedIndex() == 0);

        System.out.println("Testando classe VendaMetodos metodo: adicionarItemDe etapa 03 limpar quantidade de itens selecionados");
        assertEquals(true, view.jTcadastroQuantidadeProduto.getText().equals("0"));

        System.out.println("Testando classe VendaMetodos metodo: adicionarItemDe etapa 04 quantidade de itens selecionados");
        assertEquals(true, view.jTvendaModeloItemSelecionados.getRowCount() == 1);

    }

    @Test
    public void testRemoverItemDeProduto() {

        /* Simula clicar em produto */
        simularSelecionarItemNaListaDeProdutos();

        /* simula a remoção do item selecionado */
        view.jTvendaModeloItemSelecionados.setRowSelectionInterval(0, 0);
        vendaModeloItemList.remove(vendaModeloItem);
        vendaMetodos.removerItemDeProduto();

        System.out.println("Testando classe VendaMetodos metodo: adicionarItemDe etapa 01 comparando valor total");
        assertEquals(true, view.jTcadastroValor.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString())));

        System.out.println("Testando classe VendaMetodos metodo: adicionarItemDe etapa 02 quantidade de itens selecionados");
        assertEquals(true, view.jTvendaModeloItemSelecionados.getRowCount() == 0);

    }

}

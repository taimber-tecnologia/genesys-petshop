package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.produto.VendaIniciaMetodosProdutos;
import br.com.salomaotech.genesys.controller.venda.venda_inicia.servico.VendaIniciaMetodosServicos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaIniciaMetodosComumTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos = new VendaIniciaMetodosProdutos(view);
    private final VendaIniciaMetodosServicos vendaIniciaMetodosServicos = new VendaIniciaMetodosServicos(view);
    private VendaModeloItem vendaModeloItemCompartilhado = new VendaModeloItem();
    private final List<VendaModeloItem> vendaModeloItemListCompartilhado = new ArrayList();
    private final VendaIniciaMetodosComum vendaIniciaMetodosComum = new VendaIniciaMetodosComum(view, vendaModeloItemListCompartilhado, vendaModeloItemCompartilhado);
    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private ServicoModelo servicoModelo = new ServicoModelo();

    public VendaIniciaMetodosComumTest() {

        /* remove cadastros antigos */
        new Repository(new VendaModelo()).deleteTodos();
        new Repository(new ProdutoModelo()).deleteTodos();
        new Repository(new ServicoModelo()).deleteTodos();

        /* simula cadastro de produto */
        produtoModelo = new ProdutoModelo();
        produtoModelo.setNome("Produto Teste");
        produtoModelo.setValorVenda(new BigDecimal("100"));
        produtoModelo.setQuantidade(new BigDecimal(50));
        new Repository(produtoModelo).save();

        /* simula cadastro de serviço */
        servicoModelo = new ServicoModelo();
        servicoModelo.setNome("Serviço Teste");
        servicoModelo.setValor(new BigDecimal("19.90"));
        new Repository(servicoModelo).save();

    }

    @Test
    public void testSetVendaIniciaMetodosProdutos() {

        boolean isErro = false;

        try {

            vendaIniciaMetodosComum.setVendaIniciaMetodosProdutos(new VendaIniciaMetodosProdutos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: setVendaIniciaMetodosProdutos");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetVendaIniciaMetodosServicos() {

        boolean isErro = false;

        try {

            vendaIniciaMetodosComum.setVendaIniciaMetodosServicos(new VendaIniciaMetodosServicos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: setVendaIniciaMetodosServicos");
        assertEquals(false, isErro);

    }

    @Test
    public void testExibirSelecionados() {

        // Nada foi selecionado ainda
        vendaIniciaMetodosComum.exibirSelecionados();

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: exibirSelecionados");
        assertEquals(true, view.jTitensSelecionados.getRowCount() == 0);
        assertEquals("R$ 0,00", view.jTvendaValorTotal.getText());

    }

    @Test
    public void testHabilitarCampos() {

        // Habilita campos para uma nova venda
        vendaIniciaMetodosComum.habilitarCampos();

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: habilitarCampos");
        assertEquals(false, view.jBvendaExcluir.isEnabled());
        assertEquals(false, view.jBimprimir.isEnabled());
        assertEquals(true, view.jBvendaFinaliza.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());
        assertEquals(true, view.jTitensSelecionados.isEnabled());
        assertEquals(true, view.jTlistaDeProdutos.isEnabled());
        assertEquals(true, view.jTlistaDeServicos.isEnabled());
        assertEquals(true, view.jTpesquisaNomeProduto.isEnabled());
        assertEquals(true, view.jTpesquisaNomeServico.isEnabled());

    }

    @Test
    public void testHabilitarCamposDeExcluirItemAdicionado() {

        // Etapa 01 - Nenhum item foi selecionado ainda
        vendaIniciaMetodosComum.habilitarCamposDeExcluirItemAdicionado();

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: habilitarCamposDeExcluirItemAdicionado etapa 01");
        assertEquals(false, view.jBremoveItemSelecionadoLista.isEnabled());

        /* simula seleção de item de produto */
        vendaModeloItemListCompartilhado.clear();
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdProduto(produtoModelo.getId());
        vendaModeloItemCompartilhado.setValor(produtoModelo.getValorVenda());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        /* adiciona produto a lista */
        vendaIniciaMetodosComum.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
        ((DefaultTableModel) view.jTitensSelecionados.getModel()).setRowCount(0);
        vendaIniciaMetodosProdutos.pesquisarProdutos();

        /* seleciona um produto na jtable */
        if (view.jTlistaDeProdutos.getRowCount() > 0) {
            view.jTlistaDeProdutos.setRowSelectionInterval(0, 0);
        }

        /* informa a quantidade e adiciona a lista */
        view.jTitemQuantidade.setText("1");
        vendaIniciaMetodosComum.addItemNaLista();

        /* seleciona o primeiro item da lista */
        if (view.jTitensSelecionados.getRowCount() > 0) {
            view.jTitensSelecionados.setRowSelectionInterval(0, 0);
        }

        // Etapa 02 - Agora um item foi selecionado na lista
        vendaIniciaMetodosComum.habilitarCamposDeExcluirItemAdicionado();

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: habilitarCamposDeExcluirItemAdicionado etapa 02");
        assertEquals(true, view.jBremoveItemSelecionadoLista.isEnabled());

    }

    @Test
    public void testAddItemNaLista() {

        /* simula seleção de item de produto */
        vendaModeloItemListCompartilhado.clear();
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdProduto(produtoModelo.getId());
        vendaModeloItemCompartilhado.setValor(produtoModelo.getValorVenda());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        // Etapa 01 - Adiciona produto a lista
        vendaIniciaMetodosComum.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
        ((DefaultTableModel) view.jTitensSelecionados.getModel()).setRowCount(0);
        vendaIniciaMetodosProdutos.pesquisarProdutos();

        if (view.jTlistaDeProdutos.getRowCount() > 0) {
            view.jTlistaDeProdutos.setRowSelectionInterval(0, 0);
        }

        view.jTitemQuantidade.setText("1");
        vendaIniciaMetodosComum.addItemNaLista();
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: addItemNaLista etapa 01");
        assertEquals(true, view.jTitensSelecionados.getRowCount() == 1);
        assertEquals("R$ 100,00", view.jTvendaValorTotal.getText());

        /* simula seleção de item de serviço */
        vendaModeloItemListCompartilhado.clear();
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdServico(servicoModelo.getId());
        vendaModeloItemCompartilhado.setValor(servicoModelo.getValor());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        // Etapa 02 - Adiciona serviço a lista
        vendaIniciaMetodosComum.setVendaIniciaMetodosServicos(vendaIniciaMetodosServicos);
        ((DefaultTableModel) view.jTitensSelecionados.getModel()).setRowCount(0);
        vendaIniciaMetodosServicos.pesquisarServicos();

        if (view.jTlistaDeServicos.getRowCount() > 0) {
            view.jTlistaDeServicos.setRowSelectionInterval(0, 0);
        }

        view.jTitemQuantidade.setText("1");
        vendaIniciaMetodosComum.addItemNaLista();
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: addItemNaLista etapa 02");
        System.out.println(view.jTvendaValorTotal.getText());
        assertEquals(true, view.jTitensSelecionados.getRowCount() == 1);
        assertEquals("R$ 19,90", view.jTvendaValorTotal.getText());

    }

    @Test
    public void testRemoverItemNaLista() {

        // Etapa 01 - Limpa a lista antiga e adiciona um item de venda
        vendaModeloItemListCompartilhado.clear();
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdProduto(produtoModelo.getId());
        vendaModeloItemCompartilhado.setValor(produtoModelo.getValorVenda());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        // Etapa 02 - Adiciona item a lista
        vendaIniciaMetodosComum.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
        ((DefaultTableModel) view.jTitensSelecionados.getModel()).setRowCount(0);
        vendaIniciaMetodosProdutos.pesquisarProdutos();

        // Etapa 03 - Simula que está adicionando um produto a lista
        if (view.jTlistaDeProdutos.getRowCount() > 0) {
            view.jTlistaDeProdutos.setRowSelectionInterval(0, 0);
        }

        view.jTitemQuantidade.setText("1");
        vendaIniciaMetodosComum.addItemNaLista();

        // Etapa 04 - Seleciona o primeiro item que foi adicionado a lista de itens
        if (view.jTitensSelecionados.getRowCount() > 0) {
            view.jTitensSelecionados.setRowSelectionInterval(0, 0);
        }

        // Etapa 05 - Agora simula que está removendo o item selecionado da lista
        vendaIniciaMetodosComum.removerItemNaLista();
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: removerItemNaLista");
        assertEquals(true, vendaModeloItemListCompartilhado.isEmpty());

    }

    @Test
    public void testCalcularItemSelecionado() {

        /* Etapa 01 - simula seleção de item de produto */
        vendaModeloItemListCompartilhado.clear();
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdProduto(produtoModelo.getId());
        vendaModeloItemCompartilhado.setValor(produtoModelo.getValorVenda());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        // Etapa 02 - Adiciona quantidade e retorna o calculo
        view.jTitemQuantidade.setText("3");
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: calcularItemSelecionado etapa 01");
        assertEquals(0, vendaIniciaMetodosComum.calcularItemSelecionado(vendaModeloItemCompartilhado).compareTo(new BigDecimal("300")));

        /* Etapa 03 - simula seleção de item de servico */
        vendaModeloItemListCompartilhado.clear();
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdServico(servicoModelo.getId());
        vendaModeloItemCompartilhado.setValor(servicoModelo.getValor());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        // Etapa 04 - Adiciona quantidade e retorna o calculo
        view.jTitemQuantidade.setText("3");
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: calcularItemSelecionado etapa 02");
        assertEquals(0, vendaIniciaMetodosComum.calcularItemSelecionado(vendaModeloItemCompartilhado).compareTo(new BigDecimal("59.70")));

    }

    @Test
    public void testAbrirCadastro() {

        // 1 - Simula cadastro de produto
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdProduto(produtoModelo.getId());
        vendaModeloItemCompartilhado.setValor(produtoModelo.getValorVenda());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        /**
         * 2 - Simula cadastro de venda (OBS: não preciso usar repository para
         * salvar porque está usando o cascade)
         */
        VendaModelo vendaModelo = new VendaModelo();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemListCompartilhado);
        vendaModelo.setFormaPagamento("Credito");

        // 3 - Abre o cadastro
        vendaIniciaMetodosComum.abrirCadastro(vendaModelo.getId());
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: abrirCadastro");
        System.out.println(vendaModeloItemListCompartilhado.size());
        assertEquals(true, vendaModeloItemListCompartilhado.size() == 1);

    }

    @Test
    public void testFinalizarVenda() {

        /**
         * Tive que usar esta forma de teste porque para finalizar a venda
         * depende de outro formulário do qual não tenho acesso aos métodos
         *
         */
        boolean isErro = false;

        try {

            vendaIniciaMetodosComum.finalizarVenda();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaMetodosComum metodo: finalizarVenda");
        assertEquals(false, isErro);

    }

    @Test
    public void testExcluir() {

        // 1 - Simula cadastro de produto
        vendaModeloItemCompartilhado = new VendaModeloItem();
        vendaModeloItemCompartilhado.setIdProduto(produtoModelo.getId());
        vendaModeloItemCompartilhado.setValor(produtoModelo.getValorVenda());
        vendaModeloItemCompartilhado.setQuantidade(new BigDecimal(1));
        vendaModeloItemListCompartilhado.add(vendaModeloItemCompartilhado);

        /**
         * Simula cadastro de venda (OBS: não preciso usar repository para
         * salvar porque está usando o cascade)
         */
        VendaModelo vendaModelo = new VendaModelo();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemListCompartilhado);
        vendaModelo.setFormaPagamento("Credito");

        // 3 - Abre o cadastro
        vendaIniciaMetodosComum.abrirCadastro(vendaModelo.getId());

        // 4 - Exclui o registro aberto
        vendaIniciaMetodosComum.excluir();
        System.out.println("Testando classe VendaIniciaMetodosComum metodo: excluir");
        vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(vendaModelo.getId());
        assertEquals(0, vendaModelo.getId());

    }

}

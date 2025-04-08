package br.com.salomaotech.genesys.controller.venda.venda_inicia.produto;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class VendaIniciaMetodosProdutosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos = new VendaIniciaMetodosProdutos(view);

    public VendaIniciaMetodosProdutosTest() {

        /* remove cadastros antigos */
        new Repository(new ProdutoModelo()).deleteTodos();

        /* simula novos cadastros */
        for (int i = 1; i <= 3; i++) {

            produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Mouse " + i);
            produtoModelo.setValorVenda(new BigDecimal(25));
            produtoModelo.setDescricao("Teste descrição");
            produtoModelo.setCategoria("Teste categoria");
            produtoModelo.setQuantidade(new BigDecimal(10));
            produtoModelo.setPeso(new BigDecimal(10));
            new Repository(produtoModelo).save();

        }

        /* simula novos cadastros */
        for (int i = 1; i <= 4; i++) {

            produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Teclado " + i);
            produtoModelo.setValorVenda(new BigDecimal(15));
            produtoModelo.setDescricao("Teste descrição");
            produtoModelo.setCategoria("Teste categoria");
            produtoModelo.setQuantidade(new BigDecimal(10));
            produtoModelo.setPeso(new BigDecimal(10));
            new Repository(produtoModelo).save();

        }

        /* simula novos cadastros */
        produtoModelo = new ProdutoModelo();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(4.5));
        produtoModelo.setDescricao("Teste descrição");
        produtoModelo.setCategoria("Teste categoria");
        produtoModelo.setQuantidade(new BigDecimal(10));
        produtoModelo.setPeso(new BigDecimal(10));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testPesquisarProdutos() {

        // Etapa 01
        view.jTpesquisaNomeProduto.setText("Mouse");
        vendaIniciaMetodosProdutos.pesquisarProdutos();

        System.out.println("Testando a classe VendaIniciaMetodosProdutos método pesquisarProdutos etapa 01");
        assertEquals(true, view.jTlistaDeProdutos.getRowCount() == 3);

        // Etapa 02
        view.jTpesquisaNomeProduto.setText("Teclado");
        vendaIniciaMetodosProdutos.pesquisarProdutos();

        System.out.println("Testando a classe VendaIniciaMetodosProdutos método pesquisarProdutos etapa 02");
        assertEquals(true, view.jTlistaDeProdutos.getRowCount() == 4);

        // Etapa 03 - Pesquisa vazia
        view.jTpesquisaNomeProduto.setText("");
        vendaIniciaMetodosProdutos.pesquisarProdutos();
        assertTrue("Deveria retornar todos os produtos", view.jTlistaDeProdutos.getRowCount() >= 8);

    }

    @Test
    public void testExibirProdutoSelecionado() {

        // Etapa 01 - Produto válido
        vendaIniciaMetodosProdutos.exibirProdutoSelecionado(produtoModelo.getId());
        System.out.println("Testando a classe VendaIniciaMetodosProdutos método exibirProdutoSelecionado etapa 01");
        assertEquals(true, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(true, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());

        // Verifica se a imagem não foi carregada (OBS: imagem não adicionada) (verifica se o painel foi modificado)
        assertTrue(view.jPdadosPerfilFoto.getComponentCount() == 0);

        // Etapa 02 - Produto inválido (ID 0)
        vendaIniciaMetodosProdutos.exibirProdutoSelecionado(0);
        System.out.println("Testando a classe VendaIniciaMetodosProdutos método exibirProdutoSelecionado etapa 02");
        assertEquals(false, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());

        // Verifica se a imagem foi removida
        assertEquals("O painel da imagem deveria estar vazio", 0, view.jPdadosPerfilFoto.getComponentCount());

    }

    @Test
    public void testLimparProdutoSelecionado() {

        // Primeiro exibe um produto para depois limpar
        vendaIniciaMetodosProdutos.exibirProdutoSelecionado(produtoModelo.getId());
        vendaIniciaMetodosProdutos.limparProdutoSelecionado();

        System.out.println("Testando a classe VendaIniciaMetodosProdutos método limparProdutoSelecionado");
        assertEquals("", view.jTitemQuantidade.getText());
        assertEquals("", view.jTitemTotal.getText());
        assertEquals(-1, view.jTlistaDeProdutos.getSelectedRow());
        assertEquals(-1, view.jTlistaDeServicos.getSelectedRow());
        assertEquals(false, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals("O painel da imagem deveria estar vazio", 0, view.jPdadosPerfilFoto.getComponentCount());

    }

    @Test
    public void testAdicionarProdutoNaLista() {

        System.out.println("Testando a classe VendaIniciaMetodosProdutos método adicionarProdutoNaLista");
        vendaIniciaMetodosProdutos.adicionarProdutoNaLista();

        // Campos que devem estar limpos (ativados ou desativados)
        assertEquals("", view.jTitemQuantidade.getText());
        assertEquals("", view.jTitemTotal.getText());
        assertEquals(-1, view.jTlistaDeProdutos.getSelectedRow());
        assertEquals(-1, view.jTlistaDeServicos.getSelectedRow());
        assertEquals(false, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());

        // Campos onde o produto será adicionado
        assertEquals(false, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());

        /**
         * Já que nenhum item está selecionado, então o botão de remoção deve
         * estar desabilitado
         */
        assertEquals(false, view.jBremoveItemSelecionadoLista.isEnabled());

    }

    @Test
    public void testCalcularProdutoSelecionado() {

        view.jTitemQuantidade.setText("3");

        System.out.println("Testando a classe VendaIniciaMetodosProdutos método calcularProdutoSelecionado");
        assertEquals(new BigDecimal("13.50"), vendaIniciaMetodosProdutos.calcularProdutoSelecionado(produtoModelo.getId()));

        // Teste com quantidade zero
        view.jTitemQuantidade.setText("0");
        assertEquals(new BigDecimal("0.00"), vendaIniciaMetodosProdutos.calcularProdutoSelecionado(produtoModelo.getId()));

        // Teste com quantidade decimal
        view.jTitemQuantidade.setText("1.5");
        assertEquals(new BigDecimal("6.750"), vendaIniciaMetodosProdutos.calcularProdutoSelecionado(produtoModelo.getId()));

    }

    @Test
    public void testPopularGranel() {

        System.out.println("Testando a classe VendaIniciaMetodosProdutos método popularGranel");
        vendaIniciaMetodosProdutos.popularGranel(new BigDecimal(14.5), new ItemVenda(produtoModelo.getId(), new ProdutoModelo()));

        // Campos para adicionar item
        assertEquals(true, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(true, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());
        assertTrue("Total deveria ser R$ 65,25", view.jTitemTotal.getText().equals("R$ 65,25"));
        assertTrue("Quantidade deveria ser 14.5", view.jTitemQuantidade.getText().equals("14.5"));

        // Verifica se a imagem não foi carregada (já que não existe)
        assertTrue(view.jPdadosPerfilFoto.getComponentCount() == 0);

    }

}

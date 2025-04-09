package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaIniciaControllerTest {

    private final VendaIniciaController vendaIniciaController = new VendaIniciaController();
    private final VendaModelo vendaModelo = new VendaModelo();

    public VendaIniciaControllerTest() {

        /* remove vendas antigas */
        new Repository(new VendaModelo()).deleteTodos();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        ProdutoModelo produtoModelo = new ProdutoModelo();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(50));
        new Repository(produtoModelo).save();

        /* simula seleção de item de produto */
        List<VendaModeloItem> vendaModeloItemList = new ArrayList();
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setFormaPagamento("Credito");
        new Repository(vendaModelo).save();

    }

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            vendaIniciaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaController metodo: construir");
        assertEquals(false, isErro);

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        JFvendaInicia view = vendaIniciaController.abrirCadastro(vendaModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        assertEquals(true, view.jTitensSelecionados.getRowCount() > 0);
        assertEquals("R$ 100,00", view.jTvendaValorTotal.getText());
        assertEquals(true, view.jBvendaExcluir.isEnabled());
        assertEquals(true, view.jBimprimir.isEnabled());
        assertEquals(false, view.jBvendaFinaliza.isEnabled());
        assertEquals(false, view.jTitemQuantidade.isEnabled());
        assertEquals(false, view.jTitensSelecionados.isEnabled());
        assertEquals(false, view.jTlistaDeProdutos.isEnabled());
        assertEquals(false, view.jTlistaDeServicos.isEnabled());
        assertEquals(false, view.jTpesquisaNomeProduto.isEnabled());
        assertEquals(false, view.jTpesquisaNomeServico.isEnabled());

    }

}

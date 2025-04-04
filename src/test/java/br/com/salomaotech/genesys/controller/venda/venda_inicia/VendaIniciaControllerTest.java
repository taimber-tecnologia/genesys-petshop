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
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaIniciaControllerTest {

    private final VendaIniciaController vendaIniciaController = new VendaIniciaController();

    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();

    public VendaIniciaControllerTest() {

        /* remove vendas antigas */
        new Repository(new VendaModelo()).deleteTodos();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(50));
        new Repository(produtoModelo).save();

        /* simula seleção de item de produto */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setDesconto(new BigDecimal(5));
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setFormaPagamento("Debito");
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

        JFvendaInicia view = vendaIniciaController.abrirCadastro(vendaModelo.getId());

        System.out.println("Testando classe VendaIniciaController metodo: abrirCadastro");
        assertEquals(true, view.getId() == vendaModelo.getId());
        assertEquals(true, view.jTprodutoSelecionado.getRowCount() == vendaModeloItemList.size());
        assertEquals(true, view.jBvendaExcluir.isVisible());

    }

}

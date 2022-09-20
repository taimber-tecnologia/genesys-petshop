package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaConcluiValidadorTest {

    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private VendaConcluiValidador vendaConcluiValidador;

    public VendaConcluiValidadorTest() {

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        ProdutoModelo produtoModelo = new ProdutoModelo();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(50));
        new Repository(produtoModelo).save();

        /* simula seleção de item */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

    }

    @Test
    public void testIsValido() {

        /* testa uma venda com valor igual a zero */
        VendaModelo vendaModelo = new VendaModelo();
        vendaConcluiValidador = new VendaConcluiValidador(vendaModelo);
        System.out.println("Testando classe metodo: VendaConcluiValidadorisValido etapa 01");
        assertEquals(false, vendaConcluiValidador.isValido());

        /* testa uma venda com valor diferente de zero */
        vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaConcluiValidador = new VendaConcluiValidador(vendaModelo);
        System.out.println("Testando classe metodo: VendaConcluiValidadorisValido etapa 02");
        assertEquals(true, vendaConcluiValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* testa uma venda com valor igual a zero */
        VendaModelo vendaModelo = new VendaModelo();
        vendaConcluiValidador = new VendaConcluiValidador(vendaModelo);
        System.out.println("Testando classe metodo: VendaConcluiValidadorgetMensagensErro etapa 01");
        vendaConcluiValidador.isValido();
        assertEquals(true, vendaConcluiValidador.getMensagensErro().length() > 0);

        /* testa uma venda com valor diferente de zero */
        vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaConcluiValidador = new VendaConcluiValidador(vendaModelo);
        System.out.println("Testando classe metodo: VendaConcluiValidadorgetMensagensErro etapa 02");
        vendaConcluiValidador.isValido();
        assertEquals(true, vendaConcluiValidador.getMensagensErro().length() == 0);

    }

}

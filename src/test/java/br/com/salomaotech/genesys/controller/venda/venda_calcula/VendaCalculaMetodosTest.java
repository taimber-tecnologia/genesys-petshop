package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaMetodosTest {

    public VendaCalculaMetodosTest() {

    }

    @Test
    public void testSetProdutoModelo() {

        System.out.println("setProdutoModelo");
        ProdutoModelo produtoModelo = null;
        VendaCalculaMetodos instance = null;
        instance.setProdutoModelo(produtoModelo);

    }

    @Test
    public void testSetVendaIniciaMetodos() {

        System.out.println("setVendaIniciaMetodos");
        VendaIniciaMetodos vendaIniciaMetodos = null;
        VendaCalculaMetodos instance = null;
        instance.setVendaIniciaMetodos(vendaIniciaMetodos);

    }

    @Test
    public void testCalcular() {

        System.out.println("calcular");
        int acao = 0;
        VendaCalculaMetodos instance = null;
        instance.calcular(acao);

    }

}

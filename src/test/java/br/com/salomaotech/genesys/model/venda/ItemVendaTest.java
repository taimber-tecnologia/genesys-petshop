package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemVendaTest {

    ProdutoModelo produtoModelo = new ProdutoModelo();
    ServicoModelo servicoModelo = new ServicoModelo();
    private ItemVenda itemVenda = new ItemVenda();

    public ItemVendaTest() {

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste A");
        produtoModelo.setDescricao("Teste ABC");
        produtoModelo.setValorVenda(new BigDecimal(140));
        produtoModelo.setQuantidade(new BigDecimal(50));
        produtoModelo.setPeso(new BigDecimal(15));
        new Repository(produtoModelo).save();

        /* peso */
        new Repository(new ServicoModelo()).deleteTodos();
        servicoModelo.setNome("Teste B");
        servicoModelo.setDescricao("Teste ABC");
        servicoModelo.setValor(new BigDecimal(30));
        new Repository(servicoModelo).save();

    }

    @Test
    public void testGetId() {

        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);
        System.out.println("Testando classe ItemVenda metodo: getId etapa 01");
        assertEquals(true, itemVenda.getId() == produtoModelo.getId());

        itemVenda = new ItemVenda(servicoModelo.getId(), servicoModelo);
        System.out.println("Testando classe ItemVenda metodo: getId etapa 02");
        assertEquals(true, itemVenda.getId() == servicoModelo.getId());

    }

    @Test
    public void testGetProdutoModelo() {

        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);
        System.out.println("Testando classe ItemVenda metodo: getProdutoModelo");
        assertEquals(true, itemVenda.getProdutoModelo() == produtoModelo);

    }

    @Test
    public void testGetServicoModelo() {

        itemVenda = new ItemVenda(servicoModelo.getId(), servicoModelo);
        System.out.println("Testando classe ItemVenda metodo: getServicoModelo");
        assertEquals(true, itemVenda.getServicoModelo() == servicoModelo);

    }

    @Test
    public void testGetPeso() {

        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);
        System.out.println("Testando classe ItemVenda metodo: getPeso etapa 01");
        assertEquals(true, itemVenda.getPeso().equals(produtoModelo.getPeso()));

        itemVenda = new ItemVenda(servicoModelo.getId(), servicoModelo);
        System.out.println("Testando classe ItemVenda metodo: getPeso etapa 02");
        assertEquals(true, itemVenda.getPeso().equals(new BigDecimal(0)));

    }

    @Test
    public void testGetValor() {

        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);
        System.out.println("Testando classe ItemVenda metodo: getValor etapa 01");
        assertEquals(true, itemVenda.getValor().equals(produtoModelo.getValorVenda()));

        itemVenda = new ItemVenda(servicoModelo.getId(), servicoModelo);
        System.out.println("Testando classe ItemVenda metodo: getValor etapa 02");
        assertEquals(true, itemVenda.getValor().equals(servicoModelo.getValor()));

    }

    @Test
    public void testGetNome() {

        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);
        System.out.println("Testando classe ItemVenda metodo: getNome etapa 01");
        assertEquals(true, itemVenda.getNome().equals(produtoModelo.getNome()));

        itemVenda = new ItemVenda(servicoModelo.getId(), servicoModelo);
        System.out.println("Testando classe ItemVenda metodo: getNome etapa 02");
        assertEquals(true, itemVenda.getNome().equals(servicoModelo.getNome()));

    }

}

package br.com.salomaotech.genesys.model.venda;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaModeloItemTest {

    private final VendaModeloItem vendaModeloItem = new VendaModeloItem();

    @Test
    public void testGetId() {

        System.out.println("Testando classe VendaModeloItem metodo: getId");
        assertEquals(true, vendaModeloItem.getId() == 0);

    }

    @Test
    public void testSetId() {

        long id = 1;
        vendaModeloItem.setId(id);

        System.out.println("Testando classe VendaModeloItem metodo: setId");
        assertEquals(true, vendaModeloItem.getId() == id);

    }

    @Test
    public void testGetIdProduto() {

        System.out.println("Testando classe VendaModeloItem metodo: getIdProduto");
        assertEquals(true, vendaModeloItem.getIdProduto() == 0);

    }

    @Test
    public void testSetIdProduto() {

        long idProduto = 1;
        vendaModeloItem.setIdProduto(idProduto);

        System.out.println("Testando classe VendaModeloItem metodo: setIdProduto");
        assertEquals(true, vendaModeloItem.getIdProduto() == idProduto);

    }

    @Test
    public void testGetValor() {

        System.out.println("Testando classe VendaModeloItem metodo: getValor");
        assertEquals(true, vendaModeloItem.getValor().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetValor() {

        BigDecimal valor = new BigDecimal(99.99);
        vendaModeloItem.setValor(valor);

        System.out.println("Testando classe VendaModeloItem metodo: setValor");
        assertEquals(true, vendaModeloItem.getValor().equals(valor));

    }

    @Test
    public void testGetQuantidade() {

        System.out.println("Testando classe VendaModeloItem metodo: getQuantidade");
        assertEquals(true, vendaModeloItem.getQuantidade().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetQuantidade() {

        BigDecimal quantidade = new BigDecimal(2);
        vendaModeloItem.setQuantidade(quantidade);

        System.out.println("Testando classe VendaModeloItem metodo: setQuantidade");
        assertEquals(true, vendaModeloItem.getQuantidade().equals(quantidade));

    }

    @Test
    public void testGetDesconto() {

        System.out.println("Testando classe VendaModeloItem metodo: getDesconto");
        assertEquals(true, vendaModeloItem.getDesconto().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetDesconto() {

        BigDecimal desconto = new BigDecimal(2.3);
        vendaModeloItem.setDesconto(desconto);

        System.out.println("Testando classe VendaModeloItem metodo: setDesconto");
        assertEquals(true, vendaModeloItem.getDesconto().equals(desconto));

    }

}

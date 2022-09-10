package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import java.math.BigDecimal;
import static java.util.Objects.isNull;
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

        vendaModeloItem.setId(1);
        System.out.println("Testando classe VendaModeloItem metodo: setId");
        assertEquals(true, vendaModeloItem.getId() == 1);

    }

    @Test
    public void testGetDescricao() {

        System.out.println("Testando classe VendaModeloItem metodo: getDescricao");
        assertEquals(true, isNull(vendaModeloItem.getDescricao()));

    }

    @Test
    public void testSetDescricao() {

        vendaModeloItem.setDescricao("Teste");
        System.out.println("Testando classe VendaModeloItem metodo: setDescricao");
        assertEquals(true, vendaModeloItem.getDescricao().equals("Teste"));

    }

    @Test
    public void testGetValor() {

        System.out.println("Testando classe VendaModeloItem metodo: getValor");
        assertEquals(true, vendaModeloItem.getValor().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetValor() {

        vendaModeloItem.setValor(new BigDecimal(100));
        System.out.println("Testando classe VendaModeloItem metodo: setValor");
        assertEquals(true, vendaModeloItem.getValor().equals(new BigDecimal(100)));

    }

    @Test
    public void testGetIdProdutos() {

        System.out.println("Testando classe VendaModeloItem metodo: getIdProdutos");
        assertEquals(true, vendaModeloItem.getIdProduto() == 0);

    }

    @Test
    public void testSetIdProdutos() {

        vendaModeloItem.setIdProduto(1);
        System.out.println("Testando classe VendaModeloItem metodo: setIdProdutos");
        assertEquals(true, vendaModeloItem.getIdProduto() == 1);

    }

    @Test
    public void testGetQuantidade() {

        System.out.println("Testando classe VendaModeloItem metodo: getQuantidade");
        assertEquals(true, vendaModeloItem.getQuantidade().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetQuantidade() {

        vendaModeloItem.setQuantidade(new BigDecimal(100));
        System.out.println("Testando classe VendaModeloItem metodo: setQuantidade");
        assertEquals(true, vendaModeloItem.getQuantidade().equals(new BigDecimal(100)));

    }

    @Test
    public void testGetMedida() {

        System.out.println("Testando classe VendaModeloItem metodo: getMedida");
        assertEquals(true, isNull(vendaModeloItem.getMedida()));

    }

    @Test
    public void testSetMedida() {

        String medida = "Teste";
        vendaModeloItem.setMedida(medida);
        System.out.println("Testando classe VendaModeloItem metodo: setMedida");
        assertEquals(true, vendaModeloItem.getMedida().equals(medida));

    }

}

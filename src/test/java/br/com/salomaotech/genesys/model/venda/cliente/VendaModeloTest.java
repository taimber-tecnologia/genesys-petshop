package br.com.salomaotech.genesys.model.venda.cliente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaModeloTest {

    private VendaModelo vendaModelo = new VendaModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe VendaModelo metodo: getId");
        assertEquals(true, vendaModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        vendaModelo.setId(1);
        System.out.println("Testando classe VendaModelo metodo: setId");
        assertEquals(true, vendaModelo.getId() == 1);

    }

    @Test
    public void testGetData() {

        System.out.println("Testando classe VendaModelo metodo: getData");
        assertEquals(true, isNull(vendaModelo.getData()));

    }

    @Test
    public void testSetData() {

        Calendar data = Calendar.getInstance();
        vendaModelo.setData(data);
        System.out.println("Testando classe VendaModelo metodo: setData");
        assertEquals(true, vendaModelo.getData().equals(data));

    }

    @Test
    public void testGetIdCliente() {

        System.out.println("Testando classe VendaModelo metodo: getIdCliente");
        assertEquals(true, vendaModelo.getIdCliente() == 0);

    }

    @Test
    public void testSetIdCliente() {

        vendaModelo.setIdCliente(1);
        System.out.println("Testando classe VendaModelo metodo: setIdCliente");
        assertEquals(true, vendaModelo.getIdCliente() == 1);

    }

    @Test
    public void testGetIdAnimal() {

        System.out.println("Testando classe VendaModelo metodo: getIdAnimal");
        assertEquals(true, vendaModelo.getIdAnimal() == 0);

    }

    @Test
    public void testSetIdAnimal() {

        vendaModelo.setIdAnimal(1);
        System.out.println("Testando classe VendaModelo metodo: setIdAnimal");
        assertEquals(true, vendaModelo.getIdAnimal() == 1);

    }

    @Test
    public void testGetVendaModeloItemList() {

        System.out.println("Testando classe VendaModelo metodo: getVendaModeloItemList");
        assertEquals(true, vendaModelo.getVendaModeloItemList().isEmpty());

    }

    @Test
    public void testSetVendaModeloItemList() {

        List<VendaModeloItem> vendaModeloItem = new ArrayList();
        vendaModeloItem.add(new VendaModeloItem());
        vendaModeloItem.add(new VendaModeloItem());
        vendaModeloItem.add(new VendaModeloItem());
        vendaModelo.setVendaModeloItemList(vendaModeloItem);

        System.out.println("Testando classe VendaModelo metodo: setVendaModeloItemList");
        assertEquals(true, vendaModelo.getVendaModeloItemList().equals(vendaModeloItem));

    }

    @Test
    public void testGetDesconto() {

        System.out.println("Testando classe VendaModelo metodo: getDesconto");
        assertEquals(true, vendaModelo.getDesconto().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetDesconto() {

        BigDecimal desconto = new BigDecimal(5);
        vendaModelo.setDesconto(desconto);
        System.out.println("Testando classe VendaModelo metodo: setDesconto");
        assertEquals(true, vendaModelo.getDesconto().equals(desconto));

    }

    @Test
    public void testGetFormaPagamento() {

        System.out.println("Testando classe VendaModelo metodo: getFormaPagamento");
        assertEquals(true, isNull(vendaModelo.getFormaPagamento()));

    }

    @Test
    public void testSetFormaPagamento() {

        String formaPagamento = "Pix";
        vendaModelo.setFormaPagamento(formaPagamento);
        System.out.println("Testando classe VendaModelo metodo: setFormaPagamento");
        assertEquals(true, vendaModelo.getFormaPagamento().equals(formaPagamento));

    }

    @Test
    public void testGetJuros() {

        System.out.println("Testando classe VendaModelo metodo: getJuros");
        assertEquals(true, vendaModelo.getJuros().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetJuros() {

        BigDecimal juros = new BigDecimal(3);
        vendaModelo.setJuros(juros);
        System.out.println("Testando classe VendaModelo metodo: setJuros");
        assertEquals(true, vendaModelo.getJuros().equals(juros));

    }

    @Test
    public void testIsFinalizado() {

        System.out.println("Testando classe VendaModelo metodo: isFinalizado");
        assertEquals(false, vendaModelo.isFinalizado());

    }

    @Test
    public void testSetFinalizado() {

        vendaModelo.setFinalizado(true);
        System.out.println("Testando classe VendaModelo metodo: setFinalizado");
        assertEquals(true, vendaModelo.isFinalizado());

    }

    @Test
    public void testGetNumeroParcelas() {

        System.out.println("Testando classe VendaModelo metodo: getNumeroParcelas");
        assertEquals(true, vendaModelo.getNumeroParcelas() == 0);

    }

    @Test
    public void testSetNumeroParcelas() {

        int numeroParcelas = 10;
        vendaModelo.setNumeroParcelas(numeroParcelas);
        System.out.println("Testando classe VendaModelo metodo: setNumeroParcelas");
        assertEquals(true, vendaModelo.getNumeroParcelas() == numeroParcelas);

    }

    @Test
    public void testGetIdCentroCusto() {

        System.out.println("Testando classe VendaModelo metodo: getIdCentroCusto");
        assertEquals(true, vendaModelo.getIdCentroCusto() == 0);

    }

    @Test
    public void testSetIdCentroCusto() {

        vendaModelo.setIdCentroCusto(1);
        System.out.println("Testando classe VendaModelo metodo: setIdCentroCusto");
        assertEquals(true, vendaModelo.getIdCentroCusto() == 1);

    }

    @Test
    public void testGetValor() {

        /* esperado 0 porque não foi populado nenhum item de venda */
        System.out.println("Testando classe VendaModelo metodo: getValor etapa 01");
        assertEquals(true, vendaModelo.getValor().equals(new BigDecimal(0)));

        /* item de venda */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setDescricao("Teste");
        vendaModeloItem.setValor(new BigDecimal(100));
        vendaModeloItem.setQuantidade(new BigDecimal(1));

        /* lista de itens de venda */
        List<VendaModeloItem> vendaModeloItemList = new ArrayList();
        vendaModeloItemList.add(vendaModeloItem);
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);

        /* esperado 100 que é o valor do item de venda populado */
        System.out.println("Testando classe VendaModelo metodo: getValor etapa 02");
        assertEquals(true, vendaModelo.getValor().equals(vendaModeloItem.getValor()));

    }

    @Test
    public void testGetValorTotal() {

        /* item de venda */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setDescricao("Teste");
        vendaModeloItem.setValor(new BigDecimal(100));
        vendaModeloItem.setQuantidade(new BigDecimal(1));

        /* adiciona a lista */
        List<VendaModeloItem> vendaModeloItemList = new ArrayList();
        vendaModeloItemList.add(vendaModeloItem);

        /* usando filtro: sem desconto */
        vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        System.out.println("Testando classe VendaModelo metodo: getValorTotal etapa 01");
        assertEquals(true, vendaModelo.getValorTotal().equals(new BigDecimal(100)));

        /* usando filtro: com desconto */
        vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setDesconto(new BigDecimal(5));
        System.out.println("Testando classe VendaModelo metodo: getValorTotal etapa 02");
        assertEquals(true, vendaModelo.getValorTotal().equals(new BigDecimal(95)));

        /* usando filtro: adiciona juros */
        vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setJuros(new BigDecimal(12));
        System.out.println("Testando classe VendaModelo metodo: getValorTotal etapa 03");
        assertEquals(true, vendaModelo.getValorTotal().equals(new BigDecimal(112)));

        /* usando filtro: adiciona juros, adiciona desconto */
        vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setJuros(new BigDecimal(12));
        vendaModelo.setDesconto(new BigDecimal(4.5));
        System.out.println("Testando classe VendaModelo metodo: getValorTotal etapa 04");
        assertEquals(true, vendaModelo.getValorTotal().equals(new BigDecimal("106.96")));

    }

}

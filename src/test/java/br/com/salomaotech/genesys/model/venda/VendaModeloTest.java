package br.com.salomaotech.genesys.model.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaModeloTest {

    private final VendaModelo vendaModelo = new VendaModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe VendaModelo metodo: getId");
        assertEquals(true, vendaModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        long id = 1;
        vendaModelo.setId(id);

        System.out.println("Testando classe VendaModelo metodo: setId");
        assertEquals(true, vendaModelo.getId() == id);

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
    public void testSetHora() {

        String hora = "11:17";
        vendaModelo.setHora(hora);

        System.out.println("Testando classe VendaModelo metodo: setHora");
        assertEquals(true, vendaModelo.getHora().equals(hora));

    }

    @Test
    public void testGetHora() {

        System.out.println("Testando classe VendaModelo metodo: getHora");
        assertEquals(true, isNull(vendaModelo.getHora()));

    }

    @Test
    public void testGetVendaModeloItemList() {

        System.out.println("Testando classe VendaModelo metodo: getVendaModeloItemList");
        assertEquals(true, vendaModelo.getVendaModeloItemList().isEmpty());

    }

    @Test
    public void testSetVendaModeloItemList() {

        List<VendaModeloItem> vendaModeloItemList = new ArrayList();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);

        System.out.println("Testando classe VendaModelo metodo: setVendaModeloItemList");
        assertEquals(true, vendaModelo.getVendaModeloItemList().equals(vendaModeloItemList));

    }

    @Test
    public void testGetFormaPagamento() {

        System.out.println("Testando classe VendaModelo metodo: getFormaPagamento");
        assertEquals(true, isNull(vendaModelo.getFormaPagamento()));

    }

    @Test
    public void testSetFormaPagamento() {

        String formaPagamento = "Debito";
        vendaModelo.setFormaPagamento(formaPagamento);

        System.out.println("Testando classe VendaModelo metodo: setFormaPagamento");
        assertEquals(true, vendaModelo.getFormaPagamento().equals(formaPagamento));

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
    public void testIsIsPago() {

        System.out.println("Testando classe VendaModelo metodo: isIsPago");
        assertEquals(false, vendaModelo.isIsPago());

    }

    @Test
    public void testSetIsPago() {

        boolean isPago = true;
        vendaModelo.setIsPago(isPago);

        System.out.println("Testando classe VendaModelo metodo: setIsPago");
        assertEquals(true, vendaModelo.isIsPago());

    }

    @Test
    public void testGetRevisoes() {

        System.out.println("Testando classe VendaModelo metodo: getRevisoes");
        assertEquals(true, vendaModelo.getRevisoes() == 0);

    }

    @Test
    public void testSetRevisoes() {

        int revisoes = 5;
        vendaModelo.setRevisoes(revisoes);

        System.out.println("Testando classe VendaModelo metodo: setRevisoes");
        assertEquals(true, vendaModelo.getRevisoes() == revisoes);

    }

    @Test
    public void testGetValor() {

        System.out.println("Testando classe VendaModelo metodo: getValor");
        assertEquals(true, vendaModelo.getValor().equals(new BigDecimal(0)));

    }

}

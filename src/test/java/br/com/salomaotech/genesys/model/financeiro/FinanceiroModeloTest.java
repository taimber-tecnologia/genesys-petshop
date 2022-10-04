package br.com.salomaotech.genesys.model.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinanceiroModeloTest {

    FinanceiroModelo financeiroModelo = new FinanceiroModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe FinanceiroModelo metodo: getId");
        assertEquals(true, financeiroModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        financeiroModelo.setId(1);
        System.out.println("Testando classe FinanceiroModelo metodo: setId");
        assertEquals(true, financeiroModelo.getId() == 1);

    }

    @Test
    public void testGetData() {

        System.out.println("Testando classe FinanceiroModelo metodo: getData");
        assertEquals(true, isNull(financeiroModelo.getData()));

    }

    @Test
    public void testSetData() {

        Calendar data = Calendar.getInstance();
        financeiroModelo.setData(data);

        System.out.println("Testando classe FinanceiroModelo metodo: setData");
        assertEquals(true, financeiroModelo.getData().equals(data));

    }

    @Test
    public void testGetValor() {

        System.out.println("Testando classe FinanceiroModelo metodo: getValor");
        assertEquals(true, financeiroModelo.getValor().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetValor() {

        BigDecimal valor = new BigDecimal(100);
        financeiroModelo.setValor(valor);

        System.out.println("Testando classe FinanceiroModelo metodo: setValor");
        assertEquals(true, financeiroModelo.getValor().equals(valor));

    }

    @Test
    public void testGetDescricao() {

        System.out.println("Testando classe FinanceiroModelo metodo: getDescricao");
        assertEquals(true, isNull(financeiroModelo.getDescricao()));

    }

    @Test
    public void testSetDescricao() {

        String descricao = "Teste";
        financeiroModelo.setDescricao(descricao);

        System.out.println("Testando classe FinanceiroModelo metodo: setDescricao");
        assertEquals(true, financeiroModelo.getDescricao().equals(descricao));

    }

    @Test
    public void testGetIdCentroCusto() {

        System.out.println("Testando classe FinanceiroModelo metodo: getIdCentroCusto");
        assertEquals(true, financeiroModelo.getIdCentroCusto() == 0);

    }

    @Test
    public void testSetIdCentroCusto() {

        long idCentroCusto = 1;
        financeiroModelo.setIdCentroCusto(idCentroCusto);

        System.out.println("Testando classe FinanceiroModelo metodo: setIdCentroCusto");
        assertEquals(true, financeiroModelo.getIdCentroCusto() == idCentroCusto);

    }

    @Test
    public void testIsIsDespesa() {

        System.out.println("Testando classe FinanceiroModelo metodo: isIsDespesa");
        assertEquals(false, financeiroModelo.isIsDespesa());

    }

    @Test
    public void testSetIsDespesa() {

        financeiroModelo.setIsDespesa(true);

        System.out.println("Testando classe FinanceiroModelo metodo: setIsDespesa");
        assertEquals(true, financeiroModelo.isIsDespesa());

    }

    @Test
    public void testIsIsPago() {

        System.out.println("Testando classe FinanceiroModelo metodo: isIsPago");
        assertEquals(false, financeiroModelo.isIsPago());

    }

    @Test
    public void testSetIsPago() {

        financeiroModelo.setIsPago(true);

        System.out.println("Testando classe FinanceiroModelo metodo: setIsPago");
        assertEquals(true, financeiroModelo.isIsPago());

    }

    @Test
    public void testGetIdVenda() {

        System.out.println("Testando classe FinanceiroModelo metodo: getIdVenda");
        assertEquals(true, financeiroModelo.getIdVenda() == 0);

    }

    @Test
    public void testSetIdVenda() {

        financeiroModelo.setIdVenda(1);

        System.out.println("Testando classe FinanceiroModelo metodo: setIdVenda");
        assertEquals(true, financeiroModelo.getIdVenda() == 1);

    }

    @Test
    public void testGetCpfCliente() {

        System.out.println("Testando classe FinanceiroModelo metodo: getIdCliente");
        assertEquals(true, isNull(financeiroModelo.getCpfCliente()));

    }

    @Test
    public void testSetCpfCliente() {

        financeiroModelo.setCpfCliente("000.000.000-00");

        System.out.println("Testando classe FinanceiroModelo metodo: setIdCliente");
        assertEquals(true, financeiroModelo.getCpfCliente().equals("000.000.000-00"));

    }

}

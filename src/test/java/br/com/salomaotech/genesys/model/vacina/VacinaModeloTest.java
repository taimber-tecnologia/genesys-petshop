package br.com.salomaotech.genesys.model.vacina;

import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacinaModeloTest {

    private final Calendar calendar = Calendar.getInstance();
    private final VacinaModelo vacinaModelo = new VacinaModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe VacinaModelo metodo: getId");
        assertEquals(true, vacinaModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        vacinaModelo.setId(1);
        System.out.println("Testando classe VacinaModelo metodo: setId");
        assertEquals(true, vacinaModelo.getId() == 1);

    }

    @Test
    public void testGetNome() {

        System.out.println("Testando classe VacinaModelo metodo: getNome");
        assertEquals(true, isNull(vacinaModelo.getNome()));

    }

    @Test
    public void testSetNome() {

        vacinaModelo.setNome("Teste");
        System.out.println("Testando classe VacinaModelo metodo: setNome");
        assertEquals(true, vacinaModelo.getNome().equals("Teste"));

    }

    @Test
    public void testGetIdCliente() {

        System.out.println("Testando classe VacinaModelo metodo: getIdCliente");
        assertEquals(true, vacinaModelo.getIdCliente() == 0);

    }

    @Test
    public void testSetIdCliente() {

        vacinaModelo.setIdCliente(1);
        System.out.println("Testando classe VacinaModelo metodo: setIdCliente");
        assertEquals(true, vacinaModelo.getIdCliente() == 1);

    }

    @Test
    public void testGetIdAnimal() {

        System.out.println("Testando classe VacinaModelo metodo: getIdAnimal");
        assertEquals(true, vacinaModelo.getIdAnimal() == 0);

    }

    @Test
    public void testSetIdAnimal() {

        vacinaModelo.setIdAnimal(1);
        System.out.println("Testando classe VacinaModelo metodo: setIdAnimal");
        assertEquals(true, vacinaModelo.getIdAnimal() == 1);

    }

    @Test
    public void testGetDataAplicacao() {

        System.out.println("Testando classe VacinaModelo metodo: getDataAplicacao");
        assertEquals(true, isNull(vacinaModelo.getDataAplicacao()));

    }

    @Test
    public void testSetDataAplicacao() {

        vacinaModelo.setDataAplicacao(calendar);
        System.out.println("Testando classe VacinaModelo metodo: setDataAplicacao");
        assertEquals(true, vacinaModelo.getDataAplicacao().equals(calendar));

    }

    @Test
    public void testGetDataAplicacaoProxima() {

        System.out.println("Testando classe VacinaModelo metodo: getDataAplicacaoProxima");
        assertEquals(true, isNull(vacinaModelo.getDataAplicacaoProxima()));

    }

    @Test
    public void testSetDataAplicacaoProxima() {

        vacinaModelo.setDataAplicacaoProxima(calendar);
        System.out.println("Testando classe VacinaModelo metodo: setDataAplicacaoProxima");
        assertEquals(true, vacinaModelo.getDataAplicacaoProxima().equals(calendar));

    }

    @Test
    public void testGetDoses() {

        System.out.println("Testando classe VacinaModelo metodo: getDoses");
        assertEquals(true, isNull(vacinaModelo.getDoses()));

    }

    @Test
    public void testSetDoses() {

        vacinaModelo.setDoses("1");
        System.out.println("Testando classe VacinaModelo metodo: setDoses");
        assertEquals(true, vacinaModelo.getDoses().equals("1"));

    }

    @Test
    public void testGetObservacoes() {

        System.out.println("Testando classe VacinaModelo metodo: getObservacoes");
        assertEquals(true, isNull(vacinaModelo.getObservacoes()));

    }

    @Test
    public void testSetObservacoes() {

        vacinaModelo.setObservacoes("Teste");
        System.out.println("Testando classe VacinaModelo metodo: setObservacoes");
        assertEquals(true, vacinaModelo.getObservacoes().equals("Teste"));

    }

}

package br.com.salomaotech.genesys.model.servico;

import java.math.BigDecimal;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoModeloTest {

    private final ServicoModelo servicoModelo = new ServicoModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe ServicoModelo metodo: getId");
        assertEquals(true, servicoModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        servicoModelo.setId(1);
        System.out.println("Testando classe ServicoModelo metodo: setId");
        assertEquals(true, servicoModelo.getId() == 1);

    }

    @Test
    public void testGetNome() {

        System.out.println("Testando classe ServicoModelo metodo: getNome");
        assertEquals(true, isNull(servicoModelo.getNome()));

    }

    @Test
    public void testSetNome() {

        servicoModelo.setNome("Teste");
        System.out.println("Testando classe ServicoModelo metodo: setNome");
        assertEquals(true, servicoModelo.getNome().equals("Teste"));

    }

    @Test
    public void testGetDescricao() {

        System.out.println("Testando classe ServicoModelo metodo: getDescricao");
        assertEquals(true, isNull(servicoModelo.getDescricao()));

    }

    @Test
    public void testSetDescricao() {

        servicoModelo.setDescricao("Teste");
        System.out.println("Testando classe ServicoModelo metodo: setDescricao");
        assertEquals(true, servicoModelo.getDescricao().equals("Teste"));

    }

    @Test
    public void testGetValor() {

        System.out.println("Testando classe ServicoModelo metodo: getValor");
        assertEquals(true, servicoModelo.getValor().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetValor() {

        servicoModelo.setValor(new BigDecimal(100));
        System.out.println("Testando classe ServicoModelo metodo: setValor");
        assertEquals(true, servicoModelo.getValor().equals(new BigDecimal(100)));

    }

}

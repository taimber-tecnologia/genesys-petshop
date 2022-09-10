package br.com.salomaotech.genesys.model.centro_custo;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class CentroCustoModeloTest {

    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe CentroCustoModelo metodo: getId");
        assertEquals(true, centroCustoModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        long id = 1;
        centroCustoModelo.setId(id);

        System.out.println("Testando classe CentroCustoModelo metodo: setId");
        assertEquals(true, centroCustoModelo.getId() == id);

    }

    @Test
    public void testGetCodigo() {

        System.out.println("Testando classe CentroCustoModelo metodo: getCodigo");
        assertEquals(true, isNull(centroCustoModelo.getCodigo()));

    }

    @Test
    public void testSetCodigo() {

        String codigo = "1.1.1";
        centroCustoModelo.setCodigo(codigo);

        System.out.println("Testando classe CentroCustoModelo metodo: setCodigo");
        assertEquals(true, centroCustoModelo.getCodigo().equals(codigo));

    }

    @Test
    public void testGetNome() {

        System.out.println("Testando classe CentroCustoModelo metodo: getNome");
        assertEquals(true, isNull(centroCustoModelo.getNome()));

    }

    @Test
    public void testSetNome() {

        String nome = "Teste";
        centroCustoModelo.setNome(nome);

        System.out.println("Testando classe CentroCustoModelo metodo: setNome");
        assertEquals(true, centroCustoModelo.getNome().equals(nome));

    }

    @Test
    public void testGetNomeCompleto() {

        String nomeCompleto = centroCustoModelo.getNome() + " - " + centroCustoModelo.getCodigo();

        System.out.println("Testando classe CentroCustoModelo metodo: getNomeCompleto");
        assertEquals(true, centroCustoModelo.getNomeCompleto().equals(nomeCompleto));

    }

}

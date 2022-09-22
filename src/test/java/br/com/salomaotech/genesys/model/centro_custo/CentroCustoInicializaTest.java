package br.com.salomaotech.genesys.model.centro_custo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CentroCustoInicializaTest {

    private final CentroCustoInicializa centroCustoInicializa = new CentroCustoInicializa();

    @Test
    public void testCadastrar() {

        boolean isErro = false;

        try {

            centroCustoInicializa.cadastrar();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe CentroCustoInicializa metodo: cadastrar");
        assertEquals(false, isErro);

    }

}

package br.com.salomaotech.genesys.model.centro_custo;

import org.junit.Test;
import static org.junit.Assert.*;

public class CentroCustoPadraoTest {

    public CentroCustoPadraoTest() {

    }

    @Test
    public void testCadastrar() {

        System.out.println("cadastrar");
        CentroCustoPadrao instance = new CentroCustoPadrao();
        instance.cadastrar();

    }

}

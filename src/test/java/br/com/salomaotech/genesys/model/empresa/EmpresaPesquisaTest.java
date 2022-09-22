package br.com.salomaotech.genesys.model.empresa;

import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaPesquisaTest {

    private final EmpresaModelo empresaModelo = new EmpresaModelo();

    public EmpresaPesquisaTest() {

        /* simula cadastro de empresa */
        new Repository(new EmpresaModelo()).deleteTodos();
        empresaModelo.setNome("Teste");
        empresaModelo.setCnpj("00.000.000/0001-00");
        new Repository(empresaModelo).save();

    }

    @Test
    public void testGetDadosEmpresa() {

        System.out.println("Testando classe EmpresaModelo metodo: getDadosEmpresa");
        assertEquals(true, EmpresaPesquisa.getDadosEmpresa().getId() != 0);

    }

}

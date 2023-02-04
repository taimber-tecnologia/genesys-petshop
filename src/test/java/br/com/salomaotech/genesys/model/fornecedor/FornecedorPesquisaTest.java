package br.com.salomaotech.genesys.model.fornecedor;

import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class FornecedorPesquisaTest {

    private final JFfornecedor view = new JFfornecedor();
    private final FornecedorModelo fornecedorModelo = new FornecedorModelo();
    private FornecedorPesquisa fornecedorPesquisa = new FornecedorPesquisa(view.jTresultados, view.jCpaginador);

    public FornecedorPesquisaTest() {

        /* simula cadastro de fornecedor */
        new Repository(new FornecedorModelo()).deleteTodos();
        fornecedorModelo.setNome("Teste");
        fornecedorModelo.setCnpj("00.000.000/0001-00");
        new Repository(fornecedorModelo).save();

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            fornecedorPesquisa.setNome(fornecedorModelo.getNome());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FornecedorPesquisa metodo: setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetCnpj() {

        boolean isErro = false;

        try {

            fornecedorPesquisa.setCnpj(fornecedorModelo.getCnpj());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FornecedorPesquisa metodo: setCnpj");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* utilizando filtro: nenhum */
        fornecedorPesquisa = new FornecedorPesquisa(view.jTresultados, view.jCpaginador);
        fornecedorPesquisa.setNome(null);
        fornecedorPesquisa.setCnpj(null);
        fornecedorPesquisa.pesquisar();
        System.out.println("Testando classe FornecedorPesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: nome */
        fornecedorPesquisa = new FornecedorPesquisa(view.jTresultados, view.jCpaginador);
        fornecedorPesquisa.setNome(fornecedorModelo.getNome());
        fornecedorPesquisa.setCnpj(null);
        fornecedorPesquisa.pesquisar();
        System.out.println("Testando classe FornecedorPesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: CNPJ */
        fornecedorPesquisa = new FornecedorPesquisa(view.jTresultados, view.jCpaginador);
        fornecedorPesquisa.setNome(null);
        fornecedorPesquisa.setCnpj(fornecedorModelo.getCnpj());
        fornecedorPesquisa.pesquisar();
        System.out.println("Testando classe FornecedorPesquisa metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: todos */
        fornecedorPesquisa = new FornecedorPesquisa(view.jTresultados, view.jCpaginador);
        fornecedorPesquisa.setNome(fornecedorModelo.getNome());
        fornecedorPesquisa.setCnpj(fornecedorModelo.getCnpj());
        fornecedorPesquisa.pesquisar();
        System.out.println("Testando classe FornecedorPesquisa metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}

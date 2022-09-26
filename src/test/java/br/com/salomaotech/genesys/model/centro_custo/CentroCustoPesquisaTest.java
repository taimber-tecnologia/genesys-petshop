package br.com.salomaotech.genesys.model.centro_custo;

import br.com.salomaotech.genesys.view.JFcentroCusto;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class CentroCustoPesquisaTest {

    private final JFcentroCusto view = new JFcentroCusto();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private CentroCustoPesquisa centroCustoPesquisa = new CentroCustoPesquisa(view.jTresultados);

    public CentroCustoPesquisaTest() {

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            centroCustoPesquisa.setNome(centroCustoModelo.getNome());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe CentroCustoPesquisa metodo: setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* utilizando filtro: nenhum */
        centroCustoPesquisa = new CentroCustoPesquisa(view.jTresultados);
        view.jTpesquisaNome.setText(null);
        centroCustoPesquisa.pesquisar();
        System.out.println("Testando classe CentroCustoPesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: nome */
        centroCustoPesquisa = new CentroCustoPesquisa(view.jTresultados);
        view.jTpesquisaNome.setText(centroCustoModelo.getNome());
        centroCustoPesquisa.pesquisar();
        System.out.println("Testando classe CentroCustoPesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}

package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.controller.centro_custo.CentroCustoValidador;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.view.JFcentroCusto;
import br.com.salomaotech.sistema.jpa.Repository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CentroCustoValidadorTest {

    private final JFcentroCusto view = new JFcentroCusto();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private CentroCustoValidador centroCustoValidador;

    public CentroCustoValidadorTest() {

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.1");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

    }

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jTcadastroCodigo.setText("");
        view.jTcadastroNome.setText("");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        System.out.println("Testando classe CentroCustoValidador metodo: isValido etapa 01");
        assertEquals(false, centroCustoValidador.isValido());

        /* usando filtro: codigo */
        view.jTcadastroCodigo.setText("1.2");
        view.jTcadastroNome.setText("");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        System.out.println("Testando classe CentroCustoValidador metodo: isValido etapa 02");
        assertEquals(false, centroCustoValidador.isValido());

        /* usando filtro: nome */
        view.jTcadastroCodigo.setText("");
        view.jTcadastroNome.setText("Teste");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        System.out.println("Testando classe CentroCustoValidador metodo: isValido etapa 03");
        assertEquals(false, centroCustoValidador.isValido());

        /* usando filtro: todos */
        view.jTcadastroCodigo.setText("1.2");
        view.jTcadastroNome.setText("Teste 2");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        System.out.println("Testando classe CentroCustoValidador metodo: isValido etapa 04");
        assertEquals(true, centroCustoValidador.isValido());

        /* não permite um novo cadastro com um código já em uso */
        view.jTcadastroCodigo.setText(centroCustoModelo.getCodigo());
        view.jTcadastroNome.setText(centroCustoModelo.getNome());
        centroCustoValidador = new CentroCustoValidador(view, 0);
        System.out.println("Testando classe CentroCustoValidador metodo: isValido etapa 05");
        assertEquals(false, centroCustoValidador.isValido());

        /* simula a atualização de um registro com o código aberto */
        view.jTcadastroCodigo.setText(centroCustoModelo.getCodigo());
        view.jTcadastroNome.setText(centroCustoModelo.getNome());
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        System.out.println("Testando classe CentroCustoValidador metodo: isValido etapa 06");
        assertEquals(true, centroCustoValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jTcadastroCodigo.setText("");
        view.jTcadastroNome.setText("");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        centroCustoValidador.isValido();
        System.out.println("Testando classe CentroCustoValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, centroCustoValidador.getMensagensErro().length() > 0);

        /* usando filtro: codigo */
        view.jTcadastroCodigo.setText("1.2");
        view.jTcadastroNome.setText("");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        centroCustoValidador.isValido();
        System.out.println("Testando classe CentroCustoValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, centroCustoValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome */
        view.jTcadastroCodigo.setText("");
        view.jTcadastroNome.setText("Teste");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        centroCustoValidador.isValido();
        System.out.println("Testando classe CentroCustoValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, centroCustoValidador.getMensagensErro().length() > 0);


        /* usando filtro: todos */
        view.jTcadastroCodigo.setText("1.2");
        view.jTcadastroNome.setText("Teste 2");
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        centroCustoValidador.isValido();
        System.out.println("Testando classe CentroCustoValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, centroCustoValidador.getMensagensErro().length() == 0);

        /* não permite um novo cadastro com um código já em uso */
        view.jTcadastroCodigo.setText(centroCustoModelo.getCodigo());
        view.jTcadastroNome.setText(centroCustoModelo.getNome());
        centroCustoValidador = new CentroCustoValidador(view, 0);
        centroCustoValidador.isValido();
        System.out.println("Testando classe CentroCustoValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, centroCustoValidador.getMensagensErro().length() > 0);


        /* simula a atualização de um registro com o código aberto */
        view.jTcadastroCodigo.setText(centroCustoModelo.getCodigo());
        view.jTcadastroNome.setText(centroCustoModelo.getNome());
        centroCustoValidador = new CentroCustoValidador(view, centroCustoModelo.getId());
        centroCustoValidador.isValido();
        System.out.println("Testando classe CentroCustoValidador metodo: getMensagensErro etapa 06");
        assertEquals(true, centroCustoValidador.getMensagensErro().length() == 0);

    }

}

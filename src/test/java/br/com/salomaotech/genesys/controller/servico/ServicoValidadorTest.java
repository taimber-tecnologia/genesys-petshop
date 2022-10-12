package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.view.JFservico;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoValidadorTest {

    private final JFservico view = new JFservico();
    private ServicoValidador servicoValidador;

    @Test
    public void testIsValido() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 01");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: nome */
        view.jTnome.setText("Teste");
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 02");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: valor */
        view.jTnome.setText(null);
        view.jTvalor.setText("199");
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 03");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: todos */
        view.jTnome.setText("Teste");
        view.jTvalor.setText("199");
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 04");
        assertEquals(true, servicoValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, servicoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: nome */
        view.jTnome.setText("Teste");
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, servicoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor */
        view.jTnome.setText(null);
        view.jTvalor.setText("199");
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, servicoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos */
        view.jTnome.setText("Teste");
        view.jTvalor.setText("199");
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, servicoValidador.getMensagensErro().length() == 0);

    }

}

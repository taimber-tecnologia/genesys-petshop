package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoValidadorTest {

    private final JFservico view = new JFservico();
    private ServicoValidador servicoValidador;
    private final ServicoModelo servicoModelo = new ServicoModelo();

    public ServicoValidadorTest() {

        /* simula cadastro de servico */
        new Repository(new ServicoModelo()).deleteTodos();
        servicoModelo.setNome("Teste");
        servicoModelo.setDescricao("Teste ABC");
        servicoModelo.setValor(new BigDecimal(15));
        new Repository(servicoModelo).save();

    }

    @Test
    public void testIsValido() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 01");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: nome */
        view.jTnome.setText(servicoModelo.getNome());
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 02");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: valor */
        view.jTnome.setText(null);
        view.jTvalor.setText(servicoModelo.getValor().toString());
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 03");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: todos com nome já existente */
        view.jTnome.setText(servicoModelo.getNome());
        view.jTvalor.setText(servicoModelo.getValor().toString());
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 04");
        assertEquals(false, servicoValidador.isValido());

        /* validando usando filtro: todos com nome diferente */
        view.jTnome.setText("Teste DEF");
        view.jTvalor.setText(servicoModelo.getValor().toString());
        servicoValidador = new ServicoValidador(view);
        System.out.println("Testando classe ServicoValidador metodo: isValido etapa 05");
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
        assertEquals(false, servicoValidador.getMensagensErro().isEmpty());

        /* validando usando filtro: nome */
        view.jTnome.setText(servicoModelo.getNome());
        view.jTvalor.setText(null);
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 02");
        assertEquals(false, servicoValidador.getMensagensErro().isEmpty());

        /* validando usando filtro: valor */
        view.jTnome.setText(null);
        view.jTvalor.setText(servicoModelo.getValor().toString());
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 03");
        assertEquals(false, servicoValidador.getMensagensErro().isEmpty());

        /* validando usando filtro: todos com nome já existente */
        view.jTnome.setText(servicoModelo.getNome());
        view.jTvalor.setText(servicoModelo.getValor().toString());
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 04");
        assertEquals(false, servicoValidador.getMensagensErro().isEmpty());

        /* validando usando filtro: todos com nome diferente */
        view.jTnome.setText("Teste DEF");
        view.jTvalor.setText(servicoModelo.getValor().toString());
        servicoValidador = new ServicoValidador(view);
        servicoValidador.isValido();
        System.out.println("Testando classe ServicoValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, servicoValidador.getMensagensErro().isEmpty());

    }

}

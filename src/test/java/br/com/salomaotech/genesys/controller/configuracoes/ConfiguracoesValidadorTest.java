package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.genesys.view.JFconfiguracoes;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfiguracoesValidadorTest {

    private final JFconfiguracoes view = new JFconfiguracoes();
    private final ConfiguracoesMetodos configuracoesMetodos = new ConfiguracoesMetodos(view);
    private final ConfiguracoesValidador configuracoesValidador = new ConfiguracoesValidador(view, configuracoesMetodos);

    @Test
    public void testIsValido() {

        /* usando filtros: nenhum */
        view.jTservidor.setText(null);
        view.jTlogin.setText(null);
        view.jTsenha.setText(null);
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: isValido etapa 01");
        assertEquals(false, configuracoesValidador.isValido());

        /* usando filtros: servidor */
        view.jTservidor.setText("127.0.0.1");
        view.jTlogin.setText(null);
        view.jTsenha.setText(null);
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: isValido etapa 02");
        assertEquals(false, configuracoesValidador.isValido());

        /* usando filtros: login */
        view.jTservidor.setText(null);
        view.jTlogin.setText("root");
        view.jTsenha.setText(null);
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: isValido etapa 03");
        assertEquals(false, configuracoesValidador.isValido());

        /* usando filtros: senha */
        view.jTservidor.setText(null);
        view.jTlogin.setText(null);
        view.jTsenha.setText("123456");
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: isValido etapa 04");
        assertEquals(false, configuracoesValidador.isValido());

        /* usando filtros: banco de dados */
        view.jTservidor.setText(null);
        view.jTlogin.setText(null);
        view.jTsenha.setText(null);
        view.jTbancoDados.setText("bd_teste");
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: isValido etapa 05");
        assertEquals(false, configuracoesValidador.isValido());

        /* usando filtros: todos */
        view.jTservidor.setText("127.0.0.1");
        view.jTlogin.setText("root");
        view.jTsenha.setText("root");
        view.jTbancoDados.setText("bd_teste");
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: isValido etapa 06");
        assertEquals(false, configuracoesValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtros: nenhum */
        view.jTservidor.setText(null);
        view.jTlogin.setText(null);
        view.jTsenha.setText(null);
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: getMensagensErro etapa 01");
        configuracoesValidador.isValido();
        assertEquals(true, configuracoesValidador.getMensagensErro().length() > 0);

        /* usando filtros: servidor */
        view.jTservidor.setText("127.0.0.1");
        view.jTlogin.setText(null);
        view.jTsenha.setText(null);
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: getMensagensErro etapa 02");
        configuracoesValidador.isValido();
        assertEquals(true, configuracoesValidador.getMensagensErro().length() > 0);

        /* usando filtros: login */
        view.jTservidor.setText(null);
        view.jTlogin.setText("root");
        view.jTsenha.setText(null);
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: getMensagensErro etapa 03");
        configuracoesValidador.isValido();
        assertEquals(true, configuracoesValidador.getMensagensErro().length() > 0);

        /* usando filtros: senha */
        view.jTservidor.setText(null);
        view.jTlogin.setText(null);
        view.jTsenha.setText("123456");
        view.jTbancoDados.setText(null);
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: getMensagensErro etapa 04");
        configuracoesValidador.isValido();
        assertEquals(true, configuracoesValidador.getMensagensErro().length() > 0);

        /* usando filtros: banco de dados */
        view.jTservidor.setText(null);
        view.jTlogin.setText(null);
        view.jTsenha.setText(null);
        view.jTbancoDados.setText("bd_teste");
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: getMensagensErro etapa 05");
        configuracoesValidador.isValido();
        assertEquals(true, configuracoesValidador.getMensagensErro().length() > 0);

        /* usando filtros: todos */
        view.jTservidor.setText("127.0.0.1");
        view.jTlogin.setText("root");
        view.jTsenha.setText("root");
        view.jTbancoDados.setText("bd_teste");
        configuracoesMetodos.conectar();
        System.out.println("Testando classe ConfiguracoesValidador metodo: getMensagensErro etapa 06");
        configuracoesValidador.isValido();
        assertEquals(true, configuracoesValidador.getMensagensErro().length() > 0);

    }

}

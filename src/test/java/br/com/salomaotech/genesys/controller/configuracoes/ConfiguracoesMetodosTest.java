package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.genesys.view.JFconfiguracoes;
import br.com.salomaotech.sistema.algoritmos.ArquivoPropriedade;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import br.com.salomaotech.sistema.jpa.ConfiguracoesConexao;
import br.com.salomaotech.sistema.swing.PopUp;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfiguracoesMetodosTest {

    private final JFconfiguracoes view = new JFconfiguracoes();
    private final ConfiguracoesMetodos configuracoesMetodos;
    private static ArquivoPropriedade arquivoPropriedade;
    private Properties propriedades = new Properties();

    public ConfiguracoesMetodosTest() {

        /* propriedades padrão */
        propriedades.setProperty("servidor", "0.0.0.0");
        propriedades.setProperty("login", "root_xxxxxx");
        propriedades.setProperty("senha", "root_xxxxxx");
        propriedades.setProperty("banco", "bd_xxxxxx");
        propriedades.setProperty("pasta_raiz", "pasta_xxxxxx");

        /* salva as propriedades */
        arquivoPropriedade = new ArquivoPropriedade(new ConfiguracoesConexao().getPathArquivo());
        arquivoPropriedade.salvar(propriedades);

        /* metodos */
        configuracoesMetodos = new ConfiguracoesMetodos(view);

    }

    @Test
    public void testPopularFormulario() {

        configuracoesMetodos.popularFormulario();

        System.out.println("Testando classe ConfiguracoesMetodos metodo: popularFormulario");
        assertEquals(true, view.jTservidor.getText().equals(propriedades.get("servidor")));
        assertEquals(true, view.jTlogin.getText().equals(propriedades.get("login")));
        assertEquals(true, view.jTsenha.getText().equals(propriedades.get("senha")));
        assertEquals(true, view.jTbancoDados.getText().equals(propriedades.get("banco")));
        assertEquals(true, view.jTpastaDeArquivos.getText().equals(propriedades.getProperty("pasta_raiz")));

    }

    @Test
    public void testHabilitarCampos() {

        System.out.println("Testando classe ConfiguracoesMetodos metodo: habilitarCampos etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        configuracoesMetodos.habilitarCampos();
        System.out.println("Testando classe ConfiguracoesMetodos metodo: habilitarCampos etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

        configuracoesMetodos.excluir();
        configuracoesMetodos.habilitarCampos();
        System.out.println("Testando classe ConfiguracoesMetodos metodo: habilitarCampos etapa 03");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testPopularFormularioSelecionarPastaDeArquivos() {

        boolean isPastaSelecionada = configuracoesMetodos.popularFormularioSelecionarPastaDeArquivos();

        System.out.println("Testando classe ConfiguracoesMetodos metodo: popularFormularioSelecionarPastaDeArquivos");
        assertEquals(true, ValidaStringIsEmpty.isEmpty(view.jTpastaDeArquivos.getText()) != isPastaSelecionada);

    }

    @Test
    public void testAddPopUpMenu() {

        configuracoesMetodos.addPopUpMenu();

        PopUp popUp = new PopUp();
        System.out.println("Testando classe ConfiguracoesMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTservidor));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTlogin));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTsenha));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTbancoDados));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpastaDeArquivos));

    }

    @Test
    public void testSalvar() {

        /* popula view */
        view.jTservidor.setText("0.0.0.0");
        view.jTlogin.setText("A1");
        view.jTsenha.setText("B2");
        view.jTbancoDados.setText("C2");
        configuracoesMetodos.salvar();

        /* carrega as propriedades novamente para ver se foram salvas */
        propriedades = arquivoPropriedade.getProperties();

        System.out.println("Testando classe ConfiguracoesMetodos metodo: salvar");
        assertEquals(true, view.jTservidor.getText().equals(propriedades.get("servidor")));
        assertEquals(true, view.jTlogin.getText().equals(propriedades.get("login")));
        assertEquals(true, view.jTsenha.getText().equals(propriedades.get("senha")));
        assertEquals(true, view.jTbancoDados.getText().equals(propriedades.get("banco")));
        assertEquals(true, view.jTpastaDeArquivos.getText().equals(propriedades.getProperty("pasta_raiz")));

    }

    @Test
    public void testExcluir() {

        configuracoesMetodos.excluir();

        System.out.println("Testando classe ConfiguracoesMetodos metodo: excluir");
        assertEquals(false, arquivoPropriedade.isArquivoConfiguracaoExiste());

    }

    @Test
    public void testConectar() {

        /* 1 - fecha a conexão aberta */
        ConexaoSingleton conexaoSingleton = new ConexaoSingleton();
        conexaoSingleton.fecharConexao();
        configuracoesMetodos.popularFormulario();

        /* 2 - tenta se conectar ao Mysql */
        System.out.println("Testando classe ConfiguracoesMetodos metodo: conectar Mysql");
        assertEquals(false, configuracoesMetodos.conectar());
        conexaoSingleton.fecharConexao();
        arquivoPropriedade.deletar();

        /* 3 - tenta se conectar ao Derby */
        conexaoSingleton.abrirConexao("Conexao");
        System.out.println("Testando classe ConfiguracoesMetodos metodo: conectar Derby");
        assertEquals(true, conexaoSingleton.isConexaoAberta());

    }

    @AfterClass
    public static void metodoFinal() {

        arquivoPropriedade.deletar();

    }

}

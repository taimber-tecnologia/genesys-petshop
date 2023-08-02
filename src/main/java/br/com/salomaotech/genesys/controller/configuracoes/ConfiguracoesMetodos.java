package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.sistema.jpa.ConfiguracoesConexao;
import br.com.salomaotech.genesys.view.JFconfiguracoes;
import br.com.salomaotech.sistema.algoritmos.ArquivoPropriedade;
import br.com.salomaotech.sistema.algoritmos.CopiarArquivosSemDialogo;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import br.com.salomaotech.sistema.swing.PopUp;
import java.io.File;
import java.util.Properties;
import javax.swing.JFileChooser;

public class ConfiguracoesMetodos {

    private final JFconfiguracoes view;
    private Properties propriedades = new Properties();
    private final ArquivoPropriedade arquivoPropriedade;

    public ConfiguracoesMetodos(JFconfiguracoes view) {

        this.view = view;
        arquivoPropriedade = new ArquivoPropriedade(new ConfiguracoesConexao().getPathArquivo());
        propriedades = arquivoPropriedade.getProperties();

    }

    public void popularFormulario() {

        try {

            view.jTservidor.setText(propriedades.getProperty("servidor"));
            view.jTlogin.setText(propriedades.getProperty("login"));
            view.jTsenha.setText(propriedades.getProperty("senha"));
            view.jTbancoDados.setText(propriedades.getProperty("banco"));
            view.jTpastaDeArquivos.setText(propriedades.getProperty("pasta_raiz"));

        } catch (Exception ex) {

        }

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(arquivoPropriedade.isArquivoConfiguracaoExiste());

    }

    public boolean popularFormularioSelecionarPastaDeArquivos() {

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Selecionar pasta de arquivos");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setAcceptAllFileFilterUsed(false);

        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            view.jTpastaDeArquivos.setText(jFileChooser.getSelectedFile().getAbsoluteFile().toString());
            return true;

        } else {

            return false;

        }

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTservidor);
        popUp.adicionarMenu(view.jTlogin);
        popUp.adicionarMenu(view.jTsenha);
        popUp.adicionarMenu(view.jTbancoDados);
        popUp.adicionarMenu(view.jTpastaDeArquivos);

    }

    public void salvar() {

        propriedades.setProperty("servidor", view.jTservidor.getText());
        propriedades.setProperty("login", view.jTlogin.getText());
        propriedades.setProperty("senha", view.jTsenha.getText());
        propriedades.setProperty("banco", view.jTbancoDados.getText());
        propriedades.setProperty("pasta_raiz", view.jTpastaDeArquivos.getText());
        arquivoPropriedade.salvar(propriedades);

    }

    public boolean excluir() {

        return arquivoPropriedade.deletar();

    }

    public boolean conectar() {

        /* 1 - inicializa arquivo de backup e conexão */
        File fileBackup = new File(new File(arquivoPropriedade.absolutePath()).getParent() + "/bkp.txt");
        ConexaoSingleton conexaoSingleton = new ConexaoSingleton();
        boolean isPreconfiguracaoExiste = arquivoPropriedade.isArquivoConfiguracaoExiste();

        /* 2 - se o arquivo de configuração original existir, então copiar ele */
        if (isPreconfiguracaoExiste) {

            CopiarArquivosSemDialogo.copiar(arquivoPropriedade.absolutePath(), fileBackup.getAbsolutePath());

        }

        /* 3 - salve as novas configurações, e tente uma nova conexão com as novas informações */
        salvar();
        conexaoSingleton.fecharConexao();
        conexaoSingleton.abrirConexao("Conexao");
        boolean isConectado = conexaoSingleton.isConexaoAberta() && new File(arquivoPropriedade.getProperties().getProperty("pasta_raiz")).exists();

        /* 4 - restaura o arquivo de backup original se necessário, e apaga o de backup */
        if (isPreconfiguracaoExiste) {

            CopiarArquivosSemDialogo.copiar(fileBackup.getAbsolutePath(), arquivoPropriedade.absolutePath());
            fileBackup.delete();

        } else {

            /* aqui é deletado porque como não existia antes, não pode existir agora */
            arquivoPropriedade.deletar();

        }

        /* 5 - restaura o estado anterior */
        conexaoSingleton.fecharConexao();
        conexaoSingleton.abrirConexao("Conexao");
        propriedades = arquivoPropriedade.getProperties();
        popularFormulario();

        return isConectado;

    }

}

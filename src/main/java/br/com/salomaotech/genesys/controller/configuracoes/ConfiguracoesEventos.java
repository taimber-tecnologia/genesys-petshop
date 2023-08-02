package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.genesys.view.JFconfiguracoes;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class ConfiguracoesEventos {

    private final JFconfiguracoes view;
    private ConfiguracoesMetodos configuracoesMetodos;

    public ConfiguracoesEventos(JFconfiguracoes view) {
        this.view = view;
    }

    public void setConfiguracoesMetodos(ConfiguracoesMetodos configuracoesMetodos) {
        this.configuracoesMetodos = configuracoesMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            ConfiguracoesValidador configuracoesValidador = new ConfiguracoesValidador(view, configuracoesMetodos);

            if (configuracoesValidador.isValido()) {

                configuracoesMetodos.salvar();
                JOptionPane.showMessageDialog(null, "Configurações salvas com sucesso!");
                JOptionPane.showMessageDialog(null, "Para que as novas configurações sejam aplicadas, o sistema será encerrado.");
                JOptionPane.showMessageDialog(null, "Depois disto basta abri-lo novamente!");
                new ConexaoSingleton().fecharConexao();
                System.exit(0);

            } else {

                JOptionPane.showMessageDialog(null, configuracoesValidador.getMensagensErro());

            }

        });

        /* botão conectar */
        view.jBconectar.addActionListener((ActionEvent e) -> {

            if (configuracoesMetodos.conectar()) {

                JOptionPane.showMessageDialog(null, "Servidor de banco de dados conectado!");

            } else {

                JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor de banco de dados.");

            }

        });

        /* botão para selecionar pasta de arquivos */
        view.jBpastaDeArquivosSeleciona.addActionListener((ActionEvent e) -> {

            configuracoesMetodos.popularFormularioSelecionarPastaDeArquivos();

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir configurações?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (configuracoesMetodos.excluir()) {

                    JOptionPane.showMessageDialog(null, "Configurações excluídas!");
                    JOptionPane.showMessageDialog(null, "Para que as novas configurações sejam aplicadas, o sistema será encerrado.");
                    JOptionPane.showMessageDialog(null, "Depois disto basta abri-lo novamente!");
                    new ConexaoSingleton().fecharConexao();
                    System.exit(0);

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

    }

}

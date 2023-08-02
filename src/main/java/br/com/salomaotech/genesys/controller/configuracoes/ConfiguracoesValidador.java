package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.genesys.view.JFconfiguracoes;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;

public class ConfiguracoesValidador {

    private final JFconfiguracoes view;
    private String mensagensErro = "";
    private final ConfiguracoesMetodos configuracoesMetodos;

    public ConfiguracoesValidador(JFconfiguracoes view, ConfiguracoesMetodos configuracoesMetodos) {
        this.view = view;
        this.configuracoesMetodos = configuracoesMetodos;
    }

    public boolean isValido() {

        /* valida servidor */
        if (ValidaStringIsEmpty.isEmpty(view.jTservidor.getText())) {

            mensagensErro = "Informe o IP do servidor.";
            view.jTservidor.requestFocus();
            return false;

        }

        /* valida login */
        if (ValidaStringIsEmpty.isEmpty(view.jTlogin.getText())) {

            mensagensErro = "Informe o login do banco de dados.";
            view.jTlogin.requestFocus();
            return false;

        }

        /* valida senha */
        if (ValidaStringIsEmpty.isEmpty(view.jTsenha.getText())) {

            mensagensErro = "Informe a senha do banco de dados.";
            view.jTsenha.requestFocus();
            return false;

        }

        /* valida banco de dados */
        if (ValidaStringIsEmpty.isEmpty(view.jTbancoDados.getText())) {

            mensagensErro = "Informe o nome do banco de dados.";
            view.jTbancoDados.requestFocus();
            return false;

        }

        /* valida conexão com servidor */
        if (!configuracoesMetodos.conectar()) {

            mensagensErro = "Impossível se conectar a este servidor com estas configurações.";
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}

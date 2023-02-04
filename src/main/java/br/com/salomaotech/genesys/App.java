package br.com.salomaotech.genesys;

import br.com.salomaotech.genesys.controller.ativador.AtivadorController;
import br.com.salomaotech.genesys.controller.principal.PrincipalController;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoInicializa;
import br.com.salomaotech.genesys.view.JFinicializar;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class App {

    private static void carregarConfiguracoesPadrao() {

        new CentroCustoInicializa().cadastrar();

    }

    private static void carregarAtivador() {

        new AtivadorController().construir();

    }

    public static void main(String[] args) throws ClassNotFoundException {

        /* tela de inicialização */
        JFinicializar view = new JFinicializar();
        new MudaIconeJframe().alterar("animal64x", view);
        view.setVisible(true);

        /* abre a conexão com o banco de dados */
        new ConexaoSingleton().abrirConexao("Conexao");

        /* carrega as configurações padrão */
        carregarConfiguracoesPadrao();

        /* carrega o ativador */
        carregarAtivador();

        /* abre o JFrame principal */
        new PrincipalController().construir();

        /* fecha a tela de inicialização */
        view.setVisible(false);

    }

}

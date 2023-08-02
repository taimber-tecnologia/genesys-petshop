package br.com.salomaotech.genesys;

import br.com.salomaotech.genesys.controller.ativador.AtivadorController;
import br.com.salomaotech.genesys.controller.novidades.ExibeNovidades;
import br.com.salomaotech.genesys.controller.principal.PrincipalController;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoInicializa;
import br.com.salomaotech.genesys.view.JFinicializar;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class App {

    private static void carregarConfiguracoesPadrao() {

        /* conexão */
        new ConexaoSingleton().abrirConexao("Conexao");

        /* cadastros */
        new CentroCustoInicializa().cadastrar();

        /* serviços adicionais */
        new AtivadorController().construir("genesys-petshop-2.0");
        ExibeNovidades.exibir();

    }

    public static void main(String[] args) {

        /* tela de inicialização */
        JFinicializar view = new JFinicializar();
        new MudaIconeJframe().alterar("animal64x", view);
        view.setVisible(true);

        /* carrega as configurações padrão */
        carregarConfiguracoesPadrao();

        /* abre o JFrame principal */
        new PrincipalController().construir();

        /* fecha a tela de inicialização */
        view.setVisible(false);

    }

}

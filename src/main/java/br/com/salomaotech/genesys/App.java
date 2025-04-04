package br.com.salomaotech.genesys;

import br.com.salomaotech.genesys.controller.ativador.AtivadorController;
import br.com.salomaotech.genesys.controller.novidades.ExibeNovidades;
import br.com.salomaotech.genesys.controller.principal.PrincipalController;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoInicializa;
import br.com.salomaotech.genesys.view.JFinicializar;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class App {

    private static void carregarConexaoBancoDados() {

        new ConexaoSingleton().abrirConexao("Conexao");

    }

    private static void carregarServicosAdicionais() {

        new AtivadorController().construir("genesys-petshop-3.0");
        ExibeNovidades.exibir();

    }

    private static void carregarConfiguracoesPadrao() {

        new CentroCustoInicializa().cadastrar();

    }

    public static void main(String[] args) {

        /* tela de inicialização */
        JFinicializar view = new JFinicializar();
        new MudaIconeJframe().alterar("animal64x", view);
        view.setVisible(true);

        /* carrega a conexão com o banco de dados */
        carregarConexaoBancoDados();

        /* carrega as configurações padrão */
        carregarConfiguracoesPadrao();

        /* abre o JFrame principal */
        new PrincipalController().construir();

        /* fecha a tela de inicialização */
        view.setVisible(false);

        /* carrega serviços adicionais */
        //carregarServicosAdicionais();

    }

}

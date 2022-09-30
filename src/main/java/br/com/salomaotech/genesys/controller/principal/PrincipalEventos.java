package br.com.salomaotech.genesys.controller.principal;

import br.com.salomaotech.genesys.controller.agenda.AgendaController;
import br.com.salomaotech.genesys.controller.animal.AnimalController;
import br.com.salomaotech.genesys.controller.empresa.EmpresaController;
import br.com.salomaotech.genesys.controller.cliente.ClienteController;
import br.com.salomaotech.genesys.controller.financeiro.FinanceiroController;
import br.com.salomaotech.genesys.controller.fornecedor.FornecedorController;
import br.com.salomaotech.genesys.controller.produto.ProdutoController;
import br.com.salomaotech.genesys.controller.venda.venda_pesquisa.VendaPesquisaController;
import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.genesys.view.JFprincipal;
import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import br.com.salomaotech.sistema.jpa.ConexaoSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

public class PrincipalEventos {

    private final JFprincipal view;
    private PrincipalMetodos principalMetodos;

    public PrincipalEventos(JFprincipal view) {
        this.view = view;
    }

    public void setPrincipalMetodos(PrincipalMetodos principalMetodos) {
        this.principalMetodos = principalMetodos;
    }

    public void addEventos() {

        /* atalho para clientes */
        view.jBatalhoClientes.addActionListener((ActionEvent e) -> {

            new ClienteController().construir();

        });

        /* atalho para agenda */
        view.jBatalhoAgenda.addActionListener((ActionEvent e) -> {

            new AgendaController().construir();

        });

        /* jtable de resultados agenda */
        view.jTagendaResultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    long id = (long) view.jTagendaResultados.getModel().getValueAt(view.jTagendaResultados.getSelectedRow(), 0);
                    AgendaController agendaController = new AgendaController();
                    agendaController.construir();
                    agendaController.getMetodos().abrirCadastro(id);

                }

            }

        });

        /* botão produto */
        view.jBatalhoProdutos.addActionListener((ActionEvent e) -> {

            new ProdutoController().construir();

        });

        /* botão de configurações */
        view.jBatalhoConfiguracoesEmpresa.addActionListener((ActionEvent e) -> {

            new EmpresaController().construir();

        });

        /* evento da view */
        view.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                new ConexaoSingleton("Conexao").fechar();

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

        /* atalho para ajuda */
        view.jLabrirAjuda.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (!ExecutaProgramaExterno.executarModoDesktop(new PastasSistema().getSubPastaEnderecoArquivoDeDocumentacao())) {

                    JOptionPane.showMessageDialog(null, "Documentação de ajuda não localizada.");

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        /* botão paginador */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            principalMetodos.carregaAgendaDia();

        });

        /* atalho para suporte */
        view.jLabrirSuporte.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                ExecutaProgramaExterno.abreUrlNoBrowser("https://www.taimber.com/");

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        /* atalho para cadastro de animal */
        view.jBatalhoAnimais.addActionListener((ActionEvent e) -> {

            new AnimalController().construir();

        });

        /* atalho para fornecedores */
        view.jBatalhoFornecedores.addActionListener((ActionEvent e) -> {

            new FornecedorController().construir();

        });

        /* atalho para financeiro */
        view.jBatalhoFinanceiro.addActionListener((ActionEvent e) -> {

            new FinanceiroController().construir();

        });

        /* atalho para venda */
        view.jBatalhoVenda.addActionListener((ActionEvent e) -> {

            new VendaPesquisaController().construir();

        });

        /* atalho para agenda */
        view.jMagenda.addActionListener((ActionEvent e) -> {

            new AgendaController().construir();

        });

        /* atalho para financeiro */
        view.jMfinanceiroPagar.addActionListener((ActionEvent e) -> {

            new FinanceiroController().construir();

        });

        /* atalho para financeiro */
        view.jMfinanceiroReceber.addActionListener((ActionEvent e) -> {

            new FinanceiroController().construir();

        });

    }

}

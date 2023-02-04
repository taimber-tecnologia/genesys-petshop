package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoPesquisa;
import br.com.salomaotech.genesys.view.JFservico;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ServicoEventos {

    private final JFservico view;
    private ServicoMetodos servicoMetodos;

    public ServicoEventos(JFservico view) {
        this.view = view;
    }

    public void setServicoMetodos(ServicoMetodos servicoMetodos) {
        this.servicoMetodos = servicoMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            ServicoValidador servicoValidador = new ServicoValidador(view);

            if (servicoValidador.isValido()) {

                ServicoModelo servicoModelo = servicoMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (servicoModelo.getId() != 0) {

                    servicoMetodos.popularFormulario(servicoModelo);
                    servicoMetodos.habilitarCampos();
                    new ServicoPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTnome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, servicoValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (servicoMetodos.excluir()) {

                    servicoMetodos.resetarView();
                    servicoMetodos.habilitarCampos();
                    new ServicoPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    view.jTnome.requestFocus();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* abrir */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    view.jTabaPrincipal.setSelectedIndex(0);
                    servicoMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            servicoMetodos.resetarView();
            servicoMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jTnome.requestFocus();

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            servicoMetodos.pesquisar();

        });

        /* pesquisa */
        view.jTpesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    servicoMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            servicoMetodos.pesquisar();

        });

    }

}

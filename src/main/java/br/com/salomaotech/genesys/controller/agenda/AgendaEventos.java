package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.view.JFagenda;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class AgendaEventos {

    private final JFagenda view;
    private AgendaMetodos agendaMetodos;

    public AgendaEventos(JFagenda view) {
        this.view = view;
    }

    public void setAgendaMetodos(AgendaMetodos agendaMetodos) {
        this.agendaMetodos = agendaMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            AgendaValidador agendaValidador = new AgendaValidador(view);

            if (agendaValidador.isValido()) {

                AgendaModelo agendaModelo = agendaMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (agendaModelo.getId() != 0) {

                    agendaMetodos.popularFormulario(agendaModelo);
                    agendaMetodos.habilitarCampos();
                    new AgendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, agendaValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (agendaMetodos.excluir()) {

                    agendaMetodos.resetarView();
                    agendaMetodos.habilitarCampos();
                    new AgendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
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

                    agendaMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* novo */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            agendaMetodos.resetarView();
            agendaMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            agendaMetodos.pesquisar();

        });

        /* paginador e pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            agendaMetodos.pesquisar();

        });

        /* pesquisa */
        view.jTpesquisaNomeCliente.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    agendaMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }

}

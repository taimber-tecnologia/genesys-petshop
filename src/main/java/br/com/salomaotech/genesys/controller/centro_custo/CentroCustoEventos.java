package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoPesquisa;
import br.com.salomaotech.genesys.view.JFcentroCusto;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class CentroCustoEventos {

    private final JFcentroCusto view;
    private CentroCustoMetodos centroCustoMetodos;

    public CentroCustoEventos(JFcentroCusto view) {
        this.view = view;
    }

    public void setCentroCustoMetodos(CentroCustoMetodos centroCustoMetodos) {
        this.centroCustoMetodos = centroCustoMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            CentroCustoValidador centroCustoValidador = new CentroCustoValidador(view, view.getId());

            if (centroCustoValidador.isValido()) {

                CentroCustoModelo centroCustoModelo = centroCustoMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (centroCustoModelo.getId() != 0) {

                    centroCustoMetodos.popularFormulario(centroCustoModelo);
                    centroCustoMetodos.habilitarCampos();
                    new CentroCustoPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTcadastroCodigo.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, centroCustoValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (centroCustoMetodos.excluir()) {

                    centroCustoMetodos.resetarView();
                    centroCustoMetodos.habilitarCampos();
                    new CentroCustoPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.jTcadastroCodigo.requestFocus();

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

                    centroCustoMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* novo */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(0);
            centroCustoMetodos.resetarView();
            centroCustoMetodos.habilitarCampos();

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);
            centroCustoMetodos.pesquisar();

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            centroCustoMetodos.pesquisar();

        });

        /* pesquisa */
        view.jTpesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    centroCustoMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* paginador e pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            centroCustoMetodos.pesquisar();

        });

        /* atalho para resetar a pesquisa */
        view.jBpesquisaReseta.addActionListener((ActionEvent e) -> {

            centroCustoMetodos.resetarViewPesquisa();

        });

    }

}

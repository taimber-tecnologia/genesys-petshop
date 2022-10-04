package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaController;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VendaPesquisaEventos {

    private final JFvendaPesquisa view;
    private VendaPesquisaMetodos vendaPesquisaMetodos;

    public VendaPesquisaEventos(JFvendaPesquisa view) {
        this.view = view;
    }

    public void setVendaPesquisaMetodos(VendaPesquisaMetodos vendaPesquisaMetodos) {
        this.vendaPesquisaMetodos = vendaPesquisaMetodos;
    }

    public void addEventos() {

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            new VendaIniciaController().construir();
            view.dispose();

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            vendaPesquisaMetodos.pesquisar();

        });

        /* paginador */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            vendaPesquisaMetodos.pesquisar();

        });

        /* abre */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    long id = (long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0);
                    vendaPesquisaMetodos.abrirCadastro(id);
                    view.dispose();

                }

            }

        });

        /* pesquisa */
        view.jTpesquisaCpf.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    vendaPesquisaMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }

}

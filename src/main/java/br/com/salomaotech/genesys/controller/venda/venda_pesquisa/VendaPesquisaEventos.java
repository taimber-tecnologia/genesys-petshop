package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaController;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VendaPesquisaEventos {

    private final JFvendaPesquisa view;
    private VendaPesquisaMetodos vendaPesquisaMetodos;
    private ComboBoxClientes comboBoxClientesPesquisa;

    public VendaPesquisaEventos(JFvendaPesquisa view) {
        this.view = view;
    }

    public void setVendaPesquisaMetodos(VendaPesquisaMetodos vendaPesquisaMetodos) {
        this.vendaPesquisaMetodos = vendaPesquisaMetodos;
    }

    public void setComboBoxClientesPesquisa(ComboBoxClientes comboBoxClientesPesquisa) {
        this.comboBoxClientesPesquisa = comboBoxClientesPesquisa;
    }

    public void addEventos() {

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            new VendaController().construir();
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

        /* atualiza lista de clientes */
        view.jBpesquisaNomeClienteRefresh.addActionListener((ActionEvent e) -> {

            comboBoxClientesPesquisa.preencher();

        });

        /* abre */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    long id = (long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0);
                    vendaPesquisaMetodos.abrirCadastro(id);

                }

            }

        });

    }

}

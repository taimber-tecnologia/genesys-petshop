package br.com.salomaotech.genesys.controller.venda.venda_visualiza;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.view.JFvendaVisualiza;
import br.com.salomaotech.sistema.jpa.Repository;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class VendaVisualizaEventos {

    private final JFvendaVisualiza view;
    private VendaVisualizaMetodos vendaVisualizaMetodos;

    public VendaVisualizaEventos(JFvendaVisualiza view) {
        this.view = view;
    }

    public void setVendaVisualizaMetodos(VendaVisualizaMetodos vendaVisualizaMetodos) {
        this.vendaVisualizaMetodos = vendaVisualizaMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            VendaVisualizaValidador vendaVisualizaValidador = new VendaVisualizaValidador(view);

            if (vendaVisualizaValidador.isValido()) {

                VendaModelo vendaModelo = vendaVisualizaMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (vendaModelo.getId() != 0) {

                    /* atualiza a view */
                    vendaVisualizaMetodos.popularFormulario(vendaModelo);
                    vendaVisualizaMetodos.habilitarCampos();
                    JOptionPane.showMessageDialog(null, "Venda atualizada! Acompanhe as contas a receber no seu financeiro.");

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, vendaVisualizaValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(view.getId());

                /* valida se excluiu e atualiza os dados na view */
                if (vendaVisualizaMetodos.excluir()) {

                    /* realiza a movimentação de uma venda */
                    VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo);
                    vendaMovimenta.excluir();

                    /* informa que foi excluido, e fecha a view */
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

    }

}

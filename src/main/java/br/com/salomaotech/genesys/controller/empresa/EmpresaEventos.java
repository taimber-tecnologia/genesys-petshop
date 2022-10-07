package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.algoritmos.BuscaCep;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class EmpresaEventos {

    private final JFempresa view;
    private EmpresaMetodos empresaMetodos;

    public EmpresaEventos(JFempresa view) {
        this.view = view;
    }

    public void setEmpresaMetodos(EmpresaMetodos empresaMetodos) {
        this.empresaMetodos = empresaMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            EmpresaValidador empresaValidador = new EmpresaValidador(view);

            if (empresaValidador.isValido()) {

                EmpresaModelo empresaModelo = empresaMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (empresaModelo.getId() != 0) {

                    empresaMetodos.popularFormulario(empresaModelo);
                    empresaMetodos.habilitarCampos();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTbasicoNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, empresaValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (empresaMetodos.excluir()) {

                    empresaMetodos.resetarView();
                    empresaMetodos.habilitarCampos();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.jTbasicoNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* buscar cep */
        view.jBenderecoBuscarCep.addActionListener((ActionEvent e) -> {

            BuscaCep buscaCep = new BuscaCep();
            buscaCep.buscar(view.jFenderecoCep.getText());
            view.jTenderecoRua.setText(buscaCep.getLogradouro());
            view.jTenderecoBairro.setText(buscaCep.getBairro());
            view.jTenderecoCidade.setText(buscaCep.getCidade());
            view.jCenderecoUf.setSelectedItem(buscaCep.getUf());

        });

    }

}

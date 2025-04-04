package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.model.fornecedor.FornecedorModelo;
import br.com.salomaotech.genesys.model.fornecedor.FornecedorPesquisa;
import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.algoritmos.BuscaCep;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class FornecedorEventos {

    private final JFfornecedor view;
    private FornecedorMetodos fornecedorMetodos;

    public FornecedorEventos(JFfornecedor view) {
        this.view = view;
    }

    public void setFornecedorMetodos(FornecedorMetodos fornecedorMetodos) {
        this.fornecedorMetodos = fornecedorMetodos;
    }

    public void addEventos() {

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            fornecedorMetodos.resetarView();
            fornecedorMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jTabaCadastro.setSelectedIndex(0);
            view.jTbasicoNome.requestFocus();

        });

        /* salva cadastro */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            FornecedorValidador fornecedorValidador = new FornecedorValidador(view, view.getId());

            if (fornecedorValidador.isValido()) {

                FornecedorModelo fornecedorModelo = fornecedorMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (fornecedorModelo.getId() != 0) {

                    view.jTabaCadastro.setSelectedIndex(0);
                    fornecedorMetodos.popularFormulario(fornecedorModelo);
                    fornecedorMetodos.habilitarCampos();
                    new FornecedorPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTbasicoNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, fornecedorValidador.getMensagensErro());

            }

        });

        /* exclui cadastro */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (fornecedorMetodos.excluir()) {

                    fornecedorMetodos.resetarView();
                    fornecedorMetodos.habilitarCampos();
                    new FornecedorPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.jTbasicoNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            fornecedorMetodos.pesquisar();

        });

        /* abrir */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    fornecedorMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* pesquisa */
        view.jTpesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    fornecedorMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* pesquisa */
        view.jTpesquisaCnpj.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    fornecedorMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* busca pelo cep */
        view.jBenderecoBuscarCep.addActionListener((ActionEvent e) -> {

            BuscaCep buscaCep = new BuscaCep();
            buscaCep.buscar(view.jFenderecoCep.getText());
            view.jTenderecoRua.setText(buscaCep.getLogradouro());
            view.jTenderecoBairro.setText(buscaCep.getBairro());
            view.jTenderecoCidade.setText(buscaCep.getCidade());
            view.jCenderecoUf.setSelectedItem(buscaCep.getUf());

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);
            view.jTpesquisaNome.requestFocus();

        });

        /* pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            fornecedorMetodos.pesquisar();

        });

    }

}

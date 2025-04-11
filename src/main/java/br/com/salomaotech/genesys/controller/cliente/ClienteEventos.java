package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ClientePesquisa;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.algoritmos.BuscaCep;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ClienteEventos {

    private final JFcliente view;
    private ClienteMetodos clienteMetodos;

    public ClienteEventos(JFcliente view) {
        this.view = view;
    }

    public void setClienteMetodos(ClienteMetodos clienteMetodos) {
        this.clienteMetodos = clienteMetodos;
    }

    public void addEventos() {

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            clienteMetodos.resetarView();
            clienteMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jTabaCadastro.setSelectedIndex(0);
            view.jTbasicoNome.requestFocus();

        });

        /* salva cadastro */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            ClienteValidador clienteValidador = new ClienteValidador(view, view.getId());

            if (clienteValidador.isValido()) {

                ClienteModelo clienteModelo = clienteMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (clienteModelo.getId() != 0) {

                    view.jTabaCadastro.setSelectedIndex(0);
                    clienteMetodos.popularFormulario(clienteModelo);
                    clienteMetodos.habilitarCampos();
                    new ClientePesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTbasicoNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, clienteValidador.getMensagensErro());

            }

        });

        /* exclui cadastro */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (clienteMetodos.excluir()) {

                    clienteMetodos.resetarView();
                    clienteMetodos.habilitarCampos();
                    new ClientePesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.jTbasicoNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            clienteMetodos.pesquisar();

        });

        /* jtable de resultados */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    clienteMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* pesquisa */
        view.jTresultados.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                clienteMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

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

                    clienteMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

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

                    clienteMetodos.pesquisar();

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

            clienteMetodos.pesquisar();

        });

        /* atalho para resetar a pesquisa */
        view.jBpesquisaReseta.addActionListener((ActionEvent e) -> {

            clienteMetodos.resetarViewPesquisa();

        });

    }

}

package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ComboBoxProdutoCategoria;
import br.com.salomaotech.genesys.model.produto.ImagemProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoPesquisa;
import br.com.salomaotech.genesys.view.JFproduto;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ProdutoEventos {

    private final JFproduto view;
    private ProdutoMetodos produtoMetodos;
    private ComboBoxProdutoCategoria comboBoxProdutoCategoriaCadastro;
    private ComboBoxProdutoCategoria comboBoxProdutoCategoriaPesquisa;

    public ProdutoEventos(JFproduto view) {
        this.view = view;
    }

    public void setComboBoxProdutoCategoriaCadastro(ComboBoxProdutoCategoria comboBoxProdutoCategoriaCadastro) {
        this.comboBoxProdutoCategoriaCadastro = comboBoxProdutoCategoriaCadastro;
    }

    public void setComboBoxProdutoCategoriaPesquisa(ComboBoxProdutoCategoria comboBoxProdutoCategoriaPesquisa) {
        this.comboBoxProdutoCategoriaPesquisa = comboBoxProdutoCategoriaPesquisa;
    }

    public void setProdutoMetodos(ProdutoMetodos produtoMetodos) {
        this.produtoMetodos = produtoMetodos;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            ProdutoValidador produtoValidador = new ProdutoValidador(view);

            if (produtoValidador.isValido()) {

                ProdutoModelo produtoModelo = produtoMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (produtoModelo.getId() != 0) {

                    produtoMetodos.popularFormulario(produtoModelo);
                    produtoMetodos.habilitarCampos();
                    new ProdutoPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTnome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, produtoValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (produtoMetodos.excluir()) {

                    produtoMetodos.resetarView();
                    produtoMetodos.habilitarCampos();
                    new ProdutoPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
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
                    produtoMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            produtoMetodos.resetarView();
            produtoMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jTnome.requestFocus();

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            produtoMetodos.pesquisar();

        });

        /* pesquisa */
        view.jTpesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    produtoMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* refresh categoria */
        view.jBrefreshCategoria.addActionListener((ActionEvent e) -> {

            comboBoxProdutoCategoriaCadastro.preencher();
            comboBoxProdutoCategoriaPesquisa.preencher();

        });

        /* refresh categoria pesquisa */
        view.jBrefreshPesquisaCategoria.addActionListener((ActionEvent e) -> {

            comboBoxProdutoCategoriaCadastro.preencher();
            comboBoxProdutoCategoriaPesquisa.preencher();

        });

        /* adicionar foto */
        view.jBadicionaFoto.addActionListener((ActionEvent e) -> {

            if (ImagemProduto.upload(String.valueOf(view.getId())) == true) {

                new ImagemProduto().exibir(String.valueOf(view.getId()), view.jPdadosPerfilFoto);

            }

        });

        /* remover foto */
        view.jBremoveFoto.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir foto do produto?") == 0) {

                ImagemProduto.remover(String.valueOf(view.getId()), view.jPdadosPerfilFoto);

            }

        });

        /* pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            produtoMetodos.pesquisar();

        });

    }

}

package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.controller.animal.AnimalController;
import br.com.salomaotech.genesys.controller.centro_custo.CentroCustoController;
import br.com.salomaotech.genesys.controller.cliente.ClienteController;
import br.com.salomaotech.genesys.controller.produto.ProdutoController;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.model.produto.JtableProduto;
import br.com.salomaotech.genesys.model.venda.VendaComprovantePdf;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;

public class VendaEventos {

    private final JFvenda view;
    private VendaMetodos vendaMetodos;
    private ComboBoxAnimais comboBoxAnimaisCadastro;
    private ComboBoxClientes comboBoxClientesCadastro;
    private ComboBoxClientes comboBoxClientesPesquisa;
    private ComboBoxCentroCusto comboBoxCentroCusto;
    private JtableProduto jtableProduto;

    public VendaEventos(JFvenda view) {
        this.view = view;
    }

    public void setVendaMetodos(VendaMetodos vendaMetodos) {
        this.vendaMetodos = vendaMetodos;
    }

    public void setComboBoxAnimaisCadastro(ComboBoxAnimais comboBoxAnimaisCadastro) {
        this.comboBoxAnimaisCadastro = comboBoxAnimaisCadastro;
    }

    public void setComboBoxClientesCadastro(ComboBoxClientes comboBoxClientesCadastro) {
        this.comboBoxClientesCadastro = comboBoxClientesCadastro;
    }

    public void setComboBoxClientesPesquisa(ComboBoxClientes comboBoxClientesPesquisa) {
        this.comboBoxClientesPesquisa = comboBoxClientesPesquisa;
    }

    public void setComboBoxCentroCusto(ComboBoxCentroCusto comboBoxCentroCusto) {
        this.comboBoxCentroCusto = comboBoxCentroCusto;
    }

    public void setJtableProduto(JtableProduto jtableProduto) {
        this.jtableProduto = jtableProduto;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            VendaValidador vendaValidador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);

            if (vendaValidador.isValido()) {

                VendaModelo vendaModelo = vendaMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (vendaModelo.getId() != 0) {

                    /* atualiza a view */
                    vendaMetodos.popularFormulario(vendaModelo);
                    vendaMetodos.habilitarCampos();
                    new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jCcadastroNomeCliente.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, vendaValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(view.getId());

                /* valida se excluiu e atualiza os dados na view */
                if (vendaMetodos.excluir()) {

                    /* realiza a movimentação de uma venda */
                    VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo);
                    vendaMovimenta.excluir();

                    /* atualiza a view */
                    vendaMetodos.resetarView();
                    vendaMetodos.habilitarCampos();
                    new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.jCcadastroNomeCliente.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* abre */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    long id = (long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0);
                    vendaMetodos.abrirCadastro(id);

                }

            }

        });

        /* atalho para cadastro */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            vendaMetodos.resetarView();
            vendaMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jCcadastroNomeCliente.requestFocus();

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            vendaMetodos.pesquisar();

        });

        /* botão imprimir */
        view.jBimprimir.addActionListener((ActionEvent e) -> {

            new VendaComprovantePdf(new PastasSistema().getSubPastaImpressao(), view.getId()).gerar();

        });

        /* paginador */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            vendaMetodos.pesquisar();

        });

        /* atalho para cadastro de animal */
        view.jBpesquisaAnimal.addActionListener((ActionEvent e) -> {

            new AnimalController().construir();

        });

        /* atualiza a lista de animais do cliente */
        view.jBrefreshAnimais.addActionListener((ActionEvent e) -> {

            comboBoxAnimaisCadastro.preencher();

        });

        /* atualiza lista de clientes */
        view.jBrefreshCliente.addActionListener((ActionEvent e) -> {

            comboBoxClientesCadastro.preencher();
            comboBoxClientesPesquisa.preencher();

        });

        /* atualiza lista de clientes no campo de pesquisa */
        view.jBpesquisaNomeClienteRefresh.addActionListener((ActionEvent e) -> {

            comboBoxClientesCadastro.preencher();
            comboBoxClientesPesquisa.preencher();

        });

        /* atalho para cadastro de clientes */
        view.jBpesquisaCliente.addActionListener((ActionEvent e) -> {

            new ClienteController().construir();

        });

        /* adiciona item de produto */
        view.jBcadastroSelecionarProduto.addActionListener((ActionEvent e) -> {

            vendaMetodos.adicionarItemDeProduto();

        });

        /* remove item de produto */
        view.jBcadastroRemoverItem.addActionListener((ActionEvent e) -> {

            vendaMetodos.removerItemDeProduto();

        });

        /* atualiza os calculos ao atualizar a porcentagem */
        view.jTcadastroPorcentagemJuros.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaMetodos.recalcularValorTotal();

            }

        });

        /* atualiza os calculos ao atualizar o desconto */
        view.jTcadastroValorDesconto.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaMetodos.recalcularValorTotal();

            }

        });

        /* atalho para cadastro de produto */
        view.jBcadastroPesquisarItemProduto.addActionListener((ActionEvent e) -> {

            new ProdutoController().construir();

        });

        /* atualiza lista de produtos */
        view.jBcadastroRefreshItemProduto.addActionListener((ActionEvent e) -> {

            jtableProduto.preencher();
            vendaMetodos.habilitarCamposSelecaoProdutos();

        });

        /* selecionar item de produto pelo mouse */
        view.jTvendaProdutosDisponiveis.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                vendaMetodos.habilitarCamposSelecaoProdutos();

            }

        });

        /* selecionar item de produto pelo teclado */
        view.jTvendaProdutosDisponiveis.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaMetodos.habilitarCamposSelecaoProdutos();

            }

        });

        /* selecionar item de produto adicionado pelo mouse */
        view.jTvendaModeloItemSelecionados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                vendaMetodos.habilitarCamposSelecaoProdutosSelecionados();

            }

        });

        /* selecionar item de produto adicionado pelo teclado */
        view.jTvendaModeloItemSelecionados.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaMetodos.habilitarCamposSelecaoProdutosSelecionados();

            }

        });

        /* finalizar venda */
        view.jBfinalizarVenda.addActionListener((ActionEvent e) -> {

            VendaValidador vendaValidador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);

            if (vendaValidador.isValido()) {

                if (JOptionPane.showConfirmDialog(null, "Deseja finalizar esta venda?") == JOptionPane.YES_OPTION) {

                    VendaModelo vendaModelo = vendaMetodos.salvar();

                    /* valida se salvou e atualiza os dados na view */
                    if (vendaModelo.getId() != 0) {

                        /* realiza a movimentação de uma venda */
                        VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo);
                        vendaMovimenta.finalizar();

                        /* atualiza a view */
                        vendaMetodos.popularFormulario(vendaModelo);
                        vendaMetodos.habilitarCampos();
                        new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                        jtableProduto.preencher();

                        JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso.\nAcompanhe as contas a receber no seu financeiro.");

                    }

                }

            } else {

                JOptionPane.showMessageDialog(null, vendaValidador.getMensagensErro());

            }

        });

        /* reabrir venda */
        view.jBreabrirVenda.addActionListener((ActionEvent e) -> {

            VendaValidador vendaValidador = new VendaValidador(view, comboBoxAnimaisCadastro, comboBoxClientesCadastro, comboBoxCentroCusto);

            if (vendaValidador.isValido()) {

                if (JOptionPane.showConfirmDialog(null, "Deseja reabrir esta venda?") == JOptionPane.YES_OPTION) {

                    VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(view.getId());

                    if (!isNull(vendaModelo)) {

                        /* realiza a movimentação de uma venda */
                        VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo);
                        vendaMovimenta.reabrir();

                        /* atualiza a view */
                        vendaMetodos.popularFormulario(vendaModelo);
                        vendaMetodos.habilitarCampos();
                        new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                        jtableProduto.preencher();
                        JOptionPane.showMessageDialog(null, "Venda reaberta, qualquer alteração aqui irá refletir em contas a receber.");

                    }

                }

            } else {

                JOptionPane.showMessageDialog(null, vendaValidador.getMensagensErro());

            }

        });

        /* atualiza os calculos ao atualizar a entrada */
        view.jTcadastroValorEntrada.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaMetodos.recalcularValorTotal();

            }

        });

        /* refresh centro de custo */
        view.jBrefreshCadastroCentroCusto.addActionListener((ActionEvent e) -> {

            comboBoxCentroCusto.preencher();

        });

        /* atalho para novo centro de custo */
        view.jBpesquisaCentroCusto.addActionListener((ActionEvent e) -> {

            new CentroCustoController().construir();

        });

    }

}

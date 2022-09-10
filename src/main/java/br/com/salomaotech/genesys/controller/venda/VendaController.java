package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.model.produto.JtableProduto;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaController {

    private final JFvenda view = new JFvenda();
    private final ComboBoxAnimais comboBoxAnimaisCadastro = new ComboBoxAnimais(view.jCcadastroNomeAnimal);
    private final ComboBoxClientes comboBoxClientesCadastro = new ComboBoxClientes(view.jCcadastroNomeCliente, comboBoxAnimaisCadastro);
    private final ComboBoxClientes comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente, comboBoxAnimaisCadastro);
    private final ComboBoxCentroCusto comboBoxCentroCusto = new ComboBoxCentroCusto(view.jCcadastroCentroCusto);
    private final JtableProduto jtableProduto = new JtableProduto(view.jTvendaProdutosDisponiveis);
    private final VendaMetodos vendaMetodos = new VendaMetodos(view);
    private final VendaEventos vendaEventos = new VendaEventos(view);

    public VendaController() {

        /* preenche comboboxes */
        comboBoxClientesCadastro.preencher();
        comboBoxClientesPesquisa.preencher();
        comboBoxCentroCusto.preencher();

        /* preenche jtables */
        jtableProduto.preencher();

        /* metodos */
        vendaMetodos.setComboBoxAnimaisCadastro(comboBoxAnimaisCadastro);
        vendaMetodos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        vendaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        vendaMetodos.setComboBoxCentroCusto(comboBoxCentroCusto);
        vendaMetodos.setJtableProduto(jtableProduto);

        /* eventos */
        vendaEventos.setVendaMetodos(vendaMetodos);
        vendaEventos.setComboBoxAnimaisCadastro(comboBoxAnimaisCadastro);
        vendaEventos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        vendaEventos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        vendaEventos.setComboBoxCentroCusto(comboBoxCentroCusto);
        vendaEventos.setJtableProduto(jtableProduto);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jCcadastroNomeCliente.requestFocus();

        /* eventos */
        vendaEventos.addEventos();

        /* metodos */
        vendaMetodos.addPopUpMenu();
        vendaMetodos.habilitarCampos();

        /* exibe os dados */
        new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();

    }

    public VendaMetodos getVendaMetodos() {
        return vendaMetodos;
    }

}

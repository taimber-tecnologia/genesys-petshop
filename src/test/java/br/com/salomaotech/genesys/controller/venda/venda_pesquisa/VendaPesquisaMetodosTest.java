package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaPesquisaMetodosTest {

    public VendaPesquisaMetodosTest() {

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

        System.out.println("setComboBoxClientesPesquisa");
        ComboBoxClientes comboBoxClientesPesquisa = null;
        VendaPesquisaMetodos instance = null;
        instance.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

    }

    @Test
    public void testPesquisar() {

        System.out.println("pesquisar");
        VendaPesquisaMetodos instance = null;
        instance.pesquisar();

    }

    @Test
    public void testAbrirCadastro() {

        System.out.println("abrirCadastro");
        long id = 0L;
        VendaPesquisaMetodos instance = null;
        instance.abrirCadastro(id);

    }

}

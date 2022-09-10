package br.com.salomaotech.genesys.model.fornecedor;

import br.com.salomaotech.genesys.model.fornecedor.ComboBoxFornecedores;
import br.com.salomaotech.genesys.model.fornecedor.FornecedorModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxFornecedoresTest {

    private final JComboBox jComboBox = new JComboBox();
    private final FornecedorModelo fornecedorModelo = new FornecedorModelo();
    private final ComboBoxFornecedores comboBoxFornecedores = new ComboBoxFornecedores(jComboBox);

    public ComboBoxFornecedoresTest() {

        /* simula cadastro de fornecedor */
        new Repository(new FornecedorModelo()).deleteTodos();
        fornecedorModelo.setNome("Teste");
        fornecedorModelo.setCnpj("00.000.000/0001-00");
        new Repository(fornecedorModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxFornecedores.preencher();
        System.out.println("Testando ComboBoxFornecedores metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

    @Test
    public void testSelecionarItemPorId() {

        comboBoxFornecedores.selecionarItemPorId(fornecedorModelo.getId());
        System.out.println("Testando ComboBoxFornecedores metodo: selecionarItemPorId");
        assertEquals(true, comboBoxFornecedores.getIdSelecionado() == fornecedorModelo.getId());

    }

    @Test
    public void testGetIdSelecionado() {

        System.out.println("Testando ComboBoxFornecedores metodo: getIdSelecionado");
        assertEquals(true, comboBoxFornecedores.getIdSelecionado() == 0);

    }

}

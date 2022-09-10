package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxProdutoTest {

    private final JComboBox jComboBox = new JComboBox();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final ComboBoxProduto comboBoxProduto = new ComboBoxProduto(jComboBox);

    public ComboBoxProdutoTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorCusto(new BigDecimal(100));
        produtoModelo.setValorVenda(new BigDecimal(100));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxProduto.preencher();
        System.out.println("Testando classe ComboBoxProduto metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

    @Test
    public void testSelecionarItemPorId() {

        comboBoxProduto.selecionarItemPorId(produtoModelo.getId());
        System.out.println("Testando classe ComboBoxProduto metodo: selecionarItemPorId");
        assertEquals(true, comboBoxProduto.getIdSelecionado() == produtoModelo.getId());

    }

    @Test
    public void testGetIdSelecionado() {

        System.out.println("Testando classe ComboBoxProduto metodo: getIdSelecionado");
        assertEquals(true, comboBoxProduto.getIdSelecionado() == 0);

    }

}

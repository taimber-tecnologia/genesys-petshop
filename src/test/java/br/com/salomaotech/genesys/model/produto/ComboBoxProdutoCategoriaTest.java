package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxProdutoCategoriaTest {

    private final JComboBox jComboBox = new JComboBox();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final ComboBoxProdutoCategoria comboBoxProdutoCategoria = new ComboBoxProdutoCategoria(jComboBox);

    public ComboBoxProdutoCategoriaTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setCategoria("Teste categoria");
        produtoModelo.setValorVenda(new BigDecimal(100));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxProdutoCategoria.preencher();
        System.out.println("Testando classe ComboBoxProdutoCategoria metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

}

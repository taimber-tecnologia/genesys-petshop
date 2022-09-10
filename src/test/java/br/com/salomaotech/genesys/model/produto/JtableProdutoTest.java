package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.produto.JtableProduto;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

public class JtableProdutoTest {

    private final JTable jTable = new JTable();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final JtableProduto jtableProduto = new JtableProduto(jTable);

    public JtableProdutoTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorCusto(new BigDecimal(100));
        produtoModelo.setValorVenda(new BigDecimal(100));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testPreencher() {

        jtableProduto.preencher();
        System.out.println("Testando classe JtableProduto metodo: preencher");
        assertEquals(true, jTable.getRowCount() == 1);

    }

}

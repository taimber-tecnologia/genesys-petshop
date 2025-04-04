package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxItemVendaTest {

    private final JComboBox jComboBox = new JComboBox();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final ServicoModelo servicoModelo = new ServicoModelo();
    private final ComboBoxItemVenda comboBoxProduto = new ComboBoxItemVenda(jComboBox, null);

    public ComboBoxItemVendaTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        new Repository(produtoModelo).save();

        /* simula cadastro de serviços */
        new Repository(new ServicoModelo()).deleteTodos();
        servicoModelo.setNome("Teste");
        servicoModelo.setValor(new BigDecimal(100));
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

    @Test
    public void testGetObjectList() {

        System.out.println("Testando classe ComboBoxProduto metodo: getObjectList");
        assertEquals(true, comboBoxProduto.getObjecHashMap().isEmpty());

    }

}

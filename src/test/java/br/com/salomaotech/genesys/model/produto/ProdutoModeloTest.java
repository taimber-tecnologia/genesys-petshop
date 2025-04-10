package br.com.salomaotech.genesys.model.produto;

import java.math.BigDecimal;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoModeloTest {

    private final ProdutoModelo produtoModelo = new ProdutoModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe ProdutoModelo metodo: getId");
        assertEquals(true, produtoModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        produtoModelo.setId(1);
        System.out.println("Testando classe ProdutoModelo metodo: setId");
        assertEquals(true, produtoModelo.getId() == 1);

    }

    @Test
    public void testGetNome() {

        System.out.println("Testando classe ProdutoModelo metodo: getNome");
        assertEquals(true, isNull(produtoModelo.getNome()));

    }

    @Test
    public void testSetNome() {

        produtoModelo.setNome("Teste");
        System.out.println("Testando classe ProdutoModelo metodo: setNome");
        assertEquals(true, produtoModelo.getNome().equals("Teste"));

    }

    @Test
    public void testGetValorVenda() {

        System.out.println("Testando classe ProdutoModelo metodo: getValorVenda");
        assertEquals(true, produtoModelo.getValorVenda().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetValorVenda() {

        BigDecimal valorVenda = new BigDecimal(100);
        produtoModelo.setValorVenda(valorVenda);
        System.out.println("Testando classe ProdutoModelo metodo: setValorVenda");
        assertEquals(true, produtoModelo.getValorVenda().equals(valorVenda));

    }

    @Test
    public void testGetDescricao() {

        System.out.println("Testando classe ProdutoModelo metodo: getDescricao");
        assertEquals(true, isNull(produtoModelo.getDescricao()));

    }

    @Test
    public void testSetDescricao() {

        String descricao = "Teste";
        produtoModelo.setDescricao(descricao);
        System.out.println("Testando classe ProdutoModelo metodo: setDescricao");
        assertEquals(true, produtoModelo.getDescricao().equals(descricao));

    }

    @Test
    public void testGetCategoria() {

        System.out.println("Testando classe ProdutoModelo metodo: getCategoria");
        assertEquals(true, isNull(produtoModelo.getCategoria()));

    }

    @Test
    public void testSetCategoria() {

        String categoria = "Teste";
        produtoModelo.setCategoria(categoria);
        System.out.println("Testando classe ProdutoModelo metodo: setCategoria");
        assertEquals(true, produtoModelo.getCategoria().equals(categoria));

    }

    @Test
    public void testGetQuantidade() {

        System.out.println("Testando classe ProdutoModelo metodo: getQuantidade");
        assertEquals(true, produtoModelo.getQuantidade().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetQuantidade() {

        BigDecimal quantidade = new BigDecimal(100);
        produtoModelo.setQuantidade(quantidade);
        System.out.println("Testando classe ProdutoModelo metodo: setQuantidade");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidade));

    }

    @Test
    public void testGetPeso() {

        System.out.println("Testando classe ProdutoModelo metodo: getPeso");
        assertEquals(true, produtoModelo.getPeso().equals(new BigDecimal(0)));

    }

    @Test
    public void testSetPeso() {

        BigDecimal peso = new BigDecimal(100);
        produtoModelo.setPeso(peso);
        System.out.println("Testando classe ProdutoModelo metodo: setPeso");
        assertEquals(true, produtoModelo.getPeso().equals(peso));

    }

    @Test
    public void testGetMedida() {

        System.out.println("Testando classe ProdutoModelo metodo: getMedida");
        assertEquals(true, isNull(produtoModelo.getMedida()));

    }

    @Test
    public void testSetMedida() {

        String medida = "Teste";
        produtoModelo.setMedida(medida);
        System.out.println("Testando classe ProdutoModelo metodo: setMedida");
        assertEquals(true, produtoModelo.getMedida().equals(medida));

    }

}

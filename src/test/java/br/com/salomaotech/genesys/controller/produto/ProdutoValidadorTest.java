package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoValidadorTest {

    private final JFproduto view = new JFproduto();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private ProdutoValidador produtoValidador = new ProdutoValidador(view);

    public ProdutoValidadorTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorCusto(new BigDecimal(100));
        produtoModelo.setValorVenda(new BigDecimal(100));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testIsValido() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 01");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: nome */
        view.jTnome.setText(produtoModelo.getNome());
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 02");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: unidade de medida */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(0);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 03");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: valor de custo */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(produtoModelo.getValorCusto().toString());
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 04");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: valor de venda */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 05");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: quantidade */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 06");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: estoque mínimo */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(produtoModelo.getEstoqueMinimo().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 07");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: todos */
        // Obs: neste teste manter o nome diferente do que está no modelo
        view.jTnome.setText("Teste0000001");
        view.jCmedida.setSelectedIndex(0);
        view.jTvalorCusto.setText(produtoModelo.getValorCusto().toString());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        view.jTestoqueMinimo.setText(produtoModelo.getEstoqueMinimo().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 08");
        assertEquals(true, produtoValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: nome */
        view.jTnome.setText(produtoModelo.getNome());
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: unidade de medida */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(0);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor de custo */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(produtoModelo.getValorCusto().toString());
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor de venda */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: quantidade */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        view.jTestoqueMinimo.setText(null);
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 06");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: estoque mínimo */
        view.jTnome.setText(null);
        view.jCmedida.setSelectedIndex(-1);
        view.jTvalorCusto.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        view.jTestoqueMinimo.setText(produtoModelo.getEstoqueMinimo().toString());
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 07");
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos */
        // Obs: neste teste manter o nome diferente do que está no modelo
        view.jTnome.setText("Teste0000001");
        view.jCmedida.setSelectedIndex(0);
        view.jTvalorCusto.setText(produtoModelo.getValorCusto().toString());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        view.jTestoqueMinimo.setText(produtoModelo.getEstoqueMinimo().toString());
        produtoValidador = new ProdutoValidador(view);
        produtoValidador.isValido();
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 08");
        assertEquals(true, produtoValidador.getMensagensErro().length() == 0);

    }

}

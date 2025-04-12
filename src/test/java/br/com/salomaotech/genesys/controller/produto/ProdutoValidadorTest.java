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
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(BigDecimal.ONE);
        new Repository(produtoModelo).save();

    }

    @Test
    public void testIsValido() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 01");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: nome */
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 02");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: valor */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 03");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: quantidade */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 04");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: quantidade negativa */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText("-5");
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 05");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: todos (atualização) */
        view.setId(produtoModelo.getId());
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 06");
        assertEquals(true, produtoValidador.isValido());

        /* validando usando filtro: todos (atualização quantidade negativo) */
        view.setId(produtoModelo.getId());
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText("-5");
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 07");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: todos (novo registro com o mesmo nome) */
        view.setId(0);
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 08");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: todos (novo registro quantidade negativo) */
        view.setId(0);
        view.jTnome.setText("Novo nome");
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText("-5");
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 09");
        assertEquals(false, produtoValidador.isValido());

        /* validando usando filtro: todos (novo registro com o nome diferente) */
        view.setId(0);
        view.jTnome.setText("Novo nome");
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: isValido etapa 10");
        assertEquals(true, produtoValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* validando usando filtro: nenhum */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 01");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);


        /* validando usando filtro: nome */
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 02");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: valor */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(null);
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 03");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: quantidade */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 04");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: quantidade negativa */
        view.jTnome.setText(null);
        view.jTvalorVenda.setText(null);
        view.jTquantidade.setText("-5");
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 05");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos (atualização) */
        view.setId(produtoModelo.getId());
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 06");
        produtoValidador.isValido();
        assertEquals(false, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos (atualização quantidade negativo) */
        view.setId(produtoModelo.getId());
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText("-5");
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 07");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos (novo registro com o mesmo nome) */
        view.setId(0);
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 08");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos (novo registro quantidade negativo) */
        view.setId(0);
        view.jTnome.setText("Novo nome");
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText("-5");
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 09");
        produtoValidador.isValido();
        assertEquals(true, produtoValidador.getMensagensErro().length() > 0);

        /* validando usando filtro: todos (novo registro com o nome diferente) */
        view.setId(0);
        view.jTnome.setText("Novo nome");
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        produtoValidador = new ProdutoValidador(view);
        System.out.println("Testando classe ProdutoValidador metodo: getMensagensErro etapa 10");
        produtoValidador.isValido();
        assertEquals(false, produtoValidador.getMensagensErro().length() > 0);

    }

}

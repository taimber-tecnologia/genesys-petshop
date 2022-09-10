package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.controller.fornecedor.FornecedorValidador;
import br.com.salomaotech.genesys.model.fornecedor.FornecedorModelo;
import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class FornecedorValidadorTest {

    private final JFfornecedor view = new JFfornecedor();
    private final FornecedorModelo fornecedorModelo = new FornecedorModelo();
    private FornecedorValidador fornecedorValidador;

    public FornecedorValidadorTest() {

        /* simula cadastro de fornecedor */
        new Repository(new FornecedorModelo()).deleteTodos();
        fornecedorModelo.setNome("Teste");
        fornecedorModelo.setCnpj("00.000.000/0001-00");
        new Repository(fornecedorModelo).save();

    }

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setValue(null);
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        System.out.println("Testando classe FornecedorValidador metodo: isValido etapa 01");
        assertEquals(false, fornecedorValidador.isValido());

        /* usando filtro: nome */
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setValue(null);
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        System.out.println("Testando classe FornecedorValidador metodo: isValido etapa 02");
        assertEquals(false, fornecedorValidador.isValido());

        /* usando filtro: cnpj */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        System.out.println("Testando classe FornecedorValidador metodo: isValido etapa 03");
        assertEquals(false, fornecedorValidador.isValido());

        /* usando filtro: nascimento */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setValue(null);
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        System.out.println("Testando classe FornecedorValidador metodo: isValido etapa 04");
        assertEquals(false, fornecedorValidador.isValido());

        /* usando filtro: todos */
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        System.out.println("Testando classe FornecedorValidador metodo: isValido etapa 05");
        assertEquals(true, fornecedorValidador.isValido());

        /* usando filtro: cnpj já em uso */
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());

        fornecedorValidador = new FornecedorValidador(view, 0);
        System.out.println("Testando classe FornecedorValidador metodo: isValido etapa 05");
        assertEquals(false, fornecedorValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setValue(null);
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        fornecedorValidador.isValido();
        System.out.println("Testando classe FornecedorValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, fornecedorValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome */
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setValue(null);
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        fornecedorValidador.isValido();
        System.out.println("Testando classe FornecedorValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, fornecedorValidador.getMensagensErro().length() > 0);

        /* usando filtro: cnpj */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        fornecedorValidador.isValido();
        System.out.println("Testando classe FornecedorValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, fornecedorValidador.getMensagensErro().length() > 0);

        /* usando filtro: nascimento */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setValue(null);
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        fornecedorValidador.isValido();
        System.out.println("Testando classe FornecedorValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, fornecedorValidador.getMensagensErro().length() > 0);

        /* usando filtro: todos */
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorValidador = new FornecedorValidador(view, fornecedorModelo.getId());
        fornecedorValidador.isValido();
        System.out.println("Testando classe FornecedorValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, fornecedorValidador.getMensagensErro().length() == 0);

        /* usando filtro: cnpj já em uso */
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorValidador = new FornecedorValidador(view, 0);
        fornecedorValidador.isValido();
        System.out.println("Testando classe FornecedorValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, fornecedorValidador.getMensagensErro().length() > 0);

    }

}

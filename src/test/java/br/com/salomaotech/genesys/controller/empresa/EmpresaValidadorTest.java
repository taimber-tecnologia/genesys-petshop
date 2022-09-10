package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.controller.empresa.EmpresaValidador;
import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaValidadorTest {

    private final JFempresa view = new JFempresa();
    private final EmpresaModelo empresaModelo = new EmpresaModelo();
    private EmpresaValidador empresaValidador;

    public EmpresaValidadorTest() {

        /* simula cadastro de empresa */
        new Repository(new EmpresaModelo()).deleteTodos();
        empresaModelo.setNome("Teste");
        empresaModelo.setCnpj("78.498.603/0001-52");
        empresaModelo.setCep("75370-000");
        empresaModelo.setRua("01");
        empresaModelo.setQuadra("02");
        empresaModelo.setLote("03");
        empresaModelo.setNumero("04");
        empresaModelo.setUf("GO");
        empresaModelo.setBairro("A");
        empresaModelo.setCidade("B");
        empresaModelo.setComplemento("C");
        empresaModelo.setTelefone("62 0000-0000");
        empresaModelo.setEmail("abc123@email.com");
        new Repository(empresaModelo).save();

    }

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setText(null);
        empresaValidador = new EmpresaValidador(view);
        System.out.println("Testando classe EmpresaValidador metodo: isValido etapa 01");
        assertEquals(false, empresaValidador.isValido());

        /* usando filtro: nome */
        view.jTbasicoNome.setText(empresaModelo.getNome());
        view.jFbasicoCnpj.setValue(null);
        empresaValidador = new EmpresaValidador(view);
        System.out.println("Testando classe EmpresaValidador metodo: isValido etapa 02");
        assertEquals(false, empresaValidador.isValido());

        /* usando filtro: CNPJ */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setText(empresaModelo.getCnpj());
        empresaValidador = new EmpresaValidador(view);
        System.out.println("Testando classe EmpresaValidador metodo: isValido etapa 03");
        assertEquals(false, empresaValidador.isValido());

        /* usando filtro: todos */
        view.jTbasicoNome.setText(empresaModelo.getNome());
        view.jFbasicoCnpj.setText(empresaModelo.getCnpj());
        empresaValidador = new EmpresaValidador(view);
        System.out.println("Testando classe EmpresaValidador metodo: isValido etapa 04");
        assertEquals(true, empresaValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setText(null);
        empresaValidador = new EmpresaValidador(view);
        empresaValidador.isValido();
        System.out.println("Testando classe EmpresaValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, empresaValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome */
        view.jTbasicoNome.setText(empresaModelo.getNome());
        view.jFbasicoCnpj.setValue(null);
        empresaValidador = new EmpresaValidador(view);
        empresaValidador.isValido();
        System.out.println("Testando classe EmpresaValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, empresaValidador.getMensagensErro().length() > 0);

        /* usando filtro: CNPJ */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCnpj.setText(empresaModelo.getCnpj());
        empresaValidador = new EmpresaValidador(view);
        empresaValidador.isValido();
        System.out.println("Testando classe EmpresaValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, empresaValidador.getMensagensErro().length() > 0);

        /* usando filtro: todos */
        view.jTbasicoNome.setText(empresaModelo.getNome());
        view.jFbasicoCnpj.setText(empresaModelo.getCnpj());
        empresaValidador = new EmpresaValidador(view);
        empresaValidador.isValido();
        System.out.println("Testando classe EmpresaValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, empresaValidador.getMensagensErro().length() == 0);

    }

}

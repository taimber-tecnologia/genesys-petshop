package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoMetodosTest {

    public ServicoMetodosTest() {

    }

    @Test
    public void testPopularFormulario() {

        System.out.println("popularFormulario");
        ServicoModelo servicoModelo = null;
        ServicoMetodos instance = null;
        instance.popularFormulario(servicoModelo);

    }

    @Test
    public void testResetarView() {

        System.out.println("resetarView");
        ServicoMetodos instance = null;
        instance.resetarView();

    }

    @Test
    public void testHabilitarCampos() {

        System.out.println("habilitarCampos");
        ServicoMetodos instance = null;
        instance.habilitarCampos();

    }

    @Test
    public void testAddPopUpMenu() {

        System.out.println("addPopUpMenu");
        ServicoMetodos instance = null;
        instance.addPopUpMenu();

    }

    @Test
    public void testAbrirCadastro() {

        System.out.println("abrirCadastro");
        long id = 0L;
        ServicoMetodos instance = null;
        instance.abrirCadastro(id);

    }

    @Test
    public void testSalvar() {

        System.out.println("salvar");
        ServicoMetodos instance = null;
        ServicoModelo expResult = null;
        ServicoModelo result = instance.salvar();
        assertEquals(expResult, result);

    }

    @Test
    public void testExcluir() {

        System.out.println("excluir");
        ServicoMetodos instance = null;
        boolean expResult = false;
        boolean result = instance.excluir();
        assertEquals(expResult, result);

    }

    @Test
    public void testPesquisar() {

        System.out.println("pesquisar");
        ServicoMetodos instance = null;
        instance.pesquisar();

    }

}

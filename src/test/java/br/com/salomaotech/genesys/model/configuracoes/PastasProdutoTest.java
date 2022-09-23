package br.com.salomaotech.genesys.model.configuracoes;

import org.junit.Test;
import static org.junit.Assert.*;

public class PastasProdutoTest {

    private final String pathLocal = System.getProperty("user.dir");

    @Test
    public void testGetPastaRaiz() {

        System.out.println("Testando classe PastasProduto método: getPastaRaiz");
        assertEquals(true, new PastasProduto("1").getPastaRaiz().equals(pathLocal + "/target/arquivos/produto/1/"));

    }

    @Test
    public void testGetSubPastaDeFotosDoPerfil() {

        System.out.println("Testando classe PastasProduto método: getSubPastaDeFotosDoPerfil");
        assertEquals(true, new PastasProduto("1").getSubPastaDeFotosDoPerfil().equals(pathLocal + "/target/arquivos/produto/1/foto_produto/"));

    }

    @Test
    public void testGetSubPastaDeTemporarios() {

        System.out.println("Testando classe PastasProduto método: getSubPastaDeTemporarios");
        assertEquals(true, new PastasProduto("1").getSubPastaDeTemporarios().equals(pathLocal + "/target/arquivos/produto/1/temporario/"));

    }

}

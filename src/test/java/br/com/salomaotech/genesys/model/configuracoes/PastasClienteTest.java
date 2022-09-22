package br.com.salomaotech.genesys.model.configuracoes;

import org.junit.Test;
import static org.junit.Assert.*;

public class PastasClienteTest {

    private final String pathLocal = System.getProperty("user.dir");

    @Test
    public void testGetPastaRaiz() {

        System.out.println("Testando classe PastasCliente método: getPastaRaiz");
        assertEquals(true, new PastasCliente("1").getPastaRaiz().equals(pathLocal + "/target/arquivos/cliente/1/"));

    }

    @Test
    public void testGetSubPastaDeFotosDoPerfil() {

        System.out.println("Testando classe PastasCliente método: getSubPastaDeFotosDoPerfil");
        assertEquals(true, new PastasCliente("1").getSubPastaDeFotosDoPerfil().equals(pathLocal + "/target/arquivos/cliente/1/foto_perfil/"));

    }

    @Test
    public void testGetSubPastaDeTemporarios() {

        System.out.println("Testando classe PastasCliente método: getSubPastaDeTemporarios");
        assertEquals(true, new PastasCliente("1").getSubPastaDeTemporarios().equals(pathLocal + "/target/arquivos/cliente/1/temporario/"));

    }

    @Test
    public void testGetSubPastaDeAnexosVenda() {

        System.out.println("Testando classe PastasCliente método: getSubPastaDeAnexosVenda");
        assertEquals(true, new PastasCliente("1").getSubPastaDeAnexosVenda("1").equals(pathLocal + "/target/arquivos/cliente/1/venda/1"));

    }

    @Test
    public void testGetSubPastaDeAnexosDebito() {

        System.out.println("Testando classe PastasCliente método: getSubPastaDeAnexosDebito");
        assertEquals(true, new PastasCliente("1").getSubPastaDeAnexosDebito("1").equals(pathLocal + "/target/arquivos/cliente/1/debito/1"));

    }

    @Test
    public void testGetSubPastaDeAnexosAnamnese() {

        System.out.println("Testando classe PastasCliente método: getSubPastaDeAnexosAnamnese");
        assertEquals(true, new PastasCliente("1").getSubPastaDeAnexosAnamnese("1").equals(pathLocal + "/target/arquivos/cliente/1/anamnese/1"));

    }

}

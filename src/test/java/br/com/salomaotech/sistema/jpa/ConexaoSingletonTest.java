package br.com.salomaotech.sistema.jpa;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConexaoSingletonTest {

    @Test
    public void testAbrir() {

        ConexaoSingleton conexao = new ConexaoSingleton("Conexao");
        conexao.abrir();

        System.out.println("Testando classe ConexaoSingleton metodo: abrir");
        assertEquals(true, conexao.getManager().isOpen());

    }

    @Test
    public void testFechar() {

        ConexaoSingleton conexao = new ConexaoSingleton("Conexao");
        conexao.abrir();
        conexao.fechar();

        System.out.println("Testando classe ConexaoSingleton metodo: fechar");
        assertEquals(false, conexao.getManager().isOpen());

    }

    @Test
    public void testGetManager() {

        ConexaoSingleton conexao = new ConexaoSingleton("Conexao");
        conexao.abrir();

        System.out.println("Testando classe ConexaoSingleton metodo: getManager");
        assertEquals(false, isNull(conexao.getManager()));

    }

}

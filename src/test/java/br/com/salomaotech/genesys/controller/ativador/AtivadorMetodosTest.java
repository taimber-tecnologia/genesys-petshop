package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.model.ativador.Ativador;
import br.com.salomaotech.genesys.view.JFativador;
import br.com.salomaotech.sistema.swing.PopUp;
import org.junit.Test;
import static org.junit.Assert.*;

public class AtivadorMetodosTest {

    private final JFativador view = new JFativador();
    private final AtivadorMetodos metodos = new AtivadorMetodos(view);

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        metodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se o menu de popup foi adicionado */
        System.out.println("Testando classe AtivadorMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTchave));

    }

    @Test
    public void testIsAtivar() {

        System.out.println("Testando classe AtivadorMetodos metodo: isAtivar etapa 01 chave valida");
        assertEquals(true, metodos.isAtivar("ef7aca675c9ac5f23d01826f9e78d926"));

        System.out.println("Testando classe AtivadorMetodos metodo: isAtivar etapa 02 chave invalida");
        assertEquals(false, metodos.isAtivar("123456"));

        System.out.println("Testando classe AtivadorMetodos metodo: isAtivar etapa 03 chave invalida");
        assertEquals(false, metodos.isAtivar("ABC"));

        System.out.println("Testando classe AtivadorMetodos metodo: isAtivar etapa 03 chave invalida");
        assertEquals(false, metodos.isAtivar(""));

    }

    @Test
    public void testExibeDiasRestantes() {

        /* exibe os dias restantes */
        metodos.exibeDiasRestantes();

        /* testa se os dados populados são válidos */
        System.out.println("Testando classe AtivadorMetodos metodo: exibeDiasRestantes");
        assertEquals(true, view.jLdiasRestantes.getText().equals(String.valueOf(new Ativador().getDiasRestantes())));

    }

}

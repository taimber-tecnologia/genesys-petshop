package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.genesys.view.JFconfiguracoes;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ConfiguracoesEventosTest {

    private final JFconfiguracoes view = new JFconfiguracoes();
    private final ConfiguracoesEventos configuracoesEventos = new ConfiguracoesEventos(view);

    @Test
    public void testSetConfiguracoesMetodos() {

        boolean isErro = false;

        try {

            configuracoesEventos.setConfiguracoesMetodos(new ConfiguracoesMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ConfiguracoesEventos metodo: setConfiguracoesMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        configuracoesEventos.addEventos();
        System.out.println("Testando classe ConfiguracoesEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBconectar.getActionListeners().length == 1);
        assertEquals(true, view.jBpastaDeArquivosSeleciona.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);

    }

}

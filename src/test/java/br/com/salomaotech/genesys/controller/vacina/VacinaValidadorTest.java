package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.controller.vacina.VacinaValidador;
import br.com.salomaotech.genesys.view.JFvacina;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacinaValidadorTest {

    private final JFvacina view = new JFvacina();
    private VacinaValidador vacinaValidador = new VacinaValidador(view);

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jCcadastroNome.getEditor().setItem("");
        view.jDcadastroDataAplicacao.setCalendar(null);
        vacinaValidador = new VacinaValidador(view);
        System.out.println("Testando classe VacinaValidador metodo: isValido etapa 01");
        assertEquals(false, vacinaValidador.isValido());

        /* usando filtro: nome da vacina */
        view.jCcadastroNome.getEditor().setItem("Teste");
        view.jDcadastroDataAplicacao.setCalendar(null);
        vacinaValidador = new VacinaValidador(view);
        System.out.println("Testando classe VacinaValidador metodo: isValido etapa 02");
        assertEquals(false, vacinaValidador.isValido());

        /* usando filtro: data de aplicação */
        view.jCcadastroNome.getEditor().setItem(null);
        view.jDcadastroDataAplicacao.setCalendar(Calendar.getInstance());
        vacinaValidador = new VacinaValidador(view);
        System.out.println("Testando classe VacinaValidador metodo: isValido etapa 03");
        assertEquals(false, vacinaValidador.isValido());

        /* usando filtro: data de aplicação */
        view.jCcadastroNome.getEditor().setItem("Teste");
        view.jDcadastroDataAplicacao.setCalendar(Calendar.getInstance());
        vacinaValidador = new VacinaValidador(view);
        System.out.println("Testando classe VacinaValidador metodo: isValido etapa 04");
        assertEquals(true, vacinaValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jCcadastroNome.getEditor().setItem("");
        view.jDcadastroDataAplicacao.setCalendar(null);
        vacinaValidador = new VacinaValidador(view);
        vacinaValidador.isValido();
        System.out.println("Testando classe VacinaValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, vacinaValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome da vacina */
        view.jCcadastroNome.getEditor().setItem("Teste");
        view.jDcadastroDataAplicacao.setCalendar(null);
        vacinaValidador = new VacinaValidador(view);
        vacinaValidador.isValido();
        System.out.println("Testando classe VacinaValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, vacinaValidador.getMensagensErro().length() > 0);

        /* usando filtro: data de aplicação */
        view.jCcadastroNome.getEditor().setItem(null);
        view.jDcadastroDataAplicacao.setCalendar(Calendar.getInstance());
        vacinaValidador = new VacinaValidador(view);
        vacinaValidador.isValido();
        System.out.println("Testando classe VacinaValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, vacinaValidador.getMensagensErro().length() > 0);

        /* usando filtro: data de aplicação */
        view.jCcadastroNome.getEditor().setItem("Teste");
        view.jDcadastroDataAplicacao.setCalendar(Calendar.getInstance());
        vacinaValidador = new VacinaValidador(view);
        vacinaValidador.isValido();
        System.out.println("Testando classe VacinaValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, vacinaValidador.getMensagensErro().length() == 0);

    }

}

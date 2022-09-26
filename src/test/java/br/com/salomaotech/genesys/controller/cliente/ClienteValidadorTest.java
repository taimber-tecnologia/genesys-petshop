package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteValidadorTest {

    private final JFcliente view = new JFcliente();
    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private ClienteValidador clienteValidador;

    public ClienteValidadorTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("785.535.970-24");
        clienteModelo.setNascimento(calendar);
        clienteModelo.setTelefone("62 0000-0000");
        new Repository(clienteModelo).save();

    }

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 01");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: nome */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 02");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: cpf */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 03");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: nascimento */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 04");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: telefone */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setCalendar(null);
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 05");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: todos */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 06");
        assertEquals(true, clienteValidador.isValido());

        /* usando filtro: cpf já em uso */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, 0);
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 07");
        assertEquals(false, clienteValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: cpf */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: nascimento */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: telefone */
        view.jTbasicoNome.setText(null);
        view.jFbasicoCpf.setValue(null);
        view.jDbasicoDataNascimento.setCalendar(null);
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: todos */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 06");
        assertEquals(true, clienteValidador.getMensagensErro().length() == 0);

        /* usando filtro: cpf já em uso */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 07");
        assertEquals(true, clienteValidador.getMensagensErro().length() > 0);

    }

}

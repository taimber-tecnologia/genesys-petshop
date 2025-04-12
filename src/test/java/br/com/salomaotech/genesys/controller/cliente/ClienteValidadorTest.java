package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import javax.swing.JTextField;
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
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 01");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: nome */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 02");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: telefone */
        view.jTbasicoNome.setText(null);
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 03");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: nome e telefone iguais (deve falhar) */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, 0); // ID diferente para simular novo cadastro
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 04");
        assertEquals(false, clienteValidador.isValido());

        /* usando filtro: nome igual mas telefone diferente (deve passar) */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jTcontatoTelefone.setText("62 1111-1111"); // Telefone diferente
        clienteValidador = new ClienteValidador(view, 0); // ID diferente para simular novo cadastro
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 05");
        assertEquals(true, clienteValidador.isValido());

        /* usando filtro: todos válidos (edição do mesmo registro) */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 06");
        assertEquals(true, clienteValidador.isValido());

        /* usando CPF inválido */
        view.jTbasicoNome.setText("Novo Cliente");
        view.jFbasicoCpf.setText("123.456.789-00"); // CPF inválido
        view.jDbasicoDataNascimento.setDate(calendar.getTime());
        view.jTcontatoTelefone.setText("62 2222-2222");
        clienteValidador = new ClienteValidador(view, 0);
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 07");
        assertEquals(false, clienteValidador.isValido());

        /* usando CPF válido mas já existente (deve falhar) */
        view.jTbasicoNome.setText("Outro Cliente");
        view.jFbasicoCpf.setText(clienteModelo.getCpf()); // CPF já cadastrado
        view.jDbasicoDataNascimento.setDate(calendar.getTime());
        view.jTcontatoTelefone.setText("62 3333-3333");
        clienteValidador = new ClienteValidador(view, 0);
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 08");
        assertEquals(false, clienteValidador.isValido());

        /* usando CPF válido e novo (deve passar) */
        view.jTbasicoNome.setText("Cliente Válido");
        view.jFbasicoCpf.setText("304.364.080-23"); // CPF válido e diferente
        view.jDbasicoDataNascimento.setDate(calendar.getTime());
        view.jTcontatoTelefone.setText("62 4444-4444");
        clienteValidador = new ClienteValidador(view, 0);
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 09");
        assertEquals(true, clienteValidador.isValido());

        /* usando data inválida */
        view.jTbasicoNome.setText("Cliente com data inválida");
        view.jFbasicoCpf.setText("703.765.650-36"); // CPF válido
        ((JTextField) view.jDbasicoDataNascimento.getDateEditor().getUiComponent()).setText("99/99/9999"); // Data inválida
        view.jTcontatoTelefone.setText("62 5555-5555");
        clienteValidador = new ClienteValidador(view, 0);
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 10");
        assertEquals(false, clienteValidador.isValido());

        /* usando todos os campos válidos */
        view.jTbasicoNome.setText("Cliente com data válida");
        view.jFbasicoCpf.setText("690.241.880-24"); // CPF válido
        view.jDbasicoDataNascimento.setCalendar(calendar); // Data válida
        view.jTcontatoTelefone.setText("62 1234-1234"); // Telefone válido
        clienteValidador = new ClienteValidador(view, 0);
        System.out.println("Testando classe ClienteValidador metodo: isValido etapa 11");
        assertEquals(true, clienteValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jTbasicoNome.setText(null);
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 01");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(null);
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 02");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: telefone */
        view.jTbasicoNome.setText(null);
        view.jDbasicoDataNascimento.setDate(null);
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 03");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome e telefone iguais (deve falhar) */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 04");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome igual mas telefone diferente (deve passar) */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText("62 1111-1111");
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 05");
        assertEquals(0, clienteValidador.getMensagensErro().length());

        /* usando filtro: todos válidos (edição do mesmo registro) */
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        clienteValidador = new ClienteValidador(view, clienteModelo.getId());
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 06");
        assertEquals(0, clienteValidador.getMensagensErro().length());

        /* usando CPF inválido */
        view.jTbasicoNome.setText("Novo Cliente");
        view.jFbasicoCpf.setText("123.456.789-00"); // CPF inválido
        view.jDbasicoDataNascimento.setDate(calendar.getTime());
        view.jTcontatoTelefone.setText("62 2222-2222");
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 07");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando CPF válido mas já existente (deve falhar) */
        view.jTbasicoNome.setText("Outro Cliente");
        view.jFbasicoCpf.setText(clienteModelo.getCpf()); // CPF já cadastrado
        view.jDbasicoDataNascimento.setDate(calendar.getTime());
        view.jTcontatoTelefone.setText("62 3333-3333");
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 08");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando CPF válido e novo (deve passar) */
        view.jTbasicoNome.setText("Cliente Válido");
        view.jFbasicoCpf.setText("304.364.080-23"); // CPF válido e diferente
        view.jDbasicoDataNascimento.setDate(calendar.getTime());
        view.jTcontatoTelefone.setText("62 4444-4444");
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 09");
        assertEquals(0, clienteValidador.getMensagensErro().length());

        /* usando data inválida */
        view.jTbasicoNome.setText("Cliente com data inválida");
        view.jFbasicoCpf.setText("703.765.650-36"); // CPF válido
        ((JTextField) view.jDbasicoDataNascimento.getDateEditor().getUiComponent()).setText("99/99/9999"); // Data inválida
        view.jTcontatoTelefone.setText("62 5555-5555");
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 10");
        assertTrue(clienteValidador.getMensagensErro().length() > 0);

        /* usando todos os campos válidos */
        view.jTbasicoNome.setText("Cliente com data válida");
        view.jFbasicoCpf.setText("690.241.880-24"); // CPF válido
        view.jDbasicoDataNascimento.setCalendar(calendar); // Data válida
        view.jTcontatoTelefone.setText("62 1234-1234"); // Telefone válido
        clienteValidador = new ClienteValidador(view, 0);
        clienteValidador.isValido();
        System.out.println("Testando classe ClienteValidador metodo: getMensagensErro etapa 11");
        assertEquals(0, clienteValidador.getMensagensErro().length());

    }

}

package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.jpa.Repository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AnimalValidadorTest {

    private final JFanimal view = new JFanimal();
    private final ComboBoxClientes comboBoxClientes;
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private AnimalValidador animalValidador;

    public AnimalValidadorTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* carrega a lista de clientes */
        comboBoxClientes = new ComboBoxClientes(view.jCnomeClienteCadastro);
        comboBoxClientes.preencher();

    }

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 01");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: nome */
        view.jTnomeCadastro.setText("Teste");
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 02");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: data de nascimento */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 03");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: peso */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText("Vinte quilos");
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 04");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: peso */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText("123,456,789");
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 05");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: especie */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedItem("Cachorro");
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 06");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: raça */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("Poodle");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 07");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: nome com ID do cliente */
        view.jTnomeCadastro.setText("Teste");
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 08");
        assertEquals(false, new AnimalValidador(view).isValido());

        /* usando filtro: todos */
        view.jTnomeCadastro.setText("Teste");
        view.jTpesoCadastro.setText("20");
        view.jCespecieCadastro.getEditor().setItem("Cachorro");
        view.jCracaCadastro.getEditor().setItem("Poodle");
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        System.out.println("Testando classe AnimalValidador metodo: isValido etapa 09");
        assertEquals(true, new AnimalValidador(view).isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 01");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome */
        view.jTnomeCadastro.setText("Teste");
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 02");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: data de nascimento */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 03");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: peso */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText("Vinte quilos");
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 04");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: peso */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText("123,456,789");
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 05");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: especie */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedItem("Cachorro");
        view.jCracaCadastro.getEditor().setItem("");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 06");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: raça */
        view.jTnomeCadastro.setText(null);
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("Poodle");
        view.jCnomeClienteCadastro.setSelectedIndex(0);
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 07");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro: nome com ID do cliente */
        view.jTnomeCadastro.setText("Teste");
        view.jTpesoCadastro.setText(null);
        view.jCespecieCadastro.setSelectedIndex(-1);
        view.jCracaCadastro.getEditor().setItem("");
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 08");
        assertEquals(true, animalValidador.getMensagensErro().length() > 0);

        /* usando filtro:  */
        view.jTnomeCadastro.setText("Teste");
        view.jTpesoCadastro.setText("20");
        view.jCespecieCadastro.setSelectedItem("Cachorro");
        view.jCracaCadastro.getEditor().setItem("Poodle");
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        animalValidador = new AnimalValidador(view);
        animalValidador.isValido();
        System.out.println("Testando classe getMensagensErro metodo: isValido etapa 09");
        assertEquals(true, animalValidador.getMensagensErro().length() == 0);

    }

}

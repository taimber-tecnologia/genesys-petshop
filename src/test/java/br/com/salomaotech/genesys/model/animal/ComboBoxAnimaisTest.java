package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxAnimaisTest {

    private final JComboBox jComboBox = new JComboBox();
    private ComboBoxAnimais comboBoxAnimais;
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ClienteModelo clienteModelo = new ClienteModelo();

    public ComboBoxAnimaisTest() {

        /* salva o cadastro do cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* salva cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

    }

    @Test
    public void testPreencher() {

        /* usa o construtor sem ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox);
        comboBoxAnimais.preencher();
        System.out.println("Testando classe ComboBoxAnimais metodo: preencher etapa 01");
        assertEquals(true, jComboBox.getItemCount() == 1);

        /* usa o construtor com ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox, clienteModelo.getId());
        comboBoxAnimais.preencher();
        System.out.println("Testando classe ComboBoxAnimais metodo: preencher etapa 02");
        assertEquals(true, jComboBox.getItemCount() == 2);

        /* usa o construtor com ID do cliente inexistente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox, 123456);
        comboBoxAnimais.preencher();
        System.out.println("Testando classe ComboBoxAnimais metodo: preencher etapa 03");
        assertEquals(true, jComboBox.getItemCount() == 1);

    }

    @Test
    public void testSelecionarItemComBox() {

        /* usa o construtor sem ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox);
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        System.out.println("Testando classe ComboBoxAnimais metodo: selecionarItemComBox etapa 01");
        assertEquals(true, comboBoxAnimais.getIdAnimalSelecionado() == 0);

        /* usa o construtor com ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox, clienteModelo.getId());
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        System.out.println("Testando classe ComboBoxAnimais metodo: selecionarItemComBox etapa 02");
        assertEquals(true, comboBoxAnimais.getIdAnimalSelecionado() == animalModelo.getId());

        /* usa o construtor com ID do cliente inexistente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox, 123456);
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        System.out.println("Testando classe ComboBoxAnimais metodo: selecionarItemComBox etapa 03");
        assertEquals(true, comboBoxAnimais.getIdAnimalSelecionado() == 0);

    }

    @Test
    public void testExecutar() {

        /* usa o construtor sem ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox);
        comboBoxAnimais.executar(animalModelo.getIdCliente());
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

    @Test
    public void testGetIdAnimalSelecionado() {

        /* usa o construtor sem ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox);
        System.out.println("Testando classe ComboBoxAnimais metodo: getIdAnimalSelecionado etapa 01");
        assertEquals(true, comboBoxAnimais.getIdAnimalSelecionado() == 0);

        /* usa o construtor com ID do cliente */
        comboBoxAnimais = new ComboBoxAnimais(jComboBox, clienteModelo.getId());
        System.out.println("Testando classe ComboBoxAnimais metodo: getIdAnimalSelecionado etapa 02");
        assertEquals(true, comboBoxAnimais.getIdAnimalSelecionado() == 0);

    }

}

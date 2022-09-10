package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.model.animal.AnimalPesquisa;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalEspecie;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalRaca;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class AnimalController {

    private final JFanimal view = new JFanimal();
    private final ComboBoxClientes comboBoxClientesCadastro = new ComboBoxClientes(view.jCnomeClienteCadastro);
    private final ComboBoxClientes comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);
    private final ComboBoxAnimalRaca comboBoxAnimalRaca = new ComboBoxAnimalRaca(view.jCracaCadastro);
    private final ComboBoxAnimalEspecie comboBoxAnimalEspecie = new ComboBoxAnimalEspecie(view.jCespecieCadastro);
    private final AnimalMetodos animalMetodos = new AnimalMetodos(view);
    private final AnimalEventos animalEventos = new AnimalEventos(view);

    public AnimalController() {

        /* preenche comboboxes */
        comboBoxClientesCadastro.preencher();
        comboBoxClientesPesquisa.preencher();
        comboBoxAnimalRaca.preencher();
        comboBoxAnimalEspecie.preencher();

        /* metodos */
        animalMetodos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        animalMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

        /* eventos */
        animalEventos.setAnimalMetodos(animalMetodos);
        animalEventos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        animalEventos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        animalEventos.setComboBoxAnimalRaca(comboBoxAnimalRaca);
        animalEventos.setComboBoxAnimalEspecie(comboBoxAnimalEspecie);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("animal64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTnomeCadastro.requestFocus();

        /* metodos */
        animalMetodos.addPopUpMenu();
        animalMetodos.habilitarCampos();

        /* eventos */
        animalEventos.addEventos();

        /* exibe os dados */
        new AnimalPesquisa(view.jTresultados, view.jCpaginador).pesquisar();

    }

}

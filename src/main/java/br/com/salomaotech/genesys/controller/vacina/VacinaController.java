package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.vacina.ComboBoxVacinas;
import br.com.salomaotech.genesys.view.JFvacina;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VacinaController {

    private final JFvacina view = new JFvacina();
    private final ComboBoxVacinas comboBoxVacinas;
    private final VacinaMetodos vacinaMetodos;
    private final VacinaEventos vacinaEventos = new VacinaEventos(view);

    public VacinaController(AnimalModelo animalModelo) {

        /* combobox vacinas */
        comboBoxVacinas = new ComboBoxVacinas(view.jCcadastroNome);
        comboBoxVacinas.preencher();

        /* metodos */
        vacinaMetodos = new VacinaMetodos(view, animalModelo);

        /* eventos */
        vacinaEventos.setVacinaMetodos(vacinaMetodos);
        vacinaEventos.setAnimalModelo(animalModelo);
        vacinaEventos.setComboBoxVacinas(comboBoxVacinas);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("vacina64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaPrincipal.setSelectedIndex(1);
        view.jCcadastroNome.requestFocus();

        /* metodos */
        vacinaMetodos.addPopUpMenu();
        vacinaMetodos.habilitarCampos();

        /* eventos */
        vacinaEventos.addEventos();

        /* exibe os dados */
        vacinaMetodos.pesquisar();

    }

}

package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.vacina.VacinaModelo;
import br.com.salomaotech.genesys.model.vacina.VacinaPesquisa;
import br.com.salomaotech.genesys.view.JFvacina;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class VacinaMetodos {

    private final JFvacina view;
    private final AnimalModelo animalModelo;

    public VacinaMetodos(JFvacina view, AnimalModelo animalModelo) {
        this.view = view;
        this.animalModelo = animalModelo;
    }

    public void popularFormulario(VacinaModelo VacinaModelo) {

        view.setId(VacinaModelo.getId());
        view.jCcadastroNome.getEditor().setItem(VacinaModelo.getNome());
        view.jDcadastroDataAplicacao.setCalendar(VacinaModelo.getDataAplicacao());
        view.jDcadastroDataProxima.setCalendar(VacinaModelo.getDataAplicacaoProxima());
        view.jCcadastroNumeroDoses.setSelectedItem(VacinaModelo.getDoses());
        view.jTcadastroObservacoes.setText(VacinaModelo.getObservacoes());

    }

    public void resetarView() {

        VacinaModelo VacinaModelo = new VacinaModelo();
        popularFormulario(VacinaModelo);
        view.jCcadastroNumeroDoses.setSelectedIndex(0);
        view.jCcadastroNome.requestFocus();

        /* evita erro de indexOfBounds no select */
        try {

            view.jCcadastroNome.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCcadastroNome.addItem("");
            view.jCcadastroNome.setSelectedIndex(0);

        }

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(view.getId() != 0);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jCcadastroNome);
        popUp.adicionarMenu(view.jTcadastroObservacoes);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new VacinaModelo());
        VacinaModelo vacinaModelo = (VacinaModelo) repository.findById(id);
        popularFormulario(vacinaModelo);
        view.jTabaPrincipal.setSelectedIndex(0);
        habilitarCampos();

    }

    public void pesquisar() {

        new VacinaPesquisa(view.jTresultados, animalModelo, view.jCpaginador).pesquisar();

    }

    public VacinaModelo salvar() {

        VacinaModelo vacinaModelo = new VacinaModelo();
        vacinaModelo.setId(view.getId());
        vacinaModelo.setNome(view.jCcadastroNome.getEditor().getItem().toString());
        vacinaModelo.setIdCliente(animalModelo.getIdCliente());
        vacinaModelo.setIdAnimal(animalModelo.getId());
        vacinaModelo.setDataAplicacao(view.jDcadastroDataAplicacao.getCalendar());
        vacinaModelo.setDataAplicacaoProxima(view.jDcadastroDataProxima.getCalendar());
        vacinaModelo.setDoses(view.jCcadastroNumeroDoses.getSelectedItem().toString());
        vacinaModelo.setObservacoes(view.jTcadastroObservacoes.getText());
        new Repository(vacinaModelo).save();
        return vacinaModelo;

    }

    public boolean excluir() {

        return new Repository(new VacinaModelo()).delete(view.getId());

    }

}

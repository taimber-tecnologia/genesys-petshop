package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.AnimalPesquisa;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class AnimalMetodos {

    private final JFanimal view;
    private ComboBoxClientes comboBoxClientesCadastro;
    private ComboBoxClientes comboBoxClientesPesquisa;

    public AnimalMetodos(JFanimal view) {
        this.view = view;
    }

    public void setComboBoxClientesCadastro(ComboBoxClientes comboBoxClientesCadastro) {
        this.comboBoxClientesCadastro = comboBoxClientesCadastro;
    }

    public void setComboBoxClientesPesquisa(ComboBoxClientes comboBoxClientesPesquisa) {
        this.comboBoxClientesPesquisa = comboBoxClientesPesquisa;
    }

    public void popularFormulario(AnimalModelo animalModelo) {

        view.setId(animalModelo.getId());
        view.jTnomeCadastro.setText(animalModelo.getNome());
        view.jCsexoCadastro.setSelectedItem(animalModelo.getSexo());
        view.jDnascimentoCadastro.setCalendar(animalModelo.getNascimento());
        view.jTpesoCadastro.setText(animalModelo.getPeso().toString());
        view.jCespecieCadastro.getEditor().setItem(animalModelo.getEspecie());
        view.jCracaCadastro.getEditor().setItem(animalModelo.getRaca());
        view.jTcaracteristicasCadastro.setText(animalModelo.getCaracteristica());
        comboBoxClientesCadastro.selecionarItemPorId(animalModelo.getIdCliente());

    }

    public void resetarView() {

        AnimalModelo animalModelo = new AnimalModelo();
        popularFormulario(animalModelo);
        view.jTpesoCadastro.setText("0");
        view.jCsexoCadastro.setSelectedIndex(0);
        view.jTnomeCadastro.requestFocus();

        /* evita erro de indexOfBounds no select */
        try {

            view.jCespecieCadastro.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCespecieCadastro.addItem("");
            view.jCespecieCadastro.setSelectedIndex(0);

        }

        /* evita erro de indexOfBounds no select */
        try {

            view.jCracaCadastro.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCracaCadastro.addItem("");
            view.jCracaCadastro.setSelectedIndex(0);

        }

        /* evita erro de indexOfBounds no select */
        try {

            view.jCnomeClienteCadastro.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCnomeClienteCadastro.addItem("");
            view.jCnomeClienteCadastro.setSelectedIndex(0);

        }

    }

    public void resetarViewPesquisa() {

        view.jTpesquisaNome.setText(null);
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        view.jCpaginador.setSelectedIndex(-1);
        pesquisar();

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(view.getId() != 0);
        view.jBatalhoVacinas.setEnabled(view.getId() != 0);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTnomeCadastro);
        popUp.adicionarMenu(view.jTpesoCadastro);
        popUp.adicionarMenu(view.jTcaracteristicasCadastro);
        popUp.adicionarMenu(view.jTpesquisaNome);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new AnimalModelo());
        AnimalModelo animalModelo = (AnimalModelo) repository.findById(id);
        popularFormulario(animalModelo);
        view.jTabaPrincipal.setSelectedIndex(0);
        habilitarCampos();

    }

    public void pesquisar() {

        AnimalPesquisa animalPesquisa = new AnimalPesquisa(view.jTresultados, view.jCpaginador);
        animalPesquisa.setNome(view.jTpesquisaNome.getText());
        animalPesquisa.setIdCliente(comboBoxClientesPesquisa.getIdSelecionado());
        animalPesquisa.pesquisar();

    }

    public AnimalModelo salvar() {

        AnimalModelo animalModelo = new AnimalModelo();
        animalModelo.setId(view.getId());
        animalModelo.setNome(view.jTnomeCadastro.getText());
        animalModelo.setSexo(view.jCsexoCadastro.getSelectedItem().toString());
        animalModelo.setNascimento(view.jDnascimentoCadastro.getCalendar());
        animalModelo.setPeso(BigDecimais.formatarParaBigDecimal(view.jTpesoCadastro.getText()));
        animalModelo.setEspecie(view.jCespecieCadastro.getEditor().getItem().toString());
        animalModelo.setRaca(view.jCracaCadastro.getEditor().getItem().toString());
        animalModelo.setCaracteristica(view.jTcaracteristicasCadastro.getText());
        animalModelo.setIdCliente(comboBoxClientesCadastro.getIdSelecionado());
        new Repository(animalModelo).save();
        return animalModelo;

    }

    public boolean excluir() {

        return new Repository(new AnimalModelo()).delete(view.getId());

    }

}

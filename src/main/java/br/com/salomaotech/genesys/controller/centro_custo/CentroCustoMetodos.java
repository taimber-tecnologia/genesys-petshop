package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoPesquisa;
import br.com.salomaotech.genesys.view.JFcentroCusto;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class CentroCustoMetodos {

    private final JFcentroCusto view;

    public CentroCustoMetodos(JFcentroCusto view) {
        this.view = view;
    }

    public void popularFormulario(CentroCustoModelo centroCustoModelo) {

        view.setId(centroCustoModelo.getId());
        view.jTcadastroCodigo.setText(centroCustoModelo.getCodigo());
        view.jTcadastroNome.setText(centroCustoModelo.getNome());

    }

    public void resetarView() {

        popularFormulario(new CentroCustoModelo());

    }

    public void habilitarCampos() {

        Repository repository = new Repository(new CentroCustoModelo());
        CentroCustoModelo centroCustoModelo = (CentroCustoModelo) repository.findById(view.getId());

        view.jBcadastroExcluir.setEnabled(centroCustoModelo.isEditavel() && centroCustoModelo.getId() != 0);
        view.jBcadastroSalvar.setEnabled(centroCustoModelo.isEditavel());

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTcadastroCodigo);
        popUp.adicionarMenu(view.jTcadastroNome);
        popUp.adicionarMenu(view.jTpesquisaNome);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new CentroCustoModelo());
        CentroCustoModelo centroCustoModelo = (CentroCustoModelo) repository.findById(id);
        popularFormulario(centroCustoModelo);
        habilitarCampos();
        view.jTabaPrincipal.setSelectedIndex(0);

    }

    public CentroCustoModelo salvar() {

        CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
        centroCustoModelo.setId(view.getId());
        centroCustoModelo.setCodigo(view.jTcadastroCodigo.getText());
        centroCustoModelo.setNome(view.jTcadastroNome.getText());
        new Repository(centroCustoModelo).save();
        return centroCustoModelo;

    }

    public boolean excluir() {

        return new Repository(new CentroCustoModelo()).delete(view.getId());

    }

    public void pesquisar() {

        CentroCustoPesquisa centroCustoPesquisa = new CentroCustoPesquisa(view.jTresultados, view.jCpaginador);
        centroCustoPesquisa.setNome(view.jTpesquisaNome.getText());
        centroCustoPesquisa.pesquisar();

    }

}

package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.model.fornecedor.FornecedorModelo;
import br.com.salomaotech.genesys.model.fornecedor.FornecedorPesquisa;
import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class FornecedorMetodos {

    private final JFfornecedor view;

    public FornecedorMetodos(JFfornecedor view) {
        this.view = view;
    }

    public void popularFormulario(FornecedorModelo fornecedorModelo) {

        view.setId(fornecedorModelo.getId());
        view.jTbasicoNome.setText(fornecedorModelo.getNome());
        view.jFbasicoCnpj.setText(fornecedorModelo.getCnpj());
        view.jFenderecoCep.setText(fornecedorModelo.getCep());
        view.jTenderecoRua.setText(fornecedorModelo.getRua());
        view.jTenderecoQuadra.setText(fornecedorModelo.getQuadra());
        view.jTenderecoLote.setText(fornecedorModelo.getLote());
        view.jTenderecoNumero.setText(fornecedorModelo.getNumero());
        view.jCenderecoUf.setSelectedItem(fornecedorModelo.getUf());
        view.jTenderecoBairro.setText(fornecedorModelo.getBairro());
        view.jTenderecoCidade.setText(fornecedorModelo.getCidade());
        view.jTenderecoComplemento.setText(fornecedorModelo.getComplemento());
        view.jTcontatoTelefone.setText(fornecedorModelo.getTelefone());
        view.jTcontatoEmail.setText(fornecedorModelo.getEmail());
        view.jTcontato.setText(fornecedorModelo.getContato());

    }

    public void resetarView() {

        popularFormulario(new FornecedorModelo());
        view.jFbasicoCnpj.setValue(null);
        view.jCenderecoUf.setSelectedIndex(0);

    }

    public void habilitarCampos() {

        boolean isIdAberto = view.getId() != 0;
        view.jBcadastroExcluir.setEnabled(isIdAberto);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTbasicoNome);
        popUp.adicionarMenu(view.jFbasicoCnpj);
        popUp.adicionarMenu(view.jFenderecoCep);
        popUp.adicionarMenu(view.jTenderecoRua);
        popUp.adicionarMenu(view.jTenderecoQuadra);
        popUp.adicionarMenu(view.jTenderecoLote);
        popUp.adicionarMenu(view.jTenderecoNumero);
        popUp.adicionarMenu(view.jTenderecoBairro);
        popUp.adicionarMenu(view.jTenderecoCidade);
        popUp.adicionarMenu(view.jTenderecoComplemento);
        popUp.adicionarMenu(view.jTcontatoTelefone);
        popUp.adicionarMenu(view.jTcontatoEmail);
        popUp.adicionarMenu(view.jTpesquisaNome);
        popUp.adicionarMenu(view.jTpesquisaCnpj);
        popUp.adicionarMenu(view.jTcontato);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new FornecedorModelo());
        FornecedorModelo fornecedorModelo = (FornecedorModelo) repository.findById(id);
        popularFormulario(fornecedorModelo);
        habilitarCampos();

    }

    public FornecedorModelo salvar() {

        FornecedorModelo fornecedorModelo = new FornecedorModelo();
        fornecedorModelo.setId(view.getId());
        fornecedorModelo.setNome(view.jTbasicoNome.getText());
        fornecedorModelo.setCnpj(view.jFbasicoCnpj.getText());
        fornecedorModelo.setCep(view.jFenderecoCep.getText());
        fornecedorModelo.setRua(view.jTenderecoRua.getText());
        fornecedorModelo.setQuadra(view.jTenderecoQuadra.getText());
        fornecedorModelo.setLote(view.jTenderecoLote.getText());
        fornecedorModelo.setNumero(view.jTenderecoNumero.getText());
        fornecedorModelo.setUf((String) view.jCenderecoUf.getSelectedItem());
        fornecedorModelo.setBairro(view.jTenderecoBairro.getText());
        fornecedorModelo.setCidade(view.jTenderecoCidade.getText());
        fornecedorModelo.setComplemento(view.jTenderecoComplemento.getText());
        fornecedorModelo.setTelefone(view.jTcontatoTelefone.getText());
        fornecedorModelo.setEmail(view.jTcontatoEmail.getText());
        fornecedorModelo.setContato(view.jTcontato.getText());
        new Repository(fornecedorModelo).save();
        return fornecedorModelo;

    }

    public boolean excluir() {

        return new Repository(new FornecedorModelo()).delete(view.getId());

    }

    public void pesquisar() {

        FornecedorPesquisa fornecedorPesquisa = new FornecedorPesquisa(view.jTresultados);
        fornecedorPesquisa.setNome(view.jTpesquisaNome.getText());
        fornecedorPesquisa.setCnpj(view.jTpesquisaCnpj.getText());
        fornecedorPesquisa.pesquisar();

    }

}

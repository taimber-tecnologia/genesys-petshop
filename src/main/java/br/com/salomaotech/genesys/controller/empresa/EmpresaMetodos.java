package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.model.empresa.EmpresaPesquisa;
import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class EmpresaMetodos {

    private final JFempresa view;

    public EmpresaMetodos(JFempresa view) {
        this.view = view;
    }

    public void popularFormulario(EmpresaModelo empresaModelo) {

        view.setId(empresaModelo.getId());
        view.jTbasicoNome.setText(empresaModelo.getNome());
        view.jFbasicoCnpj.setText(empresaModelo.getCnpj());
        view.jFenderecoCep.setText(empresaModelo.getCep());
        view.jTenderecoRua.setText(empresaModelo.getRua());
        view.jTenderecoQuadra.setText(empresaModelo.getQuadra());
        view.jTenderecoLote.setText(empresaModelo.getLote());
        view.jTenderecoNumero.setText(empresaModelo.getNumero());
        view.jCenderecoUf.setSelectedItem(empresaModelo.getUf());
        view.jTenderecoBairro.setText(empresaModelo.getBairro());
        view.jTenderecoCidade.setText(empresaModelo.getCidade());
        view.jTenderecoComplemento.setText(empresaModelo.getComplemento());
        view.jTcontatoTelefone.setText(empresaModelo.getTelefone());
        view.jTcontatoEmail.setText(empresaModelo.getEmail());

    }

    public void resetarView() {

        EmpresaModelo empresaModelo = new EmpresaModelo();
        popularFormulario(empresaModelo);
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
        popUp.adicionarMenu(view.jTcontatoTelefone);
        popUp.adicionarMenu(view.jTcontatoEmail);
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

    }

    public void carregaDadosEmpresa() {

        EmpresaModelo empresaModelo = EmpresaPesquisa.getDadosEmpresa();
        popularFormulario(empresaModelo);

    }

    public EmpresaModelo salvar() {

        EmpresaModelo empresaModelo = new EmpresaModelo();
        empresaModelo.setId(view.getId());
        empresaModelo.setNome(view.jTbasicoNome.getText());
        empresaModelo.setCnpj(view.jFbasicoCnpj.getText());
        empresaModelo.setCep(view.jFenderecoCep.getText());
        empresaModelo.setRua(view.jTenderecoRua.getText());
        empresaModelo.setQuadra(view.jTenderecoQuadra.getText());
        empresaModelo.setLote(view.jTenderecoLote.getText());
        empresaModelo.setNumero(view.jTenderecoNumero.getText());
        empresaModelo.setUf((String) view.jCenderecoUf.getSelectedItem());
        empresaModelo.setBairro(view.jTenderecoBairro.getText());
        empresaModelo.setCidade(view.jTenderecoCidade.getText());
        empresaModelo.setComplemento(view.jTenderecoComplemento.getText());
        empresaModelo.setTelefone(view.jTcontatoTelefone.getText());
        empresaModelo.setEmail(view.jTcontatoEmail.getText());
        new Repository(empresaModelo).save();
        return empresaModelo;

    }

    public boolean excluir() {

        return new Repository(new EmpresaModelo()).delete(view.getId());

    }

}

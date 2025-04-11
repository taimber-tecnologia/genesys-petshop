package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoPesquisa;
import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class ServicoMetodos {

    private final JFservico view;

    public ServicoMetodos(JFservico view) {
        this.view = view;
    }

    public void popularFormulario(ServicoModelo servicoModelo) {

        view.setId(servicoModelo.getId());
        view.jTnome.setText(servicoModelo.getNome());
        view.jTdescricao.setText(servicoModelo.getDescricao());
        view.jTvalor.setText(servicoModelo.getValor().toString());

    }

    public void resetarView() {

        popularFormulario(new ServicoModelo());

    }

    public void resetarViewPesquisa() {

        view.jTpesquisaNome.setText(null);
        view.jCpaginador.setSelectedIndex(-1);
        pesquisar();

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(view.getId() != 0);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTnome);
        popUp.adicionarMenu(view.jTdescricao);
        popUp.adicionarMenu(view.jTvalor);
        popUp.adicionarMenu(view.jTpesquisaNome);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new ServicoModelo());
        ServicoModelo servicoModelo = (ServicoModelo) repository.findById(id);
        popularFormulario(servicoModelo);
        habilitarCampos();
        view.jTabaPrincipal.setSelectedIndex(0);

    }

    public ServicoModelo salvar() {

        ServicoModelo servicoModelo = new ServicoModelo();
        servicoModelo.setId(view.getId());
        servicoModelo.setNome(view.jTnome.getText());
        servicoModelo.setDescricao(view.jTdescricao.getText());
        servicoModelo.setValor(BigDecimais.formatarParaBigDecimal(view.jTvalor.getText()));
        new Repository(servicoModelo).save();
        return servicoModelo;

    }

    public boolean excluir() {

        return new Repository(new ServicoModelo()).delete(view.getId());

    }

    public void pesquisar() {

        ServicoPesquisa servicoPesquisa = new ServicoPesquisa(view.jTresultados, view.jCpaginador);
        servicoPesquisa.setNome(view.jTpesquisaNome.getText());
        servicoPesquisa.pesquisar();

    }

}

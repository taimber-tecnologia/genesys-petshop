package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import static java.util.Objects.isNull;

public class ItemVenda {

    private long id;
    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private ServicoModelo servicoModelo = new ServicoModelo();
    private BigDecimal peso = new BigDecimal(0);
    private BigDecimal valor = new BigDecimal(0);
    private String nome;

    public ItemVenda() {

        this(0, null);

    }

    public ItemVenda(long id, Object object) {

        this.id = id;

        if (!isNull(object)) {

            switch (object.getClass().getSimpleName()) {

                case "ProdutoModelo":
                    produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(id);
                    peso = produtoModelo.getPeso();
                    valor = produtoModelo.getValorVenda();
                    nome = produtoModelo.getNome();
                    break;

                case "ServicoModelo":
                    servicoModelo = (ServicoModelo) new Repository(new ServicoModelo()).findById(id);
                    valor = servicoModelo.getValor();
                    nome = servicoModelo.getNome();
                    break;

            }

        }

    }

    public long getId() {
        return id;
    }

    public ProdutoModelo getProdutoModelo() {
        return produtoModelo;
    }

    public ServicoModelo getServicoModelo() {
        return servicoModelo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

}

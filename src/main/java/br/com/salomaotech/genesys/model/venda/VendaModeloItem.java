package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.sistema.patterns.Modelo;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VendaModeloItem implements Modelo, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idProduto;
    private long idServico;
    private BigDecimal valor = new BigDecimal(0);
    private BigDecimal quantidade = new BigDecimal(0);

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdServico() {
        return idServico;
    }

    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

}

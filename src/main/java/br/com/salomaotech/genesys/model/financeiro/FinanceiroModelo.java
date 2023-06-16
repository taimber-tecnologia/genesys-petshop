package br.com.salomaotech.genesys.model.financeiro;

import br.com.salomaotech.sistema.patterns.Modelo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import static java.util.Objects.isNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FinanceiroModelo implements Modelo, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Calendar data;

    private BigDecimal valor = new BigDecimal(0);

    @Column(columnDefinition = "CLOB")
    @Lob
    private String descricao;

    private long idCentroCusto;
    private boolean isDespesa;
    private boolean isPago;
    private long idVenda;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {

        if (!isNull(data)) {

            this.data = data;

        }

    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(long idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public boolean isIsDespesa() {
        return isDespesa;
    }

    public void setIsDespesa(boolean isDespesa) {
        this.isDespesa = isDespesa;
    }

    public boolean isIsPago() {
        return isPago;
    }

    public void setIsPago(boolean isPago) {
        this.isPago = isPago;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

}

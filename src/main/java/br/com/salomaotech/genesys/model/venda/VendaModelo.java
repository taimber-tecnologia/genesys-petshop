package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.sistema.patterns.Modelo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static java.util.Objects.isNull;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VendaModelo implements Modelo, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Calendar data;

    private String hora;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VendaModeloItem> vendaModeloItemList = new ArrayList();

    private String formaPagamento;

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<VendaModeloItem> getVendaModeloItemList() {
        return vendaModeloItemList;
    }

    public void setVendaModeloItemList(List<VendaModeloItem> vendaModeloItemList) {
        this.vendaModeloItemList = vendaModeloItemList;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValor() {

        BigDecimal valorTotal = new BigDecimal(0);

        for (VendaModeloItem vendaModeloItem : vendaModeloItemList) {

            BigDecimal quantidade = vendaModeloItem.getQuantidade();
            BigDecimal desconto = vendaModeloItem.getDesconto();
            BigDecimal valorCalculado = (vendaModeloItem.getValor().multiply(quantidade)).subtract(desconto);
            valorTotal = valorTotal.add(valorCalculado);

        }

        return valorTotal;

    }

}

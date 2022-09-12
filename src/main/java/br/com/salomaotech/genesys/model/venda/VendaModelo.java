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

    private long idCliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaModeloItem> vendaModeloItemList = new ArrayList();

    private BigDecimal entrada = new BigDecimal(0);
    private BigDecimal juros = new BigDecimal(0);
    private String formaPagamento;
    private boolean finalizado;
    private int numeroParcelas;
    private boolean isPago;
    private long idCentroCusto;

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

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public List<VendaModeloItem> getVendaModeloItemList() {
        return vendaModeloItemList;
    }

    public void setVendaModeloItemList(List<VendaModeloItem> vendaModeloItemList) {
        this.vendaModeloItemList = vendaModeloItemList;
    }

    public BigDecimal getEntrada() {
        return entrada;
    }

    public void setEntrada(BigDecimal entrada) {
        this.entrada = entrada;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public boolean isIsPago() {
        return isPago;
    }

    public void setIsPago(boolean isPago) {
        this.isPago = isPago;
    }

    public long getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(long idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
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

    public BigDecimal getValorTotal() {

        /* 1 - pega o valor */
        BigDecimal valor = getValor();

        /* 2 - subtrai entrada */
        valor = valor.subtract(entrada);

        /* 3 - calcula o valor real do juros em cima do valor */
        BigDecimal valorRealDoJuros = valor.multiply(juros).divide(new BigDecimal(100));

        /* 4 - adiciona juros */
        valor = valor.add(valorRealDoJuros);

        return valor;

    }

}

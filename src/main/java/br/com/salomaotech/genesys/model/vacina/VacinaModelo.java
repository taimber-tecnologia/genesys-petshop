package br.com.salomaotech.genesys.model.vacina;

import br.com.salomaotech.sistema.patterns.Modelo;
import java.io.Serializable;
import java.util.Calendar;
import static java.util.Objects.isNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VacinaModelo implements Modelo, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idCliente;
    private long idAnimal;
    private String nome;

    @Temporal(TemporalType.DATE)
    private Calendar dataAplicacao;

    @Temporal(TemporalType.DATE)
    private Calendar dataAplicacaoProxima;

    private String doses;
    private String observacoes;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Calendar getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Calendar dataAplicacao) {

        if (!isNull(dataAplicacao)) {

            this.dataAplicacao = dataAplicacao;

        }

    }

    public Calendar getDataAplicacaoProxima() {
        return dataAplicacaoProxima;
    }

    public void setDataAplicacaoProxima(Calendar dataAplicacaoProxima) {

        if (!isNull(dataAplicacaoProxima)) {

            this.dataAplicacaoProxima = dataAplicacaoProxima;

        }

    }

    public String getDoses() {
        return doses;
    }

    public void setDoses(String doses) {
        this.doses = doses;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}

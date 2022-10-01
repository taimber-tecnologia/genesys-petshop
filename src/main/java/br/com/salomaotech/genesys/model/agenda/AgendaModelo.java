package br.com.salomaotech.genesys.model.agenda;

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
public class AgendaModelo implements Modelo, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Calendar dataAgenda;

    private String dataHora;
    private String dataMinuto;
    private String nomeCliente;
    private String observacoes;
    private String status;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(Calendar dataAgenda) {

        if (!isNull(dataAgenda)) {

            this.dataAgenda = dataAgenda;

        }

    }

    public String getDataHora() {

        if (!isNull(dataHora)) {

            return dataHora;

        } else {

            return "0";

        }

    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDataMinuto() {

        if (!isNull(dataMinuto)) {

            return dataMinuto;

        } else {

            return "0";

        }

    }

    public void setDataMinuto(String dataMinuto) {
        this.dataMinuto = dataMinuto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

package com.gugawag.pdist.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mensagem {

    @Id
    private long idMensagem;
    private String texto;

    public Mensagem(){

    }

    public Mensagem(long idMensagem, String texto) {
        this.idMensagem = idMensagem;
        this.texto = texto;
    }
    public long getIdMensagem() {
        return idMensagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


}

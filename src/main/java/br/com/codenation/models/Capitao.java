package br.com.codenation.models;

import java.util.Objects;

public class Capitao {

    Long idTime;
    Long idJogador;


    public Capitao(Long idTime, Long idJogador) {
        this.idTime = idTime;
        this.idJogador = idJogador;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capitao capitao = (Capitao) o;
        return this.idTime.equals(capitao.idTime);
    }

}

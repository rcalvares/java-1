package br.com.codenation.models;

import br.com.codenation.DesafioMeuTimeApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        Time time1 = new Time(1L,"TIME1", LocalDate.now(),"AZUL","BRANCO");
        Time time2 = new Time(2L,"TIME1", LocalDate.now(),"AZUL","BRANCO");
        Time time3 = new Time(3L,"TIME1", LocalDate.now(),"AZUL","BRANCO");

        Jogador jogador1 = new Jogador(1L,1L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        Jogador jogador2 = new Jogador(2L,1L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        Jogador jogador3 = new Jogador(3L,1L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        Jogador jogador4 = new Jogador(4L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        Jogador jogador5 = new Jogador(5L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        Jogador jogador6 = new Jogador(6L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        Jogador jogador7 = new Jogador(7L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));


        desafio.incluirTime(1L,"TIME1", LocalDate.now(),"AZUL","BRANCO");
        desafio.incluirTime(2L,"TIME2", LocalDate.now(),"AZUL","BRANCO");
        desafio.incluirTime(3L,"TIME3", LocalDate.now(),"AZUL","BRANCO");

        desafio.incluirJogador(1L,1L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        desafio.incluirJogador(2L,1L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        desafio.incluirJogador(3L,1L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        desafio.incluirJogador(4L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        desafio.incluirJogador(5L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        desafio.incluirJogador(6L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));
        desafio.incluirJogador(7L,2L,"RAFAEL1",LocalDate.of(1996,02,22),100,new BigDecimal(3500));

        desafio.definirCapitao(1L);
        System.out.println(desafio.buscarCapitaoDoTime(1L));
        desafio.definirCapitao(2L);
        System.out.println(desafio.buscarCapitaoDoTime(1L));
        desafio.buscarNomeJogador(1L);
        desafio.buscarNomeTime(1L);
        System.out.println(desafio.buscarJogadoresDoTime(2L));


    }
}

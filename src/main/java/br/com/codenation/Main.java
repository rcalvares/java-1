package br.com.codenation;

import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;
import br.com.codenation.repositories.CapitaoRepository;
import br.com.codenation.repositories.JogadorRepository;
import br.com.codenation.repositories.TimeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Jogador jogador1 =
                new Jogador(1L,1L,"Rafael 1", LocalDate.of(1997,03,01),10, new BigDecimal(10));
        Jogador jogador2 =
                new Jogador(2L,1L,"Rafael 2", LocalDate.of(1990,02,22),15, new BigDecimal(10));
        Jogador jogador3 =
                new Jogador(3L,1L,"Rafael 3", LocalDate.of(1995,03,01),20, new BigDecimal(10));
        Jogador jogador4 =
                new Jogador(4L,1L,"Rafael 4", LocalDate.now(),30, new BigDecimal(10));

        Time time1 = new Time(1L,"Cruzeiro",LocalDate.now(),"Azul","Branco");
        Time time2 = new Time(2L,"Cruzeiro",LocalDate.now(),"Azul","Branco");
        Time time3 = new Time(3L,"Cruzeiro",LocalDate.now(),"Azul","Branco");
        Time time4 = new Time(4L,"Cruzeiro",LocalDate.now(),"Azul","Branco");
        Time time5 = new Time(5L,"Cruzeiro",LocalDate.now(),"Azul","Branco");

        TimeRepository.inserirTime(time1);
        TimeRepository.inserirTime(time2);
        TimeRepository.inserirTime(time3);
        TimeRepository.inserirTime(time4);
        TimeRepository.inserirTime(time5);

        JogadorRepository.incluirJogador(jogador1);
        JogadorRepository.incluirJogador(jogador2);
        JogadorRepository.incluirJogador(jogador3);
        JogadorRepository.incluirJogador(jogador4);

//        System.out.println(JogadorRepository.buscarJogadoresDoTime(2L));
        System.out.println(JogadorRepository.buscaJogadorMaisVelho(1L));




    }
}

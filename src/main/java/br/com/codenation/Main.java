package br.com.codenation;

import br.com.codenation.models.Time;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        Time cruzeiro = new Time(1L,"Cruzeiro",LocalDate.now(),"Azul","Branco") ;



        desafio.incluirTime(1L,"Cruzeiro",LocalDate.now(),"Azul","Branco");
        desafio.incluirTime(2L,"Flamengo",LocalDate.now(),"Vermelho","Branco");


        desafio.incluirJogador(6L,1L,"Rafael", LocalDate.now(),10,new BigDecimal(100));
        desafio.incluirJogador(3L,1L,"Rafael", LocalDate.now(),10,new BigDecimal(100));
        desafio.incluirJogador(1L,1L,"Rafael", LocalDate.now(),10,new BigDecimal(100));
        desafio.incluirJogador(5L,1L,"Rafael", LocalDate.now(),10,new BigDecimal(100));
        desafio.incluirJogador(2L,1L,"Rafael", LocalDate.now(),10,new BigDecimal(100));
        desafio.incluirJogador(7L,1L,"Rafael", LocalDate.now(),10,new BigDecimal(100));

        desafio.definirCapitao(2L);
        desafio.definirCapitao(3L);

        desafio.buscarJogadoresDoTime(1L);


    }
}

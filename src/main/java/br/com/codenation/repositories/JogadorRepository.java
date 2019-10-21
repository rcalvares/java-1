package br.com.codenation.repositories;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.models.Jogador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.codenation.utils.MensagensExcecao.ID_REPETIDO;
import static br.com.codenation.utils.MensagensExcecao.JOGADOR_NAO_ENCONTRADO;

public class JogadorRepository {

    private static List<Jogador> jogadores = new ArrayList<>();


    public static void incluirJogador(Jogador j){

        verificarJogadorExistente(j.getId());
        TimeRepository.verificarTimeExistente(j.getIdTime());
        jogadores.add(j);
    }

    public static boolean verificarJogadorExistente(Long id){

        jogadores.forEach(jogador -> {
            if (jogador.getId().equals(id)) throw new IdentificadorUtilizadoException(ID_REPETIDO);
        });

        return false;
    }

    public static Jogador buscarJogador(Long id){

        verificarJogadorExistente(id);
        return (Jogador) jogadores.stream().filter(jogador -> jogador.getId().equals(id)).findFirst().get();

    }

    public static String buscarNomeJogador(Long id){

        verificarJogadorExistente(id);
        return jogadores.stream().filter(jogador -> jogador.getId().equals(id)).findFirst().get().getNome();

    }

    public static void verificarJogadorNaoExistente(Long id) {

        if (jogadores.stream().noneMatch(jogador -> jogador.getId().equals(id))) throw new JogadorNaoEncontradoException(JOGADOR_NAO_ENCONTRADO);

    }

    public static List<Long> buscarJogadoresDoTime(Long idTime) {
        TimeRepository.verificarTimeExistente(idTime);
        return  jogadores.stream().
                filter(jogador -> jogador.getIdTime().equals(idTime))
                .sorted(Comparator.comparingLong(Jogador::getId))
                .map(jogador -> jogador.getId())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Long buscarMelhorJogadorDoTime(Long idTime){
        return buscarJogadoresCompletos(idTime).stream()
                .max(Comparator.comparingInt(Jogador::getNivelHabilidade))
                .get()
                .getId();
    }

    public static List<Jogador> buscarJogadoresCompletos(Long idTime) {
        TimeRepository.verificarTimeExistente(idTime);
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .sorted(Comparator.comparingLong(Jogador::getId))
                .collect(Collectors.toList());
    }

    public static Long buscarJogadorMaisVelho(Long idTime){

        List<Jogador> lista = buscarJogadoresCompletos(idTime);
        return verificaQuantosJogadoresComIdadeMaxima(retornaIdadeMaisAlta(lista),lista).getId();

    }

    private static int retornaIdadeMaisAlta(List<Jogador> lista){

        return lista.stream()
                .max(Comparator.comparingInt(Jogador::retornaIdade))
                .get()
                .retornaIdade();
    }

    public static Jogador verificaQuantosJogadoresComIdadeMaxima(int idade, List<Jogador> jogadores){

        List<Jogador> jogadoresIdadeMaxima = jogadores.stream().filter(jogador -> jogador.retornaIdade().equals(idade))
                .collect(Collectors.toCollection(ArrayList::new));

        if (jogadoresIdadeMaxima.size() == 1) return jogadoresIdadeMaxima.get(0);

        return escolheJogadorMenorIdentificador(jogadoresIdadeMaxima);
    }

    public static Jogador escolheJogadorMenorIdentificador(List<Jogador> jogadoresIdadeMaxima){

        return jogadoresIdadeMaxima.stream()
                    .min(Comparator.comparingLong(Jogador::getId))
                    .get();

    }

    public static BigDecimal buscarSalarioDoJogador(Long id){

        verificarJogadorExistente(id);
        return jogadores.stream().filter(jogador -> jogador.getId().equals(id)).findFirst().get().getSalario();

    }

    public static List<Jogador> buscarTopJogadores(Integer top){

        if (jogadores.size() <= top) return new ArrayList<>();

        Comparator<Jogador> comparator = Comparator.comparingInt(Jogador::getNivelHabilidade)
                .reversed()
                .thenComparingLong(Jogador::getId);

        Collections.sort(jogadores,comparator);

        return jogadores.subList(0,top);

    }




}

package br.com.codenation.repositories;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.models.Jogador;

import java.util.ArrayList;
import java.util.List;

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
}

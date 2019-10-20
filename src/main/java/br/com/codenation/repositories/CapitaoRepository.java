package br.com.codenation.repositories;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;

import java.util.ArrayList;
import java.util.List;

import static br.com.codenation.utils.MensagensExcecao.CAPITAO_NAO_INFORMADO;

public class CapitaoRepository {

    private static List<Jogador> capitoes = new ArrayList<>();

    public static void definirCapitao(Long idJogador){
        Jogador jogador = JogadorRepository.buscarJogador(idJogador);
        removerCapitaoAntigo(jogador);
        capitoes.add(jogador);
    }

    public static boolean verificarSeExisteCapitaoParaTime(Long idTime){

        if (!capitoes.stream().anyMatch(jogador -> jogador.getIdTime().equals(idTime))) throw new CapitaoNaoInformadoException(CAPITAO_NAO_INFORMADO);

        return true;

    }

    public static Jogador buscarCapitaoDoTime(Long idTime){

        TimeRepository.verificarTimeExistente(idTime);

        return (Jogador) capitoes.stream().filter(capitao -> capitao.getIdTime().equals(idTime)).findFirst().get();
    }

    public static void removerCapitaoAntigo(Jogador jogador){
        if (verificarSeExisteCapitaoParaTime(jogador.getIdTime())) capitoes.remove(buscarCapitaoDoTime(jogador.getIdTime()));
    }

}

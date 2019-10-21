package br.com.codenation.repositories;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;
import br.com.codenation.utils.MensagensExcecao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.codenation.utils.MensagensExcecao.*;

public class TimeRepository {

    private static List<Time> times = new ArrayList<>();

    public static void verificarTimeExistente(Long id) {

        if (times.stream().noneMatch(time -> time.getId().equals(id))) throw new TimeNaoEncontradoException(TIME_NAO_ENCONTRADO);

    }

    public static void inserirTime(Time t) {
        times.forEach(time -> { if (time.getId().equals(t.getId())) new IdentificadorUtilizadoException(ID_REPETIDO); });
        times.add(t);
    }

    public static String buscarNomeTime(Long id){

        verificarTimeExistente(id);
        return times.stream().filter(time -> time.getId().equals(id)).findFirst().get().getNome();

    }

    public static List<Long> buscarTimes() {
        return  times.stream()
                .sorted(Comparator.comparingLong(Time::getId))
                .map(time -> time.getId())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Time buscarTime(Long idTime) {
        return  times.stream()
                .filter(time -> time.getId().equals(idTime))
                .findFirst()
                .get();
    }

    public static String buscarCorCamisaTimeDeFora(Long idTimeHome, Long idTimeAway){

        if (validaCorIgual(idTimeHome, idTimeAway)) return buscarTime(idTimeAway).getCorUniformeSecundário();

        return buscarTime(idTimeAway).getCorUniformePrincipal();
    }

    private static boolean validaCorIgual(Long idTimeHome, Long idTimeAway) {

        Time timeHome = buscarTime(idTimeHome);
        Time timeAway = buscarTime(idTimeAway);

        return timeHome.getCorUniformePrincipal().equals(timeAway.getCorUniformePrincipal());

    }



}

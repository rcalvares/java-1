package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Capitao;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Time> times = new ArrayList<>();
	List<Jogador> jogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(times.stream().anyMatch(time -> time.getId().equals(id))) throw new IdentificadorUtilizadoException();
		Time time = new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario);
		times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		recuperarTime(idTime);
		if(jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))) throw new IdentificadorUtilizadoException();
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		jogadores.add(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		recuperarTime(recuperarJogador(idJogador).getIdTime()).setIdCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if (recuperarTime(idTime).getIdCapitao() == null) throw new CapitaoNaoInformadoException();
		return recuperarTime(idTime).getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return recuperarJogador(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return recuperarTime(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		recuperarTime(idTime);
		return jogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.map(jogador -> jogador.getId())
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		recuperarTime(idTime);
		return jogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getNivelHabilidade))
				.get()
				.getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		recuperarTime(idTime);

		List<Jogador> listaJogadores = jogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.collect(Collectors.toList());
		return verificaQuantosJogadoresComIdadeMaxima(retornaIdadeMaisAlta(listaJogadores),listaJogadores).getId();

	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		if (times.isEmpty()) return new ArrayList<>();
		return times.stream()
				.map(time -> time.getId())
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		recuperarTime(idTime);
		return jogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getSalario))
				.get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return recuperarJogador(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (jogadores.isEmpty()) return new ArrayList<>();

		jogadores.sort(Comparator.comparingLong(Jogador::getNivelHabilidade).reversed()
					.thenComparing(Jogador::getId));

		return jogadores.stream()
				.map(jogador -> jogador.getId())
				.collect(Collectors.toList())
				.subList(0,top);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (validaCorIgual(timeDaCasa, timeDeFora)) return recuperarTime(timeDeFora).getCorUniformeSecundário();
		return recuperarTime(timeDeFora).getCorUniformePrincipal();

	}

	private boolean validaCorIgual(Long idTimeHome, Long idTimeAway) {

		Time timeHome = recuperarTime(idTimeHome);
		Time timeAway = recuperarTime(idTimeAway);

		return timeHome.getCorUniformePrincipal().equals(timeAway.getCorUniformePrincipal());

	}

	public Jogador recuperarJogador(Long idJogador){
		if(jogadores.stream().noneMatch(jogador -> jogador.getId().equals(idJogador))) throw new JogadorNaoEncontradoException();
		return jogadores.stream()
				.filter(jogador -> jogador.getId().equals(idJogador))
				.findFirst()
				.get();
	}

	private Time recuperarTime(Long idTime){
		if(times.stream().noneMatch(time -> time.getId().equals(idTime))) throw new TimeNaoEncontradoException();
		return times.stream()
				.filter(time -> time.getId().equals(idTime))
				.findFirst()
				.get();
	}

	private  int retornaIdadeMaisAlta(List<Jogador> lista){
		return lista.stream()
				.max(Comparator.comparingInt(Jogador::retornaIdade))
				.get()
				.retornaIdade();
	}

	public Jogador verificaQuantosJogadoresComIdadeMaxima(int idade, List<Jogador> jogadores){

		List<Jogador> jogadoresIdadeMaxima = jogadores.stream()
				.filter(jogador -> jogador.retornaIdade().equals(idade))
				.collect(Collectors.toList());

		if (jogadoresIdadeMaxima.size() == 1) return jogadoresIdadeMaxima.get(0);

		return escolheJogadorMenorIdentificador(jogadoresIdadeMaxima);
	}

	public Jogador escolheJogadorMenorIdentificador(List<Jogador> jogadoresIdadeMaxima){

		return jogadoresIdadeMaxima.stream()
				.min(Comparator.comparingLong(Jogador::getId))
				.get();

	}





}

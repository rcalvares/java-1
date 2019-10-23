package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
	List<Capitao> capitoes = new ArrayList<>();

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
		Jogador jogador = recuperarJogador(idJogador);
		Capitao capitao = new Capitao(jogador.getIdTime(),jogador.getId());
		if (verificaCapitaoExistente(capitao.getIdTime())) removerCapitao(capitao.getIdTime());
		capitoes.add(capitao);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		recuperarTime(idTime);
		return recuperarCapitao(idTime).getIdJogador();
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
				.map(jogador -> jogador.getIdTime())
				.filter(id -> id.equals(idTime))
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
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

	private Capitao recuperarCapitao(Long idTime){
		if (capitoes.stream().noneMatch(capitao -> capitao.getIdTime().equals(idTime))) throw new CapitaoNaoInformadoException();
		return capitoes.stream()
				.filter(capitao -> capitao.getIdTime().equals(idTime))
				.findFirst()
				.get();
	}

	private boolean verificaCapitaoExistente(Long idTime){
		return capitoes.stream().anyMatch(capitao -> capitao.getIdTime().equals(idTime));
	}

	private void removerCapitao(Long idTime){
		capitoes.remove(recuperarCapitao(idTime));
	}


}

package br.unip.dsd.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.unip.dsd.modelos.Cidade;

public interface RepositorioCidade extends CrudRepository<Cidade, Long> {

}

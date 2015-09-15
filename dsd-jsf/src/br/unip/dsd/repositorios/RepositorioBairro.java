package br.unip.dsd.repositorios;

import br.unip.dsd.modelos.Bairro;
import br.unip.dsd.modelos.Cidade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dirceu on 9/2/15.
 */
public interface RepositorioBairro extends CrudRepository<Bairro,Long> {

    public Bairro findByIdBairro(Long id);

    public String findNomeDoBairroByIdBairro(Long id);

    public List<Cidade> findCidadeJoinBairroOnIdBairroByNomeDoBairro(String nome);
}

package br.unip.dsd.repositorios;

import br.unip.dsd.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dirceu on 8/30/15.
 */
@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,Long> {
    public Usuario findById(Long id);
    public String findNomeById(Long id);
    public Usuario findByNomeAndId(String nome, Long id);

}

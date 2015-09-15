package br.unip.dsd.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.dsd.modelos.UsuarioSenha;


public interface RepositorioUsuarioSenha extends JpaRepository<UsuarioSenha,Long>{

}

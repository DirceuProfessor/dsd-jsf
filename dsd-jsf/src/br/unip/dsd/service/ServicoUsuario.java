package br.unip.dsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unip.dsd.modelos.Usuario;
import br.unip.dsd.modelos.UsuarioSenha;
import br.unip.dsd.repositorios.RepositorioUsuario;
import br.unip.dsd.repositorios.RepositorioUsuarioSenha;

@Service
public class ServicoUsuario {
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	@Autowired
	private RepositorioUsuarioSenha repositorioUsuarioSenha;
	
	@Transactional
	public void gravaUsuario(Usuario usuario, UsuarioSenha usuarioSenha) {
		usuarioSenha.setUsuario(usuario);
		repositorioUsuarioSenha.save(usuarioSenha);
	}

	public List<Usuario> findAll() {
		return repositorioUsuario.findAll();
	}
	
}

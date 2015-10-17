package br.unip.dsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.dsd.modelos.UsuarioDetalhe;
import br.unip.dsd.repositorios.RepositorioUsuarioDetalhe;

@Service
public class ServicoUsuarioDetalhe {

	@Autowired
	private RepositorioUsuarioDetalhe repositorioUsuarioDetalhe;

	public void gravaDetalhe(UsuarioDetalhe usuarioDetalhe) {
		repositorioUsuarioDetalhe.save(usuarioDetalhe);		
	}

}

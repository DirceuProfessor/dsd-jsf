package br.unip.dsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unip.dsd.modelos.CEP;
import br.unip.dsd.modelos.Cidade;
import br.unip.dsd.modelos.Endereco;
import br.unip.dsd.modelos.Estado;
import br.unip.dsd.modelos.Rua;
import br.unip.dsd.modelos.TipoLogradouro;
import br.unip.dsd.modelos.Usuario;
import br.unip.dsd.modelos.UsuarioDetalhe;
import br.unip.dsd.repositorios.RepositorioCEP;
import br.unip.dsd.repositorios.RepositorioCidade;
import br.unip.dsd.repositorios.RepositorioEndereco;
import br.unip.dsd.repositorios.RepositorioEstado;
import br.unip.dsd.repositorios.RepositorioRua;
import br.unip.dsd.repositorios.RepositorioTipoLogradouro;
import br.unip.dsd.repositorios.RepositorioUsuario;
import br.unip.dsd.repositorios.RepositorioUsuarioDetalhe;

@Service
public class ServicoUsuarioDetalhe {

	@Autowired
	private RepositorioUsuarioDetalhe repositorioUsuarioDetalhe;
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	@Autowired
	private RepositorioCidade repositorioCidade;
	@Autowired
	private RepositorioRua repositorioRua;
	@Autowired
	private RepositorioEstado repositorioEstado;
	@Autowired
	private RepositorioCEP repositorioCEP;
	@Autowired
	private RepositorioEndereco repositorioEndereco;
	
	@Autowired
	private RepositorioTipoLogradouro repositorioTipoLogradouro;
	@Transactional()
	public void gravaDetalhe(Usuario usuario,UsuarioDetalhe usuarioDetalhe, Endereco endereco, Rua rua, Cidade cidade, 
			Estado estado, TipoLogradouro tipoLogradouro, CEP cep) {
		Usuario usuario2 = repositorioUsuario.findOne(usuario.getId());
		usuarioDetalhe.setUsuario(usuario2);
		repositorioCidade.save(cidade);
		cep.setCidade(cidade);
		repositorioRua.save(rua);
		cep.setRua(rua);
		repositorioEstado.save(estado);
		cep.setEstado(estado);
		repositorioTipoLogradouro.save(tipoLogradouro);
		cep.setTipoLogradouro(tipoLogradouro);
		repositorioCEP.save(cep);
		endereco.setCep(cep);
		repositorioEndereco.save(endereco);
		usuarioDetalhe.setEndereco(endereco);
		repositorioUsuarioDetalhe.save(usuarioDetalhe);		
	}

}

package br.unip.dsd.bean;

import java.util.Arrays;
import java.util.Collection;


import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unip.dsd.modelos.Endereco;
import br.unip.dsd.modelos.Estado;
import br.unip.dsd.repositorios.RepositorioEstado;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@ManagedBean(name="enderecoBean")
public class EnderecoBean {

	
	private Endereco endereco;
	
	@Autowired
	private RepositorioEstado repositorioEstado;
		
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
		
	public Collection getListaEstados(){  
		Stream<Estado> stream = StreamSupport.stream(repositorioEstado.findAll().spliterator(),false);
		Object[] itens = stream.map(estado ->
		new SelectItem(estado.getId(),estado.getSigla())).toArray();
		return Arrays.asList(itens);
		
		}  
}

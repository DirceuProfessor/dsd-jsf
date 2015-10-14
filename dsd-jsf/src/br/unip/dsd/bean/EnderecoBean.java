package br.unip.dsd.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

	private RepositorioEstado repositorioEstado;

	@Autowired
	public EnderecoBean(RepositorioEstado repositorioEstado){
		List<Estado> estados = new ArrayList<Estado>();
		estados.add(new Estado(1l,"Maranhão","MA"));
		estados.add(new Estado(2l,"Sergipe","SE"));
		estados.add(new Estado(3l,"Pernambuco","PE"));		
		estados.add(new Estado(4l,"São Paulo","SP"));
		estados.add(new Estado(5l,"Santa Catarina","SC"));
		estados.add(new Estado(6l,"Paraná","PR"));
		estados.add(new Estado(7l,"Rio de Janeiro","RJ"));
		estados.add(new Estado(8l,"Rio Grande do Sul","RS"));
		estados.add(new Estado(9l,"Rio Grande do Norte","RN"));
		estados.add(new Estado(10l,"Amapá","AP"));
		estados.add(new Estado(11l,"Acre","AC"));
		estados.add(new Estado(12l,"Amazonas","AM"));
		estados.add(new Estado(13l,"Paraíba","PB"));
		estados.add(new Estado(14l,"Pará","PA"));
		estados.add(new Estado(15l,"Alagoas","AL"));
		estados.add(new Estado(16l,"Roraima","RR"));
		estados.add(new Estado(17l,"Rondônia","RO"));
		estados.add(new Estado(18l,"Tocantins","TO"));
		estados.add(new Estado(19l,"Espirito Santo","ES"));
		estados.add(new Estado(20l,"Matro Grosso","MT"));
		estados.add(new Estado(21l,"Matro Grosso do Sul","MS"));
		estados.add(new Estado(22l,"Goias","GO"));
		estados.add(new Estado(23l,"Ceará","CE"));
		estados.add(new Estado(24l,"Bahia","BA"));
		estados.add(new Estado(25l,"Minas Gerais","MG"));
		estados.add(new Estado(26l,"Distrito Federal","DF"));
		this.repositorioEstado = repositorioEstado;
		repositorioEstado.save(estados);
	}
	private Endereco endereco;
	
	
		
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

package br.unip.dsd.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unip.dsd.factory.MessageFactory;
import br.unip.dsd.modelos.*;
import br.unip.dsd.service.ServicoUsuarioDetalhe;

@Component
@ManagedBean(name="usuarioDetalheBean")
public class UsuarioDetalheBean {
	
	private UsuarioDetalhe usuarioDetalhe = new UsuarioDetalhe();
	private Endereco endereco = new Endereco();
	private CEP cep = new CEP();
	private Cidade cidade = new Cidade();
	private Estado estado = new Estado();
	private Rua rua = new Rua();
	private TipoLogradouro tipoLogradouro = new TipoLogradouro();
	@Autowired
	private ServicoUsuarioDetalhe servicoUsuarioDetalhe;

	public CEP getCep() {
		return cep;
	}

	public void setCep(CEP cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public UsuarioDetalhe getUsuarioDetalhe() {
		return usuarioDetalhe;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void setUsuarioDetalhe(UsuarioDetalhe usuarioDetalhe) {
		this.usuarioDetalhe = usuarioDetalhe;
	}

	public List<SelectItem> getGeneros() {
		List<SelectItem> genders = new ArrayList<SelectItem>();
		MessageFactory msg = new MessageFactory();

		SelectItem item = new SelectItem();    
		item.setLabel(msg.getMessage("masculino"));
		item.setValue(Genero.MASCULINO.toString());
		genders.add(item);
		item = new SelectItem();
		item.setLabel(msg.getMessage("feminino"));
		item.setValue(Genero.FEMININO.toString());
		genders.add(item);
		item = new SelectItem();
		item.setLabel(msg.getMessage("indefinido"));
		item.setValue(Genero.INDEFINIDO.toString());
		genders.add(item);
		return genders;
	}

	public List<SelectItem> getEstadoCivil() {
		List<SelectItem> estadoCivil = new ArrayList<SelectItem>();
		MessageFactory msg = new MessageFactory();
		
		estadoCivil.add(new SelectItem(EstadoCivil.CASADO.toString(),msg.getMessage("casado")));    
		estadoCivil.add(new SelectItem(EstadoCivil.SOLTEIRO.toString(),msg.getMessage("solteiro")));
		estadoCivil.add(new SelectItem(EstadoCivil.DIVORCIADO.toString(),msg.getMessage("divorciado")));
		estadoCivil.add(new SelectItem(EstadoCivil.VIUVO.toString(),msg.getMessage("viuvo")));
		return estadoCivil; 
	}

	public String registrar(Usuario usuario) throws Exception {
		String toReturn="failure";
		try{
		    servicoUsuarioDetalhe.gravaDetalhe(usuario,usuarioDetalhe,endereco,rua,cidade,estado,tipoLogradouro,cep);
			String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId()+"#sec3";
			toReturn = "success";
			ExternalContext ec = FacesContext.getCurrentInstance()
					.getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath()
						+ viewId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (EntityExistsException exist) {
			MessageFactory msg = new MessageFactory();
			FacesContext ctx = FacesContext.getCurrentInstance();

			ctx.addMessage("registerForm:email", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							msg.getMessage("errorEmailExists"), null));
		}          

		return toReturn;
	}

}

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
import br.unip.dsd.modelos.Endereco;
import br.unip.dsd.modelos.EstadoCivil;
import br.unip.dsd.modelos.Genero;
import br.unip.dsd.modelos.UsuarioDetalhe;
import br.unip.dsd.service.ServicoUsuarioDetalhe;

@Component
@ManagedBean(name="usuarioDetalheBean")
public class UsuarioDetalheBean {
	
	private UsuarioDetalhe usuarioDetalhe = new UsuarioDetalhe();
	private Endereco endereco = new Endereco();
	
	@Autowired
	private ServicoUsuarioDetalhe servicoUsuarioDetalhe;
	
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
		List<SelectItem> maritalStati = new ArrayList<SelectItem>();
		MessageFactory msg = new MessageFactory();

		SelectItem item = new SelectItem();    
		item.setLabel(msg.getMessage("casado"));
		item.setValue(EstadoCivil.CASADO.toString());
		maritalStati.add(item);
		item = new SelectItem();
		item.setLabel(msg.getMessage("solteiro"));
		item.setValue(EstadoCivil.SOLTEIRO.toString());
		maritalStati.add(item);

		item = new SelectItem();
		item.setLabel(msg.getMessage("divorciado"));
		item.setValue(EstadoCivil.DIVORCIADO.toString());
		maritalStati.add(item);
		item = new SelectItem();
		item.setLabel(msg.getMessage("viuvo"));
		item.setValue(EstadoCivil.VIUVO.toString());
		maritalStati.add(item);
		return maritalStati; 
	}

	public String registrar() throws Exception {
		String toReturn="failure";
		try{
		    usuarioDetalhe.setEndereco(this.endereco);

			servicoUsuarioDetalhe.gravaDetalhe(usuarioDetalhe);
			String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId()+"#sec2";
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

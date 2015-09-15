package br.unip.dsd.bean;

import javax.faces.bean.ManagedBean;

import br.unip.dsd.modelos.Endereco;
import br.unip.dsd.modelos.EstadoCivil;
import br.unip.dsd.modelos.Genero;
import br.unip.dsd.modelos.Usuario;
import br.unip.dsd.modelos.UsuarioSenha;
import br.unip.dsd.repositorios.RepositorioUsuario;
import br.unip.dsd.repositorios.RepositorioUsuarioSenha;

import java.io.Serializable;
import java.util.*;

import javax.faces.context.*;
import javax.faces.application.*;
import javax.faces.model.SelectItem;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unip.dsd.factory.MessageFactory;

@Component
@ManagedBean
public class UsuarioBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728316233264714322L;
	
	private UsuarioSenha usuarioSenha = new UsuarioSenha();
	private Usuario usuario = new Usuario();
	private String passwordConfirm;
	private String confirmacaoEmail;
	private List<Usuario> usuarios = Collections.<Usuario>emptyList();
	
	
	
	public List<Usuario> getUsuarios() {
		return repositorioUsuario.findAll();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioSenha getUsuarioSenha() {
		return usuarioSenha;
	}

	public void setUsuarioSenha(UsuarioSenha usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}



	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SelectItem> getGenders() {
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
		item.setLabel(msg.getMessage("VIUVO"));
		item.setValue(EstadoCivil.VIUVO.toString());
		maritalStati.add(item);
		return maritalStati; 
	}

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	@Autowired
	private RepositorioUsuarioSenha repositorioUsuarioSenha;

	public String register() throws Exception {
		String toReturn = "failure";

		if (validateData()) {
			try {
				// save locale information, in case the user chose a language on the welcome page
				Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
				//        usuario.setLocaleCountry(locale.getCountry());
				//        usuario.setLocaleLanguage(locale.getLanguage());

				usuario  = repositorioUsuario.saveAndFlush(usuario);
//				usuarioSenha = repositorioUsuarioSenha.save(usuarioSenha);
				//        usuario = entities.createPerson(usuario);
				toReturn = "success";
			} 
			catch (EntityExistsException exist) {
				MessageFactory msg = new MessageFactory();
				FacesContext ctx = FacesContext.getCurrentInstance();

				ctx.addMessage("registerForm:email", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, 
								msg.getMessage("errorEmailExists"), null));
			}          
		}
		return toReturn;
	}

	/**
	 * Perform validation that can only be done once 
	 * all the field data is present
	 * in the person member. Ie: to validate one field 
	 * other field(s) values are needed.
	 * 
	 * Note that this method creates error messages for the 
	 * faces context, if errors are found.
	 * @return true if data is OK, false if not OK
	 */
	private boolean validateData() {
		boolean toReturn = true;
		MessageFactory msg = new MessageFactory();
		FacesContext ctx = FacesContext.getCurrentInstance();

		// check emailConfirm is same as email
		if (confirmacaoEmail!=null && !confirmacaoEmail.equals(usuario.getEmail())) {
			ctx.addMessage("Usuario:confirmacaoEmail", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							msg.getMessage("erroConfirmacaoEmail"), null));
			toReturn = false;
		}
		// check passwordConfirm is same as password
		if (passwordConfirm !=null && !passwordConfirm.equals(usuarioSenha.getPassword())) {
			ctx.addMessage("registerForm:passwordConfirm", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							msg.getMessage("errorPasswordConfirm"), null));
			toReturn = false;
		}
		return toReturn;
	}

	public String getConfirmacaoEmail() {
		return confirmacaoEmail;
	}

	public void setConfirmacaoEmail(String emailConfirm) {
		this.confirmacaoEmail = emailConfirm;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
package br.unip.dsd.bean;

import javax.faces.bean.ManagedBean;

import br.unip.dsd.modelos.Endereco;
import br.unip.dsd.modelos.EstadoCivil;
import br.unip.dsd.modelos.Genero;
import br.unip.dsd.modelos.Usuario;
import br.unip.dsd.modelos.UsuarioDetalhe;
import br.unip.dsd.modelos.UsuarioSenha;
import br.unip.dsd.repositorios.RepositorioUsuario;
import br.unip.dsd.repositorios.RepositorioUsuarioSenha;
import br.unip.dsd.service.ServicoUsuario;

import java.io.IOException;
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
@ManagedBean(name="usuarioBean")
public class UsuarioBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728316233264714322L;

	private UsuarioSenha usuarioSenha = new UsuarioSenha();
	private Usuario usuario = new Usuario();
	private String confirmacaoSenha;
	private String confirmacaoEmail;
	private List<Usuario> usuarios = Collections.<Usuario>emptyList();
	
	@Autowired
	private ServicoUsuario servicoUsuario;


	public List<Usuario> getUsuarios() {
		return servicoUsuario.findAll();
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

	
	public String register() throws Exception {
		String toReturn = "failure";

		if (validateData()) {
			try {
				servicoUsuario.gravaUsuario(usuario, usuarioSenha);
				//				usuarioSenha.setId(usuario.getId());
				//				usuarioSenha = repositorioUsuarioSenha.save(usuarioSenha);
				//        usuario = entities.createPerson(usuario);
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
		if (confirmacaoSenha !=null && !confirmacaoSenha.equals(usuarioSenha.getPassword())) {
			ctx.addMessage("Usuario:confirmacaoSenha", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							msg.getMessage("erroConfirmacaoSenha"), null));
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

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String passwordConfirm) {
		this.confirmacaoSenha = passwordConfirm;
	}

	public String cancelar() {
		this.confirmacaoEmail="";
		this.usuario = new Usuario();
		this.usuarioSenha = new UsuarioSenha();
		return "success";
	}
}
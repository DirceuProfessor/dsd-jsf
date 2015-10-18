package br.unip.dsd.bean.validador;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.unip.dsd.factory.MessageFactory;

@FacesValidator
public class ValidadorDeSenha implements Validator{

	//	((?=.{2,}\\d)(?=.{2,}[a-zA-Z]).{6,20})
	//	(
	//	(?=.{2,}\\d) verifica se contém pelo menos 2 ({2,}) dígitos \\d
	//	(?=.{2,}[a-zA-Z]) verifica se contém pelo menos 2 ({2,}) letras(a-zA-Z) de a a z minúsculo e A a Z maiúsculo
	//		.{6,20} verificacao de tamanho;
	//		) 
	private Pattern padraoSenha=  Pattern.compile("((?=.{2,}\\d)(?=.{2,}[a-zA-Z]).{6,20})");

	@Override
	public void validate(FacesContext context, UIComponent iuComponent, Object valor)
			throws ValidatorException {
		if(!padraoSenha.matcher(valor.toString()).matches()){
			MessageFactory msg = new MessageFactory();
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					msg.getMessage("senhaInvalida"), null));	          
		}
	}
}

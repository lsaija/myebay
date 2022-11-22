

package it.prova.myebay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import it.prova.myebay.validation.ValidationWithPassword;

public class UtenteChangePassDTO {

	private Long id;

	private String vecchiaPassword;
	
	@NotBlank(message = "{utente.password.notblank}", groups = ValidationWithPassword.class)
	@Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String password;

	private String confermaPassword;

	public UtenteChangePassDTO() {
	}
	
	public UtenteChangePassDTO(String vecchiaPassword,String password, String confermaPassword) {
		this.password = password;
		this.confermaPassword = confermaPassword;
		this.vecchiaPassword = vecchiaPassword;
	}
	
	public String getVecchiaPassword() {
		return vecchiaPassword;
	}

	public void setVecchiaPassword(String vecchiaPassword) {
		this.vecchiaPassword = vecchiaPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

}
package it.prova.myebay.repository.annuncio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public interface CustomAnnuncioRepository {
	public List<Annuncio> findByExample(Annuncio example);
	
	public List<Annuncio> findByExampleEager(Annuncio example);
	
	
}
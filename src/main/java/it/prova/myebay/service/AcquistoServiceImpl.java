

package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {
	
	@Autowired
	private AcquistoRepository acquistoRepository;
	
	@Transactional(readOnly = true)
	public List<Acquisto> listAllAcquisto() {
		return  (List<Acquisto>) acquistoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloAcquisto(Long id) {
		return acquistoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Acquisto acquisto) {
		acquistoRepository.save(acquisto);
	}

	@Transactional
	public void inserisciNuovo(Acquisto acquisto) {
		acquistoRepository.save(null);		
	}

	@Transactional
	public void rimuovi(Long idToDelete) {
		acquistoRepository.deleteById(idToDelete);
    }

	@Transactional(readOnly = true)
	public List<Acquisto> findByExample(Acquisto example) {
		return acquistoRepository.findByExample(example);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> FindAllAcquistiById(Long id) {
		return acquistoRepository.FindAllAcquistiById(id);
	}

}
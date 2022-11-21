package it.prova.myebay;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@SpringBootApplication
public class MyebayApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private UtenteService utenteServiceInstance;

	@Autowired
	private CategoriaService categoriaServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(MyebayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}

		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Elettronica", "CATEGORIA_ELETTRONICA") == null)
			categoriaServiceInstance.inserisciNuovo(new Categoria("Elettronica", "CATEGORIA_ELETTRONICA"));

		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Casa", "CATEGORIA_CASA") == null)
			categoriaServiceInstance.inserisciNuovo(new Categoria("Casa", "CATEGORIA_CASA"));

		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Tempo Libero", "CATEGORIA_TEMPOLIBERO") == null)
			categoriaServiceInstance.inserisciNuovo(new Categoria("Tempo Libero", "CATEGORIA_TEMPOLIBERO"));

		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);

			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", new Date());
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser);

			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Pluto", "Pippo", new Date());
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser1);

			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());

		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Provola", "Mozarella", new Date());
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser2);

			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}

	}

}
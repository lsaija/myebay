package it.prova.myebay.web.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AcquistoService;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/acquisto")
public class AcquistoController {
	
	@Autowired
	private AcquistoService acquistoService;
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteService utenteService;
	

	
	@GetMapping("/myacquisti")
	public ModelAndView listAllAcquisti(Authentication authentication) {
		String currentUserName = "";
		if (!(authentication instanceof AnonymousAuthenticationToken)) 
		    currentUserName = authentication.getName();	    

		ModelAndView mv = new ModelAndView();
		Utente utenteInSessione = utenteService.findByUsername(currentUserName);
		Acquisto example = new Acquisto(null, null, null, utenteInSessione);
		mv.addObject("list_acquisto_attr", AcquistoDTO.buildAcquistoDtoListFromModelList(acquistoService.findByExample(example)));
		mv.setViewName("acquisto/list");
		return mv;
	}
	
	@GetMapping(value = "/search")
	public String searchAcquisto() {
		return "acquisto/search";
	}
	
	@GetMapping(value = "/list")
	public String listAcquisti(Acquisto example, ModelMap model,HttpRequest request) {	
		model.addAttribute("acquisto_list_attr", AcquistoDTO.buildAcquistoDtoListFromModelList(acquistoService.findByExample(example)));
		return "acquisto/list";
	}
	
	@PostMapping("/confermaAcquisto")
	public String confermaAcquisto(@RequestParam(name = "idAnnuncio") Long idAnnuncio
			,@RequestParam(name = "utenteId") Long utenteId
			,Model model, RedirectAttributes redirectAttrs) {
		Utente utente = utenteService.caricaSingoloUtente(utenteId);
		Annuncio annuncio = annuncioService.caricaSingoloElemento(idAnnuncio);
		AcquistoDTO acquistoDTO = new AcquistoDTO(annuncio.getTestoAnnuncio(), annuncio.getData(), annuncio.getPrezzo());
		
		if (utente.getCreditoResiduo() < annuncio.getPrezzo()) {
			
			redirectAttrs.addFlashAttribute("errorMessage", "Credito esaurito");
			return "redirect:/home";
		}
		Integer creditoAggiornato = utente.getCreditoResiduo() - annuncio.getPrezzo();
		utente.setCreditoResiduo(creditoAggiornato);
		utenteService.aggiorna(utente);
		
		acquistoDTO.setData(new Date());
		acquistoDTO.setUtenteAcquirente(UtenteDTO.buildUtenteDTOFromModel
				(utenteService.caricaSingoloUtente(utenteId), true));
		acquistoService.inserisciNuovo(acquistoDTO.buildAcquistoModel());
		annuncio.setAperto(false);
		annuncioService.aggiorna(annuncio);
		
		return "acquisto/list";
	}
	
	
	
	
}
package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.CollezioneValidator;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.CuratoreService;
import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static it.uniroma3.siw.utility.Costanti.COLLEZIONE_CORRENTE;
import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping
public class CollezioneController {
    private final CollezioneService collezioneService;
    private final CuratoreService curatoreService;
    private final OperaService operaService;
    private CollezioneValidator collezioneValidator;

    public CollezioneController(final CollezioneService collezioneService,
                                final CuratoreService curatoreService,
                                final OperaService operaService,
                                final CollezioneValidator collezioneValidator) {
        this.collezioneService = collezioneService;
        this.curatoreService = curatoreService;
        this.operaService = operaService;
        this.collezioneValidator = collezioneValidator;
    }

    @GetMapping(path = {"/admin/collezioni", "/admin", "/admin/"})
    public String getCollezioniPerAdmin(final Model model) {
        model.addAttribute("collezioni", collezioneService.getCollezioni());
        return "admin_collezioni";
    }

    @GetMapping(path = "/admin/crea_collezione")
    public String getCreaCollezionePage(final Model model) {
        model.addAttribute("curatori", this.curatoreService.getCuratori());
        model.addAttribute("nuovaCollezione", new Collezione());
        return "admin_crea_collezione";
    }

    @PostMapping(path = "/admin/crea_collezione")
    public String creaCollezione(@RequestParam final String curatoreSelezionato,
                                 @ModelAttribute("nuovaCollezione") final Collezione nuovaCollezione,
                                 final Model model,
                                 final BindingResult bindingResult) {
        this.collezioneValidator.validate(nuovaCollezione, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.collezioneService.salvaCollezione(nuovaCollezione, this.curatoreService.findById(curatoreSelezionato));
            return "redirect:/admin/collezioni";
        }
        model.addAttribute("curatori", this.curatoreService.getCuratori());
        return "admin_crea_collezione";
    }

    @PostMapping(path = "/admin/collezione/{idCollezione}/gestisci")
    public String aggiornaCollezione(@PathVariable final Long idCollezione,
                                     @ModelAttribute final Collezione nuovaCollezione) {
        this.collezioneService.aggiornaCollezione(idCollezione, nuovaCollezione);
        return "redirect:/admin/collezioni";
    }

    @GetMapping(path = "/admin/collezione/{idCollezione}/gestisci")
    public String gestisciCollezione(@PathVariable("idCollezione") final Long idCollezione,
                                     final Model model) {
        Collezione collezioneCorrente = this.collezioneService.findById(idCollezione);
        model.addAttribute("opere", this.operaService.getOpere().stream().filter((opera) -> !collezioneCorrente.contieneOpera(opera)).collect(toList()));
        model.addAttribute(COLLEZIONE_CORRENTE, collezioneCorrente);
        model.addAttribute("opereCollezione", this.operaService.findByCollezione(collezioneCorrente));
        return "admin_gestisci_collezione";
    }

    @PostMapping(path = "/admin/collezione/{idCollezione}/gestisci/aggiungiOpera")
    public String aggiungiOperaACollezione(@PathVariable("idCollezione") final Long idCollezione,
                                           @RequestParam("operaSelezionata") final Long idOpera,
                                           final Model model) {
        this.collezioneService.aggiungiOpera(idCollezione, this.operaService.findById(idOpera));
        return "redirect:/admin/collezione/" + idCollezione + "/gestisci";
    }

    @PostMapping(path = "/admin/collezione/{idCollezione}/gestisci/{idOpera}/elimina")
    public String eliminaOperaDaCollezione(@PathVariable("idCollezione") final Long idCollezione,
                                           @PathVariable("idOpera") final Long idOpera,
                                           final Model model) {
        this.collezioneService.eliminaOpera(idCollezione, this.operaService.findById(idOpera));
        return "redirect:/admin/collezione/" + idCollezione + "/gestisci";
    }

    @PostMapping(path = "/admin/collezione/{idCollezione}/elimina")
    public String eliminaCollezione(@PathVariable("idCollezione") final Long idCollezione) {
        this.collezioneService.eliminaCollezioneById(idCollezione);
        return "redirect:/admin/collezioni";
    }

    @GetMapping(path = "/collezione")
    public String getCollezioniPage(final Model model) {
        model.addAttribute("collezioni", this.collezioneService.getCollezioni());
        return "collezioni";
    }

    @GetMapping(path = "/collezione/{idCollezione}")
    public String getCollezione(@PathVariable("idCollezione") final Long idCollezione, final Model model) {
        model.addAttribute("collezione", this.collezioneService.findById(idCollezione));
        return "collezione";
    }
}

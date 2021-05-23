package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.service.AmministratoreService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.CuratoreService;
import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static it.uniroma3.siw.utility.Costanti.COLLEZIONE_CORRENTE;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/admin")
public class AmministratoreController {
    private final AmministratoreService amministratoreService;
    private final CollezioneService collezioneService;
    private final OperaService operaService;
    private CuratoreService curatoreService;

    public AmministratoreController(AmministratoreService amministratoreService,
                                    CollezioneService collezioneService,
                                    OperaService operaService,
                                    CuratoreService curatoreService) {
        this.amministratoreService = amministratoreService;
        this.collezioneService = collezioneService;
        this.operaService = operaService;
        this.curatoreService = curatoreService;
    }

    @GetMapping(path = {"/collezioni", "/", ""})
    public String getCollezioniPerAdmin(Model model) {
        model.addAttribute("collezioni", collezioneService.getCollezioni());
        return "admin_collezioni";
    }

    @GetMapping(path="/crea_collezione")
    public String getCreaCollezionePage(Model model) {
        model.addAttribute("curatori", this.curatoreService.getCuratori());
        model.addAttribute("nuovaCollezione", new Collezione());
        return "admin_crea_collezione";
    }

    @PostMapping(path="/crea_collezione")
    public String creaCollezione(Model model, @RequestParam String curatoreSelezionato, @ModelAttribute Collezione nuovaCollezione) {
        nuovaCollezione.setCuratore(this.curatoreService.findById(curatoreSelezionato));
        this.collezioneService.salvaCollezione(nuovaCollezione);
        model.addAttribute("collezioni", collezioneService.getCollezioni());
        return "admin_collezioni";
    }

    @PostMapping(path = "/collezione/{idCollezione}/gestisci")
    public String aggiornaCollezione(@PathVariable Long idCollezione, @ModelAttribute Collezione nuovaCollezione, Model model) {
        this.collezioneService.aggiornaCollezione(idCollezione, nuovaCollezione);
        model.addAttribute("collezioni", collezioneService.getCollezioni());
        return "admin_collezioni";
    }

    @GetMapping(path = "/opere")
    public String getOpere(Model model) {
        return "admin_opere";
    }

    @GetMapping(path = "/collezione/{idCollezione}/gestisci")
    public String gestisciCollezione(@PathVariable("idCollezione") Long idCollezione,
                                     Model model) {
        Collezione collezioneCorrente = this.collezioneService.findById(idCollezione);
        model.addAttribute("opere", this.operaService.getOpere().stream().filter((opera) -> !collezioneCorrente.contieneOpera(opera)).collect(toList()));
        model.addAttribute(COLLEZIONE_CORRENTE, collezioneCorrente);
        model.addAttribute("opereCollezione", this.operaService.findByCollezione(collezioneCorrente));
        return "admin_gestisci_collezione";
    }

    @PostMapping(path = "/collezione/{idCollezione}/gestisci/aggiungiOpera")
    public String aggiungiOperaACollezione(@PathVariable("idCollezione") Long idCollezione,
                                           @RequestParam("operaSelezionata") Long idOpera,
                                           Model model) {
        Collezione collezioneCorrente = this.collezioneService.findById(idCollezione);
        this.collezioneService.aggiungiOpera(idCollezione, this.operaService.findById(idOpera));
        model.addAttribute("opere", this.operaService.getOpere().stream().filter((opera) -> !collezioneCorrente.contieneOpera(opera)).collect(toList()));
        model.addAttribute(COLLEZIONE_CORRENTE, collezioneCorrente);
        model.addAttribute("opereCollezione", this.operaService.findByCollezione(collezioneCorrente));
        return "admin_gestisci_collezione";
    }

    @GetMapping(path = "/collezione/{idCollezione}/gestisci/{idOpera}/elimina")
    public String eliminaOperaDaCollezione(@PathVariable("idCollezione") Long idCollezione,
                                           @PathVariable("idOpera") Long idOpera,
                                           Model model) {
        Collezione collezioneCorrente = this.collezioneService.findById(idCollezione);
        this.collezioneService.eliminaOpera(idCollezione, this.operaService.findById(idOpera));
        model.addAttribute("opere", this.operaService.getOpere().stream().filter((opera) -> !collezioneCorrente.contieneOpera(opera)).collect(toList()));
        model.addAttribute(COLLEZIONE_CORRENTE, collezioneCorrente);
        model.addAttribute("opereCollezione", this.operaService.findByCollezione(collezioneCorrente));
        return "admin_gestisci_collezione";
    }
}

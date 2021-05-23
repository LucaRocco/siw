package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.AmministratoreService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static it.uniroma3.siw.utility.Costanti.COLLEZIONE_CORRENTE;

@Controller
@RequestMapping("/admin")
public class AmministratoreController {
    private final AmministratoreService amministratoreService;
    private final CollezioneService collezioneService;
    private final OperaService operaService;

    public AmministratoreController(AmministratoreService amministratoreService,
                                    CollezioneService collezioneService,
                                    OperaService operaService) {
        this.amministratoreService = amministratoreService;
        this.collezioneService = collezioneService;
        this.operaService = operaService;
    }

    @GetMapping(path = "/collezioni")
    public String getCollezioniPerAdmin(Model model) {
        model.addAttribute("collezioni", collezioneService.getCollezioni());
        return "admin_collezioni";
    }

    @PostMapping(path = "/collezioni")
    public String aggiornaCollezione(@ModelAttribute Collezione collezioneCorrente, Model model) {
        this.collezioneService.aggiornaCollezione(collezioneCorrente);
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
        model.addAttribute(COLLEZIONE_CORRENTE, collezioneCorrente);
        model.addAttribute("opere", this.operaService.findByCollezione(collezioneCorrente));
        return "admin_gestisci_collezione";
    }

    @GetMapping(path = "/collezione/{idCollezione}/gestisci/{idOpera}/elimina")
    public String eliminaOperaDaCollezione(@PathVariable("idCollezione") Long idCollezione,
                                           @PathVariable("idOpera") Long idOpera,
                                           Model model) {
        this.operaService.eliminaDaCollezione(idOpera, this.collezioneService.findById(idCollezione));
        model.addAttribute(COLLEZIONE_CORRENTE, this.collezioneService.findById(idCollezione));
        return "admin_gestisci_collezione";
    }
}

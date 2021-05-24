package it.uniroma3.siw.controller;

import it.uniroma3.siw.repository.CollezioneRepository;
import it.uniroma3.siw.service.CollezioneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/collezione"})
public class CollezioneController {
    private final CollezioneService collezioneService;

    public CollezioneController(final CollezioneService collezioneService) {
        this.collezioneService = collezioneService;
    }

    @GetMapping(path = "")
    public String getCollezioniPage(Model model) {
        model.addAttribute("collezioni", this.collezioneService.getCollezioni());
        return "collezioni";
    }

    @GetMapping(path = "/{idCollezione}")
    public String getCollezione(@PathVariable("idCollezione") Long idCollezione, Model model) {
        model.addAttribute("collezione", this.collezioneService.findById(idCollezione));
        return "collezione";
    }
}

package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {
    private final OperaService operaService;

    public HomeController(final OperaService operaService) {
        this.operaService = operaService;
    }

    @GetMapping(path = "")
    public String getHomepage(final Model model) {
        model.addAttribute("opere", this.operaService.getOpere());
        return "home";
    }

    @GetMapping(path = "/informazioni")
    public String getInformazioniPage(final Model model) {
        return "informazioni";
    }
}

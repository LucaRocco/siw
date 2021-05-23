package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/opera")
public class OperaController {
    private final OperaService operaService;

    public OperaController(OperaService operaService) {
        this.operaService = operaService;
    }

    @GetMapping(path = "/{idOpera}")
    public String getOperaById(@PathVariable Long idOpera, Model model) {
        model.addAttribute("opera", this.operaService.findById(idOpera));
        return "opera";
    }
}

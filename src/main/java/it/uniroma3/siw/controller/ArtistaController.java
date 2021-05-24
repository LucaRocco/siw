package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.ArtistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artista")
public class ArtistaController {
    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping(path = {"/{idArtista}"})
    public String getArtisti(@PathVariable("idArtista") Long idArtista, Model model) {
        model.addAttribute("artista", this.artistaService.findArtistaById(idArtista));
        return "artista";
    }


}

package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.ArtistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artisti")
public class ArtistaController {
    private final ArtistaService artistaService;

    public ArtistaController(final ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping(path = {"/{idArtista}"})
    public String getArtistaPerId(@PathVariable("idArtista") final Long idArtista, final Model model) {
        model.addAttribute("artista", this.artistaService.findArtistaById(idArtista));
        return "artista";
    }

    @GetMapping(path = { "", "/" })
    public String getArtistiPage(final Model model) {
        model.addAttribute("artisti", this.artistaService.getArtisti());
        return "artisti";
    }
}

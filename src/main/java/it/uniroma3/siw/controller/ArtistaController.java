package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artista")
public class ArtistaController {
    @GetMapping(path = {"", "/"})
    public String getArtisti(Model model) {
        return "artista";
    }
}

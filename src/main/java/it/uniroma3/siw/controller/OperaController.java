package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CloudinaryService;
import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class OperaController {
    private final OperaService operaService;
    private final ArtistaService artistaService;
    private final CloudinaryService cloudinaryService;

    public OperaController(OperaService operaService, ArtistaService artistaService, CloudinaryService cloudinaryService) {
        this.operaService = operaService;
        this.artistaService = artistaService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping(path = "/opera/{idOpera}")
    public String getOperaById(@PathVariable Long idOpera, Model model) {
        model.addAttribute("opera", this.operaService.findById(idOpera));
        return "opera";
    }

    @GetMapping(path = "/admin/opere")
    public String getOpere(Model model) {
        model.addAttribute("opere", this.operaService.getOpere());
        return "admin_opere";
    }

    @PostMapping(path = "/admin/opera/{idOpera}/elimina")
    public String eliminaOpera(@PathVariable("idOpera") Long idOpera, Model model) {
        this.operaService.eliminaOperaById(idOpera);
        return "redirect:/admin/opere";
    }

    @GetMapping(path = "/admin/crea_opera")
    public String getCreaOperaPage(Model model) {
        model.addAttribute("nuovaOpera", new Opera());
        model.addAttribute("artisti", this.artistaService.getArtisti());
        return "admin_crea_opera";
    }

    @PostMapping(path = "/admin/crea_opera")
    public String creaOpera(Model model,
                            @ModelAttribute Opera opera,
                            @RequestParam Long autoreSelezionato,
                            @RequestParam("immagine") MultipartFile immagine) {
        this.operaService.salva(opera, this.cloudinaryService.salvaImmagine(immagine),
                this.artistaService.findArtistaById(autoreSelezionato));
        return "redirect:/admin/opere";
    }
}

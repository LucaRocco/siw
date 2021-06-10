package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.OperaValidator;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CloudinaryService;
import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class OperaController {
    private final OperaService operaService;
    private final ArtistaService artistaService;
    private final CloudinaryService cloudinaryService;
    private OperaValidator operaValidator;

    public OperaController(final OperaService operaService, final ArtistaService artistaService,
                           final CloudinaryService cloudinaryService, final OperaValidator operaValidator) {
        this.operaService = operaService;
        this.artistaService = artistaService;
        this.cloudinaryService = cloudinaryService;
        this.operaValidator = operaValidator;
    }

    @GetMapping(path = "/opera/{idOpera}")
    public String getOperaById(@PathVariable final Long idOpera, final Model model) {
        model.addAttribute("opera", this.operaService.findById(idOpera));
        return "opera";
    }

    @GetMapping(path = "/admin/opere")
    public String getOpere(final Model model) {
        model.addAttribute("opere", this.operaService.getOpere());
        return "admin_opere";
    }

    @PostMapping(path = "/admin/opera/{idOpera}/elimina")
    public String eliminaOpera(@PathVariable("idOpera") final Long idOpera, final Model model) {
        this.operaService.eliminaOperaById(idOpera);
        return "redirect:/admin/opere";
    }

    @GetMapping(path = "/admin/crea_opera")
    public String getCreaOperaPage(final Model model) {
        model.addAttribute("nuovaOpera", new Opera());
        model.addAttribute("artisti", this.artistaService.getArtisti());
        return "admin_crea_opera";
    }

    @PostMapping(path = "/admin/crea_opera")
    public String creaOpera(@ModelAttribute("nuovaOpera") final Opera nuovaOpera,
                            final Model model,
                            final BindingResult bindingResult,
                            @RequestParam final Long autoreSelezionato,
                            @RequestParam("immagine") final MultipartFile immagine) {
        this.operaValidator.validate(nuovaOpera, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.operaService.salva(nuovaOpera, this.cloudinaryService.salvaImmagine(immagine),
                    this.artistaService.findArtistaById(autoreSelezionato));
            return "redirect:/admin/opere";
        }
        model.addAttribute("artisti", this.artistaService.getArtisti());
        return "admin_crea_opera";
    }
}

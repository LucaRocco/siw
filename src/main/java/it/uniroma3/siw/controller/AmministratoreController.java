package it.uniroma3.siw.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static it.uniroma3.siw.utility.Costanti.COLLEZIONE_CORRENTE;
import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/admin")
public class AmministratoreController {
    private final AmministratoreService amministratoreService;
    private final CollezioneService collezioneService;
    private final OperaService operaService;
    private CuratoreService curatoreService;
    private ArtistaService artistaService;
    private Cloudinary cloudinary;

    public AmministratoreController(AmministratoreService amministratoreService,
                                    CollezioneService collezioneService,
                                    OperaService operaService,
                                    CuratoreService curatoreService,
                                    ArtistaService artistaService,
                                    Cloudinary cloudinary) {
        this.amministratoreService = amministratoreService;
        this.collezioneService = collezioneService;
        this.operaService = operaService;
        this.curatoreService = curatoreService;
        this.artistaService = artistaService;
        this.cloudinary = cloudinary;
    }

    @GetMapping(path = {"/collezioni", "/", ""})
    public String getCollezioniPerAdmin(Model model) {
        model.addAttribute("collezioni", collezioneService.getCollezioni());
        return "admin_collezioni";
    }

    @GetMapping(path = "/crea_collezione")
    public String getCreaCollezionePage(Model model) {
        model.addAttribute("curatori", this.curatoreService.getCuratori());
        model.addAttribute("nuovaCollezione", new Collezione());
        return "admin_crea_collezione";
    }

    @PostMapping(path = "/crea_collezione")
    public String creaCollezione(Model model, @RequestParam String curatoreSelezionato, @ModelAttribute Collezione nuovaCollezione) {
        this.collezioneService.salvaCollezione(nuovaCollezione, this.curatoreService.findById(curatoreSelezionato));
        return "redirect:/admin/collezioni";
    }

    @PostMapping(path = "/collezione/{idCollezione}/gestisci")
    public String aggiornaCollezione(@PathVariable Long idCollezione, @ModelAttribute Collezione nuovaCollezione, Model model) {
        this.collezioneService.aggiornaCollezione(idCollezione, nuovaCollezione);
        return "redirect:/admin/collezioni";
    }

    @GetMapping(path = "/opere")
    public String getOpere(Model model) {
        model.addAttribute("opere", this.operaService.getOpere());
        return "admin_opere";
    }

    @PostMapping(path = "/opera/{idOpera}/elimina")
    public String eliminaOpera(@PathVariable("idOpera") Long idOpera, Model model) {
        this.operaService.eliminaOperaById(idOpera);
        return "redirect:/admin/opere";
    }

    @GetMapping(path = "/crea_opera")
    public String getCreaOperaPage(Model model) {
        model.addAttribute("nuovaOpera", new Opera());
        model.addAttribute("artisti", this.artistaService.getArtisti());
        return "admin_crea_opera";
    }

    @PostMapping(path = "/crea_opera")
    public String creaOpera(Model model,
                            @ModelAttribute Opera opera,
                            @RequestParam Long autoreSelezionato,
                            @RequestParam("immagine") MultipartFile multipartFile) {
        String imageUrl = "";
        try {
            imageUrl = (String) this.cloudinary.uploader().upload(this.amministratoreService.convert(multipartFile), ObjectUtils.emptyMap()).get("secure_url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.operaService.salva(opera, imageUrl, this.artistaService.findArtistaById(autoreSelezionato));
        return "redirect:/admin/opere";
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
        this.collezioneService.aggiungiOpera(idCollezione, this.operaService.findById(idOpera));
        return "redirect:/admin/collezione/" + idCollezione + "/gestisci";
    }

    @PostMapping(path = "/collezione/{idCollezione}/gestisci/{idOpera}/elimina")
    public String eliminaOperaDaCollezione(@PathVariable("idCollezione") Long idCollezione,
                                           @PathVariable("idOpera") Long idOpera,
                                           Model model) {
        this.collezioneService.eliminaOpera(idCollezione, this.operaService.findById(idOpera));
        return "redirect:/admin/collezione/" + idCollezione + "/gestisci";
    }

    @PostMapping(path = "/collezione/{idCollezione}/elimina")
    public String eliminaCollezione(@PathVariable("idCollezione") Long idCollezione) {
        this.collezioneService.eliminaCollezioneById(idCollezione);
        return "redirect:/admin/collezioni";
    }
}

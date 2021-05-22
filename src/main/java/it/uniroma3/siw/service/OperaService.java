package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperaService {
    private final OperaRepository operaRepository;

    public OperaService(OperaRepository operaRepository) {
        this.operaRepository = operaRepository;
    }

    public List<Opera> getOpere() {
        return this.operaRepository.findAll();
    }
}

package br.com.infnet.GuildaAventureiros.controller;

import br.com.infnet.GuildaAventureiros.repository.AventureiroRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aventureiros")
public class AventureiroController {

    private final AventureiroRepository repository;

    public AventureiroController(AventureiroRepository repository) {
        this.repository = repository;
    }


}


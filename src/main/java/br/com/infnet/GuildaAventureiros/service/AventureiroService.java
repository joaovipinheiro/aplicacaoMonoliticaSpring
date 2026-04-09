package br.com.infnet.GuildaAventureiros.service;

import br.com.infnet.GuildaAventureiros.dto.AventureiroDTO;
import br.com.infnet.GuildaAventureiros.dto.CompanheiroDTO;
import br.com.infnet.GuildaAventureiros.exception.ResourceNotFoundException;
import br.com.infnet.GuildaAventureiros.model.Aventureiro;
import br.com.infnet.GuildaAventureiros.model.Classe;
import br.com.infnet.GuildaAventureiros.model.Companheiro;
import br.com.infnet.GuildaAventureiros.model.Especie;
import br.com.infnet.GuildaAventureiros.repository.AventureiroRepository;
<<<<<<< HEAD
import br.com.infnet.GuildaAventureiros.repository.OrganizacaoRepository;
import br.com.infnet.GuildaAventureiros.repository.UsuarioRepository;
=======
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AventureiroService {

    private final AventureiroRepository repository;
<<<<<<< HEAD
    private final OrganizacaoRepository organizacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public AventureiroService(AventureiroRepository repository,
                              OrganizacaoRepository organizacaoRepository,
                              UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.organizacaoRepository = organizacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Aventureiro> listarTodosFiltrados(String classeFiltro, Boolean ativo, Integer nivelMinimo) {
        return repository.findAll().stream()
                .filter(a -> (classeFiltro == null || a.getClasse().name().equalsIgnoreCase(classeFiltro)))
=======

    public AventureiroService(AventureiroRepository repository) {
        this.repository = repository;
    }

    public List<Aventureiro> listarTodosFiltrados(String classeFiltro, Boolean ativo, Integer nivelMinimo) {
        Classe classeEnum = null;
        if (classeFiltro != null && !classeFiltro.isBlank()) {
            try {
                classeEnum = Classe.valueOf(classeFiltro.toUpperCase());
            } catch (IllegalArgumentException e) {
                return List.of();
            }
        }

        final Classe filtroClasseFinal = classeEnum;

        return repository.getTodos().stream()
                .filter(a -> (filtroClasseFinal == null || a.getClasse().equals(filtroClasseFinal)))
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
                .filter(a -> (ativo == null || a.isAtivo() == ativo))
                .filter(a -> (nivelMinimo == null || a.getNivel() >= nivelMinimo))
                .sorted(Comparator.comparing(Aventureiro::getId))
                .collect(Collectors.toList());
    }

    public Aventureiro buscarPorId(Long id) {
<<<<<<< HEAD
        return repository.findById(id)
=======
        return repository.getTodos().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
                .orElseThrow(() -> new ResourceNotFoundException("Aventureiro com ID " + id + " não encontrado."));
    }

    public Aventureiro criar(AventureiroDTO dto) {
<<<<<<< HEAD
        Aventureiro novo = new Aventureiro();
        novo.setNome(dto.getNome());
        novo.setClasse(Classe.valueOf(dto.getClasse().toUpperCase()));
        novo.setNivel(dto.getNivel());
        novo.setAtivo(true);
        novo.setOrganizacao(organizacaoRepository.findById(1L).orElseThrow());
        novo.setUsuarioResponsavel(usuarioRepository.findById(1L).orElseThrow());

        return repository.save(novo);
=======
        Classe classeEnum = Classe.valueOf(dto.getClasse().toUpperCase());
        Aventureiro novo = new Aventureiro(null, dto.getNome(), classeEnum, dto.getNivel(), true, null);
        return repository.salvar(novo);
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    }

    public Aventureiro atualizar(Long id, AventureiroDTO dto) {
        Aventureiro existente = buscarPorId(id);
        existente.setNome(dto.getNome());
        existente.setClasse(Classe.valueOf(dto.getClasse().toUpperCase()));
        existente.setNivel(dto.getNivel());
<<<<<<< HEAD
        return repository.save(existente);
=======
        return existente;
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    }

    public void inativar(Long id) {
        Aventureiro existente = buscarPorId(id);
        existente.setAtivo(false);
<<<<<<< HEAD
        repository.save(existente);
=======
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    }

    public void ativar(Long id) {
        Aventureiro existente = buscarPorId(id);
        existente.setAtivo(true);
<<<<<<< HEAD
        repository.save(existente);
=======
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    }

    public Aventureiro definirCompanheiro(Long idAventureiro, CompanheiroDTO dto) {
        Aventureiro aventureiro = buscarPorId(idAventureiro);
<<<<<<< HEAD

        Companheiro companheiro = new Companheiro();
        companheiro.setNome(dto.getNome());
        companheiro.setEspecie(Especie.valueOf(dto.getEspecie().toUpperCase()));
        companheiro.setLealdade(dto.getLealdade());
        companheiro.setAventureiro(aventureiro);
        aventureiro.setCompanheiro(companheiro);

        return repository.save(aventureiro);
=======
        Especie especieEnum = Especie.valueOf(dto.getEspecie().toUpperCase());
        Companheiro companheiro = new Companheiro(dto.getNome(), especieEnum, dto.getLealdade());

        aventureiro.setCompanheiro(companheiro);
        return aventureiro;
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    }

    public void removerCompanheiro(Long idAventureiro) {
        Aventureiro aventureiro = buscarPorId(idAventureiro);
        aventureiro.setCompanheiro(null);
<<<<<<< HEAD
        repository.save(aventureiro);
=======
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    }
}
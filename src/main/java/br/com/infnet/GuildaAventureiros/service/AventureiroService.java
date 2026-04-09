package br.com.infnet.GuildaAventureiros.service;

import br.com.infnet.GuildaAventureiros.dto.AventureiroDTO;
import br.com.infnet.GuildaAventureiros.dto.CompanheiroDTO;
import br.com.infnet.GuildaAventureiros.exception.ResourceNotFoundException;
import br.com.infnet.GuildaAventureiros.model.Aventureiro;
import br.com.infnet.GuildaAventureiros.model.Classe;
import br.com.infnet.GuildaAventureiros.model.Companheiro;
import br.com.infnet.GuildaAventureiros.model.Especie;
import br.com.infnet.GuildaAventureiros.repository.AventureiroRepository;
import br.com.infnet.GuildaAventureiros.repository.OrganizacaoRepository;
import br.com.infnet.GuildaAventureiros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AventureiroService {

    private final AventureiroRepository repository;
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
                .filter(a -> (ativo == null || a.isAtivo() == ativo))
                .filter(a -> (nivelMinimo == null || a.getNivel() >= nivelMinimo))
                .sorted(Comparator.comparing(Aventureiro::getId))
                .collect(Collectors.toList());
    }

    public Aventureiro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aventureiro com ID " + id + " não encontrado."));
    }

    public Aventureiro criar(AventureiroDTO dto) {
        Aventureiro novo = new Aventureiro();
        novo.setNome(dto.getNome());
        novo.setClasse(Classe.valueOf(dto.getClasse().toUpperCase()));
        novo.setNivel(dto.getNivel());
        novo.setAtivo(true);
        novo.setOrganizacao(organizacaoRepository.findById(1L).orElseThrow());
        novo.setUsuarioResponsavel(usuarioRepository.findById(1L).orElseThrow());

        return repository.save(novo);
    }

    public Aventureiro atualizar(Long id, AventureiroDTO dto) {
        Aventureiro existente = buscarPorId(id);
        existente.setNome(dto.getNome());
        existente.setClasse(Classe.valueOf(dto.getClasse().toUpperCase()));
        existente.setNivel(dto.getNivel());
        return repository.save(existente);
    }

    public void inativar(Long id) {
        Aventureiro existente = buscarPorId(id);
        existente.setAtivo(false);
        repository.save(existente);
    }

    public void ativar(Long id) {
        Aventureiro existente = buscarPorId(id);
        existente.setAtivo(true);
        repository.save(existente);
    }

    public Aventureiro definirCompanheiro(Long idAventureiro, CompanheiroDTO dto) {
        Aventureiro aventureiro = buscarPorId(idAventureiro);

        Companheiro companheiro = new Companheiro();
        companheiro.setNome(dto.getNome());
        companheiro.setEspecie(Especie.valueOf(dto.getEspecie().toUpperCase()));
        companheiro.setLealdade(dto.getLealdade());
        companheiro.setAventureiro(aventureiro);
        aventureiro.setCompanheiro(companheiro);

        return repository.save(aventureiro);
    }

    public void removerCompanheiro(Long idAventureiro) {
        Aventureiro aventureiro = buscarPorId(idAventureiro);
        aventureiro.setCompanheiro(null);
        repository.save(aventureiro);
    }
}
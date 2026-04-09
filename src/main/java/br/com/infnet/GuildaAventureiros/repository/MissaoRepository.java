package br.com.infnet.GuildaAventureiros.repository;

import br.com.infnet.GuildaAventureiros.dto.RelatorioMissaoDTO;
import br.com.infnet.GuildaAventureiros.model.aventura.Missao;
import br.com.infnet.GuildaAventureiros.model.aventura.NivelPerigo;
import br.com.infnet.GuildaAventureiros.model.aventura.StatusMissao;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    @Query("SELECT m FROM Missao m WHERE " +
            "(:status IS NULL OR m.status = :status) AND " +
            "(:nivelPerigo IS NULL OR m.nivelPerigo = :nivelPerigo) AND " +
            "(m.dataCriacao >= COALESCE(:dataInicio, m.dataCriacao)) AND " +
            "(m.dataCriacao <= COALESCE(:dataFim, m.dataCriacao))")
    Page<Missao> buscarComFiltros(
            @Param("status") StatusMissao status,
            @Param("nivelPerigo") NivelPerigo nivelPerigo,
            @Param("dataInicio") OffsetDateTime dataInicio,
            @Param("dataFim") OffsetDateTime dataFim,
            Pageable pageable
    );

    @Query("SELECT m FROM Missao m LEFT JOIN FETCH m.participacoes p LEFT JOIN FETCH p.aventureiro WHERE m.id = :id")
    Optional<Missao> buscarDetalhesComParticipantes(@Param("id") Long id);

    @Query("SELECT new br.com.infnet.GuildaAventureiros.dto.RelatorioMissaoDTO(" +
            "m.titulo, m.status, m.nivelPerigo, " +
            "COUNT(p.aventureiro.id), " +
            "SUM(COALESCE(p.recompensaOuro, 0))) " +
            "FROM Missao m LEFT JOIN m.participacoes p " +
            "WHERE (m.dataCriacao >= COALESCE(:dataInicio, m.dataCriacao)) AND " +
            "(m.dataCriacao <= COALESCE(:dataFim, m.dataCriacao)) " +
            "GROUP BY m.id, m.titulo, m.status, m.nivelPerigo")
    List<RelatorioMissaoDTO> gerarRelatorioMetricas(
            @Param("dataInicio") OffsetDateTime dataInicio,
            @Param("dataFim") OffsetDateTime dataFim
    );
}
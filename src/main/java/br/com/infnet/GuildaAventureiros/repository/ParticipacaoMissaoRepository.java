package br.com.infnet.GuildaAventureiros.repository;

import br.com.infnet.GuildaAventureiros.dto.RankingAventureiroDTO;
import br.com.infnet.GuildaAventureiros.model.aventura.ParticipacaoMissao;
import br.com.infnet.GuildaAventureiros.model.aventura.ParticipacaoMissaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipacaoMissaoRepository extends JpaRepository<ParticipacaoMissao, ParticipacaoMissaoId> {
    @Query("SELECT new br.com.infnet.GuildaAventureiros.dto.RankingAventureiroDTO(" +
            "p.aventureiro.id, p.aventureiro.nome, " +
            "COUNT(p.missao.id), " +
            "SUM(p.recompensaOuro), " +
            "SUM(CASE WHEN p.destaqueMvp = true THEN 1 ELSE 0 END)) " +
            "FROM ParticipacaoMissao p " +
            "GROUP BY p.aventureiro.id, p.aventureiro.nome " +
            "ORDER BY COUNT(p.missao.id) DESC, SUM(p.recompensaOuro) DESC")
    List<RankingAventureiroDTO> gerarRankingParticipacao();
}
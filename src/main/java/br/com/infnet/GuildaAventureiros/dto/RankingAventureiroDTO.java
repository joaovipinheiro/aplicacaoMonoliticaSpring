package br.com.infnet.GuildaAventureiros.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RankingAventureiroDTO {
    private Long aventureiroId;
    private String nomeAventureiro;
    private Long totalParticipacoes;
    private Double somaRecompensas;
    private Long quantidadeDestaques;
}
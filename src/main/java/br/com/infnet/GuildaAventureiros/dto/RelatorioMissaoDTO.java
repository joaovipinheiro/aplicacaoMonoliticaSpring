package br.com.infnet.GuildaAventureiros.dto;

import br.com.infnet.GuildaAventureiros.model.aventura.StatusMissao;
import br.com.infnet.GuildaAventureiros.model.aventura.NivelPerigo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RelatorioMissaoDTO {
    private String titulo;
    private StatusMissao status;
    private NivelPerigo nivelPerigo;
    private Long quantidadeParticipantes;
    private Double totalRecompensas;
}


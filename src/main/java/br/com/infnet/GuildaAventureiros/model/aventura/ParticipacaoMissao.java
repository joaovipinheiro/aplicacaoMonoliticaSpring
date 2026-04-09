package br.com.infnet.GuildaAventureiros.model.aventura;

import br.com.infnet.GuildaAventureiros.model.Aventureiro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "participacoes_missao", schema = "aventura")
public class ParticipacaoMissao {

    @EmbeddedId
    private ParticipacaoMissaoId id = new ParticipacaoMissaoId();

    @ManyToOne
    @MapsId("missaoId")
    @JoinColumn(name = "missao_id")
    private Missao missao;

    @ManyToOne
    @MapsId("aventureiroId")
    @JoinColumn(name = "aventureiro_id")
    private Aventureiro aventureiro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PapelMissao papelNaMissao;

    @Min(0)
    @Column(name = "recompensa_ouro")
    private Double recompensaOuro = 0.0;

    @Column(name = "destaque_mvp", nullable = false)
    private Boolean destaqueMvp = false;

    @Column(name = "data_registro", updatable = false)
    private OffsetDateTime dataRegistro;

    @PrePersist
    protected void onCreate() {
        this.dataRegistro = OffsetDateTime.now();
    }
}
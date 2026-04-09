package br.com.infnet.GuildaAventureiros.model.aventura;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ParticipacaoMissaoId implements Serializable {

    private Long missaoId;
    private Long aventureiroId;

}
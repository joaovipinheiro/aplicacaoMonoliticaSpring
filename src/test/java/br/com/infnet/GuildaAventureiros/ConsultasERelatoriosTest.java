package br.com.infnet.GuildaAventureiros;

import br.com.infnet.GuildaAventureiros.dto.RankingAventureiroDTO;
import br.com.infnet.GuildaAventureiros.dto.RelatorioMissaoDTO;
import br.com.infnet.GuildaAventureiros.model.Aventureiro;
import br.com.infnet.GuildaAventureiros.model.aventura.Missao;
import br.com.infnet.GuildaAventureiros.repository.AventureiroRepository;
import br.com.infnet.GuildaAventureiros.repository.MissaoRepository;
import br.com.infnet.GuildaAventureiros.repository.ParticipacaoMissaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsultasERelatoriosTest {

    @Autowired
    private AventureiroRepository aventureiroRepository;

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private ParticipacaoMissaoRepository participacaoMissaoRepository;

    @Test
    void testConsultaSimplesComFiltro() {
        System.out.println("--- LOG DA QUERY: CONSULTA SIMPLES ---");
        Page<Aventureiro> resultado = aventureiroRepository.buscarComFiltros(true, null, 5, PageRequest.of(0, 10));
        assertNotNull(resultado);
    }

    @Test
    void testConsultaComMultiplosRelacionamentos() {
        System.out.println("--- LOG DA QUERY: MÚLTIPLOS RELACIONAMENTOS ---");
        Optional<Missao> missao = missaoRepository.buscarDetalhesComParticipantes(1L);
        assertNotNull(missao);
    }

    @Test
    void testConsultaComAgregacaoRanking() {
        System.out.println("--- LOG DA QUERY: AGREGAÇÃO (RANKING) ---");
        List<RankingAventureiroDTO> ranking = participacaoMissaoRepository.gerarRankingParticipacao();
        assertNotNull(ranking);
    }

    @Test
    void testConsultaComAgregacaoRelatorioMissoes() {
        System.out.println("--- LOG DA QUERY: AGREGAÇÃO (RELATÓRIO DE MISSÕES) ---");
        List<RelatorioMissaoDTO> relatorio = missaoRepository.gerarRelatorioMetricas(null, null);
        assertNotNull(relatorio);
    }
}
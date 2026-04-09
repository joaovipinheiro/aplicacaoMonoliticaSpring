package br.com.infnet.GuildaAventureiros.repository;

import br.com.infnet.GuildaAventureiros.model.Aventureiro;
import br.com.infnet.GuildaAventureiros.model.Classe;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {

    Page<Aventureiro> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT a FROM Aventureiro a WHERE " +
            "(:ativo IS NULL OR a.ativo = :ativo) AND " +
            "(:classe IS NULL OR a.classe = :classe) AND " +
            "(:nivelMinimo IS NULL OR a.nivel >= :nivelMinimo)")
    Page<Aventureiro> buscarComFiltros(
            @Param("ativo") Boolean ativo,
            @Param("classe") Classe classe,
            @Param("nivelMinimo") Integer nivelMinimo,
            Pageable pageable
    );
}
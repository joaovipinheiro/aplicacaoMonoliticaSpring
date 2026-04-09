package br.com.infnet.GuildaAventureiros.repository;

import br.com.infnet.GuildaAventureiros.model.Companheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanheiroRepository extends JpaRepository<Companheiro, Long> {
}
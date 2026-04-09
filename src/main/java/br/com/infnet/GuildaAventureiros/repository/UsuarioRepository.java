package br.com.infnet.GuildaAventureiros.repository;

import br.com.infnet.GuildaAventureiros.model.audit.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
}

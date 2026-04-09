package br.com.infnet.GuildaAventureiros;

import br.com.infnet.GuildaAventureiros.model.audit.Usuario;
import br.com.infnet.GuildaAventureiros.repository.OrganizacaoRepository;
import br.com.infnet.GuildaAventureiros.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuditMappingTest {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Test
    void testCarregarUsuarioERoles() {
        Usuario usuario = usuarioRepository.findById(1L).orElse(null);

        assertNotNull(usuario, "Usuário deve existir no banco legado");
        assertNotNull(usuario.getOrganizacao(), "O relacionamento com organização deve funcionar");
        assertFalse("O usuário deve ter roles carregadas", usuario.getRoles().isEmpty());
        assertFalse("As permissões devem estar acessíveis via roles", usuario.getRoles().get(0).getPermissions().isEmpty());

    }
}

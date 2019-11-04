package pe.upn.embarcadero.service;

import java.util.Optional;

import pe.upn.embarcadero.model.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long> {
	Optional<Usuario> findByUsername(String username) throws Exception;
}

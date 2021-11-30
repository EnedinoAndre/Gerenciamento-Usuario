package service;

import java.util.List;

import model.Usuario;

public interface UsuarioService {
	
	public void salvar(Usuario usuario);
	public Usuario buscaUsuarioPorId(Long id);
	public List<Usuario> listarUsuarios();
	public boolean deletarUsuarioPorId(Long id);
	public boolean atualizarUsuario(Usuario usuario);

}

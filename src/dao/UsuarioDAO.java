package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
	
	public boolean salvar(Usuario usuario);
	public List<Usuario> listar();
	public void removerPeloId(Long id);
	public Usuario buscarPorNome(String nome);

}

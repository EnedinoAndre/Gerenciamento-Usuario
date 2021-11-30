package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Usuario;

public class UsuarioServiceImpl implements UsuarioService{
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	

	@Override
	public void salvar(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	@Override
	public Usuario buscaUsuarioPorId(Long id) {
		
		usuarios.forEach(u -> u.getId().equals(id));
		
		return null;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		
		return this.usuarios;
	}

	@Override
	public boolean deletarUsuarioPorId(Long id) {
		for(Usuario u : usuarios) {
			if(u.getId().equals(id)) {
				this.usuarios.remove(u);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean atualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

}

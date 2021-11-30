import java.util.List;

import dao.UsuarioDAOImpl;
import model.Usuario;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Usuario usuario = new Usuario();
		//usuario.setNome("André Enedino");
		//usuario.setEmail("andre@gmail.com");
		//usuario.setTelefone("99887766");
		
		/*
		Usuario usuario = new Usuario();
		usuario.setNome("Carlos Barbosa");
		usuario.setEmail("carlos@gmail.com");
		usuario.setTelefone("900000000");
		*/
		
		UsuarioDAOImpl dao = new UsuarioDAOImpl("jdbc:mysql://localhost:3306/"
				+ "usuario?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "root");
		
		//SALVAR NO BANCO
		//System.out.println(dao.salvar(usuario));
		
		//RECEBENDO USUÁRIOS DO BANCO
		List<Usuario> usuarios = dao.listar();
		
		//MOSTRAR
		System.out.println("------------- LISTAR --------------");
		usuarios.forEach(usu -> System.out.println(usu));
		
		//REMOVER
		//dao.removerPeloId(1L);
		
		System.out.println("------------- BUSCAR POR NOME --------------");
		//BUSCAR PELO NOME
		System.out.println(dao.buscarPorNome("Carlos"));
	}

}

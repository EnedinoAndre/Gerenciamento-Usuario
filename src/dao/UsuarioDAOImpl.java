package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/usuario?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private String jdbcUser = "root";
	private String jdbcSenha = "root";
	private Connection jdbcConnection;
	
	public UsuarioDAOImpl(String jdbcURL, String jdbcUser, String jdbcSenha) {
		
		this.jdbcURL = jdbcURL;
		this.jdbcUser = jdbcUser;
		this.jdbcSenha = jdbcSenha;
		
	}
	
	public void conectar() throws SQLException {
		
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
			}catch(Exception e){
				throw new SQLException();
			}
			
			this.jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcSenha);
		}
		
	}
	
	public void disconecta() throws SQLException{
		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	
	@Override
	public boolean salvar(Usuario usuario) {
		boolean inseriu = false;
		String sql = "INSERT INTO usuario (nome, email, telefone) VALUES (?,?,?)";
		
		try {
			
			conectar();
			
			java.sql.PreparedStatement statement = this.jdbcConnection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getTelefone());
			
			inseriu = statement.executeUpdate() > 0;
			
			statement.close();
			disconecta();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return inseriu;
		
	}

	@Override
	public List<Usuario> listar() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario";
		
		try {
			conectar();
			java.sql.PreparedStatement statement = this.jdbcConnection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				
				Long id = result.getLong("id");
				String nome = result.getString("nome");
				String email = result.getString("email");
				String telefone = result.getString("telefone");
				
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setTelefone(telefone);
				
				usuarios.add(usuario);
			}
			
			result.close();
			statement.close();
			disconecta();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuarios;
	}

	@Override
	public void removerPeloId(Long id) {
		
		String sql = "DELETE FROM usuario WHERE id LIKE ?";
		
		
		try {
			
			conectar();
			
			java.sql.PreparedStatement statement = this.jdbcConnection.prepareStatement(sql);
			//statement.executeQuery(sql);
			statement.setLong(1, id);
			System.out.println(statement.executeUpdate()); 
			
			statement.close();
			disconecta();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Usuário não encontrado");
			
		}
		
		
	}

	@Override
	public Usuario buscarPorNome(String nome) {
		
		Usuario usuario = new Usuario();
		String sql = "SELECT * FROM usuario WHERE nome LIKE '%"+nome+"%'";
		
		try {
			conectar();
			java.sql.PreparedStatement statement = this.jdbcConnection.prepareStatement(sql);
			//statement.setString(1, nome);
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				
				Long id = result.getLong("id");
				String nome1 = result.getString("nome");
				String email = result.getString("email");
				String telefone = result.getString("telefone");
				
				usuario.setId(id);
				usuario.setNome(nome1);
				usuario.setEmail(email);
				usuario.setTelefone(telefone);
			}
			
			result.close();
			statement.close();
			disconecta();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	}

}

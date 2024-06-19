package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() {
		super();
		databaseConnection();
	}
	
	public void bdClose() {
		databaseConnectionClose();
	}
	
	public boolean insert(Usuario usuario) {
		System.out.println("chamou insert");
		boolean status = false;
			try {
				System.out.println("chamou insert");
				Statement stm = conn.createStatement();
				String query = "insert into usuario (nome, email, senha) values ('" + usuario.getNome() + "', '" + usuario.getEmail() + "', '"+ usuario.getSenha() +"')";
				stm.executeUpdate(query);
				stm.close();
				status = true;
				System.out.println("chamou insert");
			} catch (SQLException e) {
				e.getMessage();
			}
		
		return status;
	}
	
	public Usuario getUsuario(String email, String senha) {
		try {
			Statement stm = conn.createStatement();
			String query = "select * from usuario where email='" + email + "' AND senha='" + senha + "'";
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				Usuario aux = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
				stm.close();
				return aux;
			}
			
		} catch (SQLException e) {
			System.out.println("deu ruim " + e.getMessage());
		}
		
		return null;
	}
	
	public String getAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Statement stm = conn.createStatement();
			String query = "select * from usuario";
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
                Usuario u = new Usuario(rs.getInt("usuario_id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                usuarios.add(u);
            }
			stm.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return usuarios.toString();
	}
}

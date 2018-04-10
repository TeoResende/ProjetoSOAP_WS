package CRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Banco.Banco;
import DAO.Usuario;

public class UsuarioCRUD {
	
	public boolean novoUsuario(Usuario novoUsuario) {
		boolean resposta = true;
		String sql = "INSERT INTO usuarios "
				+ "(usuario,senha,nome,sexo,email,nivel) "
				+ "VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = 
					Banco.getConexao().prepareStatement(sql);
			stmt.setString(1,novoUsuario.getUsuario());
			stmt.setString(2,novoUsuario.getSenha());
			stmt.setString(3, novoUsuario.getNome());
			stmt.setString(4, novoUsuario.getSexo());
			stmt.setString(5, novoUsuario.getEmail());
			stmt.setInt(6, novoUsuario.getNivel());
			resposta = stmt.execute();
			stmt.close();
		}catch(SQLException t) {
			t.printStackTrace();
		}
		return resposta;
	}
	
	public boolean alteraUsuario(Usuario alteraUsuario) {
		boolean resposta = true;
		String sql = "UPDATE usuarios SET"
				+ " usuario=?, senha=?, nome=?, sexo=?,"
				+ " email=?, nivel=? WHERE idUsuario = ?";
		try {
			PreparedStatement stmt = Banco.getConexao().prepareStatement(sql);
			stmt.setString(1,alteraUsuario.getUsuario());
			stmt.setString(2,alteraUsuario.getSenha());
			stmt.setString(3,alteraUsuario.getNome());
			stmt.setString(4,alteraUsuario.getSexo());
			stmt.setString(5,alteraUsuario.getEmail());
			stmt.setInt(   6,alteraUsuario.getNivel());
			stmt.setInt(   7,alteraUsuario.getId());
			resposta = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resposta;
	}
	
	public boolean removeUsuario(Usuario remover) {
		boolean resposta = true;
		String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
		try {
			PreparedStatement stmt = Banco.getConexao().prepareStatement(sql);
			stmt.setInt(1, remover.getId());
			resposta = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resposta;
	}
	
	public ResultSet selecionaUsuario() {
		ResultSet dados = null;
		String sql = "SELECT * FROM usuarios ORDER BY nome";
		try {
			PreparedStatement stmt = Banco.getConexao().prepareStatement(sql);
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dados;
	}
	
	public ResultSet selecionaUsuario(String x) {
		ResultSet dados = null;
		String sql = "SELECT * FROM usuarios WHERE nome like ? ORDER BY nome";
		try {
			PreparedStatement stmt = Banco.getConexao().prepareStatement(sql);
			stmt.setString(1, "%"+x+"%");
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dados;
	}
}

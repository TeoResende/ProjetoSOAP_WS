package CRUD;

import java.sql.PreparedStatement;
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
	
	public static void main(String[] args) {
		Usuario novo = new Usuario();
		novo.setUsuario("teo");
		novo.setSenha("minhasenha");
		novo.setNome("Francisco teófilo de Resende Netto");
		novo.setSexo("M");
		novo.setEmail("resendenetto@yahoo.com.br");
		novo.setNivel(1);
		
		if(new UsuarioCRUD().novoUsuario(novo)) {
			System.out.println("Erro ao cadastrar o novo usuário!");
		}else {
			System.out.println("novo usuario cadastrado com sucesso!");
		}
	}

}

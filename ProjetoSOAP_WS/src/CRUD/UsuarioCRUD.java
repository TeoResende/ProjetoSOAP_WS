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
	
	public static void main(String[] args) {
		Usuario novo = new Usuario();
		novo.setUsuario("seila");
		novo.setSenha("123");
		novo.setNome("Meu nome");
		novo.setSexo("M");
		novo.setEmail("oi@oi.com.oi");
		novo.setNivel(100);
		
		if(new UsuarioCRUD().novoUsuario(novo)) {
			System.out.println("Erro ao cadastrar o novo usuário!");
		}else {
			System.out.println("novo usuario cadastrado com sucesso!");
		}
	}

}

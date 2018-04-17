package WebService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Banco.Banco;
import Email.EnviarEmail;

public class WebService {
	
	public String testeLogin(String usuario, String senha) {
		String msg = "";
		String sql = "SELECT * FROM usuarios "
				+ "WHERE usuario = ? and senha = ?";
		try {
			PreparedStatement stmt = 
					Banco.getConexao().prepareStatement(sql);
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			ResultSet dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			if(dados.next()) {
				msg = "OK";
			}else {
				msg = "Erro";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	public String recuperarSenha(String email) {
		String resposta = "";
		resposta = new EnviarEmail().enviarEmail(email);
		return resposta;
	}
}

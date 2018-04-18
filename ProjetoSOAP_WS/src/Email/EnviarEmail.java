package Email;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import Banco.Banco;

public class EnviarEmail {
	
	public ResultSet verificaEmail(String email) {
		ResultSet emailValido = null;
		String sql = "SELECT * FROM usuarios WHERE email = ?";
		PreparedStatement stmt;
		try {
			stmt = Banco.getConexao().prepareStatement(sql);
			stmt.setString(1, email);
			emailValido = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return emailValido;
	}

	public String novaSenha() {
		String nova = "";
		Random r = new Random();
		int numero = 0;
		char valor;
		for(int x=0;x<8;x++) {
			numero = r.nextInt(74)+48;
			if((numero > 57 && numero < 65) || (numero > 90 && numero < 97)) {
				x--;
				continue;
			}
			valor = (char)numero;
			nova =  nova + valor;
		}
		return nova;
	}
	
	public String enviarEmail(String email) {
		ResultSet conta = null;
		conta = new EnviarEmail().verificaEmail(email);
		String novaSenha;
		int idUsuario;
		try {
			if(conta.next()) {
				novaSenha = new EnviarEmail().novaSenha();
				idUsuario = conta.getInt("idUsuario");
			}else {
				return "E-mail invalido!";
			}
			
			if(alteraSenha(idUsuario,novaSenha)==true) {
				return "Erro ao alterar a senha no banco de dados";
			}else {
				try {
					SimpleEmail enviarEmail = new SimpleEmail();
					enviarEmail.setHostName("smtp.gmail.com");
					enviarEmail.setSmtpPort(465);
					enviarEmail.setAuthentication("projetojavasenai@gmail.com", "senai123");
					enviarEmail.setSSLOnConnect(true);
					enviarEmail.setFrom("projetojavasenai@gmail.com");
					enviarEmail.setSubject("Recuperação de senha - SENAI2018");
					enviarEmail.setMsg("Recuperação de SENHA \n"
							+ "Sua nova senha é "+novaSenha
							+ "\n Não responda este E-mail \n"
							+ "Equipe SENAI 2018");
					enviarEmail.addTo(email);
					enviarEmail.send();
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "E-mail enviado com sucesso!";
	}
	
	public boolean alteraSenha(int id,String senha) {
		boolean resposta = true;
		String sql = "UPDATE usuarios SET senha=? WHERE idUsuario = ?";
		try {
			PreparedStatement stmt = Banco.getConexao().prepareStatement(sql);
			stmt.setString(1, senha);
			stmt.setInt(2, id);
			resposta = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resposta;
	}

}

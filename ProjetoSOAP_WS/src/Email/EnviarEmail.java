package Email;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import Banco.Banco;

public class EnviarEmail {
	
	public ResultSet verificaEmail(String email) {
		ResultSet emailValido = null;
		String sql = "SELECT (idUsuario,email)"
				+ " FROM usuarios WHERE email = ?";
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
	
	public static void main(String[] args) {
		System.out.println(new EnviarEmail().novaSenha());
	}
}
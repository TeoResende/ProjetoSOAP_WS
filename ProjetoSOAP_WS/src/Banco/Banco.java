package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	
	static Connection conexao;
	
	public static Connection getConexao() {
		String usuario = "root";
		String senha = "root";
		String nomeBanco = "SoapWebService";
		//String ip = "127.0.0.1";
		String ip = "189.91.101.54";
		String porta = "3306";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:mysql://"+ip+":"+porta+"/"+nomeBanco,usuario,senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocorreu um erro ao conectar-se ao banco!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro driver não localizado!");
		}
		return conexao;
	}//fim do método getConexao()

}

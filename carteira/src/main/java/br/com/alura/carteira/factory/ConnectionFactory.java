package br.com.alura.carteira.factory;

import br.com.alura.carteira.dao.TransacaoDao;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.management.RuntimeErrorException;


public class ConnectionFactory {
	public Connection getConnection() {
		try {

			String url = "jdbc:mysql://localhost:3306/carteira?useTimezone=true&serverTimezone=UTC";
			String username = "root";
			String password = "admin";

			// Para forçar o carregamento do driver da class Mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Tomcat não carrega automaticamente o driver do Jdbc

			Connection conexao = DriverManager.getConnection(url, username, password);
			return conexao;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		
	}
}

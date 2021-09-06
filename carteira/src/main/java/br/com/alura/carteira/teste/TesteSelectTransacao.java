package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import br.com.alura.carteira.dao.TransacaoDao;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;

public class TesteSelectTransacao {
	public static void main(String[] args) {
		
		try {
		String url = "jdbc:mysql://localhost:3306/carteira?useTimezone=true&serverTimezone=UTC";
		String username = "root";
		String password = "admin";
		Connection conexao = DriverManager.getConnection(url,username,password);
		TransacaoDao dao = new TransacaoDao(conexao);
		
		List<Transacao> transacoes = dao.listar();
		
		transacoes.forEach(System.out::println);
		
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro");
		}
		
			
		
		/* Ou isso:
		 * for (Transacao transacao : transacoes) { 
		 * System.out.println(transacao); }
		 */	}

}

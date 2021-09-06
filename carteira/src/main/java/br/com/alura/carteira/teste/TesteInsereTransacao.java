/*
package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;

public class TesteInsereTransacao {
	public static void main(String[] args) {
		
		try {
			// estabelecer conexao com MySQL

			String url = "jdbc:mysql://localhost:3306/carteira";
			String usuario = "root";
			String senha = "admin";

			Connection conexao = DriverManager.getConnection(url, usuario, senha);

			Transacao t = new Transacao(
					"PRIO3", 
					LocalDate.of(2021, 2, 1), 
					new BigDecimal("20.9"), 
					300,
					TipoTransacao.VENDA);

			// Para se proteger contra ataques de SQL injections
			String sql = "insert into transacoes(ticker,data,preco,quantidade,tipo) values(?,?,?,?,?)";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, t.getTicker());
			ps.setDate(2, Date.valueOf(t.getData()));
			ps.setBigDecimal(3, t.getPreco());
			ps.setInt(4, t.getQuantidade());
			ps.setString(5, t.getTipo().toString());

			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao conectar no MySQL.");
		}

	}
} */


package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.alura.carteira.dao.TransacaoDao;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;

public class TesteInsereTransacao {

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/carteira?useTimezone=true&serverTimezone=UTC";
			String username = "root";
			String password = "admin";
			Connection conexao = DriverManager.getConnection(url,username,password);
			TransacaoDao dao = new TransacaoDao(conexao);
			
			
			Transacao transacao = new Transacao("XPTO2",
					LocalDate.now(),
					new BigDecimal("333"),
					200,
					TipoTransacao.COMPRA);
			
			dao.cadastrar(transacao);
		}catch (Exception e) {
			System.out.println("Ocorreu um erro");
		}
		
	}

}

package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.mysql.cj.protocol.Resultset;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;

public class TesteSelectTransacao {
	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/carteira?useTimezone=true&serverTimezone=UTC";
			String username = "root";
			String password = "admin";
			Connection conexao = DriverManager.getConnection(url, username, password);

			
			String sql = "select * from transacoes";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			//boolean true tem proximo registro - false nao tem
			while(rs.next()) {
				Transacao t = new Transacao();
				
				t.setTicker(rs.getString("ticker"));
				t.setData(rs.getDate("data").toLocalDate());
				t.setPreco(rs.getBigDecimal("preco"));
				t.setQuantidade(rs.getInt("quantidade"));
				t.setTipo(TipoTransacao.valueOf(rs.getString("tipo")));
				
				System.out.println(t);
				System.out.println("=====================================");
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao conectar com MySQL!");
		}
	}

}

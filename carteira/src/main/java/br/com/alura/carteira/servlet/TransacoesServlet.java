package br.com.alura.carteira.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;




@WebServlet ("/transacoes")
public class TransacoesServlet extends HttpServlet{
	
	private List<Transacao> transacoes = new ArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		//encaminhando o List para o JSP
		req.setAttribute("transacoes", transacoes);
		
		//joga as infos do codigo java para a pagina JSP exibir
		req.getRequestDispatcher("/WEB-INF/jsp/transacoes.jsp")
		.forward(req, res);
		
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		  todos os atributos que não são Strings 
		  tem que ser convertidos, pois padrão do request é 
		  devolver como String
		*/
		try {
			String ticker = req.getParameter("ticker");
			LocalDate data =  LocalDate.parse(req.getParameter("data"),
					DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			BigDecimal preco = new BigDecimal(
					req.getParameter("preco").replace(",", "."));
			
			int quantidade = Integer.parseInt(req.getParameter("quantidade"));
			TipoTransacao tipo = TipoTransacao.valueOf(req.getParameter("tipo"));
			
			
			Transacao transacao = new Transacao(
					ticker, 
					data,
					preco, 
					quantidade, 
					tipo);
			
			transacoes.add(transacao);
			
			//como se estivesse fazendo uma requisicao Get para transacoes
			resp.sendRedirect("transacoes");
		}catch(NumberFormatException e){
			resp.sendRedirect("transacoes?erro= campo invalido");
		}
		
	}
	
	
}

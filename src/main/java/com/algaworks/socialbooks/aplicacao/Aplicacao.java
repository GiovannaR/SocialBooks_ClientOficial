package com.algaworks.socialbooks.aplicacao;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;



public class Aplicacao {

	public static void main(String[] args) throws ParseException{
	
		LivrosClient cliente = new LivrosClient("http://localhost:8080", "algaworks", "senha");
		
		List<Livro> listaLivros = cliente.listar();
		
		for(Livro livro : listaLivros){
			System.out.println("Livro : " + livro.getNome());
			
		}
		
		Livro livro = new Livro();
		livro.setNome("Git passo-a-passo");
		livro.setEditora("AlgaWorks");
		
		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
		livro.setPublicacao(publicacao.parse("01/01/2016"));
		
		livro.setResumo("Este livro mostra como o Git funciona");
		
		String localizacao = cliente.salvar(livro);
		
		System.out.println("URI do livro salvo: " + localizacao);
		
		Livro livroBuscado = cliente.buscar(localizacao);
		
		System.out.println("Livro buscado: " + livroBuscado.getNome());
	}
	

	
}

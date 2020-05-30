package threads.fila;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread{

	private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha_fila = new ConcurrentLinkedQueue<>(); //Estático para ser utilizada a mesma instância para todas os objetos dessa classe
	
	public static void add(ObjetoFilaThread objetoFilhaThread) {
		pilha_fila.add(objetoFilhaThread);
	}
	
	@Override
	public void run() {//Fazendo um processamento em paralelo concorrente de processo muito pesado como gerar PDFs por exemplo sem travar o sistema
		while(true) {
		synchronized(pilha_fila) { // Bloqueia o acesso a esta lista por outros processos, isso garante que apenas uma Theread vai acessar essa pilha até o fim de seu processamento
			Iterator<ObjetoFilaThread> iteracao = pilha_fila.iterator();
			while(iteracao.hasNext()) { // hasNext verifica se tem um próximo elemento a ser percorrido na lista
				
				ObjetoFilaThread processar = iteracao.next(); // Pegando  objeto iterado no momento que no caso como é uma fila, é sempre pego o primeiro elemento desta coleção
				 
				/*
				 * Aqui ficaria os processamentos pesados, tais como geração de PDFs, 
				 * Emissão de notas ficais, envio de emails, geração de relatórios
				*/
				
				System.out.println("----------------------------------------------------------------------------");
				System.out.println("Processando dados...");
				System.out.println(processar.getNome());
				System.out.println(processar.getEmail());
				System.out.println("Posição: " + processar.getPosicao());
				System.out.println("----------------------------------------------------------------------------");
				iteracao.remove();//Retirando o elemento iterado da lista para que ele não seja iterado novamente
				try {
					Thread.sleep(1000); // Dar uma pausa para descarga de memória
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		  }
		
		try {
			Thread.sleep(1000); // Depois de processar toda a lista dá um tempo para limpeza de memória
		} catch (InterruptedException e) {
			e.printStackTrace();
		  }
		}	
	}
}

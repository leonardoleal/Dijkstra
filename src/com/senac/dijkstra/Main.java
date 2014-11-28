package com.senac.dijkstra;

import static java.lang.System.out;

import java.util.Scanner;

import com.senac.dijkstra.auxiliar.ArquivoGrafo;
import com.senac.dijkstra.grafo.Dijkstra;
import com.senac.dijkstra.grafo.Grafo;
import com.senac.dijkstra.grafo.Vertice;

public class Main {
	public static void main(String[] args) {
	    Scanner teclado = new Scanner(System.in);

	    // cria o grafo, vazio
	    Grafo<Double> g = new Grafo<Double>();

	    // efetua a leitura do arquivo de dados
	    // e insere no grafo passado como par�metro
	    new ArquivoGrafo<Double>("dadosTeste", " ", g).lerArquivoGrafo();//max indice 13628
//	    out.print(g.toString()); System.exit(0);

	    // usu�rio deve entrar com o �ndice
	    // do ponto inicial e final
	    out.printf("�ndice m�ximo: %d\n", g.getVertices().size() - 1);
        out.print("�ndice do Vertice de origem: ");
        int inicial = teclado.nextInt();
        out.print("�ndice do Vertice de destino: ");
        int finau = teclado.nextInt();

        teclado.close();

        // Algoritmo de Dijkstra, calcula o melhor caminho
        // entre os vertices inicial e final
        Dijkstra<Double> d = new Dijkstra<Double>(g);
        d.calcularMelhorCaminho(g.getVertice(inicial), g.getVertice(finau));

        if (d.getMelhorCaminho() == null) {
            out.println("Rota inexistante!");
        } else {
            StringBuilder strBldr = new StringBuilder();
            for (Vertice<Double> v : d.getMelhorCaminho()) {
                strBldr.append(v.getSequencial());
                strBldr.append(" -> ");
            }
            strBldr.delete(strBldr.lastIndexOf(" -> "), strBldr.length());

            out.println("Melhor caminho:");
            out.println(strBldr.toString());

            out.print("Custo Total: ");
            out.println(d.getCustoTotal());
        }
	}
}
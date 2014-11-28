package com.senac.dijkstra.grafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra<T> {
    private Grafo<T> grafo;
    private double custoTotal;
    private Queue<Vertice<T>> melhorCaminho;
    private ArrayList<Double> custo;
    private ArrayList<Double> caminhoC;

    public Dijkstra(Grafo<T> g) {
        this.grafo = g;
        this.custoTotal = 0;

        // inicia valores infinitos
        // e o vertice utilizado para o caminho
        custo = new ArrayList<Double>();
        caminhoC = new ArrayList<Double>();
        for (int i = 0; i < grafo.getVertices().size(); i++) {
            custo.add(Double.POSITIVE_INFINITY);
            caminhoC.add(-1.0);
        }
    }

    public Queue<Vertice<T>> calcularMelhorCaminho(Vertice<T> inicial, Vertice<T> finau) {
        // se os vertices forem inválidos
        if (inicial == null || finau == null) {
            return null;
        }

        // TODO rever o alg de Dijktra
//        // seta valor 0 para a origem
//        custo.set(grafo.getVertices().indexOf(inicial), 0.0);
//
//        // vértices ainda não contém a distância
//        ArrayList<Vertice<T>> naoVisitados = grafo.getVertices();
//        naoVisitados.remove(inicial);
//
//        while (! naoVisitados.isEmpty()) {
//            Vertice<T> u = getMenorCusto(naoVisitados);
//
//            // para cada v adjacente a u
//            for (Vertice<T> v : u.getAdjacentes()) {
//                // peso = peso(de u, até v);
//                Double peso = u.getPesoAte(v);
//
//                // se d[v] > d[u] + w(u, v) 
//                if (custo.get(v.getSequencial()) < custo.get(u.getSequencial()) + peso) {
//                    // custo[v] = custo [u] + peso(de u, até v)
//                    custo.set(v.getSequencial(),
//                            custo.get(u.getSequencial()) + peso
//                            );
//
//                    // caminho[v] = u
//                    caminhoC.set(v.getSequencial(),
//                            (double) u.getSequencial());
//                    melhorCaminho.offer(u);
//
//                    // Q = Q U {v} // remove V dos visitados
//                    naoVisitados.remove(v);
//                    System.out.println("Faltam " + naoVisitados.size() + " vertices para visitar.");
//                }
//            }
//
//            naoVisitados.remove(u);
//            System.out.println("Faltam " + naoVisitados.size() + " vertices para visitar.");
//        }

        

        //trecho de código somente de teste
        // remover após implementação correta
        /* inicio */
        Queue<Vertice<T>> caminho = new LinkedList<Vertice<T>>();
        caminho.offer(inicial);
        caminho.offer(finau);
        this.custoTotal = inicial.getAresta(0).getPeso();
        this.custoTotal += finau.getAresta(0).getPeso();
        this.setMelhorCaminho(caminho);
        /* fim */

//        this.setMelhorCaminho(caminhoC);
        return caminho;
    }

    private Vertice<T> getMenorCusto(ArrayList<Vertice<T>> vertices) {
        Vertice<T> menorCusto = null;

        for (Vertice<T> v : vertices) {
            if (menorCusto == null 
                || custo.get(grafo.getVertices().indexOf(v))
                    < custo.get(grafo.getVertices().indexOf(menorCusto))
            ) {
                menorCusto = v;
            }
        }

        return menorCusto;
    }

    public double getCustoTotal() {
        return this.custoTotal;
    }

    public Queue<Vertice<T>> getMelhorCaminho() {
        return this.melhorCaminho;
    }
    
    private void setMelhorCaminho(Queue<Vertice<T>> caminho) {
        if (caminho != null) {
            this.melhorCaminho = new LinkedList<Vertice<T>>();
            this.melhorCaminho = caminho;
            
        }
    }
}
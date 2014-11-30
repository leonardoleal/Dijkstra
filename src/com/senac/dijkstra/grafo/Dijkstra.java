package com.senac.dijkstra.grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


public class Dijkstra<T> {
	private Set<Vertice<T>> naoVisitados;
	private Map<Vertice<T>, Vertice<T>> anteriores; 
	private Map<Vertice<T>, Double> custos;
	private Stack<Vertice<T>> melhorCaminho;
    private double custoTotal;

	public Dijkstra() {}

    /**
     * Inicializa o estado de todos objetos
     */
    private void init() {
        this.naoVisitados = new HashSet<Vertice<T>>();
        this.anteriores = new HashMap<Vertice<T>, Vertice<T>>();
        this.custos = new HashMap<Vertice<T>, Double>();
        this.melhorCaminho = new Stack<Vertice<T>>();
        this.custoTotal = 0;
    }

	public Stack<Vertice<T>> calcularMenorCaminho(Vertice<T> inicial, Vertice<T> finau) {
	    // caso usado mais de uma vez, limpa tudo
	    this.init();

	    // ponto inexistente
	    if (inicial == null || finau == null) {
	        return null;

	    } else if (inicial.equals(finau)) {
        // ponto inicial e final são os mesmos
	        melhorCaminho.push(inicial);

	    } else {
    	    naoVisitados.add(inicial);
    		custos.put(inicial, 0.0);
    
    		while (naoVisitados.size() > 0) {
    			Vertice<T> vertice = getMinimo(this.naoVisitados);
    			this.naoVisitados.remove(vertice);
    			procurarMenorCusto(vertice);
    		}
    
    		if (anteriores.get(finau) == null) {
    			return null;
    		}
    
    		Vertice<T> passo = finau;
    		this.melhorCaminho.push(passo);
    
    		while (anteriores.get(passo) != null) {
    		    this.custoTotal += passo.getPesoAte(this.anteriores.get(passo));
    			passo = this.anteriores.get(passo);
    			this.melhorCaminho.push(passo);
    		}
        }

		return this.melhorCaminho;
	}

	private Vertice<T> getMinimo(Set<Vertice<T>> vertices) {
		Vertice<T> minimo = null;
		for (Vertice<T> vertice : vertices) {
			if (minimo == null || (getMenorPeso(vertice) < getMenorPeso(minimo)))
				minimo = vertice;
		}

		return minimo;
	}

	private double getMenorPeso(Vertice<T> destino) {
		Double distancia = this.custos.get(destino);
		return (distancia == null ? Double.POSITIVE_INFINITY : distancia);
	}

	private void procurarMenorCusto(Vertice<T> vertice) {
		List<Vertice<T>> verticesAdjacentes = vertice.getAdjacentes();
		for (Vertice<T> destino : verticesAdjacentes) {
		    double peso = vertice.getPesoAte(destino);

			if (getMenorPeso(destino) > (getMenorPeso(vertice) + peso)) {
			    this.custos.put(destino, getMenorPeso(vertice) + peso);
				this.anteriores.put(destino, vertice);
				this.naoVisitados.add(destino);
			}
		}
	}

    public double getCustoTotal() {
        return this.custoTotal;
    }

    public Stack<Vertice<T>> getMelhorCaminho() {
        return this.melhorCaminho;
    }
}
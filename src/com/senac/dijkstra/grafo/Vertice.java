package com.senac.dijkstra.grafo;

import java.util.ArrayList;

public class Vertice<T> {
    public static int SEQUENCIAL = 0;

    private ArrayList<Aresta> arestas;
    private int sequencial;
    private T valor;

    public Vertice(T valor) {
        this.sequencial = Vertice.SEQUENCIAL++;
        this.valor = valor;
        arestas = new ArrayList<Aresta>();
    }

    public T getValor() {
        return this.valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public ArrayList<Aresta> getArestas() {
        return this.arestas;
    }

    public int getSequencial() {
        return sequencial;
    }

    public Aresta getAresta(int index) {
        return this.arestas.get(index);
    }    

    public void addAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }

    public int getGrau() {
        return this.arestas.size();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Vertice<T>> getAdjacentes() {
        ArrayList<Vertice<T>> adjs = new ArrayList<Vertice<T>>();

        for (Aresta aresta : this.getArestas()) {
            if (! this.equals(aresta.getVerticeInicio())) {
                adjs.add(aresta.getVerticeInicio());
            } else {
                adjs.add(aresta.getVerticeFim());
            }
        }

        return adjs;
    }

    public Double getPesoAte(Vertice<T> destino) {
        Double peso = Double.POSITIVE_INFINITY;

        for (Aresta aresta : this.getArestas()) {
            if (aresta.getVerticeInicio().equals(destino)
                    || aresta.getVerticeFim().equals(destino)
            ) {
                peso = aresta.getPeso();
            }
        }

        return peso;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null
            && obj instanceof Vertice
        ) {
            return this.getSequencial() == ((Vertice<?>) obj).getSequencial();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
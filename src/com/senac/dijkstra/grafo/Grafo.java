package com.senac.dijkstra.grafo;

import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new ArrayList<Aresta>();
    }

    public ArrayList<Vertice<T>> getVertices() {
        return this.vertices;
    }

    public Vertice<T> getVertice(int posicao) {
        // verifica se é um índice válido
        if (this.vertices.size() -1 < posicao ) {
            return null;
        }

        return this.vertices.get(posicao);
    }

    public void addVertice(T valor) {
        this.vertices.add(new Vertice<T>(valor));
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void addAresta(int posicaoVerticeInicio, int posicaoVerticeFim, double custo) {
        Vertice<T> inicio   = getVertice(posicaoVerticeInicio);
        Vertice<T> fim      = getVertice(posicaoVerticeFim);

        Aresta aresta = new Aresta(inicio, fim, custo);

        inicio.addAresta(aresta);
        fim.addAresta(aresta);

        this.arestas.add(aresta);
    }

    public void addAresta(int posicaoVerticeInicio, int posicaoVerticeFim) {
        this.addAresta(posicaoVerticeInicio, posicaoVerticeFim, 0);
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();

        for (Vertice<T> v : this.getVertices()) {
            strBldr.append("Vertice: ");
            strBldr.append(v.getSequencial());

            strBldr.append("\n\tArestas: ");
            for (Aresta a : v.getArestas()) {
                strBldr.append("{");
                strBldr.append("Inicio: ");
                strBldr.append(a.getVerticeInicio().getSequencial());
                strBldr.append(" | Fim: ");
                strBldr.append(a.getVerticeFim().getSequencial());
                strBldr.append(" | Custo: ");
                strBldr.append(a.getPeso());
                strBldr.append("} ");
            }

            strBldr.append("\n");
        }

        return strBldr.toString();
    }
}
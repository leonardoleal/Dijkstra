package com.senac.dijkstra.grafo;

import java.awt.geom.Point2D;

@SuppressWarnings("rawtypes")
public class Aresta {
    private Vertice verticeInicio, verticeFim;
    private double peso;

    public Aresta(Vertice verticeInicio, Vertice verticeFim) {
        this.setVerticeInicio(verticeInicio);
        this.setVerticeFim(verticeFim);
    }

    public Aresta(Vertice verticeInicio, Vertice verticeFim, double peso) {
        this.setVerticeInicio(verticeInicio);
        this.setVerticeFim(verticeFim);
        this.setPeso(peso);
    }

    public Vertice getVerticeInicio() {
        return this.verticeInicio;
    }

    public void setVerticeInicio(Vertice verticeInicio) {
        this.verticeInicio = verticeInicio;
        
        if (isVerticesPointType()) {
            calcularPesoEntrePontos();
        }
    }

    public Vertice getVerticeFim() {
        return this.verticeFim;
    }

    public void setVerticeFim(Vertice verticeFim) {
        this.verticeFim = verticeFim;
        
        if (isVerticesPointType()) {
            calcularPesoEntrePontos();
        }
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        if (!isVerticesPointType()) {
            this.peso = peso;
        } else {
            calcularPesoEntrePontos();
        }
    }

    /**
     * @see Cálculo:
     * <a href="http://www.brasilescola.com/matematica/distancia-entre-dois-pontos.htm">geometria analítica</a>
     */
    private double calcularPesoEntrePontos() {
        double inicioX, inicioY, fimX, fimY, distancia;
        Point2D.Double pontoInicial, pontoFinal;

        pontoInicial  = (Point2D.Double) this.verticeInicio.getValor();
        pontoFinal    = (Point2D.Double) this.verticeFim.getValor();

        inicioX = pontoInicial.getX();
        inicioY = pontoInicial.getY();
        fimX    = pontoFinal.getX();
        fimY    = pontoFinal.getY();

        distancia = Math.sqrt(Math.pow((inicioX - fimX), 2) + Math.pow((inicioY - fimY), 2));

        this.peso = distancia;
        return distancia;
    }

    private boolean isVerticesPointType() {
        return this.verticeInicio != null
                && this.verticeFim != null
                && this.verticeInicio.getValor() instanceof Point2D.Double
                && this.verticeFim.getValor() instanceof Point2D.Double;
    }
}
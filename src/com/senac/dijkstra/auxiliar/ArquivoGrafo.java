package com.senac.dijkstra.auxiliar;

import java.awt.geom.Point2D;
import java.io.IOException;

import com.senac.dijkstra.grafo.Grafo;

public class ArquivoGrafo<T> {
    private Grafo<T> grafo;
    private String caminhoArquivo;
    private String separador;

    public ArquivoGrafo(String caminhoArquivo, String separador) {
        this.setCaminhoArquivo(caminhoArquivo);
        this.setSeparador(separador);
        this.setGrafo(new Grafo<T>());
    }

    public ArquivoGrafo(String caminhoArquivo, String separador, Grafo<T> grafo) {
        this.setCaminhoArquivo(caminhoArquivo);
        this.setSeparador(separador);
        this.setGrafo(grafo);
    }

    private void setSeparador(String separador) {
        this.separador = "["+ separador +"]";
    }

    public Grafo<T> getGrafo() {
        return this.grafo;
    }

    public void setGrafo(Grafo<T> grafo) {
        this.grafo = grafo;
    }

    public String getCaminhoArquivo() {
        return this.caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Grafo<T> lerArquivoGrafo() {
        if (caminhoArquivo.isEmpty()) {
            return null;
        }

        TextFile arquivo = new TextFile(caminhoArquivo);

        try {
            String tipo;
            arquivo.openTextFile();

            while (arquivo.next()) {
                tipo = arquivo.readLine();
                if (tipo.equalsIgnoreCase("vertices")) {
                    this.lerVertices(arquivo);
                }


                tipo = arquivo.getLastReadLine();
                if (tipo.equalsIgnoreCase("arestas")) {
                    this.lerArestas(arquivo);
                }
            }

            arquivo.closeTextFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.grafo;
    }

    @SuppressWarnings("unchecked")
    private void lerVertices(TextFile arquivo) throws IOException {
        String dados[];

        while (arquivo.next()) {
            dados = arquivo.readLine().split(this.separador);

            if (dados.length < 2) {
                break;
            }

            this.grafo.addVertice(
                    (T) new Point2D.Double(
                            Double.parseDouble(dados[0]),
                            Double.parseDouble(dados[1])
                    )
            );
        }
    }

    private void lerArestas(TextFile arquivo) throws IOException {
        String dados[];

        while (arquivo.next()) {
            dados = arquivo.readLine().split(this.separador);

            if (dados.length < 2) {
                break;
            }

            this.grafo.addAresta(
                    Integer.parseInt(dados[0]),
                    Integer.parseInt(dados[1])
            );
        }
    }
}

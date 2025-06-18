package Tipos;

import java.util.HashMap;
import java.util.Map;


public class ElementoComplexo {
    private final Character id;
    private int heuristica;
    private Map<ElementoComplexo, Integer> vizinhos = new HashMap<>();
    private ElementoComplexo pai;
    private boolean visitado = false;

    public ElementoComplexo(Character id, int heuristica) {
        this.id = id;
        this.heuristica = heuristica;
    }

    public void adicionarVizinho(ElementoComplexo vizinho, int custo) {
        vizinhos.put(vizinho, custo);
    }

    public Character getId() {
        return id;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public Map<ElementoComplexo, Integer> getVizinhos() {
        return vizinhos;
    }

    public ElementoComplexo getPai() {
        return pai;
    }

    public void setPai(ElementoComplexo pai) {
        this.pai = pai;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" [h=").append(heuristica).append("] -> ");
        sb.append("{ ");
        vizinhos.forEach((viz, custo) -> sb.append(viz.id).append("(c=").append(custo).append(") "));
        sb.append("}");
        return sb.toString();
    }
}

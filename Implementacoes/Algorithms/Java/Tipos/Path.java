package Tipos;

import java.util.ArrayDeque;
import java.util.Deque;

public class Path {
    private int custoTotal = 0;
    private ElementoComplexo origem = null;
    private ElementoComplexo destino = null;
    private Deque<ElementoComplexo> caminho = new ArrayDeque<>();

    public Path() {
    }

    public int getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(int custoTotal) {
        this.custoTotal = custoTotal;
    }

    public ElementoComplexo getOrigem() {
        return origem;
    }

    public void setOrigem(ElementoComplexo origem) {
        this.origem = origem;
    }

    public ElementoComplexo getDestino() {
        return destino;
    }

    public void setDestino(ElementoComplexo destino) {
        this.destino = destino;
    }

    public ElementoComplexo[] getCaminho() {
        return caminho.toArray(new ElementoComplexo[0]);
    }

    public void setCaminho(Deque<ElementoComplexo> caminho) {
        this.caminho = caminho;
    }

}

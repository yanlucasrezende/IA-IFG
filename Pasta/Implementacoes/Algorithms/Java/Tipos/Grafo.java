package Tipos;

public class Grafo {
    private ElementoSimples raiz;
    private int qtdeElementos;

    public Grafo() {
        this.raiz = null;
        this.qtdeElementos = 0;
    }

    public ElementoSimples getRaiz() {
        return raiz;
    }

    public void setRaiz(ElementoSimples raiz) {
        this.raiz = raiz;
    }

    public int getQtdeElementos() {
        return qtdeElementos;
    }

    public void setQtdeElementos(int qtdeElementos) {
        this.qtdeElementos = qtdeElementos;
    }
}

public class Grafo {
    private Elemento raiz;
    private int qtdeElementos;

    public Grafo() {
        this.raiz = null;
        this.qtdeElementos = 0;
    }

    public Elemento getRaiz() {
        return raiz;
    }

    public void setRaiz(Elemento raiz) {
        this.raiz = raiz;
    }

    public int getQtdeElementos() {
        return qtdeElementos;
    }

    public void setQtdeElementos(int qtdeElementos) {
        this.qtdeElementos = qtdeElementos;
    }
}

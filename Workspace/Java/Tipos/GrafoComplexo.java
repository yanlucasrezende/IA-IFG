package Workspace.Java.Tipos;

public class GrafoComplexo {
    private ElementoComplexo raiz;
    private int qtdeElementos;

    public GrafoComplexo() {
        this.raiz = null;
        this.qtdeElementos = 0;
    }

    public ElementoComplexo getRaiz() {
        return raiz;
    }

    public void setRaiz(ElementoComplexo raiz) {
        this.raiz = raiz;
    }

    public int getQtdeElementos() {
        return qtdeElementos;
    }

    public void setQtdeElementos(int qtdeElementos) {
        this.qtdeElementos = qtdeElementos;
    }
}
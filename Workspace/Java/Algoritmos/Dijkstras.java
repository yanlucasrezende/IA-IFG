package Workspace.Java.Algoritmos;

import java.util.ArrayDeque;

import Workspace.Java.Tipos.*;

public class Dijkstras {
    private static int QTDE_ELEMENTOS_MAX;

    public static void main(String[] args) {
        GrafoComplexo grafo = Util.inicializaGrafoComplexo();

        QTDE_ELEMENTOS_MAX = grafo.getQtdeElementos();
        ElementoComplexo raiz = grafo.getRaiz();

        raiz = dijkstra(raiz);

        System.out.println("\nResultado do algoritmo de Dijkstra: ");

    }

    // Studying Dijkstra's algorithm i saw implementations that just create an
    // array of cost but i want do different and save the shortest to all the nodes
    public static ElementoComplexo dijkstra(ElementoComplexo raiz) {

        return raiz;
    }

    private static Path inicializarPathVazio(ElementoComplexo origem, ElementoComplexo destino) {
        Path path = new Path();
        path.setCustoTotal(Integer.MAX_VALUE);
        path.setOrigem(origem);
        path.setDestino(destino);
        path.setCaminho(new ArrayDeque<ElementoComplexo>());
        return path;
    }
}

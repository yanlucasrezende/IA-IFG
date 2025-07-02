package Workspace.Java.Algoritmos;

import java.util.ArrayList;
import java.util.List;

import Workspace.Java.Tipos.*;

public class Util {
    public static Grafo inicializaGrafoSimples() {
        Grafo grafo = new Grafo();
        // Inicio
        ElementoSimples s = new ElementoSimples(null, 'S');
        // 2 nivel
        ElementoSimples a = new ElementoSimples(s, 'A');
        ElementoSimples b = new ElementoSimples(s, 'B');
        // 3 nivel
        ElementoSimples d = new ElementoSimples(a, 'D');
        ElementoSimples c = new ElementoSimples(a, 'C');

        ElementoSimples e = new ElementoSimples(b, 'E');
        ElementoSimples f = new ElementoSimples(b, 'F');
        // 4 nivel
        ElementoSimples g = new ElementoSimples(f, 'G');
        ElementoSimples h = new ElementoSimples(d, 'H');

        s.setDireita(a);
        s.setEsquerda(b);

        a.setDireita(c);
        a.setEsquerda(d);

        b.setDireita(e);
        b.setEsquerda(f);

        f.setEsquerda(g);

        d.setDireita(h);

        grafo.setRaiz(s);
        grafo.setQtdeElementos(9);

        return grafo;
    }

    public static GrafoComplexo inicializaGrafoComplexo() {
        GrafoComplexo grafo = new GrafoComplexo();

        ElementoComplexo s = new ElementoComplexo('S', 10);
        ElementoComplexo a = new ElementoComplexo('A', 8);
        ElementoComplexo b = new ElementoComplexo('B', 7);
        ElementoComplexo c = new ElementoComplexo('C', 6);
        ElementoComplexo d = new ElementoComplexo('D', 5);
        ElementoComplexo e = new ElementoComplexo('E', 4);
        ElementoComplexo f = new ElementoComplexo('F', 3);
        ElementoComplexo g = new ElementoComplexo('G', 0);

        s.adicionarVizinho(a, 2);
        s.adicionarVizinho(b, 5);
        s.adicionarVizinho(c, 4);

        a.adicionarVizinho(d, 7);
        a.adicionarVizinho(e, 3);

        b.adicionarVizinho(e, 6);
        b.adicionarVizinho(f, 3);

        c.adicionarVizinho(f, 4);

        d.adicionarVizinho(g, 3);
        e.adicionarVizinho(g, 2);
        f.adicionarVizinho(g, 4);

        grafo.setRaiz(s);
        grafo.setQtdeElementos(8);

        return grafo;
    }

    // Dijkstras does not need heuristic so all the elements/nodes of
    // this tree will be initialized as zero, just need the weight information
    // because its not about find a path to something but rather about
    // find the shortest path to all the nodes of tree from a source element/node
    public static GrafoComplexo inicializarGrafoDijkstras() {
        GrafoComplexo gf = new GrafoComplexo();

        ElementoComplexo raiz = new ElementoComplexo('S', 0);

        ElementoComplexo a = new ElementoComplexo('A', 0);
        ElementoComplexo b = new ElementoComplexo('B', 0);
        ElementoComplexo c = new ElementoComplexo('C', 0);
        ElementoComplexo d = new ElementoComplexo('D', 0);
        ElementoComplexo e = new ElementoComplexo('E', 0);
        ElementoComplexo f = new ElementoComplexo('F', 0);
        ElementoComplexo g = new ElementoComplexo('G', 0);

        raiz.adicionarVizinho(a, 5);
        raiz.adicionarVizinho(b, 4);
        raiz.adicionarVizinho(c, 3);

        a.adicionarVizinho(d, 2);
        a.adicionarVizinho(e, 1);

        b.adicionarVizinho(f, 2);
        b.adicionarVizinho(g, 1);

        c.adicionarVizinho(g, 3);

        e.adicionarVizinho(f, 1);

        gf.setQtdeElementos(8);
        gf.setRaiz(raiz);

        return gf;
    }

    public static String caminhoElementoParaRaiz(ElementoSimples elemento) {
        if (elemento == null) {
            return "Caminho não encontrado!";
        }
        List<ElementoSimples> caminho = new ArrayList<>();

        caminho.add(elemento);
        ElementoSimples pai = elemento.getPai();

        while (pai != null) {
            caminho.add(pai);
            pai = pai.getPai();
        }

        caminho = caminho.reversed();
        StringBuilder sb = new StringBuilder();

        for (ElementoSimples e : caminho) {
            sb.append(e.getId()).append(" -> ");
        }
        // Remover a última seta
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 4);
        }
        return sb.toString();
    }
}

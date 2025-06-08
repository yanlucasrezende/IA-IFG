import java.util.ArrayList;
import java.util.List;

import Tipos.ElementoSimples;
import Tipos.Grafo;

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

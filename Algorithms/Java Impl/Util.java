import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Grafo inicializaGrafoSimples() {
        Grafo grafo = new Grafo();
        // Inicio
        Elemento s = new Elemento(null, 'S');
        // 2 nivel
        Elemento a = new Elemento(s, 'A');
        Elemento b = new Elemento(s, 'B');
        // 3 nivel
        Elemento d = new Elemento(a, 'D');
        Elemento c = new Elemento(a, 'C');
        Elemento e = new Elemento(b, 'E');
        Elemento f = new Elemento(b, 'F');
        // 4 nivel
        Elemento g = new Elemento(f, 'G');
        Elemento h = new Elemento(d, 'G');

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

    public static String caminhoElementoParaRaiz(Elemento elemento) {
        if (elemento == null) {
            return "Caminho não encontrado!";
        }
        List<Elemento> caminho = new ArrayList<>();

        caminho.add(elemento);
        Elemento pai = elemento.getPai();

        while (pai != null) {
            caminho.add(pai);
            pai = pai.getPai();
        }

        caminho = caminho.reversed();
        StringBuilder sb = new StringBuilder();

        for (Elemento e : caminho) {
            sb.append(e.getId()).append(" -> ");
        }
        // Remover a última seta
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 4);
        }
        return sb.toString();
    }
}

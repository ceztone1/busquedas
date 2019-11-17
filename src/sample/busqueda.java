package sample;

import javafx.scene.control.Alert;

import java.util.*;

public class busqueda {

    String[][] arbol;


    void coste_uniforme(nodo raiz, String solucion, String mat[][]) {
        List<nodo> aux = new ArrayList<>();
        nodo nodo_incial = raiz;
        nodo nodo_actual;
        nodo nodo_hijo;
        ArrayList<nodo> nodos_frontera = new ArrayList<>();
        List<nodo> adyasencias = new ArrayList<>();
        ArrayList<String> nodos_visitados = new ArrayList<>();
        nodos_frontera.add(nodo_incial);
        int costo = 0;
        while (!nodos_frontera.isEmpty()) {
            ordenar(nodos_frontera);
            nodo_actual = nodos_frontera.get(0);
            costo = nodo_actual.getCoste();
            nodos_frontera.remove(0);
            if (nodo_actual.info.equals(solucion)) {
                System.out.println("Encontrado");
                System.out.println(nodo_actual.getCoste());
                camino(nodo_actual);
                return;
            }
            nodos_visitados.add(nodo_actual.getInfo());
            adyasencias = adyacensiasCoste(mat, nodo_actual, nodos_visitados);
            for (int i = 0; i < adyasencias.size(); i++) {
                nodo_hijo = adyasencias.get(i);
                nodo_hijo.setCoste(nodo_hijo.getCoste() + costo);
                if (!nodos_visitados.contains(nodo_hijo))
                    if (nodos_frontera.contains(nodo_hijo))
                        menorCoste(nodos_frontera, nodo_hijo);
                    else
                        nodos_frontera.add(nodo_hijo);
            }

        }
        System.out.println("No encontrado");
    }

    void camino(nodo nod) {

    }

    void menorCoste(ArrayList<nodo> frontera, nodo nodo) {
        boolean b = true;
        for (int i = 0; i < frontera.size() && b == true; i++) {
            if (frontera.get(i).getCoste() < nodo.getCoste()) {
                frontera.get(i).setInfo(nodo.info);
                frontera.get(i).setCoste(nodo.coste);
                b = false;
            }

            ordenar(frontera);

        }

    }

    void ordenar(ArrayList<nodo> list) {
        Collections.sort(list, new Comparator<nodo>() {
            @Override
            public int compare(nodo p1, nodo p2) {
                return new Integer(p1.getCoste()).compareTo(new Integer(p2.getCoste()));
            }
        });
    }


    void profundidad(int raiz, String mat[][]) {
        ArrayList<Integer> visitados = new ArrayList<>();
        arbol = new String[mat.length][mat.length];
        iniciarArbol();
        ArrayList<Integer> adyacensias = new ArrayList<>();
        int w = raiz;
        insercion(mat, visitados, w, raiz);


    }

    void insercion(String mat[][], ArrayList<Integer> visitados, int w, int raiz) {
        int vk = 0;
        agregarVisitados(visitados, w);
        vk = hijoMenor(mat, w, visitados);

        if (vk == -1) {
            finalizacion(mat, visitados, w, raiz);

        } else {
            agregarMatProf(w, vk);
            w = vk;
            insercion(mat, visitados, w, raiz);
        }


    }

    void finalizacion(String mat[][], ArrayList<Integer> visitados, int w, int raiz) {

        if (w == raiz) {
            mostrarMatrizProfFinal(mat);
            return;

        } else {
            regresar(mat, visitados, w, raiz);

        }

    }

    void regresar(String mat[][], ArrayList<Integer> visitados, int w, int raiz) {
        int x;
        x = padre(arbol, w);
        w = x;
        insercion(mat, visitados, w, raiz);

    }


    void anchura(int raiz, String mat[][]) {
        Queue<Integer> s = new LinkedList<>();
        arbol = new String[mat.length][mat.length];
        iniciarArbol();
        ArrayList<Integer> visitados = new ArrayList<>();
        ArrayList<Integer> adyacensias = new ArrayList<>();
        s.add(raiz);
        visitados.add(raiz);
        while (!s.isEmpty()) {
            raiz = s.poll();

            adyacensias = adyacensias(mat, raiz);


            for (int i = 0; i < adyacensias.size(); i++) {
                if (!visitados.contains(adyacensias.get(i)))
                    agregarMat(raiz, adyacensias.get(i));

            }

            sustituirHijos(s, adyacensias, visitados);
            agregarVisitados(visitados, adyacensias);
        }
        adyacensias(mat, raiz);


    }

    ArrayList<Integer> adyacensias(String mat[][], int nodo) {
        ArrayList<Integer> adyasencias = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            if (mat[nodo][i].equalsIgnoreCase("1"))
                adyasencias.add(i);
        }

        return adyasencias;

    }

    ArrayList<nodo> adyacensiasCoste(String mat[][], nodo nodo, ArrayList<String> visitados) {
        ArrayList<nodo> adyasencias = new ArrayList<>();
        int aux;

        for (int i = 0; i < mat.length; i++) {
            if (mat[i][0].equalsIgnoreCase(nodo.info)) {
                aux = i;
                for (int j = 1; j < mat.length; j++) {
                    if (!mat[aux][j].equalsIgnoreCase("0")) {
                        if (!visitados.contains(mat[0][j] + ""))
                            adyasencias.add(new nodo(Integer.parseInt(mat[aux][j]), mat[0][j] + ""));
                    }
                }
            }

        }


        return adyasencias;

    }


    int hijoMenor(String mat[][], int nodo, ArrayList<Integer> visitados) {
        ArrayList<Integer> adyasencias = new ArrayList<>();
        int hijo = -1;
        for (int i = 0; i < mat.length; i++) {
            if (mat[nodo][i].equalsIgnoreCase("1") && !visitados.contains(i)) {
                adyasencias.add(i);
            }

        }
        if (adyasencias.isEmpty()) {

            return hijo;
        } else {
            return adyasencias.get(0);
        }

    }

    int padre(String mat[][], int nodo) {
        boolean b = false;
        int padre = 0;
        for (int i = 0; i < mat.length && b == false; i++) {
            if (mat[i][nodo].equalsIgnoreCase("1")) {
                padre = i;
                b = true;
            }
        }


        return padre;

    }

    void agregarMat(int origen, int destino) {
        arbol[origen][destino] = 1 + "";
        arbol[destino][origen] = 1 + "";

    }

    void agregarMatProf(int origen, int destino) {
        arbol[origen][destino] = 1 + "";


    }

    void mostrarMatrizProfFinal(String[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (arbol[i][j].equals("1")) {
                    arbol[j][i] = mat[i][j];
                    arbol[j][i] = mat[i][j];

                }
            }
        }
    }

    void sustituirHijos(Queue cola, ArrayList<Integer> hijos, ArrayList<Integer> visitados) {
        for (int i = 0; i < hijos.size(); i++) {
            if (!visitados.contains(hijos.get(i)))
                cola.add(hijos.get(i));
        }

    }

    void agregarVisitados(ArrayList<Integer> visitados, ArrayList<Integer> adyasencias) {

        for (int i = 0; i < adyasencias.size(); i++) {
            if (!visitados.contains(adyasencias.get(i)))
                visitados.add(adyasencias.get(i));
        }
    }

    void agregarVisitados(ArrayList<Integer> visitados, int hijo) {


        if (!visitados.contains(hijo))
            visitados.add(hijo);

    }

    void iniciarArbol() {
        for (int i = 0; i < arbol.length; i++) {
            for (int j = 0; j < arbol.length; j++) {
                arbol[i][j] = "0";
            }
        }
    }

    void imprimirArbol() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Matriz adyacencia");
        String alert1 = "";
        for (int i = 0; i < arbol.length; i++) {
            for (int j = 0; j < arbol.length; j++) {
                System.out.print(arbol[i][j] + " ");
                alert1 += arbol[i][j] + " ";
            }
            System.out.println();
            alert1 += "\n";
        }
        alert.setContentText(alert1);

        alert.show();

    }

    @Override
    public String toString() {
        return "busqueda{" +
                "arbol=" + Arrays.toString(arbol) +
                '}';
    }
}


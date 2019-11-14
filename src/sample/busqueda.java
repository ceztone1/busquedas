package sample;

import javafx.scene.control.Alert;

import java.util.*;

public class busqueda {

    String[][] arbol;

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
        if (!visitados.contains(w)) {
            vk = hijoMenor(mat,w,visitados);
        }
        if (vk == 0) {
            agregarVisitados(visitados, w);
            finalizacion(mat,visitados,w, raiz);

        } else {
            agregarMat(w, vk);
            w = vk;
            insercion(mat, visitados, w, raiz);
        }


    }

    void finalizacion(String mat[][], ArrayList<Integer> visitados,int w, int raiz) {

        if (w == raiz) {
            return;
        } else {
            regresar(mat,visitados,w, raiz);

        }

    }

    void regresar(String mat[][], ArrayList<Integer> visitados,int w, int raiz) {
        int x;
        x = padre(mat, w);
        w = x;
        insercion(mat,visitados,w, raiz);

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

    int hijoMenor(String mat[][], int nodo, ArrayList<Integer> visitados) {
        ArrayList<Integer> adyasencias = new ArrayList<>();
        int hijo=0;
        for (int i = 0; i < mat.length; i++) {
            if (mat[nodo][i].equalsIgnoreCase("1") && !visitados.contains(i)) {
                if(nodo<i)
                adyasencias.add(i);
            }

        }
        if(adyasencias.isEmpty()){

            return hijo;
        }else{
            return adyasencias.get(0);
        }

    }

    int padre(String mat[][], int nodo) {
        boolean b=false;
        int padre = 0;
        for (int i = 0; i < mat.length && b==false; i++) {
            if (mat[nodo][i].equalsIgnoreCase("1")) {
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


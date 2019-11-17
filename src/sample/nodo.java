package sample;

public class nodo {
    int coste;
    String info;

    public nodo() {
    }

    public nodo(int coste, String info) {
        this.coste = coste;
        this.info = info;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

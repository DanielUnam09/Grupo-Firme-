public interface Subject {
    void agregarObserver(Observer observer);
    void removerObserver(Observer observer);
    void notificarObservers(String mensaje);
}

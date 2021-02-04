package produtorconsumidordemo;

public class Consumidor implements Runnable {

    private static final int DELAY = 3;
    private int count;
    private PilhaVetor pilha;

    public Consumidor(PilhaVetor pilha,int contador) {
        this.pilha = pilha;
        this.count = contador;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= count; i++) {
                pilha.pop();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException ex) {

        } catch (Exception ex) {
        }
    }

}

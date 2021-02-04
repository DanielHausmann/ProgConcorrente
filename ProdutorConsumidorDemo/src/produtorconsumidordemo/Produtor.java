package produtorconsumidordemo;

public class Produtor implements Runnable{

    private static final int DELAY = 2;
    private int count;
    private PilhaVetor pilha;
    public int valorInicio;
    
    public Produtor(PilhaVetor pilha,int contador, int valorInicio){
        this.pilha = pilha;
        this.count = contador;
        this.valorInicio = valorInicio;
    }
    
    @Override
    public void run() {
        try{
            for (int i = 1; i <= count; i++) {
                pilha.push(valorInicio);
                this.valorInicio++;
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException ex) {
          
        } catch (Exception ex) {
        }
    }
    
}

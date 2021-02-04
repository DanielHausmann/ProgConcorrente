package produtorconsumidordemo;

public class ProdutorConsumidorDemo {

    public static void main(String[] args) throws Exception {
       
        PilhaVetor pilha = new PilhaVetor(5);
        final int repeticao = 10;
        final int threads = 1;
        
        
        for (int i = 1; i <= threads; i++) {
            Produtor p = new Produtor(pilha, repeticao,1);
            Consumidor c = new Consumidor(pilha, repeticao);
            Thread prod = new Thread(p);
            Thread cons = new Thread(c);
            prod.start();
            cons.start();
       
        }
        
        
       
    }
   
}

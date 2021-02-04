package produtorconsumidordemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PilhaVetor implements Pilha {

    // capacidade maxima da pilha
    private int tam;
    // quantodade atual de elementos
    private int n;
    // array que contém os elementos da pilha
    private int vet[];

    private Lock chaveDeAlteracaoPilha;
    
    // Condição que coloca a thread em espera, até que tenha algum produto na pilha
    private Condition condicaoPilhaVazia;
    // Condição que coloca a thread em espera, até que algum produto seja consumido
    private Condition condicaoPilhaCheia;
    int temp;

    public PilhaVetor(int tamanho) {
        this.n = 0;
        this.tam = tamanho;
        this.vet = new int[tamanho];
        chaveDeAlteracaoPilha = new ReentrantLock();
        condicaoPilhaVazia = chaveDeAlteracaoPilha.newCondition();
        condicaoPilhaCheia = chaveDeAlteracaoPilha.newCondition();
    }

    @Override
    public void push(int v) throws Exception {
        chaveDeAlteracaoPilha.lock();
        try {
            while (n == tam) {
                System.out.println("Tentou produzir mas a pilha estava cheia! Espere um produto ser Consumido");
                condicaoPilhaCheia.await();
            }

            this.vet[n] = v;
            this.n++;
            System.out.println("Valor " + v + " inserido/produzido. Pilha com: " + n + " produto(s)");
            condicaoPilhaVazia.signalAll();
        } finally {
            chaveDeAlteracaoPilha.unlock();
        }

    }

    @Override
    public int pop() throws Exception {

        chaveDeAlteracaoPilha.lock();
        try {
            while (this.n == 0) {
                System.out.println("Tentou consumir mas a pilha estava vazia! Espere até ter um produto ");
                condicaoPilhaVazia.await();

            }
            temp = this.vet[this.n - 1];
            this.n--;
            System.out.println("Valor Consumido: pilha com " + n + " produto(s)");
            condicaoPilhaCheia.signalAll();
        } finally {
            chaveDeAlteracaoPilha.unlock();
        }

        return temp;
    }

    @Override
    public boolean vazia() {
        return (this.n == 0);
    }

    @Override
    public void reset() {
        this.n = 0;
    }

    @Override
    public String toString() {
        String s = "";

        if (vazia()) {
            System.out.println("Pilha Vazia");
            return "Pilha vazia.";
        } else {
            s = s + "Pilha: ";
            for (int i = 0; i < this.n; i++) {
                s = s + this.vet[i] + " ";
            }
            System.out.println(s);
        }
        return s;
    }

}

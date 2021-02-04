package produtorconsumidordemo;

public interface Pilha {
	// insere novo elemento
	void push(int v) throws Exception;
	// retira e retorna elemento do topo da pilha
	int pop () throws Exception;
	// verifica se está vzia
	boolean vazia();
	// remove todo mundo e zera a pilha
	void reset();
}
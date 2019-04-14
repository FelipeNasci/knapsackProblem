
public class ProblemaDaMochila {

    public static void main(String args[]) {
        int mochila = 7;
        int peso[] =  {0, 2, 1,  6,  5};
        int valor[] = {0, 10, 7, 25, 24};
        int escolha[];

        int G[][] = inteira(peso, valor, mochila);
        escolha = obterProdutos(G, peso);           //Obtem os produtos que serao levados
        
        exibeTabela(G);
        imprima(peso, valor, escolha);

    }

    public static int[][] inteira(int peso[], int valor[], int mochila) {

        int G[][] = new int[peso.length][mochila + 1];

        //Insere zeros em todas as colunas da primeira linha
        for (int i = 0; i < G[0].length; i++) { G[0][i] = 0; }

        for (int i = 1; i < G.length; i++) {
            G[i][0] = 0;                                        //Zero na linha da primeira coluna
            for (int j = 1; j < G[0].length; j++) {
                if (peso[i] > j) {                              //Se a mochila nao aguenta o produto
                    G[i][j] = G[i - 1][j];                      //Coloque um produto mais leve
                } else {
                    G[i][j] = max( G[i - 1][j - peso[i]] + valor[i], G[i - 1][j] );
                }
            }
        }
        return G;
    }

    public static int[] obterProdutos(int G[][], int peso[]) {

        int escolha[] = new int[G.length];
        int j = G[0].length - 1;

        for (int i = G.length - 1; i > 0; i--) {

            if (G[i][j] != G[i - 1][j]) {
                escolha[i] = 1;
                j -= peso[i];
            } else 
                escolha[i] = 0;

        }
        return escolha;
    }

    public static int produtoInterno(int escolha[], int produto[]){
        
        int somatorio = 0;
        
        for (int i = 0; i < escolha.length; i++){
            somatorio += escolha[i] * produto[i];
        }
        return somatorio;
    }
    
//********************  AUXILIARES  ********************
    
    public static int max(int a, int b) {
        if (a >= b)
            return a;
        return b;
    }

    public static void exibeTabela(int G[][]) {
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[0].length; j++) {
                System.out.print(G[i][j] + "\t");
            }
            try {
                Thread.sleep(1000);
            } catch (Exception err) {
            }
            System.err.println("\n");

        }
    }

    public static void exibeVetor(int vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
    }
    
    public static void imprima(int peso[], int valor[], int escolha[]){
        System.out.println("Valor total: " + produtoInterno(escolha, valor));
        System.out.println("Peso total: " + produtoInterno(escolha, peso));
        
        System.err.println("Produtos a serem levados");
        for (int i = 0; i < escolha.length; i++){
            if(escolha[i] == 1)
                System.err.println("Produto " + i + " | Peso " + peso[i] + " | Valor " + valor[i]);
        }
    }

}

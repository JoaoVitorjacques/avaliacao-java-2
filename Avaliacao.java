import java.util.Scanner;

class Pessoa {
    String nome;
    int idade;
    double peso;
    double altura;
}

public class Avaliacao {

    static Scanner input = new Scanner(System.in);

    // --- Questão 1 ---
    public static int buscarPeloNome(Pessoa[] v, int qtd, String nomeBusca) {
        for (int i = 0; i < qtd; i++) {
            if (v[i].nome.equalsIgnoreCase(nomeBusca)) {
                return i;
            }
        }
        return -1;
    }

    public static int cadastrarPessoa(Pessoa[] v, int qtd) {
        if (qtd >= v.length) {
            System.out.println("Erro: O vetor está cheio.");
            return qtd;
        }
        Pessoa nova = new Pessoa();
        String nomeDigitado;
        do {
            System.out.print("Digite o nome: ");
            nomeDigitado = input.nextLine();
            if (buscarPeloNome(v, qtd, nomeDigitado) != -1) {
                System.out.println("Nome já existe. Digite outro.");
            }
        } while (buscarPeloNome(v, qtd, nomeDigitado) != -1);
        nova.nome = nomeDigitado;
        System.out.print("Digite a idade: ");
        nova.idade = input.nextInt();
        System.out.print("Digite o peso (kg): ");
        nova.peso = input.nextDouble();
        System.out.print("Digite a altura (m): ");
        nova.altura = input.nextDouble();
        input.nextLine();
        v[qtd] = nova;
        return qtd + 1;
    }

    // --- Questão 2 ---
    public static double calcularIMC(Pessoa p) {
        if (p.altura == 0) {
            return 0;
        }
        return p.peso / (p.altura * p.altura);
    }

    public static void imprimirPessoas(Pessoa[] v, int qtd) {
        System.out.println("\n--- Lista de Pessoas ---");
        for (int i = 0; i < qtd; i++) {
            Pessoa p = v[i];
            double imc = calcularIMC(p);
            System.out.println("Nome: " + p.nome);
            System.out.println("  Idade: " + p.idade);
            System.out.println("  Peso: " + p.peso + " kg");
            System.out.println("  Altura: " + p.altura + " m");
            System.out.printf("  IMC: %.2f\n", imc);
            System.out.println("------------------------");
        }
    }

    // --- Questão 3 ---
    public static int maisVelhaIMCMagreza(Pessoa[] v, int qtd) {
        int indiceMaisVelha = -1;
        int maiorIdade = -1;
        for (int i = 0; i < qtd; i++) {
            Pessoa p = v[i];
            double imc = calcularIMC(p);
            if (imc < 18.5) {
                if (p.idade > maiorIdade) {
                    maiorIdade = p.idade;
                    indiceMaisVelha = i;
                }
            }
        }
        return indiceMaisVelha;
    }

    // --- Questão 4 ---
    
    public static void insertionSortPorNome(Pessoa[] v, int qtd) {
        for (int i = 1; i < qtd; i++) {
            Pessoa pivo = v[i];
            int j = i - 1;
            while (j >= 0 && v[j].nome.compareToIgnoreCase(pivo.nome) > 0) {
                v[j + 1] = v[j];
                j--;
            }
            v[j + 1] = pivo;
        }
    }

    // --- Questão 5 ---
    public static void atualizarPesoPorNome(Pessoa[] v, int qtd, String nomeBusca, double novoPeso) {
        boolean encontrou = false;
        for (int i = 0; i < qtd; i++) {
            if (v[i].nome.equalsIgnoreCase(nomeBusca)) {
                v[i].peso = novoPeso;
                encontrou = true;
                System.out.println("Peso de " + v[i].nome + " atualizado.");
                break;
            }
        }
        if (!encontrou) {
            System.out.println("Pessoa '" + nomeBusca + "' não encontrada.");
        }
    }

    /**
     * Bloco Main (necessário para executar)
     * Ele chama as funções para você poder testar.
     */
    public static void main(String[] args) {
        Pessoa[] fichario = new Pessoa[50];
        int quantidade = 0;

        // 1. Teste da Q1 (cadastra 2 pessoas)
        quantidade = cadastrarPessoa(fichario, quantidade);
        quantidade = cadastrarPessoa(fichario, quantidade);

        // 2. Teste da Q2 (imprime as 2 pessoas)
        imprimirPessoas(fichario, quantidade);

        // 3. Teste da Q4 (ordena por nome)
        insertionSortPorNome(fichario, quantidade);
        
        System.out.println("\n--- Após Ordenar (Q4) ---");
        imprimirPessoas(fichario, quantidade); // Imprime de novo para ver ordenado

        // 4. Teste da Q3 (acha a mais velha com magreza)
        int idx = maisVelhaIMCMagreza(fichario, quantidade);
        if (idx != -1) {
            System.out.println("\n(Q3) Pessoa mais velha com magreza: " + fichario[idx].nome);
        } else {
            System.out.println("\n(Q3) Ninguém com magreza foi encontrado.");
        }

        // 5. Teste da Q5 (atualiza peso)
        System.out.println("\n--- Teste (Q5) ---");
        System.out.print("Qual nome quer atualizar o peso? ");
        String nome = input.nextLine();
        System.out.print("Qual o novo peso? ");
        double peso = input.nextDouble();
        input.nextLine();
        
        atualizarPesoPorNome(fichario, quantidade, nome, peso);
        
        // Imprime lista final
        imprimirPessoas(fichario, quantidade);
        
        input.close();
    }
}
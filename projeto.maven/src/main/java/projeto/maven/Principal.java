package projeto.maven;

import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XDAO xDAO = new XDAO();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1) Listar");
            System.out.println("2) Inserir");
            System.out.println("3) Excluir");
            System.out.println("4) Atualizar");
            System.out.println("5) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    List<X> xList = xDAO.listarTodos();
                    for (X x : xList) {
                        System.out.println("ID: " + x.getId() + ", Nome: " + x.getNome() + ", Idade: " + x.getIdade());
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    X novoX = new X(0, nome, idade);
                    xDAO.inserir(novoX);
                    System.out.println("Registro inserido com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o ID do registro a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    xDAO.excluir(idExcluir);
                    System.out.println("Registro excluído com sucesso.");
                    break;
                case 4:
                    System.out.print("Digite o ID do registro a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Digite o novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite a nova idade: ");
                    int novaIdade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    X xAtualizar = new X(idAtualizar, novoNome, novaIdade);
                    xDAO.atualizar(xAtualizar);
                    System.out.println("Registro atualizado com sucesso.");
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    xDAO.close(); // Fechar a conexão com o banco de dados
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
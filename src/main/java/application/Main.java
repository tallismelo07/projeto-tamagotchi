package application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do seu Tamagotchi: ");
        String nome = scanner.nextLine();
        Tamagotchi tamagotchi = new Tamagotchi(nome);

        int opcao;
        do {
            tamagotchi.getRelogio().avancarHoras(1);

            if (EventosAleatorios.houveChuva()) {
                System.out.println("☔ Está chovendo! Seu Tamagotchi pode pegar um resfriado.");
            }

            System.out.println("\n========= Menu =========");
            System.out.println("1. Alimentar");
            System.out.println("2. Brincar");
            System.out.println("3. Dormir");
            System.out.println("4. Dar Remédio");
            System.out.println("7. Tomar Banho");
            System.out.println("8. Verificar Status");
            System.out.println("0. Sair");
            System.out.println("========================");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido!");
                scanner.next();
            }
            opcao = scanner.nextInt();

            if (tamagotchi.getIdade() >= 100) {
                System.out.println("Seu Tamagotchi morreu e não pode mais interagir!");
                break;
            }

            switch (opcao) {
                case 1:
                    tamagotchi.alimentar();
                    break;
                case 2:
                    tamagotchi.brincar();
                    break;
                case 3:
                    tamagotchi.dormir();
                    break;
                case 4:
                    tamagotchi.darMedicamento();
                    break;
                case 5:
                    tamagotchi.interagir();
                    break;
                case 6:
                    System.out.println("Opção ainda não implementada.");
                    break;
                case 7:
                    tamagotchi.tomarBanho();
                    break;
                case 8:
                    System.out.println("\nStatus Atual:");
                    System.out.println("Nome: " + tamagotchi.getNome());
                    System.out.println("Idade: " + tamagotchi.getIdade());
                    System.out.println("Fase: " + tamagotchi.getFase());
                    System.out.println("Fome: " + tamagotchi.getFome());
                    System.out.println("Felicidade: " + tamagotchi.getFelicidade());
                    System.out.println("Energia: " + tamagotchi.getEnergia());
                    System.out.println("Saúde: " + tamagotchi.getSaude());
                    System.out.println("Higiene: " + tamagotchi.getHigiene());
                    System.out.println("Socialização: " + tamagotchi.getSocializacao());
                    break;
                case 0:
                    System.out.println("Saindo... Obrigado por jogar!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
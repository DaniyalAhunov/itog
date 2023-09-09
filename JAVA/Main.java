package JAVA;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Создать игрушку");
            System.out.println("2. Обновить игрушку");
            System.out.println("3. Организовать розыгрыш");
            System.out.println("4. Выдать приз");
            System.out.println("5. Показать склад");
            System.out.println("6. Показать невыданные призы");
            System.out.println("7. Показать выданные призы");
            System.out.println("8. Выйти");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createToy(scanner, toyShop);
                    break;
                case 2:
                    updateToyProbability(scanner, toyShop);
                    break;
                case 3:
                    toyShop.organizeRaffle();
                    break;
                case 4:
                    toyShop.claimPrize();
                    break;
                case 5:
                    toyShop.displayStock();
                    break;
                case 6:
                    toyShop.displayUnclaimedPrizes();
                    break;
                case 7:
                    toyShop.displayClaimedPrizes();
                    break;
                case 8:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        }
    }

    private static void createToy(Scanner scanner, ToyShop toyShop) {
        System.out.print("Введите ID игрушки: ");
        int id = scanner.nextInt();
        System.out.print("Введите название игрушки: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Введите количество игрушек: ");
        int quantity = scanner.nextInt();
        System.out.print("Введите шанс выпадения игрушки (в процентах): ");
        double probability = scanner.nextDouble();

        Toy newToy = new Toy(id, name, quantity, probability);
        toyShop.addToy(newToy);
        System.out.println("Игрушка создана и добавлена в магазин.");
    }

    private static void updateToyProbability(Scanner scanner, ToyShop toyShop) {
        System.out.print("Введите ID игрушки, которую вы хотите обновить: ");
        int toyId = scanner.nextInt();
    
        System.out.print("Введите новую вероятность (в процентах) для игрушки: ");
        double newProbability = scanner.nextDouble();
    
        System.out.print("Введите новое количество для игрушки: ");
        int newQuantity = scanner.nextInt();
    
        // Вызываем метод ToyShop для обновления вероятности и количества игрушки
        toyShop.updateToyProbabilityAndQuantity(toyId, newProbability, newQuantity);
    }
    
}

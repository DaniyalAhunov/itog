package JAVA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyShop {
    private List<Toy> stock;
    private List<Toy> unclaimedPrizes;
    private List<Toy> claimedPrizes = new ArrayList<>();
    private static final String STOCK_FILE = "JAVA/stock.csv";
    private static final String UNCLAIMED_PRIZES_FILE = "JAVA/unclaimed_prizes.csv";
    private static final String PRIZE_FILE = "JAVA/prizes.csv";

    public ToyShop() {
        stock = new ArrayList<>();
        unclaimedPrizes = new ArrayList<>();
        loadStockFromFile();
        loadUnclaimedPrizesFromFile();
    }

    public void addToy(Toy toy) {
        stock.add(toy);
        saveStockToFile();
    }

    public void updateToyProbability(int toyId, double probability) {
        for (Toy toy : stock) {
            if (toy.getId() == toyId) {
                toy.setProbability(probability);
                saveStockToFile();
                break;
            }
        }
    }

    public void organizeRaffle() {
        Random random = new Random();
        List<Toy> raffledToys = new ArrayList<>();

        for (Toy toy : stock) {
            if (toy.hasStock() && random.nextDouble() < (toy.getProbability() / 100.0)) {
                toy.decreaseQuantity();
                raffledToys.add(toy);
            }
        }

        unclaimedPrizes.addAll(raffledToys);
        saveStockToFile();
        saveUnclaimedPrizesToFile();

        System.out.println("Розыгрыш организован.");
    }

    public void claimPrize() {
        if (!unclaimedPrizes.isEmpty()) {
            Toy prizeToy = unclaimedPrizes.remove(0);
            saveUnclaimedPrizesToFile(); // Сохраняем обновленные данные о невыданных призах
            claimedPrizes.add(prizeToy); // Добавляем приз в список выданных
            savePrizeToFile(prizeToy); // Сохраняем выданный приз в файл
            System.out.println("Приз выдан: " + prizeToy.getName());
        } else {
            System.out.println("Нет невыданных призов.");
        }
    }
    
    public void displayClaimedPrizes() {
        if (!claimedPrizes.isEmpty()) {
            System.out.println("Выданные призы:");
            for (Toy prize : claimedPrizes) {
                System.out.println(prize);
            }
        } else {
            System.out.println("Нет выданных призов.");
        }
    }
    
    private void savePrizeToFile(Toy prizeToy) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PRIZE_FILE, true))) {
            writer.println(prizeToy.getId() + "," + prizeToy.getName() + "," + prizeToy.getQuantity() + "," + prizeToy.getProbability());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void displayStock() {
        for (Toy toy : stock) {
            System.out.println(toy);
        }
    }

    public void displayUnclaimedPrizes() {
        for (Toy toy : unclaimedPrizes) {
            System.out.println(toy);
        }
    }

    private void loadStockFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(STOCK_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double probability = Double.parseDouble(parts[3]);
                    Toy toy = new Toy(id, name, quantity, probability);
                    stock.add(toy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    private void loadUnclaimedPrizesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(UNCLAIMED_PRIZES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double probability = Double.parseDouble(parts[3]);
                    Toy toy = new Toy(id, name, quantity, probability);
                    unclaimedPrizes.add(toy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void saveStockToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STOCK_FILE))) {
            for (Toy toy : stock) {
                writer.println(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getProbability());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void saveUnclaimedPrizesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(UNCLAIMED_PRIZES_FILE))) {
            for (Toy toy : unclaimedPrizes) {
                writer.println(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getProbability());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateToyProbabilityAndQuantity(int toyId, double newProbability, int newQuantity) {
        for (Toy toy : stock) {
            if (toy.getId() == toyId) {
                toy.setProbability(newProbability);
                toy.setQuantity(newQuantity);
                saveStockToFile();
                return; 
            }
        }
        System.out.println("Игрушка с ID " + toyId + " не найдена.");
    }
    
}

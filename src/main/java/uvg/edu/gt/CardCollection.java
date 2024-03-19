package uvg.edu.gt;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CardCollection {

    private final Map<String, Integer> userCollection;
    private final Map<String, String> allCards;

    public CardCollection(Map<String, String> allCards) {
        this.userCollection = new HashMap<>();
        this.allCards = allCards;
    }

    public void readCardsFromFile(String filePath) throws IOException {
        try (Scanner scanner = new Scanner(new File("cards_desc.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String cardName = parts[0];
                String cardType = parts[0]; // Hasta ahorita me percate que al buscar tipo especifico de una carta tira Null :(
                allCards.put(cardName, cardType);
            }
        }
    }

    public void addCard(String cardName) {
        if (!allCards.containsKey(cardName)) {
            System.out.println("Error: La carta " + cardName + " no existe.");
            return;
        }

        long initTime = System.nanoTime();
        userCollection.put(cardName, userCollection.getOrDefault(cardName, 0) + 1);
        long finishTime = System.nanoTime();

        long elapsedTime = finishTime - initTime;
        System.out.println("Tiempo de inserción de " + cardName + ": " + elapsedTime + " nanosegundos");
    }

    public String getCardType(String cardName) {
        return allCards.get(cardName);
    }

    public Map<String, Integer> getUserCollection() {
        return userCollection;
    }

    public Map<String, Integer> getUserCollectionSortedByCardType() {
        return new TreeMap<>(userCollection);
    }

    public List<String> getAllCards() {
        return allCards.keySet().stream().toList();
    }

    public List<String> getAllCardsSortedByCardType() {
        return new ArrayList<>(allCards.keySet()).stream()
                .sorted(Comparator.comparing(allCards::get))
                .toList();
    }
}

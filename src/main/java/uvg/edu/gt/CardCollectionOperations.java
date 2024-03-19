package uvg.edu.gt;
import java.util.Map;

import java.io.IOException;

public interface CardCollectionOperations {

    void readCardsFromFile(String fileName) throws IOException;

    void addCard(String cardName);

    String getCardType(String cardName);

    Map<String, Integer> getUserCollection();

    void displayUserCollection();

    void displaySortedUserCollection();

    void displayAllCards();

    void displaySortedAllCards();
}

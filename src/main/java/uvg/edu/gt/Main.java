package uvg.edu.gt;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
public class Main {

    private static final String DEFAULT_MAP_TYPE = "HashMap";
    private static final String CARDS_FILE_PATH = "cards_descsas.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Seleccionar el tipo de mapa
        System.out.println("Seleccionar la implementación de MAP que usará su programa:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        int choice = scanner.nextInt();
        String mapType = getMapType(choice);

        // Crear el mapa y la colección de cartas
        Map<String, String> allCards = MapFactory.createMap(mapType); // Se crea la instancia de allCards
        CardCollection cardCollection = new CardCollection(allCards);

        // Leer las cartas del archivo
        try {
            cardCollection.readCardsFromFile("cards_desc.txt");
            System.out.println("Leido con exito ") ;
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Menú principal
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarCarta(cardCollection, scanner);
                    break;
                case 2:
                    obtenerTipoCarta(cardCollection, scanner);
                    break;
                case 3:
                    mostrarColeccionUsuario(cardCollection);
                    break;
                case 4:
                    mostrarColeccionUsuarioOrdenada(cardCollection);
                    break;
                case 5:
                    long initTime = System.nanoTime();

                    mostrarTodasCartas(cardCollection);
                    long finishTime = System.nanoTime();
                    long elapsedTime = finishTime - initTime;
                    System.out.println("Tiempo de ejecución: " + elapsedTime + " nanosegundos");

                    break;
                case 6:
                    initTime = System.nanoTime();
                    mostrarTodasCartasOrdenadas(cardCollection);
                    finishTime = System.nanoTime();
                    elapsedTime = finishTime - initTime;
                    System.out.println("Tiempo de ejecución: " + elapsedTime + " nanosegundos");

                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarColeccionUsuario(CardCollection cardCollection) {
    }

    private static void mostrarMenu() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Agregar una carta a la colección del usuario.");
        System.out.println("2. Mostrar el tipo de una carta específica");
        System.out.println("3. Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección.");
        System.out.println("4. Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección, ordenadas por tipo.");
        System.out.println("5. Mostrar el nombre y tipo de todas las cartas existentes.");
        System.out.println("6. Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo.");
        System.out.println("7. Salir");
    }

    private static String getMapType(int choice) {
        switch (choice) {
            case 1:
                return "HashMap";
            case 2:
                return "TreeMap";
            case 3:
                return "LinkedHashMap";
            default:
                System.out.println("Opción no válida. Se usará el HashMap por defecto.");
                return DEFAULT_MAP_TYPE;
        }
    }

    private static void agregarCarta(CardCollection cardCollection, Scanner scanner) {
        System.out.println("Ingrese el nombre de la carta para agregarla a la colección:");
        scanner.nextLine();
        String cardName = scanner.nextLine();
        cardCollection.addCard(cardName);
    }

    private static void obtenerTipoCarta(CardCollection cardCollection, Scanner scanner) {
        System.out.println("Ingrese el nombre de la carta para obtener el tipo:");
        scanner.nextLine();
        String cardName = scanner.nextLine();
        String cardType = cardCollection.getCardType(cardName);
        System.out.println("Nombre: " + cardName + " Tipo: " + cardType);
    }

    private static void mostrarColeccionUsuarioOrdenada(CardCollection cardCollection) {
        System.out.println("Colección del usuario ordenada por tipo:");
        Map<String, Integer> sortedUserCollection = cardCollection.getUserCollectionSortedByCardType();
        for (Map.Entry<String, Integer> entry : sortedUserCollection.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + " Tipo: " + entry.getValue());
        }
    }
    private static void mostrarTodasCartas(CardCollection cardCollection) {
        System.out.println("Todas las cartas:");
        for (String cardName : cardCollection.getAllCards()) {
            System.out.println("Nombre: " + cardName);
        }
    }
    private static void mostrarTodasCartasOrdenadas(CardCollection cardCollection) {
        System.out.println("Todas las cartas ordenadas por tipo:");
        List<String> sortedAllCards = cardCollection.getAllCardsSortedByCardType();
        for (String cardName : sortedAllCards) {
            System.out.println("Nombre: " + cardName);
        }
    }
}


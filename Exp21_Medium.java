import java.util.*;

class Card {
    String symbol;
    String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public void display() {
        System.out.println(symbol + " - " + value);
    }
}

public class Exp21_Medium {
    static Collection<Card> cards = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addCard() {
        System.out.print("Enter Card Symbol: ");
        String symbol = sc.next();
        System.out.print("Enter Card Value: ");
        String value = sc.next();
        cards.add(new Card(symbol, value));
        System.out.println("Card added successfully!\n");
    }

    public static void searchCardsBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = sc.next();
        boolean found = false;
        for (Card card : cards) {
            if (card.symbol.equalsIgnoreCase(symbol)) {
                card.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for this symbol.\n");
        }
    }

    public static void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards stored.\n");
        } else {
            for (Card card : cards) {
                card.display();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Card\n2. Search Cards by Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: addCard(); break;
                case 2: searchCardsBySymbol(); break;
                case 3: displayAllCards(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice. Try again.\n");
            }
        }
    }
}

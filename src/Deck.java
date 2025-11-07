import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    private static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final int[] VALUES = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        cards.clear();
        for (String suit : SUITS) {
            for (int i = 0; i < RANKS.length; i++) {
                cards.add(new Card(RANKS[i], suit, VALUES[i]));
            }
        }
    }

    
    public void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            initializeDeck();
            shuffle();
        }
        return cards.remove(0);
    }

    public int getCardsRemaining() {
        return cards.size();
    }

    public void reset() {
        initializeDeck();
    }
}

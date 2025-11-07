import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getTotal() {
        int total = 0;
        int aces = 0;

        // First, add up all card values
        for (Card card : cards) {
            total += card.getPoints();
            if (card.getRank().equals("Ace")) {
                aces++;
            }
        }

        // Adjust for Aces (convert from 11 to 1 if necessary)
        while (total > 21 && aces > 0) {
            total -= 10;  // Change Ace from 11 to 1
            aces--;
        }

        return total;
    }

    public boolean isBusted() {
        return getTotal() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getTotal() == 21;
    }

    public void clear() {
        cards.clear();
    }

    public int getCardCount() {
        return cards.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i));
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String toStringHiddenFirst() {
        if (cards.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("[Hidden], ");
        for (int i = 1; i < cards.size(); i++) {
            sb.append(cards.get(i));
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

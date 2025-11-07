import java.util.Scanner;

public class BlackjackIO {
    private Scanner scanner;

    public BlackjackIO() {
        scanner = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("BLACKJACK!");
        System.out.println("Blackjack payout is 3:2");
        System.out.println();
    }

    public void displayMoney(double money) {
        System.out.println("Total money: " + String.format("%.2f", money));
        System.out.println();
    }

    public double getBetAmount(double money, double minBet, double maxBet) {
        while (true) {
            System.out.print("Bet amount: ");
            String input = scanner.nextLine().trim();
            
            try {
                double bet = Double.parseDouble(input);
                
                if (bet < minBet) {
                    System.out.println("Error: Bet must be at least " + String.format("%.0f", minBet));
                } else if (bet > maxBet) {
                    System.out.println("Error: Bet cannot exceed " + String.format("%.0f", maxBet));
                } else if (bet > money) {
                    System.out.println("Error: You don't have enough money.");
                } else {
                    System.out.println();
                    return bet;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }
    }

    public void displayDealerShowCard(Hand dealerHand) {
        System.out.println("DEALER'S SHOW CARD");
        if (dealerHand.getCardCount() > 0) {
            System.out.println(dealerHand.getCards().get(0));
        }
        System.out.println();
    }

    public void displayPlayerCards(Hand playerHand) {
        System.out.println("YOUR CARDS");
        for (Card card : playerHand.getCards()) {
            System.out.println(card);
        }
        System.out.println("YOUR POINTS: " + playerHand.getTotal());
        System.out.println();
    }

    public void displayDealerCards(Hand dealerHand) {
        System.out.println("DEALER'S CARDS");
        for (Card card : dealerHand.getCards()) {
            System.out.println(card);
        }
        System.out.println("DEALER'S POINTS: " + dealerHand.getTotal());
        System.out.println();
    }

    public String getHitOrStand() {
        while (true) {
            System.out.print("Hit or Stand? (h/s): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("h") || input.equals("hit")) {
                System.out.println();
                return "hit";
            } else if (input.equals("s") || input.equals("stand")) {
                System.out.println();
                return "stand";
            } else {
                System.out.println("Error: Invalid input. Please enter 'h' for hit or 's' for stand.");
            }
        }
    }

    public boolean getYesOrNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("y") || input.equals("yes")) {
                System.out.println();
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                System.out.println();
                return false;
            } else {
                System.out.println("Error: Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayWin() {
        System.out.println("You win!");
    }

    public void displayLose() {
        System.out.println("You lose!");
    }

    public void displayPush() {
        System.out.println("It's a tie!");
    }

    public void displayGoodbye() {
        System.out.println("Bye!");
    }

    public void close() {
        scanner.close();
    }
}

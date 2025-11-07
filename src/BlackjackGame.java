public class BlackjackGame {
    private double money;
    private double currentBet;
    private double minBet;
    private double maxBet;
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;
    private BlackjackIO io;

    public BlackjackGame(double startingMoney, double minBet, double maxBet, BlackjackIO io) {
        this.money = startingMoney;
        this.minBet = minBet;
        this.maxBet = maxBet;
        this.io = io;
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        deck.shuffle();
    }

    public double getMoney() {
        return money;
    }

    public double getMinBet() {
        return minBet;
    }

    public double getMaxBet() {
        return maxBet;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public boolean canContinuePlaying() {
        return money >= minBet;
    }

    public void placeBet(double bet) {
        currentBet = bet;
    }

    public void dealInitialCards() {
        playerHand.clear();
        dealerHand.clear();

        // Deal two cards to player and dealer
        playerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        playerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
    }

    public void playerHit() {
        playerHand.addCard(deck.dealCard());
    }

    public void dealerPlay() {
        // Dealer must hit on 16 or less, stand on 17 or more
        while (dealerHand.getTotal() < 17) {
            dealerHand.addCard(deck.dealCard());
        }
    }

    public void determineOutcome() {
        int playerTotal = playerHand.getTotal();
        int dealerTotal = dealerHand.getTotal();
        boolean playerBlackjack = playerHand.isBlackjack();
        boolean dealerBlackjack = dealerHand.isBlackjack();

        // Show final hands
        io.displayDealerCards(dealerHand);

        // Check for blackjack
        if (playerBlackjack && dealerBlackjack) {
            io.displayPush();
            
        } else if (playerBlackjack) {
            double winnings = currentBet * 1.5;  
            money += winnings;
            io.displayWin();
        } else if (dealerBlackjack) {
            money -= currentBet;
            io.displayLose();
        } else if (playerHand.isBusted()) {
            money -= currentBet;
            io.displayLose();
        } else if (dealerHand.isBusted()) {
            money += currentBet;
            io.displayWin();
        } else if (playerTotal > dealerTotal) {
            money += currentBet;
            io.displayWin();
        } else if (playerTotal < dealerTotal) {
            money -= currentBet;
            io.displayLose();
        } else {
            io.displayPush();
            
        }

        
        io.displayMoney(money);
    }

    public void playRound() {
        // Get bet
        currentBet = io.getBetAmount(money, minBet, maxBet);
        
        // Deal initial cards
        dealInitialCards();
        
        // Show dealer's show card and player's cards
        io.displayDealerShowCard(dealerHand);
        io.displayPlayerCards(playerHand);

        // Check for player blackjack
        if (playerHand.isBlackjack()) {
            determineOutcome();
            return;
        }

        // Player's turn
        boolean playerDone = false;
        while (!playerDone && !playerHand.isBusted()) {
            String action = io.getHitOrStand();
            
            if (action.equals("hit")) {
                playerHit();
                io.displayPlayerCards(playerHand);
                
                if (playerHand.isBusted()) {
                    money -= currentBet;
                    io.displayLose();
                    io.displayMoney(money);
                    return;
                }
            } else {
                playerDone = true;
            }
        }

        // Dealer's turn
        dealerPlay();

        // Determine outcome
        determineOutcome();
    }
}

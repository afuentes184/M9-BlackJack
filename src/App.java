public class App {
    private static final double STARTING_MONEY = 100.00;
    private static final double MIN_BET = 5.00;
    private static final double MAX_BET = 50.00;

    public static void main(String[] args) {
        BlackjackIO io = new BlackjackIO();
        
        // Display welcome message
        io.displayWelcome();
        
        // Create game with starting money and bet limits
        BlackjackGame game = new BlackjackGame(STARTING_MONEY, MIN_BET, MAX_BET, io);
        
        // Main game loop
        boolean continuePlaying = true;
        
        while (continuePlaying && game.canContinuePlaying()) {
            // Display current money
            io.displayMoney(game.getMoney());
            
            // Play a round
            game.playRound();
            
            // Check if player can continue
            if (!game.canContinuePlaying()) {
                io.displayMessage("You're out of money!");
                break;
            }
            
            // Ask if player wants to play again
            continuePlaying = io.getYesOrNo("Play again?");
        }
        
        // Display goodbye message
        io.displayGoodbye();
        io.close();
    }
}

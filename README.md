# Blackjack Card Game in Java
Welcome to the Blackjack Card Game in the Java repository! This project implements the classic Blackjack (also known as 21) card game in the Java programming language. It provides a graphical interface for users to play the game against the computer dealer (AI).

# Rules of the game
+ The goal of the game is to have a hand value as close to 21 as possible without exceeding it.
+ Each player is dealt two cards initially.
+ Numbered cards (2 to 10) are worth their face value, face cards (Jacks, Queens, and Kings) are worth 10, and Aces are worth 1.
+ You can choose to "hit" to receive an additional card or "stand" to keep your current hand value.
+ If your hand value exceeds 21, you bust and lose the game. If the dealer busts or has a lower hand value than you, you win the game.
+ If you and the dealer both have hands with values less than or equal to 21, the one closest to 21 without exceeding it wins the game.

# Project Structure
The repository is organized as follows:
+ AI.java: This file represents the Dealer (AI) player for the Blackjack card game. It contains a class definition for the AI class which extends the player class and has the strategy for the AI player to make decisions during the game.
+ BJ.java: This file is the main entry point of the Blackjack card game. It contains the main method, which initializes the game and manages the overall flow of the game.
+ Card.java: This file represents a single playing card in the Blackjack game. It contains a class definition for the Card. 
+ Deck.java: This file represents the deck of cards used in the Blackjack game. It contains functionalities like shuffling, dealing cards, and keeping track of remaining cards in the deck.
+ Game.java: This file contains the core game logic of the Blackjack card game. It contains a class definition for the Game class, which handles the game rules and determines the winner or loser of each round.
+ Money.java: This file is responsible for managing the money of the players during the game.
+ Player.java: This file likely represents a player in the Blackjack game. It may contain a class definition for the Player class, defining properties like the player's hand of cards, calculating his score, and also includes methods for decision-making during the game.
+ UIManager.java: This file has all the user interface (UI) management  for the Blackjack game. It is responsible for displaying the game state, accepting user inputs, and providing feedback to the player during the game. It also interacts with other classes to render the game on the screen and handle user interactions.

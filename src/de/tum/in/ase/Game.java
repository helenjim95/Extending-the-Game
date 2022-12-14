package de.tum.in.ase;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static de.tum.in.ase.GameBoard.EMPTY;
import static de.tum.in.ase.GameBoard.HERO;

//TODO: The constructor shall take another parameter of enum "HeroType". This will decide which type of hero will be
// used in the game.
// Extend the "runGame" so that it will also read "su","sr","sd" and "sl" not paying attention to upper and lower cases
// as inputs for calling the "useSpecialMethod(char option)" with its respective options.
// Also add one method "touchMonster" to check whether the hero is in the monster space.
// The game shall end when the hero touches a monster.
// Handle all exceptions with messages as seen in the example from the problem statement!

public class Game {
    private GameBoard gameBoard;
    private Hero hero;

    public Game(int sizeX, int sizeY, HeroType type) {
        this.gameBoard = new GameBoard(sizeX, sizeY);
        if (type == HeroType.MAGE) {
            this.hero = new Mage(this);
        } else if (type == HeroType.HUNTER) {
            this.hero = new Hunter(this);
        }
    }

    public void moveLeft() throws IllegalMoveException {
        if (this.getHero().getPosX() > 0) {
            move(-1, 0);
        } else {
            throw new IllegalMoveException("The next move in this direction is outside of the game board, please try another move!");
        }
    }

    public void moveRight() throws IllegalMoveException {
        if (this.getHero().getPosX() < this.getGameBoard().getSizeX() - 1) {
            move(1, 0);
        } else {
            throw new IllegalMoveException("The next move in this direction is outside of the game board, please try another move!");
        }
    }

    public void moveDown() throws IllegalMoveException {
        if (this.getHero().getPosY() < this.getGameBoard().getSizeY() - 1) {
            move(0, 1);
        } else {
            throw new IllegalMoveException("The next move in this direction is outside of the game board, please try another move!");
        }
    }

    public void moveUp() throws IllegalMoveException {
        if (this.getHero().getPosY() > 0) {
            move(0, -1);
        } else {
            throw new IllegalMoveException("The next move in this direction is outside of the game board, please try another move!");
        }
    }

    private void move(int deltaX, int deltaY) {
        this.getGameBoard().set(this.getHero().getPosX(), this.getHero().getPosY(), EMPTY);
        this.getHero().setPosX(this.getHero().getPosX() + deltaX);
        this.getHero().setPosY(this.getHero().getPosY() + deltaY);
        this.getGameBoard().set(this.getHero().getPosX(), this.getHero().getPosY(), HERO);
    }

    public boolean isWon() {
        return gameBoard.get(gameBoard.getSizeX() - 1, gameBoard.getSizeY() - 1) == HERO
               && this.getHero().getPosX() == gameBoard.getSizeX() - 1 && this.getHero().getPosY() == gameBoard.getSizeY() - 1;
    }

    public void runGame() throws IllegalMoveException {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        while (!isWon() && !this.hero.isKilled()) {
            gameBoard.printGameBoard();

            System.out.println("Please enter the first letter of the desired direction:");
            String userInput = scanner.nextLine().toLowerCase();
            while (!List.of("u", "d", "l", "r", "su", "sr", "sd", "sl").contains(userInput)) {
                System.out.println("This input is not recognized, please enter again!");
                userInput = scanner.nextLine().toLowerCase();
            }
            try {
                switch (userInput) {
                    case "l" -> moveLeft();
                    case "r" -> moveRight();
                    case "d" -> moveDown();
                    case "u" -> moveUp();
                    case "su" -> hero.useSpecialPower('U');
                    case "sr" -> hero.useSpecialPower('R');
                    case "sd" -> hero.useSpecialPower('D');
                    case "sl" -> hero.useSpecialPower('L');
                    default -> System.out.println("This input is not recognized, please enter again!");
                }
            } catch (IllegalMoveException e) {
                System.out.println(e.getMessage());
            }
        }
        if (hero.isKilled()) {
            System.out.println("Hero has been killed by a Monster!");
        } else {
            System.out.println("Hero has reached the goal!");
        }
    }

    @NonNull
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(@NonNull GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @NonNull
    public Hero getHero() {
        return hero;
    }

    public void setHero(@NonNull Hero hero) {
        this.hero = hero;
    }
}

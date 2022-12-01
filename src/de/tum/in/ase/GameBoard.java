package de.tum.in.ase;

//TODO: Add attributes "monsters" which is a list of monsters and also "maxMonsters" which is an int to limit how
// many monsters we want to have in the game. Don't forget the getters and setters!
// Also add a new method called "generateMonster" which will add a monster randomly on the map when the number of the
// monsters is still lower than "maxMonsters".

public class GameBoard {

    private static final int MIN_SIZE = 3;
    public static final char HERO = 'H';
    public static final char GOAL = 'G';
    public static final char EMPTY = '_';

    private int sizeX;
    private int sizeY;
    private char[][] boardMatrix;

    public GameBoard(int sizeX, int sizeY) {
        this.sizeX = Math.max(sizeX, MIN_SIZE);
        this.sizeY = Math.max(sizeY, MIN_SIZE);

        this.boardMatrix = new char[this.sizeX][this.sizeY];

        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                this.getBoardMatrix()[j][i] = EMPTY;
            }
        }

        this.boardMatrix[0][0] = HERO;
        this.boardMatrix[this.sizeX - 1][this.sizeY - 1] = GOAL;
    }

    public char get(int x, int y) {
        if (0 <= x && x < this.getSizeX() && 0 <= y && y < this.getSizeY()) {
            return boardMatrix[x][y];
        } else {
            return '\0';
        }
    }

    public void set(int x, int y, char piece) {
        if (0 <= x && x < getSizeX() && 0 <= y && y < getSizeY()) {
            boardMatrix[x][y] = piece;
        }
    }

    public void printGameBoard() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                builder.append(getBoardMatrix()[j][i]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public char[][] getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardMatrix(char[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

}

package de.tum.in.ase;

public class Mage extends Hero {

    public Mage(Game game) {
        super(game);
    }

    @Override
    public void useSpecialPower(char option) throws IllegalMoveException {
        int posX = this.getPosX();
        int posY = this.getPosY();
        int gameSizeX = this.getGame().getGameBoard().getSizeX();
        int gameSizeY = this.getGame().getGameBoard().getSizeY();

//        When the mage is not on the edge of the map
        if (posX != 0 && posX != gameSizeX - 1 && posY != 0 && posY != gameSizeY - 1) {
            throw new IllegalMoveException("Mage can only use special power on the edge of the board.");
        } else {
            switch (option) {
                case 'U':
                    if (posX != 0) {
                        throw new IllegalMoveException("Illegal move: Not at the upper edge of the map");
                    } else {
                        this.setPosX(gameSizeX - 1);
                    }
                    break;
                case 'R':
                    if (posY != gameSizeY - 1) {
                        throw new IllegalMoveException("Illegal move: Not at the rightmost edge of the map");
                    } else {
                        this.setPosY(0);
                    }
                    break;
                case 'D':
                    if (posX != gameSizeX - 1) {
                        throw new IllegalMoveException("Illegal move: Not at the bottom edge of the map");
                    } else {
                        this.setPosX(0);
                    }
                    break;
                case 'L':
                    if (posY != 0) {
                        throw new IllegalMoveException("Illegal move: Not at the leftmost edge of the map");
                    } else {
                        this.setPosY(gameSizeY - 1);
                    }
                    break;
                default:
                    throw new IllegalMoveException("Illegal move: unknown option");
            }
        }
    }

    @Override
    public boolean isKilled() {
        return false;
    }

}

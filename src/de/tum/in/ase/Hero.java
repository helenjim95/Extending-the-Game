package de.tum.in.ase;

//TODO: Make this class into abstract, add an attribute "game" of the Game class and a method
// "useSpecialPower(char option)" then create the subclasses.
public class Hero {

    protected int posX;
    protected int posY;

    public Hero() {
        this.posX = 0;
        this.posY = 0;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

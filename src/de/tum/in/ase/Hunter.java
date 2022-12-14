package de.tum.in.ase;

import java.util.ArrayList;
import java.util.List;

public class Hunter extends Hero {

    boolean isKilled = false;

    public Hunter(Game game) {
        super(game);
    }

    public void killMonster(Monster monster) {
        int monsterPosX = monster.getPosX();
        int monsterPosY = monster.getPosY();
        GameBoard board = this.getGame().getGameBoard();
        this.setPosX(monsterPosX);
        this.setPosY(monsterPosY);
        board.set(monsterPosX, monsterPosY, 'H');
        board.generateMonster();
        isKilled = true;
    }

    @Override
    public boolean isKilled(){
        return isKilled;
    }

    @Override
    public void useSpecialPower(char option) throws IllegalMoveException {
        GameBoard gameBoard = this.getGame().getGameBoard();
        int posXHunter = this.getPosX();
        int posYHunter = this.getPosY();
        List<Monster> monsters = gameBoard.getMonsters();
        List<Integer> posXMonsters = new ArrayList<>();
        List<Integer> posYMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            int posXMonster = monster.getPosX();
            int posYMonster = monster.getPosY();
            posXMonsters.add(posXMonster);
            posYMonsters.add(posYMonster);
        }
        boolean ajacentToMonster = false;
        boolean monsterAbove = false;
        boolean monsterToRight = false;
        boolean monsterBelow = false;
        boolean monsterToLeft = false;
        int index = 0;
        for (int i = 0; i < posXMonsters.size(); i++) {
            if (posXMonsters.get(i) + 1 == posXHunter && posYMonsters.get(i) == posYHunter) {
                monsterAbove = true;
                index = i;
                ajacentToMonster = true;
            }
            if (posXMonsters.get(i) == posXHunter && posYMonsters.get(i) - 1 == posYHunter) {
                monsterToRight = true;
                index = i;
                ajacentToMonster = true;
            }
            if (posXMonsters.get(i) - 1 == posXHunter && posYMonsters.get(i) == posYHunter) {
                monsterBelow = true;
                index = i;
                ajacentToMonster = true;
            }
            if (posXMonsters.get(i) == posXHunter && posYMonsters.get(i) + 1 == posYHunter) {
                monsterToLeft = true;
                index = i;
                ajacentToMonster = true;
            }
        }
        if (!ajacentToMonster) {
            throw new IllegalMoveException("Hhunter is not adjacent to any monster.");
        } else {
            switch (option) {
                case 'U':
                    if (!monsterAbove) {
                        throw new IllegalMoveException("there's no monster above you");
                    } else {
                        this.killMonster(monsters.get(index));
                    }
                    break;
                case 'R':
                    if (!monsterToRight) {
                        throw new IllegalMoveException("there's no monster to your right");
                    } else {
                        this.killMonster(monsters.get(index));
                    }
                    break;
                case 'D':
                    if (!monsterBelow) {
                        throw new IllegalMoveException("there's no monster below you");
                    } else {
                        this.killMonster(monsters.get(index));
                    }
                    break;
                case 'L':
                    if (!monsterToLeft) {
                        throw new IllegalMoveException("there's no monster to your left");
                    } else {
                        this.killMonster(monsters.get(index));
                    }
                    break;
                default:
                    throw new IllegalMoveException("Illegal move: unknown option");
            }
        }
    }

}

package Players;

import Enums.eMark;

public class PlayerFactory {

    public static PlayerFactory playerFactory = new PlayerFactory();

    private PlayerFactory(){};


    /**
     * A constructor that gets a player name, and accordingly creates such an object.
     * @param type class name.
     * @return Player object.
     */
    public Player buildPlayer(String type, String name, eMark defaultSign) {

        switch(type) {
            case "Human":
                return new HumanPlayer(name, defaultSign);
            case "Whatever":
                return new WhateverPlayer(name, defaultSign);
            case "TTT.Players.CleverPlayer":
                return new CleverPlayer(name, defaultSign);
            default:
                return null;
        }
    }
}

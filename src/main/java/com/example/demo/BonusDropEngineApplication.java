package com.example.demo;


public class BonusDropEngineApplication {

    public static void main(String[] args) {
        PlayerChoice choice = new PlayerChoice();
        choice.middle = "Middle";

        BonusDropEngine engine = new BonusDropEngineImpl();
        System.out.println(engine.calculateBonus(choice));
    }

}

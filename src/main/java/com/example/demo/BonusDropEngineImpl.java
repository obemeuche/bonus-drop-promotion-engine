package com.example.demo;

import java.util.*;

public class BonusDropEngineImpl implements BonusDropEngine {

    @Override
    public BonusPlayResult calculateBonus(PlayerChoice choice) {
        Map<String, List<Integer>> getProbability = populateBonusProbability();

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        System.out.println(getProbability);
        System.out.println("Random number: " + randomNumber);
        // Check all possible choices in sequence: left, right, middle
        if (!choice.left.isEmpty()) {
            return determineBonus(getProbability.get(choice.left), randomNumber);
        } else if (!choice.right.isEmpty()) {
            return determineBonus(getProbability.get(choice.right), randomNumber);
        } else if (!choice.middle.isEmpty()) {
            return determineBonus(getProbability.get(choice.middle), randomNumber);
        }

        return null;
    }

    private BonusPlayResult determineBonus(List<Integer> prob, int randomNumber) {
        BonusPlayResult result = new BonusPlayResult();

        int bonusLimit = prob.get(0);      // Upper limit for "Bonus"
        int noBonusLimit = prob.get(1);    // Upper limit for "No Bonus"
        int replayLimit = prob.get(2);     // Upper limit for "Replay"

        // Adjust logic based on how the probability ranges work
        if (randomNumber <= replayLimit) {
            result.replay = "Replay";
            return result;
        }
        if (randomNumber <= noBonusLimit) {
            result.noBonus = "No Bonus";
            return result;
        }
        if (randomNumber <= bonusLimit) {
            result.bonus = "Bonus";
            return result;
        }

        return result;
    }

    public Map<String, List<Integer>> populateBonusProbability() {
        PlayerChoice choice = new PlayerChoice();

        Map<String, List<Integer>> map = new HashMap<>();
        map.put(choice.left = "Left", new ArrayList<>(Arrays.asList(38, 40, 22)));
        map.put(choice.right = "Right", new ArrayList<>(Arrays.asList(75, 25, 5)));
        map.put(choice.middle = "Middle", new ArrayList<>(Arrays.asList(50, 30, 20)));

        return map;
    }

    public static void main(String[] args) {
        PlayerChoice choice = new PlayerChoice();
        choice.left = "Left";

        BonusDropEngine engine = new BonusDropEngineImpl();
        System.out.println(engine.calculateBonus(choice));
    }
}

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
            return determineBonus(getProbability.get(choice.left), randomNumber, choice.left);
        } else if (!choice.right.isEmpty()) {
            return determineBonus(getProbability.get(choice.right), randomNumber, choice.right);
        } else if (!choice.middle.isEmpty()) {
            return determineBonus(getProbability.get(choice.middle), randomNumber, choice.middle);
        }

        return null;
    }

    private BonusPlayResult determineBonus(List<Integer> prob, int randomNumber, String choice) {
        BonusPlayResult result = new BonusPlayResult();

        int min = prob.get(0);      // Upper limit for "Bonus"
        int mid = prob.get(1);    // Upper limit for "No Bonus"
        int max = prob.get(2);     // Upper limit for "Replay"

        Collections.sort(prob);
        // Adjust logic based on how the probability ranges work
        if (randomNumber <= max) {
            if (choice.equals("left")) {
                result.replay = "No Bonus";
                return result;
            }
            result.replay = "Replay";
            return result;
        }
        if (randomNumber <= mid) {
            if (choice.equals("left")) {
                result.replay = "Bonus";
                return result;
            }
            result.noBonus = "No Bonus";
            return result;
        }
        if (randomNumber <= min) {
            if (choice.equals("left")) {
                result.replay = "Replay";
                return result;
            }
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

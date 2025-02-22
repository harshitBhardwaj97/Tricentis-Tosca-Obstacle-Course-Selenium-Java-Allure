package com.harshitbhardwaj.utils;

/**
 * @author Harshit Bhardwaj
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static String sanitizeObstacleName(String obstacleName) {
        String[] obstacleParts = obstacleName.split(" ");
        if (obstacleParts.length == 1) {
            return capitalize(obstacleParts[0]);
        }

        StringBuilder result = new StringBuilder();
        result.append(obstacleParts[0]);
        for (int i = 1; i < obstacleParts.length; i++) {
            result.append(capitalize(obstacleParts[i]));
        }

        return result.toString();
    }

    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        text = text.replaceAll("[^a-zA-Z0-9\\s]", "");

        String[] words = text.split("\\s+");
        StringBuilder capitalizedText = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedText.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return capitalizedText.toString().trim();
    }

    public static String createTestName(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        text = text.replaceAll("[^a-zA-Z0-9\\s]", "");

        return text.substring(0, 1).toLowerCase() + text.substring(1);
    }
}

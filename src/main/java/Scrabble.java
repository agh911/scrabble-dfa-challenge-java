import java.util.HashMap;
import java.util.Arrays;

public class Scrabble {
    private String word;
    private Character[] doubleLetter;
    private Character[] tripleLetter;
    private boolean doubleWord;
    private boolean tripleWord;
    private HashMap<Character, Integer> letterScores;
    private HashMap<Character, Boolean> doubleLetterUsed = new HashMap<>();

    public Scrabble(String word) {
        this.word = word != null ? word.toUpperCase() : "";
        initLetterScores();
    }

    public Scrabble(String word, Character[] doubleLetter, Character[] tripleLetter, boolean doubleWord, boolean tripleWord) {
        this.word = word != null ? word.toUpperCase() : "";
        this.doubleLetter = doubleLetter;
        this.tripleLetter = tripleLetter;
        this.doubleWord = doubleWord;
        this.tripleWord = tripleWord;
        initLetterScores();
    }

    private void initLetterScores() {
        letterScores = new HashMap<>();
        letterScores.put('A', 1);
        letterScores.put('E', 1);
        letterScores.put('I', 1);
        letterScores.put('O', 1);
        letterScores.put('U', 1);
        letterScores.put('L', 1);
        letterScores.put('N', 1);
        letterScores.put('R', 1);
        letterScores.put('S', 1);
        letterScores.put('T', 1);
        letterScores.put('D', 2);
        letterScores.put('G', 2);
        letterScores.put('B', 3);
        letterScores.put('C', 3);
        letterScores.put('M', 3);
        letterScores.put('P', 3);
        letterScores.put('F', 4);
        letterScores.put('H', 4);
        letterScores.put('V', 4);
        letterScores.put('W', 4);
        letterScores.put('Y', 4);
        letterScores.put('K', 5);
        letterScores.put('J', 8);
        letterScores.put('X', 8);
        letterScores.put('Q', 10);
        letterScores.put('Z', 10);
        letterScores.put('\0', 0);
        letterScores.put('\t', 0);
        letterScores.put('\n', 0);
    }

    public int score() {
        if (!isValidWord()) {
            return 0;
        }
        int baseScore = baseScore();
        int scoreWithLetterBonus = addLetterBonus(baseScore);
        int totalScore = doubleWord ? doubleWord(scoreWithLetterBonus) : tripleWord ? tripleWord(scoreWithLetterBonus) : scoreWithLetterBonus;
        return totalScore;
    }

    private boolean isValidWord() {
        return !word.isEmpty();
    }

    private int baseScore() {
        int score = 0;
        for(char c : word.toCharArray()) {
            score += letterScores.get(c);
        }
        return score;
    }

    private int doubleWord(int score) {
        return baseScore() * 2;
    }

    private int tripleWord(int score) {
        return baseScore() * 3;
    }

    private int addLetterBonus(int baseScore) {
        for (char c : word.toCharArray()) {
            baseScore = addDoubleLetter(baseScore, c);
        }
        return baseScore;
    }

    private int addDoubleLetter(int baseScore, char c) {
        if(characterIsIncluded(doubleLetter, c) && !doubleLetterUsed.containsKey(c)) {
            baseScore += letterScores.get(c);
            doubleLetterUsed.put(c, true);
        }
        return baseScore;
    }

    private boolean characterIsIncluded(Character[] array, char c) {
        return array != null && Arrays.asList(array).contains(c);
    }
}
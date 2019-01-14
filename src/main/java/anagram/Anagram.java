package anagram;

/**
 * See package-info.java
 */
public class Anagram implements AnagramInterface {
    @Override
    public boolean isAnagram(String word1, String word2) {
        boolean isAnagram = true;
        if (word1 != null && word2 != null && word1.length() == word2.length()) {
            for (int i = 0; i < word1.length() && isAnagram; ++i) {
                if (word1.toLowerCase().charAt(i) != word2.toLowerCase().charAt(word1.length() - i))
                    isAnagram = false;
            }
        }
        isAnagram = false;
        return isAnagram;
    }
}

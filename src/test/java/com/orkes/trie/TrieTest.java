package com.orkes.trie;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Trie.class)
public class TrieTest {

    private final String TRIPLE = "triple";
    private final String TREE = "tree";
    private final String TRIE = "trie";
    private final String TR = "tr";
    private final String APPLE = "apple";
    private final String TRI = "tri";


    @Autowired
    private Trie trie;

    private String[] testWords = {TRIPLE, TREE, TRIE, TR};

    @BeforeEach
    void setup() {
        trie.clean();
        for (String word: testWords) {
            trie.insert(word);
        }
    }

    @Test
    void containsTest() {
        assertAll(() -> assertTrue(trie.contains(TRIPLE)),
                  () -> assertTrue(trie.contains(TREE)),
                  () -> assertTrue(trie.contains(TRIE)),
                  () -> assertTrue(trie.contains(TR)));
    }

    @Test
    void notContainsTest() {
        assertAll(() -> assertFalse(trie.contains(APPLE)),
                  () -> assertFalse(trie.contains("trees")));
    }

    @Test
    void testInsert() {
        assertFalse(trie.contains(APPLE));
        trie.insert(APPLE);
        assertTrue(trie.contains(APPLE));
    }

    @Test
    void testIsNotEmpty() {
        assertFalse(trie.isEmpty());
    }

    @Test
    void testIsEmpty() {
        Trie trie = new Trie();
        assertTrue(trie.isEmpty());
    }

    @Test
    void testGetAllWordsWithPrefix() {
        List<String> words = trie.getAllWordsWithPrefix(TRI);
        assertEquals(2, words.size());
        assertTrue(words.contains(TRIPLE));
        assertTrue(words.contains(TRIE));

        String triad = "triad";
        trie.insert(triad);
        words = trie.getAllWordsWithPrefix(TRI);
        assertEquals(3, words.size());
        assertTrue(words.contains(triad));
    }

    @Test
    void testGetAllWordsWithPrefixNoWords() {
        List<String> words = trie.getAllWordsWithPrefix("spring");
        assertTrue(words.isEmpty());
    }

    @Test
    void testCleanTrie() {
        assertFalse(trie.isEmpty());
        trie.clean();
        assertTrue(trie.isEmpty());
    }

}

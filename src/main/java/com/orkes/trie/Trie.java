package com.orkes.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root = new TrieNode(' '); // always init root node with space char

    private void getAllWordsRecursively(String prefix, TrieNode trieNode, List<String> words) {
        if (trieNode.isEndOfWord()) {
            words.add(prefix);
        }

        if (trieNode.getChildren().isEmpty())
            return;

        for (TrieNode child : trieNode.getChildren().values()) {
            getAllWordsRecursively(prefix + child.getCharacter(), child, words);
        }
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode(l));
        }
        current.setEndOfWord(true);
    }

    public boolean contains(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    public List<String> getAllWordsWithPrefix(String prefix) {
        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return List.of();
            }
            current = node;
        }

        List<String> result = new ArrayList<>();
        getAllWordsRecursively(prefix, current, result);

        return result;
    }

    public boolean isEmpty() {
        return root.getChildren().isEmpty();
    }

    public void clean() {
        root = new TrieNode(' ');
    }

}

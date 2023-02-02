package com.orkes.trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    final private Character ch;
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;

    TrieNode(Character ch) {
        this.ch = ch;
    }

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    Character getCharacter() {
        return this.ch;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}

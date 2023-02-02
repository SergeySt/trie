package com.orkes.trie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest(classes = Trie.class)
public class StressTrieTest {

    @Autowired
    private Trie trie;

    @Value("classpath:words_alpha.txt")
    Resource resource;

    @BeforeEach
    void setup() throws URISyntaxException, IOException, InterruptedException {
        try (Stream<String> stream = Files.lines(Paths.get(resource.getFile().getPath()))) {
			stream.forEach(s -> trie.insert(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Test
    void testOnBigSet() {
        List<String> words = trie.getAllWordsWithPrefix("aa");
        assertEquals(28, words.size());
    }

}

package dev.bamban;

import dev.bamban.utils.FrequencyMap;
import dev.bamban.utils.Tokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Profile {

  private final char TOKEN_PADDING_CHAR = '_';
  private final int NGRAM_MIN_LENGTH = 1;
  private final int NGRAM_MAX_LENGTH = 5;

  // ngram -> (pos, freq)
  private HashMap<NGram, NGramInProfileDetails> ngrams;

  Profile(String documentPath) throws IOException {
    String fileContent;
    fileContent = loadFileContent(documentPath);
    String[] tokens = Tokenizer.getTokens(fileContent);
    initializeNGrams(tokens);
  }

  private void initializeNGrams(String[] tokens) {
    Objects.requireNonNull(tokens);

    FrequencyMap<NGram> ngramFrequency = new FrequencyMap<>();

    for(String token : tokens) {
      for(int n = NGRAM_MIN_LENGTH; n <= NGRAM_MAX_LENGTH; n++) {
        String paddedToken = Tokenizer.addPaddingToToken(token, n - 1, TOKEN_PADDING_CHAR);
        for(int i = n; i <= paddedToken.length(); i++) {
          String subToken = paddedToken.substring(i - n, i);
          ngramFrequency.add(new NGram(subToken, n));
        }
      }
    }
    sortNGrams(ngramFrequency);
  }

  private String loadFileContent(String documentPath) throws IOException {
    File document = new File(documentPath);
    String fileContent;
    FileInputStream fileInputStream;
    fileInputStream = new FileInputStream(document);
    byte[] byteContent = new byte[(int) document.length()];
    fileInputStream.read(byteContent);
    fileInputStream.close();

    return new String(byteContent, StandardCharsets.UTF_8);
  }

  private void sortNGrams(FrequencyMap<NGram> ngramFrequency) {
    SortedSet<NGram> sortedEntries = ngramFrequency.getSortedFrequencySet();
    Iterator<NGram> i = sortedEntries.iterator();
    ngrams = new HashMap<>();
    int counter = 0;
    while(i.hasNext()) {
      NGram ngram = i.next();
      ngrams.put(ngram, new NGramInProfileDetails(ngramFrequency.getFreq(ngram), counter++));
    }
  }

  public int distanceTo(Profile otherProfile) {
    int distance = 0;
    Set<NGram> otherProfileNGrams = otherProfile.ngrams.keySet();

    for (NGram ngram : otherProfileNGrams) {
      NGramInProfileDetails otherProfileNGramDetails = otherProfile.ngrams.get(ngram);
      NGramInProfileDetails thisProfileNGramDetails = this.ngrams.get(ngram);
      if (thisProfileNGramDetails == null) {
        distance += this.ngrams.size();
      } else {
        distance += Math.abs(otherProfileNGramDetails.getPosition() - thisProfileNGramDetails.getPosition());
      }
    }
    return distance;
  }

}


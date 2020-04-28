package dev.bamban.utils;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

  public static String[] getTokens(String text) {
    String[] words = text.split("\\s+");
    List<String> tokens = new ArrayList<>();

    for(String word : words) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < word.length(); i++) {
        char character = word.charAt(i);
        if(Character.isLetter(character) || character == '\'') {
          sb.append(Character.toLowerCase(character));
        }
      }
      if (sb.length() != 0) {
        tokens.add(sb.toString());
      }
    }
    return tokens.toArray(new String[0]);
  }

  public static String addPaddingToToken(String token, int paddingLength, char paddingChar) {
    if (paddingLength < 0) {
      throw new IllegalArgumentException("Length of padding cannot be negative!");
    }
    else if (paddingLength < 1) {
      return token;
    }
    return paddingChar + token + String.valueOf(paddingChar).repeat(paddingLength);
  }

}


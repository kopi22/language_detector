package dev.bamban;

import dev.bamban.utils.FrequencyMap;

import java.util.Objects;

public final class NGram {

  private final int length;
  private final String content;

  NGram(String content, int length) {
    if (content.length() != length) {
      throw new IllegalArgumentException("NGram content different than specified length!");
    }

    FrequencyMap<Integer> freq = new FrequencyMap<>();

    this.length = length;
    this.content = content;
  }

  public int getLength() {
    return length;
  }

  public String getContent() {
    return content;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof  NGram) {
      return content.equals(((NGram) obj).getContent());
    } else {
      throw new IllegalArgumentException("Not an NGram!");
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, length);
  }
}

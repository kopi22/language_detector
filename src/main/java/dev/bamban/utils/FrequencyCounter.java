package dev.bamban.utils;

class FrequencyCounter implements Comparable<FrequencyCounter> {

  private int value = 1;

  public void increment() {
    ++value;
  }
  public int getValue() {
    return value;
  }

  @Override
  public int compareTo(FrequencyCounter i) {
    return value - i.value;
  }
}

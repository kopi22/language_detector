package dev.bamban;

public class NGramInProfileDetails {

  private final int frequency;
  private final int position;

  NGramInProfileDetails(int frequency, int position) {
    this.frequency = frequency;
    this.position = position;
  }

  public int getFrequency() {
    return frequency;
  }

  public int getPosition() {
    return position;
  }
}

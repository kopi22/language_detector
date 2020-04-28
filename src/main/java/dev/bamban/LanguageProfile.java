package dev.bamban;

import java.io.IOException;

public class LanguageProfile extends Profile {

  private final String name;

  LanguageProfile(String profilePath) throws IOException {
    super(profilePath);
    // TODO: REGEX NAME DETECTION
    name = profilePath.substring(profilePath.length() - 6, profilePath.length() - 4);
  }

  public String getName() {
    return name;
  }
}

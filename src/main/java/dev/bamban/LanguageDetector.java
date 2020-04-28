package dev.bamban;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LanguageDetector {

  // TODO SINGLETON

  private ArrayList<LanguageProfile> profiles = new ArrayList<>();

  LanguageDetector(String pathToProfiles) {
    File profileDirectory = new File(pathToProfiles);
    if (!(profileDirectory.exists() && profileDirectory.isDirectory())){
      return;
    }

    File[] profileFiles = profileDirectory.listFiles((dir, name) -> name.endsWith(".txt"));

    for (File profileFile : profileFiles) {
      try {
        profiles.add(new LanguageProfile(profileFile.getPath()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.print("Supported languages: ");
    profiles.forEach(profile -> System.out.print(profile.getName() + " "));
    System.out.println();
  }

  public String detectLanguage(Profile document) {
    int minDistance = Integer.MAX_VALUE;
    LanguageProfile bestMatchingProfile = null;

    Iterator<LanguageProfile> i = profiles.iterator();

    while (i.hasNext()) {
      LanguageProfile profile = i.next();
      int distance = profile.distanceTo(document);
      if (distance < minDistance) {
        minDistance = distance;
        bestMatchingProfile = profile;
      }
    }
    return bestMatchingProfile.getName();
  }
}


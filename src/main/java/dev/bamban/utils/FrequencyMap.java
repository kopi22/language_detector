package dev.bamban.utils;

import dev.bamban.NGram;

import java.util.*;

public class FrequencyMap<T> {

  private Map<T, FrequencyCounter> frequencyMap;

  FrequencyMap() {
    frequencyMap = new HashMap<>();
  }

  FrequencyMap(Map<T, FrequencyCounter> mapToCopy) {
    frequencyMap = new HashMap<>(mapToCopy);
  }

  public void add(T element) {
    FrequencyCounter count = frequencyMap.get(element);
    if(count == null) {
      frequencyMap.put(element, new FrequencyCounter());
    } else {
      count.increment();
    }
  }

  public int getFreq(T key) {
    return frequencyMap.get(key).getValue();
  }

  public SortedSet<T> getSortedFrequencySet() {
    SortedSet<T> sortedEntries = new TreeSet<>(
            new Comparator<T>() {
              @Override public int compare(T key1, T key2) {
                int res = frequencyMap.get(key2).compareTo(frequencyMap.get(key1));
                // TODO: CHECKOUT WHAT'S IS GOING ON, WHY NOT 0?
                // MAYBE LAMBDA?
                return res != 0 ? res : 1;
              }
            }
    );
    sortedEntries.addAll(frequencyMap.keySet());
    return sortedEntries;
  }
}


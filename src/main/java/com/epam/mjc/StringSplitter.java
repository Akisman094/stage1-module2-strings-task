package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result;
        String regex = "[";
        for(int i = 0; i < delimiters.size(); i++) {
            regex += delimiters.toArray()[i];
            }
        regex += "]+";
        result = Stream.of(source.split(regex)).filter(s -> s.trim().length() > 0).collect(Collectors.toList());
        return result;
    }
}

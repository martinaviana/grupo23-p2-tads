package uy.edu.um.prog2.tad.f1;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class NameTokenizer {
    private Set<String> discardedNames = new HashSet<>();

    public String[] tokenize(String fullName) {
        return Arrays.stream(fullName.split(" "))
                .filter(name -> !discardedNames.contains(name))
                .collect(Collectors.toList()).toArray(new String[] {});
    }
}

package com.ook.ookChat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ook {

    private static final Map<String, String> cipher = Stream.of(new String[][]{
            {"0", "OOOOO"},
            {"1", "oOOOO"},
            {"2", "ooOOO"},
            {"3", "oooOO"},
            {"4", "ooooO"},
            {"5", "ooooo"},
            {"6", "Ooooo"},
            {"7", "OOooo"},
            {"8", "OOOoo"},
            {"9", "OOOOo"},
            {"a", "oO"},
            {"b", "Oooo"},
            {"c", "OoOo"},
            {"d", "Ooo"},
            {"e", "o"},
            {"f", "ooOo"},
            {"g", "OOo"},
            {"h", "oooo"},
            {"i", "oo"},
            {"j", "oOOO"},
            {"k", "OoO"},
            {"l", "oOoo"},
            {"m", "OO"},
            {"n", "Oo"},
            {"o", "OOO"},
            {"p", "oOOo"},
            {"q", "OOoO"},
            {"r", "oOo"},
            {"s", "ooo"},
            {"t", "O"},
            {"u", "ooO"},
            {"v", "oooO"},
            {"w", "oOO"},
            {"x", "OooO"},
            {"y", "OoOO"},
            {"z", "OOoo"}
    }).collect(Collectors.collectingAndThen(
            Collectors.toMap(data -> data[0], data -> data[1]),
            Collections::unmodifiableMap));

    public static String encrypt(String data) {
        StringBuilder builder = new StringBuilder();

        String[] enc = data.toLowerCase().split(" ");
        for (String words : enc) {
            Arrays.stream(words.split("")).forEach(a -> {
                if (cipher.containsKey(a)) {
                    builder.append(cipher.get(a));
                    builder.append("0");
                } else {
                    builder.append(a);
                }
            });
            builder.append("k ");
        }
        return builder.toString();
    }
}

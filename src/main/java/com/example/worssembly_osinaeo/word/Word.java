package com.example.worssembly_osinaeo.word;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Word {
    public String word = "";

    public String type = "";

    public void setType(String type) {
        this.type = type;
    }

    public String assemble() {
        String[] vowels = {"a", "e", "i", "o", "u"};
        String[] longv = {"ā", "ē", "ī", "ō", "ū"};
        String[] allowedToRepeat = {"c", "s", "m", "n", "b" ,"p", "r", "t", "z", "l", "f"};
        String previous = "";
        boolean repeated = false;
        boolean upperCase = false;
        String[] str = new String[40];
        int consonantCounter = 0;

        Random rnd = new Random();
        int limit;
        int min = 2;
        if(!Objects.equals(this.type, "Preposition")) {
            int generated = rnd.nextInt(1,11);

            //determines the length of the word
            if(generated <= 5) {
                limit = rnd.nextInt(min, 6);
            } else if (generated <= 8) {
                limit = rnd.nextInt(6, 9);
            } else if (generated == 9) {
                limit = rnd.nextInt(9, 12);
            } else {
                limit = rnd.nextInt(12, 15);
            }
        } else {
            limit = rnd.nextInt(min, 4);
        }

        //creates the word
        for(int i = 0; i < limit; i++) {
            String character = "";

            //assuring pronounceable sound clusters(new version)
            if(i == 0 || Arrays.asList(vowels).contains(previous) || Arrays.asList(longv).contains(previous)) {
                //checks for unneeded letters
                do {
                    character = String.valueOf((char) rnd.nextInt(97, 123));
                } while (Objects.equals(character, String.valueOf((char) 113)) || Objects.equals(character, String.valueOf((char) 119))
                        || Objects.equals(character, String.valueOf((char) 121)) || Objects.equals(character, String.valueOf((char) 107)));

            } else {
                //chance to add a vowel after a consonant
                int nr;
                if(consonantCounter > 0 && consonantCounter < 3 ) {
                    int vwl = rnd.nextInt(1, 11);
                    int chance = consonantCounter < 2 ? 6 : 8;
                    if (vwl <= chance) {
                        nr = rnd.nextInt(vowels.length);
                        character = vowels[nr];
                        consonantCounter = 0;
                    } else if (vwl == chance + 1 && Arrays.asList(allowedToRepeat).contains(previous)) {
                        character = previous;
                    } else {
                        nr = rnd.nextInt(str.length);
                        character = str[nr];
                    }

                } else if (consonantCounter == 3) {
                    nr = rnd.nextInt(vowels.length);
                    character = vowels[nr];
                    consonantCounter = 0;
                }

            }

            //chance to turn vowel into long vowel
            if(Arrays.asList(vowels).contains(character)) {
                int nr = rnd.nextInt(1, 11);

                if(nr > 5) {
                    character = switch (character) {
                        case "a" -> "ā";
                        case "e" -> "ē";
                        case "i" -> "ī";
                        case "o" -> "ō";
                        case "u" -> "ū";
                        default -> character;
                    };
                }
            }

            //checking for repetition
            if(character.equals(previous) ) {
                if(Arrays.asList(allowedToRepeat).contains(character) && !repeated) {
                    repeated = true;
                } else {
                    i--; continue;
                }
            } else if (repeated) {
                repeated = false;
            }


            //creating the list of allowed next sounds
            if(!Arrays.asList(vowels).contains(character) || !Arrays.asList(longv).contains(character)) {

                if(Arrays.asList(vowels).contains(previous) || Arrays.asList(longv).contains(previous)) {
                    str = switch (character) {
                        case "b" -> new String[]{"c", "s", "m", "n", "r", "t", "d", "l"};
                        case "c" -> new String[]{"s", "m", "n", "r", "t", "l"};
                        case "d" -> new String[]{"r", "l", "v"};
                        case "f" -> new String[]{"r", "l", "g", "d", "s", "c", "b", "n", "m", "t", "p"};
                        case "g" -> new String[]{"r", "l", "d", "s", "b", "n", "m", "v", "z", "t", "p"};
                        case "h" -> new String[]{"r", "l", "s", "v"};
                        case "j" -> new String[]{"r", "d"};
                        case "l" -> new String[]{"l", "g", "f", "s", "z", "c", "b", "t", "d", "p", "r", "m", "n", "v"};
                        case "m" -> new String[]{"n", "d", "f", "p", "r", "t", "s", "g"};
                        case "n" -> new String[]{"m", "d", "f", "p", "r", "t", "s", "g", "h"};
                        case "p" -> new String[]{"m", "n", "d", "f", "r", "t", "s", "g", "c", "z"};
                        case "r" -> new String[]{"m", "n", "d", "f", "p", "t", "s", "g", "c", "z", "v", "g", "j", "l"};
                        case "t" -> new String[]{"m", "n", "f", "r", "s"};
                        case "s" -> new String[]{"m", "n", "f", "t", "d", "c", "l", "p", "g"};
                        case "v" -> new String[]{"r", "l"};
                        case "x" -> new String[]{"a", "e", "i", "o", "u"};
                        case "z" -> new String[]{"r", "t", "p", "b", "n", "m", "g", "f", "v"};
                        default -> new String[]{};
                    };

                } else {
                    str = switch (character) {
                        case "b" -> new String[]{"r", "l"};
                        case "c" -> new String[]{"s", "n", "r", "l"};
                        case "d" -> new String[]{"r"};
                        case "f" -> new String[]{"r", "l", "n", "t"};
                        case "g" -> new String[]{"r", "l", "n", "m", "z"};
                        case "h" -> new String[]{"r", "l", "s"};
                        case "j" -> new String[]{"r", "d"};
                        case "l" -> new String[]{"n"};
                        case "m" -> new String[]{"n", "r", "s"};
                        case "n" -> new String[]{"l"};
                        case "p" -> new String[]{"n", "r", "s", "z"};
                        case "r" -> new String[]{"m"};
                        case "t" -> new String[]{"m", "n", "f", "r", "s"};
                        case "s" -> new String[]{"n", "t", "l"};
                        case "v" -> new String[]{"r", "l", "m", "n"};
                        case "x" -> new String[]{"a", "e", "i", "o", "u"};
                        case "z" -> new String[]{"r", "t", "n", "m"};
                        default -> new String[]{};
                    };
                }

            }

            if(!Arrays.asList(vowels).contains(character) && !Arrays.asList(longv).contains(character)) consonantCounter++; else consonantCounter = 0;

            previous = character;
            character = !upperCase ? character.toUpperCase() : character;
            upperCase = true;
            this.word += character;

        }
        return this.word;
    }
}

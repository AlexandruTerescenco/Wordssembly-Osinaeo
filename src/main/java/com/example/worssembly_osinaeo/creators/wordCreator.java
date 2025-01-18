package com.example.worssembly_osinaeo.creators;

import com.example.worssembly_osinaeo.word.Word;

public class wordCreator extends Creator{
    @Override
    public Word createObject() {
        return new Word();
    }
}

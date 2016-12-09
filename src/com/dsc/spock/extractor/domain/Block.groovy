package com.dsc.spock.extractor.domain

enum Block {
    EXPECT,
    CLEANUP,
    WHEN,
    THEN,
    SETUP,
    GIVEN,
    WHERE,
    AND;

    String capitalized() {
        "${name().charAt(0)}${name().substring(1).toLowerCase()}"
    }
}

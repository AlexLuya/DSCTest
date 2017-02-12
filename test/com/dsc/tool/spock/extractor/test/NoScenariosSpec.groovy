package com.dsc.tool.spock.extractor.test

import spock.lang.Specification

class NoScenariosSpec extends Specification {
    String test() {
        ""
    }

    int size(int t) {
        t + 1
    }
}
package com.dsc.spock.extractor

import org.codehaus.groovy.antlr.parser.GroovyRecognizer

import groovyjarjarantlr.TokenStream

class MyGroovyRecognizer extends GroovyRecognizer {
    MyGroovyRecognizer(TokenStream lexer) {
        super(lexer)
    }

    @Override
    void addWarning(String warning, String solution) {

    }
}

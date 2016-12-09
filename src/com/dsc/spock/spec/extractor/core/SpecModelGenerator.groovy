package com.dsc.spock.spec.extractor.core

import com.dsc.spock.spec.extractor.core.domain.Spec
import groovyjarjarantlr.collections.AST

class SpecModelGenerator {
    static List<Spec> generateSpec(File testFile, ClassLoader classLoader) {
        generateSpec(testFile.text, classLoader)
    }

    static List<Spec> generateSpec(String code, ClassLoader classLoader) {
        AST ast = CodeParser.createAST(code)
        return new SpecParser(classLoader).getSpecs(ast)
    }


}

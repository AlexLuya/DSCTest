package com.dsc.tool.spock.extractor

import org.codehaus.groovy.antlr.parser.GroovyLexer

import groovy.transform.PackageScope
import groovyjarjarantlr.collections.AST

@PackageScope
class CodeParser {
	static AST createAST(String code) {
		new MyGroovyRecognizer(
				new GroovyLexer(
				new StringReader(code)).plumb()).with {
					p ->
					p.compilationUnit()
					p.AST
				}
	}
}

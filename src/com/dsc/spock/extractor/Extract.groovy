package com.dsc.spock.extractor

import groovyjarjarantlr.collections.AST

import com.dsc.spock.extractor.domain.Spec
import com.dsc.util.Util

class Extract {
	static List<Spec> specsOf(File file, ClassLoader classLoader) {
		AST ast = CodeParser.createAST(file.getText('UTF-8'))
		return new SpecParser(classLoader).getSpecs(ast)
	}

	static List<Spec> specsOf(Class<?> clz) {

		return specsOf(new File(filePath(clz)),clz.getClassLoader())
	}

	static List<String> specNamesOf(Class<?> clz){
		List<Spec> specs=specsOf(clz)

		List<String> names=[]

		for(Spec spec:specs){
			names.add(spec.name)
		}

		return names
	}

	static void printSpecNamesOf(String code, ClassLoader classLoader){

		for (String name in specNamesOf(code,classLoader)) {
			print name
			print "\n"
		}
	}

	static String filePath(Class<?> clz){

		File fileInSrcDir = possibleFilePath(clz,"src")

		if (fileInSrcDir.exists()) {
			return fileInSrcDir.getAbsolutePath()
		}

		File fileInTestDir = possibleFilePath(clz,"test")

		if (!fileInTestDir.exists()) {
			throw new RuntimeException(Util.wrap("neither:"+fileInSrcDir.getAbsolutePath()+" nor "+fileInTestDir.getAbsolutePath()+" existed"))
		}

		return fileInTestDir.getAbsolutePath()

	}

	static File possibleFilePath(Class<?> clz,String dir){
		//get class file dir,such as,/path/to/project/bin/,and remove bin/
		String projDir = new File(clz.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()

		String path= projDir+File.separator+dir+File.separator+clz.getCanonicalName().replaceAll("\\.",File.separator)+".groovy"

		return new File(path)
	}
}

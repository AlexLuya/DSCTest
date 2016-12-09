package com.dsc.spock.spec.extractor.mavenplugin;

import com.dsc.spock.spec.extractor.core.SpecModelGenerator;
import com.dsc.spock.spec.extractor.core.domain.Spec;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class SpecificationModelGeneratorWrapper {
    private final Log log;

    public SpecificationModelGeneratorWrapper(Log log) {
        this.log = log;
    }

    List<Spec> generateSpecificationModel(ClassLoader contextClassLoader, List<File> spockFiles) {
        List<Spec> specs = new ArrayList<>();
        for (File file : spockFiles) {
            specs.addAll(SpecModelGenerator.generateSpec(file, contextClassLoader));
            log.info("Generated specification model for file " + file.getAbsolutePath());
        }
        return specs;
    }
}

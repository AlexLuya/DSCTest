package com.dsc.spock.spec.extractor.core.domain

import groovy.transform.Immutable

@Immutable
class Statement {
    Block block
    String description
}

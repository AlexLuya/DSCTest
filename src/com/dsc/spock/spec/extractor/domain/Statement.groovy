package com.dsc.spock.spec.extractor.domain

import groovy.transform.Immutable

@Immutable
class Statement {
    Block block
    String description
}

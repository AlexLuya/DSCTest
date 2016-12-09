package com.dsc.spock.extractor.domain

import groovy.transform.Immutable

@Immutable
class Statement {
    Block block
    String description
}

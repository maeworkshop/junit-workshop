package com.maemresen.it.tc.basics.shared.container.cases.global;

import org.junit.jupiter.api.Test;

class SharedContainerComparisonChild2Test extends AbstractBaseGlobalSharedContainerTest {

    @Test
    void child2SpecificTest() {
        assertNotNewContainerCreated();
    }
}

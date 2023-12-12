package com.maemresen.it.tc.basics.shared.container.cases.global;

import org.junit.jupiter.api.Test;

class SharedContainerComparisonChild1Test extends AbstractBaseGlobalSharedContainerTest {
    @Test
    void child1SpecificTest() {
        assertNotNewContainerCreated();
    }
}
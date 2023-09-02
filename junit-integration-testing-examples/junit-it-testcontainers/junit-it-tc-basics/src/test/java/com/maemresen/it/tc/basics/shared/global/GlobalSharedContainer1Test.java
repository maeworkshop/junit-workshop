package com.maemresen.it.tc.basics.shared.global;

import org.junit.jupiter.api.Test;

class GlobalSharedContainer1Test extends AbstractBaseGlobalSharedContainerTest {

    @Test
    void test1() {
        assertEqualsInitialGlobalContainerId();
    }
}

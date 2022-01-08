/**
 * DO NOT EDIT THIS FILE!
 * This file is auto-generated by executing `gradle :prism4j-languages:regenerateTests` and should not be edited by you!
 */

package io.noties.prism4j.languages;

import io.noties.prism4j.DefaultGrammarLocator;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.TestUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

@RunWith(Parameterized.class)
public class TestMakefile {

    private final String file;
    private Prism4j prism4j;

    public TestMakefile(@NotNull String file) {
        this.file = file;
    }

    @Parameterized.Parameters(name = "{0}")
    @NotNull
    public static Collection<Object> parameters() {
        return TestUtils.testFiles("makefile");
    }

    @Before
    public void before() {
        prism4j = new Prism4j(new DefaultGrammarLocator());
    }

    @Test
    public void test() {
        final TestUtils.Case c = TestUtils.readCase(file);
        TestUtils.assertCase(c, prism4j.tokenize(c.input, prism4j.grammar("Makefile")));
    }

    @Test
    public void testCloned() {
        final TestUtils.Case c = TestUtils.readCase(file);
        final Prism4j.Grammar grammar = GrammarUtils.clone(prism4j.grammar("Makefile"));
        TestUtils.assertCase(c, prism4j.tokenize(c.input, grammar));
    }
}

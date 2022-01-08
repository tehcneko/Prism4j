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
import java.util.Objects;

@RunWith(Parameterized.class)
public class TestMarkup {

    private final String file;
    private Prism4j prism4j;

    public TestMarkup(@NotNull String file) {
        this.file = file;
    }

    @Parameterized.Parameters(name = "{0}")
    @NotNull
    public static Collection<Object> parameters() {
        return TestUtils.testFiles("markup");
    }

    @Before
    public void before() {
        prism4j = new Prism4j(new DefaultGrammarLocator());
    }

    @Test
    public void test() {
        final TestUtils.Case c = TestUtils.readCase(file);
        TestUtils.assertCase(c, prism4j.tokenize(c.input, Objects.requireNonNull(prism4j.grammar("markup"))));
    }

    @Test
    public void testClone() {
        final TestUtils.Case c = TestUtils.readCase(file);
        final Prism4j.Grammar grammar = GrammarUtils.clone(Objects.requireNonNull(prism4j.grammar("markup")));
        TestUtils.assertCase(c, prism4j.tokenize(c.input, grammar));
    }
}

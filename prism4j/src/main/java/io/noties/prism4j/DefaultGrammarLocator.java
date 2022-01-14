package io.noties.prism4j;

import io.noties.prism4j.languages.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DefaultGrammarLocator implements GrammarLocator {

    @SuppressWarnings("ConstantConditions")
    private static final Grammar NULL =
            new Grammar("", new ArrayList<>()) {
                @NotNull
                @Override
                public String name() {
                    return null;
                }

                @NotNull
                @Override
                public List<Token> tokens() {
                    return null;
                }
            };

    private final HashMap<String, Grammar> cache = new HashMap<>(3);

    @Nullable
    @Override
    public Grammar grammar(@NotNull Prism4j prism4j, @NotNull String language) {

        final String name = realLanguageName(language);

        Grammar grammar = cache.get(name);
        if (grammar != null) {
            if (NULL == grammar) {
                grammar = null;
            }
            return grammar;
        }

        grammar = obtainGrammar(prism4j, name);
        if (grammar == null) {
            cache.put(name, NULL);
        } else {
            cache.put(name, grammar);
            Grammar grammarExtended = triggerModify(prism4j, name);
            if (grammarExtended != null) {
                cache.put(name, grammarExtended);
                grammar = grammarExtended;
            }
        }

        return grammar;
    }

    @NotNull
    protected String realLanguageName(@NotNull String name) {
        final String out;
        switch (name) {
            case "dotnet":
                out = "csharp";
                break;
            case "tex":
            case "context":
                out = "latex";
                break;
            case "kt":
            case "kts":
                out = "kotlin";
                break;
            case "xml":
            case "html":
            case "mathml":
            case "svg":
                out = "markup";
                break;
            case "webmanifest":
                out = "json";
                break;
            case "js":
                out = "javascript";
                break;
            default:
                out = name;
        }
        return out;
    }

    @Nullable
    protected Grammar obtainGrammar(@NotNull Prism4j prism4j, @NotNull String name) {
        final Grammar grammar;
        switch (name) {
            case "basic":
                grammar = Prism_basic.create(prism4j);
                break;
            case "brainfuck":
                grammar = Prism_brainfuck.create(prism4j);
                break;
            case "c":
                grammar = Prism_c.create(prism4j);
                break;
            case "clike":
                grammar = Prism_clike.create(prism4j);
                break;
            case "clojure":
                grammar = Prism_clojure.create(prism4j);
                break;
            case "cpp":
                grammar = Prism_cpp.create(prism4j);
                break;
            case "csharp":
                grammar = Prism_csharp.create(prism4j);
                break;
            case "css":
                grammar = Prism_css.create(prism4j);
                break;
            case "css-extras":
                grammar = Prism_css_extras.create(prism4j);
                break;
            case "dart":
                grammar = Prism_dart.create(prism4j);
                break;
            case "git":
                grammar = Prism_git.create(prism4j);
                break;
            case "go":
                grammar = Prism_go.create(prism4j);
                break;
            case "groovy":
                grammar = Prism_groovy.create(prism4j);
                break;
            case "java":
                grammar = Prism_java.create(prism4j);
                break;
            case "javascript":
                grammar = Prism_javascript.create(prism4j);
                break;
            case "json":
                grammar = Prism_json.create(prism4j);
                break;
            case "jsonp":
                grammar = Prism_jsonp.create(prism4j);
                break;
            case "kotlin":
                grammar = Prism_kotlin.create(prism4j);
                break;
            case "latex":
                grammar = Prism_latex.create(prism4j);
                break;
            case "makefile":
                grammar = Prism_makefile.create(prism4j);
                break;
            case "markdown":
                grammar = Prism_markdown.create(prism4j);
                break;
            case "markup":
                grammar = Prism_markup.create(prism4j);
                break;
            case "python":
                grammar = Prism_python.create(prism4j);
                break;
            case "scala":
                grammar = Prism_scala.create(prism4j);
                break;
            case "sql":
                grammar = Prism_sql.create(prism4j);
                break;
            case "swift":
                grammar = Prism_swift.create(prism4j);
                break;
            case "yaml":
                grammar = Prism_yaml.create(prism4j);
                break;
            default:
                grammar = null;
        }
        return grammar;
    }

    protected Grammar triggerModify(@NotNull Prism4j prism4j, @NotNull String name) {
        Grammar grammar = null;
        switch (name) {
            case "markup":
                prism4j.grammar("css");
                prism4j.grammar("javascript");
                break;
            case "css":
                grammar = prism4j.grammar("css-extras");
                break;
        }
        return grammar;
    }

    @Override
    @NotNull
    public HashSet<String> languages() {
        final HashSet<String> set = new HashSet<>(27);
        set.add("basic");
        set.add("brainfuck");
        set.add("c");
        set.add("clike");
        set.add("clojure");
        set.add("cpp");
        set.add("csharp");
        set.add("css");
        set.add("css-extras");
        set.add("dart");
        set.add("git");
        set.add("go");
        set.add("groovy");
        set.add("java");
        set.add("javascript");
        set.add("json");
        set.add("jsonp");
        set.add("kotlin");
        set.add("latex");
        set.add("makefile");
        set.add("markdown");
        set.add("markup");
        set.add("python");
        set.add("scala");
        set.add("sql");
        set.add("swift");
        set.add("yaml");
        return set;
    }
}

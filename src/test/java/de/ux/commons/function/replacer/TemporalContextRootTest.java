package de.ux.commons.function.replacer;

import de.ux.commons.function.parser.TemporalContextExpressionParser;
import de.ux.commons.function.resource.UrlResourceLocator;
import de.ux.commons.function.resource.UrlResourceReader;
import org.junit.Test;

import java.net.URL;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TemporalContextRootTest {

    @Test
    public void test() throws Exception {
        ReplacementDefinition replacementDefinition = new ReplacementDefinition(new ReplacementDefinition(Pattern.compile("\\$\\{(.+?)}"), 1), new TemporalContextExpressionParser());

        MatchReplacer matchReplacer = new MatchReplacer(replacementDefinition);

        String text = "this is some text..... \n tomorrow is the '${today().plusDays(1).format(as('uuuuMMdd'))}'\ndswrset\n and today is the ${today().format(as('uuuuMMdd'))}";
        System.out.println(matchReplacer.apply(text));

        URL resource = this.getClass().getResource("/test.raw");
        System.out.println(resource);

        Consumer<String> consumer = new UrlResourceLocator().andThen(new UrlResourceReader(UTF_8)).andThen(matchReplacer).consume(System.out::println);
        consumer.accept("test.raw");
    }

    @Test
    public void test2() throws Exception {
        MatchReplacer matchReplacer = new MatchReplacer(Pattern.compile("<Test>"), (match) -> "'$0'");
        System.out.println(matchReplacer.apply("Hello World <Test> :)"));
    }
}
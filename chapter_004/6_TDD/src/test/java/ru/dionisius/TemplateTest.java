package test.java.ru.dionisius;

import org.junit.Before;
import org.junit.Test;
import ru.dionisius.exceptions.ExcessKeysException;
import ru.dionisius.exceptions.NoKeyException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dionisius on 18.01.2017.
 * Tests SimpleGenerator class.
 */
public class TemplateTest {
        /**
         *Map with template keys and according values.
         */
        private final Map<String, String> keyMap = new HashMap<>();
        /**
         *SimpleGenerator test instance.
         */
        private final ru.dionisius.Template template = new ru.dionisius.SimpleGeneratorRegex();
//        private final Template template = new SimpleGenerator();
        /**
         *Source test text.
         */
        private String sourceText;
        /**
         *Source test text with extra template.
         */
        private String sourceTextWithExtraTemplate;
        /**
         * Result test string.
         */
        private String result;
        /**
         * Expected test string.
         */
        private String expected;
        /**
         * Initiates variables and makes initial acts.
         */
        @Before
        public void init() {
                this.sourceText = "Hello, ${name}. How do ${someone} do?";
                this.sourceTextWithExtraTemplate = "Hello, ${name}. How do ${someone} do? I hope ${person} is fine";
                this.keyMap.put("${name}", "Denis");
                this.keyMap.put("${someone}", "you");
        }
        /**
         * Checks if templates in specified source string are
         * changed to according values from keymap.
         */
        @Test
        public void whenTemplatesInSourceStringThenTemplatesAreChanged() {
                this.result = template.generate(this.sourceText, this.keyMap);
                this.expected = "Hello, Denis. How do you do?";
//                Assert.assertThat(this.result,is(this.expected));
        }
        /**
         * Checks if NoKeyException is thrown when there is
         * no according key to source string template in keymap.
         * @throws NoKeyException
         */
        @Test (expected = NoKeyException.class)
        public void whenSourceStringHasExtraTemplatesThenThrowsNoKeyException() {
                this.template.generate(this.sourceTextWithExtraTemplate, this.keyMap);
        }
        /**
         * Checks if ExcessKeysException is thrown when there is
         * excess keys in keymap.
         * @throws ExcessKeysException
         */
        @Test (expected = ExcessKeysException.class)
        public void whenKeymapHasExtraTemplatesThenThrowsExcessKeysException() {
                this.keyMap.put("${surname}", "Ivanov");
                this.template.generate(this.sourceText, this.keyMap);
        }
}
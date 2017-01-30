package ru.dionisius;

import ru.dionisius.exceptions.ExcessKeysException;
import ru.dionisius.exceptions.NoKeyException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dionisius on 30.01.2017.
 */
public class SimpleGeneratorRegex implements Template {
    @Override
    public String generate(String template, Map<String, String> pairs) {
        String result = template;
        String name;
        String subject;
        Set keys = pairs.keySet();
        Set <String> stringPatterns = new HashSet<>();
        Pattern pattern = Pattern.compile("\\$\\{[a-z]+\\}");
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            stringPatterns.add(template.substring(matcher.start(), matcher.end()));
        }
        if (!stringPatterns.containsAll(keys)) {
            throw new ExcessKeysException("Excess patterns in specified map!");
        }
        if (!keys.containsAll(stringPatterns)) {
            throw new NoKeyException("Excess patterns in specified string!");
        }
        for (Map.Entry<String, String> pair: pairs.entrySet()) {
            name = pair.getKey();
            subject = pair.getValue();
            result = this.replaceAllKeys(result, name, subject);
        }
        return result;
    }

    public String replaceAllKeys(String string, String regex, String replacement) {
        String reg = "\\$\\{" + regex.substring(2, regex.length() - 1) + "\\}";
        return Pattern.compile(reg).matcher(string).replaceAll(replacement);
    }
}

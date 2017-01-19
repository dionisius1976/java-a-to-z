package ru.dionisius;

import ru.dionisius.exceptions.ExcessKeysException;
import ru.dionisius.exceptions.NoKeyException;

import java.util.Map;

/**
 * Created by Dionisius on 18.01.2017.
 */
public class SimpleGenerator implements Template {
    @Override
    public String generate(String template, Map<String, String> pairs) {
        int templatesNumber = 0;
        String key = null;
        String temp = null;
        int templateIndex = 0;
        int resultIndex = -1;
        StringBuilder resultString = new StringBuilder();
        if (template != null) {
            for (int i = 0; i < template.length(); i++) {
                resultString.append(template.charAt(i));
                resultIndex++;
                if (template.charAt(i) == '$' && template.charAt(i + 1) == '{') {
                    templatesNumber++;
                    resultString.deleteCharAt(resultIndex);
                    resultIndex--;
                    templateIndex = i;
                    while (template.charAt(i) != '}') {
                        i++;
                    }
                    temp = template.substring(templateIndex, ++i);
                    key = pairs.get(temp);
                    if (key == null) {
                        String msg = "There is no key for template " + temp;
                        throw new NoKeyException(msg);
                    }
                    resultString.append(key);
                    resultIndex = resultIndex + key.length();
                    i--;
                }
            }
            if (templatesNumber != pairs.size()) {
                throw new ExcessKeysException("There is an excess keys in keymap!");
            }
        }
        return resultString.toString();
    }
}

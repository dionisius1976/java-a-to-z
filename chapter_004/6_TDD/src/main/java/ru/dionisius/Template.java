package ru.dionisius;

import java.util.Map;

/**
 * Created by Dionisius on 18.01.2017.
 * Interface for all Template classes.
 */
public interface Template {

    /**
     * Generate method.
     * Replaces all templates in specified string
     * to according this template key from keymap.
     * @param template specified string.
     * @param pairs keymap.
     * @return specified string with replaced templates.
     */
    String generate(String template, Map<String, String> pairs);
}

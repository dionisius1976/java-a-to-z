package ru.dionisius;

/**
 * Created by Dionisius on 18.01.2017.
 */
public interface Template {
    String generate(String template, Object[] data);
}

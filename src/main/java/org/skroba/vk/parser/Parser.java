package org.skroba.vk.parser;

public interface Parser<T> {
    T parse(String data) throws ParseException;
}

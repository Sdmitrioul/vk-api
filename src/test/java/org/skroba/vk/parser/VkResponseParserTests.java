package org.skroba.vk.parser;

import org.junit.jupiter.api.Test;
import org.skroba.vk.util.VkResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VkResponseParserTests {
    private static final String BAD_RESPONSE = "{\"error\": {}}";
    private static final String BAD_FORMED_RESPONSE = "{\"response\":{\"items\":[{\"date\":\"dksfm\"},{}]}}";
    private static final String GOOD_RESPONSE = "{\"response\":{\"items\":[{\"date\":12234359043},{\"date\":43243240423}]}}";
    
    private final Parser<VkResponse> parser = new VkResponseParser();
    
    @Test
    public void parseResponse() throws ParseException {
        assertEquals(List.of(12234359043L, 43243240423L), parser.parse(GOOD_RESPONSE).getPostTimes());
    }
    
    @Test
    public void parseErrorResponse() {
        assertThrows(ParseException.class, () -> parser.parse(BAD_RESPONSE));
    }
    
    @Test
    public void parseBadFormedResponse() {
        assertThrows(ParseException.class, () -> parser.parse(BAD_FORMED_RESPONSE));
    }
}

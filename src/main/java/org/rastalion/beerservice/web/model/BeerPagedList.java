package org.rastalion.beerservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPagedList extends PageImpl<BeerDto> {

    /*
    We're using the @JsonCreator annotation here, and showing how this can be made.

    So we are setting up the more properties to do property binding by name,
    and then we are going through the BeerPagedList.

    You can see we have 10 properties that we are binding to,
    and then inside the constructor, we are calling out the super constructor.

    So this is setting up a pretty complex JSON Object, we are setting up a JsonCreator to take
    JSON of a fairly complex object because of the paging.

    You can see all the elements there that are on that implementation.
    And then we are gonna bind that and create a fairly complex POJO of that.

    So this is how you can do some more complex mappings...
     */

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BeerPagedList(@JsonProperty("content") List<BeerDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") long totalElements,
                         @JsonProperty("pageable") JsonNode pageable,
                         @JsonProperty("last") boolean last,
                         @JsonProperty("totalPages") int totalPages,
                         @JsonProperty("sort") JsonNode sort,
                         @JsonProperty("first") boolean first,
                         @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }
}

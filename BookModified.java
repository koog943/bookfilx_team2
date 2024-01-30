package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BookModified extends AbstractEvent {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String index;
    private String introducing;

    public BookModified(Book aggregate) {
        super(aggregate);
    }

    public BookModified() {
        super();
    }
}
//>>> DDD / Domain Event

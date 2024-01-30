package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.BookApplication;
import untitled.domain.BookAdded;
import untitled.domain.BookDeleted;
import untitled.domain.BookModified;

@Entity
@Table(name = "Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String index;

    private String introducing;

    @PostPersist
    public void onPostPersist() {
        BookAdded bookAdded = new BookAdded(this);
        bookAdded.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        BookModified bookModified = new BookModified(this);
        bookModified.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        BookDeleted bookDeleted = new BookDeleted(this);
        bookDeleted.publishAfterCommit();
    }

    public static BookRepository repository() {
        BookRepository bookRepository = BookApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    public static void update(BookAdded bookAdded) {
        //implement business logic here:

        /** Example 1:  new item */
        Book book = new Book();
        book.setId(bookAdded.getId());
        book.setTitle(bookAdded.getTitle());
        book.setAuthor(bookAdded.getAuthor());
        book.setIndex(bookAdded.getIndex());
        book.setIntroducing(bookAdded.getIntroducing());
        repository().save(book);
    }

    public static void update(BookDeleted bookDeleted) {
        
        repository().findById(bookDeleted.getId()).ifPresent(book->{
            
            repository().deleteById(book.getId()); // do something
            repository().save(book);
            

         });
    }

    public static void update(BookModified bookModified) {
        //implement business logic here:

        /** Example 1:  new item 
        Front front = new Front();
        repository().save(front);

        

        /** Example 2:  finding and process
        
        repository().findById(bookModified.get???()).ifPresent(front->{
            
            front // do something
            repository().save(front);


         });
        */

    }
}
//>>> DDD / Aggregate Root

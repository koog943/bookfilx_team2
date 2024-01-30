package login.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import login.BookflixLoginApplication;
import lombok.Data;

@Entity
@Data
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String bookName;

    private String bookTitle;

    private String bookAuthor;

    private String bookPublisher;

    private String bookIndex;

    private String bookIntroducing;

    private String bookSignImage;


    @PostPersist
    public void onPostPersist() {
    }

    @PrePersist
    public void onPrePersist() {}

    public static BookRepository repository() {
        BookRepository bookRepository = BookflixLoginApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

}

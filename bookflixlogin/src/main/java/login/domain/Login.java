package login.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import login.BookflixLoginApplication;
import lombok.Data;

@Entity
@Data
public class Login {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String userName;
    
    private Integer userMoney;

    private boolean userSubscribeStatus;
    
    @ElementCollection
    private List<Book> userReadBooks = new ArrayList<>();

    @ElementCollection
    private List<Book> userInterstBooks = new ArrayList<>();

    @PostPersist
    public void onPostPersist() {
    }

    @PrePersist
    public void onPrePersist() {}
    
    public void readBookAdd(Book book) {
        this.userReadBooks.add(book);
    }
    
    public static LoginRepostitory repository() {
        LoginRepostitory loginRepostitory = BookflixLoginApplication.applicationContext.getBean(
            LoginRepostitory.class
        );
        return loginRepostitory;
    }

}

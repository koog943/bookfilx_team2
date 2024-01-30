package login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import login.config.kafka.KafkaProcessor;
import login.domain.LoginRepostitory;
import login.domain.Book;
import login.domain.BookRepository;
import login.domain.Login;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class BookflixLoginApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(BookflixLoginApplication.class, args);
            addBook();
            test();
    }

    private static void test() {
        LoginRepostitory loginRepostitory = login.domain.Login.repository();
        BookRepository bookRepository = login.domain.Book.repository();

        for (int i = 1; i <= 5; i++) {
            Login login = new Login();
            login.setUserId("userId" + i);
            login.setUserMoney(i * 10000);
            if (i % 2 == 0) {
                login.setUserSubscribeStatus(false);
            } else {
                login.setUserSubscribeStatus(true);
            }
            Book book1 = bookRepository.findByBookName("setBookName" + 1);
            Book book2 = bookRepository.findByBookName("setBookName" + 2);
            Book book3 = bookRepository.findByBookName("setBookName" + 3);

            login.readBookAdd(book1);
            login.readBookAdd(book2);
            login.readBookAdd(book3);

            loginRepostitory.save(login);



            System.out.println("LoginAAAAAA id: = " + login.getId());
        }


    }

    private static void addBook() {
        BookRepository bookRepository = login.domain.Book.repository();

        for (int i = 1; i <= 5; i++) {
            Book book = new Book();
            book.setBookName("setBookName" + i);
            book.setBookTitle("setTitle" + i);
            book.setBookAuthor("setAuthor" + i);
            book.setBookPublisher("setPublisher" + i);
            book.setBookIndex("setIndex" + i);
            book.setBookIntroducing("setIntroducing" + i);
            book.setBookSignImage("setSignImage" + i);
            bookRepository.save(book);
        } 
    }

}

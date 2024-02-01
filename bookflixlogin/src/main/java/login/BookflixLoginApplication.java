package login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import login.domain.LoginRepostitory;
import login.domain.Book;
import login.domain.BookRepository;
import login.domain.Login;

@SpringBootApplication
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

        for (int i = 1; i <= 5; i++) {
            Login login = new Login();
            login.setUserId("userId" + i);
            login.setUserMoney(i * 10000);
            if (i % 2 == 0) {
                login.setUserSubscribeStatus(false);
            } else {
                login.setUserSubscribeStatus(true);
            }

            loginRepostitory.save(login);

            System.out.println("LoginAAAAAA id: = " + login.getId());
        }

    }

    private static void addBook() {
        BookRepository bookRepository = login.domain.Book.repository();

        for (int i = 1; i <= 5; i++) {
            Book book = new Book();
            book.setTitle("책타이틀" + i);
            book.setAuthor("책저자" + i);
            book.setPublisher("책출판사" + i);
            bookRepository.save(book);
        } 
    }

}

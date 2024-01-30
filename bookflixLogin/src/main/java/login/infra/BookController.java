package login.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import login.domain.Book;
import login.domain.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping("/list")
    @ResponseBody
    public Iterable<Book> bookList() {
        BookRepository bookRepository = login.domain.Book.repository();

        Iterable<Book> books = null;
        try {
            books = bookRepository.findAll();
        } catch(NullPointerException e) {
            
        }
        return books;
    }

    @GetMapping("/{bookName}")
    @ResponseBody
    public Book bookList(@PathVariable String bookName) {
        BookRepository bookRepository = login.domain.Book.repository();

        Book book = bookRepository.findByBookName(bookName);

        return book;
    }

    @GetMapping("/search")
    @ResponseBody
    public Book bookSearch(@RequestParam String word) {
        BookRepository bookRepository = login.domain.Book.repository();
        Book book = bookRepository.findByBookName(word);

        return book;
    }
    
}

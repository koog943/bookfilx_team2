package login.infra;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.IPAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import brave.http.HttpRequest;
import login.domain.Book;
import login.domain.BookRepository;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/book")
public class BookController {

    static int i = 0;

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

    @GetMapping("/login")
    public RedirectView redirectView(HttpServletRequest request, HttpServletResponse response) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000");
        String token = UUID.randomUUID().toString();
    
        Cookie cookie = new Cookie("Login_Check", token);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        this.i++;
        System.out.println("***쿠키발급" + this.i + "토큰명" + token + "******");
        return redirectView;
    }
    
}

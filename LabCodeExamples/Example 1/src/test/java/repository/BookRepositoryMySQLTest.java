package repository;

import database.DatabaseConnectionFactory;
import model.Book;
import model.builder.BookBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookRepositoryMySQLTest {

    private static BookRepository bookRepository;

    @BeforeClass
    public static void setupClass() {
        bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(
                        DatabaseConnectionFactory.getConnectionWrapper(true)
                ),
                new Cache<>()
        );
    }

    @Before
    public void cleanUp() {
        bookRepository.removeAll();
    }

    @Test
    public void findAll() {
        List<Book> books = bookRepository.findAll();
        assertEquals(books.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() {
        Book book = new BookBuilder()
                .setTitle("Title")
                .setAuthor("Author")
                .setPublishedDate(LocalDate.now())
                .build();
        bookRepository.save(book);
        bookRepository.save(book);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertEquals(books.size(), 3);
    }

    @Test
    public void findById() {

    }

    @Test
    public void save() {
        assertTrue(bookRepository.save(
                new BookBuilder()
                        .setTitle("Title")
                        .setAuthor("Author")
                        .setPublishedDate(LocalDate.now())
                        .build()
        ));
    }

    @Test
    public void removeAll() {

    }

}
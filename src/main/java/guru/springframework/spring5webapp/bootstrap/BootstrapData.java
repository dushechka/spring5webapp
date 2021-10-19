package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher oriley = new Publisher();
        oriley.setName("O'Rilley");
        oriley.setAddress("Spb, Georgia, 123123");
        publisherRepository.save(oriley);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12312321");
        saveBookToRepository(oriley, eric, ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "12346541536");
        saveBookToRepository(oriley, rod, noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books by O'Rilley: " + oriley.getBooks().size());
    }

    private void saveBookToRepository(Publisher publisher, Author author, Book book) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
        publisher.getBooks().add(book);
        book.setPublisher(publisher);
        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);
    }


}

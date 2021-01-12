package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.domain.Author;
import com.example.spring5webapp.domain.Book;
import com.example.spring5webapp.domain.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
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

            Publisher pub1 = new Publisher("SFG publishing", "NewYork street", "NewYork", "US", 124346);
            publisherRepository.save(pub1);

            Author eric = new Author("Eric", "Evans");
            Book ddd = new Book("Domain Driven Design", "12345");
            eric.getBooks().add(ddd);
            ddd.getAuthors().add(eric);
            ddd.setPublisher(pub1);
            pub1.getBooks().add(ddd);

            authorRepository.save(eric);
            bookRepository.save(ddd);
            publisherRepository.save(pub1);

            Author rod = new Author("Rod", "Johnson");
            Book noEJB = new Book("J2EE development without EJB", "456788");
            rod.getBooks().add(noEJB);
            noEJB.getAuthors().add(rod);
            noEJB.setPublisher(pub1);
            pub1.getBooks().add(noEJB);

            authorRepository.save(rod);
            bookRepository.save(noEJB);
            publisherRepository.save(pub1);

            System.out.println("Started in Bootstrap");
            System.out.println("Number of Books: " + bookRepository.count());
            System.out.println("Publisher no.of books: " + pub1.getBooks().size());
    }
}

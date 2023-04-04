package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private PostController controller;
    // Constants
    private static String API_POSTS_PATH = "/api/posts";
    private static String API_POSTS_DIGITAL_SYMBOL = "/api/posts/\\d+";
    private static String SLASH = "/";

    @Override
    // initializing dependencies
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.netology");
        controller = context.getBean(PostController.class);
    }

    // request processing
    @Override
    // a GET request come in
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.equals(API_POSTS_PATH)) {
            controller.all(resp);
            return;
        }
        if (path.matches(API_POSTS_DIGITAL_SYMBOL)) {
            // easy way
            final var id = Long.parseLong(path.substring(path.lastIndexOf(SLASH)));
            controller.getById(id, resp);
            return;
        }
        // if we don't know how to process it
        super.doGet(req, resp);
    }

    @Override
    // a POST request come in
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.equals(API_POSTS_PATH)) {
            controller.save(req.getReader(), resp);
            return;
        }
        // if we don't know how to process it
        super.doPost(req, resp);
    }

    @Override
    // a DELETE request come in
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.matches(API_POSTS_DIGITAL_SYMBOL)) {
            // easy way
            final var id = Long.parseLong(path.substring(path.lastIndexOf(SLASH)));
            controller.removeById(id, resp);
            return;
        }
        // if we don't know how to process it
        super.doDelete(req, resp);
    }

}


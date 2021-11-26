package be.ecam.ms_studenthelp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import be.ecam.ms_studenthelp.Object.Post;

@RestController
public class PostController {

    @GetMapping("/getPost")
    public Post GetPost(){
        Post post = new Post();
        post.setAuthorId("L-A");
        post.setContent("Coucou");
        return post;
    }
    
}

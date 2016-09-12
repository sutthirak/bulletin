package me.sutthirak.bulletin.controller;

import me.sutthirak.bulletin.model.Comment;
import me.sutthirak.bulletin.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "topics/{topicId}/comments", method = RequestMethod.GET)
    public List<Comment> get(@PathVariable Long topicId){
        return commentRepository.findByTopicId(topicId);
    }

    @RequestMapping(value = "topics/{topicId}/comments", method = RequestMethod.POST)
    ResponseEntity create(@RequestBody Comment comment){

        if(comment != null) {
            commentRepository.save(comment);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}

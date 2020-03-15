package me.sutthirak.bulletin.controller;

import me.sutthirak.bulletin.model.Topic;
import me.sutthirak.bulletin.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping(value = "topics", method = RequestMethod.GET)
    Iterable<Topic> get(@RequestParam(name = "p",required = false, defaultValue = "0") int page) {
        PageRequest pageRequest = new PageRequest(page,10, new Sort(Sort.Direction.DESC,"id"));
        Iterable<Topic> topics = new ArrayDeque<>();
        topics.forEach(topic -> {
            topic.setWriterPassword("");
        });
        return topics;
    }

    @RequestMapping(value = "topics/{topicId}", method = RequestMethod.GET)
    Topic get(@PathVariable Long topicId) {
        Topic topic = new Topic();
        return topic;
    }

    @RequestMapping(value = "topics", method = RequestMethod.POST)
    ResponseEntity create(@RequestBody Topic topic){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "topics/{topicId}", method = RequestMethod.PUT)
    ResponseEntity update(@RequestBody Topic topic, @PathVariable Long topicId){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "topics/{topicId}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long topicId,@RequestBody String deletePassword){
        return ResponseEntity.ok().build();
    }

}

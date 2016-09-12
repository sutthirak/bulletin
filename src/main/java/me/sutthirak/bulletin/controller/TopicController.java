package me.sutthirak.bulletin.controller;

import me.sutthirak.bulletin.model.Topic;
import me.sutthirak.bulletin.repository.TopicRepository;
import org.apache.commons.codec.digest.DigestUtils;
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

import java.util.Date;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping(value = "topics", method = RequestMethod.GET)
    Iterable<Topic> get(@RequestParam(name = "p",required = false, defaultValue = "0") int page) {
        PageRequest pageRequest = new PageRequest(page,10, new Sort(Sort.Direction.DESC,"id"));
        Iterable<Topic> topics =  topicRepository.findAll(pageRequest);
        topics.forEach(topic -> {
            topic.setWriterPassword("");
        });
        return topics;
    }

    @RequestMapping(value = "topics/{topicId}", method = RequestMethod.GET)
    Topic get(@PathVariable Long topicId) {
        Topic topic =  topicRepository.findOne(topicId);
        topic.setNumberOfView(topic.getNumberOfView() + 1);
        topicRepository.save(topic);
        topic.setWriterPassword("");
        return topic;
    }

    @RequestMapping(value = "topics", method = RequestMethod.POST)
    ResponseEntity create(@RequestBody Topic topic){

        if(topic != null) {
            topic.setNumberOfView(0L);
            topic.setCreatedDate(new Date());
            topic.setWriterPassword(DigestUtils.sha256Hex(topic.getWriterPassword()));
            topicRepository.save(topic);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @RequestMapping(value = "topics/{topicId}", method = RequestMethod.PUT)
    ResponseEntity update(@RequestBody Topic topic, @PathVariable Long topicId){

        Topic dataTopic = topicRepository.findOne(topicId);

        if(dataTopic != null) {
            if (dataTopic.getWriterPassword().equals(DigestUtils.sha256Hex(topic.getWriterPassword()))) {
                topicRepository.update(topic.getTitle(), topic.getDescription(), topicId);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();

    }

    @RequestMapping(value = "topics/{topicId}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long topicId,@RequestBody String deletePassword){

        Topic dataTopic = topicRepository.findOne(topicId);
        if(dataTopic != null) {
            if (dataTopic.getWriterPassword().equals(DigestUtils.sha256Hex(deletePassword))) {
                topicRepository.delete(topicId);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

}

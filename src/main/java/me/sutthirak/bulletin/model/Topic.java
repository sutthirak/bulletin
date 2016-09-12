package me.sutthirak.bulletin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Topic implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int writerTitle;

    @Column(nullable = false)
    private String writerEmail;

    @Column(nullable = false)
    private String writerName;

    @Column(nullable = false)
    private String writerPassword;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Long numberOfView;

    public Topic(){

    }

    public Topic(String title,
                 String description,
                 int writerTitle,
                 String writerEmail,
                 String writerName,
                 String password){

        this.title = title;
        this.description = description;
        this.writerTitle = writerTitle;
        this.writerName = writerName;
        this.writerEmail = writerEmail;
        this.writerPassword = password;
        this.createdDate = new Date();
        this.numberOfView = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWriterTitle() {
        return writerTitle;
    }

    public void setWriterTitle(int writerTitle) {
        this.writerTitle = writerTitle;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterPassword() {
        return writerPassword;
    }

    public void setWriterPassword(String writerPassword) {
        this.writerPassword = writerPassword;
    }


    public Long getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(Long numberOfView) {
        this.numberOfView = numberOfView;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }
}

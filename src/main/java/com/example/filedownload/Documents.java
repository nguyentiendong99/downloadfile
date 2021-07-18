package com.example.filedownload;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private long size;
    @Column(name = "upload_time")
    private Date uploadTime;
    private byte[] content;

    public Documents(Integer id, String name, long size, Date uploadTime, byte[] content) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.uploadTime = uploadTime;
        this.content = content;
    }

    public Documents() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

package com.fresco.scienceExhibition.model;

import jakarta.persistence.*;

@Entity
public class ExhibitionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private final String eventName="TCH 25th Foundation Day - Silver Jubilee Celebrations";

    private String studentName;

    @Column(unique = true)
    private Long idCardNumber;

    private String topic;

    private String guideMember;


    //constructors

    public ExhibitionModel() {
    }

    public ExhibitionModel(int id, String studentName, Long idCardNumber, String topic, String guideMember) {
        this.id = id;
        this.studentName = studentName;
        this.idCardNumber = idCardNumber;
        this.topic = topic;
        this.guideMember = guideMember;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Long idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGuideMember() {
        return guideMember;
    }

    public void setGuideMember(String guideMember) {
        this.guideMember = guideMember;
    }


    //to-string


    @Override
    public String toString() {
        return "ExhibitionModel{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", idCardNumber=" + idCardNumber +
                ", topic='" + topic + '\'' +
                ", guideMember='" + guideMember + '\'' +
                '}';
    }

}

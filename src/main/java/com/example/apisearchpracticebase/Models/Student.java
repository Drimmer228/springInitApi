package com.example.apisearchpracticebase.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Student_ID")
    private Long id;

    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Middle_Name")
    private String middleName;

    @Column(name = "Student_Login", nullable = false, unique = true)
    private String studentLogin;

    @Column(name = "Student_Password", nullable = false)
    private String studentPassword;

    @Column(name = "Internship_Period")
    private String internshipPeriod;

    @Column(name = "IsInternship", nullable = false)
    private boolean isInternship;

    @OneToOne
    @JoinColumn(name = "PracticeBase_ID", nullable = true)
    private PracticeBase practiceBase;

    @OneToOne
    @JoinColumn(name = "Resume_ID")
    private ResumeStudent resume;

    public Student() {
    }

    public Student(Long id, String lastName, String firstName, String middleName, String studentLogin, String studentPassword, String internshipPeriod, boolean isInternship, ResumeStudent resume, PracticeBase practiceBase) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.studentLogin = studentLogin;
        this.studentPassword = studentPassword;
        this.internshipPeriod = internshipPeriod;
        this.isInternship = isInternship;
        this.practiceBase = practiceBase;
        this.resume = resume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getInternshipPeriod() {
        return internshipPeriod;
    }

    public void setInternshipPeriod(String internshipPeriod) {
        this.internshipPeriod = internshipPeriod;
    }

    public boolean isInternship() {
        return isInternship;
    }

    public void setInternship(boolean internship) {
        isInternship = internship;
    }

    public ResumeStudent getResume() {
        return resume;
    }

    public void setResume(ResumeStudent resume) {
        this.resume = resume;
    }

    public PracticeBase getPracticeBase() {
        return practiceBase;
    }

    public void setPracticeBase(PracticeBase practiceBase) {
        this.practiceBase = practiceBase;
    }
}

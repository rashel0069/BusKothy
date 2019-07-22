package com.example.buskothy;

public class Author {
    String authorId;
    String authorName;
    String authoremail;
    String authorgender;
    String authorreligion;
    String authormobile;
    String authornid;
    String authorbusnumber;
    String authorpassword;

    public Author(){

    }

    public Author(String authorId, String authorName, String authoremail, String authorgender, String authorreligion, String authormobile, String authornid, String authorbusnumber, String authorpassword) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authoremail = authoremail;
        this.authorgender = authorgender;
        this.authorreligion = authorreligion;
        this.authormobile = authormobile;
        this.authornid = authornid;
        this.authorbusnumber = authorbusnumber;
        this.authorpassword = authorpassword;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthoremail() {
        return authoremail;
    }

    public String getAuthorgender() {
        return authorgender;
    }

    public String getAuthorreligion() {
        return authorreligion;
    }

    public String getAuthormobile() {
        return authormobile;
    }
    public String getAuthornid() {
        return authornid;
    }

    public String getAuthorbusnumber() {
        return authorbusnumber;
    }

    public String getAuthorpassword() {
        return authorpassword;
    }
}

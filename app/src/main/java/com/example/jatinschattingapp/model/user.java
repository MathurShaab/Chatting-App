package com.example.jatinschattingapp.model;

public class user {
    String profilepic, username, mail, password,userid, lastmsg;

    public user(String profilepic, String username, String mail, String password, String userid, String lastmsg) {
        this.profilepic = profilepic;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userid = userid;
        this.lastmsg = lastmsg;
    }
public user(){}
//SignUp Constructor

    public user(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }



    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastmsg() {
        return lastmsg;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }

    public String getUserid(String key) {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid()
    {
  return userid;
    }

}

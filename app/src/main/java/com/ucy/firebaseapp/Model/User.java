package com.ucy.firebaseapp.Model;

public class User {
    private String nick, pass, mail, name, phone;

    public User(){


    }

    public User(String nick, String pass, String mail, String name, String phone) {
        this.nick = nick;
        this.pass = pass;
        this.mail = mail;
        this.name = name;
        this.phone = phone;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class TelegramUser {
    public static List<TelegramUser> telegramUserList = new ArrayList<>();

    private String chatId;
    private String firstName;
    private String lastName;
    private int age;
    private String course;
    private String phoneNumber;
    private TelegramUserState state;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TelegramUserState getState() {
        return state;
    }

    public void setState(TelegramUserState state) {
        this.state = state;
    }
}

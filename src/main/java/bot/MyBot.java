package bot;

import model.TelegramUser;
import model.TelegramUserState;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        TelegramUser currentUser = null;

        for (TelegramUser user : TelegramUser.telegramUserList) {
            if (user.getChatId().equals(chatId)) {
                currentUser = user;
            }
        }

        if (currentUser == null) {
            currentUser = new TelegramUser();
            currentUser.setChatId(chatId);
            TelegramUser.telegramUserList.add(currentUser);
        }

        if(text.equals("/inlinebutton")){
            SendMessage sendMessage = new SendMessage(chatId, "Tugmani tanlang!");

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();
            List<InlineKeyboardButton> inlineKeyboardButtons1 = new ArrayList<>();
            List<InlineKeyboardButton> inlineKeyboardButtons2 = new ArrayList<>();
            InlineKeyboardButton salom1 = new InlineKeyboardButton("Salom1");
            salom1.setCallbackData("SALOM_1");
            salom1.setUrl("https://kun.uz");
            InlineKeyboardButton salom2 = new InlineKeyboardButton("Salom2");
            salom2.setCallbackData("SALOM_2");
            InlineKeyboardButton salom3 = new InlineKeyboardButton("Salom3");
            salom3.setCallbackData("SALOM_3");
//            InlineKeyboardButton salom4 = new InlineKeyboardButton("Salom4");
//            InlineKeyboardButton salom5 = new InlineKeyboardButton("Salom5");
            InlineKeyboardButton salom6 = new InlineKeyboardButton("Salom6");
            salom6.setCallbackData("SALOM_6");
            InlineKeyboardButton salom7 = new InlineKeyboardButton("Salom7");
            salom7.setCallbackData("SALOM_7");
            inlineKeyboardButtons1.add(salom1);
            inlineKeyboardButtons1.add(salom2);
            inlineKeyboardButtons1.add(salom3);
            inlineKeyboardButtons2.add(salom6);
            inlineKeyboardButtons2.add(salom7);
            listButtons.add(inlineKeyboardButtons1);
            listButtons.add(inlineKeyboardButtons2);

            inlineKeyboardMarkup.setKeyboard(listButtons);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            sendMyMessage(sendMessage);
        }

        if(text.equals("/button")){
            SendMessage sendMessage = new SendMessage(chatId, "Tugmani bosing!");

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

            List<KeyboardRow> keyboardRows = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardButton keyboardButton1 = new KeyboardButton("Kontaktni ulashish");
            keyboardButton1.setRequestContact(true);
            KeyboardButton keyboardButton2 = new KeyboardButton("Lokatsiya yuborish");
            keyboardButton2.setRequestLocation(true);
            KeyboardButton keyboardButton3 = new KeyboardButton("Open app");
            keyboardButton3.setWebApp(new WebAppInfo("https://kun.uz"));
            row1.add(keyboardButton1);
            row1.add(keyboardButton2);
            row2.add(keyboardButton3);
            keyboardRows.add(row1);
            keyboardRows.add(row2);

            replyKeyboardMarkup.setKeyboard(keyboardRows);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);

            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMyMessage(sendMessage);
        }

        if (currentUser.getState() == null && text.equals("/start")) {
            SendMessage sendMessage1 = new SendMessage(chatId, "Salom botga xush kelibsiz!");
            SendMessage sendMessage2 = new SendMessage(chatId, "Ismizni yuboring");
            currentUser.setState(TelegramUserState.FIRSTNAME);
            sendMyMessage(sendMessage1);
            sendMyMessage(sendMessage2);
        } else if (currentUser.getState().equals(TelegramUserState.FIRSTNAME)) {
            currentUser.setFirstName(text);
            currentUser.setState(TelegramUserState.LASTNAME);
            SendMessage sendMessage = new SendMessage(chatId, "Familiyezni yuboring!");
            sendMyMessage(sendMessage);
        } else if (currentUser.getState().equals(TelegramUserState.LASTNAME)) {
            currentUser.setLastName(text);
            currentUser.setState(TelegramUserState.AGE);
            SendMessage sendMessage = new SendMessage(chatId, "Yoshizni yuboring!");
            sendMyMessage(sendMessage);
        } else if (currentUser.getState().equals(TelegramUserState.AGE)) {
            currentUser.setAge(Integer.parseInt(text));
            currentUser.setState(TelegramUserState.COURSE);
            SendMessage sendMessage1 = new SendMessage(chatId, "Kursni tanlab raqamini yuboring!");
            SendMessage sendMessage2 = new SendMessage(chatId, "1. Java Generation\n2.Python\n3.Frontend\n4.Robototexnika");
            sendMyMessage(sendMessage1);
            sendMyMessage(sendMessage2);
        } else if (currentUser.getState().equals(TelegramUserState.COURSE)) {
            String kursNomi = null;
            switch (text) {
                case "1":
                    kursNomi = "Java Generation";
                    break;
                case "2":
                    kursNomi = "Python";
                    break;
                case "3":
                    kursNomi = "Frontend";
                    break;
                case "4":
                    kursNomi = "Robototexnika";
                    break;
            }
            currentUser.setCourse(kursNomi);
            currentUser.setState(TelegramUserState.TELEFONRAQAM);
            SendMessage sendMessage = new SendMessage(chatId, "Telefon raqamizni kiriting!");
            sendMyMessage(sendMessage);
        } else if (currentUser.getState().equals(TelegramUserState.TELEFONRAQAM)) {
            currentUser.setPhoneNumber(text);
            currentUser.setState(TelegramUserState.REGISTERED);

            String tekshirishUchun = "Ism: " + currentUser.getFirstName() +"\n" +
                    "Familiya: " + currentUser.getLastName() +"\n" +
                    "Yosh: " + currentUser.getAge() +"\n" +
                    "Tanlangan kurs: " + currentUser.getCourse() + "\n" +
                    "Telefon raqam: " + currentUser.getPhoneNumber() + "\n\n" +
                    "Malumotlariz to'g'rimi ? 1.Xa 2.Yo'q";

            SendMessage sendMessage1 = new SendMessage(chatId, "Tabriklaymiz siz muvaffaqiyatli ro'xatdan o'tdiz, malunmotlarizni tekshiring Admin siz bn boglnada!");
            SendMessage sendMessage2 = new SendMessage(chatId, tekshirishUchun);
            sendMyMessage(sendMessage1);
            sendMyMessage(sendMessage2);
        }else if(currentUser.getState().equals(TelegramUserState.REGISTERED)){
            SendMessage sendMessage = new SendMessage();
            if(text.equals("2")){
                currentUser.setState(TelegramUserState.FIRSTNAME);
                sendMessage.setText("Qaytadan malumotlarizni kiriting!");
            }else{
                sendMessage.setText("Iltimos admin javobini kuting!");
            }
            sendMessage.setChatId(chatId);
            sendMyMessage(sendMessage);
        }


        if(text.equals("/show")){
            StringBuilder showText = new StringBuilder();
            for (TelegramUser user : TelegramUser.telegramUserList) {
                showText.append("Ismi: ").append(user.getFirstName()).append("\n");
                showText.append("Familiyasi: ").append(user.getLastName()).append("\n");
                showText.append("Yoshi: ").append(user.getAge()).append("\n");
                showText.append("Tanlagan kursi: ").append(user.getCourse()).append("\n");
                showText.append("Telefon raqami: ").append(user.getPhoneNumber()).append("\n");
                showText.append("\n");
            }

            SendMessage sendMessage = new SendMessage(chatId, showText.toString());
            sendMyMessage(sendMessage);
        }


    }

    private void sendMyMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "educenterd28bot";
    }

    @Override
    public String getBotToken() {
        return "bot-token";
    }
}

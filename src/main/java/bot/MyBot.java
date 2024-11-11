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
            TelegramUser.telegramUserList.add(currentUser);       }

        if(text.equals("/inlinebutton")){
            SendMessage sendMessage = new SendMessage(chatId, "Tugmalardan O'zingizga keragligin tanlang✅!");

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();
            List<InlineKeyboardButton> inlineKeyboardButtons1 = new ArrayList<>();
            List<InlineKeyboardButton> inlineKeyboardButtons2 = new ArrayList<>();
            InlineKeyboardButton salom1 = new InlineKeyboardButton("Salom1");
            salom1.setCallbackData("SALOM_1");
            salom1.setUrl("https://uzmovi.com/");
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

        if(text.equals("/yordam")){
            SendMessage sendMessage = new SendMessage(chatId, "Tugmani bosing!");

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

            List<KeyboardRow> keyboardRows = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardRow row3 = new KeyboardRow();
            KeyboardButton keyboardButton1 = new KeyboardButton("Eng Yaqin Avtosevis:🚗");
            keyboardButton1.setWebApp(new WebAppInfo("https://yandex.uz/maps/10335/tashkent/search/%D0%90%D0%B2%D1%82%D0%BE%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81%D1%8B/?ll=69.250320%2C41.284652&sctx=ZAAAAAgBEAAaKAoSCbMlqyLcUVFAESQlPQytqERAEhIJmgXaHVIMvD8RgVt381SHrD8iBgABAgMEBSgKOABA31BIAWoCdXqdAc3MzD2gAQCoAQC9Afga8g7CAZIBleix07oCg8KVgMMB0JHYstoD%2F9f9gYcGhbHuqfsE%2FOOH894GhqezlmLJ%2FZ2LngaW1u%2BEuwGnxOHdN4XO6Nu3AaCDpMqLAvr%2BxuP4AojF7ovXBLvW7r2yAZeA96mABv3r75HDA%2B%2BuzY6NA%2BKeho0kp7mao9cF3PyPuc8FzsXasBSO5%2FfBlQbrlPyE5AWItsDYhQOCAhbQkNCy0YLQvtGB0LXRgNCy0LjRgdGLigIdMTg0MTA1MjQ2JDE4NDEwNTI2MCQxODQxMDUyNTaSAgCaAgxkZXNrdG9wLW1hcHM%3D&sll=69.250320%2C41.284652&sspn=0.133031%2C0.067690&z=13.26"));
            KeyboardButton keyboardButton2 = new KeyboardButton("Onlayn Yo'rdam:🆘");
            keyboardButton2.setWebApp(new WebAppInfo("https://www.youtube.com/results?search_query=kochada+balon+almashtirish"));
            KeyboardButton keyboardButton3 = new KeyboardButton("Mehanik bilan bog'lanish:🧑🏿‍🔧");
            keyboardButton3.setWebApp(new WebAppInfo("https://auto.ustabor.uz/uz/price/transmission_repairs"));
            KeyboardButton keyboardButton4 = new KeyboardButton("Tezko'r Yo'rdam:📞");
            keyboardButton4.setWebApp(new WebAppInfo("https://iibb.uz/uz/menu/shoshilinch-xizmat-telefon-raqamlari"));
            row1.add(keyboardButton1);
            row1.add(keyboardButton2);
            row2.add(keyboardButton3);
            row3.add(keyboardButton4);
            keyboardRows.add(row1);
            keyboardRows.add(row2);

            replyKeyboardMarkup.setKeyboard(keyboardRows);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);

            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMyMessage(sendMessage);
        }

        if (currentUser.getState() == null && text.equals("/start")) {
            SendMessage sendMessage1 = new SendMessage(chatId, "Salom Yordam bot ga Hush Kelib siz!");
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
            SendMessage sendMessage1 = new SendMessage(chatId, "Sizga Yordam kerag bolsa : /yordam : ni Bosing");

            sendMyMessage(sendMessage1);
        } else if (currentUser.getState().equals(TelegramUserState.COURSE)) {
            String kursNomi = null;
            switch (text) {
                case "1":
                    kursNomi = "Lavash";
                    break;
                case "2":
                    kursNomi = "Senvich";
                    break;
                case "3":
                    kursNomi = "kurvasan";
                    break;
            }
            currentUser.setCourse(kursNomi);
            currentUser.setState(TelegramUserState.TELEFONRAQAM);
            SendMessage sendMessage = new SendMessage(chatId, "Telefon raqamizni kiriting! Va joylashgan joyingizni kriting");
            sendMyMessage(sendMessage);
        } else if (currentUser.getState().equals(TelegramUserState.TELEFONRAQAM)) {
            currentUser.setPhoneNumber(text);
            currentUser.setState(TelegramUserState.REGISTERED);

            String tekshirishUchun = "Ism: " + currentUser.getFirstName() +"\n" +
                    "Familiya: " + currentUser.getLastName() +"\n" +
                    "Yosh: " + currentUser.getAge() +"\n" +
                    "Tanlangan tanlov: " + currentUser.getCourse() + "\n" +
                    "Telefon raqam va Lokatsiya: " + currentUser.getPhoneNumber() + "\n\n" +
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
                showText.append("Telefon raqami va lokatsiya: ").append(user.getPhoneNumber()).append("\n");
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
        return "coscoovo_bot";
    }

    @Override
    public String getBotToken() {
        return "7652403414:AAHCNXCqZcu5j0kWxB07OmQ74_qGmnM6SP4";
    }
}

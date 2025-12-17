import java.util.ArrayList;
import java.util.List;

// 1. Интерфейс посредника (чат-комната)
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// 2. Конкретный посредник
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату");
    }

    @Override
    public void sendMessage(String message, User sender) {
        System.out.println("\n" + sender.getName() + " отправляет: " + message);

        // Посредник решает, как обработать сообщение
        for (User user : users) {
            // Не отправляем сообщение отправителю самому себе
            if (user != sender) {
                user.receive(message);
            }
        }

        // Дополнительная логика посредника
        logMessage(message, sender);
    }

    private void logMessage(String message, User sender) {
        // Посредник может выполнять дополнительную логику
        System.out.println("[Лог] " + sender.getName() + ": " + message);
    }
}

// 3. Базовый класс пользователя
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

// 4. Конкретный пользователь
class ConcreteUser extends User {

    public ConcreteUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " пишет: " + message);
        // Пользователь не знает о других пользователях,
        // он только просит посредника отправить сообщение
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " получает: " + message);
    }
}

// 5. Демонстрационный класс
public class MediatorPatternDemo {
    public static void main(String[] args) {
        // Создаем посредника (чат-комнату)
        ChatMediator chatRoom = new ChatRoom();

        // Создаем пользователей
        User anna = new ConcreteUser(chatRoom, "Анна");
        User ivan = new ConcreteUser(chatRoom, "Иван");
        User maria = new ConcreteUser(chatRoom, "Мария");

        // Регистрируем пользователей в чате (через посредника)
        chatRoom.addUser(anna);
        chatRoom.addUser(ivan);
        chatRoom.addUser(maria);

        System.out.println("\n--- Начало общения в чате ---");

        // Пользователи общаются через посредника
        anna.send("Привет всем!");
        ivan.send("Привет, Анна! Как дела?");
        maria.send("Добрый день! Я тоже здесь.");

        // Добавим нового пользователя
        User petr = new ConcreteUser(chatRoom, "Петр");
        chatRoom.addUser(petr);
        petr.send("Привет, я новичок здесь!");
    }
}
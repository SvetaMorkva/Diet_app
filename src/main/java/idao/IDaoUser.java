package idao;

import entity.User;

public interface IDaoUser extends IDaoBase<User>{
    User findByLogin(String login);
}

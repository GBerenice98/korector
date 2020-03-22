package miage.korector.login.services.interfaces;

import miage.korector.login.models.User;

import java.util.List;

public interface IUser  {

    void deleteUser(Integer UserId);


    User readUser(Integer UserId);

    boolean checkIfIdexists(Integer id);

    List<User> readAllUser();
}

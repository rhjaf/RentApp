package Services;

import DataModels.User;
import DataModels.UserArrayDataAccessService;
import DataModels.UserDao;
import DataModels.UserFileDataAccessService;

import java.util.UUID;
import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers(){
        return userDao.getUsers();
    }

    public User getUserById(UUID id){
        for(User user:getUsers()){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
}

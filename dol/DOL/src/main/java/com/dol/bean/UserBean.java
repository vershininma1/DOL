package com.dol.bean;

import com.dol.entity.User;
import org.apache.log4j.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import static com.dol.Constants.*;

@Stateless
@Local
/**
 * Класс, содержащий функции,необходимые для работы с пользователями
 */
public class UserBean {
    @PersistenceContext(unitName = CONST_DEVMODE)
    private EntityManager em;
    private ResourceBundle mybundle = ResourceBundle.getBundle(CONST_Bundle_Ru);
    private static final Logger log = Logger.getLogger(UserBean.class);
    private static final String CONST_Start_check="Start  check";
    private static final String CONST_login_text="login:  ";
    private static final String CONST_Start_searchByLogin="Start  searchByLogin";
    private static final String CONST_User_getUser="User.getUser";
    private static final String CONST_Start_makeHash="Start  makeHash";
    private static final String CONST_hash="MD5";
    private static final String CONST_error1="Логин не уникален";

    /**
     * сравнивает логин и пароль с БД
     * @param login
     * @param pass
     * @return boolean
     * @throws NoSuchAlgorithmException
     */
    public boolean check(String login, String pass) throws NoSuchAlgorithmException {
        log.info(CONST_Start_check);
        boolean result = false;
        if (login != null && login != CONST_empty && pass != CONST_empty && pass != null) {
//возвращает юзера по логину
            User user =   searchByLogin(login);
            if (user != null) {
                result =  Arrays.equals(user.getPass(),this.makeHash(pass));
            }
        }
        log.info(CONST_login_text +result);
        return result;
    }

    /**
     * Добавить пользователя
     * @param user
     * @return User
     */
    public User add(User user){
        return em.merge(user);
    }


    /**
     *  Ищет пользователя по имени
     * @param login
     * @return User
     */
    public User searchByLogin(String login){
        log.info(CONST_Start_searchByLogin);
        TypedQuery<User> namedQuery= em.createNamedQuery(CONST_User_getUser, User.class).setParameter(CONST_login, login);
        List<User> users= namedQuery.getResultList();
        User user=null;
        if(users.size()>1){
            log.error(CONST_error1);
        }
        if(!users.isEmpty()){
            user=users.remove(0);
        }
        return user;
    }

    /**
     * переводит строку в хеш функцию
     * @param pass
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    public byte[] makeHash(String pass) throws NoSuchAlgorithmException {
        log.info(CONST_Start_makeHash);
        MessageDigest digeast = MessageDigest.getInstance(CONST_hash);
        byte[] bytes = digeast.digest(pass.getBytes());
        return bytes;
    }
}




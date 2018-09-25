package com.dol.bean;
import com.dol.entity.MySet;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import org.apache.log4j.Logger;
import static com.dol.Constants.*;
@Stateless
@Local
/**
 * Класс, содержащий функции,необходимые для работы с комплектами
 */
public class SetBean {
    @PersistenceContext(unitName = CONST_DEVMODE)
    private EntityManager em;
    private static final Logger log = Logger.getLogger(SetBean.class);
    private static final String CONST_Start_get="Start get";
    private static final String CONST_Start_update="Start update";
    private static final String CONST_Start_makeBusy="Start makeBusy";
    private static final String CONST_Start_getFree="Start getFree";
    private static final String CONST_MySet_getFree="MySet.getFree";
    private static final String CONST_dealerName="dealerName";
    /**
     * возвращает комплект по id
     * @param id
     * @return MySet
     */
    public MySet get(int id){
        log.info(CONST_Start_get);
        return em.find(MySet.class, id);
    }
    /**
     * обновляет комплект
     * @param changeSet
     * @return MySet
     */
    public MySet update (MySet changeSet) {
        log.info(CONST_Start_update);
        return em.merge(changeSet);
    }

    /**
     * делает комплект занятым
     * @param changeSet
     */
    public void makeBusy(MySet changeSet){
        log.info(CONST_Start_makeBusy);
        changeSet.setFree(false);
        update(changeSet);
    }

    /**
     * возвращает все свободные комплекты
     * @param dealerName
     * @return  List<MySet>
     */
    public List<MySet> getFree(String dealerName){
    log.info(CONST_Start_getFree);
    TypedQuery<MySet> query = em.createNamedQuery(CONST_MySet_getFree, MySet.class).setParameter(CONST_dealerName, dealerName);
        return query.getResultList();
}

}

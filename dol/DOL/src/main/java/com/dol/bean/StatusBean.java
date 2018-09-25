package com.dol.bean;
import com.dol.entity.Status;

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
 * Класс, содержащий функции,необходимые для работы со статусами
 */
public class StatusBean {
    @PersistenceContext(unitName = CONST_DEVMODE)
    private EntityManager em;
    private static final Logger log = Logger.getLogger(StatusBean.class);
    private static final String CONST_Start_getAll="Start getAll";
    private static final String CONST_Status_getAll="Status.getAll";

    /**
     * возвращает все строки таблицы статусов
     * @return List<Status>
     */
    public List<Status> getAll(){
        log.info(CONST_Start_getAll);
        TypedQuery<Status> namedQuery = em.createNamedQuery(CONST_Status_getAll, Status.class);
        return namedQuery.getResultList();
    }
}

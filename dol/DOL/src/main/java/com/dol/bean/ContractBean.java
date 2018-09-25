package com.dol.bean;

import com.dol.entity.Contract;
import org.apache.log4j.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import static com.dol.Constants.*;

@Stateless
@Local
/**
 * Класс, содержащий функции,необходимые для работы с контрактами
 */
public class ContractBean {
     @PersistenceContext(unitName = CONST_DEVMODE)
    private EntityManager em;
    private static final Logger log = Logger.getLogger(ContractBean.class);
    private static final String CONST_Start_getSearch="Start  getSearch";
    private static final String CONST_String_format="%%%s%%";
    private static final String CONST_mySet="mySet";
    private static final String CONST_status_ID="status_ID";

    /**
     * добавляет новый контракт
     * @param contract
     * @return
     */
    public Contract add(Contract contract){
        return em.merge(contract);
    }

    /**
     * Возвращает контракт по id
     * @param id
     * @return Contract
     */
    public Contract get(int id){
        return em.find(Contract.class, id);
    }


    /**
     * //возвращает контракты по указанным критериям поиска
     * @param SearchName
     * @param SearchSetName
     * @param searchStatusId
     * @param StartDate
     * @param EndDate
     * @return List<Contract>
     */
    public List<Contract>  getSearch(String SearchName, String SearchSetName, String searchStatusId, Date StartDate,Date EndDate){
        log.info(CONST_Start_getSearch);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
        Root<Contract> from = cq.from(Contract.class);
        cq.select(from).distinct(true);
        Predicate userLoginPredicate=cb.like(from.<String>get(CONST_userLogin), String.format(CONST_String_format,SearchName));
        Predicate SetPredicate=cb.like(from.get(CONST_mySet).<String>get(CONST_name), String.format(CONST_String_format,SearchSetName));
        //всегда истинный предикат
        Predicate StatusPredicate =cb.and();
if(!(searchStatusId.equals(CONST_empty)||searchStatusId==null)) {
  StatusPredicate = cb.equal(from.get(CONST_status).<Integer>get(CONST_status_ID), Integer.valueOf(searchStatusId));
}
        Predicate DatePredicate=userLoginPredicate;
           if(StartDate!=null) {
            if (EndDate != null) {//end!=null,start!=null
                if (!(StartDate.after(EndDate))) {
                    DatePredicate = cb.between(from.<Date>get(CONST_date), StartDate, EndDate);
                }else{
                    //всегда ложный предикат
                    DatePredicate= cb.or();
                }
            }else{//end=null,start!=null
                DatePredicate=cb.equal(from.get(CONST_date), StartDate);
            }
        }else{
            if (EndDate != null) {//end!=null,start=null
                DatePredicate=cb.equal(from.get(CONST_date), EndDate);
            }else{//end=null,start=null
                DatePredicate=userLoginPredicate;
            }
        }
        cq.where(cb.and(userLoginPredicate,SetPredicate,StatusPredicate,DatePredicate));
       TypedQuery<Contract> q = em.createQuery(cq);
        List<Contract> allitems = q.getResultList();
        return allitems;
    }
}

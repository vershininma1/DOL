package com.dol.entity;
import javax.persistence.*;
import java.util.ResourceBundle;
import static com.dol.Constants.*;
@Entity(name = "sets")
@NamedQueries({
        @NamedQuery(name = "MySet.getFree", query = "SELECT a FROM sets a where a.dealerName LIKE :dealerName and a.free=true")
})
/**
 * комплекты
 */
public class MySet
{
    private static final String CONST_free="free";
    private static final String CONST_ctn="ctn";
    private static final String CONST_plan="plan";
    private static final String CONST_dealerName="dealerName";
    private static final String CONST_myset_tostring="myset_tostring";
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )

    @Column(name = CONST_id, nullable = false)
    private int set_ID;
    @Column(name =CONST_name , nullable = false)
    private String name;
    @Column(name =CONST_free , nullable = false)
    private Boolean free;
    @Column(name = CONST_ctn, nullable = false)
    private int ctn;
    @Column(name = CONST_plan, nullable = false)
    private String plan;
    @Column(name =CONST_dealerName , nullable = false)
    private String dealerName;

    public MySet() {
    }
@Override
/**
 * преобразование к строке, удобной для выода
 */
public String toString() {
    ResourceBundle mybundle = ResourceBundle.getBundle(CONST_Bundle_Ru);
    return String.format(mybundle.getString(CONST_myset_tostring),this.name,this.plan, this.ctn);
}
    public int getSet_ID() {
        return set_ID;
    }

    public void setSet_ID(int set_ID) {
        this.set_ID = set_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public int getCtn() {
        return ctn;
    }

    public void setCtn(int stn) {
        this.ctn = stn;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

}
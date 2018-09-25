package com.dol.entity;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.ResourceBundle;
import static com.dol.Constants.*;

@Entity(name = "contracts")
/**
 * контракты
 */
public class Contract {
    private static final String CONST_contract_ID="contract_ID";
    private static final String CONST_passportID="passportID";
    private static final String CONST_setNumber="setNumber";
    private static final String CONST_scan="scan";
    private static final String CONST_statusNumber="statusNumber";
    private static final String CONST_contract_new="contract_new";
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CONST_contract_ID)
    private int id;
    @NotNull
    @Column(name = CONST_userLogin, nullable = false)
    private String userLogin;
    @NotNull
    @Column(name = CONST_passportID, nullable = false)
    private String passportID;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CONST_setNumber)
     MySet mySet;
    @NotNull
    @Column(name = CONST_date, nullable = false)
    private Date date;
    @NotNull
    @Lob
    @Column(name = CONST_scan, nullable = false)
    @Type(type=CONST_hibernate)
    private byte[] scan;
    @NotNull
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = CONST_statusNumber)
    private Status status;


    public Contract() {
    }

    /**
     * конструктор контракта
     * @param userLogin
     * @param passportID
     * @param mySet
     * @param scan
     */
    public Contract(String userLogin, String passportID,MySet mySet,byte[] scan) {
        ResourceBundle mybundle = ResourceBundle.getBundle(CONST_Bundle_Ru);
        this.userLogin = userLogin;
        this.passportID = passportID;
        this.scan = scan;
        Status status2 = new Status();
        status2.setName(mybundle.getString(CONST_contract_new));
        status2.setStatus_ID(1);
        this.status=status2;
        this.mySet = mySet;
        this.date=new Date();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MySet getMySet() {
        return mySet;
    }

    public void setMySet(MySet mySet) {
        this.mySet = mySet;
    }

    public byte[] getScan() {
        return scan;
    }

    public void setScan(byte[] scan) {
        this.scan = scan;
    }
}

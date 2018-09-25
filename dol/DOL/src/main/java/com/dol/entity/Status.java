package com.dol.entity;
import javax.persistence.*;
import static com.dol.Constants.*;
@Entity(name = "statuses")
@NamedQueries({
        @NamedQuery(name = "Status.getAll", query = "select u from statuses u")
})
/**
 * статусы
 */
public class Status
{

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )

    @Column(name = CONST_id, nullable = false)
    private int status_ID;
    @Column(name = CONST_name, nullable = false)
    private String name;

    public int getStatus_ID() {
        return status_ID;
    }

    public void setStatus_ID(int statusID) {
        this.status_ID = statusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
package database.entity;

import javax.persistence.*;

@Entity
@Table(name = "Service_Request")
public class ServiceRequest {

    private long id;
    private String name;
    private String address;
    private long amount;
    private String status;

    public ServiceRequest() {
    }

    public ServiceRequest(String name, String address, long amount, String status) {
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

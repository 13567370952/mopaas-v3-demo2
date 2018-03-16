package com.test.demo.primary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Balance")
@Table(name="t_balance")
public class Balance {
    @Id
    private Long  id;
    @Column(name="org_guid")
    private String orgGuid;
    private Double balance;
    @Column(name="balance_type")
    private Integer balanceType;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrgGuid() {
        return orgGuid;
    }
    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Integer getBalanceType() {
        return balanceType;
    }
    public void setBalanceType(Integer balanceType) {
        this.balanceType = balanceType;
    }
    @Override
    public String toString() {
        return "Balance [id=" + id + ", orgGuid=" + orgGuid + ", balance=" + balance
               + ", balanceType=" + balanceType + "]";
    }
}

package com.test.demo.secondary.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "TPayments")
@Table(name="t_payments")
public class TPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(name="org_guid")
    private String orgGuid;
    @Column(name="resource_type")
    private String resourceType;//0 app  1 service
    @Column(name="resource_name")
    private String resourceName;//name
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")//最后使用时间
    private Date createdAt;
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
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "TPayments [id=" + id + ", orgGuid=" + orgGuid + ", resourceType=" + resourceType
               + ", resourceName=" + resourceName + ", createdAt=" + createdAt + "]";
    }
}

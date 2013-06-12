package net.agetsuma.jbatchdemo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author agetsuma
 */
@Entity
@NamedQuery(name = "Tweet.deleteAll", query = "delete from Tweet")
public class Tweet implements Serializable {
    @Id
    private long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private String text;
    
    public Tweet() {
        // 処理なし
    }
    
    public Tweet(long id, Date createdAt, String text) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
    }
    
    public long getId() {
        return id;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public String getText() {
        return text;
    }
    
}

package net.agetsuma.jbatchdemo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 時間帯ごとのTweet回数を示すエンティティ。
 * @author Norito AGETSUMA
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "TweetTimes.findAll", query = "select t from TweetTimes t"),
    @NamedQuery(name = "TweetTimes.deleteAll", query = "delete from TweetTimes")
})
public class TweetTimes implements Serializable {
    
    /** 集計した時間帯 */
    @Id
    @Enumerated(EnumType.STRING)
    private Hours hours;
    
    /** tweet回数 */
    private int times;
    
    public TweetTimes() {
        // 処理なし
    }
    
    public TweetTimes(Hours hours, int times) {
        this.hours = hours;
        this.times = times;
    }
    
    public Hours getHours() {
        return hours;
    }
    
    public int getTimes() {
        return times;
    }
    
    public int add() {
        times++;
        return times;
    }
}

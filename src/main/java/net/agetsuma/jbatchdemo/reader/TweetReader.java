/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.jbatchdemo.reader;

import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Chunk処理のItemReader実装。
 * TwitterAPI経由で情報を取得します。
 * @author Norito AGETSUMA
 */
@Named
public class TweetReader extends AbstractItemReader {
    
    /** タイムライン情報 */
    private ResponseList<Status> timeLineList;
    
    /** readItemで１件ずつtweet情報を返すときに使うインデックス */
    private int index;
    
    /**
     * Twitter4jを使用して、タイムラインから最大100件の
     * tweet情報を取得します。
     * @param checkpointInfo このサンプルではチェックポイントは未サポートです。
     * @throws Exception Tweetが取得できなかった場合。
     */
    @Override
    public void open(Serializable checkpointInfo) throws Exception {
        Twitter twitter = new TwitterFactory().getInstance();
        Paging paging = new Paging(1, 100);
        timeLineList = twitter.getUserTimeline("n_agetsu", paging);
    }

    /**
     * Open時に取得したTweet情報を１件返します。
     * @return Tweet情報
     * @throws Exception 例外は発生しません。 
     */
    @Override
    public Object readItem() throws Exception {
        if (timeLineList.size() == index) {
            return null;
        }
        Status status = timeLineList.get(index);
        index++;
        return status;
    }
    
    // closeとcheckpointは空実装。
    
}

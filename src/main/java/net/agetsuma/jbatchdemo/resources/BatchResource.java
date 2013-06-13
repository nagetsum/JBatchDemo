package net.agetsuma.jbatchdemo.resources;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import net.agetsuma.jbatchdemo.entity.TweetTimes;
import net.agetsuma.jbatchdemo.job.Jobs;
import net.agetsuma.jbatchdemo.service.BatchManagerService;
import net.agetsuma.jbatchdemo.service.TweetService;

/**
 * バッチ起動用のJAX-RSサービスクラス。
 * @author Norito AGETSUMA
 */
@Path("/tweets")
public class BatchResource {
    
    @EJB
    private TweetService tweetService;
    
    @EJB
    private BatchManagerService batchManagerService;
    
    @GET
    @Consumes("application/json")
    public List<TweetTimes> times(@QueryParam("intervalHours") int intervalHours) {
        // TODO 時間帯間隔のクエリパラメータ指定。
        // このサンプルではパラメータを無視しして、時間帯は6時間ごとに固定とする。
        return tweetService.getTweetTimes();
    }
    
    @POST
    @Consumes("application/json")
//    @TwitterAuth
    public List<TweetTimes> update() {
        // データベース上のTweet情報をクリア
        tweetService.clear();
        
        // バッチジョブの起動
        batchManagerService.startBatch(Jobs.COLLECT_TWEET, null);
        
        // 更新後の時間帯別tweet回数を返す
        return tweetService.getTweetTimes();
    }
}

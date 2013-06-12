package net.agetsuma.jbatchdemo.service;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import net.agetsuma.jbatchdemo.job.Jobs;

@Stateless
public class BatchManagerService {
    
    /**
     * 引数に指定されたジョブIDのバッチを起動します。
     * このメソッドはブロックされず、バッチ完了を待たずに応答を返します。
     * @param jobs ジョブ
     * @param property ジョブプロパティ
     */
    public void startBatch(Jobs jobs, Properties property) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        String jobId = jobs.toString();
        
        // Java Batchの起動
        jobOperator.start(jobId, property);
    }
    
}

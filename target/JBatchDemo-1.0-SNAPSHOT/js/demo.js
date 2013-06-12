"use strict";
$(function() {
    // バッチ起動ボタンへのイベント登録
    $("#startBatchButton").click(startBatch);
    
    // グラフ更新ボタンへのイベント登録
    $("#updateGraphButton").click(updateGraph);
    
    updateGraph();
});

function updateGraph() {
    var times = [];
    $.ajax({
        url: "/JBatchDemo/batch/tweets",
        type: "GET",
        dataType: "json",
        success:function(json) {
            for (var i in json) {
                var tweetTimes = json[i];
                // TODO もっと綺麗なやり方を考える
                switch(tweetTimes.hours) {
                    case "MIDNIGHT": times['0'] = tweetTimes.times; break;
                    case "MORING": times['1'] = tweetTimes.times; break;
                    case "NOON": times['2'] = tweetTimes.times; break;
                    case "EVENING": times['3'] = tweetTimes.times;
                }
            }
            
            if (times.length === 0) {
                var $msgDiv = showMessage("データがありません", "バッチを実行してデータを生成してください");
                $msgDiv.addClass("alert");
            } else {
                var $msgDiv = showMessage("成功", "グラフは最新状態です。");
                $msgDiv.addClass("alert").addClass("alert-success");
                showGraph(times);
            }
        }
    });
}

function startBatch() {
    $.ajax({
        url: "/JBatchDemo/batch/tweets",
        type: "POST",
        dataType: "json",
        success:function() {
            var $msgDiv = showMessage("成功", "バッチ起動は成功しました。少し時間を空けてグラフ更新してください。");
            $msgDiv.removeClass();
            $msgDiv.addClass("alert").addClass("alert-success");
        }
    });
}

function showGraph(param) {
    var data = {
        labels : ["0〜6時","6〜12時","12〜18時","18〜24時"],
        datasets : [
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                data : param
            }
        ]
    };
    
    var options = {
        scaleOverride : true, scaleSteps : 10, scaleStepWidth : 5,
        scaleStartValue : 0, scaleShowLabels : true, barValueSpacing : 30
    };
            
    var ctx = $("#bar").get(0).getContext("2d");
    new Chart(ctx).Bar(data, options);  
}

function showMessage(msg, submsg) {
    var $msgEmptyDiv = $("#messages").empty();
    
    var $msg = $('<strong>' + msg + '</strong> <small>' + submsg + '</small>');
    var $msgDiv = $msgEmptyDiv.append($msg);
    
    return $msgDiv;
}
package com.tugalsan.java.core.serialcom.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.thread;
import module com.tugalsan.java.core.union;
import java.time.*;

public class TS_SerialComMessageBroker {

    final public static TS_Log d = TS_Log.of(true, TS_SerialComMessageBroker.class);

    public TS_SerialComMessageBroker(int maxSize) { 
        this.maxSize = maxSize;
    }
    final public TS_ThreadSyncLst<String> replies = TS_ThreadSyncLst.ofSlowRead();
    final public int maxSize;

    public static TS_SerialComMessageBroker of(int maxSize) {
        return new TS_SerialComMessageBroker(maxSize);
    }

    public void setConnection(TS_SerialComConnection con) {
        this.con = con;
    }
    private TS_SerialComConnection con;

    public void onReply(String reply) {
        replies.add(reply);
        replies.cropToLength_byRemovingFirstItems(maxSize);
        if (d.infoEnable) {
            if (reply.startsWith("REPLY_OF:")) {
                d.cr("onReply", reply);
                return;
            }
            if (reply.startsWith("ERROR")) {
                d.ce("onReply", reply);
                return;
            }
            d.ci("onReply", reply);
        }
    }

    public TGS_UnionExcuse<String> sendTheCommand_and_fetchMeReplyInMaxSecondsOf(TS_ThreadSyncTrigger killTrigger, String command, Duration maxDuration, String filterPrefix, boolean filterContainCommand) {
        if (!con.send(command)) {
            return TGS_UnionExcuse.ofExcuse(d.className(), "sendTheCommand_and_fetchMeReplyInMaxSecondsOf", command + " -> ERROR_SENDING");
        }
        TGS_FuncMTU_OutBool_In1<String> condition = val -> {
            if (filterContainCommand && !val.contains(command)) {
                return false;
            }
            if (filterPrefix != null && !val.startsWith(filterPrefix)) {
                return false;
            }
            return true;
        };
        var run = TS_ThreadAsyncAwait.callSingle(killTrigger.newChild(d.className()).newChild("sendTheCommand_and_fetchMeReplyInMaxSecondsOf"), maxDuration, kt -> TGS_FuncMTUEffectivelyFinal.ofStr()
                .anoint(reply -> {
                    while (reply == null && killTrigger.hasNotTriggered()) {
                        reply = replies.findFirst(val -> condition.validate(val));
                        TS_ThreadSyncWait.milliseconds100();
                        Thread.yield();
                    }
                    return reply;
                })
                .anointAndCoronateIf(reply -> filterContainCommand && filterPrefix != null, reply -> reply.substring(filterPrefix.length() + command.length() + "->".length()))
                .anointAndCoronateIf(reply -> filterContainCommand, reply -> reply.substring(command.length() + "->".length()))
                .anointAndCoronateIf(reply -> filterPrefix != null, reply -> reply.substring(filterPrefix.length() + "->".length()))
                .coronate()
        );
        replies.removeAll(val -> condition.validate(val));
        if (run.timeout()) {
            return TGS_UnionExcuse.ofExcuse(d.className(), "sendTheCommand_and_fetchMeReplyInMaxSecondsOf", command + " -> ERROR_TIMEOUT");
        }
        if (run.hasError()) {
            return TGS_UnionExcuse.ofExcuse(d.className(), "sendTheCommand_and_fetchMeReplyInMaxSecondsOf", command + " -> " + run.exceptionIfFailed().get());
        }
        return TGS_UnionExcuse.of(run.result().get());
    }
}

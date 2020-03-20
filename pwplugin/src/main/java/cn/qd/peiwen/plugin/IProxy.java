package cn.qd.peiwen.plugin;


public interface IProxy {

    boolean isResumed();

    void onComponentPrepared();

    void onComponentPrepareing();

    void onComponentResourceWaiting();
}

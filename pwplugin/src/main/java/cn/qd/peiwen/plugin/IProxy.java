package cn.qd.peiwen.plugin;


public interface IProxy {
    boolean isResumed();

    void onComponentPrepareing();

    void onComponentPrepareFailured();

    void onComponentPrepareSuccessed();
}

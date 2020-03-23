package cn.qd.peiwen.plugin;


import cn.qd.peiwen.plugin.event.IEventEntity;

public interface IProxy {
    boolean isResumed();

    void onComponentPrepareing();

    void onComponentPrepareFailured();

    void onComponentPrepareSuccessed();

    void onProcessEvent(IEventEntity event);
}

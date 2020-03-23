package cn.qd.peiwen.plugin;

import cn.qd.peiwen.plugin.event.IEventEntity;
import cn.qd.peiwen.plugin.param.IParamEntity;

public interface IComponent {
    void onPause();

    void onResume();

    void onRelease();

    void onProcessEvent(IEventEntity event);

    void onInitialRequest(IParamEntity params);
}

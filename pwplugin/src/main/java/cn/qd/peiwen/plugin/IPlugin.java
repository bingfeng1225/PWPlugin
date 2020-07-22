package cn.qd.peiwen.plugin;


import android.content.Context;

import cn.qd.peiwen.plugin.event.IEventEntity;


public interface IPlugin {
    String name();

    void onPause();

    void onResume();

    void onRelease();

    void onPluginProcessEvent(IEventEntity event);

    IComponent onCreateComponent(Context context);
}

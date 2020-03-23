package cn.qd.peiwen.plugin;


import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import cn.qd.peiwen.plugin.event.IEventEntity;
import cn.qd.peiwen.plugin.param.IParamEntity;
import cn.qd.peiwen.pwtools.EmptyUtils;

public class PluginManager {
    private WeakReference<IProxy> proxy;
    private static PluginManager instance = null;
    private static final Object lock = new Object();
    private Map<String, Class<?>> paramsType = new HashMap<>();
    private Map<String, Class<?>> pluginsType = new HashMap<>();

    public static PluginManager getInstance() {
        if (null == instance) {
            synchronized (lock) {
                instance = new PluginManager();
            }
        }
        return instance;
    }

    private PluginManager() {

    }

    public void changeProxy(IProxy proxy){
        this.proxy = new WeakReference<>(proxy);
    }

    public void insertPlugin(String name, Class<? extends IPlugin> pluginType) {
        this.insertPlugin(name, pluginType, null);
    }

    public void insertPlugin(String name, Class<? extends IPlugin> pluginType, Class<? extends IParamEntity> paramsType) {
        if (EmptyUtils.isEmpty(name)) {
            return;
        }

        if (EmptyUtils.isEmpty(pluginType)) {
            return;
        }
        this.pluginsType.put(name, pluginType);
        if (EmptyUtils.isEmpty(paramsType)) {
            return;
        }
        this.paramsType.put(name, paramsType);
    }

    public Class<?> findParamType(String name) {
        if (EmptyUtils.isEmpty(name)) {
            return null;
        }
        return this.paramsType.get(name);
    }

    public Class<?> findPluginType(String name) {
        if (EmptyUtils.isEmpty(name)) {
            return null;
        }
        return this.pluginsType.get(name);
    }


    public boolean isResumed(){
        boolean result = false;
        if(EmptyUtils.isNotEmpty(this.proxy)){
            result = this.proxy.get().isResumed();
        }
        return result;
    }

    public void postComponentPrepareing(){
        if(EmptyUtils.isNotEmpty(this.proxy)){
            this.proxy.get().onComponentPrepareing();
        }
    }

    public void postComponentPrepareFailured(){
        if(EmptyUtils.isNotEmpty(this.proxy)){
            this.proxy.get().onComponentPrepareFailured();
        }
    }

    public void postComponentPrepareSuccessed(){
        if(EmptyUtils.isNotEmpty(this.proxy)){
            this.proxy.get().onComponentPrepareSuccessed();
        }
    }

    public void postPluginEvent(String plugin, IEventEntity event){
        if(EmptyUtils.isNotEmpty(this.proxy)){
            this.proxy.get().onProcessEvent(plugin,event);
        }
    }
}

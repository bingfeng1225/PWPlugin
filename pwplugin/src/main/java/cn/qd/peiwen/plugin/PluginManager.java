package cn.qd.peiwen.plugin;


import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import cn.qd.peiwen.plugin.event.IEventEntity;
import cn.qd.peiwen.plugin.param.IParamEntity;

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
        if (TextUtils.isEmpty(name)) {
            return;
        }
        if (pluginType == null) {
            return;
        }
        this.pluginsType.put(name, pluginType);
        if (paramsType == null) {
            return;
        }
        this.paramsType.put(name, paramsType);
    }

    public Class<?> findParamType(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        return this.paramsType.get(name);
    }

    public Class<?> findPluginType(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        return this.pluginsType.get(name);
    }


    public boolean isResumed(){
        boolean result = false;
        if(null != this.proxy && null != this.proxy.get()){
            result = this.proxy.get().isResumed();
        }
        return result;
    }

    public void postComponentPrepareing(){
        if(null != this.proxy && null != this.proxy.get()){
            this.proxy.get().onComponentPrepareing();
        }
    }

    public void postComponentPrepareFailured(){
        if(null != this.proxy && null != this.proxy.get()){
            this.proxy.get().onComponentPrepareFailured();
        }
    }

    public void postComponentPrepareSuccessed(){
        if(null != this.proxy && null != this.proxy.get()){
            this.proxy.get().onComponentPrepareSuccessed();
        }
    }

    public void postPluginEvent(IEventEntity event){
        this.postPluginEvent(null,event);
    }

    public void postPluginEvent(String plugin, IEventEntity event){
        if(null != this.proxy && null != this.proxy.get()){
            this.proxy.get().onProxyProcessEvent(plugin,event);
        }
    }
}

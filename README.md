# Ezreal java 插件式类隔离容器

## 简介

   java 插件框架，正在写

## 已实现功能

 - 通过不同的 classloader 加载 plugin 
 
## 运行
 
 运行 `cn.fanhub.ezreal.core.container.ContainerManagerTest` 中的测试方法即可。
 
 
 - ezreal-testb-1.0-SNAPSHOT.jar 的主要内容：
 
```java

package cn.fanhub.ezreal.testtest;

import cn.fanhub.ezreal.core.plugin.EzPlugin;

/**
 *
 * @author chengfan
 * @version $Id: TestB.java, v 0.1 2018年03月15日 下午9:05 chengfan Exp $
 */
public class TestB implements EzPlugin {
    @Override
    public void init() {
        System.out.println("init b");
    }

    @Override
    public void execute() {
        System.out.println("execute b");
    }

    @Override
    public void destory() {
        System.out.println("destory b");
    }
}


```
ezreal.properties
```java
name=pluga
version=0.1
main=cn.fanhub.ezreal.test.TestAPlugin
```

- test-a-1.0-SNAPSHOT.jar 的主要内容：
 
```java

package cn.fanhub.ezreal.test;

import cn.fanhub.ezreal.core.plugin.EzPlugin;

/**
 *
 * @author chengfan
 * @version $Id: TestAPlugin.java, v 0.1 2018年03月15日 下午8:49 chengfan Exp $
 */
public class TestAPlugin implements EzPlugin {
    @Override
    public void init() {
        System.out.println("init a");
    }

    @Override
    public void execute() {
        System.out.println("execute a");
    }

    @Override
    public void destory() {
        System.out.println("des");
    }
}


```
ezreal.properties
```java
name=pluga
version=0.1
main=cn.fanhub.ezreal.test.TestAPlugin
```





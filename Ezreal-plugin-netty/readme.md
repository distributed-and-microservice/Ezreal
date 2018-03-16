# ezreal plugin for netty

## 测试请查看 TestNetty.java 

pluga 主要代码

cn.fanhub.ezreal.test.TestAPlugin

```java
package cn.fanhub.ezreal.test;

import cn.fanhub.ezreal.plugin.netty.NettyInboundHandlerPlugin;

/**
 *
 * @author chengfan
 * @version $Id: TestAPlugin.java, v 0.1 2018年03月15日 下午8:49 chengfan Exp $
 */
public class TestAPlugin extends NettyInboundHandlerPlugin {

}
```

ezreal.properties

```$xslt
name=pluga
version=0.1
main=cn.fanhub.ezreal.test.TestAPlugin
```
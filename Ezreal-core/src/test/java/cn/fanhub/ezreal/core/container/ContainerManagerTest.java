package cn.fanhub.ezreal.core.container;
import cn.fanhub.ezreal.core.context.ContainerContext;
import cn.fanhub.ezreal.core.plugin.EzPlugin;
import org.junit.Test;

import java.io.IOException;
import java.util.Map.Entry;

public class ContainerManagerTest {

    @Test
    public void createPlugin() throws IOException {
        ContainerManager.getInstance().createPlugins("plugin");
        for (Entry<String, EzPlugin> entry : ContainerContext.getPlugins().entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}

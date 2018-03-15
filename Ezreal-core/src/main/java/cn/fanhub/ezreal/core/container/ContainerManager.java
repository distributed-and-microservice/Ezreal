/**
 *
 *  Copyright 2018 chengfan
 *
 *  website: fanhub.cn
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.fanhub.ezreal.core.container;


import cn.fanhub.ezreal.core.classloader.EzClassLoaderFactory;
import cn.fanhub.ezreal.core.context.ContainerContext;
import cn.fanhub.ezreal.core.context.PluginContext;
import cn.fanhub.ezreal.core.model.SystemParam;
import cn.fanhub.ezreal.core.plugin.EzPlugin;
import cn.fanhub.ezreal.core.plugin.PluginConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 *
 * @author chengfan
 * @version $Id: ContainerManager.java, v 0.1 2018年03月15日 下午4:13 chengfan Exp $
 */
public class ContainerManager {

    private static ContainerManager containerManager;

    private EzClassLoaderFactory classLoaderFactory;

    private ContainerManager(){

    }

    public static ContainerManager getInstance() {
        if (containerManager == null) {
            containerManager = new ContainerManager();
            try {
                containerManager.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return containerManager;
    }

    public void init() throws IOException {
        classLoaderFactory = new EzClassLoaderFactory();
        //createPlugins(getBasePath());
    }

    public void createPlugins(String basePath) throws IOException {
        for (String pluginPath : scanPluginPath(basePath)) {
                File file = new File(pluginPath);


            ClassLoader loader = classLoaderFactory.getClassLoader(ContainerManager.class.getClassLoader(), file.toURI().toURL());

            try {
                Properties prop = new Properties();
                prop.load(loader.getResourceAsStream(SystemParam.CONFIG_FILE));
                PluginConfig config = initPluginContext(prop);


                Class<?> appClass = loader.loadClass(config.getMain());


                EzPlugin plugin = (EzPlugin)appClass.newInstance();

                plugin.init();


                ContainerContext.getPlugins().put(config.getName(), plugin);
                ContainerContext.getPluginClassloaderMap().put(config.getName(), loader);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }

    private PluginConfig initPluginContext(Properties prop) {
        PluginConfig config = new PluginConfig();
        config.setName(prop.getProperty("name"));
        config.setMain(prop.getProperty("main"));
        config.setVersion(prop.getProperty("version"));

        PluginContext.getPluginConfigMap().put(config.getName(), config);
        return config;
    }

    private List<String> scanPluginPath(String pluginPath) {

        List<String> plugins = new ArrayList<>();
        File folder = new File(pluginPath);
        if (!folder.isDirectory()) {
            throw new RuntimeException("The file path to scan for the plugins is not a directory, path:" + pluginPath);
        }

        for (File f : Objects.requireNonNull(folder.listFiles())) {
            if (!f.isFile()) {
                continue;
            }
            String name = f.getName();

            // check the file is a .jar file
            if (name.length() == 0) {
                continue;
            }

            int extIndex = name.lastIndexOf(".");
            if (extIndex < 0) {
                continue;
            }

            String ext = name.substring(extIndex);
            if (!ext.equalsIgnoreCase(".jar")) {
                continue;
            }

            plugins.add(pluginPath + "/" + name);
        }
        return plugins;
    }

    public String getBasePath(){
        return this.getClass().getClassLoader().getResource("").getPath();
    }
}
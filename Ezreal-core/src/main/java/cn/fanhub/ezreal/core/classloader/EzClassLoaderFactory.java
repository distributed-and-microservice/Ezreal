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
package cn.fanhub.ezreal.core.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author chengfan
 * @version $Id: EzClassLoaderFactory.java, v 0.1 2018年03月15日 下午3:39 chengfan Exp $
 */
public class EzClassLoaderFactory implements EzClassLoader {

    @Override
    public ClassLoader getClassLoader(ClassLoader parentClassLoader, URL pluginPath) throws MalformedURLException {


        return new URLClassLoader(new URL[]{pluginPath}, parentClassLoader);
    }

}
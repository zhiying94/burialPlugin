# Burial Plugin

一个用于 Android 方法计时和监控的 Gradle 插件。

## 功能特性

- 自动插桩 Android 应用中的方法调用
- 提供方法执行时间统计
- 支持自定义插桩规则和忽略列表
- 轻量级，对应用性能影响最小
- **新增：支持控制插桩范围（只插桩项目代码 vs 插桩所有代码）**

## 安装

### 插件依赖

在项目根目录的 `build.gradle` 文件中添加：

```groovy
buildscript {
  repositories {
    maven { url 'https://jitpack.io' }
  }
  dependencies {
    classpath 'com.github.zhiying94:burial-plugin:2.0.19'
  }
}
```

在应用模块的 `build.gradle` 文件中应用插件：

```groovy
apply plugin: 'com.github.zhiying94.burial-plugin'
```

### 库依赖

在应用模块的 `build.gradle` 文件中添加：

```groovy
dependencies {
  implementation "com.github.zhiying94.burialPlugin:burial-plugin:2.0.19"
}
```

## 配置

在应用模块的 `build.gradle` 文件中配置插件选项：

```groovy
burialExt {
  logEnable = true
  listenerWithMethodDetail = true
//  foreList = ['com.your.package']
  ignoreList = ['com.your.package.ignored']
  instrumentOnlyProjectCode = true  // 新增：控制插桩范围
}
```

### 配置参数说明

- `logEnable`: 是否启用日志输出
- `listenerWithMethodDetail`: 是否包含方法详细信息
- `foreList`: 白名单，只插桩指定包名的类
- `ignoreList`: 黑名单，跳过指定包名的类
- `instrumentOnlyProjectCode`: **新增参数**，控制插桩范围
  - `true`: 只插桩项目代码（默认值，推荐）
  - `false`: 插桩所有代码（包括依赖库）

## 使用方法

插件会自动插桩到你的应用中，无需额外的代码修改。

## 发布信息

- **插件版本**: 2.0.19
- **库版本**: 2.0.19
- **JitPack 仓库**: https://jitpack.io

## 许可证

Apache License 2.0

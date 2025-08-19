# Burial Plugin

一个用于 Android 方法计时和监控的 Gradle 插件。

## 功能特性

- 自动插桩 Android 应用中的方法调用
- 提供方法执行时间统计
- 支持自定义插桩规则和忽略列表
- 轻量级，对应用性能影响最小

## 安装

### 插件依赖

在项目根目录的 `build.gradle` 文件中添加：

```groovy
buildscript {
  repositories {
    maven { url 'https://jitpack.io' }
  }
  dependencies {
    classpath 'com.github.zhiying94:burial-plugin:2.0.10'
  }
}
```

在应用模块的 `build.gradle` 文件中应用插件：

```groovy
apply plugin: 'io.github.zhiying94.burial-plugin'
```

### 库依赖

在应用模块的 `build.gradle` 文件中添加：

```groovy
dependencies {
  implementation 'com.github.zhiying94:burialTimer:2.0.3'
}
```

## 配置

在应用模块的 `build.gradle` 文件中配置插件选项：

```groovy
burialExt {
  logEnable = true
  listenerWithMethodDetail = true
  ignoreList = ['com.example.ignored.package']
}
```

## 使用方法

插件会自动插桩到你的应用中，无需额外的代码修改。

## 发布信息

- **插件版本**: 2.0.10
- **库版本**: 2.0.3
- **JitPack 仓库**: https://jitpack.io

## 许可证

Apache License 2.0

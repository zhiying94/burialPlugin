# JitPack 发布指南

本文档说明如何将 `burial-plugin` 和 `burialTimer` 发布到 JitPack。

## 发布前准备

### 1. 确保代码已提交到 GitHub

```bash
git add .
git commit -m "准备发布到 JitPack: 插件版本 2.0.10, 库版本 2.0.3"
git push origin main
```

### 2. 创建 Git 标签

```bash
# 为插件创建标签
git tag -a v2.0.10 -m "插件版本 2.0.10"
git push origin v2.0.10

# 为库创建标签
git tag -a v2.0.3 -m "库版本 2.0.3"
git push origin v2.0.3
```

## JitPack 配置

### 1. jitpack.yml 配置

项目根目录的 `jitpack.yml` 文件已配置好：

```yaml
jvm:
  - 17
install:
  - ./gradlew -S -i :burial_plugin:publishReleasePublicationToMavenLocal
  - ./gradlew -S -i :burialTimer:publishReleasePublicationToMavenLocal
before_install:
  - ./gradlew clean
build:
  - ./gradlew :burial_plugin:build
  - ./gradlew :burialTimer:build
```

### 2. 模块配置

#### burial_plugin 模块

- `group`: `com.github.zhiying94`
- `artifactId`: `burial-plugin`
- `version`: `2.0.10`

#### burialTimer 模块

- `group`: `com.github.zhiying94`
- `artifactId`: `burialTimer`
- `version`: `2.0.3`

## 发布步骤

### 1. 访问 JitPack

打开浏览器访问：https://jitpack.io

### 2. 登录 GitHub 账号

使用 GitHub 账号登录 JitPack。

### 3. 查找仓库

在搜索框中输入：`zhiying94/burialPlugin`

### 4. 发布版本

点击对应的 Git 标签（如 `v2.0.10` 或 `v2.0.3`）进行发布。

### 5. 等待构建完成

JitPack 会自动构建项目，构建成功后会在版本旁边显示绿色勾号。

## 使用方式

### 插件依赖

```groovy
buildscript {
  repositories {
    maven { url 'https://jitpack.io' }
  }
  dependencies {
    classpath 'com.github.zhiying94:burial-plugin:2.0.10'
  }
}

apply plugin: 'io.github.zhiying94.burial-plugin'
```

### 库依赖

```groovy
dependencies {
  implementation 'com.github.zhiying94:burialTimer:2.0.3'
}
```

## 验证发布

### 1. 检查 JitPack 页面

确保版本显示为绿色（构建成功）。

### 2. 测试依赖

在其他项目中测试依赖是否能正常使用。

### 3. 检查 Maven 坐标

验证 Maven 坐标是否正确：
- `com.github.zhiying94:burial-plugin:2.0.10`
- `com.github.zhiying94:burialTimer:2.0.3`

## 故障排除

### 构建失败

1. 检查 `jitpack.yml` 配置
2. 确保所有依赖都能正常解析
3. 检查 Java 版本兼容性

### 依赖无法解析

1. 确认版本已成功发布
2. 检查 Maven 坐标是否正确
3. 确保项目中添加了 JitPack 仓库

## 注意事项

1. 每次发布新版本都需要创建新的 Git 标签
2. 版本号必须遵循语义化版本规范
3. 确保代码能正常构建
4. 发布后可能需要等待几分钟才能使用

## 联系支持

如果遇到问题，可以：
1. 查看 JitPack 构建日志
2. 在 GitHub 上提交 Issue
3. 参考 JitPack 官方文档

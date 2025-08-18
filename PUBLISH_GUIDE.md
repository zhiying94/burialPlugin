# Burial Plugin 发布指南

## 概述

Burial Plugin 是一个用于 Android 方法耗时统计的 Gradle 插件，支持编译期字节码插桩。本文档介绍如何将插件发布到公共仓库。

## 发布配置

### 1. Maven Central (推荐)

#### 1.1 申请 Sonatype OSSRH 账号

1. 访问 [Sonatype OSSRH](https://s01.oss.sonatype.org/)
2. 注册账号并创建工单申请发布权限
3. 等待审核通过

#### 1.2 配置 GPG 签名

```bash
# 生成 GPG 密钥
gpg --gen-key

# 导出公钥
gpg --armor --export your-email@example.com > public-key.txt

# 导出私钥
gpg --armor --export-secret-key your-email@example.com > private-key.txt
```

#### 1.3 配置凭据

在 `~/.gradle/gradle.properties` 中添加：

```properties
# Sonatype OSSRH 凭据
ossrhUsername=your-sonatype-username
ossrhPassword=your-sonatype-password

# GPG 签名配置
signing.keyId=your-gpg-key-id
signing.password=your-gpg-password
signing.secretKeyRingFile=~/.gnupg/secring.gpg
```

#### 1.4 发布

```bash
# 发布到 Maven Central
./gradlew -b scripts/publish.gradle publishToMavenCentral

# 发布到本地 Maven 仓库（测试用）
./gradlew -b scripts/publish.gradle publishToLocalMaven
```

### 2. GitHub Packages

#### 2.1 配置 GitHub Token

1. 在 GitHub 设置中生成 Personal Access Token
2. 在 `~/.gradle/gradle.properties` 中添加：

```properties
githubUsername=your-github-username
githubToken=your-github-token
```

#### 2.2 修改发布配置

在 `burial_plugin/build.gradle` 中添加 GitHub Packages 仓库：

```groovy
repositories {
    maven {
        name = 'GitHubPackages'
        url = uri("https://maven.pkg.github.com/your-username/burial-plugin")
        credentials {
            username = findProperty('githubUsername') ?: System.getenv('GITHUB_USERNAME')
            password = findProperty('githubToken') ?: System.getenv('GITHUB_TOKEN')
        }
    }
}
```

### 3. JitPack

#### 3.1 推送到 GitHub

```bash
git add .
git commit -m "Release v2.0.0"
git tag v2.0.0
git push origin main
git push origin v2.0.0
```

#### 3.2 配置 JitPack

在项目根目录创建 `jitpack.yml`：

```yaml
jvm:
  - 17
before_install:
  - ./gradlew clean
install:
  - ./gradlew build
```

## 使用方式

### 插件使用

```groovy
// settings.gradle
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

// build.gradle
plugins {
    id 'com.yan.burial.burial-plugin' version '2.0.0'
}

burialExt {
    logEnable = true
    listenerWithMethodDetail = true
    runVariant = 'ALWAYS'
    ignoreList = ['com.example.ignore']
}
```

### 库使用

```groovy
dependencies {
    implementation 'com.yan.burial.method.timer:burial-timer:2.0.0'
}
```

## 版本管理

### 版本号规范

- 主版本号.次版本号.修订号 (如: 2.0.0)
- 快照版本使用 `-SNAPSHOT` 后缀 (如: 2.0.1-SNAPSHOT)

### 发布流程

1. 更新版本号
2. 更新 CHANGELOG.md
3. 提交代码并打标签
4. 执行发布命令
5. 验证发布结果

## 常见问题

### Q: 发布失败，提示签名错误
A: 检查 GPG 密钥配置是否正确，确保密钥已上传到公钥服务器

### Q: 发布到 Maven Central 后无法立即使用
A: Maven Central 有同步延迟，通常需要几分钟到几小时

### Q: 如何回滚版本
A: 发布到 Maven Central 的版本无法删除，只能发布新版本

## 联系方式

如有问题，请通过以下方式联系：

- GitHub Issues: [项目地址]
- Email: your-email@example.com

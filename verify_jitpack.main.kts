#!/usr/bin/env kotlin

/**
 * JitPack 验证脚本 (Kotlin DSL)
 * 用于验证远程依赖和插件是否可用
 */

@file:Repository("https://jitpack.io")
@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("com.github.zhiying94:burialTimer:2.0.6")
@file:DependsOn("com.github.zhiying94:burial-plugin:2.0.13")

import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.exitProcess

println("🚀 开始验证 JitPack 发布状态...")

// 验证依赖URL
val dependencies = listOf(
    "burialTimer" to "https://jitpack.io/com/github/zhiying94/burialTimer/2.0.6/burialTimer-2.0.6.pom",
    "burial-plugin" to "https://jitpack.io/com/github/zhiying94/burial-plugin/2.0.13/burial-plugin-2.0.13.pom"
)

var allSuccess = true

dependencies.forEach { (name, url) ->
    try {
        println("🔍 检查 $name...")
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "HEAD"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        
        val responseCode = connection.responseCode
        when (responseCode) {
            200 -> {
                println("✅ $name 可用 (HTTP $responseCode)")
            }
            401 -> {
                println("⏳ $name 正在构建中 (HTTP $responseCode)")
                allSuccess = false
            }
            404 -> {
                println("❌ $name 未找到 (HTTP $responseCode)")
                allSuccess = false
            }
            else -> {
                println("⚠️  $name 状态未知 (HTTP $responseCode)")
                allSuccess = false
            }
        }
        connection.disconnect()
    } catch (e: Exception) {
        println("❌ $name 检查失败: ${e.message}")
        allSuccess = false
    }
}

println()
if (allSuccess) {
    println("🎉 所有依赖都已成功发布到 JitPack！")
    println("📋 使用方法:")
    println("   插件: id 'com.github.zhiying94.burial-plugin' version '2.0.13'")
    println("   依赖: implementation 'com.github.zhiying94:burialTimer:2.0.6'")
} else {
    println("⚠️  部分依赖尚未完成构建，请稍后重试")
    println("🔗 JitPack 构建状态: https://jitpack.io/com/github/zhiying94/burialPlugin")
}

println("\n✨ 验证完成")

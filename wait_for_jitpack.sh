#!/bin/bash

echo "🚀 开始持续监控 JitPack 构建状态..."
echo "📦 等待版本: v2.0.16"
echo "🔗 构建页面: https://jitpack.io/com/github/zhiying94/burialPlugin"
echo ""

max_wait=1800  # 最多等待 30 分钟
check_interval=30  # 每 30 秒检查一次
elapsed=0

while [ $elapsed -lt $max_wait ]; do
    echo "⏰ 已等待: ${elapsed}秒"
    
    # 检查 burialTimer
    response=$(curl -s -o /dev/null -w "%{http_code}" "https://jitpack.io/com/github/zhiying94/burialTimer/2.0.16/burialTimer-2.0.16.pom")
    
    if [ "$response" = "200" ]; then
        echo "✅ burialTimer 构建成功！"
        
        # 检查 burial-plugin
        response=$(curl -s -o /dev/null -w "%{http_code}" "https://jitpack.io/com/github/zhiying94/burial-plugin/2.0.16/burial-plugin-2.0.16.pom")
        
        if [ "$response" = "200" ]; then
            echo "🎉 所有模块构建成功！"
            echo ""
            echo "📋 使用方法:"
            echo "   插件: id 'com.github.zhiying94.burial-plugin' version '2.0.16'"
            echo "   依赖: implementation 'com.github.zhiying94:burialTimer:2.0.16'"
            echo ""
            echo "✨ 验证完成！"
            exit 0
        else
            echo "⏳ burial-plugin 仍在构建中 (HTTP $response)"
        fi
    else
        echo "⏳ burialTimer 仍在构建中 (HTTP $response)"
    fi
    
    echo "🔄 等待 ${check_interval} 秒后重试..."
    echo "----------------------------------------"
    
    sleep $check_interval
    elapsed=$((elapsed + check_interval))
done

echo "⏰ 等待超时 (${max_wait}秒)"
echo "❌ JitPack 构建可能失败，请检查构建日志"
echo "🔗 构建日志: https://jitpack.io/com/github/zhiying94/burialPlugin/v2.0.13/build.log"
exit 1

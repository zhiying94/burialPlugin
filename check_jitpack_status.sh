#!/bin/bash

echo "🚀 检查 JitPack 构建状态..."

# 检查 burialTimer
echo "🔍 检查 burialTimer:2.0.15..."
response=$(curl -s -o /dev/null -w "%{http_code}" "https://jitpack.io/com/github/zhiying94/burialTimer/2.0.15/burialTimer-2.0.15.pom")
case $response in
    200)
        echo "✅ burialTimer 可用 (HTTP $response)"
        ;;
    401)
        echo "⏳ burialTimer 正在构建中 (HTTP $response)"
        ;;
    404)
        echo "❌ burialTimer 未找到 (HTTP $response)"
        ;;
    *)
        echo "⚠️  burialTimer 状态未知 (HTTP $response)"
        ;;
esac

# 检查 burial-plugin
echo "🔍 检查 burial-plugin:2.0.15..."
response=$(curl -s -o /dev/null -w "%{http_code}" "https://jitpack.io/com/github/zhiying94/burial-plugin/2.0.15/burial-plugin-2.0.15.pom")
case $response in
    200)
        echo "✅ burial-plugin 可用 (HTTP $response)"
        ;;
    401)
        echo "⏳ burial-plugin 正在构建中 (HTTP $response)"
        ;;
    404)
        echo "❌ burial-plugin 未找到 (HTTP $response)"
        ;;
    *)
        echo "⚠️  burial-plugin 状态未知 (HTTP $response)"
        ;;
esac

echo ""
echo "🔗 JitPack 构建状态页面:"
echo "   https://jitpack.io/com/github/zhiying94/burialPlugin"
echo ""
echo "📋 使用方法:"
echo "   插件: id 'com.github.zhiying94.burial-plugin' version '2.0.15'"
echo "   依赖: implementation 'com.github.zhiying94:burialTimer:2.0.15'"
echo ""
echo "✨ 检查完成"

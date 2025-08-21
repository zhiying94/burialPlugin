package com.yan.burial;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.android.build.api.instrumentation.InstrumentationParameters;

import org.objectweb.asm.ClassVisitor;

public abstract class BurialAsmFactory implements AsmClassVisitorFactory<InstrumentationParameters.None> {

    private static volatile BurialExtension sExtension;

    static void setExtension(BurialExtension extension) {
        sExtension = extension;
    }

    @Override
    public ClassVisitor createClassVisitor(ClassContext classContext, ClassVisitor nextClassVisitor) {
        return new BurialClassAdapter(nextClassVisitor, sExtension);
    }

    @Override
    public boolean isInstrumentable(ClassData classData) {
        String className = classData.getClassName().replace('/', '.');

        if (className.contains(BurialExtension.PLUGIN_LIBRARY)) {
            return false;
        }
        if (sExtension != null) {
            // 如果有白名单，优先使用白名单
            if (!sExtension.foreList.isEmpty()) {
                return sExtension.isInWhitelist(className);
            }
            // 如果有黑名单，跳过黑名单中的类
            if (sExtension.isInBlacklist(className)) {
                return false;
            }
            if (className.startsWith("kotlin.") || className.startsWith("java.") || className.startsWith("android.") || className.startsWith("androidx.")) {
                // 如果只插桩项目代码，且是系统类，则不插桩
                return false;
            }
            // 默认插桩（InstrumentationScope 已经控制了范围）
            return true;
        }

        return true;
    }
}



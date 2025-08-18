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
        if (className.contains(BurialExtension.PLUGIN_LIBRARY)) return false;
        if (sExtension != null) {
            if (!sExtension.foreList.isEmpty()) return sExtension.isInWhitelist(className);
            return !sExtension.isInBlacklist(className);
        }
        return true;
    }
}



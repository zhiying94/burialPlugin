package com.yan.burial;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationParameters;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class BurialPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        BurialExtension burialExtension = project.getExtensions().create("burialExt", BurialExtension.class);

        AndroidComponentsExtension<?, ?, ?> androidComponents =
                project.getExtensions().getByType(AndroidComponentsExtension.class);

        BurialAsmFactory.setExtension(burialExtension);
        androidComponents.onVariants(androidComponents.selector().all(), variant -> {
            // 根据配置动态设置插桩范围
            InstrumentationScope scope = burialExtension.instrumentOnlyProjectCode ? 
                InstrumentationScope.PROJECT : InstrumentationScope.ALL;
            
            variant.getInstrumentation().transformClassesWith(BurialAsmFactory.class, scope, parameters -> null);
            variant.getInstrumentation().setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES);
        });
    }
}

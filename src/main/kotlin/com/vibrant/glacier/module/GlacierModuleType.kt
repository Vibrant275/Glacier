package com.vibrant.glacier.module

import com.intellij.openapi.module.ModuleType
import com.vibrant.glacier.utils.Extensions
import com.vibrant.glacier.utils.GlacierIcons
import com.vibrant.glacier.utils.GlacierIcons.FrameworkIcon
import org.jetbrains.jps.model.module.JpsModuleSourceRootType
import javax.swing.Icon

class GlacierModuleType : ModuleType<GlacierModuleBuilder>("GLACIER_MODULE") {
    override fun createModuleBuilder(): GlacierModuleBuilder {
        return GlacierModuleBuilder()
    }

    override fun getName(): String {
        return "Glacier"
    }

    override fun getDescription(): String {
        return "A module for developing applications using the Glacier framework with support for multiple platforms."
    }

    val bigIcon: Icon?
        get() = null

    override fun getNodeIcon(isOpened: Boolean): Icon {
        return FrameworkIcon
    }

    companion object {
        val instance: GlacierModuleType = GlacierModuleType()
    }
}

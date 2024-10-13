package com.vibrant.glacier.module

import GlacierProjectWizard
import com.intellij.ide.util.projectWizard.*
import com.intellij.openapi.Disposable
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.Messages
import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import java.awt.Color
import java.awt.Cursor
import java.awt.Desktop
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.LineBorder

class GlacierModuleBuilder : ModuleBuilder(), ModuleBuilderListener {

    override fun getModuleType(): ModuleType<*> {
        return GlacierModuleType.instance
    }

    override fun moduleCreated(module: Module) {
    }

    override fun getCustomOptionsStep(context: WizardContext?, parentDisposable: Disposable?): ModuleWizardStep? {
        return GlacierProjectWizard()
    }

    override fun modifySettingsStep(settingsStep: SettingsStep): ModuleWizardStep? {
        val projectTypeComboBox = ComboBox(arrayOf("Application", "Library"))

        val androidCheckBox = JCheckBox("Android", true)
        val iosCheckBox = JCheckBox("iOS", true)
        val windowsCheckBox = JCheckBox("Windows", true)
        val macosCheckBox = JCheckBox("macOS", true)
        val linuxCheckBox = JCheckBox("Linux", true)

        val checkBoxSpace = 10

        // Panel for platforms checkboxes with padding and space between checkboxes
        val platformsPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS) // Change to vertical alignment
            add(androidCheckBox)
            add(Box.createHorizontalStrut(checkBoxSpace)) // Space between checkboxes
            add(iosCheckBox)
            add(Box.createHorizontalStrut(checkBoxSpace)) // Space between checkboxes
            add(windowsCheckBox)
            add(Box.createHorizontalStrut(checkBoxSpace)) // Space between checkboxes
            add(macosCheckBox)
            add(Box.createHorizontalStrut(checkBoxSpace)) // Space between checkboxes
            add(linuxCheckBox)
        }

        val organizationField = JTextField()
        organizationField.text = "com.example"

        val helpPanelSpacer = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(Box.createVerticalStrut(130))
        }

        val helpPanel = JPanel().apply {

            border = LineBorder(JBColor.LIGHT_GRAY, 2, true) // 2px border width with rounded corners
            layout = BoxLayout(this, BoxLayout.Y_AXIS)

            add(Box.createVerticalStrut(10))

            // Row layout for "Help:" and clickable "Visit site"
            val helpRow = JPanel().apply {
                layout = BoxLayout(this, BoxLayout.X_AXIS)
                add(Box.createHorizontalStrut(10))
                add(JLabel("<html><b>Help:</b></html>"))
                add(Box.createHorizontalStrut(5))
                val visitSiteLabel = JLabel("<html><font color='#0099FF'>Get Started with your first Glacier Software</font></html>").apply {
                    addMouseListener(object : MouseAdapter() {
                        override fun mouseClicked(e: MouseEvent?) {
                            Desktop.getDesktop().browse(URI("https://glacier.com/docs/getting_started"))
                        }

                        override fun mouseEntered(e: MouseEvent?) {
                            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) // Change cursor on hover
                        }

                        override fun mouseExited(e: MouseEvent?) {
                            cursor = Cursor.getDefaultCursor() // Reset cursor
                        }
                    })
                }
                add(visitSiteLabel) // Add the clickable text
            }

            add(helpRow) // Add the row to the panel
            add(Box.createVerticalStrut(10)) // Vertical space at the bottom
        }


        settingsStep.addSettingsField("Project type:", projectTypeComboBox)
        settingsStep.addSettingsField("Organization:", organizationField)
        settingsStep.addSettingsField("Platforms:", platformsPanel)
        settingsStep.addSettingsComponent(helpPanelSpacer)
        settingsStep.addSettingsComponent(helpPanel)


        return super.modifySettingsStep(settingsStep)
    }

    // Function to show help dialog
    private fun showHelpDialog() {
        val message = "For more information, please visit our website:\n\n" +
                "https://www.example.com\n\n" +
                "You can find documentation, tutorials, and support there."
        Messages.showMessageDialog(message, "Help", Messages.getInformationIcon())
    }
}
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBOptionButton
import com.intellij.ui.components.JBTextField
import javax.swing.*
import java.awt.*

class GlacierProjectWizard : ModuleWizardStep() {
    private val panel = JPanel().apply {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
    }

    private val projectNameField = JBTextField()
    private val platformComboBox = ComboBox(arrayOf("Android", "iOS", "Windows", "macOS"))

    init {
        // Add components to the panel
        panel.add(JBLabel("Project Name:"))
        panel.add(projectNameField)
        panel.add(JBLabel("Select Platform:"))
        panel.add(platformComboBox)

        // Create button to finalize project creation
        val createButton = JButton("Create").apply {
            addActionListener {
                handleCreate()
            }
        }

        panel.add(createButton)
    }

    private fun handleCreate() {
        // Logic to create the project
        val projectName = projectNameField.text
        val selectedPlatform = platformComboBox.selectedItem as String

        if (projectName.isBlank()) {
            showError("Project name cannot be empty.")
            return
        }

        // Call the method to create the project with the specified name and platform
        createProject(projectName, selectedPlatform)
    }

    private fun createProject(name: String, platform: String) {
        // Implementation for creating the project
        println("Creating project '$name' for platform '$platform'")
        // Add your project creation logic here
    }

    private fun showError(message: String) {
        // Show an error dialog
        JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.ERROR_MESSAGE)
    }

    override fun getComponent(): JComponent {
        return panel
    }

    override fun updateDataModel() {
        // Update the model according to the UI
        // You can store the values from the input fields into your module's properties
    }

    override fun isStepVisible(): Boolean {
        return super.isStepVisible()
    }
}

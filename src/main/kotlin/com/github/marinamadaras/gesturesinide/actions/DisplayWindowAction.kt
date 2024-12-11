import com.github.marinamadaras.gesturesinide.managers.WindowManager
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class DisplayWindowAction : AnAction("Display Window",
                            "Display Window with Meme in it",
                            AllIcons.Ide.Gift){

    /**
     * Displays the window with which you interact in the application,
     * upon pressing the "Display Window" action from the "Tools" menu.
     */
    override fun actionPerformed(e: AnActionEvent) {
        WindowManager.showWindow()
    }
}
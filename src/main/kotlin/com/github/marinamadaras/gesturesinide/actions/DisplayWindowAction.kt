import com.github.marinamadaras.gesturesinide.visuals.managers.WindowManager
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class DisplayWindowAction : AnAction("Display Window",
                            "Display Window with Meme in it",
                            AllIcons.Ide.Gift){

    override fun actionPerformed(e: AnActionEvent) {
        WindowManager.showWindow()
    }
}
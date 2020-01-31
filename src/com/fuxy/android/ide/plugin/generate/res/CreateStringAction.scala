package com.fuxy.android.ide.plugin.generate.res

import com.fuxy.android.ide.plugin.generate.BaseAndroidGenerateCodeAction
import com.fuxy.android.ide.plugin.ui.CreateStringDialog
import com.fuxy.android.ide.plugin.utils.{AndroidStringInfo, AndroidUtils}
import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.util.PsiUtilBase
import org.jetbrains.annotations.NotNull

/**
 * Created by xiuyuan.fuxy on 2015/3/24.
 */
class CreateStringAction (handler:CodeInsightActionHandler) extends BaseAndroidGenerateCodeAction(handler) {
  def this() = this(null)
  override def actionPerformedImpl(@NotNull project: Project ,@NotNull editor: Editor): Unit = {
//    import scala.collection.JavaConversions._
//    val cursorPosition = editor.getCaretModel.getOffset
//    val arrayList = new util.ArrayList[VirtualFile]()
//    project.getProjectFile.getChildren.foreach(arrayList.add _ )
    val f = PsiUtilBase.getPsiFileInEditor(editor, project)
    val sFile = AndroidUtils.findXmlResourceByName(project,"strings.xml")
    val strList = AndroidUtils.getStringFromXML(sFile)
//    val notification = new Notification("test","title","content",NotificationType.WARNING)
//    Notifications.Bus.notify(notification)
    val dialog = new CreateStringDialog((str:AndroidStringInfo)=>{
        val writer = new CreateStringWriter(sFile,project,editor,str,getTargetClass(editor, f),"create string")
        writer.execute()
    },project)
    dialog.setSize(400,800)
    dialog.show()
  }
}

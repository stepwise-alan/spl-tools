import de.fosd.typechef.parser.c.TranslationUnit

import scala.sys.process.{Process, ProcessLogger}

object CleverSelfComposer extends SelfComposer {
  override def apply(oldTranslationUnit: TranslationUnit, oldFunctionName: String,
                     newTranslationUnit: TranslationUnit, newFunctionName: String): TranslationUnit = {
    apply(oldTranslationUnit, oldFunctionName, newTranslationUnit, newFunctionName, Array())
  }

  def apply(oldTranslationUnit: TranslationUnit, oldFunctionName: String,
            newTranslationUnit: TranslationUnit, newFunctionName: String,
            frontendArgs: Array[String]): TranslationUnit = {
    val oldFilepath = "old.c"
    Util.writeToFile(oldTranslationUnit, oldFilepath)
    val newFilepath = "new.c"
    Util.writeToFile(newTranslationUnit, newFilepath)
    assert(oldFunctionName == newFunctionName)
    val outputFilePath = "0.c"
    Process(f"python3 ./src/main/py/self_composer.py " +
      f"--old $oldFilepath --new $newFilepath --function $oldFunctionName --out $outputFilePath"
    ).!!(ProcessLogger(_ => ()))
    Util.parse(frontendArgs :+ outputFilePath)._1
  }

  //  def main(args: Array[String]): Unit = {
  //    val oldTranslationUnit = Util.parse(Array("new_converted.c"))._1
  //    val newTranslationUnit = Util.parse(Array("old_converted.c"))._1
  //    val oldFunctionName = "lib"
  //    val newFunctionName = "lib"
  //    Util.writeToString(CleverSelfComposer(oldTranslationUnit, oldFunctionName, newTranslationUnit, newFunctionName))
  //  }
}

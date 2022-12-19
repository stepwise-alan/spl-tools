import de.fosd.typechef.parser.c.TranslationUnit

trait SelfComposer {
  def apply(oldTranslationUnit: TranslationUnit, oldFunctionName: String,
            newTranslationUnit: TranslationUnit, newFunctionName: String): TranslationUnit
}

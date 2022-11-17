import scala.sys.process.Process

object LiftedEquivalenceCheckerV1 {
  def main(args: Array[String]): Unit = {
    val (oldTranslationUnit, _, oldFeatures) = Util.parse(Array(args(0)))
    val (newTranslationUnit, featureModel, newFeatures) = Util.parse(Array(args(1)))
    val features = newFeatures ++ oldFeatures

    VariabilitySearcher(featureModel)(oldTranslationUnit)
    VariabilitySearcher(featureModel)(newTranslationUnit)

    val oldSuperposition = Superposer(featureModel)(oldTranslationUnit, features)
    val newSuperposition = Superposer(featureModel)(newTranslationUnit, features)

    val selfComposition = CleverSelfComposer(oldSuperposition, args(2), newSuperposition, args(2))

    val selfCompositionFilepath = "self_composed.c"
    Util.writeToFile(selfComposition, selfCompositionFilepath)
    Process(f"python3 src/main/py/vamc.py " +
      f"$selfCompositionFilepath --features ${features.toList} --out ."
    ).!!<
  }
}

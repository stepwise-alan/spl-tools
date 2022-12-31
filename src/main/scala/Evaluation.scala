import org.sat4j.specs.TimeoutException

import java.io.{BufferedWriter, File, FileOutputStream, FileWriter, PrintStream}
import java.lang.System.currentTimeMillis
import java.nio.file.{Files, Paths}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.concurrent.*
import scala.concurrent.duration.*
import scala.util.{Failure, Success}
import ExecutionContext.Implicits.global

object Evaluation {
  def main(args: Array[String]): Unit = {
    val timeString = LocalDateTime.now.format(DateTimeFormatter.ofPattern("YYYYMMdd_HHmmss"))
    val outDirectory = s"/home/shuolin/$timeString"
    Files.createDirectories(Paths.get(outDirectory))
    val filepath = s"$outDirectory/results.txt"
    val bw = new BufferedWriter(new FileWriter(new File(filepath)))
    //    val timeout = 600000
    val timeout = 10.minutes
    for (i <- 1 to 5) {
      for (newFeatureCount <- List(8, 9, 7, 10, 6)) {
        val directory = s"/home/shuolin/IdeaProjects/spl-tools/examples/hard/$newFeatureCount"
        Files.createDirectories(Paths.get(directory))
        for ((oldFilename, newFilename) <- List(
          ("old.c", "new.c"),
          ("old.fe.c", "new.fe.c"),
          ("old.op.c", "new.op.c"),
          ("old.op.fe.c", "new.op.fe.c")
        )) {
          bw.write(s"Alg 1, SPL $i, $newFeatureCount new features, $oldFilename, $newFilename, ")
          print(s"Alg 1, SPL $i, $newFeatureCount new features, $oldFilename, $newFilename, ")
          try {
            val t0 = currentTimeMillis()
            val outStream = new FileOutputStream(s"$directory/alg1.$i.$oldFilename.$newFilename.out1.txt")
            val errStream = new FileOutputStream(s"$directory/alg1.$i.$oldFilename.$newFilename.err1.txt")
            System.setOut(new PrintStream(new FileOutputStream(s"$directory/alg1.$i.$oldFilename.$newFilename.out2.txt")))
            System.setErr(new PrintStream(new FileOutputStream(s"$directory/alg1.$i.$oldFilename.$newFilename.err2.txt")))
            val t1 = Await.result(Future {
              Console.withOut(outStream) {
                Console.withErr(errStream) {
                  EquivalenceChecker.main(Array(
                    s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.hard.spl$i/$oldFilename",
                    s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.hard.spl$i/$newFilename",
                    "sortcmp",
                    "/home/shuolin/CLionProjects/seahorn/build/run/bin/sea",
                    "/home/shuolin/CLionProjects/seahorn/build/run/bin/z3",
                    "/home/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis",
                  ))
                }
              }
              currentTimeMillis()
            }, timeout)
            System.setOut(System.out)
            System.setErr(System.err)
            bw.write(s"${t1 - t0}")
            println(s"${t1 - t0}")
          } catch {
            case _: concurrent.TimeoutException =>
              System.setOut(System.out)
              System.setErr(System.err)
              bw.write("timeout")
              println("timeout")
            case e: Throwable =>
              System.setOut(System.out)
              System.setErr(System.err)
              bw.write("error")
              println("error")
              println(e.getMessage)
          }
          bw.write("\n")

          bw.write(s"Alg 1 & 2, SPL $i, $newFeatureCount new features, $oldFilename, $newFilename, ")
          print(s"Alg 1 & 2, SPL $i, $newFeatureCount new features, $oldFilename, $newFilename, ")
          try {
            val t0 = currentTimeMillis()
            val outStream = new FileOutputStream(s"$directory/alg12.$i.$oldFilename.$newFilename.out1.txt")
            val errStream = new FileOutputStream(s"$directory/alg12.$i.$oldFilename.$newFilename.err1.txt")
            System.setOut(new PrintStream(new FileOutputStream(s"$directory/alg12.$i.$oldFilename.$newFilename.out2.txt")))
            System.setErr(new PrintStream(new FileOutputStream(s"$directory/alg12.$i.$oldFilename.$newFilename.err2.txt")))
            val t1 = Await.result(Future {
              Console.withOut(outStream) {
                Console.withErr(errStream) {
                  EquivalenceCheckerV1.main(Array(
                    s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.hard.spl$i/$oldFilename",
                    s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.hard.spl$i/$newFilename",
                    "sortcmp",
                    "/home/shuolin/CLionProjects/seahorn/build/run/bin/sea",
                    "/home/shuolin/CLionProjects/seahorn/build/run/bin/z3",
                    "/home/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis",
                  ))
                }
              }
              currentTimeMillis()
            }, timeout)
            System.setOut(System.out)
            System.setErr(System.err)
            bw.write(s"${t1 - t0}")
            println(s"${t1 - t0}")
          } catch {
            case _: concurrent.TimeoutException =>
              System.setOut(System.out)
              System.setErr(System.err)
              bw.write("timeout")
              println("timeout")
            case e: Throwable =>
              System.setOut(System.out)
              System.setErr(System.err)
              bw.write("error")
              println("error")
              println(e.getMessage)
          }
          bw.write("\n")

          bw.write(s"Baseline, SPL $i, $newFeatureCount new features, $oldFilename, $newFilename, ")
          print(s"Baseline, SPL $i, $newFeatureCount new features, $oldFilename, $newFilename, ")
          try {
            val t0 = currentTimeMillis()
            val outStream = new FileOutputStream(s"$directory/baseline.$i.$oldFilename.$newFilename.out1.txt")
            val errStream = new FileOutputStream(s"$directory/baseline.$i.$oldFilename.$newFilename.err1.txt")
            System.setOut(new PrintStream(new FileOutputStream(s"$directory/baseline.$i.$oldFilename.$newFilename.out2.txt")))
            System.setErr(new PrintStream(new FileOutputStream(s"$directory/baseline.$i.$oldFilename.$newFilename.err2.txt")))
            val t1 = Await.result(Future {
              Console.withOut(outStream) {
                Console.withErr(errStream) {
                  BaselineEquivalenceChecker.main(Array(
                    s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.hard.spl$i/$oldFilename",
                    s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.hard.spl$i/$newFilename",
                    "sortcmp",
                    "/home/shuolin/CLionProjects/seahorn/build/run/bin/sea",
                    "/home/shuolin/CLionProjects/seahorn/build/run/bin/z3",
                    "/home/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis",
                  ))
                }
              }
              currentTimeMillis()
            }, timeout)
            System.setOut(System.out)
            System.setErr(System.err)
            bw.write(s"${t1 - t0}")
            println(s"${t1 - t0}")
          } catch {
            case _: concurrent.TimeoutException =>
              System.setOut(System.out)
              System.setErr(System.err)
              bw.write("timeout")
              println("timeout")
            case e: Throwable =>
              System.setOut(System.out)
              System.setErr(System.err)
              bw.write("error")
              println("error")
              println(e.getMessage)
          }
          bw.write("\n")
        }
      }
    }

    bw.close()
  }
}

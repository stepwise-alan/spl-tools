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
    val parDirectory = s"/home/shuolin/$timeString"
    println(parDirectory)
    Files.createDirectories(Paths.get(parDirectory))
    val filepath = s"$parDirectory/results.txt"
    val bw = new BufferedWriter(new FileWriter(new File(filepath)))
    val timeout = 10.minutes
    for (i <- 1 to 6) {
      for (newFeatureCount <- 1 to 13) {
        val directory = s"$parDirectory/$newFeatureCount"
        Files.createDirectories(Paths.get(directory))
        for ((oldFilename, newFilename) <- List(
          ("old.c", "new.c"),
          ("old.fe.c", "new.fe.c"),
          ("old.op.c", "new.op.c"),
          ("old.op.fe.c", "new.op.fe.c")
        )) {
          val oldFilepath = s"/home/aliraeis/Projekte/spl-tools/" +
            s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.spl$i/$oldFilename"
          val newFilepath = s"/home/aliraeis/Projekte/spl-tools/" +
            s"examples/hard/$newFeatureCount/coreutils.ls.6b01b71e.sat.spl$i/$newFilename"
          val seaPath = "/home/aliraeis/Projekte/seahorn/build/run/bin/sea"
          val z3Path = "/home/aliraeis/Projekte/seahorn/build/run/bin/z3"
          val typeChefBusyboxAnalysisPath = "/home/aliraeis/Projekte/TypeChef-BusyboxAnalysis"
          val args = Array(oldFilepath, newFilepath, "sortcmp", seaPath, z3Path, typeChefBusyboxAnalysisPath)

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
                  EquivalenceChecker.main(args)
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
                  EquivalenceCheckerV1.main(args)
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
                  BaselineEquivalenceChecker.main(args)
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

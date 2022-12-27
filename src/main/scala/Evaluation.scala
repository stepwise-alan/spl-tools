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
    val directory = s"/home/shuolin/$timeString"
    Files.createDirectories(Paths.get(directory))
    val filepath = s"$directory/results.txt"
    val bw = new BufferedWriter(new FileWriter(new File(filepath)))
    //    val timeout = 600000
    val timeout = 10.seconds
    for (i <- 1 to 1) {
      for ((oldFilename, newFilename) <- List(
//        ("old.c", "new.c"),
        ("old.fe.c", "new.fe.c"),
//        ("old.op.c", "new.op.c"),
//        ("old.op.fe.c", "new.op.fe.c")
      )) {
        bw.write(s"Alg 1, SPL $i, $oldFilename, $newFilename, ")
        print(s"Alg 1, SPL $i, $oldFilename, $newFilename, ")
        try {
          Await.result(Future {
            val t0 = currentTimeMillis()
            val outStream = new FileOutputStream(s"$directory/$i.$oldFilename.$newFilename.out.txt")
            val errStream = new FileOutputStream(s"$directory/$i.$oldFilename.$newFilename.err.txt")
            Console.withOut(outStream) {
              Console.withErr(errStream) {
                EquivalenceChecker.main(Array(
                  s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i/$oldFilename",
                  s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i/$newFilename",
                  "sortcmp",
                  "/home/shuolin/CLionProjects/seahorn/build/run/bin/sea",
                  "/home/shuolin/CLionProjects/seahorn/build/run/bin/z3",
                  "/home/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis",
                ))
              }
            }
            System.setOut(System.out)
            System.setErr(System.err)
            val t1 = currentTimeMillis()
            bw.write(s"${t1 - t0}")
            println(s"${t1 - t0}")
          }, timeout)
        } catch {
          case _: concurrent.TimeoutException =>
            bw.write("timeout")
            println("timeout")
          case e: Throwable =>
            bw.write("error")
            println("error")
            println(e.getMessage)
        }
        bw.write("\n")
        //
        //        try {
        //          runWithTimeout(timeout) {
        //            val t0 = currentTimeMillis()
        //            EquivalenceCheckerV1.main(Array(
        //              s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i/$oldFilename",
        //              s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i/$newFilename",
        //              "sortcmp",
        //              "/home/shuolin/CLionProjects/seahorn/build/run/bin/sea",
        //              "/home/shuolin/CLionProjects/seahorn/build/run/bin/z3",
        //              "/home/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis",
        //            ))
        //            val t1 = currentTimeMillis()
        //            s"Alg 1 & 2, SPL $i, $oldFilename, $newFilename, ${t1 - t0}"
        //          } match {
        //            case Some(value) =>
        //              bw.write(value)
        //            case None =>
        //              bw.write(s"Alg 1 & 2, SPL $i, $oldFilename, $newFilename, timeout")
        //          }
        //        } catch {
        //          case e: Throwable =>
        //            bw.write(e.getMessage)
        //        }
        //        try {
        //          runWithTimeout(timeout) {
        //            val t0 = currentTimeMillis()
        //            BaselineEquivalenceChecker.main(Array(
        //              s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i/$oldFilename",
        //              s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i/$newFilename",
        //              "sortcmp",
        //              "/home/shuolin/CLionProjects/seahorn/build/run/bin/sea",
        //              "/home/shuolin/CLionProjects/seahorn/build/run/bin/z3",
        //              "/home/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis",
        //            ))
        //            val t1 = currentTimeMillis()
        //            s"Baseline, SPL $i, $oldFilename, $newFilename, ${t1 - t0}"
        //          } match {
        //            case Some(value) =>
        //              bw.write(value)
        //            case None =>
        //              bw.write(s"Baseline, SPL $i, $oldFilename, $newFilename, timeout")
        //          }
        //        } catch {
        //          case e: Throwable =>
        //            bw.write(e.getMessage)
        //        }
      }
    }

    bw.close()
  }
}

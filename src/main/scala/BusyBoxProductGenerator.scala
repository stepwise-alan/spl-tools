object BusyBoxProductGenerator extends ProductGenerator with BusyBox {
  override val filePaths: Iterator[String] = List(
    "clever/sortcmp/new.c", "clever/sortcmp/old.c", "clever/sortcmp/merged_0.c").iterator
  def main(args: Array[String]): Unit = {
    run()
  }
}

#!/bin/bash

dir="./examples"
old_file=$dir/"old.c"
new_file=$dir/"new.c"
func_name="lib"

module="EquivalenceCheckerV1"



timeout_time="2m"

echo "Running module: "$module
echo "With old file: "$old_file
echo "With new file: "$new_file
echo "Under function: "$func_name
echo ""
echo "Timing out after: "$timeout_time
echo ""
echo "************************************"
timeout $timeout_time sbt "runMain $module $old_file $new_file $func_name"


# examples/toy/new.c
# examples/toy/old.c
# f
# /mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/sea
# /mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/z3
# /mnt/c/Users/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis

# sbt runMain "MutantGenerator"
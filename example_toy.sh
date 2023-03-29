#!/bin/bash

dir="./examples/toy"
old_file=$dir/"old.c"
new_file=$dir/"new.c"
func_name="lib"
z3_path="z3"
seahorn_path="./seahorn_docker_run.sh"
typechef_bb_path="./TypeChef-BusyboxAnalysis"

module="EquivalenceChecker"


timeout_time="2m"

echo "Running module: "$module
echo ""
echo "With old file: "$old_file
echo "With new file: "$new_file
echo "Under function: "$func_name
echo ""
echo "Z3 path: "$z3_path
echo "Seahorn path: "$seahorn_path
echo "TypeChef-BusyboxAnalysis path: "$typechef_bb_path
echo ""
echo "Timing out after: "$timeout_time
echo ""
echo "************************************"
cmd="timeout $timeout_time sbt \"runMain $module $old_file $new_file $func_name $z3_path $seahorn_path $typechef_bb_path\""
echo $cmd
# eval $cmd
timeout $timeout_time sbt "runMain $module $old_file $new_file $func_name $z3_path $seahorn_path $typechef_bb_path"


# examples/toy/new.c
# examples/toy/old.c
# f
# /mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/sea
# /mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/z3
# /mnt/c/Users/shuolin/IdeaProjects/TypeChef-BusyboxAnalysis

# sbt runMain "MutantGenerator"
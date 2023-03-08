#!/bin/bash

dir="./examples"
old_file=$dir/"old.c"
new_file=$dir/"new.c"
func_name="lib"

module="LiftedEquivalenceCheckerV1"

timeout_time="20s"

echo "Running module: "$module
echo "With old file: "$old_file
echo "With new file: "$new_file
echo "Under library: "$lib

echo "Timing out after: "$timeout_time

echo "************************************"
timeout $timeout_time sbt "runMain $module $old_file $new_file $lib"

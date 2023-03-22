# Lifted Functional Equivalence
CLEVER V2

## Introduction

TODO insert content

## Getting Started
### Prerequisites:
1. Clone the repository
   ```
   git clone https://github.com/stepwise-alan/spl-tools.git
   cd spl-tools
   ```

2. Install Python 3.10

3. Install the requirements
   ```
   pip3 install -r requirements.txt
   ```

4. Install [Scala](https://www.scala-lang.org/download/)
   ```
   # On Arch based Linux
   sudo pacman -S scala
   ```

5. Install [SBT](https://www.scala-sbt.org/download.html)
   ```
   # On Arch based Linux
   sudo pacman -S sbt
   ```

6. Install the latest fork of TypeChef
   ```
   cd ~
   git clone https://github.com/stepwise-alan/TypeChef
   # Follow the instruction in this repository to build from source

   # But this instruction should be something like
   cd TypeChef
   sbt publishLocal
   ```

7. Install [Seahorne](https://github.com/seahorn/seahorn/)

This may be any version, but we have used version [dev14](https://github.com/seahorn/seahorn/tree/dev14), and we recommend you to do the same. 

8. Install [Z3](https://github.com/Z3Prover/z3)

9. Install the Scala Requirements
   ```
   make build

   # alternatively you can run
   sbt # in the same directory as build.sbt
   ```

### To Run the other examples
10. Build the BusyBox analyser

Use the instructions for the Busybox analysis
You can just clone the model, and it should come with the model. 
If you would like to find other examples, follow the instructions and use this script to evaluate the examples ``BusyBoxAnalyzer.scala``

11. Build

## Usage

### Timeouts
In early usage, giving the wrong file, can clog your system into a non-halting situation. 
It is a good practice to prefix the commands with a ``timeout 2m``

### Arguments

TODO fill

#### Module

TODO fill

### Ways of Running

```shell
sbt "runMain EquivalenceCheckerV1 OLD_FILE NEW_FILE FUNCTION_NAME"
```
**where `OLD_FILE`/`NEW_FILE` is the path to the old/new file, and `FUNCTION_NAME` is the function name.**

You can also simply run
```
sbt run
# or equivalently
make run
```

There is a toy shell that runs the toy example. 
You can invoke it by running
```
make run_toy
```

or by running the file itself.
```
./example_toy.sh
```

## Examples
```shell
sbt "runMain LiftedEquivalenceCheckerV1 examples/toy/old.c examples/toy/new.c client"
```